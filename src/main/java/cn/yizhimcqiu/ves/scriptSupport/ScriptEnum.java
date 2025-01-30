package cn.yizhimcqiu.ves.scriptSupport;

import net.minecraft.util.Identifier;

public abstract class ScriptEnum {
    protected final Identifier identifier;
    public ScriptEnum(Identifier identifier) {
        this.identifier = identifier;
    }
    public Identifier getIdentifier() {
        return identifier;
    }
}
