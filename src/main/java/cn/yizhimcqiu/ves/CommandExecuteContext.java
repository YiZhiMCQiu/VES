package cn.yizhimcqiu.ves;

import cn.yizhimcqiu.ves.scriptSupport.*;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class CommandExecuteContext {
    public final ScriptServerPlayerEntity player;
    public final ScriptServerCommandSource commandSource;
    public CommandExecuteContext(ServerPlayerEntity player, ServerCommandSource commandSource) {
        this.player = new ScriptServerPlayerEntity(player);
        this.commandSource = new ScriptServerCommandSource(commandSource);
    }
}
