package cn.yizhimcqiu.vine.installer;

import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * VES安装器
 */
public class VineBuiltinInstaller {
    private static final String GITHUB_PAGES_RESOURCE = "https://yizhimcqiu.github.io/resources/ves_builtin.zip";
    private static final String GITEE_REPOSITORY_RESOURCE = "https://gitee.com/yizhimcqiu/ves-resources/raw/master/ves-builtin.zip";
    private static final String ZIP_SAVE_PATH = "ves_builtin.zip";
    private static final String EXTRACT_PATH = ".";
    @SuppressWarnings("deprecation")
    private static void download(String resourcePath) throws IOException {
        URL url = new URL(resourcePath);
        try (ReadableByteChannel rbc = Channels.newChannel(url.openStream());
             FileOutputStream fos = new FileOutputStream(ZIP_SAVE_PATH)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
    }
    private static void unzip() throws IOException {
        File file = new File(EXTRACT_PATH);
        if (file.exists() || file.mkdir());
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(ZIP_SAVE_PATH))) {
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                String filePath = EXTRACT_PATH + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    extractFile(zipIn, filePath);
                } else {
                    File dir = new File(filePath);
                    dir.mkdir();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
    }
    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[4096];
            int read;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }
    }
    private static void clean() {
        File file = new File(ZIP_SAVE_PATH);
        if (file.exists() && file.delete());
    }
    public static void install() throws IOException {
        try {
            download(GITEE_REPOSITORY_RESOURCE);
            unzip();
            clean();
        } catch (IOException e1) {
            /*
             * 不会有人访问Gitee还会出问题吧……
             */
            try {
                download(GITHUB_PAGES_RESOURCE);
                unzip();
                clean();
            } catch (IOException e2) {
                throw new CrashException(new CrashReport("Error while installing VES!", e2));
            }
        }
    }
}
