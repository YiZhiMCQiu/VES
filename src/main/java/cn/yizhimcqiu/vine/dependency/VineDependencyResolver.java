package cn.yizhimcqiu.vine.dependency;

import cn.yizhimcqiu.ves.VESManifest;
import cn.yizhimcqiu.ves.VentiScriptMod;
import cn.yizhimcqiu.ves.core.VEScriptExecutor;
import cn.yizhimcqiu.ves.util.ListUtil;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VineDependencyResolver {
    public static List<String> getMissingDependencies(VESManifest manifest) {
        if (manifest == null || manifest.requires == null) {
            return null;
        }
        List<String> missingDependencies = new ArrayList<>();
        File vesFolder = Path.of("ves").toFile();
        File[] scripts;
        if ((scripts = vesFolder.listFiles()) != null) {
            List<String> names = ListUtil.convertList(Arrays.stream(scripts).toList(), File::getName);
            for (String dependency : manifest.requires) {
                if (!names.contains(dependency)) {
                    missingDependencies.add(dependency);
                }
            }
            missingDependencies.removeAll(VentiScriptMod.getInstalledPlugins());
            return missingDependencies;
        }
        return null;
    }
    public static List<String> getMissingDependencies(String sid) {
        return getMissingDependencies(VEScriptExecutor.readManifest(sid));
    }
}
