package cn.yizhimcqiu.ves.commands;

import static net.minecraft.server.command.CommandManager.*;

import cn.yizhimcqiu.ves.CommandExecuteContext;
import cn.yizhimcqiu.ves.core.VEScriptExecutor;
import cn.yizhimcqiu.vine.VineExecuteExceptionHandler;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ExecuteScriptCommand {
    private static final SuggestionProvider<ServerCommandSource> SUGGESTION_PROVIDER = createSuggestionProvider();
    public void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("execscript")
                .then(argument("sid", StringArgumentType.greedyString()).suggests(SUGGESTION_PROVIDER).executes(context -> {
                    final VEScriptExecutor.VESExecuteResult[] result = { null };
                    String sid = StringArgumentType.getString(context, "sid");
                    context.getSource().sendFeedback(() -> Text.translatable("execute.feedback.start", sid), false);
                    new Thread(() -> {
                        CommandExecuteContext cec = new CommandExecuteContext(context.getSource().getPlayer(), context.getSource());
                        Supplier<VEScriptExecutor.VESExecuteResult> sup = () -> VEScriptExecutor.defaultLoader.execute(sid.split("::")[0], sid.split("::")[1], cec);
                        VEScriptExecutor.defaultLoader.initContext();
                        result[0] = sup.get();
                        if (result[0].success) {
                            context.getSource().sendFeedback(() -> Text.translatable("execute.feedback.success"), false);
                        } else {
                            context.getSource().sendError(Text.translatable("execute.feedback.fail"));
                            MutableText errorMessage = VineExecuteExceptionHandler.createErrorMessage(result[0].throwable, (ServerPlayerEntity) context.getSource().getEntity());
                            HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal(result[0].throwable.getMessage()).styled((style1) -> style1.withColor(Formatting.RED)));
                            errorMessage = errorMessage.styled(style -> style.withHoverEvent(hoverEvent));
                            context.getSource().sendMessage(errorMessage);
                        }
                    }).start();
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
