import { Blocks, delay } from ".ves_builtin/funcapi.mjs";
import { getContext } from ".ves_builtin/init.mjs";

(async function(){const context = getContext();

const world = context.getPlayer().getWorld();
world.setBlock(context.getPlayer().getX(), context.getPlayer().getY(), context.getPlayer().getZ(), Blocks.TNT);
context.getPlayer().sendMessage("调用delay");
await delay(1000);
context.getPlayer().sendMessage("delay结束");})()
