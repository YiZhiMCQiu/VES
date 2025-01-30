package cn.yizhimcqiu.ves.client;

import cn.yizhimcqiu.ves.client.commands.VESSettingsCommand;
import cn.yizhimcqiu.ves.network.SendCommandS2CPacket;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.slf4j.Logger;

public class VentiScriptModClient implements ClientModInitializer {
    public static final Logger LOGGER = LogUtils.getLogger();
    @Override
    public void onInitializeClient() {
        VESSettingsCommand.register();

        ClientPlayNetworking.registerGlobalReceiver(SendCommandS2CPacket.TYPE, (packet, clientPlayer, sender) -> {
            clientPlayer.networkHandler.sendChatCommand(packet.getCommand());
        });
    }
}
