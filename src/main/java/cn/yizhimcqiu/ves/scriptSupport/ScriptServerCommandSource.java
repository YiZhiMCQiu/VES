package cn.yizhimcqiu.ves.scriptSupport;

import cn.yizhimcqiu.ves.network.SendCommandS2CPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.Objects;

@SuppressWarnings("unused")
public class ScriptServerCommandSource {
    private final ServerCommandSource commandSource;
    public ScriptServerCommandSource(ServerCommandSource commandSource) {
        this.commandSource = commandSource;
    }
    public void sendFeedback(String text) {
        commandSource.sendFeedback(() -> Text.literal(text), false);
    }
    public void sendError(String error) {
        commandSource.sendError(Text.literal(error));
    }
    public void sendCommand(String command) {
        ServerPlayNetworking.send(Objects.requireNonNull(this.commandSource.getPlayer()), new SendCommandS2CPacket(command));
    }
}
