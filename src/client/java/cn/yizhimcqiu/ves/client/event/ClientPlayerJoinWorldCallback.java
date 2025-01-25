package cn.yizhimcqiu.ves.client.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;

public interface ClientPlayerJoinWorldCallback {
    Event<ClientPlayerJoinWorldCallback> EVENT = EventFactory.createArrayBacked(ClientPlayerJoinWorldCallback.class,
            (listeners) -> (player, world, client) -> {
                for (ClientPlayerJoinWorldCallback listener : listeners) {
                    listener.onPlayerJoinWorld(player, world, client);
                }
            });

    void onPlayerJoinWorld(ClientPlayerEntity player, ClientWorld world, MinecraftClient client);
}
