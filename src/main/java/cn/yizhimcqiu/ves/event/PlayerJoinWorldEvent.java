package cn.yizhimcqiu.ves.event;

import cn.yizhimcqiu.ves.scriptSupport.ScriptServerPlayerEntity;

public record PlayerJoinWorldEvent(ScriptServerPlayerEntity player) {
    public static final Event<PlayerJoinWorldEvent> EVENT = new Event<>();
}
