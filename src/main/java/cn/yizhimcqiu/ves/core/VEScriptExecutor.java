package cn.yizhimcqiu.ves.core;

import cn.yizhimcqiu.ves.CommandExecuteContext;
import cn.yizhimcqiu.ves.VentiScriptMod;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class VEScriptExecutor {
    public static VEScriptExecutor defaultLoader = new VEScriptExecutor();
    public static final List<String> SCRIPT_IDENTIFIERS = new ArrayList<>();
    private Context context;
    public VEScriptExecutor() {
        this.initContext();
    }
    public void initContext() {
        this.context = Context.newBuilder("js")
                .allowAllAccess(true)
                .currentWorkingDirectory(Path.of("ves").toAbsolutePath())
                .build();
    }
    public VESExecuteResult execute(String namespace, String name, CommandExecuteContext executeContext) {
        return this.execute(Path.of("ves", namespace, name+".mjs").toAbsolutePath(), executeContext);
    }
    public VESExecuteResult execute(Path path, CommandExecuteContext executeContext) {
        if (path.getFileName().startsWith(".")) {
            return new VESExecuteResult(false, "Invalid script name", null);
        }
        try {
            return execute(createSource(createFile(path.toAbsolutePath())), executeContext);
        } catch (Exception e) {
            VentiScriptMod.LOGGER.error("Error while loading script: ", e);
            return new VESExecuteResult(false, e.getMessage(), e);
        } catch (Error e) {
            return new VESExecuteResult(false, e.getMessage(), e);
        }
    }
    private VESExecuteResult execute(Source source, CommandExecuteContext context) {
        this.context.getBindings("js").putMember("_context", context);
        this.context.getBindings("js").putMember("IS_DEVELOP", VentiScriptMod.isDevelop);
        this.context.getBindings("js").putMember("builtin", "(name)=>\"../.ves/builtin/\"+name+\".mjs\"");

        return VESExecuteResult.success(this.context.eval(source).toString());
    }
    private Source createSource(String script, String name) throws IOException {
        return Source.newBuilder("js", script, name)
                .mimeType("application/javascript+module")
                .encoding(StandardCharsets.UTF_8)
                .build();
    }
    private Source createSource(File file) throws IOException {
        return this.createSource(this.readScript(file.toPath()), file.getName());
    }
    private String readScript(Path path) throws IOException {
        return Files.readString(path);
    }
    private File createFile(Path path) {
        return new File(path.toUri());
    }
    public static class VESExecuteResult {
        public final boolean success;
        public final String message;
        public final Throwable throwable;
        VESExecuteResult(boolean success, String message, Throwable throwable) {
            this.success = success;
            this.message = message;
            this.throwable = throwable;
        }
        private static VESExecuteResult success(String message) {
            return new VESExecuteResult(true, message, null);
        }
    }
    public static void initialize() {
        try (Stream<Path> scriptFolders = Files.list(Path.of("ves")).filter(path ->
                !(path.getFileName().toString().charAt(0) == '.') && path.toFile().isDirectory())) {
            for (Path path : scriptFolders.toList()) {
                for (File file : Objects.requireNonNull(path.toFile().listFiles())) {
                    if (file.isFile() && file.getName().split("\\.")[1].equals("mjs")) {
                        String s = path.getFileName() + "::" + file.getName().substring(0, file.getName().lastIndexOf('.'));
                        SCRIPT_IDENTIFIERS.add(s);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
