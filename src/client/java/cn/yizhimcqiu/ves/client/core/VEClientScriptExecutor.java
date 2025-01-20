package cn.yizhimcqiu.ves.client.core;

import cn.yizhimcqiu.ves.core.VEScriptExecutor;

import java.nio.file.Path;

public class VEClientScriptExecutor extends VEScriptExecutor {
    public static VEClientScriptExecutor defaultLoader = new VEClientScriptExecutor();
    @Override
    protected boolean isInvalidScriptName(Path path) {
        return super.isInvalidScriptName(path) && !path.getFileName().endsWith("_client");
    }
}
