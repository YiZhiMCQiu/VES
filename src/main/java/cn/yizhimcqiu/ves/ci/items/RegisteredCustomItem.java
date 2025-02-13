package cn.yizhimcqiu.ves.ci.items;

import cn.yizhimcqiu.ves.ci.CustomItemManager;
import cn.yizhimcqiu.ves.scriptSupport.ScriptEntity;
import cn.yizhimcqiu.ves.scriptSupport.ScriptItemStack;
import cn.yizhimcqiu.ves.scriptSupport.ScriptServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class RegisteredCustomItem extends Item {
    public CustomItemManager.CustomItemEntry entry;

    public RegisteredCustomItem(CustomItemManager.CustomItemEntry entry) {
        super(new Settings());
        this.entry = entry;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (!entry.description().isEmpty()) {
            tooltip.add(Text.literal(entry.description()));
        }
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.literal(entry.name());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (world.isClient) {
            return entry.onClientUse().onUse(new ScriptItemStack(stack), new ScriptEntity(user, null)).createActionResult(stack);
        } else {
            return entry.onServerUse().onUse(new ScriptItemStack(stack), new ScriptServerPlayerEntity(((ServerPlayerEntity) user))).createActionResult(stack);
        }
    }

    public Item getTextureItem() {
        return this.entry.texture().$_getItem();
    }
}
