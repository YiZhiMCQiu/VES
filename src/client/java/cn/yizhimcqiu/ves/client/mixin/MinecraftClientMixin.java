package cn.yizhimcqiu.ves.client.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(at = @At("HEAD"), method = "getGpuUtilizationPercentage", cancellable = true)
    public void getGpuUtilizationPercentage(CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(100.0D);
    }
}
