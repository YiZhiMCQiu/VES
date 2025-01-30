package cn.yizhimcqiu.ves.scriptSupport.world.block;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import cn.yizhimcqiu.ves.scriptSupport.ScriptEnum;

import java.util.Objects;

public class ScriptBlockType extends ScriptEnum {
    public ScriptBlockType(Identifier identifier) {
        super(identifier);
    }
    public Block $_getBlock() {
        return Registries.BLOCK.get(this.identifier);
    }
    public static ScriptBlockType parse(String id) {
        return new ScriptBlockType(Identifier.tryParse(id));
    }
    public static ScriptBlockType parseOrThrow(String id) {
        return new ScriptBlockType(Objects.requireNonNull(Identifier.tryParse(id)));
    }
    public static ScriptBlockType getBlock(Block block) {
        return new ScriptBlockType(Registries.BLOCK.getId(block));
    }
}
