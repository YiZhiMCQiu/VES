package cn.yizhimcqiu.ves.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record SendCommandS2CPayload(String command) implements CustomPayload {
    public static final Id<SendCommandS2CPayload> ID = new Id<>(Networking.SEND_COMMAND_S2C);
    public static final PacketCodec<RegistryByteBuf, SendCommandS2CPayload> CODEC = PacketCodec.of((value, buf) -> buf.writeString(value.command), buf -> new SendCommandS2CPayload(buf.readString()));
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
