package cn.yizhimcqiu.ves.core;

import cn.yizhimcqiu.ves.ScriptExecuteContext;
import cn.yizhimcqiu.ves.VESManifest;
import cn.yizhimcqiu.ves.VentiScriptMod;
import com.google.gson.Gson;
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
    public static final Path VES_PATH = Path.of("ves").toAbsolutePath();
    public static VEScriptExecutor defaultExecutor = new VEScriptExecutor();
    public static final List<String> SCRIPT_IDENTIFIERS = new ArrayList<>();
    public static final List<String> SCRIPT_NAMESPACES = new ArrayList<>();
    private Context context;

    public VEScriptExecutor() {
        this.initContext();
    }

    public void initContext() {
        this.context = Context.newBuilder("js")
                .allowAllAccess(true)
                .currentWorkingDirectory(VES_PATH)
                .build();
    }

    public VESExecuteResult execute(String sid, ScriptExecuteContext executeContext) {
        return this.execute(Path.of("ves", sid.split("::")[0], sid.split("::")[1] + ".mjs").toAbsolutePath(), executeContext);
    }

    public VESExecuteResult execute(String namespace, String name, ScriptExecuteContext executeContext) {
        return this.execute(Path.of("ves", namespace, name + ".mjs").toAbsolutePath(), executeContext);
    }

    public VESExecuteResult execute(Path path, ScriptExecuteContext executeContext) {
        if (this.isInvalidScriptName(path)) {
            return new VESExecuteResult(false, "Invalid script name", null);
        }
        try {
            return execute(createSource(createFile(path.toAbsolutePath())), executeContext);
        } catch (Throwable t) {
            VentiScriptMod.LOGGER.error("Error while loading script: ", t);
            return new VESExecuteResult(false, t.getMessage(), t);
        }
    }

    protected boolean isInvalidScriptName(Path path) {
        return path.getFileName().startsWith(".");
    }

    private VESExecuteResult execute(Source source, ScriptExecuteContext context) {
        this.context.getBindings("js").putMember("_context", context); // 声明Java的context
        this.context.getBindings("js").putMember("IS_DEVELOP", VentiScriptMod.isDevelop); // 声明环境类型
        String res = this.context.eval(source).toString();
        if (res.contains("[[PromiseStatus]]: \"rejected\"")) {
            throw new RuntimeException("Promise is rejected: " + res); // 直接扔给Vine处理
        }
        return VESExecuteResult.success(res);
    }

    public static Source createSource(String script, String name) throws IOException {
        return Source.newBuilder("js", script, name)
                .mimeType("application/javascript+module")
                .encoding(StandardCharsets.UTF_8)
                .build();
    }

    public static Source createSource(File file) throws IOException {
        return createSource(readScript(file.toPath()), file.getName());
    }

    public static String readScript(Path path) throws IOException {
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
        try (Stream<Path> scriptFolders = Files.list(VES_PATH).filter(path ->
                !(path.getFileName().toString().charAt(0) == '.') && path.toFile().isDirectory())) {
            for (Path path : scriptFolders.toList()) {
                SCRIPT_NAMESPACES.add(path.getFileName().toString());
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

    public static VESManifest readManifest(String sid) {
        Gson gson = new Gson();
        Path scriptFolder = Path.of("ves", sid.split("::")[0]);
        File manifest = scriptFolder.resolve("manifest.json").toFile();
        if (!manifest.exists()) {
            VentiScriptMod.LOGGER.error("{} is not exist", manifest);
        }
        if (manifest.isDirectory()) {
            VentiScriptMod.LOGGER.error("{} is not a file", manifest);
        }
        try (FileReader reader = new FileReader(manifest)) {
            return gson.fromJson(reader, VESManifest.class);
        } catch (IOException e) {
            VentiScriptMod.LOGGER.error("Failed to load manifest.json", e);
        }
        return null;
    }
}
