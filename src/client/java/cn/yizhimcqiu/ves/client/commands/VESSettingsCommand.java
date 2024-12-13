package cn.yizhimcqiu.ves.client.commands;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.text.Text;

public class VESSettingsCommand {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("ves-settings").executes(context -> {
            final MinecraftClient client = MinecraftClient.getInstance();
            client.setScreen(new OptionsScreen(null, client.options));
            context.getSource().sendFeedback(Text.of("awa"));
            return 1;
        })));
    }
}
