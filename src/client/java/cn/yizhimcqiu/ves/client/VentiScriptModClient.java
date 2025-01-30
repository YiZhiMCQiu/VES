package cn.yizhimcqiu.ves.client;

import cn.yizhimcqiu.ves.client.commands.VESSettingsCommand;
import cn.yizhimcqiu.ves.network.SendCommandS2CPacket;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import org.slf4j.Logger;

import java.util.Objects;

public class VentiScriptModClient implements ClientModInitializer {
    public static final Logger LOGGER = LogUtils.getLogger();
    @Override
    public void onInitializeClient() {
        VESSettingsCommand.register();

        PayloadTypeRegistry.playS2C().register(SendCommandS2CPacket.ID, SendCommandS2CPacket.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(SendCommandS2CPacket.ID, (payload, context) -> Objects.requireNonNull(context.client().getNetworkHandler()).sendChatCommand(payload.command()));
    }
}
