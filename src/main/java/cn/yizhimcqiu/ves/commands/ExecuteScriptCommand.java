package cn.yizhimcqiu.ves.commands;

import static net.minecraft.server.command.CommandManager.*;

import cn.yizhimcqiu.ves.core.VESCommandHandler;
import cn.yizhimcqiu.ves.core.VEScriptExecutor;
import cn.yizhimcqiu.ves.util.ModPersistentStates;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.concurrent.CompletableFuture;

public class ExecuteScriptCommand {
    private static final SuggestionProvider<ServerCommandSource> SUGGESTION_PROVIDER = createSuggestionProvider();
    public void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("execscript")
                .then(argument("sid", StringArgumentType.greedyString()).suggests(SUGGESTION_PROVIDER).executes(context -> {
                    VESCommandHandler.onCommand(context);
                    try {
                        ServerPlayerEntity player = context.getSource().getPlayer();
                        ModPersistentStates.instance.customBlockMap.put(new BlockPos(
                                (int) Math.round(player.getX()), (int) Math.round(player.getY()),(int) Math.round(player.getZ())
                                ), "test");
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
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
