package cn.yizhimcqiu.ves;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class VEScriptExecutor {
    public static final List<String> loadedScripts = new ArrayList<>();
    public static final String VERSION = "0.0.3";
    public static VEScriptExecutor defaultLoader = new VEScriptExecutor();
    private Context context;
    public VEScriptExecutor() {
        this.initContext();
    }
    public void initContext() {
        this.context = Context.newBuilder("js").allowAllAccess(true).build();
    }
    public VESExecuteResult execute(String name, CommandExecuteContext executeContext) {
        if (name.startsWith(".")) {
            return new VESExecuteResult(false, "Invalid script name", null);
        }
        try {
            Source init = createSource(createFile(Path.of("ves", ".ves_builtin", "init.mjs").toAbsolutePath()));
            Source source = createSource(createFile(Path.of("ves", name, "main.mjs").toAbsolutePath()));

            context.getBindings("js").putMember("context", executeContext);
            context.getBindings("js").putMember("IS_DEVELOP", VentiScriptMod.isDevelop);
            context.getBindings("js").putMember("builtin", "(name)=>\"../.ves/builtin/\"+name+\".mjs\"");

            this.context.eval(init);
            loadedScripts.add(name);
            return VESExecuteResult.success(this.context.eval(source).toString());
        } catch (Exception e) {
            VentiScriptMod.LOGGER.error("Error while loading script: ", e);
            return new VESExecuteResult(false, e.getMessage(), e);
        } catch (Error e) {
            return new VESExecuteResult(false, e.getMessage(), e);
        }
    }
    private Source createSource(String script, String name) throws IOException {
        return Source.newBuilder("js", script, name).mimeType("application/javascript+module").build();
    }
    private Source createSource(File file) throws IOException {
        return Source.newBuilder("js", file).mimeType("application/javascript+module").build();
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
}
