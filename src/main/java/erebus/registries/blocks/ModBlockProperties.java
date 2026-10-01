package erebus.registries.blocks;

import erebus.Erebus;
import erebus.utils.BlockPropUtils;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModBlockProperties {

    // MARK: Amber
    public static final Properties AMBER_PROPERTIES = amberProperties(1.5F, 6);
    public static final Properties AMBER_BRICKS_PROPERTIES = amberProperties(2.5F, 6);
    public static final Properties AMBER_GLASS_PROPERTIES = amberProperties(1.5F, 6);
    public static final Properties PRESERVED_AMBER_PROPERTIES = amberProperties(10, 6);
    public static final Properties PRESERVED_AMBER_GLASS_PROPERTIES = amberProperties(10, 6);
    public static final Properties GLOWING_JAR_PROPERTIES = Properties.of()
            .strength(0.5F, 10.0F)
            .sound(SoundType.GLASS)
            .lightLevel(_ -> 15)
            .noOcclusion()
            .noTerrainParticles()
            .randomTicks()
            .isViewBlocking((_, _, _) -> false);
    public static final Properties FLUID_JAR_PROPERTIES = Properties.ofFullCopy(Blocks.GLASS)
            .strength(1.0F, 1.0F)
            .sound(SoundType.GLASS)
            .noOcclusion()
            .isViewBlocking((_, _, _) -> false);
    public static final Properties AMBER_DOOR_PROPERTIES = Properties.of()
            .mapColor(MapColor.GOLD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0F)
            .noOcclusion()
            .pushReaction(PushReaction.DESTROY);
    public static final Properties DOOR_ASPER = doorProperties().mapColor(MapColor.WOOD);
    public static final Properties DOOR_BALSAM = doorProperties().mapColor(MapColor.TERRACOTTA_PINK);
    public static final Properties DOOR_BAOBAB = doorProperties().mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties DOOR_CYPRESS = doorProperties().mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties DOOR_EUCALYPTUS = doorProperties().mapColor(MapColor.TERRACOTTA_PINK);
    public static final Properties DOOR_MAHOGANY = doorProperties().mapColor(MapColor.COLOR_BROWN);
    public static final Properties DOOR_MARSHWOOD = doorProperties().mapColor(MapColor.TERRACOTTA_GREEN);
    public static final Properties DOOR_MOSSBARK = doorProperties().mapColor(MapColor.COLOR_BROWN);
    public static final Properties DOOR_ROTTEN = doorProperties().mapColor(MapColor.COLOR_BLACK);
    public static final Properties DOOR_SCORCHED = doorProperties().mapColor(MapColor.COLOR_BLACK);
    public static final Properties DOOR_WHITE = doorProperties().mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties DOOR_PETRIFIED = Properties.of().mapColor(MapColor.TERRACOTTA_BROWN)
            .strength(3.0F).noOcclusion().pushReaction(PushReaction.DESTROY);
    public static final Properties FENCE_ASPER = fenceProperties().mapColor(MapColor.WOOD);
    public static final Properties FENCE_BALSAM = fenceProperties().mapColor(MapColor.TERRACOTTA_PINK);
    public static final Properties FENCE_BAMBOO = fenceProperties().mapColor(MapColor.SAND);
    public static final Properties FENCE_BAOBAB = fenceProperties().mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties FENCE_CYPRESS = fenceProperties().mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties FENCE_EUCALYPTUS = fenceProperties().mapColor(MapColor.TERRACOTTA_PINK);
    public static final Properties FENCE_MAHOGANY = fenceProperties().mapColor(MapColor.COLOR_BROWN);
    public static final Properties FENCE_MARSHWOOD = fenceProperties().mapColor(MapColor.TERRACOTTA_GREEN);
    public static final Properties FENCE_MOSSBARK = fenceProperties().mapColor(MapColor.COLOR_BROWN);
    public static final Properties FENCE_ROTTEN = fenceProperties().mapColor(MapColor.COLOR_BLACK);
    public static final Properties FENCE_SCORCHED = fenceProperties().mapColor(MapColor.COLOR_BLACK);
    public static final Properties FENCE_VARNISHED = fenceProperties().mapColor(MapColor.WOOD);
    public static final Properties FENCE_WHITE = fenceProperties().mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties FENCE_GATE_ASPER = fenceGateProperties().mapColor(MapColor.WOOD);
    public static final Properties FENCE_GATE_BALSAM = fenceGateProperties().mapColor(MapColor.TERRACOTTA_PINK);
    public static final Properties FENCE_GATE_BAMBOO = fenceGateProperties().mapColor(MapColor.SAND);
    public static final Properties FENCE_GATE_BAOBAB = fenceGateProperties().mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties FENCE_GATE_CYPRESS = fenceGateProperties().mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties FENCE_GATE_EUCALYPTUS = fenceGateProperties().mapColor(MapColor.TERRACOTTA_PINK);
    public static final Properties FENCE_GATE_MAHOGANY = fenceGateProperties().mapColor(MapColor.COLOR_BROWN);
    public static final Properties FENCE_GATE_MARSHWOOD = fenceGateProperties().mapColor(MapColor.TERRACOTTA_GREEN);
    public static final Properties FENCE_GATE_MOSSBARK = fenceGateProperties().mapColor(MapColor.COLOR_BROWN);
    public static final Properties FENCE_GATE_ROTTEN = fenceGateProperties().mapColor(MapColor.COLOR_BLACK);
    public static final Properties FENCE_GATE_SCORCHED = fenceGateProperties().mapColor(MapColor.COLOR_BLACK);
    public static final Properties FENCE_GATE_VARNISHED = fenceGateProperties().mapColor(MapColor.WOOD);
    public static final Properties FENCE_GATE_WHITE = fenceGateProperties().mapColor(MapColor.TERRACOTTA_WHITE);
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
    // MARK: Plants
    public static final Properties CROP_PROPS = Properties.of().mapColor(MapColor.PLANT)
            .noCollision()
            .randomTicks()
            .instabreak()
            .sound(SoundType.CROP)
            .pushReaction(PushReaction.DESTROY);
    public static final Properties BUSH_PROPS = Properties.of().mapColor(MapColor.PLANT)
            .strength(0.2F)
            .randomTicks()
            .noCollision()
            .sound(SoundType.SWEET_BERRY_BUSH)
            .pushReaction(PushReaction.DESTROY);
    public static final Properties DARK_FRUIT_VINE_PROPS = Properties.of().mapColor(MapColor.PLANT)
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
    public static final Properties DARK_CAPPED_MUSHROOM_BLOCK_PROPS = hugeMushroomProperties().mapColor(MapColor.COLOR_BLACK);
    public static final Properties DUTCH_CAP_MUSHROOM_BLOCK_PROPS = hugeMushroomProperties().mapColor(MapColor.COLOR_YELLOW);
    public static final Properties GRANDMAS_SHOES_MUSHROOM_BLOCK_PROPS = hugeMushroomProperties().mapColor(MapColor.COLOR_GREEN);
    public static final Properties KAIZERS_FINGERS_MUSHROOM_BLOCK_PROPS = hugeMushroomProperties().mapColor(MapColor.COLOR_BROWN);
    public static final Properties SARCASTIC_CZECH_MUSHROOM_BLOCK_PROPS = hugeMushroomProperties().mapColor(MapColor.COLOR_RED);
    public static final Properties FLOWER_BLACK_PROPS = flowerProperties().mapColor(MapColor.COLOR_BLACK);
    public static final Properties FLOWER_RED_PROPS = flowerProperties().mapColor(MapColor.COLOR_RED);
    public static final Properties FLOWER_BROWN_PROPS = flowerProperties().mapColor(MapColor.COLOR_BROWN);
    public static final Properties FLOWER_BLUE_PROPS = flowerProperties().mapColor(MapColor.COLOR_BLUE);
    public static final Properties FLOWER_PURPLE_PROPS = flowerProperties().mapColor(MapColor.COLOR_PURPLE);
    public static final Properties FLOWER_CYAN_PROPS = flowerProperties().mapColor(MapColor.COLOR_CYAN);
    public static final Properties FLOWER_LIGHT_GRAY_PROPS = flowerProperties().mapColor(MapColor.COLOR_LIGHT_GRAY);
    public static final Properties FLOWER_GRAY_PROPS = flowerProperties().mapColor(MapColor.COLOR_GRAY);
    public static final Properties FLOWER_PINK_PROPS = flowerProperties().mapColor(MapColor.COLOR_PINK);
    public static final Properties FLOWER_YELLOW_PROPS = flowerProperties().mapColor(MapColor.COLOR_YELLOW);
    public static final Properties FLOWER_LIGHT_BLUE_PROPS = flowerProperties().mapColor(MapColor.COLOR_LIGHT_BLUE);
    public static final Properties FLOWER_MAGENTA_PROPS = flowerProperties().mapColor(MapColor.COLOR_MAGENTA);
    public static final Properties FLOWER_ORANGE_PROPS = flowerProperties().mapColor(MapColor.COLOR_ORANGE);
    public static final Properties FLOWER_WHITE_PROPS = flowerProperties().mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties FLOWER_RAINBOW_PROPS = flowerProperties().mapColor(MapColor.COLOR_RED);
    public static final Properties SLAB_PLANKS_PETRIFIED = petrifiedWoodProperties().strength(2);
    public static final Properties SLAB_STONE = Properties.ofFullCopy(Blocks.STONE_SLAB).strength(2);
    public static final Properties SLAB_AMBER = amberProperties(2, 2);
    public static final Properties SLAB_AMBER_BRICKS = amberProperties(2, 2);
    public static final Properties STAIRS_ASPER = woodStairProperties().mapColor(MapColor.WOOD);
    public static final Properties STAIRS_BAMBOO = woodStairProperties().mapColor(MapColor.SAND);
    public static final Properties STAIRS_BALSAM = woodStairProperties().mapColor(MapColor.TERRACOTTA_PINK);
    public static final Properties STAIRS_BAOBAB = woodStairProperties().mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties STAIRS_CYPRESS = woodStairProperties().mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties STAIRS_EUCALYPTUS = woodStairProperties().mapColor(MapColor.TERRACOTTA_PINK);
    public static final Properties STAIRS_MAHOGANY = woodStairProperties().mapColor(MapColor.COLOR_BROWN);
    public static final Properties STAIRS_MARSHWOOD = woodStairProperties().mapColor(MapColor.TERRACOTTA_GREEN);
    public static final Properties STAIRS_MOSSBARK = woodStairProperties().mapColor(MapColor.COLOR_BROWN);
    public static final Properties STAIRS_PETRIFIED = petrifiedWoodProperties();
    public static final Properties STAIRS_ROTTEN = woodStairProperties().mapColor(MapColor.COLOR_BLACK);
    public static final Properties STAIRS_SCORCHED = woodStairProperties().mapColor(MapColor.COLOR_BLACK);
    public static final Properties STAIRS_VARNISHED = woodStairProperties().mapColor(MapColor.WOOD);
    public static final Properties STAIRS_WHITE = woodStairProperties().mapColor(MapColor.TERRACOTTA_WHITE);
    public static final Properties STAIRS_AMBER = amberProperties(1.5F, 6);
    public static final Properties STAIRS_AMBER_BRICKS = amberProperties(2.5F, 6);
    public static final Properties STAIRS_MIR_BRICKS = stoneStairProperties(1.5F, 1.5F);
    public static final Properties STAIRS_MUD_BRICKS = stoneStairProperties(0.8F, 0.6F);
    public static final Properties STAIRS_UMBERCOBBLE = stoneStairProperties(1.5F, 6);
    public static final Properties STAIRS_UMBERCOBBLE_MOSSY = stoneStairProperties(1.5F, 6);
    public static final Properties STAIRS_UMBERCOBBLE_WEBBED = stoneStairProperties(1.5F, 6);
    public static final Properties STAIRS_UMBERPAVER = stoneStairProperties(3.5F, 3.5F);
    public static final Properties STAIRS_UMBERPAVER_MOSSY = stoneStairProperties(3.5F, 3.5F);
    public static final Properties STAIRS_UMBERPAVER_WEBBED = stoneStairProperties(3.5F, 3.5F);
    public static final Properties STAIRS_UMBERSTONE = stoneStairProperties(1.5F, 6);
    public static final Properties STAIRS_UMBERSTONE_BRICKS = stoneStairProperties(1.5F, 6);
    public static final Properties STAIRS_UMBERTILE_SMOOTH = stoneStairProperties(1.5F, 6);
    public static final Properties STAIRS_UMBERTILE_SMOOTH_SMALL = stoneStairProperties(1.5F, 6);
    // MARK: Umberstone
    public static final Properties UMBERSTONE = Properties.ofFullCopy(Blocks.STONE);
    public static final Properties UMBERSTONE_BRICKS = Properties.ofFullCopy(Blocks.STONE_BRICKS);
    public static final Properties UMBERCOBBLE = Properties.ofFullCopy(Blocks.STONE);
    public static final Properties UMBERCOBBLE_MOSSY = Properties.ofFullCopy(Blocks.STONE);
    public static final Properties UMBERCOBBLE_WEBBED = Properties.ofFullCopy(Blocks.STONE);
    public static final Properties UMBERTILE_SMOOTH = Properties.ofFullCopy(Blocks.STONE);
    public static final Properties UMBERTILE_SMOOTH_SMALL = Properties.ofFullCopy(Blocks.STONE);
    public static final Properties UMBERGRAVEL = Properties.ofFullCopy(Blocks.GRAVEL);
    public static final Properties UMBERPAVER = Properties.ofFullCopy(Blocks.STONE).strength(3.5F, 3.5F);
    public static final Properties UMBERPAVER_MOSSY = Properties.ofFullCopy(Blocks.STONE).strength(3.5F, 3.5F);
    public static final Properties UMBERPAVER_WEBBED = Properties.ofFullCopy(Blocks.STONE).strength(3.5F, 3.5F);
    public static final Properties UMBERSTONE_PILLAR = Properties.ofFullCopy(Blocks.STONE);
    public static final Properties VOLCANIC_ROCK = Properties.of().mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 12).sound(SoundType.STONE);
    public static final Properties DUST = Properties.of().mapColor(MapColor.DIRT).sound(SoundType.SNOW);
    public static final Properties DUST_LAYER = Properties.ofFullCopy(Blocks.SNOW);
    public static final Properties PETRIFIED_WOOD_ROCK = petrifiedRockProperties();
    public static final Properties PETRIFIED_WOOD_ROCK_2 = petrifiedRockProperties();
    public static final Properties PETRIFIED_WOOD_ROCK_3 = petrifiedRockProperties();
    public static final Properties PETRIFIED_WOOD_ROCK_4 = petrifiedRockProperties();
    public static final Properties PETRIFIED_WOOD_ROCK_5 = petrifiedRockProperties();
    public static final Properties PETRIFIED_WOOD_ROCK_6 = petrifiedRockProperties();
    public static final Properties PETRIFIED_BARK_RED = petrifiedRockProperties();
    public static final Properties PETRIFIED_BARK_BROWN = petrifiedRockProperties();
    public static final Properties PETRIFIED_LOG_INNER = petrifiedRockProperties();
    public static final Properties DUNG = Properties.of().mapColor(MapColor.DIRT).strength(0.4F)
            .sound(new net.neoforged.neoforge.common.util.DeferredSoundType(1, 1,
                    () -> net.minecraft.sounds.SoundEvents.GRAVEL_BREAK, erebus.registries.ModSounds.CABBAGE_FART,
                    erebus.registries.ModSounds.CABBAGE_FART, () -> net.minecraft.sounds.SoundEvents.GRAVEL_HIT,
                    erebus.registries.ModSounds.CABBAGE_FART));
    public static final Properties MIR_BRICKS = Properties.of().mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F).sound(SoundType.STONE);
    public static final Properties MUD_BRICKS = Properties.of().mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F, 0.6F).sound(SoundType.STONE);
    public static final Properties WALL_AMBER = amberProperties(2, 2);
    public static final Properties WALL_AMBER_BRICKS = amberProperties(2, 2);
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
    public static final Properties PLANKS_PETRIFIED = petrifiedWoodProperties();
    // MARK: Other
    public static final Properties PORTAL = Properties.ofFullCopy(Blocks.NETHER_PORTAL);
    public static final Properties GAEAN_KEYSTONE = Properties.of()
            .noOcclusion()
            .requiresCorrectToolForDrops()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .strength(3.0F)
            .sound(SoundType.STONE)
            .mapColor(MapColor.STONE);
    public static final Properties JADE_BLOCK = Properties.of()
            .requiresCorrectToolForDrops()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .strength(5.0F, 6.0F)
            .sound(SoundType.STONE)
            .mapColor(DyeColor.GREEN);
    public static final Properties MUD = Properties.ofFullCopy(Blocks.MUD);
    public static final Properties QUICK_SAND = Properties.of().strength(28F).sound(SoundType.SAND).mapColor(MapColor.SAND).noCollision();
    public static final Properties GHOST_SAND = Properties.ofFullCopy(Blocks.SAND).strength(0.42F).noCollision().noOcclusion();
    public static final Properties SWAMP_VENT = Properties.ofFullCopy(Blocks.GRASS_BLOCK);
    public static final Properties GNEISS_VENT = Properties.of().mapColor(MapColor.STONE).strength(30, 3600000).requiresCorrectToolForDrops().sound(SoundType.STONE).randomTicks();
    public static final Properties RED_GEM_BLOCK = Properties.of().mapColor(MapColor.COLOR_RED).lightLevel((_) -> 15).sound(SoundType.GLASS).strength(0.3F);
    public static final Properties RED_GEM_LAMP = Properties.of()
            .mapColor(MapColor.COLOR_RED)
            .lightLevel((state) -> state.getValue(BlockStateProperties.LIT) ? 15 : 0)
            .strength(0.3F)
            .sound(SoundType.GLASS)
            .isValidSpawn(Blocks::always);
    public static final Properties WITHER_WEB = Properties.of().mapColor(MapColor.WOOL).sound(SoundType.COBWEB).forceSolidOn().noCollision().requiresCorrectToolForDrops().strength(4.0F).pushReaction(PushReaction.DESTROY);
    public static final Properties LAVA_WEB = Properties.of().mapColor(MapColor.WOOL).sound(SoundType.COBWEB).forceSolidOn().noCollision().requiresCorrectToolForDrops().strength(4.0F).pushReaction(PushReaction.DESTROY);
    public static final Properties GNEISS = Properties.of().mapColor(MapColor.STONE).strength(30, 3600000).requiresCorrectToolForDrops().sound(SoundType.STONE);
    public static final Properties GNEISS_CARVED = Properties.of().mapColor(MapColor.STONE).strength(30, 3600000).requiresCorrectToolForDrops().sound(SoundType.STONE);
    public static final Properties GNEISS_RELIEF = Properties.of().mapColor(MapColor.STONE).strength(30, 3600000).requiresCorrectToolForDrops().sound(SoundType.STONE);
    public static final Properties GNEISS_BRICKS = Properties.of().mapColor(MapColor.STONE).strength(30, 3600000).requiresCorrectToolForDrops().sound(SoundType.STONE);
    public static final Properties GNEISS_SMOOTH = Properties.of().mapColor(MapColor.STONE).strength(30, 3600000).requiresCorrectToolForDrops().sound(SoundType.STONE);
    public static final Properties GNEISS_TILES = Properties.of().mapColor(MapColor.STONE).strength(30, 3600000).requiresCorrectToolForDrops().sound(SoundType.STONE);
    public static final Properties GNEISS_TILES_CRACKED = Properties.of().mapColor(MapColor.STONE).strength(30, 3600000).requiresCorrectToolForDrops().sound(SoundType.STONE);
    public static final Properties TEMPLE_BRICK = Properties.of().mapColor(MapColor.STONE).strength(2).requiresCorrectToolForDrops().sound(SoundType.STONE);
    public static final Properties TEMPLE_PILLAR = Properties.of().mapColor(MapColor.STONE).strength(2).requiresCorrectToolForDrops().sound(SoundType.STONE);
    public static final Properties TEMPLE_TILE = Properties.of().mapColor(MapColor.STONE).strength(2).requiresCorrectToolForDrops().sound(SoundType.STONE);
    public static final Properties SILK = Properties.ofFullCopy(Blocks.WHITE_WOOL).strength(0.2F);
    public static final Properties GIANT_LILY_PAD = Properties.of().mapColor(MapColor.WOOD)
            .instrument(NoteBlockInstrument.BASS).strength(5.0F).sound(SoundType.GRASS).ignitedByLava();
    public static final Properties REIN_EXO = Properties.of().mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM).strength(1.5F, 1200).requiresCorrectToolForDrops().sound(SoundType.STONE);
    public static final Properties VELOCITY_BLOCK = Properties.of().mapColor(MapColor.STONE).strength(1.5F, 6)
            .requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE);
    public static final Properties VELOCITY_BLOCK_LIGHTNING_SPEED = Properties.of().mapColor(MapColor.STONE).strength(1.5F, 6)
            .requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE);
    public static final Properties BLOCK_OF_BONES = Properties.ofFullCopy(Blocks.BONE_BLOCK).noOcclusion();
    public static final Properties ANTLION_EGG = Properties.of().mapColor(MapColor.STONE).strength(2.0F).requiresCorrectToolForDrops().noOcclusion();
    public static final Properties TARANTULA_EGG = Properties.of().mapColor(MapColor.STONE).strength(2.0F).requiresCorrectToolForDrops().noOcclusion();
    public static final Properties WASP_NEST = Properties.of().mapColor(MapColor.STONE).strength(50.0F, 1200.0F).requiresCorrectToolForDrops().noOcclusion();
    public static final Properties STAIRS_WASP_NEST = Properties.of().strength(50.0F, 1200.0F).requiresCorrectToolForDrops().noOcclusion();
    public static final Properties INSECT_REPELLENT = Properties.of().noCollision().noOcclusion().replaceable();
    // Spawners
    public static final Properties ANTLION_SPAWNER = Properties.ofFullCopy(Blocks.SPAWNER).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("antlion_spawner")));
    public static final Properties DRAGON_FLY_SPAWNER = Properties.ofFullCopy(Blocks.SPAWNER);
    public static final Properties JUMPING_SPIDER_SPAWNER = Properties.ofFullCopy(Blocks.SPAWNER).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("jumping_spider_spawner")));
    public static final Properties SPIDER_SPAWNER = Properties.ofFullCopy(Blocks.SPAWNER).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("spider_spawner")));
    public static final Properties TARANTULA_SPAWNER = Properties.ofFullCopy(Blocks.SPAWNER).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("tarantula_spawner")));
    public static final Properties WASP_SPAWNER = Properties.ofFullCopy(Blocks.SPAWNER).noOcclusion();
    public static final Properties ZOMBIE_ANT_SPAWNER = Properties.ofFullCopy(Blocks.SPAWNER).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("zombie_ant_spawner")));
    public static final Properties ZOMBIE_ANT_SOLDIER_SPAWNER = Properties.ofFullCopy(Blocks.SPAWNER).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("zombie_ant_soldier_spawner")));
    public static final Properties MAGMA_CRAWLER_SPAWNER = Properties.ofFullCopy(Blocks.SPAWNER).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("magma_crawler_spawner")));
    public static final Properties DUNG_SPAWNER_FLY = Properties.ofFullCopy(Blocks.SPAWNER).noOcclusion();
    public static final Properties DUNG_SPAWNER_BOT_FLY = Properties.ofFullCopy(Blocks.SPAWNER).noOcclusion();
    public static final Properties LOCUST_SPAWNER = Properties.ofFullCopy(Blocks.SPAWNER).noOcclusion();
    // Utility Blocks
    public static final Properties PETRIFIED_CRAFTING_TABLE = Properties.of()
            .mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .strength(2.5F)
            .sound(SoundType.STONE);
    public static final Properties BAMBOO_CRATE = Properties.of().mapColor(MapColor.COLOR_GREEN).instrument(NoteBlockInstrument.BASS).ignitedByLava().strength(2F).noOcclusion().sound(SoundType.WOOD);
    public static final Properties BAMBOO_BRIDGE = Properties.of().mapColor(MapColor.COLOR_GREEN).instrument(NoteBlockInstrument.BASS).ignitedByLava().strength(0.4F).noOcclusion().dynamicShape().sound(SoundType.LADDER);
    public static final Properties BAMBOO_LADDER = Properties.ofFullCopy(Blocks.LADDER).sound(SoundType.BAMBOO);
    public static final Properties BAMBOO_NERD_POLE = Properties.of().mapColor(MapColor.COLOR_GREEN).instrument(NoteBlockInstrument.BASS).ignitedByLava().strength(0.4F).noOcclusion().sound(SoundType.LADDER);
    public static final Properties BAMBOO_EXTENDER = Properties.of().mapColor(MapColor.COLOR_GREEN).instrument(NoteBlockInstrument.BASS).ignitedByLava().strength(0.4F).noOcclusion().sound(SoundType.LADDER);
    public static final Properties BAMBOO_TORCH = Properties.of().mapColor(MapColor.COLOR_GREEN).instrument(NoteBlockInstrument.BASS).ignitedByLava().noCollision().sound(SoundType.BAMBOO).lightLevel((_) -> 15);
    public static final Properties BAMBOO_PIPE = Properties.of().mapColor(MapColor.COLOR_GREEN).instrument(NoteBlockInstrument.BASS).ignitedByLava().strength(1.5F).noOcclusion().sound(SoundType.BAMBOO);
    public static final Properties BAMBOO_PIPE_EXTRACT = Properties.of().mapColor(MapColor.COLOR_GREEN).instrument(NoteBlockInstrument.BASS).ignitedByLava().strength(1.5F).noOcclusion().sound(SoundType.BAMBOO);
    public static final Properties SILO_ROOF = Properties.of().mapColor(MapColor.METAL).strength(3F).sound(SoundType.METAL).instrument(NoteBlockInstrument.BASS).ignitedByLava().noOcclusion();
    public static final Properties SILO_TANK = Properties.of().mapColor(MapColor.WOOD).strength(3F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion();
    public static final Properties SILO_SUPPORTS = Properties.of().mapColor(MapColor.WOOD).noCollision().strength(2F).sound(SoundType.WOOD).instrument(NoteBlockInstrument.BASS).ignitedByLava().noOcclusion();
    public static final Properties HONEY_COMB = Properties.of().mapColor(MapColor.COLOR_ORANGE)
            .instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F, 6F).requiresCorrectToolForDrops()
            .noOcclusion().lightLevel(_ -> 7).sound(SoundType.WOOL);
    public static final Properties COMPOSTER = Properties.of().mapColor(MapColor.COLOR_GREEN).strength(2F).sound(SoundType.WOOD).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().noOcclusion();
    public static final Properties BLENDER = Properties.of().mapColor(MapColor.STONE).noOcclusion()
            .instrument(NoteBlockInstrument.BASEDRUM).strength(2, 3).sound(SoundType.STONE).requiresCorrectToolForDrops();
    public static final Properties UMBER_FURNACE = Properties.ofFullCopy(Blocks.FURNACE);
    public static final Properties UMBERSTONE_BUTTON = Properties.ofFullCopy(Blocks.STONE_BUTTON).mapColor(MapColor.STONE);
    public static final Properties LIQUIFIER = Properties.ofFullCopy(Blocks.GLASS)
            .mapColor(MapColor.STONE)
            .strength(10.0F, 10.0F)
            .sound(SoundType.GLASS)
            .noOcclusion()
            .isViewBlocking((_, _, _) -> false);
    public static final Properties GLOW_GEM_ACTIVE = Properties.of().mapColor(MapColor.COLOR_YELLOW).noCollision().sound(SoundType.GLASS).lightLevel((_) -> 15);
    public static final Properties GLOW_GEM_INACTIVE = Properties.of().mapColor(MapColor.COLOR_RED).noCollision().sound(SoundType.GLASS).lightLevel((_) -> 0);
    public static final Properties MUCUS_BOMB = Properties.ofFullCopy(Blocks.TNT);
    public static final Properties UMBER_GOLEM_STATUE = Properties.of().mapColor(MapColor.STONE).strength(2).noOcclusion()
            .requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM);
    public static final Properties ALTAR = Properties.of().mapColor(MapColor.STONE).strength(2F).noOcclusion();
    // Antlion Dungeon
    public static final Properties CAPSTONE = capstoneProperties();
    public static final Properties CAPSTONE_MUD = capstoneProperties();
    public static final Properties CAPSTONE_IRON = capstoneProperties();
    public static final Properties CAPSTONE_GOLD = capstoneProperties();
    public static final Properties CAPSTONE_JADE = capstoneProperties();
    public static final Properties TEMPLE_BRICK_UNBREAKING = Properties.of().mapColor(MapColor.STONE).strength(-1.0F, Blocks.BEDROCK.getExplosionResistance());
    public static final Properties TEMPLE_BRICK_UNBREAKING_JADE = Properties.of().mapColor(MapColor.STONE).strength(-1.0F, Blocks.BEDROCK.getExplosionResistance());
    public static final Properties TEMPLE_BRICK_UNBREAKING_EXO = Properties.of().mapColor(MapColor.STONE).strength(-1.0F, Blocks.BEDROCK.getExplosionResistance());
    public static final Properties TEMPLE_BRICK_UNBREAKING_CREAM = Properties.of().mapColor(MapColor.STONE).strength(-1.0F, Blocks.BEDROCK.getExplosionResistance());
    public static final Properties TEMPLE_BRICK_UNBREAKING_EYE = Properties.of().mapColor(MapColor.STONE).strength(-1.0F, Blocks.BEDROCK.getExplosionResistance());
    public static final Properties TEMPLE_BRICK_UNBREAKING_STRING = Properties.of().mapColor(MapColor.STONE).strength(-1.0F, Blocks.BEDROCK.getExplosionResistance());
    public static final Properties TEMPLE_TELEPORTER = Properties.of().mapColor(MapColor.STONE).strength(-1.0F, Blocks.BEDROCK.getExplosionResistance());
    public static final Properties FORCE_FIELD = Properties.of().mapColor(MapColor.STONE).noOcclusion().strength(-1.0F, Blocks.BEDROCK.getExplosionResistance()).sound(SoundType.GLASS).lightLevel(_ -> 12);
    public static final Properties FORCE_LOCK = Properties.of().mapColor(MapColor.STONE).noOcclusion().strength(-1.0F, Blocks.BEDROCK.getExplosionResistance()).sound(SoundType.GLASS).lightLevel(_ -> 12);
    public static final Properties ANT_HILL_BLOCK = Properties.of().mapColor(MapColor.STONE)
            .strength(-1, Blocks.BEDROCK.getExplosionResistance()).sound(SoundType.STONE);
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
    private static final Properties MUSHROOM_PROPS = Properties.of()
            .noCollision()
            .randomTicks()
            .instabreak()
            .sound(SoundType.GRASS)
            .lightLevel((_) -> 1)
            .pushReaction(PushReaction.DESTROY)
            .offsetType(BlockBehaviour.OffsetType.XZ);
    public static final Properties DARK_CAPPED_MUSHROOM_PROPS = MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties DUTCH_CAP_MUSHROOM_PROPS = MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties GRANDMAS_SHOES_MUSHROOM_PROPS = MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties KAIZERS_FINGERS_MUSHROOM_PROPS = MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    public static final Properties SARCASTIC_CZECH_MUSHROOM_PROPS = MUSHROOM_PROPS.mapColor(MapColor.COLOR_BROWN);
    // MARK: Walls
    private static final Properties WALL_BASE = Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).strength(2);
    public static final Properties WALL_UMBERSTONE = WALL_BASE;
    public static final Properties WALL_UMBERCOBBLE = WALL_BASE;
    public static final Properties WALL_UMBERCOBBLE_MOSSY = WALL_BASE;
    public static final Properties WALL_UMBERCOBBLE_WEBBED = WALL_BASE;
    public static final Properties WALL_UMBERSTONE_BRICKS = WALL_BASE;
    public static final Properties WALL_UMBERTILE_SMOOTH = WALL_BASE;
    public static final Properties WALL_UMBERTILE_SMOOTH_SMALL = WALL_BASE;
    public static final Properties WALL_UMBERPAVER = WALL_BASE;
    public static final Properties WALL_UMBERPAVER_MOSSY = WALL_BASE;
    public static final Properties WALL_UMBERPAVER_WEBBED = WALL_BASE;

    private static Properties amberProperties(float hardness, float resistance) {
        // Each registration owns its mutable builder; preserved blocks must not alter walls or raw Amber.
        return Properties.of().noOcclusion().strength(hardness, resistance)
                .isViewBlocking((_, _, _) -> false).mapColor(MapColor.GOLD)
                .instrument(NoteBlockInstrument.HAT).sound(SoundType.GLASS);
    }

    // MARK: Doors
    private static Properties doorProperties() {
        return Properties.of()
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0F)
                .noOcclusion()
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY);
    }

    // MARK: Fences
    private static Properties fenceProperties() {
        return Properties.of()
                .forceSolidOn()
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F, 3.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava();
    }

    private static Properties fenceGateProperties() {
        return Properties.of()
                .forceSolidOn()
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F, 3.0F)
                .ignitedByLava();
    }

    private static Properties hugeMushroomProperties() {
        return Properties.of()
                .instrument(NoteBlockInstrument.BASS)
                .strength(0.2F)
                .sound(SoundType.WOOD)
                .ignitedByLava();
    }

    private static Properties flowerProperties() {
        return Properties.of()
                .noCollision()
                .noOcclusion()
                .instabreak()
                .sound(SoundType.AZALEA);
    }

    // MARK: Slabs
    public static Properties woodSlabProperties() {
        return Properties.ofFullCopy(Blocks.OAK_SLAB).strength(2);
    }

    // MARK: Stairs
    private static Properties woodStairProperties() {
        return Properties.of()
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F, 3.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava();
    }

    private static Properties stoneStairProperties(float hardness, float resistance) {
        return Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops().strength(hardness, resistance).sound(SoundType.STONE);
    }

    private static Properties petrifiedRockProperties() {
        return Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops().strength(5, 6).sound(SoundType.STONE);
    }

    public static Properties planksProperties() {
        return Properties.of()
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F, 3.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava();
    }

    private static Properties petrifiedWoodProperties() {
        return Properties.of().mapColor(MapColor.TERRACOTTA_BROWN)
                .instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD);
    }

    public static Properties chestProperties() {
        return Properties.of()
                .mapColor(MapColor.WOOD)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.5F)
                .sound(SoundType.WOOD)
                .ignitedByLava();
    }

    public static Properties log(MapColor top, MapColor side) {
        return Properties.of()
                .mapColor(p_152624_ -> p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : side)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava();
    }

    public static Properties honeyTreatProperties() {
        return Properties.of().forceSolidOn().strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY);
    }

    private static Properties capstoneProperties() {
        return Properties.of().mapColor(MapColor.STONE).strength(-1, Blocks.BEDROCK.getExplosionResistance()).sound(SoundType.STONE);
    }
}
