import { Color, CommandExecuteContext, Registry } from ".ves_builtin/funcapi.mjs";
import { getContext } from ".ves_builtin/init.mjs";

var options = [];
if (IS_DEVELOP) {
    options.push(Color.LIGHT_RED+"开发"+Color.RESET);
}

const context = getContext();
context.getPlayer().sendMessage(Color.BLUE+"["+"AWA"+"] "+Color.GREEN+"脚本已加载, 环境情况:"+options)
Registry.registerItem();

