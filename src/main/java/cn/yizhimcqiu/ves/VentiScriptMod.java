package cn.yizhimcqiu.ves;

import cn.yizhimcqiu.ves.ci.CustomItemManager;
import cn.yizhimcqiu.ves.commands.ExecuteScriptCommand;
import cn.yizhimcqiu.ves.commands.VESCommand;
import cn.yizhimcqiu.ves.core.VEScriptExecutor;
import cn.yizhimcqiu.ves.ci.items.components.VESDataComponentTypes;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class VentiScriptMod implements ModInitializer {
    public static final String MOD_ID = "ves";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static boolean isDevelop = false;
    @Override
    public void onInitialize() {
        this.registerCommands();
        this.updateEnvironment();

        checkVESFolder();
        VEScriptExecutor.initialize();
        VESDataComponentTypes.initialize();

        CustomItemManager.initialize();
    }

    private void updateEnvironment() {
        try {
            Class.forName("net.minecraft.entity.mob.CreeperEntity");
            isDevelop = true;
            LOGGER.info("Development environment detected");
            updateVES();
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
    private void checkVESFolder() {
        Path path = Path.of("ves");
        try {
            if (Files.exists(path)) {
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
