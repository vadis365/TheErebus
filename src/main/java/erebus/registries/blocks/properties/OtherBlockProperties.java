package erebus.registries.blocks.properties;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class OtherBlockProperties {

    // MARK: Other
    public static final Properties PORTAL;
    public static final Properties GAEAN_KEYSTONE;
    public static final Properties JADE_BLOCK;
    public static final Properties MUD;
    public static final Properties QUICK_SAND;
    public static final Properties GHOST_SAND;
    public static final Properties SWAMP_VENT;
    public static final Properties GNEISS_VENT;
    public static final Properties RED_GEM_BLOCK;
    public static final Properties RED_GEM_LAMP;
    public static final Properties WITHER_WEB;
    public static final Properties LAVA_WEB;
    public static final Properties GNEISS;
    public static final Properties GNEISS_CARVED;
    public static final Properties GNEISS_RELIEF;
    public static final Properties GNEISS_BRICKS;
    public static final Properties GNEISS_SMOOTH;
    public static final Properties GNEISS_TILES;
    public static final Properties GNEISS_TILES_CRACKED;
    public static final Properties TEMPLE_BRICK;
    public static final Properties TEMPLE_PILLAR;
    public static final Properties TEMPLE_TILE;
    public static final Properties SILK;
    public static final Properties REIN_EXO;
    public static final Properties VELOCITY_BLOCK;
    public static final Properties VELOCITY_BLOCK_LIGHTNING_SPEED;
    public static final Properties BLOCK_OF_BONES;
    public static final Properties ANTLION_EGG;
    public static final Properties TARANTULA_EGG;
    public static final Properties HONEY_TREAT;
    public static final Properties WASP_NEST;
    public static final Properties STAIRS_WASP_NEST;
    public static final Properties INSECT_REPELLENT;

    // MARK: Spawners
    public static final Properties ANTLION_SPAWNER;
    public static final Properties DRAGON_FLY_SPAWNER;
    public static final Properties JUMPING_SPIDER_SPAWNER;
    public static final Properties SPIDER_SPAWNER;
    public static final Properties TARANTULA_SPAWNER;
    public static final Properties WASP_SPAWNER;
    public static final Properties ZOMBIE_ANT_SPAWNER;
    public static final Properties ZOMBIE_ANT_SOLDIER_SPAWNER;
    public static final Properties MAGMA_CRAWLER_SPAWNER;
    public static final Properties DUNG_SPAWNER_FLY;
    public static final Properties DUNG_SPAWNER_BOT_FLY;
    public static final Properties LOCUST_SPAWNER;

    // MARK: Utility Blocks
    public static final Properties PETRIFIED_CRAFTING_TABLE;
    public static final Properties PETRIFIED_WOOD_CHEST;
    public static final Properties BAMBOO_CRATE;
    public static final Properties BAMBOO_BRIDGE;
    public static final Properties BAMBOO_LADDER;
    public static final Properties BAMBOO_NERD_POLE;
    public static final Properties BAMBOO_EXTENDER;
    public static final Properties BAMBOO_TORCH;
    public static final Properties BAMBOO_PIPE;
    public static final Properties BAMBOO_PIPE_EXTRACT;
    public static final Properties SILO_ROOF;
    public static final Properties SILO_TANK;
    public static final Properties SILO_SUPPORTS;
    public static final Properties HONEY_COMB;
    public static final Properties COMPOSTER;
    public static final Properties BLENDER;
    public static final Properties UMBER_FURNACE;
    public static final Properties UMBERSTONE_BUTTON;
    public static final Properties LIQUIFIER;
    public static final Properties GLOW_GEM_ACTIVE;
    public static final Properties GLOW_GEM_INACTIVE;
    public static final Properties MUCUS_BOMB;
    public static final Properties UMBER_GOLEM_STATUE;

    // Altars
    public static final Properties ALTAR;

    // MARK: Antlion Dungeon
    public static final Properties CAPSTONE;
    public static final Properties CAPSTONE_MUD;
    public static final Properties CAPSTONE_IRON;
    public static final Properties CAPSTONE_GOLD;
    public static final Properties CAPSTONE_JADE;
    public static final Properties TEMPLE_BRICK_UNBREAKING;
    public static final Properties TEMPLE_BRICK_UNBREAKING_JADE;
    public static final Properties TEMPLE_BRICK_UNBREAKING_EXO;
    public static final Properties TEMPLE_BRICK_UNBREAKING_CREAM;
    public static final Properties TEMPLE_BRICK_UNBREAKING_EYE;
    public static final Properties TEMPLE_BRICK_UNBREAKING_STRING;
    public static final Properties TEMPLE_TELEPORTER;
    public static final Properties FORCE_FIELD;
    public static final Properties FORCE_LOCK;
    public static final Properties ANT_HILL_BLOCK;

    static {
        PORTAL = Properties.ofFullCopy(Blocks.NETHER_PORTAL);
        GAEAN_KEYSTONE = Properties.of()
                .strength(3.0F)
                .sound(SoundType.STONE)
                .mapColor(MapColor.STONE);
        JADE_BLOCK = Properties.of()
                .strength(5.0F, 10.0F)
                .sound(SoundType.STONE)
                .mapColor(DyeColor.GREEN);

        MUD = Properties.ofFullCopy(Blocks.MUD);
        QUICK_SAND = Properties.of().strength(28F).sound(SoundType.SAND).mapColor(MapColor.SAND).noCollission();
        GHOST_SAND = Properties.of().mapColor(MapColor.STONE);
        SWAMP_VENT = Properties.ofFullCopy(Blocks.GRASS_BLOCK);
        GNEISS_VENT = Properties.of().mapColor(MapColor.STONE);
        RED_GEM_BLOCK = Properties.of().mapColor(MapColor.STONE);
        RED_GEM_LAMP = Properties.ofFullCopy(Blocks.REDSTONE_LAMP);
        WITHER_WEB = Properties.of().mapColor(MapColor.WOOL).sound(SoundType.COBWEB).forceSolidOn().noCollission().requiresCorrectToolForDrops().strength(4.0F).pushReaction(PushReaction.DESTROY);
        LAVA_WEB = Properties.of().mapColor(MapColor.WOOL).sound(SoundType.COBWEB).forceSolidOn().noCollission().requiresCorrectToolForDrops().strength(4.0F).pushReaction(PushReaction.DESTROY);
        GNEISS = Properties.of().mapColor(MapColor.STONE);
        GNEISS_CARVED = Properties.of().mapColor(MapColor.STONE);
        GNEISS_RELIEF = Properties.of().mapColor(MapColor.STONE);
        GNEISS_BRICKS = Properties.of().mapColor(MapColor.STONE);
        GNEISS_SMOOTH = Properties.of().mapColor(MapColor.STONE);
        GNEISS_TILES = Properties.of().mapColor(MapColor.STONE);
        GNEISS_TILES_CRACKED = Properties.of().mapColor(MapColor.STONE);
        TEMPLE_BRICK = Properties.of().mapColor(MapColor.STONE);
        TEMPLE_PILLAR = Properties.of().mapColor(MapColor.STONE);
        TEMPLE_TILE = Properties.of().mapColor(MapColor.STONE);
        SILK = Properties.of().mapColor(MapColor.STONE);
        REIN_EXO = Properties.of().mapColor(MapColor.STONE);
        VELOCITY_BLOCK = Properties.of().mapColor(MapColor.STONE).strength(1.5F).explosionResistance(10F).sound(SoundType.STONE);
        VELOCITY_BLOCK_LIGHTNING_SPEED = Properties.of().mapColor(MapColor.STONE).strength(1.5F).explosionResistance(10F).sound(SoundType.STONE);
        BLOCK_OF_BONES = Properties.ofFullCopy(Blocks.BONE_BLOCK).noCollission();
        ANTLION_EGG = Properties.of().mapColor(MapColor.STONE).noOcclusion();
        TARANTULA_EGG = Properties.of().mapColor(MapColor.STONE).noOcclusion();
        HONEY_TREAT = Properties.of().forceSolidOn().strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY);
        WASP_NEST = Properties.of().mapColor(MapColor.STONE);
        STAIRS_WASP_NEST = Properties.of().strength(2.0F);
        INSECT_REPELLENT = Properties.of().noCollission().noOcclusion().replaceable();

        // Spawners
        ANTLION_SPAWNER = Properties.of().mapColor(MapColor.STONE);
        DRAGON_FLY_SPAWNER = Properties.of().mapColor(MapColor.STONE);
        JUMPING_SPIDER_SPAWNER = Properties.of().mapColor(MapColor.STONE);
        SPIDER_SPAWNER = Properties.of().mapColor(MapColor.STONE);
        TARANTULA_SPAWNER = Properties.of().mapColor(MapColor.STONE);
        WASP_SPAWNER = Properties.of().mapColor(MapColor.STONE);
        ZOMBIE_ANT_SPAWNER = Properties.of().mapColor(MapColor.STONE);
        ZOMBIE_ANT_SOLDIER_SPAWNER = Properties.of().mapColor(MapColor.STONE);
        MAGMA_CRAWLER_SPAWNER = Properties.of().mapColor(MapColor.STONE);
        DUNG_SPAWNER_FLY = Properties.of().mapColor(MapColor.STONE);
        DUNG_SPAWNER_BOT_FLY = Properties.of().mapColor(MapColor.STONE).noOcclusion();
        LOCUST_SPAWNER = Properties.of().mapColor(MapColor.STONE);

        // Utility Blocks
        PETRIFIED_CRAFTING_TABLE = Properties.ofFullCopy(Blocks.CRAFTING_TABLE);
        PETRIFIED_WOOD_CHEST = Properties.ofFullCopy(Blocks.CHEST);
        BAMBOO_CRATE = Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.4F).noOcclusion().sound(SoundType.LADDER);
        BAMBOO_BRIDGE = Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.4F).noOcclusion().sound(SoundType.LADDER);
        BAMBOO_LADDER = Properties.ofFullCopy(Blocks.LADDER).sound(SoundType.BAMBOO);
        BAMBOO_NERD_POLE = Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.4F).noOcclusion().sound(SoundType.LADDER);
        BAMBOO_EXTENDER = Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.4F).noOcclusion().sound(SoundType.LADDER);
        BAMBOO_TORCH = Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().sound(SoundType.BAMBOO).lightLevel((state) -> 15);
        BAMBOO_PIPE = Properties.of().mapColor(MapColor.COLOR_GREEN).strength(1.5F).noOcclusion().sound(SoundType.BAMBOO);
        BAMBOO_PIPE_EXTRACT = Properties.of().mapColor(MapColor.COLOR_GREEN).strength(1.5F).noOcclusion().sound(SoundType.BAMBOO);
        SILO_ROOF = Properties.of().mapColor(MapColor.METAL).strength(3F, 10F).sound(SoundType.METAL).noOcclusion();
        SILO_TANK = Properties.of().mapColor(MapColor.WOOD).strength(3F, 10F).sound(SoundType.METAL).noOcclusion();
        SILO_SUPPORTS = Properties.of().mapColor(MapColor.WOOD).noCollission().strength(2F, 10F).sound(SoundType.WOOD).noOcclusion();
        HONEY_COMB = Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(0.5F, 10F).lightLevel(value -> 1).sound(SoundType.WOOL);
        COMPOSTER = Properties.of().mapColor(MapColor.COLOR_GREEN).strength(2F, 10F).sound(SoundType.WOOD).noOcclusion();
        BLENDER = Properties.of().mapColor(MapColor.STONE).noOcclusion();
        UMBER_FURNACE = Properties.ofFullCopy(Blocks.FURNACE);
        UMBERSTONE_BUTTON = Properties.of().mapColor(MapColor.STONE);
        LIQUIFIER = Properties.ofFullCopy(Blocks.GLASS).mapColor(MapColor.STONE).strength(1.0F, 2000.0F).sound(SoundType.GLASS).noOcclusion().isViewBlocking((blockState, blockGetter, blockPos) -> false);
        GLOW_GEM_ACTIVE = Properties.of().mapColor(MapColor.COLOR_YELLOW).noCollission().sound(SoundType.GLASS).lightLevel((state) -> 15);
        GLOW_GEM_INACTIVE = Properties.of().mapColor(MapColor.COLOR_RED).noCollission().sound(SoundType.GLASS).lightLevel((state) -> 0);
        MUCUS_BOMB = Properties.of().mapColor(MapColor.STONE);
        UMBER_GOLEM_STATUE = Properties.of().mapColor(MapColor.STONE);

        ALTAR = Properties.of().mapColor(MapColor.STONE).strength(2F).noOcclusion();

        // Antlion Dungeon
        CAPSTONE = Properties.of().mapColor(MapColor.STONE);
        CAPSTONE_MUD = Properties.of().mapColor(MapColor.STONE);
        CAPSTONE_IRON = Properties.of().mapColor(MapColor.STONE);
        CAPSTONE_GOLD = Properties.of().mapColor(MapColor.STONE);
        CAPSTONE_JADE = Properties.of().mapColor(MapColor.STONE);
        TEMPLE_BRICK_UNBREAKING = Properties.of().mapColor(MapColor.STONE);
        TEMPLE_BRICK_UNBREAKING_JADE = Properties.of().mapColor(MapColor.STONE);
        TEMPLE_BRICK_UNBREAKING_EXO = Properties.of().mapColor(MapColor.STONE);
        TEMPLE_BRICK_UNBREAKING_CREAM = Properties.of().mapColor(MapColor.STONE);
        TEMPLE_BRICK_UNBREAKING_EYE = Properties.of().mapColor(MapColor.STONE);
        TEMPLE_BRICK_UNBREAKING_STRING = Properties.of().mapColor(MapColor.STONE);
        TEMPLE_TELEPORTER = Properties.of().mapColor(MapColor.STONE);
        FORCE_FIELD = Properties.of().mapColor(MapColor.STONE);
        FORCE_LOCK = Properties.of().mapColor(MapColor.STONE);
        ANT_HILL_BLOCK = Properties.of().mapColor(MapColor.STONE);
    }
}
