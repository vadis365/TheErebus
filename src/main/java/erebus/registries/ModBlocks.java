package erebus.registries;

import erebus.Erebus;
import erebus.block.ConnectedTextureBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {

    // Deferred Register to hold all our Blocks
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Erebus.MODID);

    // Our Blocks

    public static final DeferredBlock<Block> ALGAE = registerBlock(
            "algae",
            () -> new WaterlilyBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .instabreak()
                    .sound(SoundType.LILY_PAD)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
            )
    );
    public static final DeferredBlock<Block> AMBER = registerBlock(
            "amber",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)
                    .strength(1.5F)
                    .noOcclusion()
                    .isViewBlocking((blockState, blockGetter, blockPos) -> false)
                    .sound(SoundType.GLASS)
                    .mapColor(MapColor.GOLD)
            )
    );
    public static final DeferredBlock<Block> AMBER_BRICKS = registerBlock(
            "amber_bricks",
            () -> new TransparentBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)
                    .strength(2.5F)
                    .noOcclusion()
                    .isViewBlocking((blockState, blockGetter, blockPos) -> false)
                    .sound(SoundType.GLASS)
                    .mapColor(MapColor.GOLD)
            )
    );
    public static final DeferredBlock<Block> AMBER_GLASS = registerBlock(
            "amber_glass",
            () -> new ConnectedTextureBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)
                    .strength(2.5F)
                    .sound(SoundType.GLASS)
                    .mapColor(MapColor.GOLD)
            )
    );
    public static final DeferredBlock<Block> AMBER_DOOR = registerBlock(
            "door_amber",
            () -> new DoorBlock(
                    BlockSetType.STONE,
                    BlockBehaviour.Properties.of()
                            .strength(2.5F)
                            .sound(SoundType.GLASS)
                            .mapColor(MapColor.GOLD)
            )
    );

    // MARK: Umberstone

    public static final DeferredBlock<Block> UMBERSTONE = registerSimpleBlock("umberstone", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> UMBERGRAVEL = registerSimpleBlock("umbergravel", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> UMBERPAVER = registerSimpleBlock("umberpaver", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<RotatedPillarBlock> UMBERSTONE_PILLAR = registerBlock(
            "umberstone_pillar",
            () -> new RotatedPillarBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
            )
    );

    // MARK: Walls

    public static final DeferredBlock<Block> WALL_UMBERSTONE = registerBlock(
            "wall_umberstone",
            () -> new WallBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
            )
    );

    public static final DeferredBlock<Block> WALL_UMBERCOBBLE = registerBlock(
            "wall_umbercobble",
            () -> new WallBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            )
    );

    public static final DeferredBlock<Block> WALL_UMBERCOBBLE_MOSSY = registerBlock(
            "wall_umbercobble_mossy",
            () -> new WallBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            )
    );

    public static final DeferredBlock<Block> WALL_UMBERCOBBLE_WEBBED = registerBlock(
            "wall_umbercobble_webbed",
            () -> new WallBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            )
    );

    public static final DeferredBlock<Block> WALL_UMBERSTONE_BRICKS = registerBlock(
            "wall_umberstone_bricks",
            () -> new WallBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            )
    );

    public static final DeferredBlock<Block> WALL_UMBERTILE_SMOOTH = registerBlock(
            "wall_umbertile_smooth",
            () -> new WallBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            )
    );

    public static final DeferredBlock<Block> WALL_UMBERTILE_SMOOTH_SMALL = registerBlock(
            "wall_umbertile_smooth_small",
            () -> new WallBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            )
    );

    public static final DeferredBlock<Block> WALL_UMBERPAVER = registerBlock(
            "wall_umberpaver",
            () -> new WallBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            )
    );

    public static final DeferredBlock<Block> WALL_UMBERPAVER_MOSSY = registerBlock(
            "wall_umberpaver_mossy",
            () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            )
    );

    public static final DeferredBlock<Block> WALL_UMBERPAVER_WEBBED = registerBlock(
            "wall_umberpaver_webbed",
            () -> new WallBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            )
    );

    public static final DeferredBlock<Block> WALL_AMBER = registerBlock(
            "wall_amber",
            () -> new WallBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            )
    );

    public static final DeferredBlock<Block> WALL_AMBER_BRICKS = registerBlock(
            "wall_amber_bricks",
            () -> new WallBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            )
    );

    // MARK: Slabs

    public static final DeferredBlock<Block> SLAB_UMBERSTONE = registerBlock(
            "slab_umberstone",
            () -> new SlabBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            )
    );


    public static final DeferredBlock<Block> SLAB_UMBERCOBBLE = BLOCKS.registerBlock("slab_umbercobble", SlabBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_UMBERCOBBLE_MOSSY = BLOCKS.registerBlock("slab_umbercobble_mossy", SlabBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_UMBERCOBBLE_WEBBED = BLOCKS.registerBlock("slab_umbercobble_webbed", SlabBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_UMBERSTONE_BRICKS = BLOCKS.registerBlock("slab_umberstone_bricks", SlabBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_UMBERTILE_SMOOTH = BLOCKS.registerBlock("slab_umberstone_smooth", SlabBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_UMBERTILE_SMOOTH_SMALL = BLOCKS.registerBlock("slab_umberstone_smooth_small", SlabBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_UMBERPAVER = BLOCKS.registerBlock("slab_umberpaver", SlabBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_AMBER = BLOCKS.registerBlock("slab_amber", SlabBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_AMBER_BRICKS = BLOCKS.registerBlock("slab_amber_bricks", SlabBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_UMBERPAVER_MOSSY = BLOCKS.registerBlock("slab_umberpaver_mossy", SlabBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_UMBERPAVER_WEBBED = BLOCKS.registerBlock("slab_umberpaver_webbed", SlabBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_PLANKS_PETRIFIED_WOOD = BLOCKS.registerBlock("slab_petrified_wood", SlabBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    // MARK: Stairs

    public static final DeferredBlock<Block> STAIRS_UMBERCOBBLE_MOSSY = registerBlock(
            "stairs_umbercobble_mossy",
            () -> new StairBlock(
                    UMBERSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2.0F)
            )
    );

    public static final DeferredBlock<Block> STAIRS_UMBERCOBBLE_WEBBED = registerBlock(
            "stairs_umbercobble_webbed",
            () -> new StairBlock(
                    UMBERSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2.0F)
            )
    );

    public static final DeferredBlock<Block> STAIRS_UMBERSTONE_BRICKS = registerBlock(
            "stairs_umberstone_bricks",
            () -> new StairBlock(
                    UMBERSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2.0F)
            )
    );

    public static final DeferredBlock<Block> STAIRS_UMBERTILE_SMOOTH = registerBlock(
            "stairs_umberstone_smooth",
            () -> new StairBlock(
                    UMBERSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2.0F)
            )
    );

    public static final DeferredBlock<Block> STAIRS_UMBERTILE_SMOOTH_SMALL = registerBlock(
            "stairs_umberstone_smooth_small",
            () -> new StairBlock(
                    UMBERSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2.0F)
            )
    );

    public static final DeferredBlock<Block> STAIRS_UMBERPAVER = registerBlock(
            "stairs_umberpaver",
            () -> new StairBlock(
                    UMBERSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2.0F)
            )
    );

    public static final DeferredBlock<Block> STAIRS_UMBERPAVER_MOSSY = registerBlock(
            "stairs_umberpaver_mossy",
            () -> new StairBlock(
                    UMBERSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2.0F)
            )
    );

    public static final DeferredBlock<Block> STAIRS_UMBERPAVER_WEBBED = registerBlock(
            "stairs_umberpaver_webbed",
            () -> new StairBlock(
                    UMBERSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2.0F)
            )
    );

    public static final DeferredBlock<Block> STAIRS_AMBER = registerBlock(
            "stairs_amber",
            () -> new StairBlock(
                    UMBERSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2.0F)
            )
    );

    public static final DeferredBlock<Block> STAIRS_AMBER_BRICKS = registerBlock(
            "stairs_amber_bricks",
            () -> new StairBlock(
                    UMBERSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2.0F)
            )
    );

    public static final DeferredBlock<Block> STAIRS_PETRIFIED_WOOD = registerBlock(
            "stairs_petrified_wood",
            () -> new StairBlock(
                    UMBERSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2.0F)
            )
    );

    public static final DeferredBlock<Block> STAIRS_MUD_BRICKS = registerBlock(
            "stairs_mud_bricks",
            () -> new StairBlock(
                    UMBERSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2.0F)
            )
    );

    public static final DeferredBlock<Block> STAIRS_MIR_BRICKS = registerBlock(
            "stairs_mir_bricks",
            () -> new StairBlock(
                    UMBERSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2.0F)
            )
    );

    public static final DeferredBlock<Block> STAIRS_UMBERSTONE = registerBlock(
            "stairs_umberstone",
            () -> new StairBlock(
                    UMBERSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2.0F)
            )
    );

    public static final DeferredBlock<Block> STAIRS_UMBERCOBBLE = registerBlock(
            "stairs_umbercobble",
            () -> new StairBlock(
                    UMBERSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2.0F)
            )
    );

    // WIP

    // STONE STAIRS
    public static final DeferredBlock<Block> PORTAL = registerSimpleBlock("portal", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GAEAN_KEYSTONE = registerSimpleBlock("gaean_keystone", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK = registerSimpleBlock("petrified_wood_rock", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_2 = registerSimpleBlock("petrified_wood_rock_2", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_3 = registerSimpleBlock("petrified_wood_rock_3", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_4 = registerSimpleBlock("petrified_wood_rock_4", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_5 = registerSimpleBlock("petrified_wood_rock_5", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_6 = registerSimpleBlock("petrified_wood_rock_6", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_BARK_RED = registerSimpleBlock("petrified_bark_red", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_BARK_BROWN = registerSimpleBlock("petrified_bark_brown", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUST_LAYER = registerSimpleBlock("dust_layer", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUST = registerSimpleBlock("dust", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUNG = registerSimpleBlock("dung", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_IRON = registerSimpleBlock("ore_iron", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_GOLD = registerSimpleBlock("ore_gold", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_COAL = registerSimpleBlock("ore_coal", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_DIAMOND = registerSimpleBlock("ore_diamond", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_EMERALD = registerSimpleBlock("ore_emerald", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_LAPIS = registerSimpleBlock("ore_lapis", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_QUARTZ = registerSimpleBlock("ore_quartz", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_PETRIFIED_QUARTZ = registerSimpleBlock("ore_petrified_quartz", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_COPPER = registerSimpleBlock("ore_copper", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_SILVER = registerSimpleBlock("ore_silver", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_TIN = registerSimpleBlock("ore_tin", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_LEAD = registerSimpleBlock("ore_lead", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_ALUMINUM = registerSimpleBlock("ore_aluminum", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_JADE = registerSimpleBlock("ore_jade", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_ENCRUSTED_DIAMOND = registerSimpleBlock("ore_encrusted_diamond", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_FOSSIL = registerSimpleBlock("ore_fossil", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_GNEISS = registerSimpleBlock("ore_gneiss", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_PETRIFIED_WOOD = registerSimpleBlock("ore_petrified_wood", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ORE_TEMPLE = registerSimpleBlock("ore_temple", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> JADE_BLOCK = registerSimpleBlock("jade_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PRESERVED_BLOCK = registerSimpleBlock("preserved_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> MUD = registerSimpleBlock("mud", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> QUICK_SAND = registerSimpleBlock("quick_sand", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> RED_GEM = registerSimpleBlock("red_gem", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SWAMP_VENT = registerSimpleBlock("swamp_vent", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GHOST_SAND = registerSimpleBlock("ghost_sand", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> CROP_TURNIP = registerSimpleBlock("crop_turnip", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> CROP_CABBAGE = registerSimpleBlock("crop_cabbage", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> CROP_MANDRAKE = registerSimpleBlock("crop_mandrake", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> JADE_BERRY_BUSH = registerSimpleBlock("jade_berry_bush", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> HEART_BERRY_BUSH = registerSimpleBlock("heart_berry_bush", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SWAMP_BERRY_BUSH = registerSimpleBlock("swap_berry_bush", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DARK_FRUIT_VINE = registerSimpleBlock("dark_fruit_vine", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PRICKLY_PEAR = registerSimpleBlock("prickly_pear", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GIANT_FLOWER = registerSimpleBlock("giant_flower", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GIANT_FLOWER_STIGMA = registerSimpleBlock("giant_flower_stigma", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PLANTED_FLOWER = registerSimpleBlock("planted_flower", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SMALL_PLANT = registerSimpleBlock("small_plant", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> THORNS = registerSimpleBlock("thorns", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> HANGING_WEB = registerSimpleBlock("hanging_web", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DOUBLE_PLANT = registerSimpleBlock("double_plant", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WALL_PLANTS = registerSimpleBlock("wall_plants", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WALL_PLANTS_CULTIVATED = registerSimpleBlock("wall_plants_cultivated", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> HONEY_TREAT = registerSimpleBlock("honey_treat", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DARK_CAPPED_MUSHROOM = registerSimpleBlock("dark_capped_mushroom", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SARCASTIC_CZECH_MUSHROOM = registerSimpleBlock("sarcastic_czech_mushroom", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GRANDMAS_SHOES_MUSHROOM = registerSimpleBlock("grandmas_shoes_mushroom", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUTCH_CAP_MUSHROOM = registerSimpleBlock("dutch_cap_mushroom", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> KAIZERS_FINGERS_MUSHROOM = registerSimpleBlock("kaizers_fingers_mushroom", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DARK_CAPPED_MUSHROOM_BLOCK = registerSimpleBlock("dark_capped_mushroom_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SARCASTIC_CZECH_MUSHROOM_BLOCK = registerSimpleBlock("sarcastic_czech_mushroom_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GRANDMAS_SHOES_MUSHROOM_BLOCK = registerSimpleBlock("grandmas_shoes_mushroom_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUTCH_CAP_MUSHROOM_BLOCK = registerSimpleBlock("dutch_cap_mushroom_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> KAIZERS_FINGERS_MUSHROOM_BLOCK = registerSimpleBlock("kaizers_fingers_mushroom_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GLOWSHROOM = registerSimpleBlock("glowshroom", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GLOWSHROOM_STALK_MAIN = registerSimpleBlock("glowshroom_stalk_main", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WITHER_WEB = registerSimpleBlock("wither_web", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SILK = registerSimpleBlock("silk", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> MIR_BRICK = registerSimpleBlock("mir_brick", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_MIR_BRICKS = registerSimpleBlock("slab_mir_bricks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PLANKS_PETRIFIED_WOOD = registerSimpleBlock("planks_petrified_wood", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DOOR_PETRIFIED_WOOD = registerSimpleBlock("door_petrified_wood", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> REIN_EXO = registerSimpleBlock("rein_exo", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> MUD_BRICK = registerSimpleBlock("mud_brick", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SLAB_MUD_BRICKS = registerSimpleBlock("slab_mud_bricks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_BRICK = registerSimpleBlock("temple_brick", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_PILLAR = registerSimpleBlock("temple_pillar", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_TILE = registerSimpleBlock("temple_tile", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> VOLCANIC_ROCK = registerSimpleBlock("volcanic_rock", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GNEISS = registerSimpleBlock("gneiss", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GNEISS_VENT = registerSimpleBlock("gneiss_vent", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> HOLLOW_LOG = registerSimpleBlock("hollow_log", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> LOG_BALSAM_RESINLESS = registerSimpleBlock("log_balsam_resinless", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    /**
     * Utility
     */
    public static final DeferredBlock<Block> UMBER_FURNACE = registerSimpleBlock("umber_furnace", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> UMBER_FURNACE_ACTIVE = registerSimpleBlock("umber_furnace_active", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_CRAFTING_TABLE = registerSimpleBlock("petrified_crafting_table", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_CRATE = registerSimpleBlock("bamboo_crate", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_BRIDGE = registerSimpleBlock("bamboo_bridge", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_LADDER = registerSimpleBlock("bamboo_ladder", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_NERD_POLE = registerSimpleBlock("bamboo_nerd_pole", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_EXTENDER = registerSimpleBlock("bamboo_extender", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_TORCH = registerSimpleBlock("bamboo_torch", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_PIPE = registerSimpleBlock("bamboo_pipe", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_PIPE_EXTRACT = registerSimpleBlock("bamboo_pipe_extract", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_PIPE_EXTRACT_ACTIVE = registerSimpleBlock("bamboo_pipe_extract_active", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> LIQUIFIER = registerSimpleBlock("liquifier", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SILO_ROOF = registerSimpleBlock("silo_roof", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SILO_TANK = registerSimpleBlock("silo_tank", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SILO_SUPPORTS = registerSimpleBlock("silo_supports", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> HONEY_COMB = registerSimpleBlock("honey_comb", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> UMBER_GOLEM_STATUE = registerSimpleBlock("umber_golem_statue", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> INSECT_REPELLENT = registerSimpleBlock("insect_repellent", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_CHEST = registerSimpleBlock("petrified_wood_chest", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GLOWING_JAR = registerSimpleBlock("glowing_jar", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FLUID_JAR = registerSimpleBlock("fluid_jar", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> COMPOSTER = registerSimpleBlock("composter", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SMOOTHIE_MAKER = registerSimpleBlock("smoothie_maker", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> UMBERSTONE_BUTTON = registerSimpleBlock("umberstone_button", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GLOW_GEM_ACTIVE = registerSimpleBlock("glow_gem_active", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GLOW_GEM_INACTIVE = registerSimpleBlock("glow_gem_inactive", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> MUCUS_BOMB = registerSimpleBlock("mucus_bomb", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    /**
     * Dungeons
     */
    public static final DeferredBlock<Block> SPIDER_SPAWNER = registerSimpleBlock("spider_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> JUMPING_SPIDER_SPAWNER = registerSimpleBlock("jumping_spider_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TARANTULA_SPAWNER = registerSimpleBlock("tarantula_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WASP_SPAWNER = registerSimpleBlock("wasp_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ANTLION_SPAWNER = registerSimpleBlock("antlion_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DRAGON_FLY_SPAWNER = registerSimpleBlock("dragon_fly_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ZOMBIE_ANT_SPAWNER = registerSimpleBlock("zombie_ant_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ZOMBIE_ANT_SOLDIER_SPAWNER = registerSimpleBlock("zombie_ant_soldier_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> MAGMA_CRAWLER_SPAWNER = registerSimpleBlock("magma_crawler_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> LOCUST_SPAWNER = registerSimpleBlock("locust_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GIANT_LILY_PAD = registerSimpleBlock("giant_lily_pad", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WASP_NEST = registerSimpleBlock("wasp_nest", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STAIRS_WASP_NEST = registerSimpleBlock("stairs_wasp_nest", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ANTLION_EGG = registerSimpleBlock("antlion_egg", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TARANTULA_EGG = registerSimpleBlock("tarantula_egg", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> CAPSTONE = registerSimpleBlock("capstone", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ANT_HILL_BLOCK = registerSimpleBlock("ant_hill_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FORCE_FIELD = registerSimpleBlock("force_field", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FORCE_LOCK = registerSimpleBlock("force_lock", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING = registerSimpleBlock("temple_brick_unbreaking", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_TELEPORTER = registerSimpleBlock("temple_teleporter", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BLOCK_OF_BONES = registerSimpleBlock("block_of_bones", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUNG_SPAWNER_BOT_FLY = registerSimpleBlock("dung_spawner_bot_fly", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUNG_SPAWNER_FLY = registerSimpleBlock("dung_spawner_fly", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    private static DeferredBlock<Block> registerSimpleBlock(String name, BlockBehaviour.Properties properties) {
        DeferredBlock<Block> deferredBlock = BLOCKS.registerSimpleBlock(name, properties);
        registerBlockItem(name, deferredBlock);
        return deferredBlock;
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> deferredBlock = BLOCKS.register(name, block);
        registerBlockItem(name, deferredBlock);
        return deferredBlock;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
