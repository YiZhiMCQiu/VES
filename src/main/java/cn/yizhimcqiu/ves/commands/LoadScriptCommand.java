package cn.yizhimcqiu.ves.commands;

import static net.minecraft.server.command.CommandManager.*;

import cn.yizhimcqiu.ves.CommandExecuteContext;
import cn.yizhimcqiu.ves.core.VEScriptExecutor;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class LoadScriptCommand {
    public void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("loadscript")
                .then(argument("name", StringArgumentType.greedyString()).suggests(createSuggestionProvider()).executes(context -> {
                    AtomicBoolean result = new AtomicBoolean(true);
                    String name = StringArgumentType.getString(context, "name");
                    context.getSource().sendFeedback(() -> Text.translatable("execute.feedback.start", name), false);
                    new Thread(() -> {
                        Supplier<VEScriptExecutor.VESExecuteResult> sup;
                        CommandExecuteContext cec = new CommandExecuteContext(context.getSource().getPlayer(), context.getSource());
                        if (name.split("::")[0].equals("ves")) {
                            sup = () -> VEScriptExecutor.defaultLoader.execute(name.split("::")[1], cec);
                        } else {
                            sup = () -> VEScriptExecutor.defaultLoader.execute(VEScriptExecutor.MOD_VES_PATHS.get(name.split("::")[0]), cec);
                        }
                        VEScriptExecutor.defaultLoader.initContext();
                        result.set(sup.get().success);
                        if (result.get()) {
                            context.getSource().sendFeedback(() -> Text.translatable("execute.feedback.success"), false);
                        } else {
                            context.getSource().sendError(Text.translatable("execute.feedback.fail"));
                        }
                    }).start();
                    return result.get() ? 1 : 0;
                }))));
    }
    private static SuggestionProvider<ServerCommandSource> createSuggestionProvider() {
        return (context, builder) -> {
            VEScriptExecutor.MOD_VES_PATHS.keySet().forEach(name -> builder.suggest(name+"::main"));
            return CompletableFuture.supplyAsync(builder::build);
        };
    }
}
