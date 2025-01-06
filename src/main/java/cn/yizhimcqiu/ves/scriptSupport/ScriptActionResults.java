package cn.yizhimcqiu.ves.scriptSupport;

import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;

public enum ScriptActionResults {
    PASS, FAIL, SUCCESS;
    public <T> TypedActionResult<T> createActionResult(T value) {
        return switch (this) {
            case FAIL -> TypedActionResult.fail(value);
            case PASS -> TypedActionResult.pass(value);
            default -> TypedActionResult.success(value);
        };
    }
    public ActionResult createActionResult() {
        return switch (this) {
            case FAIL -> ActionResult.FAIL;
            case PASS -> ActionResult.PASS;
            default -> ActionResult.SUCCESS;
        };
    }
}
