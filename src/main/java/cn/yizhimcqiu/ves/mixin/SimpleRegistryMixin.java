package cn.yizhimcqiu.ves.mixin;

import net.minecraft.registry.Registry;
import net.minecraft.registry.SimpleRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SimpleRegistry.class)
public class SimpleRegistryMixin {
    @Inject(at = @At("HEAD"), method = "freeze", cancellable = true)
    private void freeze(CallbackInfoReturnable<Registry> cir) {
        cir.setReturnValue((Registry) this);
    }
}
