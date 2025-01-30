package cn.yizhimcqiu.ves.mixin;

import com.mojang.logging.LogUtils;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.fabricmc.fabric.impl.registry.sync.RegistrySyncManager;
import net.fabricmc.fabric.impl.registry.sync.RemapException;
import net.fabricmc.fabric.impl.registry.sync.RemappableRegistry;
import net.fabricmc.fabric.impl.registry.sync.packet.RegistryPacketHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.thread.ThreadExecutor;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Consumer;

import static net.fabricmc.fabric.impl.registry.sync.RegistrySyncManager.*;

@Mixin(RegistrySyncManager.class)
public class RegistrySyncManagerMixin {
    @Unique
    private static final Logger LOGGER = LogUtils.getLogger();
    @Inject(at = @At("HEAD"), method = "receivePacket", cancellable = true)
    private static void receivePacket(ThreadExecutor<?> executor, RegistryPacketHandler handler, PacketByteBuf buf, boolean accept, Consumer<Exception> errorHandler, CallbackInfo ci) {
        handler.receivePacket(buf);
        if (handler.isPacketFinished()) {
            if (DEBUG) {
                String handlerName = handler.getClass().getSimpleName();
                LOGGER.info("{} total packet: {}", handlerName, handler.getTotalPacketReceived());
                LOGGER.info("{} raw size: {}", handlerName, handler.getRawBufSize());
                LOGGER.info("{} deflated size: {}", handlerName, handler.getDeflatedBufSize());
            }

            Map<Identifier, Object2IntMap<Identifier>> map = handler.getSyncedRegistryMap();
            if (accept) {
                try {
                    executor.submit(() -> {
                        if (map == null) {
                            errorHandler.accept(new RemapException("Received null map in sync packet!"));
                            return null;
                        } else {
                            try {
                                apply(map, RemappableRegistry.RemapMode.REMOTE);
                            } catch (RemapException ignored) {

                            }

                            return null;
                        }
                    }).get(30L, TimeUnit.SECONDS);
                } catch (InterruptedException | TimeoutException | ExecutionException var7) {
                    Exception e = var7;
                    errorHandler.accept(e);
                }
            }

        }
    }
}
