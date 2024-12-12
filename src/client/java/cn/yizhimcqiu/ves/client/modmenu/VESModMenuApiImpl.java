package cn.yizhimcqiu.ves.client.modmenu;

//import cn.yizhimcqiu.ves.client.screens.VESSettingsScreen;
//import com.terraformersmc.modmenu.api.ConfigScreenFactory;
//import com.terraformersmc.modmenu.api.ModMenuApi;
//import net.minecraft.client.gui.screen.Screen;

import java.util.function.Consumer;

public class VESModMenuApiImpl/* implements ModMenuApi*/ {
    //@Override
    public void attachModpackBadges(Consumer<String> consumer) {
        consumer.accept("modmenu");
    }
//    @Override
//    public ConfigScreenFactory<?> getModConfigScreenFactory() {
//        return screen -> new VESSettingsScreen(screen);
//    }
}
