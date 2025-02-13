package cn.yizhimcqiu.ves.commands;

import cn.yizhimcqiu.ves.core.VESItemDefinitionFinder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.*;

public class ReloadCICommand {
    public void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, access, env) -> dispatcher.register(literal("reload-ci")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(context -> {
                    VESItemDefinitionFinder.reload();
                    context.getSource().sendFeedback(() -> Text.translatable("reload-ci.success", VESItemDefinitionFinder.REGISTERED_ITEMS.size()), true);
                    return 1;
                })));
    }
}
