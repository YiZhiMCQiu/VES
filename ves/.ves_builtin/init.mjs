import { CommandExecuteContext } from "funcapi.mjs";
export function getContext() {
    return new CommandExecuteContext(_context);
}
