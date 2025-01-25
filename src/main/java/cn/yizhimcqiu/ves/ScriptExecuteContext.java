package cn.yizhimcqiu.ves;

import cn.yizhimcqiu.ves.scriptSupport.*;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class ScriptExecuteContext {
    public final ScriptServerPlayerEntity player;
    public final ScriptServerCommandSource commandSource;
    public ScriptExecuteContext(ServerPlayerEntity player, ServerCommandSource commandSource) {
        this.player = new ScriptServerPlayerEntity(player);
        this.commandSource = new ScriptServerCommandSource(commandSource);
    }
}
