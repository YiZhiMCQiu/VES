package cn.yizhimcqiu.vine.installer;

import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class VineLibInstaller {
    private static final String GITEE_REPOSITORY_RESOURCE = "https://gitee.com/yizhimcqiu/ves-resources/raw/master/libVES.jar";
    private static final String SAVE_PATH = "mods/libVES.jar";
    public static void install() {
        try {
            URL url = new URL(GITEE_REPOSITORY_RESOURCE);
            try (InputStream inputStream = url.openStream();
                 FileOutputStream outputStream = new FileOutputStream(SAVE_PATH)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
            throw new CrashException(new CrashReport("libVES.jar已下载完成，您可立即重启游戏。", null));
        } catch (IOException e) {
            throw new CrashException(new CrashReport("Error while installing libVES.jar!", e));
        }
    }
}
