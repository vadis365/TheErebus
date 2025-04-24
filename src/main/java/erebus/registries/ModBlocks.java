package erebus.registries;

import erebus.Erebus;
import erebus.block.CandleHoneyTreatBlock;
import erebus.block.ConnectedTextureBlock;
import erebus.block.GaeanKeystoneBlock;
import erebus.block.HoneyTreatBlock;
import erebus.block.LavaWeb;
import erebus.block.ModBerryBushBlock;
import erebus.block.ModCropBlock;
import erebus.block.PetrifiedCraftingTableBlock;
import erebus.block.UmberFurnaceBlock;
import erebus.block.WitherWeb;
import erebus.block.altars.OfferingAltar;
import erebus.block.portal.ErebusPortalBlock;
import erebus.registries.helpers.ModBlockHelpers;
import erebus.registries.world.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.MudBlock;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks extends ModBlockHelpers {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Erebus.MODID);

    // MARK: Umberstone
    public static final DeferredBlock<Block> UMBERSTONE = registerSimpleBlock(
            "umberstone",
            BlockBehaviour.Properties.of()
                    .strength(1.5F, 10.0F)
                    .requiresCorrectToolForDrops()
    );
    public static final DeferredBlock<Block> UMBERSTONE_BRICKS = registerSimpleBlock("umberstone_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS));
    public static final DeferredBlock<Block> UMBERCOBBLE = registerSimpleBlock("umbercobble", BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE));
    public static final DeferredBlock<Block> UMBERCOBBLE_MOSSY = registerSimpleBlock("umbercobble_mossy", BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE));
    public static final DeferredBlock<Block> UMBERCOBBLE_WEBBED = registerSimpleBlock("umbercobble_webbed", BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE));
    public static final DeferredBlock<Block> UMBERTILE_SMOOTH = registerSimpleBlock("umbertile_smooth", BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE));
    public static final DeferredBlock<Block> UMBERTILE_SMOOTH_SMALL = registerSimpleBlock("umbertile_smooth_small", BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE));
    public static final DeferredBlock<Block> UMBERGRAVEL = registerSimpleBlock("umbergravel", BlockBehaviour.Properties.ofFullCopy(Blocks.GRAVEL));
    public static final DeferredBlock<Block> UMBERPAVER = registerSimpleBlock("umberpaver", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> UMBERPAVER_MOSSY = registerSimpleBlock("umberpaver_mossy", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> UMBERPAVER_WEBBED = registerSimpleBlock("umberpaver_webbed", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<RotatedPillarBlock> UMBERSTONE_PILLAR = registerBlock("umberstone_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> VOLCANIC_ROCK = registerSimpleBlock("volcanic_rock", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUST = registerSimpleBlock("dust", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUST_LAYER = registerBlock("dust_layer", () -> new SnowLayerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW)));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK = registerSimpleBlock("petrified_wood_rock", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_2 = registerSimpleBlock("petrified_wood_rock_2", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_3 = registerSimpleBlock("petrified_wood_rock_3", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_4 = registerSimpleBlock("petrified_wood_rock_4", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_5 = registerSimpleBlock("petrified_wood_rock_5", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_6 = registerSimpleBlock("petrified_wood_rock_6", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_BARK_RED = registerSimpleBlock("petrified_bark_red", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_BARK_BROWN = registerSimpleBlock("petrified_bark_brown", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETRIFIED_LOG_INNER = registerSimpleBlock("petrified_log_inner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUNG = registerSimpleBlock("dung", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> MIR_BRICKS = registerSimpleBlock("mir_bricks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> MUD_BRICKS = registerSimpleBlock("mud_bricks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    // MARK: Amber
    public static final DeferredBlock<TransparentBlock> AMBER = registerTransparentBlock("amber", BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(1.5F).noOcclusion().isViewBlocking((blockState, blockGetter, blockPos) -> false).sound(SoundType.GLASS).mapColor(MapColor.GOLD));
    public static final DeferredBlock<TransparentBlock> AMBER_BRICKS = registerTransparentBlock("amber_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(2.5F).noOcclusion().isViewBlocking((blockState, blockGetter, blockPos) -> false).sound(SoundType.GLASS).mapColor(MapColor.GOLD));
    public static final DeferredBlock<ConnectedTextureBlock> AMBER_GLASS = registerConnectedTextureBlock("amber_glass", BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(2.5F).sound(SoundType.GLASS).mapColor(MapColor.GOLD));
    public static final DeferredBlock<TransparentBlock> PRESERVED_AMBER = registerTransparentBlock("preserved_amber", BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(2.5F).noOcclusion().isViewBlocking((blockState, blockGetter, blockPos) -> false).sound(SoundType.GLASS).mapColor(MapColor.GOLD));
    public static final DeferredBlock<ConnectedTextureBlock> PRESERVED_AMBER_GLASS = registerConnectedTextureBlock("preserved_amber_glass", BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(2.5F).sound(SoundType.GLASS).mapColor(MapColor.GOLD));

    public static final DeferredBlock<Block> GLOWING_JAR = registerSimpleBlock("glowing_jar", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FLUID_JAR = registerSimpleBlock("fluid_jar", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    public static final DeferredBlock<DoorBlock> AMBER_DOOR = registerDoor("amber_door", BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));

    // MARK: Ores
    public static final DeferredBlock<Block> ORE_IRON = registerBlock("ore_iron", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final DeferredBlock<Block> ORE_GOLD = registerBlock("ore_gold", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_ORE)));
    public static final DeferredBlock<Block> ORE_COAL = registerBlock("ore_coal", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.COAL_ORE)));
    public static final DeferredBlock<Block> ORE_DIAMOND = registerBlock("ore_diamond", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)));
    public static final DeferredBlock<Block> ORE_EMERALD = registerBlock("ore_emerald", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE)));
    public static final DeferredBlock<Block> ORE_LAPIS = registerBlock("ore_lapis", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_ORE)));
    public static final DeferredBlock<Block> ORE_QUARTZ = registerBlock("ore_quartz", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE)));
    public static final DeferredBlock<Block> ORE_PETRIFIED_QUARTZ = registerBlock("ore_petrified_quartz", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE)));
    public static final DeferredBlock<Block> ORE_COPPER = registerBlock("ore_copper", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));
    public static final DeferredBlock<Block> ORE_SILVER = registerBlock("ore_silver", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final DeferredBlock<Block> ORE_TIN = registerBlock("ore_tin", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final DeferredBlock<Block> ORE_LEAD = registerBlock("ore_lead", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final DeferredBlock<Block> ORE_ALUMINUM = registerBlock("ore_aluminum", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final DeferredBlock<Block> ORE_JADE = registerBlock("ore_jade", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final DeferredBlock<Block> ORE_ENCRUSTED_DIAMOND = registerBlock("ore_encrusted_diamond", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final DeferredBlock<Block> ORE_FOSSIL = registerBlock("ore_fossil", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final DeferredBlock<Block> ORE_GNEISS = registerBlock("ore_gneiss", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final DeferredBlock<Block> ORE_PETRIFIED_WOOD = registerBlock("ore_petrified_wood", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final DeferredBlock<Block> ORE_TEMPLE = registerBlock("ore_temple", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));

    // MARK: Logs
    public static final DeferredBlock<RotatedPillarBlock> LOG_BAOBAB = registerBlock("log_baobab", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<RotatedPillarBlock> LOG_EUCALYPTUS = registerBlock("log_eucalyptus", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<RotatedPillarBlock> LOG_MAHOGANY = registerBlock("log_mahogany", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<RotatedPillarBlock> LOG_MOSSBARK = registerBlock("log_mossbark", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<RotatedPillarBlock> LOG_ASPER = registerBlock("log_asper", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<RotatedPillarBlock> LOG_CYPRESS = registerBlock("log_cypress", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<RotatedPillarBlock> LOG_BALSAM = registerBlock("log_balsam", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<RotatedPillarBlock> LOG_BALSAM_RESINLESS = registerBlock("log_balsam_resinless", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<RotatedPillarBlock> LOG_ROTTEN = registerBlock("log_rotten", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<RotatedPillarBlock> LOG_MARSHWOOD = registerBlock("log_marshwood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<RotatedPillarBlock> LOG_SCORCHED = registerBlock("log_scorched", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<RotatedPillarBlock> LOG_BAMBOO = registerBlock("log_bamboo", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<RotatedPillarBlock> LOG_HOLLOW = registerBlock("log_hollow", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));

    // MARK: Saplings
    public static final DeferredBlock<SaplingBlock> SAPLING_MOSSBARK = registerSapling("sapling_mossbark", ModTreeGrowers.MOSSBARK);
    public static final DeferredBlock<SaplingBlock> SAPLING_ASPER = registerSapling("sapling_asper", ModTreeGrowers.ASPER);
    public static final DeferredBlock<SaplingBlock> SAPLING_EUCALYPTUS = registerSapling("sapling_eucalyptus", ModTreeGrowers.EUCALYPTUS);
    public static final DeferredBlock<SaplingBlock> SAPLING_MAHOGANY = registerSapling("sapling_mahogany", ModTreeGrowers.MAHOGANY);
    public static final DeferredBlock<SaplingBlock> SAPLING_BALSAM = registerSapling("sapling_balsam", ModTreeGrowers.BALSAM);
    public static final DeferredBlock<SaplingBlock> SAPLING_BAOBAB = registerSapling("sapling_baobab", ModTreeGrowers.BAOBAB);
    public static final DeferredBlock<SaplingBlock> SAPLING_MARSHWOOD = registerSapling("sapling_marshwood", ModTreeGrowers.MARSHWOOD);
    public static final DeferredBlock<SaplingBlock> SAPLING_CYPRESS = registerSapling("sapling_cypress", ModTreeGrowers.CYPRESS);
    public static final DeferredBlock<SaplingBlock> SAPLING_BAMBOO = registerSapling("sapling_bamboo", ModTreeGrowers.BAMBOO);

    // MARK: Leaves
    public static final DeferredBlock<Block> LEAVES_MOSSBARK = registerSimpleBlock("leaves_mossbark", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> LEAVES_ASPER = registerSimpleBlock("leaves_asper", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> LEAVES_EUCALYPTUS = registerSimpleBlock("leaves_eucalyptus", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> LEAVES_MAHOGANY = registerSimpleBlock("leaves_mahogany", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> LEAVES_BALSAM = registerSimpleBlock("leaves_balsam", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> LEAVES_BAOBAB = registerSimpleBlock("leaves_baobab", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> LEAVES_MARSHWOOD = registerSimpleBlock("leaves_marshwood", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> LEAVES_CYPRESS = registerSimpleBlock("leaves_cypress", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));

    // MARK: Planks
    public static final DeferredBlock<Block> PLANKS_BAOBAB = registerSimpleBlock("planks_baobab", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> PLANKS_EUCALYPTUS = registerSimpleBlock("planks_eucalyptus", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> PLANKS_MAHOGANY = registerSimpleBlock("planks_mahogany", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> PLANKS_MOSSBARK = registerSimpleBlock("planks_mossbark", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> PLANKS_ASPER = registerSimpleBlock("planks_asper", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> PLANKS_CYPRESS = registerSimpleBlock("planks_cypress", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> PLANKS_BALSAM = registerSimpleBlock("planks_balsam", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> PLANKS_WHITE = registerSimpleBlock("planks_white", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> PLANKS_BAMBOO = registerSimpleBlock("planks_bamboo", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> PLANKS_ROTTEN = registerSimpleBlock("planks_rotten", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> PLANKS_MARSHWOOD = registerSimpleBlock("planks_marshwood", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> PLANKS_SCORCHED = registerSimpleBlock("planks_scorched", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> PLANKS_VARNISHED = registerSimpleBlock("planks_varnished", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> PLANKS_PETRIFIED = registerSimpleBlock("planks_petrified", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    // MARK: Slabs Wood
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_BAOBAB = registerBlock("slab_planks_baobab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_EUCALYPTUS = registerBlock("slab_planks_eucalyptus", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_MAHOGANY = registerBlock("slab_planks_mahogany", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_MOSSBARK = registerBlock("slab_planks_mossbark", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_ASPER = registerBlock("slab_planks_asper", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_CYPRESS = registerBlock("slab_planks_cypress", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_BALSAM = registerBlock("slab_planks_balsam", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_WHITE = registerBlock("slab_planks_white", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_BAMBOO = registerBlock("slab_planks_bamboo", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_ROTTEN = registerBlock("slab_planks_rotten", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_MARSHWOOD = registerBlock("slab_planks_marshwood", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_SCORCHED = registerBlock("slab_planks_scorched", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_VARNISHED = registerBlock("slab_planks_varnished", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_PETRIFIED = registerBlock("slab_planks_petrified", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));

    // MARK: Slabs Stone
    public static final DeferredBlock<SlabBlock> SLAB_UMBERSTONE = registerBlock("slab_umberstone", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_UMBERCOBBLE = registerBlock("slab_umbercobble", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_UMBERCOBBLE_MOSSY = registerBlock("slab_umbercobble_mossy", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_UMBERCOBBLE_WEBBED = registerBlock("slab_umbercobble_webbed", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_UMBERSTONE_BRICKS = registerBlock("slab_umberstone_bricks", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_UMBERTILE_SMOOTH = registerBlock("slab_umberstone_smooth", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_UMBERTILE_SMOOTH_SMALL = registerBlock("slab_umberstone_smooth_small", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_UMBERPAVER = registerBlock("slab_umberpaver", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_UMBERPAVER_MOSSY = registerBlock("slab_umberpaver_mossy", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_UMBERPAVER_WEBBED = registerBlock("slab_umberpaver_webbed", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_AMBER = registerBlock("slab_amber", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(AMBER.get())));
    public static final DeferredBlock<SlabBlock> SLAB_AMBER_BRICKS = registerBlock("slab_amber_bricks", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(AMBER.get())));
    public static final DeferredBlock<SlabBlock> SLAB_MUD_BRICKS = registerBlock("slab_mud_bricks", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
    public static final DeferredBlock<SlabBlock> SLAB_MIR_BRICKS = registerBlock("slab_mir_bricks", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));

    // MARK: Stairs Wood
    public static final DeferredBlock<StairBlock> STAIRS_BAOBAB = registerStairs("stairs_baobab", PLANKS_BAOBAB, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_EUCALYPTUS = registerStairs("stairs_eucalyptus", PLANKS_EUCALYPTUS, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_MAHOGANY = registerStairs("stairs_mahogany", PLANKS_MAHOGANY, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_MOSSBARK = registerStairs("stairs_mossbark", PLANKS_MOSSBARK, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_ASPER = registerStairs("stairs_asper", PLANKS_ASPER,  BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_CYPRESS = registerStairs("stairs_cypress", PLANKS_CYPRESS, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_BALSAM = registerStairs("stairs_balsam", PLANKS_BALSAM, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_WHITE = registerStairs("stairs_white", PLANKS_WHITE, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_BAMBOO = registerStairs("stairs_bamboo", PLANKS_BAMBOO, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_ROTTEN = registerStairs("stairs_rotten", PLANKS_ROTTEN, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_MARSHWOOD = registerStairs("stairs_marshwood", PLANKS_MARSHWOOD, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_SCORCHED = registerStairs("stairs_scorched", PLANKS_SCORCHED, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_VARNISHED = registerStairs("stairs_varnished", PLANKS_VARNISHED, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_PETRIFIED = registerStairs("stairs_petrified", PLANKS_PETRIFIED, BlockBehaviour.Properties.of().strength(2.0F));

    // MARK: Stairs Stone
    public static final DeferredBlock<StairBlock> STAIRS_UMBERSTONE = registerStairs("stairs_umberstone", UMBERSTONE, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_UMBERCOBBLE = registerStairs("stairs_umbercobble", UMBERCOBBLE, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_UMBERCOBBLE_MOSSY = registerStairs("stairs_umbercobble_mossy", UMBERCOBBLE_MOSSY, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_UMBERCOBBLE_WEBBED = registerStairs("stairs_umbercobble_webbed", UMBERCOBBLE_WEBBED, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_UMBERSTONE_BRICKS = registerStairs("stairs_umberstone_bricks", UMBERSTONE_BRICKS, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_UMBERTILE_SMOOTH = registerStairs("stairs_umberstone_smooth", UMBERTILE_SMOOTH, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_UMBERTILE_SMOOTH_SMALL = registerStairs("stairs_umberstone_smooth_small", UMBERTILE_SMOOTH_SMALL, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_UMBERPAVER = registerStairs("stairs_umberpaver", UMBERPAVER, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_UMBERPAVER_MOSSY = registerStairs("stairs_umberpaver_mossy", UMBERPAVER_MOSSY, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_UMBERPAVER_WEBBED = registerStairs("stairs_umberpaver_webbed", UMBERPAVER_WEBBED, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_AMBER = registerStairs("stairs_amber", AMBER, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_AMBER_BRICKS = registerStairs("stairs_amber_bricks", AMBER_BRICKS, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_MUD_BRICKS = registerStairs("stairs_mud_bricks", MUD_BRICKS, BlockBehaviour.Properties.of().strength(2.0F));
    public static final DeferredBlock<StairBlock> STAIRS_MIR_BRICKS = registerStairs("stairs_mir_bricks", MIR_BRICKS, BlockBehaviour.Properties.of().strength(2.0F));

    // MARK: Doors
    public static final DeferredBlock<DoorBlock> DOOR_BAOBAB = registerDoor("door_baobab", BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<DoorBlock> DOOR_EUCALYPTUS = registerDoor("door_eucalyptus", BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<DoorBlock> DOOR_MAHOGANY = registerDoor("door_mahogany", BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<DoorBlock> DOOR_MOSSBARK = registerDoor("door_mossbark", BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<DoorBlock> DOOR_ASPER = registerDoor("door_asper", BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<DoorBlock> DOOR_CYPRESS = registerDoor("door_cypress", BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<DoorBlock> DOOR_BALSAM = registerDoor("door_balsam", BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<DoorBlock> DOOR_WHITE = registerDoor("door_white", BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<DoorBlock> DOOR_ROTTEN = registerDoor("door_rotten", BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<DoorBlock> DOOR_MARSHWOOD = registerDoor("door_marshwood", BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<DoorBlock> DOOR_SCORCHED = registerDoor("door_scorched", BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));

    // MARK: Fences
    public static final DeferredBlock<FenceBlock> FENCE_BAOBAB = registerFence("fence_baobab", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final DeferredBlock<FenceBlock> FENCE_EUCALYPTUS = registerFence("fence_eucalyptus", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final DeferredBlock<FenceBlock> FENCE_MAHOGANY = registerFence("fence_mahogany", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final DeferredBlock<FenceBlock> FENCE_MOSSBARK = registerFence("fence_mossbark", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final DeferredBlock<FenceBlock> FENCE_ASPER = registerFence("fence_asper", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final DeferredBlock<FenceBlock> FENCE_CYPRESS = registerFence("fence_cypress", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final DeferredBlock<FenceBlock> FENCE_BALSAM = registerFence("fence_balsam", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final DeferredBlock<FenceBlock> FENCE_WHITE = registerFence("fence_white", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final DeferredBlock<FenceBlock> FENCE_BAMBOO = registerFence("fence_bamboo", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final DeferredBlock<FenceBlock> FENCE_ROTTEN = registerFence("fence_rotten", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final DeferredBlock<FenceBlock> FENCE_MARSHWOOD = registerFence("fence_marshwood", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final DeferredBlock<FenceBlock> FENCE_SCORCHED = registerFence("fence_scorched", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
    public static final DeferredBlock<FenceBlock> FENCE_VARNISHED = registerFence("fence_varnished", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));

    // MARK: Fence Gates
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_BAOBAB = registerSimpleFenceGate("fence_gate_baobab");
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_EUCALYPTUS = registerSimpleFenceGate("fence_gate_eucalyptus");
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_MAHOGANY = registerSimpleFenceGate("fence_gate_mahogany");
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_MOSSBARK = registerSimpleFenceGate("fence_gate_mossbark");
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_ASPER = registerSimpleFenceGate("fence_gate_asper");
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_CYPRESS = registerSimpleFenceGate("fence_gate_cypress");
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_BALSAM = registerSimpleFenceGate("fence_gate_balsam");
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_WHITE = registerSimpleFenceGate("fence_gate_white");
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_BAMBOO = registerSimpleFenceGate("fence_gate_bamboo");
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_ROTTEN = registerSimpleFenceGate("fence_gate_rotten");
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_MARSHWOOD = registerSimpleFenceGate("fence_gate_marshwood");
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_SCORCHED = registerSimpleFenceGate("fence_gate_scorched");
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_VARNISHED = registerSimpleFenceGate("fence_gate_varnished");

    // MARK: Walls
    public static final DeferredBlock<WallBlock> WALL_UMBERSTONE = registerBlock("wall_umberstone", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)));
    public static final DeferredBlock<WallBlock> WALL_UMBERCOBBLE = registerBlock("wall_umbercobble", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)));
    public static final DeferredBlock<WallBlock> WALL_UMBERCOBBLE_MOSSY = registerBlock("wall_umbercobble_mossy", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)));
    public static final DeferredBlock<WallBlock> WALL_UMBERCOBBLE_WEBBED = registerBlock("wall_umbercobble_webbed", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)));
    public static final DeferredBlock<WallBlock> WALL_UMBERSTONE_BRICKS = registerBlock("wall_umberstone_bricks", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)));
    public static final DeferredBlock<WallBlock> WALL_UMBERTILE_SMOOTH = registerBlock("wall_umbertile_smooth", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)));
    public static final DeferredBlock<WallBlock> WALL_UMBERTILE_SMOOTH_SMALL = registerBlock("wall_umbertile_smooth_small", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)));
    public static final DeferredBlock<WallBlock> WALL_AMBER = registerBlock("wall_amber", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(AMBER.get())));
    public static final DeferredBlock<WallBlock> WALL_AMBER_BRICKS = registerBlock("wall_amber_bricks", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(AMBER.get())));
    public static final DeferredBlock<WallBlock> WALL_UMBERPAVER = registerBlock("wall_umberpaver", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)));
    public static final DeferredBlock<WallBlock> WALL_UMBERPAVER_MOSSY = registerBlock("wall_umberpaver_mossy", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)));
    public static final DeferredBlock<WallBlock> WALL_UMBERPAVER_WEBBED = registerBlock("wall_umberpaver_webbed", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)));

    // MARK: Plants
    public static final DeferredBlock<ModCropBlock> CROP_TURNIP = registerCrop("crop_turnip", ModItems.TURNIP, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS));
    public static final DeferredBlock<ModCropBlock> CROP_CABBAGE = registerCrop("crop_cabbage", ModItems.CABBAGE_SEEDS, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS));
    public static final DeferredBlock<ModCropBlock> CROP_MANDRAKE = registerCrop("crop_mandrake", ModItems.MANDRAKE_ROOT, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS));
    public static final DeferredBlock<ModBerryBushBlock> JADE_BERRY_BUSH = registerBush("jade_berry_bush", ModItems.JADE_BERRIES, BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH));
    public static final DeferredBlock<ModBerryBushBlock> HEART_BERRY_BUSH = registerBush("heart_berry_bush", ModItems.HEART_BERRIES, BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH));
    public static final DeferredBlock<ModBerryBushBlock> SWAMP_BERRY_BUSH = registerBush("swamp_berry_bush", ModItems.SWAMP_BERRIES, BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH));
    public static final DeferredBlock<Block> DARK_FRUIT_VINE = registerSimpleBlock("dark_fruit_vine", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PRICKLY_PEAR = registerSimpleBlock("prickly_pear", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DARK_CAPPED_MUSHROOM = registerBlock("dark_capped_mushroom", () -> new MushroomBlock(VegetationFeatures.PATCH_BROWN_MUSHROOM, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)));
    public static final DeferredBlock<Block> DUTCH_CAP_MUSHROOM = registerBlock("dutch_cap_mushroom", () -> new MushroomBlock(VegetationFeatures.PATCH_BROWN_MUSHROOM, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)));
    public static final DeferredBlock<Block> GRANDMAS_SHOES_MUSHROOM = registerBlock("grandmas_shoes_mushroom", () -> new MushroomBlock(VegetationFeatures.PATCH_BROWN_MUSHROOM, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)));
    public static final DeferredBlock<Block> KAIZERS_FINGERS_MUSHROOM = registerBlock("kaizers_fingers_mushroom", () -> new MushroomBlock(VegetationFeatures.PATCH_BROWN_MUSHROOM, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)));
    public static final DeferredBlock<Block> SARCASTIC_CZECH_MUSHROOM = registerBlock("sarcastic_czech_mushroom", () -> new MushroomBlock(VegetationFeatures.PATCH_BROWN_MUSHROOM, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)));
    public static final DeferredBlock<Block> DARK_CAPPED_MUSHROOM_BLOCK = registerSimpleBlock("dark_capped_mushroom_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUTCH_CAP_MUSHROOM_BLOCK = registerSimpleBlock("dutch_cap_mushroom_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GRANDMAS_SHOES_MUSHROOM_BLOCK = registerSimpleBlock("grandmas_shoes_mushroom_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> KAIZERS_FINGERS_MUSHROOM_BLOCK = registerSimpleBlock("kaizers_fingers_mushroom_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SARCASTIC_CZECH_MUSHROOM_BLOCK = registerSimpleBlock("sarcastic_czech_mushroom_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DESERT_SHRUB = registerSimpleBlock("desert_shrub", BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS));
    public static final DeferredBlock<Block> MIRE_CORAL = registerSimpleBlock("mire_coral", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> NETTLE = registerSimpleBlock("nettle", BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS));
    public static final DeferredBlock<Block> NETTLE_FLOWERED = registerSimpleBlock("nettle_flowered", BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS));
    public static final DeferredBlock<Block> SWAMP_PLANT = registerSimpleBlock("swamp_plant", BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS));
    public static final DeferredBlock<Block> FIRE_BLOOM = registerSimpleBlock("fire_bloom", BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS));
    public static final DeferredBlock<Block> FERN = registerSimpleBlock("fern", BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS));
    public static final DeferredBlock<Block> FIDDLE_HEAD = registerSimpleBlock("fiddle_head", BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS));
    public static final DeferredBlock<VineBlock> THORNS = registerBlock("thorns", () -> new VineBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE)));
    public static final DeferredBlock<Block> MOSS_DOWN = registerSimpleBlock("moss_down", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> MOULD_DOWN = registerSimpleBlock("mould_down", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> CULTIVATED_MOSS_DOWN = registerSimpleBlock("cultivated_moss_down", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> CULTIVATED_MOULD_DOWN = registerSimpleBlock("cultivated_mould_down", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<WaterlilyBlock> ALGAE = registerBlock(
            "algae",
            () -> new WaterlilyBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .instabreak()
                    .sound(SoundType.LILY_PAD)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
            )
    );
    public static final DeferredBlock<Block> GLOWSHROOM_BLOCK = registerSimpleBlock("glowshroom_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GLOWSHROOM_STALK = registerSimpleBlock("glowshroom_stalk", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> HANGING_WEB = registerSimpleBlock("hanging_web", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    // MARK: Flowers
    public static final DeferredBlock<Block> PETAL_BLACK = registerSimpleBlock("petal_black", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETAL_RED = registerSimpleBlock("petal_red", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETAL_BROWN = registerSimpleBlock("petal_brown", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETAL_BLUE = registerSimpleBlock("petal_blue", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETAL_PURPLE = registerSimpleBlock("petal_purple", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETAL_CYAN = registerSimpleBlock("petal_cyan", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETAL_LIGHT_GRAY = registerSimpleBlock("petal_light_gray", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETAL_GRAY = registerSimpleBlock("petal_gray", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETAL_PINK = registerSimpleBlock("petal_pink", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETAL_YELLOW = registerSimpleBlock("petal_yellow", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETAL_LIGHT_BLUE = registerSimpleBlock("petal_light_blue", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETAL_MAGENTA = registerSimpleBlock("petal_magenta", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETAL_ORANGE = registerSimpleBlock("petal_orange", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> PETAL_WHITE = registerSimpleBlock("petal_white", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    public static final DeferredBlock<Block> EXPLODING_STIGMA = registerSimpleBlock("exploding_stigma", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STEM = registerSimpleBlock("stem", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STIGMA_BLACK = registerSimpleBlock("stigma_black", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STIGMA_RED = registerSimpleBlock("stigma_red", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STIGMA_BROWN = registerSimpleBlock("stigma_brown", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STIGMA_BLUE = registerSimpleBlock("stigma_blue", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STIGMA_PURPLE = registerSimpleBlock("stigma_purple", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STIGMA_CYAN = registerSimpleBlock("stigma_cyan", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STIGMA_LIGHT_GRAY = registerSimpleBlock("stigma_light_gray", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STIGMA_GRAY = registerSimpleBlock("stigma_gray", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STIGMA_PINK = registerSimpleBlock("stigma_pink", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STIGMA_YELLOW = registerSimpleBlock("stigma_yellow", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STIGMA_LIGHT_BLUE = registerSimpleBlock("stigma_light_blue", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STIGMA_MAGENTA = registerSimpleBlock("stigma_magenta", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STIGMA_ORANGE = registerSimpleBlock("stigma_orange", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> STIGMA_WHITE = registerSimpleBlock("stigma_white", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    public static final DeferredBlock<Block> FLOWER_BLACK = registerSimpleBlock("flower_black", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FLOWER_RED = registerSimpleBlock("flower_red", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FLOWER_BROWN = registerSimpleBlock("flower_brown", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FLOWER_BLUE = registerSimpleBlock("flower_blue", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FLOWER_PURPLE = registerSimpleBlock("flower_purple", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FLOWER_CYAN = registerSimpleBlock("flower_cyan", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FLOWER_LIGHT_GRAY = registerSimpleBlock("flower_light_gray", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FLOWER_GRAY = registerSimpleBlock("flower_gray", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FLOWER_PINK = registerSimpleBlock("flower_pink", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FLOWER_YELLOW = registerSimpleBlock("flower_yellow", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FLOWER_LIGHT_BLUE = registerSimpleBlock("flower_light_blue", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FLOWER_MAGENTA = registerSimpleBlock("flower_magenta", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FLOWER_ORANGE = registerSimpleBlock("flower_orange", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FLOWER_WHITE = registerSimpleBlock("flower_white", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FLOWER_RAINBOW = registerSimpleBlock("flower_rainbow", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    // MARK: Flowers Double Height
    public static final DeferredBlock<DoublePlantBlock> BULLRUSH = registerDoublePlant("bullrush", BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));
    public static final DeferredBlock<DoublePlantBlock> WEEPING_BLUEBELL = registerDoublePlant("weeping_bluebell", BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));
    public static final DeferredBlock<DoublePlantBlock> SUNDEW = registerDoublePlant("sundew", BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));
    public static final DeferredBlock<DoublePlantBlock> DROUGHTED_SHRUB = registerDoublePlant("droughted_shrub", BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));
    public static final DeferredBlock<DoublePlantBlock> TALL_BLOOM = registerDoublePlant("tall_bloom", BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));
    public static final DeferredBlock<DoublePlantBlock> TANGLED_STALK = registerDoublePlant("tangled_stalk", BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));
    public static final DeferredBlock<DoublePlantBlock> HIGH_CAPPED_MUSHROOM = registerDoublePlant("high_capped_mushroom", BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));
    public static final DeferredBlock<DoublePlantBlock> TALL_FERN = registerDoublePlant("tall_fern", BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));

    // MARK: Other
    public static final DeferredBlock<ErebusPortalBlock> PORTAL = registerBlock("portal", () -> new ErebusPortalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_PORTAL)));
    public static final DeferredBlock<GaeanKeystoneBlock> GAEAN_KEYSTONE = registerBlock("gaean_keystone", () -> new GaeanKeystoneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.END_PORTAL_FRAME)));
    public static final DeferredBlock<Block> JADE_BLOCK = registerSimpleBlock("jade_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> MUD = registerBlock("mud", () -> new MudBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD)) {
        @Override
        public TriState canSustainPlant(BlockState state, BlockGetter level, BlockPos soilPosition, Direction facing, BlockState plant) {
            return TriState.TRUE;
        }
    });
    public static final DeferredBlock<Block> QUICK_SAND = registerSimpleBlock("quick_sand", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GHOST_SAND = registerSimpleBlock("ghost_sand", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SWAMP_VENT = registerSimpleBlock("swamp_vent", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GNEISS_VENT = registerSimpleBlock("gneiss_vent", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> RED_GEM_BLOCK = registerSimpleBlock("red_gem_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> RED_GEM_LAMP = registerSimpleBlock("red_gem_lamp", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<WitherWeb> WITHER_WEB = registerBlock("wither_web", () -> new WitherWeb(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).sound(SoundType.COBWEB).forceSolidOn().noCollission().requiresCorrectToolForDrops().strength(4.0F).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<LavaWeb> LAVA_WEB = registerBlock("lava_web", () -> new LavaWeb(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).sound(SoundType.COBWEB).forceSolidOn().noCollission().requiresCorrectToolForDrops().strength(4.0F).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> GNEISS = registerSimpleBlock("gneiss", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GNEISS_CARVED = registerSimpleBlock("gneiss_carved", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GNEISS_RELIEF = registerSimpleBlock("gneiss_relief", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GNEISS_BRICKS = registerSimpleBlock("gneiss_bricks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GNEISS_SMOOTH = registerSimpleBlock("gneiss_smooth", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GNEISS_TILES = registerSimpleBlock("gneiss_tiles", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GNEISS_TILES_CRACKED = registerSimpleBlock("gneiss_tiles_cracked", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_BRICK = registerSimpleBlock("temple_brick", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_PILLAR = registerSimpleBlock("temple_pillar", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_TILE = registerSimpleBlock("temple_tile", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SILK = registerSimpleBlock("silk", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> REIN_EXO = registerSimpleBlock("rein_exo", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<RotatedPillarBlock> VELOCITY = registerBlock("velocity", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    public static final DeferredBlock<RotatedPillarBlock> LIGHTNING_SPEED = registerBlock("lightning_speed", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> BLOCK_OF_BONES = registerSimpleBlock("block_of_bones", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ANTLION_EGG = registerSimpleBlock("antlion_egg", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TARANTULA_EGG = registerSimpleBlock("tarantula_egg", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<HoneyTreatBlock> HONEY_TREAT = registerBlock("honey_treat", () -> new HoneyTreatBlock(BlockBehaviour.Properties.of().forceSolidOn().strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<CandleHoneyTreatBlock> CANDLE_HONEY_TREAT = registerBlock("candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.CANDLE));
    public static final DeferredBlock<CandleHoneyTreatBlock> WHITE_CANDLE_HONEY_TREAT = registerBlock("white_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.WHITE_CANDLE));
    public static final DeferredBlock<CandleHoneyTreatBlock> ORANGE_CANDLE_HONEY_TREAT = registerBlock("orange_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.ORANGE_CANDLE));
    public static final DeferredBlock<CandleHoneyTreatBlock> MAGENTA_CANDLE_HONEY_TREAT = registerBlock("magenta_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.MAGENTA_CANDLE));
    public static final DeferredBlock<CandleHoneyTreatBlock> LIGHT_BLUE_CANDLE_HONEY_TREAT = registerBlock("light_blue_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.LIGHT_BLUE_CANDLE));
    public static final DeferredBlock<CandleHoneyTreatBlock> YELLOW_CANDLE_HONEY_TREAT = registerBlock("yellow_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.YELLOW_CANDLE));
    public static final DeferredBlock<CandleHoneyTreatBlock> LIME_CANDLE_HONEY_TREAT = registerBlock("lime_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.LIME_CANDLE));
    public static final DeferredBlock<CandleHoneyTreatBlock> PINK_CANDLE_HONEY_TREAT = registerBlock("pink_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.PINK_CANDLE));
    public static final DeferredBlock<CandleHoneyTreatBlock> GRAY_CANDLE_HONEY_TREAT = registerBlock("gray_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.GRAY_CANDLE));
    public static final DeferredBlock<CandleHoneyTreatBlock> LIGHT_GRAY_CANDLE_HONEY_TREAT = registerBlock("light_gray_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.LIGHT_GRAY_CANDLE));
    public static final DeferredBlock<CandleHoneyTreatBlock> CYAN_CANDLE_HONEY_TREAT = registerBlock("cyan_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.CYAN_CANDLE));
    public static final DeferredBlock<CandleHoneyTreatBlock> PURPLE_CANDLE_HONEY_TREAT = registerBlock("purple_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.PURPLE_CANDLE));
    public static final DeferredBlock<CandleHoneyTreatBlock> BLUE_CANDLE_HONEY_TREAT = registerBlock("blue_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.BLUE_CANDLE));
    public static final DeferredBlock<CandleHoneyTreatBlock> BROWN_CANDLE_HONEY_TREAT = registerBlock("brown_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.BROWN_CANDLE));
    public static final DeferredBlock<CandleHoneyTreatBlock> GREEN_CANDLE_HONEY_TREAT = registerBlock("green_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.GREEN_CANDLE));
    public static final DeferredBlock<CandleHoneyTreatBlock> RED_CANDLE_HONEY_TREAT = registerBlock("red_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.RED_CANDLE));
    public static final DeferredBlock<CandleHoneyTreatBlock> BLACK_CANDLE_HONEY_TREAT = registerBlock("black_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.BLACK_CANDLE));
    public static final DeferredBlock<Block> WASP_NEST = registerSimpleBlock("wasp_nest", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<StairBlock> STAIRS_WASP_NEST = registerBlock(
            "stairs_wasp_nest",
            () -> new StairBlock(
                    WASP_NEST.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2.0F)
            )
    );
    public static final DeferredBlock<Block> INSECT_REPELLENT = registerSimpleBlock("insect_repellent", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    // MARK: Spawners
    public static final DeferredBlock<Block> ANTLION_SPAWNER = registerSimpleBlock("antlion_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DRAGON_FLY_SPAWNER = registerSimpleBlock("dragon_fly_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> JUMPING_SPIDER_SPAWNER = registerSimpleBlock("jumping_spider_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SPIDER_SPAWNER = registerSimpleBlock("spider_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TARANTULA_SPAWNER = registerSimpleBlock("tarantula_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> WASP_SPAWNER = registerSimpleBlock("wasp_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ZOMBIE_ANT_SPAWNER = registerSimpleBlock("zombie_ant_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ZOMBIE_ANT_SOLDIER_SPAWNER = registerSimpleBlock("zombie_ant_soldier_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> MAGMA_CRAWLER_SPAWNER = registerSimpleBlock("magma_crawler_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUNG_SPAWNER_FLY = registerSimpleBlock("dung_spawner_fly", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DUNG_SPAWNER_BOT_FLY = registerSimpleBlock("dung_spawner_bot_fly", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> LOCUST_SPAWNER = registerSimpleBlock("locust_spawner", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    // MARK: Utility Blocks
    public static final DeferredBlock<PetrifiedCraftingTableBlock> PETRIFIED_CRAFTING_TABLE = registerBlock("petrified_crafting_table", () -> new PetrifiedCraftingTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_CHEST = registerSimpleBlock("petrified_wood_chest", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_CRATE = registerSimpleBlock("bamboo_crate", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_BRIDGE = registerSimpleBlock("bamboo_bridge", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_LADDER = registerSimpleBlock("bamboo_ladder", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_NERD_POLE = registerSimpleBlock("bamboo_nerd_pole", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_EXTENDER = registerSimpleBlock("bamboo_extender", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_TORCH = registerSimpleBlock("bamboo_torch", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_PIPE = registerSimpleBlock("bamboo_pipe", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_PIPE_EXTRACT = registerSimpleBlock("bamboo_pipe_extract", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BAMBOO_PIPE_EXTRACT_ACTIVE = registerSimpleBlock("bamboo_pipe_extract_active", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SILO_ROOF = registerSimpleBlock("silo_roof", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SILO_TANK = registerSimpleBlock("silo_tank", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> SILO_SUPPORTS = registerSimpleBlock("silo_supports", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> HONEY_COMB = registerSimpleBlock("honey_comb", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> COMPOSTER = registerSimpleBlock("composter", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> BLENDER = registerSimpleBlock("blender", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<UmberFurnaceBlock> UMBER_FURNACE = registerBlock("umber_furnace", () -> new UmberFurnaceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE)));
    public static final DeferredBlock<ButtonBlock> UMBERSTONE_BUTTON = registerBlock("umberstone_button", () -> new ButtonBlock(BlockSetType.STONE, 10, BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> LIQUIFIER = registerSimpleBlock("liquifier", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> GLOW_GEM = registerSimpleBlock("glow_gem", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> MUCUS_BOMB = registerSimpleBlock("mucus_bomb", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> UMBER_GOLEM_STATUE = registerSimpleBlock("umber_golem_statue", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    public static final DeferredBlock<Block> ALTAR_BASE = registerSimpleBlock("altar_base", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ALTAR_LIGHTNING = registerSimpleBlock("altar_lightning", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ALTAR_HEALING = registerSimpleBlock("altar_healing", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ALTAR_XP = registerSimpleBlock("altar_xp", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> ALTAR_REPAIR = registerSimpleBlock("altar_repair", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<OfferingAltar> OFFERING_ALTAR = registerBlock("offering_altar", () -> new OfferingAltar(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(2F).noOcclusion()));

    // MARK: Antlion Dungeon
    public static final DeferredBlock<Block> CAPSTONE = registerSimpleBlock("capstone", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> CAPSTONE_MUD = registerSimpleBlock("capstone_mud", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> CAPSTONE_IRON = registerSimpleBlock("capstone_iron", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> CAPSTONE_GOLD = registerSimpleBlock("capstone_gold", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> CAPSTONE_JADE = registerSimpleBlock("capstone_jade", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING = registerSimpleBlock("temple_brick_unbreaking", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING_JADE = registerSimpleBlock("temple_brick_unbreaking_jade", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING_EXO = registerSimpleBlock("temple_brick_unbreaking_exo", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING_CREAM = registerSimpleBlock("temple_brick_unbreaking_cream", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING_EYE = registerSimpleBlock("temple_brick_unbreaking_eye", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING_STRING = registerSimpleBlock("temple_brick_unbreaking_string", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TEMPLE_TELEPORTER = registerSimpleBlock("temple_teleporter", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FORCE_FIELD = registerSimpleBlock("force_field", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> FORCE_LOCK = registerSimpleBlock("force_lock", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    public static final DeferredBlock<Block> ANT_HILL_BLOCK = registerSimpleBlock("ant_hill_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
}
