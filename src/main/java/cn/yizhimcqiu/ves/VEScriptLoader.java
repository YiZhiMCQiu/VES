package cn.yizhimcqiu.ves;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class VEScriptLoader {
    public static final List<String> loadedScripts = new ArrayList<>();
    public static final String VERSION = "0.0.3";
    public static VEScriptLoader defaultLoader = new VEScriptLoader();
    private Context context;
    public VEScriptLoader() {
        this.initContext();
    }
    public void initContext() {
        this.context = Context.newBuilder("js").allowAllAccess(true).build();
    }
    @Deprecated(forRemoval = true)
    public static void loads() throws IOException {
        File scriptFolder = new File("ves");
        VEScriptLoader loader = new VEScriptLoader();
        loader.context.eval(loader.createSource("console.info(\"._VES version "+VERSION+"\");", "init.mjs"));
        if (!scriptFolder.exists()) {
            scriptFolder.mkdir();
        }
        for (File file : scriptFolder.listFiles()) {
            if (file.isDirectory()) {
                if (!file.getName().startsWith(".")) {
                    //loader.load(file.getName());
                }
            }
        }
    }
    public VESLoadContext load(String name, CommandExecuteContext executeContext) {
        if (name.startsWith(".")) {
            return new VESLoadContext(false, "Invalid script name", null);
        }
        try {
            Source init = createSource(createFile(Path.of("ves", ".ves_builtin", "init.mjs").toAbsolutePath()));
            Source source = createSource(createFile(Path.of("ves", name, "main.mjs").toAbsolutePath()));

            context.getBindings("js").putMember("context", executeContext);
            context.getBindings("js").putMember("IS_DEVELOP", VentiScriptMod.isDevelop);
            context.getBindings("js").putMember("builtin", "(name)=>\"../.ves/builtin/\"+name+\".mjs\"");

            this.context.eval(init);
            loadedScripts.add(name);
            return VESLoadContext.success(this.context.eval(source).toString());
        } catch (Exception e) {
            VentiScriptMod.LOGGER.error("Error while loading script: ", e);
            return new VESLoadContext(false, e.getMessage(), e);
        } catch (Error e) {
            return new VESLoadContext(false, e.getMessage(), e);
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
    public static class VESLoadContext {
        public final boolean success;
        public final String message;
        public final Throwable throwable;
        VESLoadContext(boolean success, String message, Throwable throwable) {
            this.success = success;
            this.message = message;
            this.throwable = throwable;
        }
        private static VESLoadContext success(String message) {
            return new VESLoadContext(true, message, null);
        }
    }
}
