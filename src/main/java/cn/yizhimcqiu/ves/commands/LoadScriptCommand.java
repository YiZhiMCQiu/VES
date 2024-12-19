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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class LoadScriptCommand {
    private static final SuggestionProvider<ServerCommandSource> SUGGESTION_PROVIDER = createSuggestionProvider();
    public void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("loadscript")
                .then(argument("name", StringArgumentType.greedyString()).suggests(SUGGESTION_PROVIDER).executes(context -> {
                    final VEScriptExecutor.VESExecuteResult[] result = { null };
                    String name = StringArgumentType.getString(context, "name");
                    context.getSource().sendFeedback(() -> Text.translatable("execute.feedback.start", name), false);
                    new Thread(() -> {
                        CommandExecuteContext cec = new CommandExecuteContext(context.getSource().getPlayer(), context.getSource());
                        Supplier<VEScriptExecutor.VESExecuteResult> sup = () -> VEScriptExecutor.defaultLoader.execute(name.split("::")[0], name.split("::")[1], cec);
                        VEScriptExecutor.defaultLoader.initContext();
                        result[0] = sup.get();
                        if (result[0].success) {
                            context.getSource().sendFeedback(() -> Text.translatable("execute.feedback.success"), false);
                        } else {
                            context.getSource().sendError(Text.translatable("execute.feedback.fail", result[0].message));
                        }
                    }).start();
                    return 1;
                }))));
    }
    private static SuggestionProvider<ServerCommandSource> createSuggestionProvider() {
        return (context, builder) -> {
            try (Stream<Path> scriptFolders = Files.list(Path.of("ves")).filter(path ->
                    !(path.getFileName().toString().charAt(0) == '.') && path.toFile().isDirectory())) {
                for (Path path : scriptFolders.toList()) {
                    for (File file : path.toFile().listFiles()) {
                        if (file.isFile() && file.getName().split("\\.")[1].equals("mjs")) {
                            String s = path.getFileName() + "::" + file.getName().substring(0, file.getName().lastIndexOf('.'));
                            if (s.startsWith(builder.getInput()) || builder.getInput().isEmpty()) {
                                builder.suggest(s);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return CompletableFuture.supplyAsync(builder::build);
        };
    }
}
