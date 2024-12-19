package cn.yizhimcqiu.ves.scriptSupport.world;

import cn.yizhimcqiu.ves.scriptSupport.world.block.ScriptBlocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ScriptWorld {
    private final ServerWorld world;
    public ScriptWorld(ServerWorld world) {
        this.world = world;
    }
    public boolean isOverWorld() {
        return world.getRegistryKey() == World.OVERWORLD;
    }
    public boolean isNether() {
        return world.getRegistryKey() == World.NETHER;
    }
    public boolean isEnd() {
        return world.getRegistryKey() == World.END;
    }
    public void setBlock(int x, int y, int z, ScriptBlocks block) {
        this.world.setBlockState(new BlockPos(x, y, z), block.$_getBlock().getDefaultState());
    }
    public void setBlock(double x, double y, double z, ScriptBlocks block) {
        this.setBlock((int) Math.round(x),(int) Math.round(y), (int) Math.round(z), block);
    }
    public ScriptBlocks getBlockAt(int x, int y, int z) {
        return ScriptBlocks.getBlock(this.world.getBlockState(new BlockPos(x, y, z)).getBlock());
    }
    public ServerWorld $_getWorld() {
        return this.world;
    }
}
