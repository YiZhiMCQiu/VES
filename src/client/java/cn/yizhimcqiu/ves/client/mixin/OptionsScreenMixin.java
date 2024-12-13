package cn.yizhimcqiu.ves.client.mixin;

import cn.yizhimcqiu.ves.client.gui.screens.VESSettingsScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
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

import java.util.function.Supplier;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {
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

    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @Unique
    private ButtonWidget createButton(Text message, Supplier<Screen> screenSupplier) {
        return ButtonWidget.builder(message, (button) -> MinecraftClient.getInstance().setScreen(screenSupplier.get())).build();
    }

    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private void init(CallbackInfo ci) {
        OptionsScreen self = (OptionsScreen) ((Object) this);
        DirectionalLayoutWidget directionalLayoutWidget = self.layout.addHeader(DirectionalLayoutWidget.vertical().spacing(8));
        directionalLayoutWidget.add(new TextWidget(TITLE_TEXT, self.textRenderer), Positioner::alignHorizontalCenter);
        DirectionalLayoutWidget directionalLayoutWidget2 = directionalLayoutWidget.add(DirectionalLayoutWidget.horizontal()).spacing(8);
        directionalLayoutWidget2.add(self.settings.getFov().createWidget(self.client.options));
        directionalLayoutWidget2.add(self.createTopRightButton());
        GridWidget gridWidget = new GridWidget();
        gridWidget.getMainPositioner().marginX(4).marginBottom(4).alignHorizontalCenter();
        GridWidget.Adder adder = gridWidget.createAdder(2);
        adder.add(this.createButton(SKIN_CUSTOMIZATION_TEXT, () -> new SkinOptionsScreen(self, self.settings)));
        adder.add(this.createButton(SOUNDS_TEXT, () -> new SoundOptionsScreen(self, self.settings)));
        adder.add(this.createButton(VIDEO_TEXT, () -> new VideoOptionsScreen(self, self.client, self.settings)));
        adder.add(this.createButton(CONTROL_TEXT, () -> new ControlsOptionsScreen(self, self.settings)));
        adder.add(this.createButton(LANGUAGE_TEXT, () -> new LanguageOptionsScreen(self, self.settings, self.client.getLanguageManager())));
        adder.add(this.createButton(CHAT_TEXT, () -> new ChatOptionsScreen(self, self.settings)));
        adder.add(this.createButton(RESOURCE_PACK_TEXT, () -> new PackScreen(self.client.getResourcePackManager(), self::refreshResourcePacks, self.client.getResourcePackDir(), Text.translatable("resourcePack.title"))));
        adder.add(this.createButton(ACCESSIBILITY_TEXT, () -> new AccessibilityOptionsScreen(self, self.settings)));
        adder.add(this.createButton(VES_TEXT, () -> new VESSettingsScreen(self)));
        adder.add(this.createButton(CREDITS_AND_ATTRIBUTION_TEXT, () -> new CreditsAndAttributionScreen(self)));
        self.layout.addBody(gridWidget);
        self.layout.addFooter(ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
            self.close();
        }).width(200).build());
        self.layout.forEachChild(self::addDrawableChild);
        self.initTabNavigation();
        ci.cancel();
    }
}