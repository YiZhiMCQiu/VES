package cn.yizhimcqiu.vine;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class VineExecuteExceptionHandler {
    private static final String PREFIX = "[§c§lVINE§r] ";
    public static MutableText createErrorMessage(Throwable t) {
        MutableText message = Text.of(PREFIX).copy();
        String errorMessage = t.getMessage();
        if (errorMessage.contains("Promise is rejected")) {
            message.append(Text.translatable("vine.errorMessage.promise_hint"));
        }
        if (errorMessage.contains("TypeError")) {
            if (errorMessage.contains("is not a function")) {
                message.append(Text.translatable("vine.errorMessage.is_not_a_function"));
            } else if (errorMessage.contains("is not defined")) {
                message.append(Text.translatable("vine.errorMessage.is_not_defined"));
            }
        }
        if (errorMessage.contains("Error reading")) {
            message.append(Text.translatable("vine.errorMessage.error_reading"));
        }
        if (errorMessage.contains("ReferenceError")) {
            message.append(Text.translatable("vine.errorMessage.reference_error"));
        }

        if (message.equals(Text.of(PREFIX))) {
            return message.append(Text.translatable("vine.errorMessage.not_happened"));
        }
        return message;
    }
}
