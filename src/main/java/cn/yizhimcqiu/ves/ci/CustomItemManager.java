package cn.yizhimcqiu.ves.ci;

import cn.yizhimcqiu.ves.ci.items.CustomItem;
import cn.yizhimcqiu.ves.scriptSupport.*;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class CustomItemManager {
    public static final CustomItem CUSTOM_ITEM = CustomItem.register();
    private static final Map<String, CustomItemEntry> registeredItems = new HashMap<>();
    public static void register(String id, CustomItemEntry entry) {
        registeredItems.put(id, entry);
    }
    public static CustomItemEntry getEntry(String id) {
        return registeredItems.getOrDefault(id, new CustomItemEntry.Builder().build());
    }
    public record CustomItemEntry(String name, String description, UseProtocol onServerUse, ClientUseProtocol onClientUse, ScriptItemType texture) {
        public static class Builder {
            private String name = "undefined";
            private String description = "";
            private UseProtocol onServerUse = UseProtocol.EMPTY;
            private ClientUseProtocol onClientUse = ClientUseProtocol.EMPTY;
            private ScriptItemType texture = new ScriptItemType(Identifier.ofVanilla("glow_ink_sac"));

            public Builder withName(String name) {
                this.name = name;
                return this;
            }

            public Builder withDescription(String description) {
                this.description = description;
                return this;
            }

            public Builder onUse(UseProtocol onServerUse, ClientUseProtocol onClientUse) {
                this.onServerUse = onServerUse;
                this.onClientUse = onClientUse;
                return this;
            }

            public Builder withTexture(ScriptItemType item) {
                this.texture = item;
                return this;
            }

            public CustomItemEntry build() {
                return new CustomItemEntry(name, description, onServerUse, onClientUse, texture);
            }
        }
    }
    @FunctionalInterface
    public interface UseProtocol {
        ScriptActionResults onUse(ScriptItemStack stack, ScriptServerPlayerEntity player);
        UseProtocol EMPTY = (arg1, arg2) -> ScriptActionResults.PASS;
    }
    @FunctionalInterface
    public interface ClientUseProtocol {
        ScriptActionResults onUse(ScriptItemStack stack, ScriptEntity player);
        ClientUseProtocol EMPTY = (arg1, arg2) -> ScriptActionResults.PASS;
    }
    public static void initialize() {

    }
}
