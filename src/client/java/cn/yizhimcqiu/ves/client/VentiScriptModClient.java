package cn.yizhimcqiu.ves.client;

import cn.yizhimcqiu.ves.client.commands.VESSettingsCommand;
import cn.yizhimcqiu.ves.network.Networking;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.slf4j.Logger;

public class VentiScriptModClient implements ClientModInitializer {
    public static final Logger LOGGER = LogUtils.getLogger();
    @Override
    public void onInitializeClient() {
        VESSettingsCommand.register();

        ClientPlayNetworking.registerGlobalReceiver(Networking.SEND_COMMAND_S2C, (client, handler, buf, sender) -> client.player.sendCommand(buf.readString()));
    }
}
