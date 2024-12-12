package cn.yizhimcqiu.ves.scriptSupport;

import cn.yizhimcqiu.ves.annotations.VESCallIgnore;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

import java.util.Map;

/**
 * 对脚本NBT操控的支持
 * @see ScriptNBTCompound#$_getNBT
 */

public class ScriptNBTCompound {
    private NbtCompound nbt;
    private ScriptNBTCompound parent = null;
    private String subKey;
    public ScriptNBTCompound(NbtCompound nbt) {
        this.nbt = nbt;
    }
    public ScriptNBTCompound(ScriptEntity from) {
        this.nbt = from.getNBT().$_getNBT();
    }
    public ScriptNBTCompound() {
        this.nbt = new NbtCompound();
    }
    @VESCallIgnore
    public NbtCompound $_getNBT() {
        return this.nbt;
    }
    @VESCallIgnore
    public ScriptNBTCompound $_clone() {
        ScriptNBTCompound object = new ScriptNBTCompound(this.nbt.copy());
        object.parent = this.parent;
        object.subKey = this.subKey;
        return object;
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
        this.parent = this.$_clone();
        this.nbt = this.nbt.getCompound(key);
        this.subKey = key;
        return this;
    }
    public ScriptNBTCompound parent() {
        this.parent.nbt.put(this.subKey, this.nbt);
        this.nbt = this.parent.nbt;
        this.subKey = this.parent.subKey;
        this.parent = this.parent.parent;
        return this;
    }
    public void remove(String key) {
        this.nbt.remove(key);
    }
    public ScriptNBTCompound putList(String key, int... list) {
        this.nbt.putIntArray(key, list);
        return this;
    }
    public ScriptNBTCompound putList(String key, byte... list) {
        this.nbt.putByteArray(key, list);
        return this;
    }
    public ScriptNBTCompound putList(String key, long... list) {
        this.nbt.putLongArray(key, list);
        return this;
    }
    public ScriptNBTCompound putList(String key, ScriptNBTCompound... list) {
        NbtList nbtList = new NbtList();
        for (ScriptNBTCompound nbt : list) {
            nbtList.add(nbt.nbt);
        }
        this.nbt.put(key, nbtList);
        return this;
    }
}
