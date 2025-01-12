package cn.yizhimcqiu.ves.util;

import cn.yizhimcqiu.ves.VentiScriptMod;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Dynamic;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ModPersistentStates extends PersistentState {
    public static ModPersistentStates instance;
    private static final Logger LOGGER = LogUtils.getLogger();
    public final Map<BlockPos, String> customBlockMap = new HashMap<>();
    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        NbtList list = new NbtList();
        customBlockMap.forEach((pos, id) -> {
            NbtCompound nbtCompound = new NbtCompound();
            NbtElement encodedPos = BlockPos.CODEC.encode(pos, NbtOps.INSTANCE, new NbtCompound()).getOrThrow();
            nbtCompound.put("pos", encodedPos);
            nbtCompound.putString("id", id);
            list.add(nbtCompound);
        });
        nbt.put("CustomBlockMap", list);
        return nbt;
    }
    public static ModPersistentStates createFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup wrapperLookup) {
        ModPersistentStates states = new ModPersistentStates();
        NbtList cbmNbt = tag.getList("CustomBlockMap", 10);
        try {
            for (NbtElement element : cbmNbt) {
                if (element instanceof NbtCompound nbtCompound) {
                    BlockPos pos = BlockPos.CODEC.decode(new Dynamic<>(NbtOps.INSTANCE, nbtCompound.get("pos"))).getOrThrow().getFirst();
                    String id = nbtCompound.getString("id");
                    states.customBlockMap.put(pos, id);
                } else {
                    LOGGER.warn("{} is not a NbtCompound!", element.toString());
                }
            }
        } catch (IllegalStateException e) {
            LOGGER.error("Error while parsing \"CustomBlockMap\"", e);
        }
        return states;
    }

    private static final Type<ModPersistentStates> TYPE = new Type<>(
            ModPersistentStates::new,
            ModPersistentStates::createFromNbt,
            null
    );

    public static ModPersistentStates getServerState(MinecraftServer server) {
        PersistentStateManager persistentStateManager = server.getWorld(World.OVERWORLD).getPersistentStateManager();
        ModPersistentStates state = persistentStateManager.getOrCreate(TYPE, VentiScriptMod.MOD_ID);
        state.markDirty();
        return state;
    }
}
