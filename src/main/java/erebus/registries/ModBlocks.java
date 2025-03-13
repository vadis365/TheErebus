package erebus.registries;

import erebus.Erebus;
import erebus.block.ConnectedTextureBlock;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    // Deferred Register to hold all our Blocks
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Erebus.MODID);

    // Our Blocks

    public static final DeferredBlock<Block> ALGAE = BLOCKS.registerBlock(
            "algae",
            WaterlilyBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .instabreak()
                    .sound(SoundType.LILY_PAD)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
    );
    public static final DeferredBlock<Block> AMBER = BLOCKS.registerBlock(
            "amber",
            TransparentBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)
                    .strength(1.5F)
                    .noOcclusion()
                    .isViewBlocking((blockState, blockGetter, blockPos) -> false)
                    .sound(SoundType.GLASS)
                    .mapColor(MapColor.GOLD)
    );
    public static final DeferredBlock<Block> AMBER_BRICKS = BLOCKS.registerBlock(
            "amber_bricks",
            TransparentBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)
                    .strength(2.5F)
                    .noOcclusion()
                    .isViewBlocking((blockState, blockGetter, blockPos) -> false)
                    .sound(SoundType.GLASS)
                    .mapColor(MapColor.GOLD)
    );
    public static final DeferredBlock<Block> AMBER_GLASS = BLOCKS.registerBlock(
            "amber_glass",
            ConnectedTextureBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)
                    .strength(2.5F)
                    .sound(SoundType.GLASS)
                    .mapColor(MapColor.GOLD)
    );
    public static final DeferredBlock<Block> AMBER_DOOR = BLOCKS.registerSimpleBlock(
            "door_amber",
            BlockBehaviour.Properties.of()
                    .strength(2.5F)
                    .sound(SoundType.GLASS)
                    .mapColor(MapColor.GOLD)
    );

    // MARK: Umberstone
    public static final DeferredBlock<Block> UMBERSTONE = BLOCKS.registerSimpleBlock("umberstone", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> UMBERGRAVEL = BLOCKS.registerSimpleBlock("umbergravel", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> UMBERPAVER = BLOCKS.registerSimpleBlock("umberpaver", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<RotatedPillarBlock> UMBERSTONE_PILLAR = BLOCKS.register(
            "umberstone_pillar",
            () -> new RotatedPillarBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
            )
    );

    // WIP

    // STONE STAIRS
    public static final DeferredBlock<Block> STAIRS_UMBERSTONE = BLOCKS.registerSimpleBlock("stairs_umberstone", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PLANKS = BLOCKS.registerSimpleBlock("planks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PORTAL = BLOCKS.registerSimpleBlock("portal", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GAEAN_KEYSTONE = BLOCKS.registerSimpleBlock("gaean_keystone", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_UMBERPAVER_MOSSY = BLOCKS.registerSimpleBlock("slab_umberpaver_mossy", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_UMBERPAVER_WEBBED = BLOCKS.registerSimpleBlock("slab_umberpaver_webbed", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK = BLOCKS.registerSimpleBlock("petrified_wood_rock", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_2 = BLOCKS.registerSimpleBlock("petrified_wood_rock_2", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_3 = BLOCKS.registerSimpleBlock("petrified_wood_rock_3", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_4 = BLOCKS.registerSimpleBlock("petrified_wood_rock_4", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_5 = BLOCKS.registerSimpleBlock("petrified_wood_rock_5", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_6 = BLOCKS.registerSimpleBlock("petrified_wood_rock_6", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_BARK_RED = BLOCKS.registerSimpleBlock("petrified_bark_red", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_BARK_BROWN = BLOCKS.registerSimpleBlock("petrified_bark_brown", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUST_LAYER = BLOCKS.registerSimpleBlock("dust_layer", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUST = BLOCKS.registerSimpleBlock("dust", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUNG = BLOCKS.registerSimpleBlock("dung", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_IRON = BLOCKS.registerSimpleBlock("ore_iron", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_GOLD = BLOCKS.registerSimpleBlock("ore_gold", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_COAL = BLOCKS.registerSimpleBlock("ore_coal", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_DIAMOND = BLOCKS.registerSimpleBlock("ore_diamond", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_EMERALD = BLOCKS.registerSimpleBlock("ore_emerald", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_LAPIS = BLOCKS.registerSimpleBlock("ore_lapis", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_QUARTZ = BLOCKS.registerSimpleBlock("ore_quartz", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_PETRIFIED_QUARTZ = BLOCKS.registerSimpleBlock("ore_petrified_quartz", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_COPPER = BLOCKS.registerSimpleBlock("ore_copper", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_SILVER = BLOCKS.registerSimpleBlock("ore_silver", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_TIN = BLOCKS.registerSimpleBlock("ore_tin", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_LEAD = BLOCKS.registerSimpleBlock("ore_lead", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_ALUMINUM = BLOCKS.registerSimpleBlock("ore_aluminum", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_JADE = BLOCKS.registerSimpleBlock("ore_jade", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_ENCRUSTED_DIAMOND = BLOCKS.registerSimpleBlock("ore_encrusted_diamond", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_FOSSIL = BLOCKS.registerSimpleBlock("ore_fossil", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_GNEISS = BLOCKS.registerSimpleBlock("ore_gneiss", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_PETRIFIED_WOOD = BLOCKS.registerSimpleBlock("ore_petrified_wood", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_TEMPLE = BLOCKS.registerSimpleBlock("ore_temple", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> JADE_BLOCK = BLOCKS.registerSimpleBlock("jade_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PRESERVED_BLOCK = BLOCKS.registerSimpleBlock("preserved_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> MUD = BLOCKS.registerSimpleBlock("mud", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> QUICK_SAND = BLOCKS.registerSimpleBlock("quick_sand", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> RED_GEM = BLOCKS.registerSimpleBlock("red_gem", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SWAMP_VENT = BLOCKS.registerSimpleBlock("swamp_vent", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GHOST_SAND = BLOCKS.registerSimpleBlock("ghost_sand", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> CROP_TURNIP = BLOCKS.registerSimpleBlock("crop_turnip", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> CROP_CABBAGE = BLOCKS.registerSimpleBlock("crop_cabbage", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> CROP_MANDRAKE = BLOCKS.registerSimpleBlock("crop_mandrake", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> JADE_BERRY_BUSH = BLOCKS.registerSimpleBlock("jade_berry_bush", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> HEART_BERRY_BUSH = BLOCKS.registerSimpleBlock("heart_berry_bush", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SWAMP_BERRY_BUSH = BLOCKS.registerSimpleBlock("swap_berry_bush", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DARK_FRUIT_VINE = BLOCKS.registerSimpleBlock("dark_fruit_vine", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PRICKLY_PEAR = BLOCKS.registerSimpleBlock("prickly_pear", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GIANT_FLOWER = BLOCKS.registerSimpleBlock("giant_flower", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GIANT_FLOWER_STIGMA = BLOCKS.registerSimpleBlock("giant_flower_stigma", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PLANTED_FLOWER = BLOCKS.registerSimpleBlock("planted_flower", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SMALL_PLANT = BLOCKS.registerSimpleBlock("small_plant", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> THORNS = BLOCKS.registerSimpleBlock("thorns", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> HANGING_WEB = BLOCKS.registerSimpleBlock("hanging_web", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DOUBLE_PLANT = BLOCKS.registerSimpleBlock("double_plant", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WALL_PLANTS = BLOCKS.registerSimpleBlock("wall_plants", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WALL_PLANTS_CULTIVATED = BLOCKS.registerSimpleBlock("wall_plants_cultivated", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> HONEY_TREAT = BLOCKS.registerSimpleBlock("honey_treat", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DARK_CAPPED_MUSHROOM = BLOCKS.registerSimpleBlock("dark_capped_mushroom", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SARCASTIC_CZECH_MUSHROOM = BLOCKS.registerSimpleBlock("sarcastic_czech_mushroom", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GRANDMAS_SHOES_MUSHROOM = BLOCKS.registerSimpleBlock("grandmas_shoes_mushroom", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUTCH_CAP_MUSHROOM = BLOCKS.registerSimpleBlock("dutch_cap_mushroom", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> KAIZERS_FINGERS_MUSHROOM = BLOCKS.registerSimpleBlock("kaizers_fingers_mushroom", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DARK_CAPPED_MUSHROOM_BLOCK = BLOCKS.registerSimpleBlock("dark_capped_mushroom_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SARCASTIC_CZECH_MUSHROOM_BLOCK = BLOCKS.registerSimpleBlock("sarcastic_czech_mushroom_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GRANDMAS_SHOES_MUSHROOM_BLOCK = BLOCKS.registerSimpleBlock("grandmas_shoes_mushroom_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUTCH_CAP_MUSHROOM_BLOCK = BLOCKS.registerSimpleBlock("dutch_cap_mushroom_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> KAIZERS_FINGERS_MUSHROOM_BLOCK = BLOCKS.registerSimpleBlock("kaizers_fingers_mushroom_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GLOWSHROOM = BLOCKS.registerSimpleBlock("glowshroom", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GLOWSHROOM_STALK_MAIN = BLOCKS.registerSimpleBlock("glowshroom_stalk_main", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WITHER_WEB = BLOCKS.registerSimpleBlock("wither_web", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SILK = BLOCKS.registerSimpleBlock("silk", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> MIR_BRICK = BLOCKS.registerSimpleBlock("mir_brick", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_MIR_BRICKS = BLOCKS.registerSimpleBlock("slab_mir_bricks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PLANKS_PETRIFIED_WOOD = BLOCKS.registerSimpleBlock("planks_petrified_wood", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_PLANKS_PETRIFIED_WOOD = BLOCKS.registerSimpleBlock("slab_petrified_wood", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DOOR_PETRIFIED_WOOD = BLOCKS.registerSimpleBlock("door_petrified_wood", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> REIN_EXO = BLOCKS.registerSimpleBlock("rein_exo", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> MUD_BRICK = BLOCKS.registerSimpleBlock("mud_brick", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_MUD_BRICKS = BLOCKS.registerSimpleBlock("slab_mud_bricks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_BRICK = BLOCKS.registerSimpleBlock("temple_brick", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_PILLAR = BLOCKS.registerSimpleBlock("temple_pillar", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_TILE = BLOCKS.registerSimpleBlock("temple_tile", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> VOLCANIC_ROCK = BLOCKS.registerSimpleBlock("volcanic_rock", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GNEISS = BLOCKS.registerSimpleBlock("gneiss", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GNEISS_VENT = BLOCKS.registerSimpleBlock("gneiss_vent", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> HOLLOW_LOG = BLOCKS.registerSimpleBlock("hollow_log", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> LOG_BALSAM_RESINLESS = BLOCKS.registerSimpleBlock("log_balsam_resinless", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    /**
     * Utility
     */
    public static final DeferredBlock<Block> UMBER_FURNACE = BLOCKS.registerSimpleBlock("umber_furnace", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> UMBER_FURNACE_ACTIVE = BLOCKS.registerSimpleBlock("umber_furnace_active", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_CRAFTING_TABLE = BLOCKS.registerSimpleBlock("petrified_crafting_table", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_CRATE = BLOCKS.registerSimpleBlock("bamboo_crate", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_BRIDGE = BLOCKS.registerSimpleBlock("bamboo_bridge", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_LADDER = BLOCKS.registerSimpleBlock("bamboo_ladder", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_NERD_POLE = BLOCKS.registerSimpleBlock("bamboo_nerd_pole", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_EXTENDER = BLOCKS.registerSimpleBlock("bamboo_extender", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_TORCH = BLOCKS.registerSimpleBlock("bamboo_torch", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_PIPE = BLOCKS.registerSimpleBlock("bamboo_pipe", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_PIPE_EXTRACT = BLOCKS.registerSimpleBlock("bamboo_pipe_extract", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_PIPE_EXTRACT_ACTIVE = BLOCKS.registerSimpleBlock("bamboo_pipe_extract_active", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> LIQUIFIER = BLOCKS.registerSimpleBlock("liquifier", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SILO_ROOF = BLOCKS.registerSimpleBlock("silo_roof", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SILO_TANK = BLOCKS.registerSimpleBlock("silo_tank", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SILO_SUPPORTS = BLOCKS.registerSimpleBlock("silo_supports", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> HONEY_COMB = BLOCKS.registerSimpleBlock("honey_comb", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> UMBER_GOLEM_STATUE = BLOCKS.registerSimpleBlock("umber_golem_statue", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> INSECT_REPELLENT = BLOCKS.registerSimpleBlock("insect_repellent", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_CHEST = BLOCKS.registerSimpleBlock("petrified_wood_chest", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GLOWING_JAR = BLOCKS.registerSimpleBlock("glowing_jar", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FLUID_JAR = BLOCKS.registerSimpleBlock("fluid_jar", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> COMPOSTER = BLOCKS.registerSimpleBlock("composter", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SMOOTHIE_MAKER = BLOCKS.registerSimpleBlock("smoothie_maker", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> UMBERSTONE_BUTTON = BLOCKS.registerSimpleBlock("umberstone_button", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GLOW_GEM_ACTIVE = BLOCKS.registerSimpleBlock("glow_gem_active", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GLOW_GEM_INACTIVE = BLOCKS.registerSimpleBlock("glow_gem_inactive", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> MUCUS_BOMB = BLOCKS.registerSimpleBlock("mucus_bomb", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    /**
     * Dungeons
     */
    public static final DeferredBlock<Block> SPIDER_SPAWNER = BLOCKS.registerSimpleBlock("spider_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> JUMPING_SPIDER_SPAWNER = BLOCKS.registerSimpleBlock("jumping_spider_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TARANTULA_SPAWNER = BLOCKS.registerSimpleBlock("tarantula_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WASP_SPAWNER = BLOCKS.registerSimpleBlock("wasp_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ANTLION_SPAWNER = BLOCKS.registerSimpleBlock("antlion_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DRAGON_FLY_SPAWNER = BLOCKS.registerSimpleBlock("dragon_fly_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ZOMBIE_ANT_SPAWNER = BLOCKS.registerSimpleBlock("zombie_ant_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ZOMBIE_ANT_SOLDIER_SPAWNER = BLOCKS.registerSimpleBlock("zombie_ant_soldier_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> MAGMA_CRAWLER_SPAWNER = BLOCKS.registerSimpleBlock("magma_crawler_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> LOCUST_SPAWNER = BLOCKS.registerSimpleBlock("locust_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GIANT_LILY_PAD = BLOCKS.registerSimpleBlock("giant_lily_pad", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WASP_NEST = BLOCKS.registerSimpleBlock("wasp_nest", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STAIRS_WASP_NEST = BLOCKS.registerSimpleBlock("stairs_wasp_nest", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ANTLION_EGG = BLOCKS.registerSimpleBlock("antlion_egg", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TARANTULA_EGG = BLOCKS.registerSimpleBlock("tarantula_egg", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> CAPSTONE = BLOCKS.registerSimpleBlock("capstone", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ANT_HILL_BLOCK = BLOCKS.registerSimpleBlock("ant_hill_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FORCE_FIELD = BLOCKS.registerSimpleBlock("force_field", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FORCE_LOCK = BLOCKS.registerSimpleBlock("force_lock", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING = BLOCKS.registerSimpleBlock("temple_brick_unbreaking", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_TELEPORTER = BLOCKS.registerSimpleBlock("temple_teleporter", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BLOCK_OF_BONES = BLOCKS.registerSimpleBlock("block_of_bones", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUNG_SPAWNER_BOT_FLY = BLOCKS.registerSimpleBlock("dung_spawner_bot_fly", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUNG_SPAWNER_FLY = BLOCKS.registerSimpleBlock("dung_spawner_fly", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    /**
     * Walls
     */
    public static final DeferredBlock<Block> WALL_UMBERSTONE = BLOCKS.registerSimpleBlock("wall_umberstone", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WALL_UMBERCOBBLE = BLOCKS.registerSimpleBlock("wall_umbercobble", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WALL_UMBERCOBBLE_MOSSY = BLOCKS.registerSimpleBlock("wall_umbercobble_mossy", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WALL_UMBERCOBBLE_WEBBED = BLOCKS.registerSimpleBlock("wall_umbercobble_webbed", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WALL_UMBERSTONE_BRICKS = BLOCKS.registerSimpleBlock("wall_umberstone_bricks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WALL_UMBERTILE_SMOOTH = BLOCKS.registerSimpleBlock("wall_umbertile_smooth", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WALL_UMBERTILE_SMOOTH_SMALL = BLOCKS.registerSimpleBlock("wall_umbertile_smooth_small", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WALL_UMBERPAVER = BLOCKS.registerSimpleBlock("wall_umberpaver", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WALL_UMBERPAVER_MOSSY = BLOCKS.registerSimpleBlock("wall_umberpaver_mossy", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WALL_UMBERPAVER_WEBBED = BLOCKS.registerSimpleBlock("wall_umberpaver_webbed", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WALL_AMBER = BLOCKS.registerSimpleBlock("wall_amber", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WALL_AMBER_BRICKS = BLOCKS.registerSimpleBlock("wall_amber_bricks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    // SLABS
    public static final DeferredBlock<Block> SLAB_UMBERSTONE = BLOCKS.registerSimpleBlock("slab_umberstone", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_UMBERCOBBLE = BLOCKS.registerSimpleBlock("slab_umbercobble", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_UMBERCOBBLE_MOSSY = BLOCKS.registerSimpleBlock("slab_umbercobble_mossy", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_UMBERCOBBLE_WEBBED = BLOCKS.registerSimpleBlock("slab_umbercobble_webbed", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_UMBERSTONE_BRICKS = BLOCKS.registerSimpleBlock("slab_umberstone_bricks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_UMBERTILE_SMOOTH = BLOCKS.registerSimpleBlock("slab_umberstone_smooth", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_UMBERTILE_SMOOTH_SMALL = BLOCKS.registerSimpleBlock("slab_umberstone_smooth_small", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_UMBERPAVER = BLOCKS.registerSimpleBlock("slab_umberpaver", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STAIRS_UMBERCOBBLE = BLOCKS.registerSimpleBlock("stairs_umbercobble", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_AMBER = BLOCKS.registerSimpleBlock("slab_amber", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_AMBER_BRICKS = BLOCKS.registerSimpleBlock("slab_amber_bricks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    // Stairs
    public static final DeferredBlock<Block> STAIRS_UMBERCOBBLE_MOSSY = BLOCKS.registerSimpleBlock("stairs_umbercobble_mossy", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STAIRS_UMBERCOBBLE_WEBBED = BLOCKS.registerSimpleBlock("stairs_umbercobble_webbed", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STAIRS_UMBERSTONE_BRICKS = BLOCKS.registerSimpleBlock("stairs_umberstone_bricks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STAIRS_UMBERTILE_SMOOTH = BLOCKS.registerSimpleBlock("stairs_umberstone_smooth", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STAIRS_UMBERTILE_SMOOTH_SMALL = BLOCKS.registerSimpleBlock("stairs_umberstone_smooth_small", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STAIRS_UMBERPAVER = BLOCKS.registerSimpleBlock("stairs_umberpaver", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STAIRS_UMBERPAVER_MOSSY = BLOCKS.registerSimpleBlock("stairs_umberpaver_mossy", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STAIRS_UMBERPAVER_WEBBED = BLOCKS.registerSimpleBlock("stairs_umberpaver_webbed", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STAIRS_AMBER = BLOCKS.registerSimpleBlock("stairs_amber", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STAIRS_AMBER_BRICKS = BLOCKS.registerSimpleBlock("stairs_amber_bricks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STAIRS_PETRIFIED_WOOD = BLOCKS.registerSimpleBlock("stairs_petrified_wood", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STAIRS_MUD_BRICKS = BLOCKS.registerSimpleBlock("stairs_mud_bricks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STAIRS_MIR_BRICKS = BLOCKS.registerSimpleBlock("stairs_mir_bricks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
