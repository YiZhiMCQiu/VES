package cn.yizhimcqiu.ves.commands;

import static net.minecraft.server.command.CommandManager.*;

import cn.yizhimcqiu.ves.core.VESCommandHandler;
import cn.yizhimcqiu.ves.core.VEScriptExecutor;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;

import java.util.concurrent.CompletableFuture;

public class ExecuteScriptCommand {
    private static final SuggestionProvider<ServerCommandSource> SUGGESTION_PROVIDER = createSuggestionProvider();
    public void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("execscript")
                .then(argument("sid", StringArgumentType.greedyString()).suggests(SUGGESTION_PROVIDER).executes(context -> {
                    VESCommandHandler.onCommand(context);
                    return 1;
                }))));
    }
    private static SuggestionProvider<ServerCommandSource> createSuggestionProvider() {
        return (context, builder) -> {
            for (String sid : VEScriptExecutor.SCRIPT_IDENTIFIERS) {
                if (builder.getInput().isEmpty() || sid.startsWith(builder.getRemaining())) {
                    builder.suggest(sid);
                }
            }
            return CompletableFuture.supplyAsync(builder::build);
        };
    }
}
