package cn.yizhimcqiu.ves.event;

import cn.yizhimcqiu.ves.scriptSupport.ScriptServerPlayerEntity;
import cn.yizhimcqiu.ves.scriptSupport.world.ScriptWorld;
import cn.yizhimcqiu.ves.scriptSupport.world.block.ScriptBlockType;

import java.util.Objects;

public final class PlayerPlaceBlockEvent implements Cancellable {
    public static final Event<PlayerPlaceBlockEvent> EVENT = new Event<>();
    public final ScriptServerPlayerEntity player;
    public final ScriptWorld world;
    public final ScriptBlockType block;
    public final int x;
    public final int y;
    public final int z;
    private boolean isCancelled;

    public PlayerPlaceBlockEvent(ScriptServerPlayerEntity player, ScriptWorld world, ScriptBlockType block, int x, int y, int z) {
        this.player = player;
        this.world = world;
        this.block = block;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PlayerPlaceBlockEvent) obj;
        return Objects.equals(this.player, that.player) &&
                Objects.equals(this.world, that.world) &&
                Objects.equals(this.block, that.block) &&
                this.x == that.x &&
                this.y == that.y &&
                this.z == that.z;
    }

    @Override
    public void cancel() {
        this.isCancelled = true;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }
}
