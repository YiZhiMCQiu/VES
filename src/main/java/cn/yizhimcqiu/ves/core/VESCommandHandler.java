package cn.yizhimcqiu.ves.core;

import cn.yizhimcqiu.ves.CommandExecuteContext;
import cn.yizhimcqiu.vine.Vine;
import cn.yizhimcqiu.vine.VineExecuteExceptionHandler;
import cn.yizhimcqiu.vine.dependency.VineDependencyResolver;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class VESCommandHandler {
    public static void onCommand(CommandContext<ServerCommandSource> context) {
        String sid = StringArgumentType.getString(context, "sid");
        context.getSource().sendMessage(Text.translatable("execute.feedback.start", sid));
        new Thread(() -> {
            List<String> md;
            if ((md = VineDependencyResolver.getMissingDependencies(sid)) != null) {
                context.getSource().sendMessage(
                        Text.of(Vine.PREFIX+"缺失以下依赖项: "+(String.join(", ", md))));
                return;
            }
            CommandExecuteContext cec = new CommandExecuteContext(context.getSource().getPlayer(), context.getSource());
            VEScriptExecutor.defaultLoader.initContext();
            VEScriptExecutor.VESExecuteResult result = VEScriptExecutor.defaultLoader.execute(sid.split("::")[0], sid.split("::")[1], cec);
            if (result.success) {
                context.getSource().sendMessage(Text.translatable("execute.feedback.success"));
            } else {
                context.getSource().sendError(Text.translatable("execute.feedback.fail"));
                MutableText errorMessage = VineExecuteExceptionHandler.createErrorMessage(result.throwable);
                HoverEvent hoverEvent = createErrorHoverEvent(result.message);
                errorMessage = errorMessage.styled(style -> style.withHoverEvent(hoverEvent));
                context.getSource().sendMessage(errorMessage);
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
