package cn.yizhimcqiu.ves.scriptSupport;

import cn.yizhimcqiu.ves.ci.CustomItemManager;

public class ScriptRegistry {
    public static void registerItem(String id, CustomItemManager.CustomItemEntry entry) {
        CustomItemManager.register(id, entry);
    }
}
