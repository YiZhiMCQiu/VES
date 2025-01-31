package cn.yizhimcqiu.ves.scriptSupport;

import cn.yizhimcqiu.ves.annotations.VESCallIgnore;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Objects;

@SuppressWarnings("unused")
public class ScriptEntityType extends ScriptEnum {
    public ScriptEntityType(Identifier identifier) {
        super(identifier);
    }
    public static ScriptEntityType parse(String id) {
        return new ScriptEntityType(Identifier.tryParse(id));
    }
    public static ScriptEntityType parseOrThrow(String id) {
        return new ScriptEntityType(Objects.requireNonNull(Identifier.tryParse(id)));
    }
    @VESCallIgnore
    public EntityType<?> $_getEntityType() {
        return Registry.ENTITY_TYPE.get(this.identifier);
    }
}
