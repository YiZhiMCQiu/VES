package cn.yizhimcqiu.ves;

import net.minecraft.text.Text;

import java.nio.file.Path;
import java.util.List;

@SuppressWarnings("unused")
public class VESManifest {
    public String id;
    public String name;
    public String version = "1.0.0";
    public String description = "";
    public String icon;
    public String[] requires;
    public List<String> items;
    public Text getNameAsText() {
        return Text.of(this.name);
    }
    public Text getDescriptionAsText() {
        return Text.of(this.name);
    }
    public Path getFolderPath() {
        return Path.of("ves", this.id);
    }
}
