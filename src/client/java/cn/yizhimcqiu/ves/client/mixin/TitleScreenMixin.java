package cn.yizhimcqiu.ves.client.mixin;

import cn.yizhimcqiu.ves.VESVersion;
import cn.yizhimcqiu.ves.VentiScriptMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Properties;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Unique private static boolean showed = false;
    @Inject(at = @At("TAIL"), method = "<init>(ZLnet/minecraft/client/gui/LogoDrawer;)V")
    private void showUpdateToast(CallbackInfo ci) {
        if (showed) return;
        Properties props = VentiScriptMod.getLatestVersion();
        if (props != null) {
            String buildTime = props.getProperty("build_time");
            if (Long.parseLong(buildTime) > Long.parseLong(VESVersion.BUILD_TIME)) {
                MinecraftClient client = MinecraftClient.getInstance();
                SystemToast.add(client.getToastManager(), SystemToast.Type.PERIODIC_NOTIFICATION, Text.translatable("title.ves_update_available"),
                        Text.translatable("title.ves_update_text", buildTime, props.getProperty("update_text")));
                showed = true;
            }
        }
    }
}
