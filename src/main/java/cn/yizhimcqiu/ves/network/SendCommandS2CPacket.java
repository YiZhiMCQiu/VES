package cn.yizhimcqiu.ves.network;

import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;

public final class SendCommandS2CPacket implements FabricPacket {
    public static final PacketType<SendCommandS2CPacket> TYPE = PacketType.create(Networking.SEND_COMMAND_S2C, SendCommandS2CPacket::new);
    private final String command;
    public SendCommandS2CPacket(String command) {
        this.command = command;
    }
    public SendCommandS2CPacket(PacketByteBuf buf) {
        this.command = buf.readString();
    }
    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(command);
    }
    @Override
    public PacketType<?> getType() {
        return TYPE;
    }
    public String getCommand() {
        return command;
    }
}
