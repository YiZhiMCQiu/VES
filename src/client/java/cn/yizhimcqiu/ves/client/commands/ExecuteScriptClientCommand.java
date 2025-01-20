package cn.yizhimcqiu.ves.client.commands;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

import cn.yizhimcqiu.ves.client.core.VESClientCommandHandler;
import cn.yizhimcqiu.ves.core.VEScriptExecutor;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import java.util.concurrent.CompletableFuture;

public class ExecuteScriptClientCommand {
    private static final SuggestionProvider<FabricClientCommandSource> SUGGESTION_PROVIDER = createSuggestionProvider();
    public void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(literal("execscript")
                .then(argument("sid", StringArgumentType.greedyString()).suggests(SUGGESTION_PROVIDER).executes(context -> {
                    VESClientCommandHandler.onCommand(context);
                    return 1;
                }))));
    }
    private static SuggestionProvider<FabricClientCommandSource> createSuggestionProvider() {
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