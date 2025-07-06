package erebus.registries.blocks.providers;

import org.jetbrains.annotations.NotNull;

import erebus.block.BlenderBlock;
import erebus.block.BlockOfBonesBlock;
import erebus.block.BotFlySpawnerBlock;
import erebus.block.CandleHoneyTreatBlock;
import erebus.block.ComposterBlock;
import erebus.block.GaeanKeystoneBlock;
import erebus.block.HoneyCombBlock;
import erebus.block.HoneyTreatBlock;
import erebus.block.LavaWebBlock;
import erebus.block.LiquifierBlock;
import erebus.block.PetrifiedCraftingTableBlock;
import erebus.block.QuicksandBlock;
import erebus.block.SiloTankBlock;
import erebus.block.SwampVentBlock;
import erebus.block.UmberFurnaceBlock;
import erebus.block.WitherWebBlock;
import erebus.block.altars.AltarBase;
import erebus.block.altars.ExperienceAltar;
import erebus.block.altars.HealingAltar;
import erebus.block.altars.LightningAltar;
import erebus.block.altars.OfferingAltar;
import erebus.block.altars.RepairAltar;
import erebus.block.bamboo.BambooBridge;
import erebus.block.bamboo.BambooCrateBlock;
import erebus.block.bamboo.BambooExtender;
import erebus.block.bamboo.BambooPipe;
import erebus.block.bamboo.BambooPipeExtract;
import erebus.block.bamboo.BambooPole;
import erebus.block.bamboo.BambooTorchBlock;
import erebus.block.portal.ErebusPortalBlock;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.MudBlock;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.registries.DeferredBlock;

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
    public static final DeferredBlock<RotatedPillarBlock> VELOCITY;
    public static final DeferredBlock<RotatedPillarBlock> LIGHTNING_SPEED;
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
    public static final DeferredBlock<Block> GLOW_GEM;
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
        PORTAL = registerBlock("portal", () -> new ErebusPortalBlock(Properties.ofFullCopy(Blocks.NETHER_PORTAL)));
        GAEAN_KEYSTONE = registerBlock("gaean_keystone", () -> new GaeanKeystoneBlock(Properties.ofFullCopy(Blocks.END_PORTAL_FRAME)));
        JADE_BLOCK = registerSimpleBlock("jade_block", Properties.of().mapColor(MapColor.STONE));
        MUD = registerBlock("mud", () -> new MudBlock(Properties.ofFullCopy(Blocks.MUD)) {
            @Override
            public @NotNull TriState canSustainPlant(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos soilPosition, @NotNull Direction facing, @NotNull BlockState plant) {
                return TriState.TRUE;
            }
        });
        QUICK_SAND = registerBlock("quick_sand", () -> new QuicksandBlock(Properties.of().strength(28F).sound(SoundType.SAND).mapColor(MapColor.SAND).noCollission()));
        GHOST_SAND = registerSimpleBlock("ghost_sand", Properties.of().mapColor(MapColor.STONE));
        SWAMP_VENT = registerBlock("swamp_vent", () -> new SwampVentBlock(Properties.ofFullCopy(Blocks.GRASS_BLOCK)));
        GNEISS_VENT = registerSimpleBlock("gneiss_vent", Properties.of().mapColor(MapColor.STONE));
        RED_GEM_BLOCK = registerSimpleBlock("red_gem_block", Properties.of().mapColor(MapColor.STONE));
        RED_GEM_LAMP = registerBlock("red_gem_lamp", () -> new RedstoneLampBlock(Properties.ofFullCopy(Blocks.REDSTONE_LAMP)));
        WITHER_WEB = registerBlock("wither_web", () -> new WitherWebBlock(Properties.of().mapColor(MapColor.WOOL).sound(SoundType.COBWEB).forceSolidOn().noCollission().requiresCorrectToolForDrops().strength(4.0F).pushReaction(PushReaction.DESTROY)));
        LAVA_WEB = registerBlock("lava_web", () -> new LavaWebBlock(Properties.of().mapColor(MapColor.WOOL).sound(SoundType.COBWEB).forceSolidOn().noCollission().requiresCorrectToolForDrops().strength(4.0F).pushReaction(PushReaction.DESTROY)));
        GNEISS = registerSimpleBlock("gneiss", Properties.of().mapColor(MapColor.STONE));
        GNEISS_CARVED = registerSimpleBlock("gneiss_carved", Properties.of().mapColor(MapColor.STONE));
        GNEISS_RELIEF = registerSimpleBlock("gneiss_relief", Properties.of().mapColor(MapColor.STONE));
        GNEISS_BRICKS = registerSimpleBlock("gneiss_bricks", Properties.of().mapColor(MapColor.STONE));
        GNEISS_SMOOTH = registerSimpleBlock("gneiss_smooth", Properties.of().mapColor(MapColor.STONE));
        GNEISS_TILES = registerSimpleBlock("gneiss_tiles", Properties.of().mapColor(MapColor.STONE));
        GNEISS_TILES_CRACKED = registerSimpleBlock("gneiss_tiles_cracked", Properties.of().mapColor(MapColor.STONE));
        TEMPLE_BRICK = registerSimpleBlock("temple_brick", Properties.of().mapColor(MapColor.STONE));
        TEMPLE_PILLAR = registerSimpleBlock("temple_pillar", Properties.of().mapColor(MapColor.STONE));
        TEMPLE_TILE = registerSimpleBlock("temple_tile", Properties.of().mapColor(MapColor.STONE));
        SILK = registerSimpleBlock("silk", Properties.of().mapColor(MapColor.STONE));
        REIN_EXO = registerSimpleBlock("rein_exo", Properties.of().mapColor(MapColor.STONE));
        VELOCITY = registerBlock("velocity", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.STONE)));
        LIGHTNING_SPEED = registerBlock("lightning_speed", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.STONE)));
        BLOCK_OF_BONES = registerBlock("block_of_bones", () -> new BlockOfBonesBlock(Properties.ofFullCopy(Blocks.BONE_BLOCK).noCollission()));
        ANTLION_EGG = registerSimpleBlock("antlion_egg", Properties.of().mapColor(MapColor.STONE).noOcclusion());
        TARANTULA_EGG = registerSimpleBlock("tarantula_egg", Properties.of().mapColor(MapColor.STONE).noOcclusion());
        HONEY_TREAT = registerBlock("honey_treat", () -> new HoneyTreatBlock(Properties.of().forceSolidOn().strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY)));
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
        WASP_NEST = registerSimpleBlock("wasp_nest", Properties.of().mapColor(MapColor.STONE));
        STAIRS_WASP_NEST = registerBlock(
                "stairs_wasp_nest",
                () -> new StairBlock(
                        WASP_NEST.get().defaultBlockState(),
                        Properties.of().strength(2.0F)
                )
        );
        INSECT_REPELLENT = registerSimpleBlock("insect_repellent", Properties.of().mapColor(MapColor.STONE));

        // MARK: Spawners
        ANTLION_SPAWNER = registerSimpleBlock("antlion_spawner", Properties.of().mapColor(MapColor.STONE));
        DRAGON_FLY_SPAWNER = registerSimpleBlock("dragon_fly_spawner", Properties.of().mapColor(MapColor.STONE));
        JUMPING_SPIDER_SPAWNER = registerSimpleBlock("jumping_spider_spawner", Properties.of().mapColor(MapColor.STONE));
        SPIDER_SPAWNER = registerSimpleBlock("spider_spawner", Properties.of().mapColor(MapColor.STONE));
        TARANTULA_SPAWNER = registerSimpleBlock("tarantula_spawner", Properties.of().mapColor(MapColor.STONE));
        WASP_SPAWNER = registerSimpleBlock("wasp_spawner", Properties.of().mapColor(MapColor.STONE));
        ZOMBIE_ANT_SPAWNER = registerSimpleBlock("zombie_ant_spawner", Properties.of().mapColor(MapColor.STONE));
        ZOMBIE_ANT_SOLDIER_SPAWNER = registerSimpleBlock("zombie_ant_soldier_spawner", Properties.of().mapColor(MapColor.STONE));
        MAGMA_CRAWLER_SPAWNER = registerSimpleBlock("magma_crawler_spawner", Properties.of().mapColor(MapColor.STONE));
        DUNG_SPAWNER_FLY = registerSimpleBlock("dung_spawner_fly", Properties.of().mapColor(MapColor.STONE));
        DUNG_SPAWNER_BOT_FLY = registerBlock("dung_spawner_bot_fly", () -> new BotFlySpawnerBlock(Properties.of().mapColor(MapColor.STONE).noOcclusion()));
        LOCUST_SPAWNER = registerSimpleBlock("locust_spawner", Properties.of().mapColor(MapColor.STONE));

        // MARK: Utility Blocks
        PETRIFIED_CRAFTING_TABLE = registerBlock("petrified_crafting_table", () -> new PetrifiedCraftingTableBlock(Properties.ofFullCopy(Blocks.CRAFTING_TABLE)));
        PETRIFIED_WOOD_CHEST = registerSimpleBlock("petrified_wood_chest", Properties.of().mapColor(MapColor.STONE));
        BAMBOO_CRATE = registerBlockWithoutBlockItem("bamboo_crate", () -> new BambooCrateBlock(Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.4F).noOcclusion().sound(SoundType.LADDER)));
        BAMBOO_BRIDGE = registerBlock("bamboo_bridge", () -> new BambooBridge(Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.4F).noOcclusion().sound(SoundType.LADDER)));
        BAMBOO_LADDER = registerBlock("bamboo_ladder", () -> new LadderBlock(Properties.ofFullCopy(Blocks.LADDER).sound(SoundType.BAMBOO)));
        BAMBOO_NERD_POLE = registerBlock("bamboo_nerd_pole", () -> new BambooPole(Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.4F).noOcclusion().sound(SoundType.LADDER)));
        BAMBOO_EXTENDER = registerBlock("bamboo_extender", () -> new BambooExtender(Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.4F).noOcclusion().sound(SoundType.LADDER)));
        BAMBOO_TORCH = registerBlock("bamboo_torch", () -> new BambooTorchBlock(Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().sound(SoundType.BAMBOO).lightLevel((state) -> 15)));
        BAMBOO_PIPE = registerBlock("bamboo_pipe", () -> new BambooPipe(Properties.of().mapColor(MapColor.COLOR_GREEN).strength(1.5F).noOcclusion().sound(SoundType.BAMBOO)));
        BAMBOO_PIPE_EXTRACT = registerBlock("bamboo_pipe_extract", () -> new BambooPipeExtract(Properties.of().mapColor(MapColor.COLOR_GREEN).strength(1.5F).noOcclusion().sound(SoundType.BAMBOO)));
        SILO_ROOF = registerSimpleBlock("silo_roof", Properties.of().mapColor(MapColor.STONE));
        SILO_TANK = registerBlock("silo_tank", () -> new SiloTankBlock(Properties.of().mapColor(MapColor.WOOD).strength(3F, 10F).sound(SoundType.METAL).noOcclusion()));
        SILO_SUPPORTS = registerSimpleBlock("silo_supports", Properties.of().mapColor(MapColor.STONE));
        HONEY_COMB = registerBlock("honey_comb", () -> new HoneyCombBlock(Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(0.5F, 10F).lightLevel(value -> 1).sound(SoundType.WOOL)));
        COMPOSTER = registerBlock("composter", () -> new ComposterBlock(Properties.of().mapColor(MapColor.COLOR_GREEN).strength(2F, 10F).sound(SoundType.WOOD).noOcclusion()));
        BLENDER = registerBlock("blender", () -> new BlenderBlock(Properties.of().mapColor(MapColor.STONE).noOcclusion()));
        UMBER_FURNACE = registerBlock("umber_furnace", () -> new UmberFurnaceBlock(Properties.ofFullCopy(Blocks.FURNACE)));
        UMBERSTONE_BUTTON = registerBlock("umberstone_button", () -> new ButtonBlock(BlockSetType.STONE, 10, Properties.of().mapColor(MapColor.STONE)));
        LIQUIFIER = registerBlockWithoutBlockItem("liquifier", () -> new LiquifierBlock(Properties.ofFullCopy(Blocks.GLASS).mapColor(MapColor.STONE).strength(1.0F, 2000.0F).sound(SoundType.GLASS).noOcclusion().isViewBlocking((blockState, blockGetter, blockPos) -> false)));
        GLOW_GEM = registerSimpleBlock("glow_gem", Properties.of().mapColor(MapColor.STONE));
        MUCUS_BOMB = registerSimpleBlock("mucus_bomb", Properties.of().mapColor(MapColor.STONE));
        UMBER_GOLEM_STATUE = registerSimpleBlock("umber_golem_statue", Properties.of().mapColor(MapColor.STONE));

        ALTAR_BASE = registerBlock("altar_base", () -> new AltarBase(Properties.of().mapColor(MapColor.STONE).strength(2F).noOcclusion()));
        ALTAR_LIGHTNING = registerBlock("altar_lightning", () -> new LightningAltar(Properties.of().mapColor(MapColor.STONE).strength(2F).noOcclusion()));
        ALTAR_HEALING = registerBlock("altar_healing", () -> new HealingAltar(Properties.of().mapColor(MapColor.STONE).strength(2F).noOcclusion()));
        ALTAR_EXPERIENCE = registerBlock("altar_experience", () -> new ExperienceAltar(Properties.of().mapColor(MapColor.STONE).strength(2F).noOcclusion()));
        ALTAR_REPAIR = registerBlock("altar_repair", () -> new RepairAltar(Properties.of().mapColor(MapColor.STONE).strength(2F).noOcclusion()));
        OFFERING_ALTAR = registerBlock("offering_altar", () -> new OfferingAltar(Properties.of().mapColor(MapColor.STONE).strength(2F).noOcclusion()));
        CAPSTONE = registerSimpleBlock("capstone", Properties.of().mapColor(MapColor.STONE));
        CAPSTONE_MUD = registerSimpleBlock("capstone_mud", Properties.of().mapColor(MapColor.STONE));
        CAPSTONE_IRON = registerSimpleBlock("capstone_iron", Properties.of().mapColor(MapColor.STONE));
        CAPSTONE_GOLD = registerSimpleBlock("capstone_gold", Properties.of().mapColor(MapColor.STONE));
        CAPSTONE_JADE = registerSimpleBlock("capstone_jade", Properties.of().mapColor(MapColor.STONE));
        TEMPLE_BRICK_UNBREAKING = registerSimpleBlock("temple_brick_unbreaking", Properties.of().mapColor(MapColor.STONE));
        TEMPLE_BRICK_UNBREAKING_JADE = registerSimpleBlock("temple_brick_unbreaking_jade", Properties.of().mapColor(MapColor.STONE));
        TEMPLE_BRICK_UNBREAKING_EXO = registerSimpleBlock("temple_brick_unbreaking_exo", Properties.of().mapColor(MapColor.STONE));
        TEMPLE_BRICK_UNBREAKING_CREAM = registerSimpleBlock("temple_brick_unbreaking_cream", Properties.of().mapColor(MapColor.STONE));
        TEMPLE_BRICK_UNBREAKING_EYE = registerSimpleBlock("temple_brick_unbreaking_eye", Properties.of().mapColor(MapColor.STONE));
        TEMPLE_BRICK_UNBREAKING_STRING = registerSimpleBlock("temple_brick_unbreaking_string", Properties.of().mapColor(MapColor.STONE));
        TEMPLE_TELEPORTER = registerSimpleBlock("temple_teleporter", Properties.of().mapColor(MapColor.STONE));
        FORCE_FIELD = registerSimpleBlock("force_field", Properties.of().mapColor(MapColor.STONE));
        FORCE_LOCK = registerSimpleBlock("force_lock", Properties.of().mapColor(MapColor.STONE));
        ANT_HILL_BLOCK = registerSimpleBlock("ant_hill_block", Properties.of().mapColor(MapColor.STONE));
    }

    public static void init() {
    }
}
