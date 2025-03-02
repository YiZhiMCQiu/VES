package cn.yizhimcqiu.ves.commands;

import cn.yizhimcqiu.ves.core.VESItemDefinitionFinder;
import cn.yizhimcqiu.ves.event.Event;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.*;

public class ReloadCICommand {
    public void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, access, env) -> dispatcher.register(literal("reload-ves")
                .requires(source -> source.hasPermissionLevel(2))
                        .then(literal("ci")
                        .executes(context -> {
                            VESItemDefinitionFinder.findAndRegisterAll();
                            context.getSource().sendFeedback(() -> Text.translatable("reload-ci.success", VESItemDefinitionFinder.REGISTERED_ITEMS.size()), true);
                            return 1;
                        }))
                .then(literal("event").executes(context -> {
                    Event.clear();
                    return 1;
                }))));
    }
}
