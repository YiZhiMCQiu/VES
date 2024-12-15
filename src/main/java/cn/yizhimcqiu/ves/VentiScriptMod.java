package cn.yizhimcqiu.ves;

import cn.yizhimcqiu.ves.commands.LoadScriptCommand;
import cn.yizhimcqiu.ves.core.VEScriptExecutor;
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
        try {
            Class.forName("net.minecraft.class_2561");
        } catch (ClassNotFoundException e) {
            isDevelop = true;
            LOGGER.info("Development environment detected");
            updateVES();
        }
        VEScriptExecutor.initialize();
    }
    private void registerCommands() {
        new LoadScriptCommand().register();
    }
    public static void updateVES(Object... args) {
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
}
