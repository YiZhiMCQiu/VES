package cn.yizhimcqiu.ves.scriptSupport;

import cn.yizhimcqiu.ves.annotations.VESCallIgnore;
import cn.yizhimcqiu.ves.items.CustomItem;
import net.minecraft.item.ItemStack;

@SuppressWarnings("unused")
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
        this.stack.setCount(count);
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
