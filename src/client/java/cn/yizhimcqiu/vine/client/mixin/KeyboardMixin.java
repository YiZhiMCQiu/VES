package cn.yizhimcqiu.vine.client.mixin;

import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(at = @At("HEAD"), method = "onKey", cancellable = true)
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
//        if (InputContext.getInstance().getLocale() != Locale.ENGLISH && VineTypingFixer.isMacOS()) {
//            System.out.println(System.getenv("INPUT_METHOD"));
//            ci.cancel();
//        }
    }
}
