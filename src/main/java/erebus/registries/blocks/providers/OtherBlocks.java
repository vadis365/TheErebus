package erebus.registries.blocks.providers;

import erebus.block.*;
import erebus.block.ComposterBlock;
import erebus.block.altars.*;
import erebus.block.bamboo.*;
import erebus.block.portal.ErebusPortalBlock;
import erebus.registries.blocks.properties.OtherBlockProperties;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;

public class OtherBlocks extends ModBlockHelpers {

    // MARK: Other
    public static final DeferredBlock<ErebusPortalBlock> PORTAL;
    public static final DeferredBlock<GaeanKeystoneBlock> GAEAN_KEYSTONE;
    public static final DeferredBlock<Block> JADE_BLOCK;
    public static final DeferredBlock<Block> MUD;
    public static final DeferredBlock<Block> QUICK_SAND;
    public static final DeferredBlock<Block> GHOST_SAND;
    public static final DeferredBlock<Block> SWAMP_VENT;
    public static final DeferredBlock<Block> GNEISS_VENT;
    public static final DeferredBlock<Block> RED_GEM_BLOCK;
    public static final DeferredBlock<Block> RED_GEM_LAMP;
    public static final DeferredBlock<WitherWebBlock> WITHER_WEB;
    public static final DeferredBlock<LavaWebBlock> LAVA_WEB;
    public static final DeferredBlock<Block> GNEISS;
    public static final DeferredBlock<Block> GNEISS_CARVED;
    public static final DeferredBlock<Block> GNEISS_RELIEF;
    public static final DeferredBlock<Block> GNEISS_BRICKS;
    public static final DeferredBlock<Block> GNEISS_SMOOTH;
    public static final DeferredBlock<Block> GNEISS_TILES;
    public static final DeferredBlock<Block> GNEISS_TILES_CRACKED;
    public static final DeferredBlock<Block> TEMPLE_BRICK;
    public static final DeferredBlock<Block> TEMPLE_PILLAR;
    public static final DeferredBlock<Block> TEMPLE_TILE;
    public static final DeferredBlock<Block> SILK;
    public static final DeferredBlock<Block> REIN_EXO;
    public static final DeferredBlock<VelocityBlock> VELOCITY_BLOCK;
    public static final DeferredBlock<VelocityBlockLightningSpeed> VELOCITY_BLOCK_LIGHTNING_SPEED;
    public static final DeferredBlock<BlockOfBonesBlock> BLOCK_OF_BONES;
    public static final DeferredBlock<Block> ANTLION_EGG;
    public static final DeferredBlock<Block> TARANTULA_EGG;
    public static final DeferredBlock<HoneyTreatBlock> HONEY_TREAT;
    public static final DeferredBlock<CandleHoneyTreatBlock> CANDLE_HONEY_TREAT;
    public static final DeferredBlock<CandleHoneyTreatBlock> WHITE_CANDLE_HONEY_TREAT;
    public static final DeferredBlock<CandleHoneyTreatBlock> ORANGE_CANDLE_HONEY_TREAT;
    public static final DeferredBlock<CandleHoneyTreatBlock> MAGENTA_CANDLE_HONEY_TREAT;
    public static final DeferredBlock<CandleHoneyTreatBlock> LIGHT_BLUE_CANDLE_HONEY_TREAT;
    public static final DeferredBlock<CandleHoneyTreatBlock> YELLOW_CANDLE_HONEY_TREAT;
    public static final DeferredBlock<CandleHoneyTreatBlock> LIME_CANDLE_HONEY_TREAT;
    public static final DeferredBlock<CandleHoneyTreatBlock> PINK_CANDLE_HONEY_TREAT;
    public static final DeferredBlock<CandleHoneyTreatBlock> GRAY_CANDLE_HONEY_TREAT;
    public static final DeferredBlock<CandleHoneyTreatBlock> LIGHT_GRAY_CANDLE_HONEY_TREAT;
    public static final DeferredBlock<CandleHoneyTreatBlock> CYAN_CANDLE_HONEY_TREAT;
    public static final DeferredBlock<CandleHoneyTreatBlock> PURPLE_CANDLE_HONEY_TREAT;
    public static final DeferredBlock<CandleHoneyTreatBlock> BLUE_CANDLE_HONEY_TREAT;
    public static final DeferredBlock<CandleHoneyTreatBlock> BROWN_CANDLE_HONEY_TREAT;
    public static final DeferredBlock<CandleHoneyTreatBlock> GREEN_CANDLE_HONEY_TREAT;
    public static final DeferredBlock<CandleHoneyTreatBlock> RED_CANDLE_HONEY_TREAT;
    public static final DeferredBlock<CandleHoneyTreatBlock> BLACK_CANDLE_HONEY_TREAT;
    public static final DeferredBlock<Block> WASP_NEST;
    public static final DeferredBlock<StairBlock> STAIRS_WASP_NEST;
    public static final DeferredBlock<Block> INSECT_REPELLENT;

    // MARK: Spawners
    public static final DeferredBlock<Block> ANTLION_SPAWNER;
    public static final DeferredBlock<Block> DRAGON_FLY_SPAWNER;
    public static final DeferredBlock<Block> JUMPING_SPIDER_SPAWNER;
    public static final DeferredBlock<Block> SPIDER_SPAWNER;
    public static final DeferredBlock<Block> TARANTULA_SPAWNER;
    public static final DeferredBlock<Block> WASP_SPAWNER;
    public static final DeferredBlock<Block> ZOMBIE_ANT_SPAWNER;
    public static final DeferredBlock<Block> ZOMBIE_ANT_SOLDIER_SPAWNER;
    public static final DeferredBlock<Block> MAGMA_CRAWLER_SPAWNER;
    public static final DeferredBlock<Block> DUNG_SPAWNER_FLY;
    public static final DeferredBlock<Block> DUNG_SPAWNER_BOT_FLY;
    public static final DeferredBlock<Block> LOCUST_SPAWNER;

    // MARK: Utility Blocks
    public static final DeferredBlock<PetrifiedCraftingTableBlock> PETRIFIED_CRAFTING_TABLE;
    public static final DeferredBlock<Block> PETRIFIED_WOOD_CHEST;
    public static final DeferredBlock<BambooCrateBlock> BAMBOO_CRATE;
    public static final DeferredBlock<Block> BAMBOO_BRIDGE;
    public static final DeferredBlock<LadderBlock> BAMBOO_LADDER;
    public static final DeferredBlock<BambooPole> BAMBOO_NERD_POLE;
    public static final DeferredBlock<Block> BAMBOO_EXTENDER;
    public static final DeferredBlock<BambooTorchBlock> BAMBOO_TORCH;
    public static final DeferredBlock<Block> BAMBOO_PIPE;
    public static final DeferredBlock<Block> BAMBOO_PIPE_EXTRACT;
    public static final DeferredBlock<Block> SILO_ROOF;
    public static final DeferredBlock<Block> SILO_TANK;
    public static final DeferredBlock<Block> SILO_SUPPORTS;
    public static final DeferredBlock<HoneyCombBlock> HONEY_COMB;
    public static final DeferredBlock<ComposterBlock> COMPOSTER;
    public static final DeferredBlock<Block> BLENDER;
    public static final DeferredBlock<UmberFurnaceBlock> UMBER_FURNACE;
    public static final DeferredBlock<ButtonBlock> UMBERSTONE_BUTTON;
    public static final DeferredBlock<LiquifierBlock> LIQUIFIER;
    public static final DeferredBlock<GlowGemActiveBlock> GLOW_GEM_ACTIVE;
    public static final DeferredBlock<GlowGemInactiveBlock> GLOW_GEM_INACTIVE;
    public static final DeferredBlock<Block> MUCUS_BOMB;
    public static final DeferredBlock<Block> UMBER_GOLEM_STATUE;

    public static final DeferredBlock<AltarBase> ALTAR_BASE;
    public static final DeferredBlock<LightningAltar> ALTAR_LIGHTNING;
    public static final DeferredBlock<HealingAltar> ALTAR_HEALING;
    public static final DeferredBlock<ExperienceAltar> ALTAR_EXPERIENCE;
    public static final DeferredBlock<RepairAltar> ALTAR_REPAIR;
    public static final DeferredBlock<OfferingAltar> OFFERING_ALTAR;

    // MARK: Antlion Dungeon
    public static final DeferredBlock<Block> CAPSTONE;
    public static final DeferredBlock<Block> CAPSTONE_MUD;
    public static final DeferredBlock<Block> CAPSTONE_IRON;
    public static final DeferredBlock<Block> CAPSTONE_GOLD;
    public static final DeferredBlock<Block> CAPSTONE_JADE;
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING;
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING_JADE;
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING_EXO;
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING_CREAM;
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING_EYE;
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING_STRING;
    public static final DeferredBlock<Block> TEMPLE_TELEPORTER;
    public static final DeferredBlock<Block> FORCE_FIELD;
    public static final DeferredBlock<Block> FORCE_LOCK;

    public static final DeferredBlock<Block> ANT_HILL_BLOCK;

    static {
        PORTAL = registerBlock("portal", () -> new ErebusPortalBlock(OtherBlockProperties.PORTAL));
        GAEAN_KEYSTONE = registerBlock("gaean_keystone", () -> new GaeanKeystoneBlock(OtherBlockProperties.GAEAN_KEYSTONE));
        JADE_BLOCK = registerSimpleBlock("jade_block", OtherBlockProperties.JADE_BLOCK);
        MUD = registerBlock("mud", () -> new MudBlock(OtherBlockProperties.MUD) {
            @Override
            public @NotNull TriState canSustainPlant(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos soilPosition, @NotNull Direction facing, @NotNull BlockState plant) {
                return TriState.TRUE;
            }
        });
        QUICK_SAND = registerBlock("quick_sand", () -> new QuicksandBlock(OtherBlockProperties.QUICK_SAND));
        GHOST_SAND = registerSimpleBlock("ghost_sand", OtherBlockProperties.GHOST_SAND);
        SWAMP_VENT = registerBlock("swamp_vent", () -> new SwampVentBlock(OtherBlockProperties.SWAMP_VENT));
        GNEISS_VENT = registerSimpleBlock("gneiss_vent", OtherBlockProperties.GNEISS_VENT);
        RED_GEM_BLOCK = registerSimpleBlock("red_gem_block", OtherBlockProperties.RED_GEM_BLOCK);
        RED_GEM_LAMP = registerBlock("red_gem_lamp", () -> new RedstoneLampBlock(OtherBlockProperties.RED_GEM_LAMP));
        WITHER_WEB = registerBlock("wither_web", () -> new WitherWebBlock(OtherBlockProperties.WITHER_WEB));
        LAVA_WEB = registerBlock("lava_web", () -> new LavaWebBlock(OtherBlockProperties.LAVA_WEB));
        GNEISS = registerSimpleBlock("gneiss", OtherBlockProperties.GNEISS);
        GNEISS_CARVED = registerSimpleBlock("gneiss_carved", OtherBlockProperties.GNEISS_CARVED);
        GNEISS_RELIEF = registerSimpleBlock("gneiss_relief", OtherBlockProperties.GNEISS_RELIEF);
        GNEISS_BRICKS = registerSimpleBlock("gneiss_bricks", OtherBlockProperties.GNEISS_BRICKS);
        GNEISS_SMOOTH = registerSimpleBlock("gneiss_smooth", OtherBlockProperties.GNEISS_SMOOTH);
        GNEISS_TILES = registerSimpleBlock("gneiss_tiles", OtherBlockProperties.GNEISS_TILES);
        GNEISS_TILES_CRACKED = registerSimpleBlock("gneiss_tiles_cracked", OtherBlockProperties.GNEISS_TILES_CRACKED);
        TEMPLE_BRICK = registerSimpleBlock("temple_brick", OtherBlockProperties.TEMPLE_BRICK);
        TEMPLE_PILLAR = registerSimpleBlock("temple_pillar", OtherBlockProperties.TEMPLE_PILLAR);
        TEMPLE_TILE = registerSimpleBlock("temple_tile", OtherBlockProperties.TEMPLE_TILE);
        SILK = registerSimpleBlock("silk", OtherBlockProperties.SILK);
        REIN_EXO = registerSimpleBlock("rein_exo", OtherBlockProperties.REIN_EXO);
        VELOCITY_BLOCK = registerBlock("velocity_block", () -> new VelocityBlock(OtherBlockProperties.VELOCITY_BLOCK));
        VELOCITY_BLOCK_LIGHTNING_SPEED = registerBlock("velocity_block_lightning_speed", () -> new VelocityBlockLightningSpeed(OtherBlockProperties.VELOCITY_BLOCK_LIGHTNING_SPEED));
        BLOCK_OF_BONES = registerBlock("block_of_bones", () -> new BlockOfBonesBlock(OtherBlockProperties.BLOCK_OF_BONES));
        ANTLION_EGG = registerSimpleBlock("antlion_egg", OtherBlockProperties.ANTLION_EGG);
        TARANTULA_EGG = registerSimpleBlock("tarantula_egg", OtherBlockProperties.TARANTULA_EGG);
        HONEY_TREAT = registerBlock("honey_treat", () -> new HoneyTreatBlock(OtherBlockProperties.HONEY_TREAT));
        CANDLE_HONEY_TREAT = registerBlock("candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.CANDLE));
        WHITE_CANDLE_HONEY_TREAT = registerBlock("white_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.WHITE_CANDLE));
        ORANGE_CANDLE_HONEY_TREAT = registerBlock("orange_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.ORANGE_CANDLE));
        MAGENTA_CANDLE_HONEY_TREAT = registerBlock("magenta_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.MAGENTA_CANDLE));
        LIGHT_BLUE_CANDLE_HONEY_TREAT = registerBlock("light_blue_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.LIGHT_BLUE_CANDLE));
        YELLOW_CANDLE_HONEY_TREAT = registerBlock("yellow_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.YELLOW_CANDLE));
        LIME_CANDLE_HONEY_TREAT = registerBlock("lime_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.LIME_CANDLE));
        PINK_CANDLE_HONEY_TREAT = registerBlock("pink_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.PINK_CANDLE));
        GRAY_CANDLE_HONEY_TREAT = registerBlock("gray_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.GRAY_CANDLE));
        LIGHT_GRAY_CANDLE_HONEY_TREAT = registerBlock("light_gray_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.LIGHT_GRAY_CANDLE));
        CYAN_CANDLE_HONEY_TREAT = registerBlock("cyan_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.CYAN_CANDLE));
        PURPLE_CANDLE_HONEY_TREAT = registerBlock("purple_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.PURPLE_CANDLE));
        BLUE_CANDLE_HONEY_TREAT = registerBlock("blue_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.BLUE_CANDLE));
        BROWN_CANDLE_HONEY_TREAT = registerBlock("brown_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.BROWN_CANDLE));
        GREEN_CANDLE_HONEY_TREAT = registerBlock("green_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.GREEN_CANDLE));
        RED_CANDLE_HONEY_TREAT = registerBlock("red_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.RED_CANDLE));
        BLACK_CANDLE_HONEY_TREAT = registerBlock("black_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.BLACK_CANDLE));
        WASP_NEST = registerSimpleBlock("wasp_nest", OtherBlockProperties.WASP_NEST);
        STAIRS_WASP_NEST = registerBlock(
                "stairs_wasp_nest",
                () -> new StairBlock(
                        WASP_NEST.get().defaultBlockState(),
                        OtherBlockProperties.STAIRS_WASP_NEST
                )
        );
        INSECT_REPELLENT = registerBlock("insect_repellent", () -> new InsectRepellentBlock(OtherBlockProperties.INSECT_REPELLENT));

        // MARK: Spawners
        ANTLION_SPAWNER = registerSimpleBlock("antlion_spawner", OtherBlockProperties.ANTLION_SPAWNER);
        DRAGON_FLY_SPAWNER = registerSimpleBlock("dragon_fly_spawner", OtherBlockProperties.DRAGON_FLY_SPAWNER);
        JUMPING_SPIDER_SPAWNER = registerSimpleBlock("jumping_spider_spawner", OtherBlockProperties.JUMPING_SPIDER_SPAWNER);
        SPIDER_SPAWNER = registerSimpleBlock("spider_spawner", OtherBlockProperties.SPIDER_SPAWNER);
        TARANTULA_SPAWNER = registerSimpleBlock("tarantula_spawner", OtherBlockProperties.TARANTULA_SPAWNER);
        WASP_SPAWNER = registerSimpleBlock("wasp_spawner", OtherBlockProperties.WASP_SPAWNER);
        ZOMBIE_ANT_SPAWNER = registerSimpleBlock("zombie_ant_spawner", OtherBlockProperties.ZOMBIE_ANT_SPAWNER);
        ZOMBIE_ANT_SOLDIER_SPAWNER = registerSimpleBlock("zombie_ant_soldier_spawner", OtherBlockProperties.ZOMBIE_ANT_SOLDIER_SPAWNER);
        MAGMA_CRAWLER_SPAWNER = registerSimpleBlock("magma_crawler_spawner", OtherBlockProperties.MAGMA_CRAWLER_SPAWNER);
        DUNG_SPAWNER_FLY = registerSimpleBlock("dung_spawner_fly", OtherBlockProperties.DUNG_SPAWNER_FLY);
        DUNG_SPAWNER_BOT_FLY = registerBlock("dung_spawner_bot_fly", () -> new BotFlySpawnerBlock(OtherBlockProperties.DUNG_SPAWNER_BOT_FLY));
        LOCUST_SPAWNER = registerSimpleBlock("locust_spawner", OtherBlockProperties.LOCUST_SPAWNER);

        // MARK: Utility Blocks
        PETRIFIED_CRAFTING_TABLE = registerBlock("petrified_crafting_table", () -> new PetrifiedCraftingTableBlock(OtherBlockProperties.PETRIFIED_CRAFTING_TABLE));
        PETRIFIED_WOOD_CHEST = registerBlock("petrified_wood_chest", () -> new PetrifiedChestBlock(OtherBlockProperties.PETRIFIED_WOOD_CHEST));
        BAMBOO_CRATE = registerBlockWithoutBlockItem("bamboo_crate", () -> new BambooCrateBlock(OtherBlockProperties.BAMBOO_CRATE));
        BAMBOO_BRIDGE = registerBlock("bamboo_bridge", () -> new BambooBridge(OtherBlockProperties.BAMBOO_BRIDGE));
        BAMBOO_LADDER = registerBlock("bamboo_ladder", () -> new LadderBlock(OtherBlockProperties.BAMBOO_LADDER));
        BAMBOO_NERD_POLE = registerBlock("bamboo_nerd_pole", () -> new BambooPole(OtherBlockProperties.BAMBOO_NERD_POLE));
        BAMBOO_EXTENDER = registerBlock("bamboo_extender", () -> new BambooExtender(OtherBlockProperties.BAMBOO_EXTENDER));
        BAMBOO_TORCH = registerBlock("bamboo_torch", () -> new BambooTorchBlock(OtherBlockProperties.BAMBOO_TORCH));
        BAMBOO_PIPE = registerBlock("bamboo_pipe", () -> new BambooPipe(OtherBlockProperties.BAMBOO_PIPE));
        BAMBOO_PIPE_EXTRACT = registerBlock("bamboo_pipe_extract", () -> new BambooPipeExtract(OtherBlockProperties.BAMBOO_PIPE_EXTRACT));
        SILO_ROOF = registerBlock("silo_roof", () -> new SiloRoofBlock(OtherBlockProperties.SILO_ROOF));
        SILO_TANK = registerBlock("silo_tank", () -> new SiloTankBlock(OtherBlockProperties.SILO_TANK));
        SILO_SUPPORTS = registerBlock("silo_supports", () -> new SiloSupportsBlock(OtherBlockProperties.SILO_SUPPORTS));
        HONEY_COMB = registerBlock("honey_comb", () -> new HoneyCombBlock(OtherBlockProperties.HONEY_COMB));
        COMPOSTER = registerBlock("composter", () -> new ComposterBlock(OtherBlockProperties.COMPOSTER));
        BLENDER = registerBlock("blender", () -> new BlenderBlock(OtherBlockProperties.BLENDER));
        UMBER_FURNACE = registerBlock("umber_furnace", () -> new UmberFurnaceBlock(OtherBlockProperties.UMBER_FURNACE));
        UMBERSTONE_BUTTON = registerBlock("umberstone_button", () -> new ButtonBlock(BlockSetType.STONE, 10, OtherBlockProperties.UMBERSTONE_BUTTON));
        LIQUIFIER = registerBlockWithoutBlockItem("liquifier", () -> new LiquifierBlock(OtherBlockProperties.LIQUIFIER));
        GLOW_GEM_ACTIVE = registerBlock("glow_gem_active", () -> new GlowGemActiveBlock(OtherBlockProperties.GLOW_GEM_ACTIVE));
        GLOW_GEM_INACTIVE = registerBlock("glow_gem_inactive", () -> new GlowGemInactiveBlock(OtherBlockProperties.GLOW_GEM_INACTIVE));
        MUCUS_BOMB = registerSimpleBlock("mucus_bomb", OtherBlockProperties.MUCUS_BOMB);
        UMBER_GOLEM_STATUE = registerSimpleBlock("umber_golem_statue", OtherBlockProperties.UMBER_GOLEM_STATUE);

        ALTAR_BASE = registerBlock("altar_base", () -> new AltarBase(OtherBlockProperties.ALTAR));
        ALTAR_LIGHTNING = registerBlock("altar_lightning", () -> new LightningAltar(OtherBlockProperties.ALTAR));
        ALTAR_HEALING = registerBlock("altar_healing", () -> new HealingAltar(OtherBlockProperties.ALTAR));
        ALTAR_EXPERIENCE = registerBlock("altar_experience", () -> new ExperienceAltar(OtherBlockProperties.ALTAR));
        ALTAR_REPAIR = registerBlock("altar_repair", () -> new RepairAltar(OtherBlockProperties.ALTAR));
        OFFERING_ALTAR = registerBlock("offering_altar", () -> new OfferingAltar(OtherBlockProperties.ALTAR));
        CAPSTONE = registerSimpleBlock("capstone", OtherBlockProperties.CAPSTONE);
        CAPSTONE_MUD = registerSimpleBlock("capstone_mud", OtherBlockProperties.CAPSTONE_MUD);
        CAPSTONE_IRON = registerSimpleBlock("capstone_iron", OtherBlockProperties.CAPSTONE_IRON);
        CAPSTONE_GOLD = registerSimpleBlock("capstone_gold", OtherBlockProperties.CAPSTONE_GOLD);
        CAPSTONE_JADE = registerSimpleBlock("capstone_jade", OtherBlockProperties.CAPSTONE_JADE);
        TEMPLE_BRICK_UNBREAKING = registerSimpleBlock("temple_brick_unbreaking", OtherBlockProperties.TEMPLE_BRICK_UNBREAKING);
        TEMPLE_BRICK_UNBREAKING_JADE = registerSimpleBlock("temple_brick_unbreaking_jade", OtherBlockProperties.TEMPLE_BRICK_UNBREAKING_JADE);
        TEMPLE_BRICK_UNBREAKING_EXO = registerSimpleBlock("temple_brick_unbreaking_exo", OtherBlockProperties.TEMPLE_BRICK_UNBREAKING_EXO);
        TEMPLE_BRICK_UNBREAKING_CREAM = registerSimpleBlock("temple_brick_unbreaking_cream", OtherBlockProperties.TEMPLE_BRICK_UNBREAKING_CREAM);
        TEMPLE_BRICK_UNBREAKING_EYE = registerSimpleBlock("temple_brick_unbreaking_eye", OtherBlockProperties.TEMPLE_BRICK_UNBREAKING_EYE);
        TEMPLE_BRICK_UNBREAKING_STRING = registerSimpleBlock("temple_brick_unbreaking_string", OtherBlockProperties.TEMPLE_BRICK_UNBREAKING_STRING);
        TEMPLE_TELEPORTER = registerSimpleBlock("temple_teleporter", OtherBlockProperties.TEMPLE_TELEPORTER);
        FORCE_FIELD = registerSimpleBlock("force_field", OtherBlockProperties.FORCE_FIELD);
        FORCE_LOCK = registerSimpleBlock("force_lock", OtherBlockProperties.FORCE_LOCK);
        ANT_HILL_BLOCK = registerSimpleBlock("ant_hill_block", OtherBlockProperties.ANT_HILL_BLOCK);
    }

    public static void init() {
    }
}
