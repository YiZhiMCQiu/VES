package cn.yizhimcqiu.ves.scriptSupport;

import cn.yizhimcqiu.ves.annotations.VESCallIgnore;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Objects;

@SuppressWarnings("unused")
public class ScriptItemType extends ScriptEnum {
    public ScriptItemType(Identifier identifier) {
        super(identifier);
    }
    public static ScriptItemType parse(String id) {
        return new ScriptItemType(Identifier.tryParse(id));
    }
    public static ScriptItemType parseOrThrow(String id) {
        return new ScriptItemType(Objects.requireNonNull(Identifier.tryParse(id)));
    }
    @VESCallIgnore
    public Item $_getItem() {
        return Registry.ITEM.get(this.identifier);
    }
}
