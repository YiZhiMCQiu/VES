import { Color, CommandExecuteContext, Registry, CustomItemEntry, Items, ActionResult } from ".ves_builtin/funcapi.mjs";
import { getContext } from ".ves_builtin/init.mjs";

var options = [];
if (IS_DEVELOP) {
    options.push(Color.LIGHT_RED+"开发"+Color.RESET);
}

const context = getContext();
context.getPlayer().sendMessage(Color.BLUE+"["+"AWA"+"] "+Color.GREEN+"脚本已加载, 环境情况:"+options)
Registry.registerItem("awa", new CustomItemEntry.Builder().withName("awa!").withDescription("钻许!").withTexture(Items.DIAMOND).onUse((stack, player)=>{
    player.sendMessage("awa!")
    return ActionResult.SUCCESS
}, (stack, player)=>ActionResult.SUCCESS).build());

