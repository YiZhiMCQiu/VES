package cn.yizhimcqiu.ves.scriptSupport;

import net.minecraft.nbt.NbtCompound;

import java.util.Map;

/**
 * 对脚本NBT操控的支持
 * @see ScriptNBTCompound#$_getNBT
 */

public class ScriptNBTCompound {
    private NbtCompound nbt;
    private NbtCompound parent;
    private String subKey;
    public ScriptNBTCompound(NbtCompound nbt) {
        this.nbt = nbt;
    }
    public ScriptNBTCompound(ScriptEntity from) {
        this.nbt = from.getNBT().$_getNBT();
    }
    public NbtCompound $_getNBT() {
        return this.nbt;
    }
    public ScriptNBTCompound putInt(String key, int value) {
        this.nbt.putInt(key, value);
        return this;
    }
    public ScriptNBTCompound putDouble(String key, double value) {
        this.nbt.putDouble(key, value);
        return this;
    }
    public ScriptNBTCompound putString(String key, String value) {
        this.nbt.putString(key, value);
        return this;
    }
    public ScriptNBTCompound putBoolean(String key, boolean value) {
        this.nbt.putBoolean(key, value);
        return this;
    }
    public int getInt(String key) {
        return this.nbt.getInt(key);
    }
    public double getDouble(String key) {
        return this.nbt.getDouble(key);
    }
    public String getString(String key) {
        return this.nbt.getString(key);
    }
    public boolean getBoolean(String key) {
        return this.nbt.getBoolean(key);
    }
    public ScriptNBTCompound sub(String key) {
        this.parent = this.nbt;
        this.nbt = this.nbt.getCompound(key);
        this.subKey = key;
        return this;
    }
    public ScriptNBTCompound parent() {
        this.parent.put(this.subKey, this.nbt);
        this.nbt = this.parent;
        this.parent = null;
        this.subKey = "";
        return this;
    }
    public void remove(String key) {
        this.nbt.remove(key);
    }

    public ScriptNBTCompound putMap(String key, Map<String, ?> map) {
        return this;
    }
}
