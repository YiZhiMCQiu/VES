package cn.yizhimcqiu.ves.util;

@SuppressWarnings("unused")
public class ScriptUtil {
    public static void delay(long ms) {
        try {
            Thread.currentThread().wait(ms);
        } catch (InterruptedException ignored) { }
    }
}
