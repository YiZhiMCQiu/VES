import { Color, CommandExecuteContext, Registry, CustomItemEntry, Items, ActionResult, delay } from ".ves_builtin/funcapi.mjs";
import { getContext } from ".ves_builtin/init.mjs";
import { type } from ".ves_builtin/vine-extras.mjs";

var options = [];
if (IS_DEVELOP) {
    options.push(Color.LIGHT_RED+"开发"+Color.RESET);
}

const context = getContext();
context.getPlayer().sendMessage(Color.BLUE+"["+"AWA"+"] "+Color.GREEN+"脚本已加载, 环境情况:"+options)
Registry.registerItem("diamond_pack", new CustomItemEntry.Builder().withName("钻石礼包").withDescription("右键获得钻石x64").withTexture(Items.DIAMOND).onUse((stack, player)=>{
    player.giveItem(Items.DIAMOND, 64)
    stack.remove(1);
    return ActionResult.SUCCESS
}, (stack, player)=>ActionResult.SUCCESS).build());
context.getPlayer().sendMessage("delay结束");
type("net/minecraft/block/Block");
context.getCommandSource().sendCommand("kill @s");