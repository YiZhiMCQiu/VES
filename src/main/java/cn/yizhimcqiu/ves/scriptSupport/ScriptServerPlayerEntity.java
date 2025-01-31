package cn.yizhimcqiu.ves.scriptSupport;

import cn.yizhimcqiu.ves.scriptSupport.world.ScriptWorld;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;
@SuppressWarnings("unused")
public class ScriptServerPlayerEntity extends ScriptEntity {
    public ScriptServerPlayerEntity(ServerPlayerEntity serverPlayer) {
        super(null, new ScriptWorld((ServerWorld) serverPlayer.getWorld()));
        this.entity = serverPlayer;
    }
    @Override
    public boolean spawn(int x, int y, int z) {
        return false;
    }
    public void sendMessage(String message) {
        entity.sendMessage(Text.literal(message));
    }
    public void sendTitle(String message) {
        ((ServerPlayerEntity)entity).sendMessage(Text.literal(message), true);
    }
    @Override
    public boolean kill() {
        entity.kill();
        return true;
    }
    public void setHealth(float health) {
        ((ServerPlayerEntity)entity).setHealth(health);
    }
    public void setFood(int food) {
        ((ServerPlayerEntity)entity).getHungerManager().setFoodLevel(food);
    }
    @Override
    public boolean setPosition(double x, double y, double z) {
        entity.setPosition(new Vec3d(x, y, z));
        return true;
    }
    public void damage(float amount) {
        entity.damage(DamageSource.GENERIC, amount);
    }
    public void setGameMode(String gameMode) {
        ((ServerPlayerEntity)entity).changeGameMode(GameMode.byName(gameMode));
    }
    public void giveItem(ScriptItemType item, int count) {
        ((ServerPlayerEntity)entity).giveItemStack(new ItemStack(item.$_getItem(), count));
    }
    public void giveItem(ScriptItemStack stack) {
        ((ServerPlayerEntity)entity).giveItemStack(stack.$_getItemStack());
    }
    public ServerPlayerEntity $_getPlayer() {
        return (ServerPlayerEntity) entity;
    }
}
