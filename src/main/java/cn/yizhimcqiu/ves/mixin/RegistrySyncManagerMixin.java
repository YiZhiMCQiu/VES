package cn.yizhimcqiu.ves.mixin;

import com.mojang.logging.LogUtils;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.fabricmc.fabric.impl.registry.sync.RegistrySyncManager;
import net.fabricmc.fabric.impl.registry.sync.RemapException;
import net.fabricmc.fabric.impl.registry.sync.RemappableRegistry;
import net.fabricmc.fabric.impl.registry.sync.packet.RegistryPacketHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.thread.ThreadExecutor;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static net.fabricmc.fabric.impl.registry.sync.RegistrySyncManager.*;

@Mixin(RegistrySyncManager.class)
public class RegistrySyncManagerMixin {
    @Unique
    private static final Logger LOGGER = LogUtils.getLogger();
    @Inject(at = @At("HEAD"), method = "receivePacket", cancellable = true)
    private static <T extends RegistryPacketHandler.RegistrySyncPayload> void receivePacket(ThreadExecutor<?> executor, RegistryPacketHandler<T> handler, T payload, boolean accept, CallbackInfoReturnable<CompletableFuture<Boolean>> cir) {
        handler.receivePayload(payload);

        if (!handler.isPacketFinished()) {
            cir.setReturnValue(CompletableFuture.completedFuture(false));
        }

        Map<Identifier, Object2IntMap<Identifier>> map = handler.getSyncedRegistryMap();

        if (!accept) {
            cir.setReturnValue(CompletableFuture.completedFuture(true));
        }

        cir.setReturnValue(executor.submit(() -> {
            if (map == null) {
                throw new CompletionException(new RemapException("Received null map in sync packet!"));
            }

            try {
                apply(map, RemappableRegistry.RemapMode.REMOTE);
            } catch (RemapException e) {
                LOGGER.error("Catch RemapException", e);
            }
            return true;
        }));
    }
}
