package cn.yizhimcqiu.ves.client.core;

import cn.yizhimcqiu.ves.CommandExecuteContext;
import cn.yizhimcqiu.ves.core.VEScriptExecutor;
import cn.yizhimcqiu.vine.Vine;
import cn.yizhimcqiu.vine.VineExecuteExceptionHandler;
import cn.yizhimcqiu.vine.dependency.VineDependencyResolver;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class VESClientCommandHandler {
    public static void onCommand(CommandContext<FabricClientCommandSource> context) {
        String sid = StringArgumentType.getString(context, "sid");
        context.getSource().sendFeedback(Text.translatable("execute.feedback.start", sid));
        new Thread(() -> {
            List<String> md;
            if ((md = VineDependencyResolver.getMissingDependencies(sid)) != null) {
                context.getSource().sendFeedback(
                        Text.of(Vine.PREFIX+"缺失以下依赖项: "+(String.join(", ", md))));
                return;
            }
            CommandExecuteContext cec = null;
            VEScriptExecutor.defaultLoader.initContext();
            VEScriptExecutor.VESExecuteResult result = VEScriptExecutor.defaultLoader.execute(sid.split("::")[0], sid.split("::")[1], cec);
            if (result.success) {
                context.getSource().sendFeedback(Text.translatable("execute.feedback.success"));
            } else {
                context.getSource().sendError(Text.translatable("execute.feedback.fail"));
                MutableText errorMessage = VineExecuteExceptionHandler.createErrorMessage(result.throwable);
                HoverEvent hoverEvent = createErrorHoverEvent(result.message);
                errorMessage = errorMessage.styled(style -> style.withHoverEvent(hoverEvent));
                context.getSource().sendFeedback(errorMessage);
            }
        }).start();
    }
    private static MutableText withRedColor(MutableText text) {
        return text.styled((style) -> style.withColor(Formatting.RED));
    }
    private static HoverEvent createErrorHoverEvent(String message) {
        return new HoverEvent(HoverEvent.Action.SHOW_TEXT, withRedColor(Text.literal(message)));
    }
}
