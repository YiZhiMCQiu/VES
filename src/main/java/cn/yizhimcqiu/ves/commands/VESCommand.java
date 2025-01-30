package cn.yizhimcqiu.ves.commands;

import cn.yizhimcqiu.ves.VESVersion;
import cn.yizhimcqiu.ves.core.VEScriptExecutor;
import joptsimple.internal.Strings;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;

import java.util.List;

import static net.minecraft.server.command.CommandManager.*;

public class VESCommand {
    public void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, env) -> dispatcher.register(literal("ves")
                .then(literal("list")
                        .executes((ctx) -> {
                            List<String> sids = VEScriptExecutor.SCRIPT_IDENTIFIERS;
                            ctx.getSource().sendFeedback(() -> Text.of("已启用%d个脚本：\n".formatted(sids.size())+ Strings.join(sids, "，")), false);
                            return 1;
                        }))
                .then(literal("version")
                        .executes((ctx) -> {
                            ctx.getSource().sendFeedback(() -> VESVersion.VERSION_TEXT, false);
                            return 1;
                        }))));
    }
}
