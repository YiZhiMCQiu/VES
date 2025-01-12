package cn.yizhimcqiu.ves.ci.items.components;

import cn.yizhimcqiu.ves.VentiScriptMod;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class VESDataComponentTypes {
    public static final ComponentType<String> CUSTOM_ITEM_TYPE = register("custom_item_type", (builder) -> builder.codec(Codec.STRING).packetCodec(PacketCodecs.STRING).cache());
    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(VentiScriptMod.MOD_ID, id), builderOperator.apply(ComponentType.builder()).build());
    }
    public static void initialize() { }
}
