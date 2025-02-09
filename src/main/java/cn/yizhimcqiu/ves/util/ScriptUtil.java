package cn.yizhimcqiu.ves.util;

@SuppressWarnings("unused")
public class ScriptUtil {
    public static void delay(long ms) {
        try {
            Thread.currentThread().wait(ms);
        } catch (InterruptedException ignored) { }
    }
    public static void stackTrace() {
        System.out.println("== Stack Trace ==");
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            System.out.println("    %class%.%method% (%file%:%line%)"
                    .replace("%class%", element.getClassName())
                    .replace("%method%", element.getMethodName())
                    .replace("%file%", element.getFileName() == null ? "unknown" : element.getFileName())
                    .replace("%line%", element.getLineNumber()+""));
        }
    }
}
