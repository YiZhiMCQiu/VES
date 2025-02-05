package cn.yizhimcqiu.ves;

import cn.yizhimcqiu.ves.ci.CustomItemManager;
import cn.yizhimcqiu.ves.commands.ExecuteScriptCommand;
import cn.yizhimcqiu.ves.commands.VESCommand;
import cn.yizhimcqiu.ves.core.VEScriptExecutor;
import cn.yizhimcqiu.ves.ci.items.components.VESDataComponentTypes;
import cn.yizhimcqiu.vine.installer.VineBuiltinInstaller;
import cn.yizhimcqiu.vine.installer.VineLibInstaller;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class VentiScriptMod implements ModInitializer {
    public static final String MOD_ID = "ves";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static final String VERSION_URL = "https://gitee.com/yizhimcqiu/ves-resources/raw/master/version.properties";
    public static boolean isDevelop = false;
    private static Properties latestVersion;
    private static long lastUpdateLatestVersion = 0;
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private static final ModContainer VES = FabricLoader.getInstance().getModContainer("ves").get();
    private static List<ModMetadata> installedPlugins;
    @Override
    public void onInitialize() {
        this.registerCommands();
        this.updateEnvironment();

        before();

        VEScriptExecutor.initialize();
        VESDataComponentTypes.initialize();
        CustomItemManager.initialize();
    }

    private void updateEnvironment() {
        try {
            Class.forName("net.minecraft.entity.mob.CreeperEntity");
            isDevelop = true;
            LOGGER.info("Development environment detected");
            // updateVES();
        } catch (ClassNotFoundException ignored) { }
    }

    private void registerCommands() {
        new ExecuteScriptCommand().register();
        new VESCommand().register();
    }
    public static void updateVES() {
        try {
            copyFolder("../ves", "ves");
            LOGGER.info("Updated VES");
        } catch (IOException e) {
            LOGGER.error("Error while copying script folder", e);
        }
    }
    private static void copyFolder(String sourceFolderPath, String destinationFolderPath) throws IOException {
        Path sourcePath = Paths.get(sourceFolderPath);
        Path destinationPath = Paths.get(destinationFolderPath);

        Files.walkFileTree(sourcePath, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Files.createDirectories(destinationPath.resolve(sourcePath.relativize(dir)));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.copy(file, destinationPath.resolve(sourcePath.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });
    }
    private void before() {
        System.setProperty("ves.is_develop", "true");

        checkVESFolder();
        checkLibVES();
        if (getLatestVersion() != null) {
            if (Long.parseLong(latestVersion.getProperty("build_time"))
                    > Long.parseLong(VESVersion.BUILD_TIME)) {
                LOGGER.warn("VES has update available: {}", latestVersion.getProperty("build_time"));
            }
        }
    }
    private void checkVESFolder() {
        Path path = Path.of("ves");
        try {
            if (!Files.exists(path)) {
                // Files.createDirectory(path);
                VineBuiltinInstaller.install();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void checkLibVES() {
        if (!Files.exists(Path.of("mods", "libVES.jar")) && !isDevelop) {
            VineLibInstaller.install();
        }
    }

    /**
     * 获取Gitee上version.properties中的内容并加载为Properties
     * @return 一个Properties，若无更新或发生错误则返回null
     */
    @SuppressWarnings("unused")
    public static Properties getLatestVersion() {
        if (latestVersion == null || lastUpdateLatestVersion - System.currentTimeMillis() > 30000) {
            lastUpdateLatestVersion = System.currentTimeMillis();
            try (InputStream stream = new URL(VERSION_URL).openStream();
                 InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                Properties props = new Properties();
                props.load(reader);
                latestVersion = props;
                return props;
            } catch (IOException e) {
                LOGGER.error("Error while loading version!", e);
            }
            return null;
        }
        return latestVersion;
    }

    public static List<String> getInstalledPlugins() {
        if (installedPlugins == null) {
            installedPlugins = new ArrayList<>();
            for (ModContainer mod : VES.getContainedMods()) {
                installedPlugins.add(mod.getMetadata());
            }
        }
        return installedPlugins.stream().map(ModMetadata::getId).toList();
    }

    public static List<ModMetadata> getInstalledPluginsMetadata() {
        if (installedPlugins == null) {
            installedPlugins = new ArrayList<>();
            for (ModContainer mod : VES.getContainedMods()) {
                installedPlugins.add(mod.getMetadata());
            }
        }
        return installedPlugins;
    }
}
