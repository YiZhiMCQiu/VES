package cn.yizhimcqiu.ves.client.screens;

import cn.yizhimcqiu.ves.VESVersion;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class VESSettingsScreen extends Screen {
    private final Screen parent;
    public VESSettingsScreen(Screen parent) {
        super(Text.translatable("screen.ves.settings"));
        this.parent = parent;
    }
    @Override
    protected void init() {

    }
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawText(this.textRenderer, Text.translatable("info.ves.version", VESVersion.VERSION), width / 2, height / 2, 0xffffff, false);
    }
    @Override
    public void close() {
        this.client.setScreen(this.parent);
    }
}
