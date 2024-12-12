package cn.yizhimcqiu.ves.commands;

import static net.minecraft.server.command.CommandManager.*;

import cn.yizhimcqiu.ves.CommandExecuteContext;
import cn.yizhimcqiu.ves.VEScriptLoader;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;

public class LoadScriptCommand {
    public void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("loadscript")
                .then(argument("name", StringArgumentType.word()).executes(context -> {
                    VEScriptLoader.defaultLoader.initContext();
                    boolean result = VEScriptLoader.defaultLoader.load(StringArgumentType.getString(context, "name"),
                            new CommandExecuteContext(context.getSource().getPlayer(), context.getSource())).success;
                    if (result) {
                        context.getSource().sendFeedback(() -> Text.translatable("execute.feedback.success"), false);
                    } else {
                        context.getSource().sendError(Text.translatable("execute.feedback.fail"));
                    }
                    return result ? 1 : 0;
                }))));
    }
}
