package cn.yizhimcqiu.ves.client.screens;

import cn.yizhimcqiu.ves.VESVersion;
import cn.yizhimcqiu.ves.utils.ListUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Environment(EnvType.CLIENT)
public class VESSettingsScreen extends Screen {
    private static final Text VERSION_TEXT = Text.translatable("info.ves.version", VESVersion.VERSION);
    private final Screen parent;
    private List<String> installedScripts;
    public VESSettingsScreen(Screen parent) {
        super(Text.translatable("options.ves"));
        this.parent = parent;
        this.installedScripts = this.getInstalledScripts();
    }
    @Override
    protected void init() {
        this.addDrawableChild(ButtonWidget.builder(Text.of("x"), button -> this.close()).build());
    }
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.renderText(context);
    }
    public void renderText(DrawContext context) {
        int width = this.textRenderer.getWidth(VERSION_TEXT);
        context.drawText(this.textRenderer, VERSION_TEXT, this.width / 2 - width / 2, this.height / 8, 0xffffff, false);
        for (int i = 0;i < this.installedScripts.size();i++) {
            context.drawText(this.textRenderer, Text.of(this.installedScripts.get(i)), 5, this.height / 8 + 5 + i, 0xffffff, false);
        }
    }
    @Override
    public void close() {
        this.client.setScreen(this.parent);
    }
    private List<String> getInstalledScripts() {
        try (Stream<Path> paths = Files.list(Paths.get("ves"))) {
            return ListUtil.convertList(paths.filter(path -> Files.isDirectory(path) && !path.getFileName().startsWith(".")).toList(), path -> path.getFileName().toString());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
