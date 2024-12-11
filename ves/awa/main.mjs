import { Entity, EntityTypes, NbtCompound } from "../.ves_builtin/fp.mjs";

for (var i = 0;i < 1;i++) {
    var sheep = new Entity(EntityTypes.ITEM, context.player)
    sheep.spawn(context.player.getX()+i, context.player.getY(), context.player.getZ())
    sheep.mergeNBT(sheep.getNBT()
        .putInt("Age", 100)
        .sub("Item")
            .putString("id", "minecraft:dragon_egg")
            .putInt("count", 1)
        .parent()
    )
    context.player.sendMessage("spawned "+sheep.getNBT().$_getNBT())
}
