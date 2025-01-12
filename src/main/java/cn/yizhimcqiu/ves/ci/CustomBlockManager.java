package cn.yizhimcqiu.ves.ci;

import cn.yizhimcqiu.ves.ci.block.CustomBlock;
import cn.yizhimcqiu.ves.scriptSupport.*;
import cn.yizhimcqiu.ves.scriptSupport.world.block.ScriptBlocks;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class CustomBlockManager {
    public static final CustomBlockEntry EMPTY = new CustomBlockEntry.Builder().build();
    public static final CustomBlock CUSTOM_BLOCK = CustomBlock.register();
    private static final Map<String, CustomBlockManager.CustomBlockEntry> registeredBlocks = new HashMap<>();
    public static void register(String id, CustomBlockManager.CustomBlockEntry entry) {
        registeredBlocks.put(id, entry);
    }
    public static CustomBlockManager.CustomBlockEntry getEntry(String id) {
        return registeredBlocks.getOrDefault(id, new CustomBlockManager.CustomBlockEntry.Builder().build());
    }
    public record CustomBlockEntry(String name, CustomBlockManager.UseProtocol onServerUse, CustomBlockManager.ClientUseProtocol onClientUse, ScriptBlocks texture) {
        public static class Builder {
            private String name = "undefined";
            private CustomBlockManager.UseProtocol onServerUse = CustomBlockManager.UseProtocol.EMPTY;
            private CustomBlockManager.ClientUseProtocol onClientUse = CustomBlockManager.ClientUseProtocol.EMPTY;
            private ScriptBlocks texture = ScriptBlocks.BARRIER;

            public CustomBlockManager.CustomBlockEntry.Builder withName(String name) {
                this.name = name;
                return this;
            }

            public CustomBlockManager.CustomBlockEntry.Builder onUse(CustomBlockManager.UseProtocol onServerUse, CustomBlockManager.ClientUseProtocol onClientUse) {
                this.onServerUse = onServerUse;
                this.onClientUse = onClientUse;
                return this;
            }

            public CustomBlockManager.CustomBlockEntry.Builder withTexture(ScriptBlocks Block) {
                this.texture = Block;
                return this;
            }

            public CustomBlockManager.CustomBlockEntry build() {
                return new CustomBlockManager.CustomBlockEntry(name, onServerUse, onClientUse, texture);
            }
        }
    }
    @FunctionalInterface
    public interface UseProtocol {
        ScriptActionResults onUse(ScriptBlocks block, int x, int y, int z, ScriptServerPlayerEntity player);
        CustomBlockManager.UseProtocol EMPTY = (arg1, arg2, arg3, arg4, arg5) -> ScriptActionResults.PASS;
    }
    @FunctionalInterface
    public interface ClientUseProtocol {
        ScriptActionResults onUse(ScriptBlocks block, int x, int y, int z, ScriptEntity player);
        CustomBlockManager.ClientUseProtocol EMPTY = (arg1, arg2, arg3, arg4, arg5) -> ScriptActionResults.PASS;
    }
    
    public static void initialize() {
        register("test", new CustomBlockEntry.Builder().withName("awa").withTexture(ScriptBlocks.DIAMOND_BLOCK).build());
    }
}
