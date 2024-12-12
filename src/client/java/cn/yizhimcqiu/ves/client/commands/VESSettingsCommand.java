package cn.yizhimcqiu.ves.client.commands;

import cn.yizhimcqiu.ves.client.screens.VESSettingsScreen;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

public class VESSettingsCommand {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("ves-settings").executes(context -> {
            context.getSource().getClient().setScreen(new VESSettingsScreen(null));
            return 1;
        })));
    }
}
