package cn.yizhimcqiu.ves.mixin;

import cn.yizhimcqiu.ves.VentiScriptMod;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.network.ClientConnection;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ConnectedClientData;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Inject(at = @At("TAIL"), method = "onPlayerConnect")
    private void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, ConnectedClientData clientData, CallbackInfo ci) {
        if (player.hasPermissionLevel(2)) {
            MutableText text = Text.translatable("info.installed_plugins");
            for (ModMetadata metadata : VentiScriptMod.getInstalledPluginsMetadata()) {
                text.append(Text.of("["+metadata.getName()+" "+metadata.getVersion()+"]").copy().styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.of(metadata.getDescription())))));
            }
            player.sendMessage(text);
        }
    }
}
