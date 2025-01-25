package cn.yizhimcqiu.ves.core;

import cn.yizhimcqiu.ves.ScriptExecuteContext;
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
            List<String> md; // 缺失的依赖项列表
            if ((md = VineDependencyResolver.getMissingDependencies(sid)) != null) {
                context.getSource().sendMessage(
                        Text.of(Vine.PREFIX+"缺失以下依赖项: "+(String.join(", ", md))));
                return;
            }
            ScriptExecuteContext cec = new ScriptExecuteContext(context.getSource().getPlayer(), context.getSource()); // 创建脚本执行上下文
            VEScriptExecutor.VESExecuteResult result = VEScriptExecutor.defaultExecutor.execute(sid, cec); // 执行脚本
            if (result.success) {
                context.getSource().sendMessage(Text.translatable("execute.feedback.success"));
            } else {
                context.getSource().sendError(Text.translatable("execute.feedback.fail"));
                MutableText errorMessage = VineExecuteExceptionHandler.createErrorMessage(result.throwable); // 扔给Vine并获取错误信息
                HoverEvent hoverEvent = createErrorHoverEvent(result.message); // 创建用于显示完整错误信息的悬停事件
                errorMessage = errorMessage.styled(style -> style.withHoverEvent(hoverEvent)); // 添加悬停事件
                context.getSource().sendMessage(errorMessage); // 发送错误信息
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
