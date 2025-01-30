package cn.yizhimcqiu.ves.network;

import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;

public record SendCommandS2CPacket(String command) implements FabricPacket<ClientPlayPacketListener> {
    @Override
    public void write(PacketByteBuf buf) {

    }

    @Override
    public void apply(ClientPlayPacketListener listener) {

    }
}
