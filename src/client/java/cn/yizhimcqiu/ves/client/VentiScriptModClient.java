package cn.yizhimcqiu.ves.client;

import cn.yizhimcqiu.ves.client.commands.VESSettingsCommand;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;

public class VentiScriptModClient implements ClientModInitializer {
    public static final Logger LOGGER = LogUtils.getLogger();
    @Override
    public void onInitializeClient() {
        VESSettingsCommand.register();
    }
}
