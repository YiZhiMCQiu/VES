package cn.yizhimcqiu.ves.client.mixin;

import cn.yizhimcqiu.ves.client.gui.screens.VESSettingsScreen;
import net.minecraft.client.gui.screen.option.*;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin {
    @Unique
    private static final Text TITLE_TEXT = Text.translatable("options.title");
    @Unique
    private static final Text SKIN_CUSTOMIZATION_TEXT = Text.translatable("options.skinCustomisation");
    @Unique
    private static final Text SOUNDS_TEXT = Text.translatable("options.sounds");
    @Unique
    private static final Text VIDEO_TEXT = Text.translatable("options.video");
    @Unique
    private static final Text CONTROL_TEXT = Text.translatable("options.controls");
    @Unique
    private static final Text LANGUAGE_TEXT = Text.translatable("options.language");
    @Unique
    private static final Text CHAT_TEXT = Text.translatable("options.chat");
    @Unique
    private static final Text RESOURCE_PACK_TEXT = Text.translatable("options.resourcepack");
    @Unique
    private static final Text ACCESSIBILITY_TEXT = Text.translatable("options.accessibility");
    @Unique
    private static final Text VES_TEXT = Text.translatable("options.ves");
    @Unique
    private static final Text CREDITS_AND_ATTRIBUTION_TEXT = Text.translatable("options.credits_and_attribution");

    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private void init(CallbackInfo ci) {
        OptionsScreen self = (OptionsScreen) (Object) this;
        GridWidget gridWidget = new GridWidget();
        gridWidget.getMainPositioner().marginX(5).marginBottom(4).alignHorizontalCenter();
        GridWidget.Adder adder = gridWidget.createAdder(2);
        adder.add(self.settings.getFov().createWidget(self.client.options, 0, 0, 150));
        adder.add(self.createTopRightButton());
        adder.add(EmptyWidget.ofHeight(26), 2);
        adder.add(self.createButton(SKIN_CUSTOMIZATION_TEXT, () -> new SkinOptionsScreen(self, self.settings)));
        adder.add(self.createButton(SOUNDS_TEXT, () -> new SoundOptionsScreen(self, self.settings)));
        adder.add(self.createButton(VIDEO_TEXT, () -> new VideoOptionsScreen(self, self.settings)));
        adder.add(self.createButton(CONTROL_TEXT, () -> new ControlsOptionsScreen(self, self.settings)));
        adder.add(self.createButton(LANGUAGE_TEXT, () -> new LanguageOptionsScreen(self, self.settings, self.client.getLanguageManager())));
        adder.add(self.createButton(CHAT_TEXT, () -> new ChatOptionsScreen(self, self.settings)));
        adder.add(self.createButton(RESOURCE_PACK_TEXT, () -> new PackScreen(self.client.getResourcePackManager(), self::refreshResourcePacks, self.client.getResourcePackDir(), Text.translatable("resourcePack.title"))));
        adder.add(self.createButton(ACCESSIBILITY_TEXT, () -> new AccessibilityOptionsScreen(self, self.settings)));
        adder.add(self.createButton(VES_TEXT, () -> new VESSettingsScreen(self)));
        adder.add(self.createButton(CREDITS_AND_ATTRIBUTION_TEXT, () -> new CreditsAndAttributionScreen(self)));
        adder.add(ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
            self.client.setScreen(self.parent);
        }).width(200).build(), 2, adder.copyPositioner().marginTop(6));
        gridWidget.refreshPositions();
        SimplePositioningWidget.setPos(gridWidget, 0, self.height / 6 - 12, self.width, self.height, 0.5F, 0.0F);
        gridWidget.forEachChild(self::addDrawableChild);
        ci.cancel();
    }
}