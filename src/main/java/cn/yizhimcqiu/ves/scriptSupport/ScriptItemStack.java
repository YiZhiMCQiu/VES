package cn.yizhimcqiu.ves.scriptSupport;

import cn.yizhimcqiu.ves.annotations.VESCallIgnore;
import cn.yizhimcqiu.ves.ci.items.CustomItem;
import net.minecraft.item.ItemStack;

@SuppressWarnings("unused")
public class ScriptItemStack {
    private ScriptItems item;
    private int count;
    private final ItemStack stack;
    public ScriptItemStack(ScriptItems item, int count) {
        this.stack = new ItemStack(item.$_getItem(), count);
    }
    public ScriptItemStack(ItemStack stack) {
        this.stack = stack;
    }
    public int getCount() {
        return this.stack.getCount();
    }
    public ScriptItems getItem() {
        return item;
    }
    public void setCount(int count) {
        this.stack.setCount(count);
    }
    public void add(int count) {
        this.stack.increment(count);
    }
    public void remove(int count) {
        this.stack.decrement(count);
    }
    public ScriptItemStack custom(String id) {
        CustomItem.makeCustom(this, id);
        return this;
    }
    @VESCallIgnore
    public ItemStack $_getItemStack() {
        return this.stack;
    }
}
