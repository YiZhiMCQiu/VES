package cn.yizhimcqiu.ves.ci.items;

import cn.yizhimcqiu.ves.VentiScriptMod;
import cn.yizhimcqiu.ves.ci.CustomItemManager;
import cn.yizhimcqiu.ves.ci.items.components.VESNbtTags;
import cn.yizhimcqiu.ves.scriptSupport.ScriptItemStack;
import cn.yizhimcqiu.ves.scriptSupport.ScriptServerPlayerEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomItem extends Item {
    public CustomItem(Settings settings) {
        super(settings);
    }

    @Override
    public Text getName() {
        return Text.literal("How did you get this?");
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.literal(getCustomItemEntry(stack).name());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String desc = getCustomItemEntry(stack).description();
        if (!desc.isEmpty()) {
            tooltip.add(Text.literal(desc));
        }
    }

    public Item getTextureItem(ItemStack stack) {
        return getCustomItemEntry(stack).texture().$_getItem();
    }

    public static CustomItem register() {
        return Registry.register(Registry.ITEM, Identifier.of(VentiScriptMod.MOD_ID, "custom_item"), new CustomItem(new Item.Settings()));
    }
    public static CustomItemManager.CustomItemEntry getCustomItemEntry(ItemStack stack) {
        return CustomItemManager.getEntry(orDefault(stack.getOrCreateNbt().getString(VESNbtTags.CUSTOM_ITEM)));
    }
    private static String orDefault(String s) {
        return s == null ? "missing" : s;
    }
    public static ItemStack createCustomItem(String id) {
        ItemStack stack = new ItemStack(CustomItemManager.CUSTOM_ITEM);
        stack.getOrCreateNbt().putString(VESNbtTags.CUSTOM_ITEM, id);
        return stack;
    }
    public static void makeCustom(ScriptItemStack stack, String id) {
        stack.$_getItemStack().getOrCreateNbt().putString(VESNbtTags.CUSTOM_ITEM, id);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient() && user instanceof ServerPlayerEntity sp) {
            return getCustomItemEntry(stack).onServerUse().onUse(new ScriptItemStack(stack), new ScriptServerPlayerEntity(sp)).createActionResult(stack);
        } else {
            return getCustomItemEntry(stack).onClientUse().onUse(new ScriptItemStack(stack), null).createActionResult(stack);
        }
    }
}
