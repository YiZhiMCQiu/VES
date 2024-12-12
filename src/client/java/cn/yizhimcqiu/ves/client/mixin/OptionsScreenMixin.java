package cn.yizhimcqiu.ves.client.mixin;

import cn.yizhimcqiu.ves.client.screens.VESSettingsScreen;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.*;
import net.minecraft.client.gui.widget.*;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.function.Supplier;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin {
    @Unique
    private ButtonWidget createButton(Text message, Supplier<Screen> screenSupplier) {
        return ButtonWidget.builder(message, (button) -> MinecraftClient.getInstance().setScreen(screenSupplier.get())).build();
    }
    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/GridWidget;createAdder(I)Lnet/minecraft/client/gui/widget/GridWidget$Adder;", shift = At.Shift.BEFORE), locals = LocalCapture.NO_CAPTURE)
    private void addVESSettings(CallbackInfo ci, @Local(ordinal = 4) GridWidget.Adder adder) {
        adder.add(createButton(Text.translatable("screen.ves.settings"), () -> new VESSettingsScreen((OptionsScreen)((Object) this))));
    }
}