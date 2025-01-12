package cn.yizhimcqiu.ves.ci.block;

import cn.yizhimcqiu.ves.VentiScriptMod;
import cn.yizhimcqiu.ves.ci.CustomBlockManager;
import cn.yizhimcqiu.ves.util.ModPersistentStates;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class CustomBlock extends Block {
    private static final Map<BlockState, CustomBlockManager.CustomBlockEntry> ENTRY_MAP = new HashMap<>();
    public CustomBlock(Settings settings) {
        super(settings);
    }

    public BlockState getTextureBlock(BlockState state) {
        return ENTRY_MAP.getOrDefault(state, CustomBlockManager.EMPTY).texture().$_getBlock().getDefaultState();
    }
    public static CustomBlockManager.CustomBlockEntry getEntry(World world, BlockPos pos) {
        return CustomBlockManager.getEntry(getEntryId(world, pos));
    }
    public static String getEntryId(World world, BlockPos pos) {
        if (world instanceof ServerWorld serverWorld) {
            return ModPersistentStates.getServerState(serverWorld.getServer()).customBlockMap.getOrDefault(pos, "missing");
        }
        return "missing";
    }
    public static CustomBlock register() {
        return Registry.register(Registries.BLOCK, Identifier.of(VentiScriptMod.MOD_ID, "custom_block"), new CustomBlock(AbstractBlock.Settings.create()));
    }
    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        ENTRY_MAP.put(state, getEntry(world, pos));
    }
}
