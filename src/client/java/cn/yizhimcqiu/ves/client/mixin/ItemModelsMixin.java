package cn.yizhimcqiu.ves.client.mixin;

import cn.yizhimcqiu.ves.ci.CustomItemManager;
import cn.yizhimcqiu.ves.ci.items.CustomItem;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemModels.class)
public class ItemModelsMixin {
    @Inject(at = @At("HEAD"), method = "getModel(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/client/render/model/BakedModel;", cancellable = true)
    public void getModel(ItemStack stack, CallbackInfoReturnable<BakedModel> cir) {
        ItemModels self = (ItemModels) ((Object) this);
        if (stack.getItem() instanceof CustomItem customItem) {
            Item textureItem = customItem.getTextureItem(stack);
            BakedModel returnValue;
            cir.setReturnValue((returnValue = self.getModel(textureItem)) == null ? self.getModel(CustomItemManager.CUSTOM_ITEM) : returnValue);
        }
    }
}
