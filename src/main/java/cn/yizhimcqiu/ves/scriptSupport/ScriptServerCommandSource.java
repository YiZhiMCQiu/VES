package cn.yizhimcqiu.ves.scriptSupport;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
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
}
