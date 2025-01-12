package cn.yizhimcqiu.ves.scriptSupport;

import cn.yizhimcqiu.ves.ci.CustomBlockManager;
import cn.yizhimcqiu.ves.ci.CustomItemManager;

@SuppressWarnings("unused")
public class ScriptRegistry {
    public static void registerItem(String id, CustomItemManager.CustomItemEntry entry) {
        CustomItemManager.register(id, entry);
    }
    public static void registerBlock(String id, CustomBlockManager.CustomBlockEntry entry) {
        CustomBlockManager.register(id, entry);
    }
}
