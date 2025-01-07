// export const CommandExecuteContext = Java.type("cn.yizhimcqiu.ves.CommandExecuteContext");
export const Items = Java.type("cn.yizhimcqiu.ves.scriptSupport.ScriptItems")
export const Blocks = Java.type("cn.yizhimcqiu.ves.scriptSupport.world.block.ScriptBlocks")
// export const Entity = Java.type("cn.yizhimcqiu.ves.scriptSupport.ScriptEntity")
export const EntityTypes = Java.type("cn.yizhimcqiu.ves.scriptSupport.ScriptEntities")
export const NbtCompound = Java.type("cn.yizhimcqiu.ves.scriptSupport.ScriptNBTCompound")
export const Color = Java.type("cn.yizhimcqiu.ves.scriptSupport.ScriptChatColor")
export const Registry = Java.type("cn.yizhimcqiu.ves.scriptSupport.ScriptRegistry")
export const CustomItemEntry = Java.type("cn.yizhimcqiu.ves.ci.CustomItemManager").CustomItemEntry
export const ActionResult = Java.type("cn.yizhimcqiu.ves.scriptSupport.ScriptActionResults")

export class CommandExecuteContext {
    constructor(instance) {
        this.instance = instance
    }
    getPlayer() {
        return new Player(this.instance.player);
    }
    getCommandSource() {
        return this.instance.commandSource;
    }
}

// cn.yizhimcqiu.ves.scriptSupport.ScriptEntity
export class Entity {
    constructor(type, world) {
        this.instance = new (Java.type("cn.yizhimcqiu.ves.scriptSupport.ScriptEntity"))(type, world);
    }
    spawn(x, y, z) {
        return this.instance.spawn(x, y, z);
    }
    kill() {
        return this.instance.kill();
    }
    isAlive() {
        return this.instance.isAlive();
    }
    getName() {
        return this.instance.getName();
    }
    getUuid() {
        return this.instance.getUuid();
    }
    setPosition(x, y, z) {
        return this.instance.setPosition(x, y, z);
    }
    getX() {
        return this.instance.getX();
    }
    getY() {
        return this.instance.getY();
    }
    getZ() {
        return this.instance.getZ();
    }
    getNBT() {
        return this.instance.getNBT();
    }
    mergeNBT(nbt) {
        this.instance.mergeNBT(nbt.instance);
    }
    getWorld() {
        return this.instance.getWorld();
    }
}

// cn.yizhimcqiu.ves.scriptSupport.ScriptServerPlayer

export class Player extends Entity {
    constructor(player) {
        print(player.getWorld().isOverWorld())
        super(null, player.getWorld());
        this.instance = new (Java.type("cn.yizhimcqiu.ves.scriptSupport.ScriptServerPlayerEntity"))(player.$_getPlayer());
    }
    spawn(x, y, z) {
        return false;
    }
    sendMessage(message) {
        this.instance.sendMessage(message);
    }
    sendTitle(message) {
        this.instance.sendTitle(message);
    }
    kill() {
        return this.instance.kill();
    }
    setHealth(health) {
        this.instance.setHealth(health);
    }
    setFood(food) {
        this.instance.setFood(food);
    }
    setPosition(x, y, z) {
        return this.instance.setPosition(x, y, z);
    }
    damage(amount) {
        this.instance.damage(amount);
    }
    setGameMode(gameMode) {
        this.instance.setGameMode(gameMode);
    }
    giveItem(item, count) {
        this.instance.giveItem(item, count);
    }
}

/**
 * Usage: 
 * (async function() {
 *     <Your code here>
 *     await delay(xxx);
 * })
 * @param {number} ms 
 * @returns A Promise to let you achieve the delay effect.
 */
export function delay(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
  