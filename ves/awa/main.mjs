import { Color, CommandExecuteContext, Registry, CustomItemEntry, Items, Blocks, ActionResult, ScriptUtil } from ".ves_builtin/funcapi.mjs";
import { getContext } from ".ves_builtin/init.mjs";
import { PlayerPlaceBlockEvent } from ".ves_builtin/event.mjs";

var options = [];
if (IS_DEVELOP) {
    options.push(Color.LIGHT_RED+"开发"+Color.RESET);
}

const context = getContext();
context.getPlayer().sendMessage(Color.BLUE+"["+"AWA"+"] "+Color.GREEN+"脚本已加载, 环境情况:"+options)
Registry.registerItem("awa", "diamond_pack", new CustomItemEntry.Builder().withName("钻石礼包").withDescription("右键获得钻石x64").withTexture(Items.DIAMOND).onUse((stack, player)=>{
    player.giveItem(Items.parse("diamond"), 64)
    stack.remove(1);
    return ActionResult.SUCCESS
}, (stack, player)=>ActionResult.SUCCESS).build());
// type("net.minecraft.block.Block");
ScriptUtil.stackTrace();

PlayerPlaceBlockEvent.on(e => {
    if (e.block.equals(Blocks.parse("tnt"))) {
        e.player.sendMessage("违禁物品 禁止使用！");
        //e.cancel();
    }
})