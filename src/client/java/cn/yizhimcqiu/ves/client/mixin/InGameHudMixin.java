package cn.yizhimcqiu.ves.client.mixin;

import cn.yizhimcqiu.ves.client.util.VESToast;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ToastManager.class)
public class InGameHudMixin {
    @Inject(at = @At("TAIL"), method = "draw")
    private void draw(DrawContext context, CallbackInfo ci) {
        if (VESToast.current != null) {
            VESToast.current.render(context);
        }
    }
}
