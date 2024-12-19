import { Blocks } from ".ves_builtin/funcapi.mjs";
import { getContext } from ".ves_builtin/init.mjs";

const context = getContext();

const world = context.getPlayer().getWorld();
world.setBlock(context.getPlayer().getX(), context.getPlayer().getY(), context.getPlayer().getZ(), Blocks.TNT);