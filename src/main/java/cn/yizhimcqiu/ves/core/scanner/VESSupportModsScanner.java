package cn.yizhimcqiu.ves.core.scanner;

import cn.yizhimcqiu.ves.VentiScriptMod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class VESSupportModsScanner {
    public static Map<String, Path> scan() {
        Map<String, Path> supportMods = new HashMap<>();
        try (Stream<Path> files = Files.list(Path.of("mods"))) {
            for (Path modPath : files.toList()) {
                Path modVESFolder = getModVESFolder(createFMJPath(modPath));
                if (modVESFolder != null) {
                    supportMods.put(modVESFolder.getFileName().toString(), modVESFolder);
                }
            }
        } catch (IOException e) {
            VentiScriptMod.LOGGER.error("Error while obtaining the mod list", e);
        }
        return supportMods;
    }
    private static Path getModVESFolder(Path fmjPath) {
        if (fmjPath == null) {
            return null;
        }
        if (!Files.exists(fmjPath)) {
            VentiScriptMod.LOGGER.error("{} is not exists!", fmjPath);
            VentiScriptMod.LOGGER.debug("这玩意连fabric模组都不是?");
            return null;
        }
        Gson gson = new Gson();
        try {
            Map<String, Object> fmj = gson.fromJson(Files.readString(fmjPath), new TypeToken<>(){});
            if (!fmj.containsKey("ves")) {
                VentiScriptMod.LOGGER.debug("{} is not supported VES", fmj.get("id"));
                return null;
            }
            return fmjPath.getFileSystem().getPath(fmj.get("ves").toString());
        } catch (FileNotFoundException e) {
            VentiScriptMod.LOGGER.error("{} is not exists!", fmjPath);
            return null;
        } catch (IOException e) {
            VentiScriptMod.LOGGER.error("Error while reading {}", fmjPath, e);
            return null;
        }
    }
    private static Path createFMJPath(Path jarPath) {
        URI uri = URI.create("jar:"+jarPath.toAbsolutePath().toUri());
        FileSystem zipFs = FileSystems.getFileSystem(uri);
        return zipFs.getPath("fabric.mod.json");
    }
}
