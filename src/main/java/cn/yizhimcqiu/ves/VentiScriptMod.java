package cn.yizhimcqiu.ves;

import cn.yizhimcqiu.ves.commands.LoadScriptCommand;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;

public class VentiScriptMod implements ModInitializer {
    public static final String MOD_ID = "ves";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static boolean isDevelop = false;
    @Override
    public void onInitialize() {
        this.registerCommands();
        try {
            Class.forName("net.minecraft.class_2561");
        } catch (ClassNotFoundException e) {
            isDevelop = true;
        }
    }
    private void registerCommands() {
        new LoadScriptCommand().register();
    }
}
