package cn.yizhimcqiu.ves.client.mixin;

import net.minecraft.client.toast.ToastManager;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ToastManager.class)
public class InGameHudMixin {
    @Inject(at = @At("TAIL"), method = "draw")
    private void draw(MatrixStack matrices, CallbackInfo ci) {

    }
}
