package cn.yizhimcqiu.ves.core;

import cn.yizhimcqiu.ves.VESManifest;
import cn.yizhimcqiu.ves.ci.CustomItemManager;
import cn.yizhimcqiu.ves.ci.items.RegisteredCustomItem;
import com.mojang.logging.LogUtils;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class VESItemDefinitionFinder {
    private static Context context;
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final Map<String, RegisteredCustomItem> REGISTERED_ITEMS = new HashMap<>();

    public static void findAndRegisterAll() {
        new Thread(() -> {
            synchronized (Registry.class) {
                initContext();
                for (String namespace : VEScriptExecutor.SCRIPT_NAMESPACES) {
                    VESManifest manifest = VEScriptExecutor.readManifest(namespace);
                    if (manifest != null && manifest.items != null) {
                        for (String item : manifest.items) {
                            registerItem(namespace, item);
                        }
                    }
                }
            }
        }).start();
    }

    public static void reload() {
        REGISTERED_ITEMS.forEach((id, item) -> {
            try {
                item.entry = getEntryByItemId(id.split("::")[0], id.split("::")[1]);
            } catch (IOException e) {
                LOGGER.error("Error while reading item definition file!", e);
            }
        });
    }

    private static void initContext() {
        System.out.println("initContext INVOKE");
        context = Context.newBuilder("js")
                .allowAllAccess(true)
                .currentWorkingDirectory(Path.of("ves").toAbsolutePath())
                .build();
        System.out.println("initContext RETURN");
    }

    private static void registerItem(String namespace, String item) {
        try {
            RegisteredCustomItem registeredCustomItem = new RegisteredCustomItem(getEntryByItemId(namespace, item));
            Registry.register(Registries.ITEM, Identifier.of(namespace, item), registeredCustomItem);
            REGISTERED_ITEMS.put(namespace + "::" + item, registeredCustomItem);
        } catch (IOException e) {
            LOGGER.error("Error while reading item definition file!", e);
        }
    }

    private static CustomItemManager.CustomItemEntry getEntryByItemId(String namespace, String item) throws IOException {
        File file = VEScriptExecutor.VES_PATH.resolve(namespace).resolve(item + ".mjs").toFile();
        if (!file.exists()) {
            LOGGER.error("Item definition file {}::{} is not exists!", namespace, item);
        }
        Source source = VEScriptExecutor.createSource(file);
        return context.eval(source).as(CustomItemManager.CustomItemEntry.class);
    }
}
