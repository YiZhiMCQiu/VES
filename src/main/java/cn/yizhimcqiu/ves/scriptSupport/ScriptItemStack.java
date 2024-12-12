package cn.yizhimcqiu.ves.scriptSupport;

import cn.yizhimcqiu.ves.annotations.VESCallIgnore;
import net.minecraft.item.ItemStack;

public class ScriptItemStack {
    private final ScriptItems item;
    private int count;
    private final ItemStack stack;
    public ScriptItemStack(ScriptItems item, int count) {
        this.item = item;
        this.count = count;
        this.stack = new ItemStack(item.$_getItem(), count);
    }
    public int getCount() {
        return count;
    }
    public ScriptItems getItem() {
        return item;
    }
    public void setCount(int count) {
        this.count = count;
    }
    @VESCallIgnore
    public ItemStack $_getItemStack() {
        return this.stack;
    }
    private void updateItemStack() {
        this.stack.setCount(this.count);
    }
}
