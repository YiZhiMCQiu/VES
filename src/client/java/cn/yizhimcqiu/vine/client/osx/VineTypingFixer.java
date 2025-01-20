package cn.yizhimcqiu.vine.client.osx;

public class VineTypingFixer {
    public static boolean isMacOS() {
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }

}
