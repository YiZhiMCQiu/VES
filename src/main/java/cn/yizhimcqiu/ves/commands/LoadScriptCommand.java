package cn.yizhimcqiu.ves.commands;

import static net.minecraft.server.command.CommandManager.*;

import cn.yizhimcqiu.ves.CommandExecuteContext;
import cn.yizhimcqiu.ves.VEScriptExecutor;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;

import java.util.concurrent.atomic.AtomicBoolean;

public class LoadScriptCommand {
    public void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("loadscript")
                .then(argument("name", StringArgumentType.word()).executes(context -> {
                    AtomicBoolean result = new AtomicBoolean(true);
                    String name = StringArgumentType.getString(context, "name");
                    context.getSource().sendFeedback(() -> Text.translatable("execute.feedback.start", name), false);
                    new Thread(() -> {
                        VEScriptExecutor.defaultLoader.initContext();
                        result.set(VEScriptExecutor.defaultLoader.execute(name,
                                new CommandExecuteContext(context.getSource().getPlayer(), context.getSource())).success);
                        if (result.get()) {
                            context.getSource().sendFeedback(() -> Text.translatable("execute.feedback.success"), false);
                        } else {
                            context.getSource().sendError(Text.translatable("execute.feedback.fail"));
                        }
                    }).start();
                    return result.get() ? 1 : 0;
                }))));
    }
}
