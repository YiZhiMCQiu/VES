package cn.yizhimcqiu.ves.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.ModStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(at = @At("HEAD"), method = "getModStatus", cancellable = true)
    private static void getModStatus(CallbackInfoReturnable<ModStatus> cir) {
        cir.setReturnValue(new ModStatus(ModStatus.Confidence.DEFINITELY, "Client brand changed to 'vanilla'"));
    }
}
