package cn.yizhimcqiu.ves.scriptSupport;

import cn.yizhimcqiu.ves.annotations.VESCallIgnore;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public enum ScriptItems {
    AIR,
    STONE,
    GRANITE,
    POLISHED_GRANITE,
    DIORITE,
    POLISHED_DIORITE,
    ANDESITE,
    POLISHED_ANDESITE,
    DEEPSLATE,
    COBBLED_DEEPSLATE,
    POLISHED_DEEPSLATE,
    CALCITE,
    TUFF,
    TUFF_SLAB,
    TUFF_STAIRS,
    TUFF_WALL,
    CHISELED_TUFF,
    POLISHED_TUFF,
    POLISHED_TUFF_SLAB,
    POLISHED_TUFF_STAIRS,
    POLISHED_TUFF_WALL,
    TUFF_BRICKS,
    TUFF_BRICK_SLAB,
    TUFF_BRICK_STAIRS,
    TUFF_BRICK_WALL,
    CHISELED_TUFF_BRICKS,
    DRIPSTONE_BLOCK,
    GRASS_BLOCK,
    DIRT,
    COARSE_DIRT,
    PODZOL,
    ROOTED_DIRT,
    MUD,
    CRIMSON_NYLIUM,
    WARPED_NYLIUM,
    COBBLESTONE,
    OAK_PLANKS,
    SPRUCE_PLANKS,
    BIRCH_PLANKS,
    JUNGLE_PLANKS,
    ACACIA_PLANKS,
    CHERRY_PLANKS,
    DARK_OAK_PLANKS,
    MANGROVE_PLANKS,
    BAMBOO_PLANKS,
    CRIMSON_PLANKS,
    WARPED_PLANKS,
    BAMBOO_MOSAIC,
    OAK_SAPLING,
    SPRUCE_SAPLING,
    BIRCH_SAPLING,
    JUNGLE_SAPLING,
    ACACIA_SAPLING,
    CHERRY_SAPLING,
    DARK_OAK_SAPLING,
    MANGROVE_PROPAGULE,
    BEDROCK,
    SAND,
    SUSPICIOUS_SAND,
    SUSPICIOUS_GRAVEL,
    RED_SAND,
    GRAVEL,
    COAL_ORE,
    DEEPSLATE_COAL_ORE,
    IRON_ORE,
    DEEPSLATE_IRON_ORE,
    COPPER_ORE,
    DEEPSLATE_COPPER_ORE,
    GOLD_ORE,
    DEEPSLATE_GOLD_ORE,
    REDSTONE_ORE,
    DEEPSLATE_REDSTONE_ORE,
    EMERALD_ORE,
    DEEPSLATE_EMERALD_ORE,
    LAPIS_ORE,
    DEEPSLATE_LAPIS_ORE,
    DIAMOND_ORE,
    DEEPSLATE_DIAMOND_ORE,
    NETHER_GOLD_ORE,
    NETHER_QUARTZ_ORE,
    ANCIENT_DEBRIS,
    COAL_BLOCK,
    RAW_IRON_BLOCK,
    RAW_COPPER_BLOCK,
    RAW_GOLD_BLOCK,
    HEAVY_CORE,
    AMETHYST_BLOCK,
    BUDDING_AMETHYST,
    IRON_BLOCK,
    COPPER_BLOCK,
    GOLD_BLOCK,
    DIAMOND_BLOCK,
    NETHERITE_BLOCK,
    EXPOSED_COPPER,
    WEATHERED_COPPER,
    OXIDIZED_COPPER,
    CHISELED_COPPER,
    EXPOSED_CHISELED_COPPER,
    WEATHERED_CHISELED_COPPER,
    OXIDIZED_CHISELED_COPPER,
    CUT_COPPER,
    EXPOSED_CUT_COPPER,
    WEATHERED_CUT_COPPER,
    OXIDIZED_CUT_COPPER,
    CUT_COPPER_STAIRS,
    EXPOSED_CUT_COPPER_STAIRS,
    WEATHERED_CUT_COPPER_STAIRS,
    OXIDIZED_CUT_COPPER_STAIRS,
    CUT_COPPER_SLAB,
    EXPOSED_CUT_COPPER_SLAB,
    WEATHERED_CUT_COPPER_SLAB,
    OXIDIZED_CUT_COPPER_SLAB,
    WAXED_COPPER_BLOCK,
    WAXED_EXPOSED_COPPER,
    WAXED_WEATHERED_COPPER,
    WAXED_OXIDIZED_COPPER,
    WAXED_CHISELED_COPPER,
    WAXED_EXPOSED_CHISELED_COPPER,
    WAXED_WEATHERED_CHISELED_COPPER,
    WAXED_OXIDIZED_CHISELED_COPPER,
    WAXED_CUT_COPPER,
    WAXED_EXPOSED_CUT_COPPER,
    WAXED_WEATHERED_CUT_COPPER,
    WAXED_OXIDIZED_CUT_COPPER,
    WAXED_CUT_COPPER_STAIRS,
    WAXED_EXPOSED_CUT_COPPER_STAIRS,
    WAXED_WEATHERED_CUT_COPPER_STAIRS,
    WAXED_OXIDIZED_CUT_COPPER_STAIRS,
    WAXED_CUT_COPPER_SLAB,
    WAXED_EXPOSED_CUT_COPPER_SLAB,
    WAXED_WEATHERED_CUT_COPPER_SLAB,
    WAXED_OXIDIZED_CUT_COPPER_SLAB,
    OAK_LOG,
    SPRUCE_LOG,
    BIRCH_LOG,
    JUNGLE_LOG,
    ACACIA_LOG,
    CHERRY_LOG,
    DARK_OAK_LOG,
    MANGROVE_LOG,
    MANGROVE_ROOTS,
    MUDDY_MANGROVE_ROOTS,
    CRIMSON_STEM,
    WARPED_STEM,
    BAMBOO_BLOCK,
    STRIPPED_OAK_LOG,
    STRIPPED_SPRUCE_LOG,
    STRIPPED_BIRCH_LOG,
    STRIPPED_JUNGLE_LOG,
    STRIPPED_ACACIA_LOG,
    STRIPPED_CHERRY_LOG,
    STRIPPED_DARK_OAK_LOG,
    STRIPPED_MANGROVE_LOG,
    STRIPPED_CRIMSON_STEM,
    STRIPPED_WARPED_STEM,
    STRIPPED_OAK_WOOD,
    STRIPPED_SPRUCE_WOOD,
    STRIPPED_BIRCH_WOOD,
    STRIPPED_JUNGLE_WOOD,
    STRIPPED_ACACIA_WOOD,
    STRIPPED_CHERRY_WOOD,
    STRIPPED_DARK_OAK_WOOD,
    STRIPPED_MANGROVE_WOOD,
    STRIPPED_CRIMSON_HYPHAE,
    STRIPPED_WARPED_HYPHAE,
    STRIPPED_BAMBOO_BLOCK,
    OAK_WOOD,
    SPRUCE_WOOD,
    BIRCH_WOOD,
    JUNGLE_WOOD,
    ACACIA_WOOD,
    CHERRY_WOOD,
    DARK_OAK_WOOD,
    MANGROVE_WOOD,
    CRIMSON_HYPHAE,
    WARPED_HYPHAE,
    OAK_LEAVES,
    SPRUCE_LEAVES,
    BIRCH_LEAVES,
    JUNGLE_LEAVES,
    ACACIA_LEAVES,
    CHERRY_LEAVES,
    DARK_OAK_LEAVES,
    MANGROVE_LEAVES,
    AZALEA_LEAVES,
    FLOWERING_AZALEA_LEAVES,
    SPONGE,
    WET_SPONGE,
    GLASS,
    TINTED_GLASS,
    LAPIS_BLOCK,
    SANDSTONE,
    CHISELED_SANDSTONE,
    CUT_SANDSTONE,
    COBWEB,
    SHORT_GRASS,
    FERN,
    AZALEA,
    FLOWERING_AZALEA,
    DEAD_BUSH,
    SEAGRASS,
    SEA_PICKLE,
    WHITE_WOOL,
    ORANGE_WOOL,
    MAGENTA_WOOL,
    LIGHT_BLUE_WOOL,
    YELLOW_WOOL,
    LIME_WOOL,
    PINK_WOOL,
    GRAY_WOOL,
    LIGHT_GRAY_WOOL,
    CYAN_WOOL,
    PURPLE_WOOL,
    BLUE_WOOL,
    BROWN_WOOL,
    GREEN_WOOL,
    RED_WOOL,
    BLACK_WOOL,
    DANDELION,
    POPPY,
    BLUE_ORCHID,
    ALLIUM,
    AZURE_BLUET,
    RED_TULIP,
    ORANGE_TULIP,
    WHITE_TULIP,
    PINK_TULIP,
    OXEYE_DAISY,
    CORNFLOWER,
    LILY_OF_THE_VALLEY,
    WITHER_ROSE,
    TORCHFLOWER,
    PITCHER_PLANT,
    SPORE_BLOSSOM,
    BROWN_MUSHROOM,
    RED_MUSHROOM,
    CRIMSON_FUNGUS,
    WARPED_FUNGUS,
    CRIMSON_ROOTS,
    WARPED_ROOTS,
    NETHER_SPROUTS,
    WEEPING_VINES,
    TWISTING_VINES,
    SUGAR_CANE,
    KELP,
    MOSS_CARPET,
    PINK_PETALS,
    MOSS_BLOCK,
    HANGING_ROOTS,
    BIG_DRIPLEAF,
    SMALL_DRIPLEAF,
    BAMBOO,
    OAK_SLAB,
    SPRUCE_SLAB,
    BIRCH_SLAB,
    JUNGLE_SLAB,
    ACACIA_SLAB,
    CHERRY_SLAB,
    DARK_OAK_SLAB,
    MANGROVE_SLAB,
    BAMBOO_SLAB,
    BAMBOO_MOSAIC_SLAB,
    CRIMSON_SLAB,
    WARPED_SLAB,
    STONE_SLAB,
    SMOOTH_STONE_SLAB,
    SANDSTONE_SLAB,
    CUT_SANDSTONE_SLAB,
    PETRIFIED_OAK_SLAB,
    COBBLESTONE_SLAB,
    BRICK_SLAB,
    STONE_BRICK_SLAB,
    MUD_BRICK_SLAB,
    NETHER_BRICK_SLAB,
    QUARTZ_SLAB,
    RED_SANDSTONE_SLAB,
    CUT_RED_SANDSTONE_SLAB,
    PURPUR_SLAB,
    PRISMARINE_SLAB,
    PRISMARINE_BRICK_SLAB,
    DARK_PRISMARINE_SLAB,
    SMOOTH_QUARTZ,
    SMOOTH_RED_SANDSTONE,
    SMOOTH_SANDSTONE,
    SMOOTH_STONE,
    BRICKS,
    BOOKSHELF,
    CHISELED_BOOKSHELF,
    DECORATED_POT,
    MOSSY_COBBLESTONE,
    OBSIDIAN,
    TORCH,
    END_ROD,
    CHORUS_PLANT,
    CHORUS_FLOWER,
    PURPUR_BLOCK,
    PURPUR_PILLAR,
    PURPUR_STAIRS,
    SPAWNER,
    CHEST,
    CRAFTING_TABLE,
    FARMLAND,
    FURNACE,
    LADDER,
    COBBLESTONE_STAIRS,
    SNOW,
    ICE,
    SNOW_BLOCK,
    CACTUS,
    CLAY,
    JUKEBOX,
    OAK_FENCE,
    SPRUCE_FENCE,
    BIRCH_FENCE,
    JUNGLE_FENCE,
    ACACIA_FENCE,
    CHERRY_FENCE,
    DARK_OAK_FENCE,
    MANGROVE_FENCE,
    BAMBOO_FENCE,
    CRIMSON_FENCE,
    WARPED_FENCE,
    PUMPKIN,
    CARVED_PUMPKIN,
    JACK_O_LANTERN,
    NETHERRACK,
    SOUL_SAND,
    SOUL_SOIL,
    BASALT,
    POLISHED_BASALT,
    SMOOTH_BASALT,
    SOUL_TORCH,
    GLOWSTONE,
    INFESTED_STONE,
    INFESTED_COBBLESTONE,
    INFESTED_STONE_BRICKS,
    INFESTED_MOSSY_STONE_BRICKS,
    INFESTED_CRACKED_STONE_BRICKS,
    INFESTED_CHISELED_STONE_BRICKS,
    INFESTED_DEEPSLATE,
    STONE_BRICKS,
    MOSSY_STONE_BRICKS,
    CRACKED_STONE_BRICKS,
    CHISELED_STONE_BRICKS,
    PACKED_MUD,
    MUD_BRICKS,
    DEEPSLATE_BRICKS,
    CRACKED_DEEPSLATE_BRICKS,
    DEEPSLATE_TILES,
    CRACKED_DEEPSLATE_TILES,
    CHISELED_DEEPSLATE,
    REINFORCED_DEEPSLATE,
    BROWN_MUSHROOM_BLOCK,
    RED_MUSHROOM_BLOCK,
    MUSHROOM_STEM,
    IRON_BARS,
    CHAIN,
    GLASS_PANE,
    MELON,
    VINE,
    GLOW_LICHEN,
    BRICK_STAIRS,
    STONE_BRICK_STAIRS,
    MUD_BRICK_STAIRS,
    MYCELIUM,
    LILY_PAD,
    NETHER_BRICKS,
    CRACKED_NETHER_BRICKS,
    CHISELED_NETHER_BRICKS,
    NETHER_BRICK_FENCE,
    NETHER_BRICK_STAIRS,
    SCULK,
    SCULK_VEIN,
    SCULK_CATALYST,
    SCULK_SHRIEKER,
    ENCHANTING_TABLE,
    END_PORTAL_FRAME,
    END_STONE,
    END_STONE_BRICKS,
    DRAGON_EGG,
    SANDSTONE_STAIRS,
    ENDER_CHEST,
    EMERALD_BLOCK,
    OAK_STAIRS,
    SPRUCE_STAIRS,
    BIRCH_STAIRS,
    JUNGLE_STAIRS,
    ACACIA_STAIRS,
    CHERRY_STAIRS,
    DARK_OAK_STAIRS,
    MANGROVE_STAIRS,
    BAMBOO_STAIRS,
    BAMBOO_MOSAIC_STAIRS,
    CRIMSON_STAIRS,
    WARPED_STAIRS,
    COMMAND_BLOCK,
    BEACON,
    COBBLESTONE_WALL,
    MOSSY_COBBLESTONE_WALL,
    BRICK_WALL,
    PRISMARINE_WALL,
    RED_SANDSTONE_WALL,
    MOSSY_STONE_BRICK_WALL,
    GRANITE_WALL,
    STONE_BRICK_WALL,
    MUD_BRICK_WALL,
    NETHER_BRICK_WALL,
    ANDESITE_WALL,
    RED_NETHER_BRICK_WALL,
    SANDSTONE_WALL,
    END_STONE_BRICK_WALL,
    DIORITE_WALL,
    BLACKSTONE_WALL,
    POLISHED_BLACKSTONE_WALL,
    POLISHED_BLACKSTONE_BRICK_WALL,
    COBBLED_DEEPSLATE_WALL,
    POLISHED_DEEPSLATE_WALL,
    DEEPSLATE_BRICK_WALL,
    DEEPSLATE_TILE_WALL,
    ANVIL,
    CHIPPED_ANVIL,
    DAMAGED_ANVIL,
    CHISELED_QUARTZ_BLOCK,
    QUARTZ_BLOCK,
    QUARTZ_BRICKS,
    QUARTZ_PILLAR,
    QUARTZ_STAIRS,
    WHITE_TERRACOTTA,
    ORANGE_TERRACOTTA,
    MAGENTA_TERRACOTTA,
    LIGHT_BLUE_TERRACOTTA,
    YELLOW_TERRACOTTA,
    LIME_TERRACOTTA,
    PINK_TERRACOTTA,
    GRAY_TERRACOTTA,
    LIGHT_GRAY_TERRACOTTA,
    CYAN_TERRACOTTA,
    PURPLE_TERRACOTTA,
    BLUE_TERRACOTTA,
    BROWN_TERRACOTTA,
    GREEN_TERRACOTTA,
    RED_TERRACOTTA,
    BLACK_TERRACOTTA,
    BARRIER,
    LIGHT,
    HAY_BLOCK,
    WHITE_CARPET,
    ORANGE_CARPET,
    MAGENTA_CARPET,
    LIGHT_BLUE_CARPET,
    YELLOW_CARPET,
    LIME_CARPET,
    PINK_CARPET,
    GRAY_CARPET,
    LIGHT_GRAY_CARPET,
    CYAN_CARPET,
    PURPLE_CARPET,
    BLUE_CARPET,
    BROWN_CARPET,
    GREEN_CARPET,
    RED_CARPET,
    BLACK_CARPET,
    TERRACOTTA,
    PACKED_ICE,
    DIRT_PATH,
    SUNFLOWER,
    LILAC,
    ROSE_BUSH,
    PEONY,
    TALL_GRASS,
    LARGE_FERN,
    WHITE_STAINED_GLASS,
    ORANGE_STAINED_GLASS,
    MAGENTA_STAINED_GLASS,
    LIGHT_BLUE_STAINED_GLASS,
    YELLOW_STAINED_GLASS,
    LIME_STAINED_GLASS,
    PINK_STAINED_GLASS,
    GRAY_STAINED_GLASS,
    LIGHT_GRAY_STAINED_GLASS,
    CYAN_STAINED_GLASS,
    PURPLE_STAINED_GLASS,
    BLUE_STAINED_GLASS,
    BROWN_STAINED_GLASS,
    GREEN_STAINED_GLASS,
    RED_STAINED_GLASS,
    BLACK_STAINED_GLASS,
    WHITE_STAINED_GLASS_PANE,
    ORANGE_STAINED_GLASS_PANE,
    MAGENTA_STAINED_GLASS_PANE,
    LIGHT_BLUE_STAINED_GLASS_PANE,
    YELLOW_STAINED_GLASS_PANE,
    LIME_STAINED_GLASS_PANE,
    PINK_STAINED_GLASS_PANE,
    GRAY_STAINED_GLASS_PANE,
    LIGHT_GRAY_STAINED_GLASS_PANE,
    CYAN_STAINED_GLASS_PANE,
    PURPLE_STAINED_GLASS_PANE,
    BLUE_STAINED_GLASS_PANE,
    BROWN_STAINED_GLASS_PANE,
    GREEN_STAINED_GLASS_PANE,
    RED_STAINED_GLASS_PANE,
    BLACK_STAINED_GLASS_PANE,
    PRISMARINE,
    PRISMARINE_BRICKS,
    DARK_PRISMARINE,
    PRISMARINE_STAIRS,
    PRISMARINE_BRICK_STAIRS,
    DARK_PRISMARINE_STAIRS,
    SEA_LANTERN,
    RED_SANDSTONE,
    CHISELED_RED_SANDSTONE,
    CUT_RED_SANDSTONE,
    RED_SANDSTONE_STAIRS,
    REPEATING_COMMAND_BLOCK,
    CHAIN_COMMAND_BLOCK,
    MAGMA_BLOCK,
    NETHER_WART_BLOCK,
    WARPED_WART_BLOCK,
    RED_NETHER_BRICKS,
    BONE_BLOCK,
    STRUCTURE_VOID,
    SHULKER_BOX,
    WHITE_SHULKER_BOX,
    ORANGE_SHULKER_BOX,
    MAGENTA_SHULKER_BOX,
    LIGHT_BLUE_SHULKER_BOX,
    YELLOW_SHULKER_BOX,
    LIME_SHULKER_BOX,
    PINK_SHULKER_BOX,
    GRAY_SHULKER_BOX,
    LIGHT_GRAY_SHULKER_BOX,
    CYAN_SHULKER_BOX,
    PURPLE_SHULKER_BOX,
    BLUE_SHULKER_BOX,
    BROWN_SHULKER_BOX,
    GREEN_SHULKER_BOX,
    RED_SHULKER_BOX,
    BLACK_SHULKER_BOX,
    WHITE_GLAZED_TERRACOTTA,
    ORANGE_GLAZED_TERRACOTTA,
    MAGENTA_GLAZED_TERRACOTTA,
    LIGHT_BLUE_GLAZED_TERRACOTTA,
    YELLOW_GLAZED_TERRACOTTA,
    LIME_GLAZED_TERRACOTTA,
    PINK_GLAZED_TERRACOTTA,
    GRAY_GLAZED_TERRACOTTA,
    LIGHT_GRAY_GLAZED_TERRACOTTA,
    CYAN_GLAZED_TERRACOTTA,
    PURPLE_GLAZED_TERRACOTTA,
    BLUE_GLAZED_TERRACOTTA,
    BROWN_GLAZED_TERRACOTTA,
    GREEN_GLAZED_TERRACOTTA,
    RED_GLAZED_TERRACOTTA,
    BLACK_GLAZED_TERRACOTTA,
    WHITE_CONCRETE,
    ORANGE_CONCRETE,
    MAGENTA_CONCRETE,
    LIGHT_BLUE_CONCRETE,
    YELLOW_CONCRETE,
    LIME_CONCRETE,
    PINK_CONCRETE,
    GRAY_CONCRETE,
    LIGHT_GRAY_CONCRETE,
    CYAN_CONCRETE,
    PURPLE_CONCRETE,
    BLUE_CONCRETE,
    BROWN_CONCRETE,
    GREEN_CONCRETE,
    RED_CONCRETE,
    BLACK_CONCRETE,
    WHITE_CONCRETE_POWDER,
    ORANGE_CONCRETE_POWDER,
    MAGENTA_CONCRETE_POWDER,
    LIGHT_BLUE_CONCRETE_POWDER,
    YELLOW_CONCRETE_POWDER,
    LIME_CONCRETE_POWDER,
    PINK_CONCRETE_POWDER,
    GRAY_CONCRETE_POWDER,
    LIGHT_GRAY_CONCRETE_POWDER,
    CYAN_CONCRETE_POWDER,
    PURPLE_CONCRETE_POWDER,
    BLUE_CONCRETE_POWDER,
    BROWN_CONCRETE_POWDER,
    GREEN_CONCRETE_POWDER,
    RED_CONCRETE_POWDER,
    BLACK_CONCRETE_POWDER,
    TURTLE_EGG,
    SNIFFER_EGG,
    DEAD_TUBE_CORAL_BLOCK,
    DEAD_BRAIN_CORAL_BLOCK,
    DEAD_BUBBLE_CORAL_BLOCK,
    DEAD_FIRE_CORAL_BLOCK,
    DEAD_HORN_CORAL_BLOCK,
    TUBE_CORAL_BLOCK,
    BRAIN_CORAL_BLOCK,
    BUBBLE_CORAL_BLOCK,
    FIRE_CORAL_BLOCK,
    HORN_CORAL_BLOCK,
    TUBE_CORAL,
    BRAIN_CORAL,
    BUBBLE_CORAL,
    FIRE_CORAL,
    HORN_CORAL,
    DEAD_BRAIN_CORAL,
    DEAD_BUBBLE_CORAL,
    DEAD_FIRE_CORAL,
    DEAD_HORN_CORAL,
    DEAD_TUBE_CORAL,
    TUBE_CORAL_FAN,
    BRAIN_CORAL_FAN,
    BUBBLE_CORAL_FAN,
    FIRE_CORAL_FAN,
    HORN_CORAL_FAN,
    DEAD_TUBE_CORAL_FAN,
    DEAD_BRAIN_CORAL_FAN,
    DEAD_BUBBLE_CORAL_FAN,
    DEAD_FIRE_CORAL_FAN,
    DEAD_HORN_CORAL_FAN,
    BLUE_ICE,
    CONDUIT,
    POLISHED_GRANITE_STAIRS,
    SMOOTH_RED_SANDSTONE_STAIRS,
    MOSSY_STONE_BRICK_STAIRS,
    POLISHED_DIORITE_STAIRS,
    MOSSY_COBBLESTONE_STAIRS,
    END_STONE_BRICK_STAIRS,
    STONE_STAIRS,
    SMOOTH_SANDSTONE_STAIRS,
    SMOOTH_QUARTZ_STAIRS,
    GRANITE_STAIRS,
    ANDESITE_STAIRS,
    RED_NETHER_BRICK_STAIRS,
    POLISHED_ANDESITE_STAIRS,
    DIORITE_STAIRS,
    COBBLED_DEEPSLATE_STAIRS,
    POLISHED_DEEPSLATE_STAIRS,
    DEEPSLATE_BRICK_STAIRS,
    DEEPSLATE_TILE_STAIRS,
    POLISHED_GRANITE_SLAB,
    SMOOTH_RED_SANDSTONE_SLAB,
    MOSSY_STONE_BRICK_SLAB,
    POLISHED_DIORITE_SLAB,
    MOSSY_COBBLESTONE_SLAB,
    END_STONE_BRICK_SLAB,
    SMOOTH_SANDSTONE_SLAB,
    SMOOTH_QUARTZ_SLAB,
    GRANITE_SLAB,
    ANDESITE_SLAB,
    RED_NETHER_BRICK_SLAB,
    POLISHED_ANDESITE_SLAB,
    DIORITE_SLAB,
    COBBLED_DEEPSLATE_SLAB,
    POLISHED_DEEPSLATE_SLAB,
    DEEPSLATE_BRICK_SLAB,
    DEEPSLATE_TILE_SLAB,
    SCAFFOLDING,
    REDSTONE,
    REDSTONE_TORCH,
    REDSTONE_BLOCK,
    REPEATER,
    COMPARATOR,
    PISTON,
    STICKY_PISTON,
    SLIME_BLOCK,
    HONEY_BLOCK,
    OBSERVER,
    HOPPER,
    DISPENSER,
    DROPPER,
    LECTERN,
    TARGET,
    LEVER,
    LIGHTNING_ROD,
    DAYLIGHT_DETECTOR,
    SCULK_SENSOR,
    CALIBRATED_SCULK_SENSOR,
    TRIPWIRE_HOOK,
    TRAPPED_CHEST,
    TNT,
    REDSTONE_LAMP,
    NOTE_BLOCK,
    STONE_BUTTON,
    POLISHED_BLACKSTONE_BUTTON,
    OAK_BUTTON,
    SPRUCE_BUTTON,
    BIRCH_BUTTON,
    JUNGLE_BUTTON,
    ACACIA_BUTTON,
    CHERRY_BUTTON,
    DARK_OAK_BUTTON,
    MANGROVE_BUTTON,
    BAMBOO_BUTTON,
    CRIMSON_BUTTON,
    WARPED_BUTTON,
    STONE_PRESSURE_PLATE,
    POLISHED_BLACKSTONE_PRESSURE_PLATE,
    LIGHT_WEIGHTED_PRESSURE_PLATE,
    HEAVY_WEIGHTED_PRESSURE_PLATE,
    OAK_PRESSURE_PLATE,
    SPRUCE_PRESSURE_PLATE,
    BIRCH_PRESSURE_PLATE,
    JUNGLE_PRESSURE_PLATE,
    ACACIA_PRESSURE_PLATE,
    CHERRY_PRESSURE_PLATE,
    DARK_OAK_PRESSURE_PLATE,
    MANGROVE_PRESSURE_PLATE,
    BAMBOO_PRESSURE_PLATE,
    CRIMSON_PRESSURE_PLATE,
    WARPED_PRESSURE_PLATE,
    IRON_DOOR,
    OAK_DOOR,
    SPRUCE_DOOR,
    BIRCH_DOOR,
    JUNGLE_DOOR,
    ACACIA_DOOR,
    CHERRY_DOOR,
    DARK_OAK_DOOR,
    MANGROVE_DOOR,
    BAMBOO_DOOR,
    CRIMSON_DOOR,
    WARPED_DOOR,
    COPPER_DOOR,
    EXPOSED_COPPER_DOOR,
    WEATHERED_COPPER_DOOR,
    OXIDIZED_COPPER_DOOR,
    WAXED_COPPER_DOOR,
    WAXED_EXPOSED_COPPER_DOOR,
    WAXED_WEATHERED_COPPER_DOOR,
    WAXED_OXIDIZED_COPPER_DOOR,
    IRON_TRAPDOOR,
    OAK_TRAPDOOR,
    SPRUCE_TRAPDOOR,
    BIRCH_TRAPDOOR,
    JUNGLE_TRAPDOOR,
    ACACIA_TRAPDOOR,
    CHERRY_TRAPDOOR,
    DARK_OAK_TRAPDOOR,
    MANGROVE_TRAPDOOR,
    BAMBOO_TRAPDOOR,
    CRIMSON_TRAPDOOR,
    WARPED_TRAPDOOR,
    COPPER_TRAPDOOR,
    EXPOSED_COPPER_TRAPDOOR,
    WEATHERED_COPPER_TRAPDOOR,
    OXIDIZED_COPPER_TRAPDOOR,
    WAXED_COPPER_TRAPDOOR,
    WAXED_EXPOSED_COPPER_TRAPDOOR,
    WAXED_WEATHERED_COPPER_TRAPDOOR,
    WAXED_OXIDIZED_COPPER_TRAPDOOR,
    OAK_FENCE_GATE,
    SPRUCE_FENCE_GATE,
    BIRCH_FENCE_GATE,
    JUNGLE_FENCE_GATE,
    ACACIA_FENCE_GATE,
    CHERRY_FENCE_GATE,
    DARK_OAK_FENCE_GATE,
    MANGROVE_FENCE_GATE,
    BAMBOO_FENCE_GATE,
    CRIMSON_FENCE_GATE,
    WARPED_FENCE_GATE,
    POWERED_RAIL,
    DETECTOR_RAIL,
    RAIL,
    ACTIVATOR_RAIL,
    SADDLE,
    MINECART,
    CHEST_MINECART,
    FURNACE_MINECART,
    TNT_MINECART,
    HOPPER_MINECART,
    CARROT_ON_A_STICK,
    WARPED_FUNGUS_ON_A_STICK,
    ELYTRA,
    OAK_BOAT,
    OAK_CHEST_BOAT,
    SPRUCE_BOAT,
    SPRUCE_CHEST_BOAT,
    BIRCH_BOAT,
    BIRCH_CHEST_BOAT,
    JUNGLE_BOAT,
    JUNGLE_CHEST_BOAT,
    ACACIA_BOAT,
    ACACIA_CHEST_BOAT,
    CHERRY_BOAT,
    CHERRY_CHEST_BOAT,
    DARK_OAK_BOAT,
    DARK_OAK_CHEST_BOAT,
    MANGROVE_BOAT,
    MANGROVE_CHEST_BOAT,
    BAMBOO_RAFT,
    BAMBOO_CHEST_RAFT,
    STRUCTURE_BLOCK,
    JIGSAW,
    TURTLE_HELMET,
    TURTLE_SCUTE,
    ARMADILLO_SCUTE,
    WOLF_ARMOR,
    FLINT_AND_STEEL,
    BOWL,
    APPLE,
    BOW,
    ARROW,
    COAL,
    CHARCOAL,
    DIAMOND,
    EMERALD,
    LAPIS_LAZULI,
    QUARTZ,
    AMETHYST_SHARD,
    RAW_IRON,
    IRON_INGOT,
    RAW_COPPER,
    COPPER_INGOT,
    RAW_GOLD,
    GOLD_INGOT,
    NETHERITE_INGOT,
    NETHERITE_SCRAP,
    WOODEN_SWORD,
    WOODEN_SHOVEL,
    WOODEN_PICKAXE,
    WOODEN_AXE,
    WOODEN_HOE,
    STONE_SWORD,
    STONE_SHOVEL,
    STONE_PICKAXE,
    STONE_AXE,
    STONE_HOE,
    GOLDEN_SWORD,
    GOLDEN_SHOVEL,
    GOLDEN_PICKAXE,
    GOLDEN_AXE,
    GOLDEN_HOE,
    IRON_SWORD,
    IRON_SHOVEL,
    IRON_PICKAXE,
    IRON_AXE,
    IRON_HOE,
    DIAMOND_SWORD,
    DIAMOND_SHOVEL,
    DIAMOND_PICKAXE,
    DIAMOND_AXE,
    DIAMOND_HOE,
    NETHERITE_SWORD,
    NETHERITE_SHOVEL,
    NETHERITE_PICKAXE,
    NETHERITE_AXE,
    NETHERITE_HOE,
    STICK,
    MUSHROOM_STEW,
    STRING,
    FEATHER,
    GUNPOWDER,
    WHEAT_SEEDS,
    WHEAT,
    BREAD,
    LEATHER_HELMET,
    LEATHER_CHESTPLATE,
    LEATHER_LEGGINGS,
    LEATHER_BOOTS,
    CHAINMAIL_HELMET,
    CHAINMAIL_CHESTPLATE,
    CHAINMAIL_LEGGINGS,
    CHAINMAIL_BOOTS,
    IRON_HELMET,
    IRON_CHESTPLATE,
    IRON_LEGGINGS,
    IRON_BOOTS,
    DIAMOND_HELMET,
    DIAMOND_CHESTPLATE,
    DIAMOND_LEGGINGS,
    DIAMOND_BOOTS,
    GOLDEN_HELMET,
    GOLDEN_CHESTPLATE,
    GOLDEN_LEGGINGS,
    GOLDEN_BOOTS,
    NETHERITE_HELMET,
    NETHERITE_CHESTPLATE,
    NETHERITE_LEGGINGS,
    NETHERITE_BOOTS,
    FLINT,
    PORKCHOP,
    COOKED_PORKCHOP,
    PAINTING,
    GOLDEN_APPLE,
    ENCHANTED_GOLDEN_APPLE,
    OAK_SIGN,
    SPRUCE_SIGN,
    BIRCH_SIGN,
    JUNGLE_SIGN,
    ACACIA_SIGN,
    CHERRY_SIGN,
    DARK_OAK_SIGN,
    MANGROVE_SIGN,
    BAMBOO_SIGN,
    CRIMSON_SIGN,
    WARPED_SIGN,
    OAK_HANGING_SIGN,
    SPRUCE_HANGING_SIGN,
    BIRCH_HANGING_SIGN,
    JUNGLE_HANGING_SIGN,
    ACACIA_HANGING_SIGN,
    CHERRY_HANGING_SIGN,
    DARK_OAK_HANGING_SIGN,
    MANGROVE_HANGING_SIGN,
    BAMBOO_HANGING_SIGN,
    CRIMSON_HANGING_SIGN,
    WARPED_HANGING_SIGN,
    BUCKET,
    WATER_BUCKET,
    LAVA_BUCKET,
    POWDER_SNOW_BUCKET,
    SNOWBALL,
    LEATHER,
    MILK_BUCKET,
    PUFFERFISH_BUCKET,
    SALMON_BUCKET,
    COD_BUCKET,
    TROPICAL_FISH_BUCKET,
    AXOLOTL_BUCKET,
    TADPOLE_BUCKET,
    BRICK,
    CLAY_BALL,
    DRIED_KELP_BLOCK,
    PAPER,
    BOOK,
    SLIME_BALL,
    EGG,
    COMPASS,
    RECOVERY_COMPASS,
    BUNDLE,
    FISHING_ROD,
    CLOCK,
    SPYGLASS,
    GLOWSTONE_DUST,
    COD,
    SALMON,
    TROPICAL_FISH,
    PUFFERFISH,
    COOKED_COD,
    COOKED_SALMON,
    INK_SAC,
    GLOW_INK_SAC,
    COCOA_BEANS,
    WHITE_DYE,
    ORANGE_DYE,
    MAGENTA_DYE,
    LIGHT_BLUE_DYE,
    YELLOW_DYE,
    LIME_DYE,
    PINK_DYE,
    GRAY_DYE,
    LIGHT_GRAY_DYE,
    CYAN_DYE,
    PURPLE_DYE,
    BLUE_DYE,
    BROWN_DYE,
    GREEN_DYE,
    RED_DYE,
    BLACK_DYE,
    BONE_MEAL,
    BONE,
    SUGAR,
    CAKE,
    WHITE_BED,
    ORANGE_BED,
    MAGENTA_BED,
    LIGHT_BLUE_BED,
    YELLOW_BED,
    LIME_BED,
    PINK_BED,
    GRAY_BED,
    LIGHT_GRAY_BED,
    CYAN_BED,
    PURPLE_BED,
    BLUE_BED,
    BROWN_BED,
    GREEN_BED,
    RED_BED,
    BLACK_BED,
    COOKIE,
    CRAFTER,
    FILLED_MAP,
    SHEARS,
    MELON_SLICE,
    DRIED_KELP,
    PUMPKIN_SEEDS,
    MELON_SEEDS,
    BEEF,
    COOKED_BEEF,
    CHICKEN,
    COOKED_CHICKEN,
    ROTTEN_FLESH,
    ENDER_PEARL,
    BLAZE_ROD,
    GHAST_TEAR,
    GOLD_NUGGET,
    NETHER_WART,
    POTION,
    GLASS_BOTTLE,
    SPIDER_EYE,
    FERMENTED_SPIDER_EYE,
    BLAZE_POWDER,
    MAGMA_CREAM,
    BREWING_STAND,
    CAULDRON,
    ENDER_EYE,
    GLISTERING_MELON_SLICE,
    ARMADILLO_SPAWN_EGG,
    ALLAY_SPAWN_EGG,
    AXOLOTL_SPAWN_EGG,
    BAT_SPAWN_EGG,
    BEE_SPAWN_EGG,
    BLAZE_SPAWN_EGG,
    BOGGED_SPAWN_EGG,
    BREEZE_SPAWN_EGG,
    CAT_SPAWN_EGG,
    CAMEL_SPAWN_EGG,
    CAVE_SPIDER_SPAWN_EGG,
    CHICKEN_SPAWN_EGG,
    COD_SPAWN_EGG,
    COW_SPAWN_EGG,
    CREEPER_SPAWN_EGG,
    DOLPHIN_SPAWN_EGG,
    DONKEY_SPAWN_EGG,
    DROWNED_SPAWN_EGG,
    ELDER_GUARDIAN_SPAWN_EGG,
    ENDER_DRAGON_SPAWN_EGG,
    ENDERMAN_SPAWN_EGG,
    ENDERMITE_SPAWN_EGG,
    EVOKER_SPAWN_EGG,
    FOX_SPAWN_EGG,
    FROG_SPAWN_EGG,
    GHAST_SPAWN_EGG,
    GLOW_SQUID_SPAWN_EGG,
    GOAT_SPAWN_EGG,
    GUARDIAN_SPAWN_EGG,
    HOGLIN_SPAWN_EGG,
    HORSE_SPAWN_EGG,
    HUSK_SPAWN_EGG,
    IRON_GOLEM_SPAWN_EGG,
    LLAMA_SPAWN_EGG,
    MAGMA_CUBE_SPAWN_EGG,
    MOOSHROOM_SPAWN_EGG,
    MULE_SPAWN_EGG,
    OCELOT_SPAWN_EGG,
    PANDA_SPAWN_EGG,
    PARROT_SPAWN_EGG,
    PHANTOM_SPAWN_EGG,
    PIG_SPAWN_EGG,
    PIGLIN_SPAWN_EGG,
    PIGLIN_BRUTE_SPAWN_EGG,
    PILLAGER_SPAWN_EGG,
    POLAR_BEAR_SPAWN_EGG,
    PUFFERFISH_SPAWN_EGG,
    RABBIT_SPAWN_EGG,
    RAVAGER_SPAWN_EGG,
    SALMON_SPAWN_EGG,
    SHEEP_SPAWN_EGG,
    SHULKER_SPAWN_EGG,
    SILVERFISH_SPAWN_EGG,
    SKELETON_SPAWN_EGG,
    SKELETON_HORSE_SPAWN_EGG,
    SLIME_SPAWN_EGG,
    SNIFFER_SPAWN_EGG,
    SNOW_GOLEM_SPAWN_EGG,
    SPIDER_SPAWN_EGG,
    SQUID_SPAWN_EGG,
    STRAY_SPAWN_EGG,
    STRIDER_SPAWN_EGG,
    TADPOLE_SPAWN_EGG,
    TRADER_LLAMA_SPAWN_EGG,
    TROPICAL_FISH_SPAWN_EGG,
    TURTLE_SPAWN_EGG,
    VEX_SPAWN_EGG,
    VILLAGER_SPAWN_EGG,
    VINDICATOR_SPAWN_EGG,
    WANDERING_TRADER_SPAWN_EGG,
    WARDEN_SPAWN_EGG,
    WITCH_SPAWN_EGG,
    WITHER_SPAWN_EGG,
    WITHER_SKELETON_SPAWN_EGG,
    WOLF_SPAWN_EGG,
    ZOGLIN_SPAWN_EGG,
    ZOMBIE_SPAWN_EGG,
    ZOMBIE_HORSE_SPAWN_EGG,
    ZOMBIE_VILLAGER_SPAWN_EGG,
    ZOMBIFIED_PIGLIN_SPAWN_EGG,
    EXPERIENCE_BOTTLE,
    FIRE_CHARGE,
    WIND_CHARGE,
    WRITABLE_BOOK,
    WRITTEN_BOOK,
    MACE,
    ITEM_FRAME,
    GLOW_ITEM_FRAME,
    FLOWER_POT,
    CARROT,
    POTATO,
    BAKED_POTATO,
    POISONOUS_POTATO,
    MAP,
    GOLDEN_CARROT,
    SKELETON_SKULL,
    WITHER_SKELETON_SKULL,
    PLAYER_HEAD,
    ZOMBIE_HEAD,
    CREEPER_HEAD,
    DRAGON_HEAD,
    PIGLIN_HEAD,
    NETHER_STAR,
    PUMPKIN_PIE,
    FIREWORK_ROCKET,
    FIREWORK_STAR,
    ENCHANTED_BOOK,
    NETHER_BRICK,
    PRISMARINE_SHARD,
    PRISMARINE_CRYSTALS,
    RABBIT,
    COOKED_RABBIT,
    RABBIT_STEW,
    RABBIT_FOOT,
    RABBIT_HIDE,
    ARMOR_STAND,
    IRON_HORSE_ARMOR,
    GOLDEN_HORSE_ARMOR,
    DIAMOND_HORSE_ARMOR,
    LEATHER_HORSE_ARMOR,
    LEAD,
    NAME_TAG,
    COMMAND_BLOCK_MINECART,
    MUTTON,
    COOKED_MUTTON,
    WHITE_BANNER,
    ORANGE_BANNER,
    MAGENTA_BANNER,
    LIGHT_BLUE_BANNER,
    YELLOW_BANNER,
    LIME_BANNER,
    PINK_BANNER,
    GRAY_BANNER,
    LIGHT_GRAY_BANNER,
    CYAN_BANNER,
    PURPLE_BANNER,
    BLUE_BANNER,
    BROWN_BANNER,
    GREEN_BANNER,
    RED_BANNER,
    BLACK_BANNER,
    END_CRYSTAL,
    CHORUS_FRUIT,
    POPPED_CHORUS_FRUIT,
    TORCHFLOWER_SEEDS,
    PITCHER_POD,
    BEETROOT,
    BEETROOT_SEEDS,
    BEETROOT_SOUP,
    DRAGON_BREATH,
    SPLASH_POTION,
    SPECTRAL_ARROW,
    TIPPED_ARROW,
    LINGERING_POTION,
    SHIELD,
    TOTEM_OF_UNDYING,
    SHULKER_SHELL,
    IRON_NUGGET,
    KNOWLEDGE_BOOK,
    DEBUG_STICK,
    MUSIC_DISC_13,
    MUSIC_DISC_CAT,
    MUSIC_DISC_BLOCKS,
    MUSIC_DISC_CHIRP,
    MUSIC_DISC_CREATOR,
    MUSIC_DISC_CREATOR_MUSIC_BOX,
    MUSIC_DISC_FAR,
    MUSIC_DISC_MALL,
    MUSIC_DISC_MELLOHI,
    MUSIC_DISC_STAL,
    MUSIC_DISC_STRAD,
    MUSIC_DISC_WARD,
    MUSIC_DISC_11,
    MUSIC_DISC_WAIT,
    MUSIC_DISC_OTHERSIDE,
    MUSIC_DISC_RELIC,
    MUSIC_DISC_5,
    MUSIC_DISC_PIGSTEP,
    MUSIC_DISC_PRECIPICE,
    DISC_FRAGMENT_5,
    TRIDENT,
    PHANTOM_MEMBRANE,
    NAUTILUS_SHELL,
    HEART_OF_THE_SEA,
    CROSSBOW,
    SUSPICIOUS_STEW,
    LOOM,
    FLOWER_BANNER_PATTERN,
    CREEPER_BANNER_PATTERN,
    SKULL_BANNER_PATTERN,
    MOJANG_BANNER_PATTERN,
    GLOBE_BANNER_PATTERN,
    PIGLIN_BANNER_PATTERN,
    FLOW_BANNER_PATTERN,
    GUSTER_BANNER_PATTERN,
    GOAT_HORN,
    COMPOSTER,
    BARREL,
    SMOKER,
    BLAST_FURNACE,
    CARTOGRAPHY_TABLE,
    FLETCHING_TABLE,
    GRINDSTONE,
    SMITHING_TABLE,
    STONECUTTER,
    BELL,
    LANTERN,
    SOUL_LANTERN,
    SWEET_BERRIES,
    GLOW_BERRIES,
    CAMPFIRE,
    SOUL_CAMPFIRE,
    SHROOMLIGHT,
    HONEYCOMB,
    BEE_NEST,
    BEEHIVE,
    HONEY_BOTTLE,
    HONEYCOMB_BLOCK,
    LODESTONE,
    CRYING_OBSIDIAN,
    BLACKSTONE,
    BLACKSTONE_SLAB,
    BLACKSTONE_STAIRS,
    GILDED_BLACKSTONE,
    POLISHED_BLACKSTONE,
    POLISHED_BLACKSTONE_SLAB,
    POLISHED_BLACKSTONE_STAIRS,
    CHISELED_POLISHED_BLACKSTONE,
    POLISHED_BLACKSTONE_BRICKS,
    POLISHED_BLACKSTONE_BRICK_SLAB,
    POLISHED_BLACKSTONE_BRICK_STAIRS,
    CRACKED_POLISHED_BLACKSTONE_BRICKS,
    RESPAWN_ANCHOR,
    CANDLE,
    WHITE_CANDLE,
    ORANGE_CANDLE,
    MAGENTA_CANDLE,
    LIGHT_BLUE_CANDLE,
    YELLOW_CANDLE,
    LIME_CANDLE,
    PINK_CANDLE,
    GRAY_CANDLE,
    LIGHT_GRAY_CANDLE,
    CYAN_CANDLE,
    PURPLE_CANDLE,
    BLUE_CANDLE,
    BROWN_CANDLE,
    GREEN_CANDLE,
    RED_CANDLE,
    BLACK_CANDLE,
    SMALL_AMETHYST_BUD,
    MEDIUM_AMETHYST_BUD,
    LARGE_AMETHYST_BUD,
    AMETHYST_CLUSTER,
    POINTED_DRIPSTONE,
    OCHRE_FROGLIGHT,
    VERDANT_FROGLIGHT,
    PEARLESCENT_FROGLIGHT,
    FROGSPAWN,
    ECHO_SHARD,
    BRUSH,
    NETHERITE_UPGRADE_SMITHING_TEMPLATE,
    SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE,
    DUNE_ARMOR_TRIM_SMITHING_TEMPLATE,
    COAST_ARMOR_TRIM_SMITHING_TEMPLATE,
    WILD_ARMOR_TRIM_SMITHING_TEMPLATE,
    WARD_ARMOR_TRIM_SMITHING_TEMPLATE,
    EYE_ARMOR_TRIM_SMITHING_TEMPLATE,
    VEX_ARMOR_TRIM_SMITHING_TEMPLATE,
    TIDE_ARMOR_TRIM_SMITHING_TEMPLATE,
    SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE,
    RIB_ARMOR_TRIM_SMITHING_TEMPLATE,
    SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE,
    WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE,
    SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE,
    SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE,
    RAISER_ARMOR_TRIM_SMITHING_TEMPLATE,
    HOST_ARMOR_TRIM_SMITHING_TEMPLATE,
    FLOW_ARMOR_TRIM_SMITHING_TEMPLATE,
    BOLT_ARMOR_TRIM_SMITHING_TEMPLATE,
    ANGLER_POTTERY_SHERD,
    ARCHER_POTTERY_SHERD,
    ARMS_UP_POTTERY_SHERD,
    BLADE_POTTERY_SHERD,
    BREWER_POTTERY_SHERD,
    BURN_POTTERY_SHERD,
    DANGER_POTTERY_SHERD,
    EXPLORER_POTTERY_SHERD,
    FLOW_POTTERY_SHERD,
    FRIEND_POTTERY_SHERD,
    GUSTER_POTTERY_SHERD,
    HEART_POTTERY_SHERD,
    HEARTBREAK_POTTERY_SHERD,
    HOWL_POTTERY_SHERD,
    MINER_POTTERY_SHERD,
    MOURNER_POTTERY_SHERD,
    PLENTY_POTTERY_SHERD,
    PRIZE_POTTERY_SHERD,
    SCRAPE_POTTERY_SHERD,
    SHEAF_POTTERY_SHERD,
    SHELTER_POTTERY_SHERD,
    SKULL_POTTERY_SHERD,
    SNORT_POTTERY_SHERD,
    COPPER_GRATE,
    EXPOSED_COPPER_GRATE,
    WEATHERED_COPPER_GRATE,
    OXIDIZED_COPPER_GRATE,
    WAXED_COPPER_GRATE,
    WAXED_EXPOSED_COPPER_GRATE,
    WAXED_WEATHERED_COPPER_GRATE,
    WAXED_OXIDIZED_COPPER_GRATE,
    COPPER_BULB,
    EXPOSED_COPPER_BULB,
    WEATHERED_COPPER_BULB,
    OXIDIZED_COPPER_BULB,
    WAXED_COPPER_BULB,
    WAXED_EXPOSED_COPPER_BULB,
    WAXED_WEATHERED_COPPER_BULB,
    WAXED_OXIDIZED_COPPER_BULB,
    TRIAL_SPAWNER,
    TRIAL_KEY,
    OMINOUS_TRIAL_KEY,
    VAULT,
    OMINOUS_BOTTLE,
    BREEZE_ROD;
    @VESCallIgnore
    public Item $_getItem() {
        return Registries.ITEM.get(Identifier.ofVanilla(this.name().toLowerCase()));
    }
}
