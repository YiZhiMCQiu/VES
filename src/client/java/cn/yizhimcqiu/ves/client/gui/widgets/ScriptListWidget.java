package cn.yizhimcqiu.ves.client.gui.widgets;

import cn.yizhimcqiu.ves.VESManifest;
import cn.yizhimcqiu.ves.client.gui.widgets.entries.ScriptListEntry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;

import java.util.List;

/**
 * 666盐豆布袋腌的
 */
public class ScriptListWidget extends AlwaysSelectedEntryListWidget<ScriptListEntry> {
    private ScriptListEntry selected = null;
    public ScriptListWidget(MinecraftClient minecraftClient, int i, int j, int k, int l) {
        super(minecraftClient, i, j, k, l);
    }
    public void select(ScriptListEntry entry) {
        this.selected = entry;
    }
    public void init(List<VESManifest> scripts) {
        for (VESManifest manifest : scripts) {
            this.addEntry(new ScriptListEntry(manifest, this));
        }
    }
}
