package cn.yizhimcqiu.ves.client.mixin;

import cn.yizhimcqiu.ves.ci.block.CustomBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.block.BlockModels;
import net.minecraft.client.render.model.BakedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockModels.class)
public abstract class BlockModelsMixin {
    @Inject(at = @At("HEAD"), method = "getModel", cancellable = true)
    public void getModel(BlockState state, CallbackInfoReturnable<BakedModel> cir) {
        BlockModels self = (BlockModels) ((Object) this);
        if (state.getBlock() instanceof CustomBlock customBlock) {
            BakedModel bakedModel = self.models.get(customBlock.getTextureBlock(state));
            if (bakedModel == null) {
                bakedModel = self.getModelManager().getMissingModel();
            }
            cir.setReturnValue(bakedModel);
        }
    }
}
