package cn.yizhimcqiu.ves.client.gui.screens;

import cn.yizhimcqiu.ves.VESManifest;
import cn.yizhimcqiu.ves.VESVersion;
import cn.yizhimcqiu.ves.VentiScriptMod;
import cn.yizhimcqiu.ves.client.VentiScriptModClient;
import cn.yizhimcqiu.ves.client.gui.widgets.ScriptListWidget;
import cn.yizhimcqiu.ves.util.ListUtil;
import com.google.gson.Gson;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Environment(EnvType.CLIENT)
public class VESSettingsScreen extends Screen {
    private static final int ICON_SIZE = 512;
    private static final Identifier ICON_IDENTIFIER = Identifier.of(VentiScriptMod.MOD_ID, "icon.png");
    private static final Text VERSION_TEXT = Text.translatable("info.ves.version", VESVersion.VERSION);
    private final Screen parent;
    private final List<String> installedScripts;
    private List<VESManifest> scripts;
    private ScriptListWidget scriptList;
    public VESSettingsScreen(Screen parent) {
        super(Text.translatable("options.ves"));
        this.parent = parent;
        this.installedScripts = this.getInstalledScripts();
        this.initScripts();
    }
    @Override
    protected void init() {
        this.scriptList = new ScriptListWidget(this.client, this.width - 20, this.height - 40, 20, 32, 0);
        this.scriptList.init(this.scripts);
        ButtonWidget updateButton =  this.addDrawableChild(ButtonWidget.builder(Text.of("更新"),
                button -> VentiScriptMod.updateVES()).build());
        if (!VentiScriptMod.isDevelop) {
            updateButton.active = false;
            updateButton.setTooltip(Tooltip.of(Text.of("error.ves.is_not_develop")));
        }
    }
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.scriptList.render(context, mouseX, mouseY, delta);
        this.renderText(context);
        this.renderIcon(context);
    }
    public void renderText(DrawContext context) {
        int width = this.textRenderer.getWidth(VERSION_TEXT);
        context.drawText(this.textRenderer, VERSION_TEXT, this.width / 2 - width / 2, this.height / 8, 0xffffff, false);
        for (int i = 0;i < this.installedScripts.size();i++) {
            context.drawText(this.textRenderer, Text.of(this.installedScripts.get(i)), 5, this.height / 8 + 5 + i * this.textRenderer.fontHeight, 0xffffff, false);
        }
    }
    private void renderIcon(DrawContext context) {
        this.drawTexture(context, 0, ButtonWidget.DEFAULT_HEIGHT, ICON_SIZE, ICON_SIZE, ICON_IDENTIFIER, 0.2f);
    }
    private void drawTexture(DrawContext context, int x, int y, int w, int h, Identifier texture) {
        this.drawTexture(context, x, y, w, h, texture, 1);
    }
    private void drawTexture(DrawContext context, int x, int y, int w, int h, Identifier texture, float mul) {
        context.drawTexture(texture, x, y, 0, 0, (int)(w * mul), (int)(h * mul), (int)(w * mul), (int)(h * mul));
    }
    @Override
    public void close() {
        this.client.setScreen(this.parent);
    }
    private List<String> getInstalledScripts() {
        try (Stream<Path> paths = Files.list(Paths.get("ves"))) {
            return ListUtil.convertList(paths.filter(path -> Files.isDirectory(path) && !path.getFileName().toString().startsWith(".")).toList(), path -> path.getFileName().toString());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    private void initScripts() {
        Gson gson = new Gson();
        this.scripts = new ArrayList<>();
        for (String id : this.installedScripts) {
            Path scriptFolder = Path.of("ves", id);
            File manifest = scriptFolder.resolve("manifest.json").toFile();
            if (!manifest.exists()) {
                VentiScriptModClient.LOGGER.error("{} is not exist", manifest);
                continue;
            }
            if (manifest.isDirectory()) {
                VentiScriptModClient.LOGGER.error("{} is not a file", manifest);
                continue;
            }
            try (FileReader reader = new FileReader(manifest)) {
                VESManifest manifestData = gson.fromJson(reader, VESManifest.class);
                this.scripts.add(manifestData);
                VentiScriptModClient.LOGGER.info("Loaded {}'s manifest", id);
            } catch (IOException e) {
                VentiScriptModClient.LOGGER.error("Failed to load manifest.json", e);
            }
        }
//        VESManifest manifest = new VESManifest();
//        for (int i = 0;i < 50;i++) {
//            this.scripts.add(manifest);
//        }
    }
}