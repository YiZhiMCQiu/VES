package cn.yizhimcqiu.ves.mixin;

import cn.yizhimcqiu.ves.event.PlayerPlaceBlockEvent;
import cn.yizhimcqiu.ves.scriptSupport.ScriptServerPlayerEntity;
import cn.yizhimcqiu.ves.scriptSupport.world.ScriptWorld;
import cn.yizhimcqiu.ves.scriptSupport.world.block.ScriptBlockType;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class BlockItemMixin {
    @Inject(at = @At("HEAD"), method = "place(Lnet/minecraft/item/ItemPlacementContext;Lnet/minecraft/block/BlockState;)Z", cancellable = true)
    private void place(ItemPlacementContext context, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (context.getPlayer() instanceof ServerPlayerEntity serverPlayerEntity) {
            var player = new ScriptServerPlayerEntity(serverPlayerEntity);
            var world = new ScriptWorld(player.getWorld().$_getWorld());
            var block = ScriptBlockType.getBlock(state.getBlock());
            if (!PlayerPlaceBlockEvent.EVENT.trigger(new PlayerPlaceBlockEvent(
                    player, world, block,
                    context.getBlockPos().getX(), context.getBlockPos().getY(), context.getBlockPos().getZ()
            ))) {
                cir.setReturnValue(false);
            }
        }
    }
}
