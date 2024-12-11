package cn.yizhimcqiu.ves.scriptSupport;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;

public class ScriptServerPlayerEntity {
    private final ServerPlayerEntity player;
    public ScriptServerPlayerEntity(ServerPlayerEntity serverPlayer) {
        this.player = serverPlayer;
    }
    public String getName() {
        return player.getName().getString();
    }
    public String getUuid() {
        return player.getUuidAsString();
    }
    public void sendMessage(String message) {
        player.sendMessage(Text.literal(message));
    }
    public void sendTitle(String message) {
        player.sendMessage(Text.literal(message), true);
    }
    public void kill() {
        player.kill();
    }
    public void setHealth(float health) {
        player.setHealth(health);
    }
    public void setFood(int food) {
        player.getHungerManager().setFoodLevel(food);
    }
    public void setPosition(double x, double y, double z) {
        player.setPosition(new Vec3d(x, y, z));
    }
    public double getX() {
        return player.getX();
    }
    public double getY() {
        return player.getY();
    }
    public double getZ() {
        return player.getZ();
    }
    public void damage(float amount) {
        player.damage(player.getDamageSources().genericKill(), amount);
    }
    public void setGameMode(String gameMode) {
        player.changeGameMode(GameMode.byName(gameMode));
    }
    public void giveItem(ScriptItems item, int count) {
        player.giveItemStack(new ItemStack(item.getItem(), count));
    }
    public ServerWorld $_getWorld() {
        return (ServerWorld) this.player.getWorld();
    }
}
