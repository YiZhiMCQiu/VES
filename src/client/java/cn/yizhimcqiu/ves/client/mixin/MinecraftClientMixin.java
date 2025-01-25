package cn.yizhimcqiu.ves.client.mixin;

import cn.yizhimcqiu.ves.client.event.ClientPlayerJoinWorldCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ModStatus;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow @Nullable public ClientPlayerEntity player;
    @Shadow @Nullable public ClientWorld world;

    @Inject(at = @At("HEAD"), method = "getModStatus", cancellable = true)
    private static void getModStatus(CallbackInfoReturnable<ModStatus> cir) {
        cir.setReturnValue(new ModStatus(ModStatus.Confidence.DEFINITELY, "Client brand changed to 'vanilla'"));
    }
    @Inject(at = @At("TAIL"), method = "joinWorld")
    private void joinWorld(ClientWorld world, DownloadingTerrainScreen.WorldEntryReason worldEntryReason, CallbackInfo ci) {
        ClientPlayerJoinWorldCallback.EVENT.invoker().onPlayerJoinWorld(this.player, this.world, (MinecraftClient) (Object) this);
    }
}
