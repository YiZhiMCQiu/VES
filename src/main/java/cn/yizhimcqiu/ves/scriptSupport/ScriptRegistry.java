package cn.yizhimcqiu.ves.scriptSupport;

import cn.yizhimcqiu.ves.ci.CustomItemManager;
import cn.yizhimcqiu.ves.core.VESItemDefinitionFinder;

@SuppressWarnings("unused")
public class ScriptRegistry {
    public static void registerItem(String namespace, String id, CustomItemManager.CustomItemEntry entry) {
        VESItemDefinitionFinder.registerItem(namespace, id, entry);
    }
}
