package cn.yizhimcqiu.ves.client;

import cn.yizhimcqiu.ves.client.commands.VESSettingsCommand;
import net.fabricmc.api.ClientModInitializer;

public class VentiScriptModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        VESSettingsCommand.register();
    }
}
