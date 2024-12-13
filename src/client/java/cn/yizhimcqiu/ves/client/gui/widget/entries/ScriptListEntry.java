package cn.yizhimcqiu.ves.client.gui.widget.entries;

import cn.yizhimcqiu.ves.VESManifest;
import cn.yizhimcqiu.ves.client.VentiScriptModClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 疑似抄袭ModMenu哈🙄💅
 */
public class ScriptListEntry extends AlwaysSelectedEntryListWidget.Entry<ScriptListEntry> {
    public static final Identifier UNKNOWN_ICON = Identifier.ofVanilla("textures/misc/unknown_pack.png");
    private Identifier iconIdentifier;
    private final VESManifest manifest;
    public ScriptListEntry(VESManifest manifest) {
        this.manifest = manifest;
    }
    public Identifier getIconIdentifier() {
        if (this.iconIdentifier != null) {
            return this.iconIdentifier;
        }
        if (this.manifest.iconPath == null) {
            return UNKNOWN_ICON;
        }
        Path iconPath = this.manifest.getFolderPath().resolve(this.manifest.iconPath);
        if (!Files.exists(iconPath) || Files.isDirectory(iconPath)) {
            return UNKNOWN_ICON;
        }
        Identifier identifier = Identifier.of(this.manifest.id, "icon");
        NativeImage image = createNativeImage(iconPath);
        if (image == null) {
            return UNKNOWN_ICON;
        }
        MinecraftClient.getInstance().getTextureManager().registerTexture(identifier, new NativeImageBackedTexture(image));
        this.iconIdentifier = identifier;
        return identifier;
    }
    private static NativeImage createNativeImage(Path path) {
        try {
            File image = path.toFile();
            return NativeImage.read(new FileInputStream(image));
        } catch (IOException e) {
            VentiScriptModClient.LOGGER.error("Cannot read icon file", e);
            return null;
        }
    }
    @Override
    public Text getNarration() {
        return Text.of(this.manifest.name);
    }
    @Override
    public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
        this.drawTexture(context, x, y, 32, 32, this.getIconIdentifier(), 1f);
    }
    private void drawTexture(DrawContext context, int x, int y, int w, int h, Identifier texture, float mul) {
        context.drawTexture(texture, x, y, 0, 0, (int)(w * mul), (int)(h * mul), (int)(w * mul), (int)(h * mul));
    }
}
