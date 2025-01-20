package cn.yizhimcqiu.ves.scriptSupport;

import cn.yizhimcqiu.ves.ci.CustomItemManager;

@SuppressWarnings("unused")
public class ScriptRegistry {
    public static void registerItem(String id, CustomItemManager.CustomItemEntry entry) {
        CustomItemManager.register(id, entry);
    }
}
