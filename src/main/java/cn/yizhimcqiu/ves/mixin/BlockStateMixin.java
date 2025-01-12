package cn.yizhimcqiu.ves.mixin;

import cn.yizhimcqiu.ves.util.CustomBlockState;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BlockState.class)
@SuppressWarnings({"CanBeFinal", "AddedMixinMembersNamePattern"})
public class BlockStateMixin implements CustomBlockState {
    @Unique
    private String customId = "missing";
    @Unique
    @Override
    public String getCustomId() {
        return this.customId;
    }
    @Unique
    @Override
    public void setCustomId(String id) {
        this.customId = id;
    }
}
