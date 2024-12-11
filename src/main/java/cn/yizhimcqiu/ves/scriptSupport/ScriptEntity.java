package cn.yizhimcqiu.ves.scriptSupport;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class ScriptEntity {
    private final ScriptEntities type;
    private Entity entity = null;
    private ServerWorld world;
    public ScriptEntity(ScriptEntities type, ScriptServerPlayerEntity player) {
        this.type = type;
        this.world = player.$_getWorld();
    }
    public boolean spawn(int x, int y, int z) {
        if (this.entity == null) {
            this.entity = this.type.getEntityType().spawn(this.world, new BlockPos(x, y, z), SpawnReason.COMMAND);
            return true;
        } else {
            return false;
        }
    }
    public boolean spawn(double x, double y, double z) {
        return this.spawn((int) x, (int) y, (int) z);
    }
    public boolean kill() {
        if (this.entity == null) {
            return false;
        } else {
            this.entity.kill();
            return true;
        }
    }
    public boolean isAlive() {
        return this.entity != null && this.entity.isAlive();
    }
    public String getName() {
        return this.entity.getName().getString();
    }
    public String getUuid() {
        return this.entity.getUuidAsString();
    }
    public boolean setPosition(double x, double y, double z) {
        if (this.entity == null) {
            return false;
        } else {
            this.entity.setPosition(x, y, z);
            return true;
        }
    }
    public boolean setPosition(int x, int y, int z) {
        return this.setPosition(x, y,(double) z);
    }
    public ScriptNBTCompound getNBT() {
        return new ScriptNBTCompound(this.entity.writeNbt(new NbtCompound()));
    }
    public void mergeNBT(ScriptNBTCompound nbt) {
        this.entity.writeNbt(nbt.$_getNBT());
        this.entity.readNbt(nbt.$_getNBT());
    }
}
