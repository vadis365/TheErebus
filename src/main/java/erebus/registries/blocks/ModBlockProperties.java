package erebus.registries.blocks;

import erebus.utils.BlockPropUtils;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModBlockProperties {

    // MARK: Amber
    public static final Properties AMBER_PROPERTIES = Properties.of()
            .noOcclusion()
            .strength(1.5F, 10.0F)
            .isViewBlocking((_, _, _) -> false)
            .mapColor(MapColor.GOLD)
            .sound(SoundType.GLASS);

    public static final Properties GLOWING_JAR_PROPERTIES = Properties.of()
            .strength(0.5F, 10.0F)
            .sound(SoundType.GLASS)
            .lightLevel(_ -> 15)
            .noOcclusion()
            .noTerrainParticles()
            .randomTicks()
            .isViewBlocking((_, _, _) -> false);

    public static final Properties FLUID_JAR_PROPERTIES = Properties.ofFullCopy(Blocks.GLASS)
            .strength(1.0F, 2000.0F)
            .sound(SoundType.GLASS)
            .noOcclusion()
            .isViewBlocking((_, _, _) -> false);

    public static final Properties AMBER_DOOR_PROPERTIES = Properties.of()
            .mapColor(MapColor.GOLD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0F)
            .noOcclusion()
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY);

    // MARK: Doors
    private static final Properties DEFAULT_DOOR = Properties.of()
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0F)
            .noOcclusion()
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY);

    public static final Properties DOOR_ASPER = DEFAULT_DOOR.mapColor(MapColor.WOOD);
    public static final Properties DOOR_BALSAM = DEFAULT_DOOR.mapColor(MapColor.TERRACOTTA_PINK);
    public static final Properties DOOR_BAOBAB = DEFAULT_DOOR.mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties DOOR_CYPRESS = DEFAULT_DOOR.mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties DOOR_EUCALYPTUS = DEFAULT_DOOR.mapColor(MapColor.TERRACOTTA_PINK);
    public static final Properties DOOR_MAHOGANY = DEFAULT_DOOR.mapColor(MapColor.COLOR_BROWN);
    public static final Properties DOOR_MARSHWOOD = DEFAULT_DOOR.mapColor(MapColor.TERRACOTTA_GREEN);
    public static final Properties DOOR_MOSSBARK = DEFAULT_DOOR.mapColor(MapColor.COLOR_BROWN);
    public static final Properties DOOR_ROTTEN = DEFAULT_DOOR.mapColor(MapColor.COLOR_BLACK);
    public static final Properties DOOR_SCORCHED = DEFAULT_DOOR.mapColor(MapColor.COLOR_BLACK);
    public static final Properties DOOR_WHITE = DEFAULT_DOOR.mapColor(MapColor.TERRACOTTA_WHITE);

    // MARK: Fences
    private static final Properties DEFAULT_FENCE = Properties.of()
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F, 3.0F)
            .sound(SoundType.WOOD)
            .ignitedByLava();

    private static final Properties DEFAULT_FENCE_GATE = Properties.of()
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F, 3.0F)
            .ignitedByLava();

    public static final Properties FENCE_ASPER = DEFAULT_FENCE.mapColor(MapColor.WOOD);
    public static final Properties FENCE_BALSAM = DEFAULT_FENCE.mapColor(MapColor.TERRACOTTA_PINK);
    public static final Properties FENCE_BAMBOO = DEFAULT_FENCE.mapColor(MapColor.SAND);
    public static final Properties FENCE_BAOBAB = DEFAULT_FENCE.mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties FENCE_CYPRESS = DEFAULT_FENCE.mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties FENCE_EUCALYPTUS = DEFAULT_FENCE.mapColor(MapColor.TERRACOTTA_PINK);
    public static final Properties FENCE_MAHOGANY = DEFAULT_FENCE.mapColor(MapColor.COLOR_BROWN);
    public static final Properties FENCE_MARSHWOOD = DEFAULT_FENCE.mapColor(MapColor.TERRACOTTA_GREEN);
    public static final Properties FENCE_MOSSBARK = DEFAULT_FENCE.mapColor(MapColor.COLOR_BROWN);
    public static final Properties FENCE_ROTTEN = DEFAULT_FENCE.mapColor(MapColor.COLOR_BLACK);
    public static final Properties FENCE_SCORCHED = DEFAULT_FENCE.mapColor(MapColor.COLOR_BLACK);
    public static final Properties FENCE_VARNISHED = DEFAULT_FENCE.mapColor(MapColor.WOOD);
    public static final Properties FENCE_WHITE = DEFAULT_FENCE.mapColor(MapColor.TERRACOTTA_WHITE);

    public static final Properties FENCE_GATE_ASPER = DEFAULT_FENCE_GATE.mapColor(MapColor.WOOD);
    public static final Properties FENCE_GATE_BALSAM = DEFAULT_FENCE_GATE.mapColor(MapColor.TERRACOTTA_PINK);
    public static final Properties FENCE_GATE_BAMBOO = DEFAULT_FENCE_GATE.mapColor(MapColor.SAND);
    public static final Properties FENCE_GATE_BAOBAB = DEFAULT_FENCE_GATE.mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties FENCE_GATE_CYPRESS = DEFAULT_FENCE_GATE.mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties FENCE_GATE_EUCALYPTUS = DEFAULT_FENCE_GATE.mapColor(MapColor.TERRACOTTA_PINK);
    public static final Properties FENCE_GATE_MAHOGANY = DEFAULT_FENCE_GATE.mapColor(MapColor.COLOR_BROWN);
    public static final Properties FENCE_GATE_MARSHWOOD = DEFAULT_FENCE_GATE.mapColor(MapColor.TERRACOTTA_GREEN);
    public static final Properties FENCE_GATE_MOSSBARK = DEFAULT_FENCE_GATE.mapColor(MapColor.COLOR_BROWN);
    public static final Properties FENCE_GATE_ROTTEN = DEFAULT_FENCE_GATE.mapColor(MapColor.COLOR_BLACK);
    public static final Properties FENCE_GATE_SCORCHED = DEFAULT_FENCE_GATE.mapColor(MapColor.COLOR_BLACK);
    public static final Properties FENCE_GATE_VARNISHED = DEFAULT_FENCE_GATE.mapColor(MapColor.WOOD);
    public static final Properties FENCE_GATE_WHITE = DEFAULT_FENCE_GATE.mapColor(MapColor.TERRACOTTA_WHITE);

    // MARK: Fluids
    public static final Properties FORMIC_ACID = Properties.of()
            .mapColor(MapColor.WATER)
            .replaceable()
            .noCollision()
            .strength(100.0F)
            .pushReaction(PushReaction.DESTROY)
            .noLootTable()
            .liquid()
            .sound(SoundType.EMPTY);

    public static final Properties HONEY = Properties.of()
            .mapColor(MapColor.WATER)
            .replaceable()
            .noCollision()
            .strength(100.0F)
            .pushReaction(PushReaction.DESTROY)
            .noLootTable()
            .liquid()
            .sound(SoundType.EMPTY);

    public static final Properties BEETLE_JUICE = Properties.of()
            .mapColor(MapColor.WATER)
            .replaceable()
            .noCollision()
            .strength(100.0F)
            .pushReaction(PushReaction.DESTROY)
            .noLootTable()
            .liquid()
            .sound(SoundType.EMPTY);

    public static final Properties ANTI_VENOM = Properties.of()
            .mapColor(MapColor.WATER)
            .replaceable()
            .noCollision()
            .strength(100.0F)
            .pushReaction(PushReaction.DESTROY)
            .noLootTable()
            .liquid()
            .sound(SoundType.EMPTY);

    // MARK: Ores
    private static final Properties ORE = Properties.of()
            .strength(3.0F)
            .explosionResistance(5.0F)
            .sound(SoundType.STONE);

    public static final Properties ORE_IRON = ORE;
    public static final Properties ORE_GOLD = ORE;
    public static final Properties ORE_COAL = ORE;
    public static final Properties ORE_DIAMOND = ORE;
    public static final Properties ORE_EMERALD = ORE;
    public static final Properties ORE_LAPIS = ORE;
    public static final Properties ORE_QUARTZ = ORE;
    public static final Properties ORE_PETRIFIED_QUARTZ = ORE;
    public static final Properties ORE_COPPER = ORE;
    public static final Properties ORE_SILVER = ORE;
    public static final Properties ORE_TIN = ORE;
    public static final Properties ORE_LEAD = ORE;
    public static final Properties ORE_ALUMINUM = ORE;
    public static final Properties ORE_JADE = ORE;
    public static final Properties ORE_ENCRUSTED_DIAMOND = ORE;
    public static final Properties ORE_FOSSIL = ORE;
    public static final Properties ORE_GNEISS = ORE;
    public static final Properties ORE_PETRIFIED_WOOD = ORE;
    public static final Properties ORE_TEMPLE = ORE;

    // MARK: Plants
    private static final Properties PLANT_BASE = Properties.of().mapColor(MapColor.PLANT);

    public static final Properties CROP_PROPS = PLANT_BASE
            .noCollision()
            .randomTicks()
            .instabreak()
            .sound(SoundType.CROP)
            .pushReaction(PushReaction.DESTROY);

    public static final Properties BUSH_PROPS = PLANT_BASE
            .randomTicks()
            .noCollision()
            .sound(SoundType.SWEET_BERRY_BUSH)
            .pushReaction(PushReaction.DESTROY);

    public static final Properties DARK_FRUIT_VINE_PROPS = PLANT_BASE
            .replaceable()
            .noCollision()
            .randomTicks()
            .strength(0.2F)
            .sound(SoundType.VINE)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY);

    public static final Properties PRICKLY_PEAR_PROPS = Properties.of()
            .mapColor(MapColor.PLANT)
            .randomTicks()
            .strength(0.4F)
            .sound(SoundType.WOOL)
            .pushReaction(PushReaction.DESTROY)
            .noOcclusion();

    public static final Properties COLOSSAL_BAMBOO_PROPS = Properties.of()
            .mapColor(MapColor.PLANT)
            .randomTicks()
            .strength(2F)
            .sound(SoundType.BAMBOO)
            .pushReaction(PushReaction.DESTROY)
            .noOcclusion();

    private static final Properties MUSHROOM_PROPS = Properties.of()
            .noCollision()
            .randomTicks()
            .instabreak()
            .sound(SoundType.GRASS)
            .lightLevel((_) -> 1)
            .hasPostProcess(BlockPropUtils::always)
            .pushReaction(PushReaction.DESTROY)
            .offsetType(BlockBehaviour.OffsetType.XZ);

    private static final Properties HUGE_MUSHROOM_PROPS = Properties.of()
            .instrument(NoteBlockInstrument.BASS)
            .strength(0.2F)
            .sound(SoundType.WOOD)
            .ignitedByLava();

    private static final Properties FLOWER_PROPS = Properties.of()
            .noCollision()
            .noOcclusion()
            .instabreak()
            .sound(SoundType.AZALEA);

    public static final Properties DARK_CAPPED_MUSHROOM_PROPS = MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties DUTCH_CAP_MUSHROOM_PROPS = MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties GRANDMAS_SHOES_MUSHROOM_PROPS = MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties KAIZERS_FINGERS_MUSHROOM_PROPS = MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties SARCASTIC_CZECH_MUSHROOM_PROPS = MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);

    public static final Properties DARK_CAPPED_MUSHROOM_BLOCK_PROPS = HUGE_MUSHROOM_PROPS.mapColor(MapColor.COLOR_BLACK);
    public static final Properties DUTCH_CAP_MUSHROOM_BLOCK_PROPS = HUGE_MUSHROOM_PROPS.mapColor(MapColor.COLOR_YELLOW);
    public static final Properties GRANDMAS_SHOES_MUSHROOM_BLOCK_PROPS = HUGE_MUSHROOM_PROPS.mapColor(MapColor.COLOR_GREEN);
    public static final Properties KAIZERS_FINGERS_MUSHROOM_BLOCK_PROPS = HUGE_MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties SARCASTIC_CZECH_MUSHROOM_BLOCK_PROPS = HUGE_MUSHROOM_PROPS.mapColor(MapColor.COLOR_RED);

    public static final Properties FLOWER_BLACK_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_BLACK);
    public static final Properties FLOWER_RED_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_RED);
    public static final Properties FLOWER_BROWN_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties FLOWER_BLUE_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_BLUE);
    public static final Properties FLOWER_PURPLE_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_PURPLE);
    public static final Properties FLOWER_CYAN_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_CYAN);
    public static final Properties FLOWER_LIGHT_GRAY_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_LIGHT_GRAY);
    public static final Properties FLOWER_GRAY_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_GRAY);
    public static final Properties FLOWER_PINK_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_PINK);
    public static final Properties FLOWER_YELLOW_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_YELLOW);
    public static final Properties FLOWER_LIGHT_BLUE_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_LIGHT_BLUE);
    public static final Properties FLOWER_MAGENTA_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_MAGENTA);
    public static final Properties FLOWER_ORANGE_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_ORANGE);
    public static final Properties FLOWER_WHITE_PROPS = FLOWER_PROPS.mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties FLOWER_RAINBOW_PROPS = FLOWER_PROPS.mapColor(MapColor.COLOR_RED);

    // MARK: Slabs
    public static final Properties SLAB_PLANKS = Properties.ofFullCopy(Blocks.OAK_SLAB);
    public static final Properties SLAB_STONE = Properties.ofFullCopy(Blocks.STONE_SLAB);
    public static final Properties SLAB_AMBER = Properties.ofFullCopy(Blocks.GLASS)
            .strength(1.5F)
            .noOcclusion()
            .isViewBlocking((_, _, _) -> false)
            .sound(SoundType.GLASS)
            .mapColor(MapColor.GOLD);

    public static final Properties SLAB_AMBER_BRICKS = Properties.ofFullCopy(Blocks.GLASS)
            .strength(1.5F)
            .noOcclusion()
            .isViewBlocking((_, _, _) -> false)
            .sound(SoundType.GLASS)
            .mapColor(MapColor.GOLD);

    // MARK: Stairs
    private static final Properties WOOD_STAIRS = Properties.of()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F, 3.0F)
            .sound(SoundType.WOOD)
            .ignitedByLava();

    private static final Properties STONE_STAIRS = Properties.of()
            .mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(2.0F, 6.0F);

    public static final Properties STAIRS_ASPER = WOOD_STAIRS.mapColor(MapColor.WOOD);
    public static final Properties STAIRS_BAMBOO = WOOD_STAIRS.mapColor(MapColor.SAND);
    public static final Properties STAIRS_BALSAM = WOOD_STAIRS.mapColor(MapColor.TERRACOTTA_PINK);
    public static final Properties STAIRS_BAOBAB = WOOD_STAIRS.mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties STAIRS_CYPRESS = WOOD_STAIRS.mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties STAIRS_EUCALYPTUS = WOOD_STAIRS.mapColor(MapColor.TERRACOTTA_PINK);
    public static final Properties STAIRS_MAHOGANY = WOOD_STAIRS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties STAIRS_MARSHWOOD = WOOD_STAIRS.mapColor(MapColor.TERRACOTTA_GREEN);
    public static final Properties STAIRS_MOSSBARK = WOOD_STAIRS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties STAIRS_PETRIFIED = WOOD_STAIRS.mapColor(MapColor.TERRACOTTA_BROWN);
    public static final Properties STAIRS_ROTTEN = WOOD_STAIRS.mapColor(MapColor.COLOR_BLACK);
    public static final Properties STAIRS_SCORCHED = WOOD_STAIRS.mapColor(MapColor.COLOR_BLACK);
    public static final Properties STAIRS_VARNISHED = WOOD_STAIRS.mapColor(MapColor.WOOD);
    public static final Properties STAIRS_WHITE = WOOD_STAIRS.mapColor(MapColor.TERRACOTTA_WHITE);

    public static final Properties STAIRS_AMBER = STONE_STAIRS;
    public static final Properties STAIRS_AMBER_BRICKS = STONE_STAIRS;
    public static final Properties STAIRS_MIR_BRICKS = STONE_STAIRS;
    public static final Properties STAIRS_MUD_BRICKS = STONE_STAIRS;
    public static final Properties STAIRS_UMBERCOBBLE = STONE_STAIRS;
    public static final Properties STAIRS_UMBERCOBBLE_MOSSY = STONE_STAIRS;
    public static final Properties STAIRS_UMBERCOBBLE_WEBBED = STONE_STAIRS;
    public static final Properties STAIRS_UMBERPAVER = STONE_STAIRS;
    public static final Properties STAIRS_UMBERPAVER_MOSSY = STONE_STAIRS;
    public static final Properties STAIRS_UMBERPAVER_WEBBED = STONE_STAIRS;
    public static final Properties STAIRS_UMBERSTONE = STONE_STAIRS;
    public static final Properties STAIRS_UMBERSTONE_BRICKS = STONE_STAIRS;
    public static final Properties STAIRS_UMBERTILE_SMOOTH = STONE_STAIRS;
    public static final Properties STAIRS_UMBERTILE_SMOOTH_SMALL = STONE_STAIRS;

    // MARK: Umberstone
    public static final Properties UMBERSTONE = Properties.of().strength(1.5F, 10.0F).requiresCorrectToolForDrops();
    public static final Properties UMBERSTONE_BRICKS = Properties.ofFullCopy(Blocks.STONE_BRICKS);
    public static final Properties UMBERCOBBLE = Properties.ofFullCopy(Blocks.COBBLESTONE);
    public static final Properties UMBERCOBBLE_MOSSY = Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE);
    public static final Properties UMBERCOBBLE_WEBBED = Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE);
    public static final Properties UMBERTILE_SMOOTH = Properties.ofFullCopy(Blocks.SMOOTH_STONE);
    public static final Properties UMBERTILE_SMOOTH_SMALL = Properties.ofFullCopy(Blocks.SMOOTH_STONE);
    public static final Properties UMBERGRAVEL = Properties.ofFullCopy(Blocks.GRAVEL);
    public static final Properties UMBERPAVER = Properties.ofFullCopy(Blocks.STONE);
    public static final Properties UMBERPAVER_MOSSY = Properties.ofFullCopy(Blocks.STONE);
    public static final Properties UMBERPAVER_WEBBED = Properties.ofFullCopy(Blocks.STONE);
    public static final Properties UMBERSTONE_PILLAR = Properties.ofFullCopy(Blocks.STONE);
    public static final Properties VOLCANIC_ROCK = Properties.of().mapColor(MapColor.STONE);
    public static final Properties DUST = Properties.of().mapColor(MapColor.STONE);
    public static final Properties DUST_LAYER = Properties.ofFullCopy(Blocks.SNOW);
    public static final Properties PETRIFIED_WOOD_ROCK = Properties.ofFullCopy(Blocks.STONE);
    public static final Properties PETRIFIED_WOOD_ROCK_2 = Properties.ofFullCopy(Blocks.STONE);
    public static final Properties PETRIFIED_WOOD_ROCK_3 = Properties.ofFullCopy(Blocks.STONE);
    public static final Properties PETRIFIED_WOOD_ROCK_4 = Properties.ofFullCopy(Blocks.STONE);
    public static final Properties PETRIFIED_WOOD_ROCK_5 = Properties.ofFullCopy(Blocks.STONE);
    public static final Properties PETRIFIED_WOOD_ROCK_6 = Properties.ofFullCopy(Blocks.STONE);
    public static final Properties PETRIFIED_BARK_RED = Properties.ofFullCopy(Blocks.OAK_LOG);
    public static final Properties PETRIFIED_BARK_BROWN = Properties.ofFullCopy(Blocks.OAK_LOG);
    public static final Properties PETRIFIED_LOG_INNER = Properties.of().mapColor(MapColor.STONE);
    public static final Properties DUNG = Properties.of().mapColor(MapColor.STONE);
    public static final Properties MIR_BRICKS = Properties.of().mapColor(MapColor.STONE);
    public static final Properties MUD_BRICKS = Properties.of().mapColor(MapColor.STONE);

    // MARK: Walls
    private static final Properties WALL_BASE = Properties.ofFullCopy(Blocks.STONE_BRICK_WALL);
    public static final Properties WALL_UMBERSTONE = WALL_BASE;
    public static final Properties WALL_UMBERCOBBLE = WALL_BASE;
    public static final Properties WALL_UMBERCOBBLE_MOSSY = WALL_BASE;
    public static final Properties WALL_UMBERCOBBLE_WEBBED = WALL_BASE;
    public static final Properties WALL_UMBERSTONE_BRICKS = WALL_BASE;
    public static final Properties WALL_UMBERTILE_SMOOTH = WALL_BASE;
    public static final Properties WALL_UMBERTILE_SMOOTH_SMALL = WALL_BASE;
    public static final Properties WALL_AMBER = AMBER_PROPERTIES;
    public static final Properties WALL_AMBER_BRICKS = AMBER_PROPERTIES;
    public static final Properties WALL_UMBERPAVER = WALL_BASE;
    public static final Properties WALL_UMBERPAVER_MOSSY = WALL_BASE;
    public static final Properties WALL_UMBERPAVER_WEBBED = WALL_BASE;

    // MARK: Wood
    public static final Properties LOG_HOLLOW = Properties.of()
            .mapColor(MapColor.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F, 3.0F)
            .sound(SoundType.WOOD)
            .ignitedByLava()
            .noOcclusion();

    public static final Properties LEAVES = Properties.of()
            .strength(0.2F)
            .randomTicks()
            .sound(SoundType.GRASS)
            .noOcclusion()
            .isValidSpawn(Blocks::ocelotOrParrot)
            .isSuffocating(BlockPropUtils::never)
            .isViewBlocking(BlockPropUtils::never)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(BlockPropUtils::never);

    public static final Properties PLANKS = Properties.of()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F, 3.0F)
            .sound(SoundType.WOOD)
            .ignitedByLava();

    public static final Properties CHEST = Properties.of()
            .mapColor(MapColor.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.5F)
            .sound(SoundType.WOOD)
            .ignitedByLava();

    public static Properties log(MapColor top, MapColor side) {
        return Properties.of()
                .mapColor(p_152624_ -> p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : side)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava();
    }

    // MARK: Other
    public static final Properties PORTAL = Properties.ofFullCopy(Blocks.NETHER_PORTAL);
    public static final Properties GAEAN_KEYSTONE = Properties.of()
            .noOcclusion()
            .strength(3.0F)
            .sound(SoundType.STONE)
            .mapColor(MapColor.STONE);
    public static final Properties JADE_BLOCK = Properties.of()
            .strength(5.0F, 10.0F)
            .sound(SoundType.STONE)
            .mapColor(DyeColor.GREEN);

    public static final Properties MUD = Properties.ofFullCopy(Blocks.MUD);
    public static final Properties QUICK_SAND = Properties.of().strength(28F).sound(SoundType.SAND).mapColor(MapColor.SAND).noCollision();
    public static final Properties GHOST_SAND = Properties.of().mapColor(MapColor.STONE);
    public static final Properties SWAMP_VENT = Properties.ofFullCopy(Blocks.GRASS_BLOCK);
    public static final Properties GNEISS_VENT = Properties.of().mapColor(MapColor.STONE);
    public static final Properties RED_GEM_BLOCK = Properties.of().mapColor(MapColor.STONE);
    public static final Properties RED_GEM_LAMP = Properties.ofFullCopy(Blocks.REDSTONE_LAMP);
    public static final Properties WITHER_WEB = Properties.of().mapColor(MapColor.WOOL).sound(SoundType.COBWEB).forceSolidOn().noCollision().requiresCorrectToolForDrops().strength(4.0F).pushReaction(PushReaction.DESTROY);
    public static final Properties LAVA_WEB = Properties.of().mapColor(MapColor.WOOL).sound(SoundType.COBWEB).forceSolidOn().noCollision().requiresCorrectToolForDrops().strength(4.0F).pushReaction(PushReaction.DESTROY);
    public static final Properties GNEISS = Properties.of().mapColor(MapColor.STONE);
    public static final Properties GNEISS_CARVED = Properties.of().mapColor(MapColor.STONE);
    public static final Properties GNEISS_RELIEF = Properties.of().mapColor(MapColor.STONE);
    public static final Properties GNEISS_BRICKS = Properties.of().mapColor(MapColor.STONE);
    public static final Properties GNEISS_SMOOTH = Properties.of().mapColor(MapColor.STONE);
    public static final Properties GNEISS_TILES = Properties.of().mapColor(MapColor.STONE);
    public static final Properties GNEISS_TILES_CRACKED = Properties.of().mapColor(MapColor.STONE);
    public static final Properties TEMPLE_BRICK = Properties.of().mapColor(MapColor.STONE);
    public static final Properties TEMPLE_PILLAR = Properties.of().mapColor(MapColor.STONE);
    public static final Properties TEMPLE_TILE = Properties.of().mapColor(MapColor.STONE);
    public static final Properties SILK = Properties.of().mapColor(MapColor.STONE);
    public static final Properties REIN_EXO = Properties.of().mapColor(MapColor.STONE);
    public static final Properties VELOCITY_BLOCK = Properties.of().mapColor(MapColor.STONE).strength(1.5F).explosionResistance(10F).sound(SoundType.STONE);
    public static final Properties VELOCITY_BLOCK_LIGHTNING_SPEED = Properties.of().mapColor(MapColor.STONE).strength(1.5F).explosionResistance(10F).sound(SoundType.STONE);
    public static final Properties BLOCK_OF_BONES = Properties.ofFullCopy(Blocks.BONE_BLOCK).noCollision();
    public static final Properties ANTLION_EGG = Properties.of().mapColor(MapColor.STONE).noOcclusion();
    public static final Properties TARANTULA_EGG = Properties.of().mapColor(MapColor.STONE).noOcclusion();
    public static final Properties HONEY_TREAT = Properties.of().forceSolidOn().strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY);
    public static final Properties WASP_NEST = Properties.of().mapColor(MapColor.STONE);
    public static final Properties STAIRS_WASP_NEST = Properties.of().strength(2.0F);
    public static final Properties INSECT_REPELLENT = Properties.of().noCollision().noOcclusion().replaceable();

    // Spawners
    public static final Properties ANTLION_SPAWNER = Properties.of().mapColor(MapColor.STONE);
    public static final Properties DRAGON_FLY_SPAWNER = Properties.of().mapColor(MapColor.STONE);
    public static final Properties JUMPING_SPIDER_SPAWNER = Properties.of().mapColor(MapColor.STONE);
    public static final Properties SPIDER_SPAWNER = Properties.of().mapColor(MapColor.STONE);
    public static final Properties TARANTULA_SPAWNER = Properties.of().mapColor(MapColor.STONE);
    public static final Properties WASP_SPAWNER = Properties.of().mapColor(MapColor.STONE);
    public static final Properties ZOMBIE_ANT_SPAWNER = Properties.of().mapColor(MapColor.STONE);
    public static final Properties ZOMBIE_ANT_SOLDIER_SPAWNER = Properties.of().mapColor(MapColor.STONE);
    public static final Properties MAGMA_CRAWLER_SPAWNER = Properties.of().mapColor(MapColor.STONE);
    public static final Properties DUNG_SPAWNER_FLY = Properties.of().mapColor(MapColor.STONE);
    public static final Properties DUNG_SPAWNER_BOT_FLY = Properties.of().mapColor(MapColor.STONE).noOcclusion();
    public static final Properties LOCUST_SPAWNER = Properties.of().mapColor(MapColor.STONE);

    // Utility Blocks
    public static final Properties PETRIFIED_CRAFTING_TABLE = Properties.ofFullCopy(Blocks.CRAFTING_TABLE);
    public static final Properties BAMBOO_CRATE = Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.4F).noOcclusion().sound(SoundType.LADDER);
    public static final Properties BAMBOO_BRIDGE = Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.4F).noOcclusion().sound(SoundType.LADDER);
    public static final Properties BAMBOO_LADDER = Properties.ofFullCopy(Blocks.LADDER).sound(SoundType.BAMBOO);
    public static final Properties BAMBOO_NERD_POLE = Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.4F).noOcclusion().sound(SoundType.LADDER);
    public static final Properties BAMBOO_EXTENDER = Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.4F).noOcclusion().sound(SoundType.LADDER);
    public static final Properties BAMBOO_TORCH = Properties.of().mapColor(MapColor.COLOR_GREEN).noCollision().sound(SoundType.BAMBOO).lightLevel((_) -> 15);
    public static final Properties BAMBOO_PIPE = Properties.of().mapColor(MapColor.COLOR_GREEN).strength(1.5F).noOcclusion().sound(SoundType.BAMBOO);
    public static final Properties BAMBOO_PIPE_EXTRACT = Properties.of().mapColor(MapColor.COLOR_GREEN).strength(1.5F).noOcclusion().sound(SoundType.BAMBOO);
    public static final Properties SILO_ROOF = Properties.of().mapColor(MapColor.METAL).strength(3F, 10F).sound(SoundType.METAL).noOcclusion();
    public static final Properties SILO_TANK = Properties.of().mapColor(MapColor.WOOD).strength(3F, 10F).sound(SoundType.METAL).noOcclusion();
    public static final Properties SILO_SUPPORTS = Properties.of().mapColor(MapColor.WOOD).noCollision().strength(2F, 10F).sound(SoundType.WOOD).noOcclusion();
    public static final Properties HONEY_COMB = Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(0.5F, 10F).lightLevel(_ -> 1).sound(SoundType.WOOL);
    public static final Properties COMPOSTER = Properties.of().mapColor(MapColor.COLOR_GREEN).strength(2F, 10F).sound(SoundType.WOOD).noOcclusion();
    public static final Properties BLENDER = Properties.of().mapColor(MapColor.STONE).noOcclusion();
    public static final Properties UMBER_FURNACE = Properties.ofFullCopy(Blocks.FURNACE);
    public static final Properties UMBERSTONE_BUTTON = Properties.of().mapColor(MapColor.STONE);
    public static final Properties LIQUIFIER = Properties.ofFullCopy(Blocks.GLASS)
            .mapColor(MapColor.STONE)
            .strength(1.0F, 2000.0F)
            .sound(SoundType.GLASS)
            .noOcclusion()
            .isViewBlocking((_, _, _) -> false);
    public static final Properties GLOW_GEM_ACTIVE = Properties.of().mapColor(MapColor.COLOR_YELLOW).noCollision().sound(SoundType.GLASS).lightLevel((_) -> 15);
    public static final Properties GLOW_GEM_INACTIVE = Properties.of().mapColor(MapColor.COLOR_RED).noCollision().sound(SoundType.GLASS).lightLevel((_) -> 0);
    public static final Properties MUCUS_BOMB = Properties.of().mapColor(MapColor.STONE);
    public static final Properties UMBER_GOLEM_STATUE = Properties.of().mapColor(MapColor.STONE);

    public static final Properties ALTAR = Properties.of().mapColor(MapColor.STONE).strength(2F).noOcclusion();

    // Antlion Dungeon
    public static final Properties CAPSTONE = Properties.of().mapColor(MapColor.STONE);
    public static final Properties CAPSTONE_MUD = Properties.of().mapColor(MapColor.STONE);
    public static final Properties CAPSTONE_IRON = Properties.of().mapColor(MapColor.STONE);
    public static final Properties CAPSTONE_GOLD = Properties.of().mapColor(MapColor.STONE);
    public static final Properties CAPSTONE_JADE = Properties.of().mapColor(MapColor.STONE);
    public static final Properties TEMPLE_BRICK_UNBREAKING = Properties.of().mapColor(MapColor.STONE);
    public static final Properties TEMPLE_BRICK_UNBREAKING_JADE = Properties.of().mapColor(MapColor.STONE);
    public static final Properties TEMPLE_BRICK_UNBREAKING_EXO = Properties.of().mapColor(MapColor.STONE);
    public static final Properties TEMPLE_BRICK_UNBREAKING_CREAM = Properties.of().mapColor(MapColor.STONE);
    public static final Properties TEMPLE_BRICK_UNBREAKING_EYE = Properties.of().mapColor(MapColor.STONE);
    public static final Properties TEMPLE_BRICK_UNBREAKING_STRING = Properties.of().mapColor(MapColor.STONE);
    public static final Properties TEMPLE_TELEPORTER = Properties.of().mapColor(MapColor.STONE);
    public static final Properties FORCE_FIELD = Properties.of().mapColor(MapColor.STONE);
    public static final Properties FORCE_LOCK = Properties.of().mapColor(MapColor.STONE);
    public static final Properties ANT_HILL_BLOCK = Properties.of().mapColor(MapColor.STONE);
}
