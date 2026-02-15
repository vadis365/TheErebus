package erebus.registries.blocks;

import erebus.Erebus;
import erebus.block.*;
import erebus.block.altars.*;
import erebus.block.bamboo.*;
import erebus.block.fluid.FormicAcidFluidBlock;
import erebus.block.plants.*;
import erebus.block.portal.ErebusPortalBlock;
import erebus.block.util.ModBlockSetTypes;
import erebus.block.util.ModWoodTypes;
import erebus.registries.ModFluids;
import erebus.registries.item.ModItems;
import erebus.registries.world.feature.PlantFeatures;
import erebus.registries.world.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.TriState;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class ModBlocks extends erebus.registries.helpers.ModBlockHelpers {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Erebus.MODID);

    // MARK: Amber
    public static final DeferredBlock<Block> AMBER = registerTransparentBlock("amber", ModBlockProperties.AMBER_PROPERTIES.strength(1.5F));
    public static final DeferredBlock<Block> AMBER_BRICKS = registerTransparentBlock("amber_bricks", ModBlockProperties.AMBER_PROPERTIES.strength(2.0F));
    public static final DeferredBlock<Block> AMBER_GLASS = registerConnectedTextureBlock("amber_glass", ModBlockProperties.AMBER_PROPERTIES.strength(1.5F));
    public static final DeferredBlock<Block> PRESERVED_AMBER = registerBlock("preserved_amber", () -> new PreservedBlock(ModBlockProperties.AMBER_PROPERTIES.strength(10).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("preserved_amber")))));
    public static final DeferredBlock<Block> PRESERVED_AMBER_GLASS = registerBlock("preserved_amber_glass", () -> new PreservedBlock(ModBlockProperties.AMBER_PROPERTIES.strength(10).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("preserved_amber_glass")))));

    public static final DeferredBlock<Block> GLOWING_JAR = registerBlock("glowing_jar", () -> new GlowingJarBlock(ModBlockProperties.GLOWING_JAR_PROPERTIES.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("glowing_jar")))));
    public static final DeferredBlock<Block> FLUID_JAR = registerBlockWithoutBlockItem("fluid_jar", () -> new FluidJarBlock(ModBlockProperties.FLUID_JAR_PROPERTIES.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("fluid_jar")))));

    public static final DeferredBlock<Block> AMBER_DOOR = registerDoor("amber_door", ModBlockSetTypes.AMBER, ModBlockProperties.AMBER_DOOR_PROPERTIES);

    // MARK: Umberstone
    public static final DeferredBlock<Block> UMBERSTONE = registerSimpleBlock("umberstone", ModBlockProperties.UMBERSTONE);
    public static final DeferredBlock<Block> UMBERSTONE_BRICKS = registerSimpleBlock("umberstone_bricks", ModBlockProperties.UMBERSTONE_BRICKS);
    public static final DeferredBlock<Block> UMBERCOBBLE = registerSimpleBlock("umbercobble", ModBlockProperties.UMBERCOBBLE);
    public static final DeferredBlock<Block> UMBERCOBBLE_MOSSY = registerSimpleBlock("umbercobble_mossy", ModBlockProperties.UMBERCOBBLE_MOSSY);
    public static final DeferredBlock<Block> UMBERCOBBLE_WEBBED = registerSimpleBlock("umbercobble_webbed", ModBlockProperties.UMBERCOBBLE_WEBBED);
    public static final DeferredBlock<Block> UMBERTILE_SMOOTH = registerSimpleBlock("umbertile_smooth", ModBlockProperties.UMBERTILE_SMOOTH);
    public static final DeferredBlock<Block> UMBERTILE_SMOOTH_SMALL = registerSimpleBlock("umbertile_smooth_small", ModBlockProperties.UMBERTILE_SMOOTH_SMALL);
    public static final DeferredBlock<Block> UMBERGRAVEL = registerSimpleBlock("umbergravel", ModBlockProperties.UMBERGRAVEL);
    public static final DeferredBlock<Block> UMBERPAVER = registerSimpleBlock("umberpaver", ModBlockProperties.UMBERPAVER);
    public static final DeferredBlock<Block> UMBERPAVER_MOSSY = registerSimpleBlock("umberpaver_mossy", ModBlockProperties.UMBERPAVER_MOSSY);
    public static final DeferredBlock<Block> UMBERPAVER_WEBBED = registerSimpleBlock("umberpaver_webbed", ModBlockProperties.UMBERPAVER_WEBBED);
    public static final DeferredBlock<Block> UMBERSTONE_PILLAR = registerBlock("umberstone_pillar", () -> new RotatedPillarBlock(ModBlockProperties.UMBERSTONE_PILLAR.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("umberstone_pillar")))));
    public static final DeferredBlock<Block> VOLCANIC_ROCK = registerSimpleBlock("volcanic_rock", ModBlockProperties.VOLCANIC_ROCK);
    public static final DeferredBlock<Block> DUST = registerSimpleBlock("dust", ModBlockProperties.DUST);
    public static final DeferredBlock<Block> DUST_LAYER = registerBlock("dust_layer", () -> new SnowLayerBlock(ModBlockProperties.DUST_LAYER.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("dust_layer")))));
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK = registerSimpleBlock("petrified_wood_rock", ModBlockProperties.PETRIFIED_WOOD_ROCK);
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_2 = registerSimpleBlock("petrified_wood_rock_2", ModBlockProperties.PETRIFIED_WOOD_ROCK_2);
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_3 = registerSimpleBlock("petrified_wood_rock_3", ModBlockProperties.PETRIFIED_WOOD_ROCK_3);
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_4 = registerSimpleBlock("petrified_wood_rock_4", ModBlockProperties.PETRIFIED_WOOD_ROCK_4);
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_5 = registerSimpleBlock("petrified_wood_rock_5", ModBlockProperties.PETRIFIED_WOOD_ROCK_5);
    public static final DeferredBlock<Block> PETRIFIED_WOOD_ROCK_6 = registerSimpleBlock("petrified_wood_rock_6", ModBlockProperties.PETRIFIED_WOOD_ROCK_6);
    public static final DeferredBlock<Block> PETRIFIED_BARK_RED = registerBlock("petrified_bark_red", () -> new RotatedPillarBlock(ModBlockProperties.PETRIFIED_BARK_RED.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("petrified_bark_red")))));
    public static final DeferredBlock<Block> PETRIFIED_BARK_BROWN = registerBlock("petrified_bark_brown", () -> new RotatedPillarBlock(ModBlockProperties.PETRIFIED_BARK_BROWN.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("petrified_bark_brown")))));
    public static final DeferredBlock<Block> PETRIFIED_LOG_INNER = registerSimpleBlock("petrified_log_inner", ModBlockProperties.PETRIFIED_LOG_INNER);
    public static final DeferredBlock<Block> DUNG = registerSimpleBlock("dung", ModBlockProperties.DUNG);
    public static final DeferredBlock<Block> MIR_BRICKS = registerSimpleBlock("mir_bricks", ModBlockProperties.MIR_BRICKS);
    public static final DeferredBlock<Block> MUD_BRICKS = registerSimpleBlock("mud_bricks", ModBlockProperties.MUD_BRICKS);

    // MARK: Ore
    public static final DeferredBlock<Block> ORE_IRON = registerBlock("ore_iron", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_IRON.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_iron")))));
    public static final DeferredBlock<Block> ORE_GOLD = registerBlock("ore_gold", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_GOLD.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_gold")))));
    public static final DeferredBlock<Block> ORE_COAL = registerBlock("ore_coal", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_COAL.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_coal")))));
    public static final DeferredBlock<Block> ORE_DIAMOND = registerBlock("ore_diamond", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_DIAMOND.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_diamond")))));
    public static final DeferredBlock<Block> ORE_EMERALD = registerBlock("ore_emerald", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_EMERALD.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_emerald")))));
    public static final DeferredBlock<Block> ORE_LAPIS = registerBlock("ore_lapis", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_LAPIS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_lapis")))));
    public static final DeferredBlock<Block> ORE_QUARTZ = registerBlock("ore_quartz", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_QUARTZ.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_quartz")))));
    public static final DeferredBlock<Block> ORE_PETRIFIED_QUARTZ = registerBlock("ore_petrified_quartz", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_PETRIFIED_QUARTZ.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_petrified_quartz")))));
    public static final DeferredBlock<Block> ORE_COPPER = registerBlock("ore_copper", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_COPPER.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_copper")))));
    public static final DeferredBlock<Block> ORE_SILVER = registerBlock("ore_silver", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_SILVER.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_silver")))));
    public static final DeferredBlock<Block> ORE_TIN = registerBlock("ore_tin", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_TIN.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_tin")))));
    public static final DeferredBlock<Block> ORE_LEAD = registerBlock("ore_lead", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_LEAD.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_lead")))));
    public static final DeferredBlock<Block> ORE_ALUMINUM = registerBlock("ore_aluminum", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_ALUMINUM.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_aluminum")))));
    public static final DeferredBlock<Block> ORE_JADE = registerBlock("ore_jade", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_JADE.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_jade")))));
    public static final DeferredBlock<Block> ORE_ENCRUSTED_DIAMOND = registerBlock("ore_encrusted_diamond", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_ENCRUSTED_DIAMOND.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_encrusted_diamond")))));
    public static final DeferredBlock<Block> ORE_FOSSIL = registerBlock("ore_fossil", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_FOSSIL.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_fossil")))));
    public static final DeferredBlock<Block> ORE_GNEISS = registerBlock("ore_gneiss", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_GNEISS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_gneiss")))));
    public static final DeferredBlock<Block> ORE_PETRIFIED_WOOD = registerBlock("ore_petrified_wood", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_PETRIFIED_WOOD.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_petrified_wood")))));
    public static final DeferredBlock<Block> ORE_TEMPLE = registerBlock("ore_temple", () -> new DropExperienceBlock(UniformInt.of(2, 4), ModBlockProperties.ORE_TEMPLE.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("ore_temple")))));

    // MARK: Wood Logs
    public static final DeferredBlock<Block> LOG_ASPER = registerLog("log_asper", ModBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
    public static final DeferredBlock<Block> LOG_BALSAM = registerLog("log_balsam", ModBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
    public static final DeferredBlock<Block> LOG_BALSAM_RESINLESS = registerLog("log_balsam_resinless", ModBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
    public static final DeferredBlock<Block> LOG_BAOBAB = registerLog("log_baobab", ModBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
    public static final DeferredBlock<Block> LOG_CYPRESS = registerLog("log_cypress", ModBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
    public static final DeferredBlock<Block> LOG_EUCALYPTUS = registerLog("log_eucalyptus", ModBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
    public static final DeferredBlock<Block> LOG_MAHOGANY = registerLog("log_mahogany", ModBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
    public static final DeferredBlock<Block> LOG_MARSHWOOD = registerLog("log_marshwood", ModBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
    public static final DeferredBlock<Block> LOG_MOSSBARK = registerLog("log_mossbark", ModBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
    public static final DeferredBlock<Block> LOG_ROTTEN = registerLog("log_rotten", ModBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
    public static final DeferredBlock<Block> LOG_SCORCHED = registerLog("log_scorched", ModBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
    public static final DeferredBlock<Block> LOG_HOLLOW = registerBlock("log_hollow", () -> new HollowLogBlock(ModBlockProperties.LOG_HOLLOW.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("log_hollow")))));

    // MARK: Wood Saplings
    public static final DeferredBlock<Block> SAPLING_ASPER = registerSapling("sapling_asper", ModTreeGrowers.ASPER);
    public static final DeferredBlock<Block> SAPLING_BAOBAB = registerSapling("sapling_baobab", ModTreeGrowers.BAOBAB);
    public static final DeferredBlock<Block> SAPLING_BAMBOO = registerSapling("sapling_bamboo", ModTreeGrowers.BAMBOO);
    public static final DeferredBlock<Block> SAPLING_BALSAM = registerSapling("sapling_balsam", ModTreeGrowers.BALSAM);
    public static final DeferredBlock<Block> SAPLING_CYPRESS = registerSapling("sapling_cypress", ModTreeGrowers.CYPRESS);
    public static final DeferredBlock<Block> SAPLING_EUCALYPTUS = registerSapling("sapling_eucalyptus", ModTreeGrowers.EUCALYPTUS);
    public static final DeferredBlock<Block> SAPLING_MAHOGANY = registerSapling("sapling_mahogany", ModTreeGrowers.MAHOGANY);
    public static final DeferredBlock<Block> SAPLING_MARSHWOOD = registerSapling("sapling_marshwood", ModTreeGrowers.MARSHWOOD);
    public static final DeferredBlock<Block> SAPLING_MOSSBARK = registerSapling("sapling_mossbark", ModTreeGrowers.MOSSBARK);

    // MARK: Wood Leaves
    public static final DeferredBlock<Block> LEAVES_ASPER = registerBlock("leaves_asper", () -> new TintedParticleLeavesBlock(0.1F, ModBlockProperties.LEAVES.mapColor(MapColor.PLANT).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("leaves_asper")))));
    public static final DeferredBlock<Block> LEAVES_BAOBAB = registerBlock("leaves_baobab", () -> new TintedParticleLeavesBlock(0.1F, ModBlockProperties.LEAVES.mapColor(MapColor.PLANT).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("leaves_baobab")))));
    public static final DeferredBlock<Block> LEAVES_BALSAM = registerBlock("leaves_balsam", () -> new TintedParticleLeavesBlock(0.1F, ModBlockProperties.LEAVES.mapColor(MapColor.PLANT).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("leaves_balsam")))));
    public static final DeferredBlock<Block> LEAVES_CYPRESS = registerBlock("leaves_cypress", () -> new TintedParticleLeavesBlock(0.1F, ModBlockProperties.LEAVES.mapColor(MapColor.PLANT).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("leaves_cypress")))));
    public static final DeferredBlock<Block> LEAVES_EUCALYPTUS = registerBlock("leaves_eucalyptus", () -> new TintedParticleLeavesBlock(0.1F, ModBlockProperties.LEAVES.mapColor(MapColor.PLANT).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("leaves_eucalyptus")))));
    public static final DeferredBlock<Block> LEAVES_MAHOGANY = registerBlock("leaves_mahogany", () -> new TintedParticleLeavesBlock(0.1F, ModBlockProperties.LEAVES.mapColor(MapColor.PLANT).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("leaves_mahogany")))));
    public static final DeferredBlock<Block> LEAVES_MARSHWOOD = registerBlock("leaves_marshwood", () -> new TintedParticleLeavesBlock(0.1F, ModBlockProperties.LEAVES.mapColor(MapColor.PLANT).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("leaves_marshwood")))));
    public static final DeferredBlock<Block> LEAVES_MOSSBARK = registerBlock("leaves_mossbark", () -> new TintedParticleLeavesBlock(0.1F, ModBlockProperties.LEAVES.mapColor(MapColor.PLANT).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("leaves_mossbark")))));

    // MARK: Wood Planks
    public static final DeferredBlock<Block> PLANKS_ASPER = registerSimpleBlock("planks_asper", ModBlockProperties.PLANKS.mapColor(MapColor.WOOD));
    public static final DeferredBlock<Block> PLANKS_BAMBOO = registerSimpleBlock("planks_bamboo", ModBlockProperties.PLANKS.mapColor(MapColor.SAND));
    public static final DeferredBlock<Block> PLANKS_BAOBAB = registerSimpleBlock("planks_baobab", ModBlockProperties.PLANKS.mapColor(MapColor.TERRACOTTA_WHITE));
    public static final DeferredBlock<Block> PLANKS_BALSAM = registerSimpleBlock("planks_balsam", ModBlockProperties.PLANKS.mapColor(MapColor.TERRACOTTA_PINK));
    public static final DeferredBlock<Block> PLANKS_CYPRESS = registerSimpleBlock("planks_cypress", ModBlockProperties.PLANKS.mapColor(MapColor.TERRACOTTA_WHITE));
    public static final DeferredBlock<Block> PLANKS_EUCALYPTUS = registerSimpleBlock("planks_eucalyptus", ModBlockProperties.PLANKS.mapColor(MapColor.TERRACOTTA_PINK));
    public static final DeferredBlock<Block> PLANKS_MAHOGANY = registerSimpleBlock("planks_mahogany", ModBlockProperties.PLANKS.mapColor(MapColor.COLOR_BROWN));
    public static final DeferredBlock<Block> PLANKS_MARSHWOOD = registerSimpleBlock("planks_marshwood", ModBlockProperties.PLANKS.mapColor(MapColor.TERRACOTTA_GREEN));
    public static final DeferredBlock<Block> PLANKS_MOSSBARK = registerSimpleBlock("planks_mossbark", ModBlockProperties.PLANKS.mapColor(MapColor.COLOR_BROWN));
    public static final DeferredBlock<Block> PLANKS_PETRIFIED = registerSimpleBlock("planks_petrified", ModBlockProperties.PLANKS.mapColor(MapColor.TERRACOTTA_BROWN));
    public static final DeferredBlock<Block> PLANKS_ROTTEN = registerSimpleBlock("planks_rotten", ModBlockProperties.PLANKS.mapColor(MapColor.COLOR_BLACK));
    public static final DeferredBlock<Block> PLANKS_SCORCHED = registerSimpleBlock("planks_scorched", ModBlockProperties.PLANKS.mapColor(MapColor.COLOR_BLACK));
    public static final DeferredBlock<Block> PLANKS_VARNISHED = registerSimpleBlock("planks_varnished", ModBlockProperties.PLANKS.mapColor(MapColor.WOOD));
    public static final DeferredBlock<Block> PLANKS_WHITE = registerSimpleBlock("planks_white", ModBlockProperties.PLANKS.mapColor(MapColor.TERRACOTTA_WHITE));

    // MARK: Chests
    public static final DeferredBlock<Block> CHEST_ASPER = registerChest("chest_asper", ModBlockProperties.CHEST.mapColor(MapColor.WOOD));
    public static final DeferredBlock<Block> CHEST_BAMBOO = registerChest("chest_bamboo", ModBlockProperties.CHEST.mapColor(MapColor.SAND));
    public static final DeferredBlock<Block> CHEST_BAOBAB = registerChest("chest_baobab", ModBlockProperties.CHEST.mapColor(MapColor.TERRACOTTA_WHITE));
    public static final DeferredBlock<Block> CHEST_BALSAM = registerChest("chest_balsam", ModBlockProperties.CHEST.mapColor(MapColor.TERRACOTTA_PINK));
    public static final DeferredBlock<Block> CHEST_CYPRESS = registerChest("chest_cypress", ModBlockProperties.CHEST.mapColor(MapColor.TERRACOTTA_WHITE));
    public static final DeferredBlock<Block> CHEST_EUCALYPTUS = registerChest("chest_eucalyptus", ModBlockProperties.CHEST.mapColor(MapColor.TERRACOTTA_PINK));
    public static final DeferredBlock<Block> CHEST_MAHOGANY = registerChest("chest_mahogany", ModBlockProperties.CHEST.mapColor(MapColor.COLOR_BROWN));
    public static final DeferredBlock<Block> CHEST_MARSHWOOD = registerChest("chest_marshwood", ModBlockProperties.CHEST.mapColor(MapColor.TERRACOTTA_GREEN));
    public static final DeferredBlock<Block> CHEST_MOSSBARK = registerChest("chest_mossbark", ModBlockProperties.CHEST.mapColor(MapColor.COLOR_BROWN));
    public static final DeferredBlock<Block> CHEST_PETRIFIED = registerChest("chest_petrified", BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.5F)
            .sound(SoundType.WOOD)
            .mapColor(MapColor.TERRACOTTA_BROWN)
    );
    public static final DeferredBlock<Block> CHEST_ROTTEN = registerChest("chest_rotten", ModBlockProperties.CHEST.mapColor(MapColor.COLOR_BLACK));
    public static final DeferredBlock<Block> CHEST_SCORCHED = registerChest("chest_scorched", ModBlockProperties.CHEST.mapColor(MapColor.COLOR_BLACK));
    public static final DeferredBlock<Block> CHEST_VARNISHED = registerChest("chest_varnished", ModBlockProperties.CHEST.mapColor(MapColor.WOOD));
    public static final DeferredBlock<Block> CHEST_WHITE = registerChest("chest_white", ModBlockProperties.CHEST.mapColor(MapColor.TERRACOTTA_WHITE));

    // MARK: Slabs Wood
    public static final DeferredBlock<Block> SLAB_PLANKS_ASPER = registerSlab("slab_planks_asper", ModBlockProperties.SLAB_PLANKS.mapColor(MapColor.WOOD));
    public static final DeferredBlock<Block> SLAB_PLANKS_BAOBAB = registerSlab("slab_planks_baobab", ModBlockProperties.SLAB_PLANKS.mapColor(MapColor.TERRACOTTA_WHITE));
    public static final DeferredBlock<Block> SLAB_PLANKS_BALSAM = registerSlab("slab_planks_balsam", ModBlockProperties.SLAB_PLANKS.mapColor(MapColor.TERRACOTTA_PINK));
    public static final DeferredBlock<Block> SLAB_PLANKS_BAMBOO = registerSlab("slab_planks_bamboo", ModBlockProperties.SLAB_PLANKS.mapColor(MapColor.SAND));
    public static final DeferredBlock<Block> SLAB_PLANKS_CYPRESS = registerSlab("slab_planks_cypress", ModBlockProperties.SLAB_PLANKS.mapColor(MapColor.TERRACOTTA_WHITE));
    public static final DeferredBlock<Block> SLAB_PLANKS_EUCALYPTUS = registerSlab("slab_planks_eucalyptus", ModBlockProperties.SLAB_PLANKS.mapColor(MapColor.TERRACOTTA_PINK));
    public static final DeferredBlock<Block> SLAB_PLANKS_MAHOGANY = registerSlab("slab_planks_mahogany", ModBlockProperties.SLAB_PLANKS.mapColor(MapColor.COLOR_BROWN));
    public static final DeferredBlock<Block> SLAB_PLANKS_MARSHWOOD = registerSlab("slab_planks_marshwood", ModBlockProperties.SLAB_PLANKS.mapColor(MapColor.TERRACOTTA_GREEN));
    public static final DeferredBlock<Block> SLAB_PLANKS_MOSSBARK = registerSlab("slab_planks_mossbark", ModBlockProperties.SLAB_PLANKS.mapColor(MapColor.COLOR_BROWN));
    public static final DeferredBlock<Block> SLAB_PLANKS_PETRIFIED = registerSlab("slab_planks_petrified", ModBlockProperties.SLAB_PLANKS.mapColor(MapColor.TERRACOTTA_BROWN));
    public static final DeferredBlock<Block> SLAB_PLANKS_ROTTEN = registerSlab("slab_planks_rotten", ModBlockProperties.SLAB_PLANKS.mapColor(MapColor.COLOR_BLACK));
    public static final DeferredBlock<Block> SLAB_PLANKS_SCORCHED = registerSlab("slab_planks_scorched", ModBlockProperties.SLAB_PLANKS.mapColor(MapColor.COLOR_BLACK));
    public static final DeferredBlock<Block> SLAB_PLANKS_VARNISHED = registerSlab("slab_planks_varnished", ModBlockProperties.SLAB_PLANKS.mapColor(MapColor.WOOD));
    public static final DeferredBlock<Block> SLAB_PLANKS_WHITE = registerSlab("slab_planks_white", ModBlockProperties.SLAB_PLANKS.mapColor(MapColor.TERRACOTTA_WHITE));

    // MARK: Slabs Stone
    public static final DeferredBlock<Block> SLAB_AMBER = registerSlab("slab_amber", ModBlockProperties.SLAB_AMBER);
    public static final DeferredBlock<Block> SLAB_AMBER_BRICKS = registerSlab("slab_amber_bricks", ModBlockProperties.SLAB_AMBER_BRICKS);
    public static final DeferredBlock<Block> SLAB_MIR_BRICKS = registerSlab("slab_mir_bricks", ModBlockProperties.SLAB_STONE);
    public static final DeferredBlock<Block> SLAB_MUD_BRICKS = registerSlab("slab_mud_bricks", ModBlockProperties.SLAB_STONE);
    public static final DeferredBlock<Block> SLAB_UMBERCOBBLE = registerSlab("slab_umbercobble", ModBlockProperties.SLAB_STONE);
    public static final DeferredBlock<Block> SLAB_UMBERCOBBLE_MOSSY = registerSlab("slab_umbercobble_mossy", ModBlockProperties.SLAB_STONE);
    public static final DeferredBlock<Block> SLAB_UMBERCOBBLE_WEBBED = registerSlab("slab_umbercobble_webbed", ModBlockProperties.SLAB_STONE);
    public static final DeferredBlock<Block> SLAB_UMBERPAVER = registerSlab("slab_umberpaver", ModBlockProperties.SLAB_STONE);
    public static final DeferredBlock<Block> SLAB_UMBERPAVER_MOSSY = registerSlab("slab_umberpaver_mossy", ModBlockProperties.SLAB_STONE);
    public static final DeferredBlock<Block> SLAB_UMBERPAVER_WEBBED = registerSlab("slab_umberpaver_webbed", ModBlockProperties.SLAB_STONE);
    public static final DeferredBlock<Block> SLAB_UMBERSTONE = registerSlab("slab_umberstone", ModBlockProperties.SLAB_STONE);
    public static final DeferredBlock<Block> SLAB_UMBERSTONE_BRICKS = registerSlab("slab_umberstone_bricks", ModBlockProperties.SLAB_STONE);
    public static final DeferredBlock<Block> SLAB_UMBERTILE_SMOOTH = registerSlab("slab_umberstone_smooth", ModBlockProperties.SLAB_STONE);
    public static final DeferredBlock<Block> SLAB_UMBERTILE_SMOOTH_SMALL = registerSlab("slab_umberstone_smooth_small", ModBlockProperties.SLAB_STONE);

    // MARK: Stairs Wood
    public static final DeferredBlock<Block> STAIRS_ASPER = registerStairs("stairs_asper", PLANKS_ASPER, ModBlockProperties.STAIRS_ASPER);
    public static final DeferredBlock<Block> STAIRS_BALSAM = registerStairs("stairs_balsam", PLANKS_BALSAM, ModBlockProperties.STAIRS_BALSAM);
    public static final DeferredBlock<Block> STAIRS_BAMBOO = registerStairs("stairs_bamboo", PLANKS_BAMBOO, ModBlockProperties.STAIRS_BAMBOO);
    public static final DeferredBlock<Block> STAIRS_BAOBAB = registerStairs("stairs_baobab", PLANKS_BAOBAB, ModBlockProperties.STAIRS_BAOBAB);
    public static final DeferredBlock<Block> STAIRS_CYPRESS = registerStairs("stairs_cypress", PLANKS_CYPRESS, ModBlockProperties.STAIRS_CYPRESS);
    public static final DeferredBlock<Block> STAIRS_EUCALYPTUS = registerStairs("stairs_eucalyptus", PLANKS_EUCALYPTUS, ModBlockProperties.STAIRS_EUCALYPTUS);
    public static final DeferredBlock<Block> STAIRS_MAHOGANY = registerStairs("stairs_mahogany", PLANKS_MAHOGANY, ModBlockProperties.STAIRS_MAHOGANY);
    public static final DeferredBlock<Block> STAIRS_MARSHWOOD = registerStairs("stairs_marshwood", PLANKS_MARSHWOOD, ModBlockProperties.STAIRS_MARSHWOOD);
    public static final DeferredBlock<Block> STAIRS_MOSSBARK = registerStairs("stairs_mossbark", PLANKS_MOSSBARK, ModBlockProperties.STAIRS_MOSSBARK);
    public static final DeferredBlock<Block> STAIRS_PETRIFIED = registerStairs("stairs_petrified", PLANKS_PETRIFIED, ModBlockProperties.STAIRS_PETRIFIED);
    public static final DeferredBlock<Block> STAIRS_ROTTEN = registerStairs("stairs_rotten", PLANKS_ROTTEN, ModBlockProperties.STAIRS_ROTTEN);
    public static final DeferredBlock<Block> STAIRS_SCORCHED = registerStairs("stairs_scorched", PLANKS_SCORCHED, ModBlockProperties.STAIRS_SCORCHED);
    public static final DeferredBlock<Block> STAIRS_VARNISHED = registerStairs("stairs_varnished", PLANKS_VARNISHED, ModBlockProperties.STAIRS_VARNISHED);
    public static final DeferredBlock<Block> STAIRS_WHITE = registerStairs("stairs_white", PLANKS_WHITE, ModBlockProperties.STAIRS_WHITE);

    // MARK: Stairs Stone
    public static final DeferredBlock<Block> STAIRS_AMBER = registerStairs("stairs_amber", AMBER, ModBlockProperties.STAIRS_AMBER);
    public static final DeferredBlock<Block> STAIRS_AMBER_BRICKS = registerStairs("stairs_amber_bricks", AMBER_BRICKS, ModBlockProperties.STAIRS_AMBER_BRICKS);
    public static final DeferredBlock<Block> STAIRS_MIR_BRICKS = registerStairs("stairs_mir_bricks", MIR_BRICKS, ModBlockProperties.STAIRS_MIR_BRICKS);
    public static final DeferredBlock<Block> STAIRS_MUD_BRICKS = registerStairs("stairs_mud_bricks", MUD_BRICKS, ModBlockProperties.STAIRS_MUD_BRICKS);
    public static final DeferredBlock<Block> STAIRS_UMBERCOBBLE = registerStairs("stairs_umbercobble", UMBERCOBBLE, ModBlockProperties.STAIRS_UMBERCOBBLE);
    public static final DeferredBlock<Block> STAIRS_UMBERCOBBLE_MOSSY = registerStairs("stairs_umbercobble_mossy", UMBERCOBBLE_MOSSY, ModBlockProperties.STAIRS_UMBERCOBBLE_MOSSY);
    public static final DeferredBlock<Block> STAIRS_UMBERCOBBLE_WEBBED = registerStairs("stairs_umbercobble_webbed", UMBERCOBBLE_WEBBED, ModBlockProperties.STAIRS_UMBERCOBBLE_WEBBED);
    public static final DeferredBlock<Block> STAIRS_UMBERPAVER = registerStairs("stairs_umberpaver", UMBERPAVER, ModBlockProperties.STAIRS_UMBERPAVER);
    public static final DeferredBlock<Block> STAIRS_UMBERPAVER_MOSSY = registerStairs("stairs_umberpaver_mossy", UMBERPAVER_MOSSY, ModBlockProperties.STAIRS_UMBERPAVER_MOSSY);
    public static final DeferredBlock<Block> STAIRS_UMBERPAVER_WEBBED = registerStairs("stairs_umberpaver_webbed", UMBERPAVER_WEBBED, ModBlockProperties.STAIRS_UMBERPAVER_WEBBED);
    public static final DeferredBlock<Block> STAIRS_UMBERSTONE = registerStairs("stairs_umberstone", UMBERSTONE, ModBlockProperties.STAIRS_UMBERSTONE);
    public static final DeferredBlock<Block> STAIRS_UMBERSTONE_BRICKS = registerStairs("stairs_umberstone_bricks", UMBERSTONE_BRICKS, ModBlockProperties.STAIRS_UMBERSTONE_BRICKS);
    public static final DeferredBlock<Block> STAIRS_UMBERTILE_SMOOTH = registerStairs("stairs_umberstone_smooth", UMBERTILE_SMOOTH, ModBlockProperties.STAIRS_UMBERTILE_SMOOTH);
    public static final DeferredBlock<Block> STAIRS_UMBERTILE_SMOOTH_SMALL = registerStairs("stairs_umberstone_smooth_small", UMBERTILE_SMOOTH_SMALL, ModBlockProperties.STAIRS_UMBERTILE_SMOOTH_SMALL);

    // MARK: Walls
    public static final DeferredBlock<Block> WALL_UMBERSTONE = registerBlock("wall_umberstone", () -> new WallBlock(ModBlockProperties.WALL_UMBERSTONE.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("wall_umberstone")))));
    public static final DeferredBlock<Block> WALL_UMBERCOBBLE = registerBlock("wall_umbercobble", () -> new WallBlock(ModBlockProperties.WALL_UMBERCOBBLE.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("wall_umbercobble")))));
    public static final DeferredBlock<Block> WALL_UMBERCOBBLE_MOSSY = registerBlock("wall_umbercobble_mossy", () -> new WallBlock(ModBlockProperties.WALL_UMBERCOBBLE_MOSSY.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("wall_umbercobble_mossy")))));
    public static final DeferredBlock<Block> WALL_UMBERCOBBLE_WEBBED = registerBlock("wall_umbercobble_webbed", () -> new WallBlock(ModBlockProperties.WALL_UMBERCOBBLE_WEBBED.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("wall_umbercobble_webbed")))));
    public static final DeferredBlock<Block> WALL_UMBERSTONE_BRICKS = registerBlock("wall_umberstone_bricks", () -> new WallBlock(ModBlockProperties.WALL_UMBERSTONE_BRICKS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("wall_umberstone_bricks")))));
    public static final DeferredBlock<Block> WALL_UMBERTILE_SMOOTH = registerBlock("wall_umbertile_smooth", () -> new WallBlock(ModBlockProperties.WALL_UMBERTILE_SMOOTH.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("wall_umbertile_smooth")))));
    public static final DeferredBlock<Block> WALL_UMBERTILE_SMOOTH_SMALL = registerBlock("wall_umbertile_smooth_small", () -> new WallBlock(ModBlockProperties.WALL_UMBERTILE_SMOOTH_SMALL.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("wall_umbertile_smooth_small")))));
    public static final DeferredBlock<Block> WALL_AMBER = registerBlock("wall_amber", () -> new WallBlock(ModBlockProperties.WALL_AMBER.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("wall_amber")))));
    public static final DeferredBlock<Block> WALL_AMBER_BRICKS = registerBlock("wall_amber_bricks", () -> new WallBlock(ModBlockProperties.WALL_AMBER_BRICKS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("wall_amber_bricks")))));
    public static final DeferredBlock<Block> WALL_UMBERPAVER = registerBlock("wall_umberpaver", () -> new WallBlock(ModBlockProperties.WALL_UMBERPAVER.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("wall_umberpaver")))));
    public static final DeferredBlock<Block> WALL_UMBERPAVER_MOSSY = registerBlock("wall_umberpaver_mossy", () -> new WallBlock(ModBlockProperties.WALL_UMBERPAVER_MOSSY.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("wall_umberpaver_mossy")))));
    public static final DeferredBlock<Block> WALL_UMBERPAVER_WEBBED = registerBlock("wall_umberpaver_webbed", () -> new WallBlock(ModBlockProperties.WALL_UMBERPAVER_WEBBED.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("wall_umberpaver_webbed")))));

    // MARK: Doors
    public static final DeferredBlock<Block> DOOR_ASPER = registerDoor("door_asper", ModBlockSetTypes.ASPER, ModBlockProperties.DOOR_ASPER);
    public static final DeferredBlock<Block> DOOR_BALSAM = registerDoor("door_balsam", ModBlockSetTypes.BALSAM, ModBlockProperties.DOOR_BALSAM);
    public static final DeferredBlock<Block> DOOR_BAOBAB = registerDoor("door_baobab", ModBlockSetTypes.BAOBAB, ModBlockProperties.DOOR_BAOBAB);
    public static final DeferredBlock<Block> DOOR_CYPRESS = registerDoor("door_cypress", ModBlockSetTypes.CYPRESS, ModBlockProperties.DOOR_CYPRESS);
    public static final DeferredBlock<Block> DOOR_EUCALYPTUS = registerDoor("door_eucalyptus", ModBlockSetTypes.EUCALYPTUS, ModBlockProperties.DOOR_EUCALYPTUS);
    public static final DeferredBlock<Block> DOOR_MAHOGANY = registerDoor("door_mahogany", ModBlockSetTypes.MAHOGANY, ModBlockProperties.DOOR_MAHOGANY);
    public static final DeferredBlock<Block> DOOR_MARSHWOOD = registerDoor("door_marshwood", ModBlockSetTypes.MARSHWOOD, ModBlockProperties.DOOR_MARSHWOOD);
    public static final DeferredBlock<Block> DOOR_MOSSBARK = registerDoor("door_mossbark", ModBlockSetTypes.MOSSBARK, ModBlockProperties.DOOR_MOSSBARK);
    public static final DeferredBlock<Block> DOOR_ROTTEN = registerDoor("door_rotten", ModBlockSetTypes.ROTTEN, ModBlockProperties.DOOR_ROTTEN);
    public static final DeferredBlock<Block> DOOR_SCORCHED = registerDoor("door_scorched", ModBlockSetTypes.SCORCHED, ModBlockProperties.DOOR_SCORCHED);
    public static final DeferredBlock<Block> DOOR_WHITE = registerDoor("door_white", ModBlockSetTypes.WHITE, ModBlockProperties.DOOR_WHITE);

    // MARK: Fences
    public static final DeferredBlock<Block> FENCE_ASPER = registerFence("fence_asper", ModBlockProperties.FENCE_ASPER);
    public static final DeferredBlock<Block> FENCE_BAMBOO = registerFence("fence_bamboo", ModBlockProperties.FENCE_BAMBOO);
    public static final DeferredBlock<Block> FENCE_BAOBAB = registerFence("fence_baobab", ModBlockProperties.FENCE_BAOBAB);
    public static final DeferredBlock<Block> FENCE_BALSAM = registerFence("fence_balsam", ModBlockProperties.FENCE_BALSAM);
    public static final DeferredBlock<Block> FENCE_CYPRESS = registerFence("fence_cypress", ModBlockProperties.FENCE_CYPRESS);
    public static final DeferredBlock<Block> FENCE_EUCALYPTUS = registerFence("fence_eucalyptus", ModBlockProperties.FENCE_EUCALYPTUS);
    public static final DeferredBlock<Block> FENCE_MAHOGANY = registerFence("fence_mahogany", ModBlockProperties.FENCE_MAHOGANY);
    public static final DeferredBlock<Block> FENCE_MARSHWOOD = registerFence("fence_marshwood", ModBlockProperties.FENCE_MARSHWOOD);
    public static final DeferredBlock<Block> FENCE_MOSSBARK = registerFence("fence_mossbark", ModBlockProperties.FENCE_MOSSBARK);
    public static final DeferredBlock<Block> FENCE_ROTTEN = registerFence("fence_rotten", ModBlockProperties.FENCE_ROTTEN);
    public static final DeferredBlock<Block> FENCE_SCORCHED = registerFence("fence_scorched", ModBlockProperties.FENCE_SCORCHED);
    public static final DeferredBlock<Block> FENCE_VARNISHED = registerFence("fence_varnished", ModBlockProperties.FENCE_VARNISHED);
    public static final DeferredBlock<Block> FENCE_WHITE = registerFence("fence_white", ModBlockProperties.FENCE_WHITE);

    // MARK: Fence Gates
    public static final DeferredBlock<Block> FENCE_GATE_ASPER = registerFenceGate("fence_gate_asper", ModWoodTypes.ASPER, ModBlockProperties.FENCE_GATE_ASPER);
    public static final DeferredBlock<Block> FENCE_GATE_BALSAM = registerFenceGate("fence_gate_balsam", ModWoodTypes.BALSAM, ModBlockProperties.FENCE_GATE_BALSAM);
    public static final DeferredBlock<Block> FENCE_GATE_BAMBOO = registerFenceGate("fence_gate_bamboo", ModWoodTypes.BAMBOO, ModBlockProperties.FENCE_GATE_BAMBOO);
    public static final DeferredBlock<Block> FENCE_GATE_BAOBAB = registerFenceGate("fence_gate_baobab", ModWoodTypes.BAOBAB, ModBlockProperties.FENCE_GATE_BAOBAB);
    public static final DeferredBlock<Block> FENCE_GATE_CYPRESS = registerFenceGate("fence_gate_cypress", ModWoodTypes.CYPRESS, ModBlockProperties.FENCE_GATE_CYPRESS);
    public static final DeferredBlock<Block> FENCE_GATE_EUCALYPTUS = registerFenceGate("fence_gate_eucalyptus", ModWoodTypes.EUCALYPTUS, ModBlockProperties.FENCE_GATE_EUCALYPTUS);
    public static final DeferredBlock<Block> FENCE_GATE_MAHOGANY = registerFenceGate("fence_gate_mahogany", ModWoodTypes.MAHOGANY, ModBlockProperties.FENCE_GATE_MAHOGANY);
    public static final DeferredBlock<Block> FENCE_GATE_MARSHWOOD = registerFenceGate("fence_gate_marshwood", ModWoodTypes.MARSHWOOD, ModBlockProperties.FENCE_GATE_MARSHWOOD);
    public static final DeferredBlock<Block> FENCE_GATE_MOSSBARK = registerFenceGate("fence_gate_mossbark", ModWoodTypes.MOSSBARK, ModBlockProperties.FENCE_GATE_MOSSBARK);
    public static final DeferredBlock<Block> FENCE_GATE_ROTTEN = registerFenceGate("fence_gate_rotten", ModWoodTypes.ROTTEN, ModBlockProperties.FENCE_GATE_ROTTEN);
    public static final DeferredBlock<Block> FENCE_GATE_SCORCHED = registerFenceGate("fence_gate_scorched", ModWoodTypes.SCORCHED, ModBlockProperties.FENCE_GATE_SCORCHED);
    public static final DeferredBlock<Block> FENCE_GATE_VARNISHED = registerFenceGate("fence_gate_varnished", ModWoodTypes.VARNISHED, ModBlockProperties.FENCE_GATE_VARNISHED);
    public static final DeferredBlock<Block> FENCE_GATE_WHITE = registerFenceGate("fence_gate_white", ModWoodTypes.WHITE, ModBlockProperties.FENCE_GATE_WHITE);

    // MARK: Fluids
    public static final DeferredBlock<LiquidBlock> FLUID_FORMIC_ACID_BLOCK = registerBlock("formic_acid", () -> new FormicAcidFluidBlock(ModFluids.FORMIC_ACID_STILL.get(), ModBlockProperties.FORMIC_ACID.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("formic_acid")))));
    public static final DeferredBlock<LiquidBlock> FLUID_HONEY_BLOCK = registerBlock("honey", () -> new LiquidBlock(ModFluids.HONEY_STILL.get(), ModBlockProperties.HONEY.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("honey")))));
    public static final DeferredBlock<LiquidBlock> FLUID_BEETLE_JUICE_BLOCK = registerBlock("beetle_juice", () -> new LiquidBlock(ModFluids.BEETLE_JUICE_STILL.get(), ModBlockProperties.BEETLE_JUICE.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("beetle_juice")))));
    public static final DeferredBlock<LiquidBlock> FLUID_ANTI_VENOM_BLOCK = registerBlock("anti_venom", () -> new LiquidBlock(ModFluids.ANTI_VENOM_STILL.get(), ModBlockProperties.ANTI_VENOM.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("anti_venom")))));

    // MARK: Other
    public static final DeferredBlock<Block> PORTAL = registerBlock("portal", () -> new ErebusPortalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_PORTAL).noCollision().randomTicks().strength(-1.0F).pushReaction(PushReaction.BLOCK).lightLevel((_) -> 11).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("portal")))));
    public static final DeferredBlock<Block> GAEAN_KEYSTONE = registerBlock("gaean_keystone", () -> new GaeanKeystoneBlock(ModBlockProperties.GAEAN_KEYSTONE.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("gaean_keystone")))));
    public static final DeferredBlock<Block> JADE_BLOCK = registerSimpleBlock("jade_block", ModBlockProperties.JADE_BLOCK);
    public static final DeferredBlock<Block> MUD = registerBlock("mud", () -> new MudBlock(ModBlockProperties.MUD.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("mud")))) {
        @Override
        public @NotNull TriState canSustainPlant(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos soilPosition, @NotNull Direction facing, @NotNull BlockState plant) {
            return TriState.TRUE;
        }
    });
    public static final DeferredBlock<Block> QUICK_SAND = registerBlock("quick_sand", () -> new QuicksandBlock(ModBlockProperties.QUICK_SAND.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("quick_sand")))));
    public static final DeferredBlock<Block> GHOST_SAND = registerSimpleBlock("ghost_sand", ModBlockProperties.GHOST_SAND);
    public static final DeferredBlock<Block> SWAMP_VENT = registerBlock("swamp_vent", () -> new SwampVentBlock(ModBlockProperties.SWAMP_VENT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("swamp_vent")))));
    public static final DeferredBlock<Block> GNEISS_VENT = registerSimpleBlock("gneiss_vent", ModBlockProperties.GNEISS_VENT);
    public static final DeferredBlock<Block> RED_GEM_BLOCK = registerSimpleBlock("red_gem_block", ModBlockProperties.RED_GEM_BLOCK);
    public static final DeferredBlock<Block> RED_GEM_LAMP = registerBlock("red_gem_lamp", () -> new RedstoneLampBlock(ModBlockProperties.RED_GEM_LAMP.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("red_gem_lamp")))));
    public static final DeferredBlock<Block> WITHER_WEB = registerBlock("wither_web", () -> new WitherWebBlock(ModBlockProperties.WITHER_WEB.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("wither_web")))));
    public static final DeferredBlock<Block> LAVA_WEB = registerBlock("lava_web", () -> new LavaWebBlock(ModBlockProperties.LAVA_WEB.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("lava_web")))));
    public static final DeferredBlock<Block> GNEISS = registerSimpleBlock("gneiss", ModBlockProperties.GNEISS);
    public static final DeferredBlock<Block> GNEISS_CARVED = registerSimpleBlock("gneiss_carved", ModBlockProperties.GNEISS_CARVED);
    public static final DeferredBlock<Block> GNEISS_RELIEF = registerSimpleBlock("gneiss_relief", ModBlockProperties.GNEISS_RELIEF);
    public static final DeferredBlock<Block> GNEISS_BRICKS = registerSimpleBlock("gneiss_bricks", ModBlockProperties.GNEISS_BRICKS);
    public static final DeferredBlock<Block> GNEISS_SMOOTH = registerSimpleBlock("gneiss_smooth", ModBlockProperties.GNEISS_SMOOTH);
    public static final DeferredBlock<Block> GNEISS_TILES = registerSimpleBlock("gneiss_tiles", ModBlockProperties.GNEISS_TILES);
    public static final DeferredBlock<Block> GNEISS_TILES_CRACKED = registerSimpleBlock("gneiss_tiles_cracked", ModBlockProperties.GNEISS_TILES_CRACKED);
    public static final DeferredBlock<Block> TEMPLE_BRICK = registerSimpleBlock("temple_brick", ModBlockProperties.TEMPLE_BRICK);
    public static final DeferredBlock<Block> TEMPLE_PILLAR = registerSimpleBlock("temple_pillar", ModBlockProperties.TEMPLE_PILLAR);
    public static final DeferredBlock<Block> TEMPLE_TILE = registerSimpleBlock("temple_tile", ModBlockProperties.TEMPLE_TILE);
    public static final DeferredBlock<Block> SILK = registerSimpleBlock("silk", ModBlockProperties.SILK);
    public static final DeferredBlock<Block> REIN_EXO = registerSimpleBlock("rein_exo", ModBlockProperties.REIN_EXO);
    public static final DeferredBlock<Block> VELOCITY_BLOCK = registerBlock("velocity_block", () -> new VelocityBlock(ModBlockProperties.VELOCITY_BLOCK.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("velocity_block")))));
    public static final DeferredBlock<Block> VELOCITY_BLOCK_LIGHTNING_SPEED = registerBlock("velocity_block_lightning_speed", () -> new VelocityBlockLightningSpeed(ModBlockProperties.VELOCITY_BLOCK_LIGHTNING_SPEED.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("velocity_block_lightning_speed")))));
    public static final DeferredBlock<Block> BLOCK_OF_BONES = registerBlock("block_of_bones", () -> new BlockOfBonesBlock(ModBlockProperties.BLOCK_OF_BONES.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("block_of_bones")))));
    public static final DeferredBlock<Block> ANTLION_EGG = registerSimpleBlock("antlion_egg", ModBlockProperties.ANTLION_EGG);
    public static final DeferredBlock<Block> TARANTULA_EGG = registerSimpleBlock("tarantula_egg", ModBlockProperties.TARANTULA_EGG);
    public static final DeferredBlock<Block> HONEY_TREAT = registerBlock("honey_treat", () -> new HoneyTreatBlock(ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("honey_treat")))));
    public static final DeferredBlock<Block> CANDLE_HONEY_TREAT = registerBlock("candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.CANDLE, ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("candle_honey_treat")))));
    public static final DeferredBlock<Block> WHITE_CANDLE_HONEY_TREAT = registerBlock("white_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.WHITE_CANDLE, ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("white_candle_honey_treat")))));
    public static final DeferredBlock<Block> ORANGE_CANDLE_HONEY_TREAT = registerBlock("orange_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.ORANGE_CANDLE, ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("orange_candle_honey_treat")))));
    public static final DeferredBlock<Block> MAGENTA_CANDLE_HONEY_TREAT = registerBlock("magenta_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.MAGENTA_CANDLE, ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("magenta_candle_honey_treat")))));
    public static final DeferredBlock<Block> LIGHT_BLUE_CANDLE_HONEY_TREAT = registerBlock("light_blue_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.LIGHT_BLUE_CANDLE, ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("light_blue_candle_honey_treat")))));
    public static final DeferredBlock<Block> YELLOW_CANDLE_HONEY_TREAT = registerBlock("yellow_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.YELLOW_CANDLE, ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("yellow_candle_honey_treat")))));
    public static final DeferredBlock<Block> LIME_CANDLE_HONEY_TREAT = registerBlock("lime_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.LIME_CANDLE, ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("lime_candle_honey_treat")))));
    public static final DeferredBlock<Block> PINK_CANDLE_HONEY_TREAT = registerBlock("pink_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.PINK_CANDLE, ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("pink_candle_honey_treat")))));
    public static final DeferredBlock<Block> GRAY_CANDLE_HONEY_TREAT = registerBlock("gray_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.GRAY_CANDLE, ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("gray_candle_honey_treat")))));
    public static final DeferredBlock<Block> LIGHT_GRAY_CANDLE_HONEY_TREAT = registerBlock("light_gray_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.LIGHT_GRAY_CANDLE, ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("light_gray_candle_honey_treat")))));
    public static final DeferredBlock<Block> CYAN_CANDLE_HONEY_TREAT = registerBlock("cyan_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.CYAN_CANDLE, ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("cyan_candle_honey_treat")))));
    public static final DeferredBlock<Block> PURPLE_CANDLE_HONEY_TREAT = registerBlock("purple_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.PURPLE_CANDLE, ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("purple_candle_honey_treat")))));
    public static final DeferredBlock<Block> BLUE_CANDLE_HONEY_TREAT = registerBlock("blue_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.BLUE_CANDLE, ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("blue_candle_honey_treat")))));
    public static final DeferredBlock<Block> BROWN_CANDLE_HONEY_TREAT = registerBlock("brown_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.BROWN_CANDLE, ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("brown_candle_honey_treat")))));
    public static final DeferredBlock<Block> GREEN_CANDLE_HONEY_TREAT = registerBlock("green_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.GREEN_CANDLE, ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("green_candle_honey_treat")))));
    public static final DeferredBlock<Block> RED_CANDLE_HONEY_TREAT = registerBlock("red_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.RED_CANDLE, ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("red_candle_honey_treat")))));
    public static final DeferredBlock<Block> BLACK_CANDLE_HONEY_TREAT = registerBlock("black_candle_honey_treat", () -> new CandleHoneyTreatBlock(Blocks.BLACK_CANDLE, ModBlockProperties.HONEY_TREAT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("black_candle_honey_treat")))));
    public static final DeferredBlock<Block> WASP_NEST = registerSimpleBlock("wasp_nest", ModBlockProperties.WASP_NEST);
    public static final DeferredBlock<Block> STAIRS_WASP_NEST = registerBlock(
            "stairs_wasp_nest",
            () -> new StairBlock(
                    WASP_NEST.get().defaultBlockState(),
                    ModBlockProperties.STAIRS_WASP_NEST.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("stairs_wasp_nest")))
            )
    );
    public static final DeferredBlock<Block> INSECT_REPELLENT = registerBlock("insect_repellent", () -> new InsectRepellentBlock(ModBlockProperties.INSECT_REPELLENT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("insect_repellent")))));

    // MARK: Spawners
    public static final DeferredBlock<Block> ANTLION_SPAWNER = registerSimpleBlock("antlion_spawner", ModBlockProperties.ANTLION_SPAWNER);
    public static final DeferredBlock<Block> DRAGON_FLY_SPAWNER = registerSimpleBlock("dragon_fly_spawner", ModBlockProperties.DRAGON_FLY_SPAWNER);
    public static final DeferredBlock<Block> JUMPING_SPIDER_SPAWNER = registerSimpleBlock("jumping_spider_spawner", ModBlockProperties.JUMPING_SPIDER_SPAWNER);
    public static final DeferredBlock<Block> SPIDER_SPAWNER = registerSimpleBlock("spider_spawner", ModBlockProperties.SPIDER_SPAWNER);
    public static final DeferredBlock<Block> TARANTULA_SPAWNER = registerSimpleBlock("tarantula_spawner", ModBlockProperties.TARANTULA_SPAWNER);
    public static final DeferredBlock<Block> WASP_SPAWNER = registerSimpleBlock("wasp_spawner", ModBlockProperties.WASP_SPAWNER);
    public static final DeferredBlock<Block> ZOMBIE_ANT_SPAWNER = registerSimpleBlock("zombie_ant_spawner", ModBlockProperties.ZOMBIE_ANT_SPAWNER);
    public static final DeferredBlock<Block> ZOMBIE_ANT_SOLDIER_SPAWNER = registerSimpleBlock("zombie_ant_soldier_spawner", ModBlockProperties.ZOMBIE_ANT_SOLDIER_SPAWNER);
    public static final DeferredBlock<Block> MAGMA_CRAWLER_SPAWNER = registerSimpleBlock("magma_crawler_spawner", ModBlockProperties.MAGMA_CRAWLER_SPAWNER);
    public static final DeferredBlock<Block> DUNG_SPAWNER_FLY = registerSimpleBlock("dung_spawner_fly", ModBlockProperties.DUNG_SPAWNER_FLY);
    public static final DeferredBlock<Block> DUNG_SPAWNER_BOT_FLY = registerBlock("dung_spawner_bot_fly", () -> new BotFlySpawnerBlock(ModBlockProperties.DUNG_SPAWNER_BOT_FLY.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("dung_spawner_bot_fly")))));
    public static final DeferredBlock<Block> LOCUST_SPAWNER = registerSimpleBlock("locust_spawner", ModBlockProperties.LOCUST_SPAWNER);

    // MARK: Plants
    public static final DeferredBlock<Block> CROP_TURNIP = registerCrop("crop_turnip", ModItems.TURNIP, ModBlockProperties.CROP_PROPS);
    public static final DeferredBlock<Block> CROP_CABBAGE = registerCrop("crop_cabbage", ModItems.CABBAGE_SEEDS, ModBlockProperties.CROP_PROPS);
    public static final DeferredBlock<Block> CROP_MANDRAKE = registerCrop("crop_mandrake", ModItems.MANDRAKE_ROOT, ModBlockProperties.CROP_PROPS);
    public static final DeferredBlock<Block> JADE_BERRY_BUSH = registerBush("jade_berry_bush", ModItems.JADE_BERRIES, BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH));
    public static final DeferredBlock<Block> HEART_BERRY_BUSH = registerBush("heart_berry_bush", ModItems.HEART_BERRIES, ModBlockProperties.BUSH_PROPS);
    public static final DeferredBlock<Block> SWAMP_BERRY_BUSH = registerBush("swamp_berry_bush", ModItems.SWAMP_BERRIES, BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH));
    public static final DeferredBlock<Block> DARK_FRUIT_VINE = registerBlock("dark_fruit_vine", () -> new DarkFruitVineBlock(ModBlockProperties.DARK_FRUIT_VINE_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("dark_fruit_vine")))));
    public static final DeferredBlock<Block> PRICKLY_PEAR = registerBlock("prickly_pear", () -> new PricklyPearBlock(ModBlockProperties.PRICKLY_PEAR_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("prickly_pear")))));
    public static final DeferredBlock<Block> COLOSSAL_BAMBOO = registerBlock("colossal_bamboo", () -> new BambooBlock(ModBlockProperties.COLOSSAL_BAMBOO_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("colossal_bamboo")))));
    public static final DeferredBlock<Block> DARK_CAPPED_MUSHROOM = registerBlock("dark_capped_mushroom", () -> new MushroomBlock(PlantFeatures.DARK_CAPPED_MUSHROOM.getConfiguredResourceKey(), ModBlockProperties.DARK_CAPPED_MUSHROOM_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("dark_capped_mushroom")))));
    public static final DeferredBlock<Block> DUTCH_CAP_MUSHROOM = registerBlock("dutch_cap_mushroom", () -> new MushroomBlock(PlantFeatures.DUTCH_CAP_MUSHROOM.getConfiguredResourceKey(), ModBlockProperties.DUTCH_CAP_MUSHROOM_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("dutch_cap_mushroom")))));
    public static final DeferredBlock<Block> GRANDMAS_SHOES_MUSHROOM = registerBlock("grandmas_shoes_mushroom", () -> new MushroomBlock(PlantFeatures.GRANDMAS_SHOES_MUSHROOM.getConfiguredResourceKey(), ModBlockProperties.GRANDMAS_SHOES_MUSHROOM_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("grandmas_shoes_mushroom")))));
    public static final DeferredBlock<Block> KAIZERS_FINGERS_MUSHROOM = registerBlock("kaizers_fingers_mushroom", () -> new MushroomBlock(PlantFeatures.KAIZERS_FINGERS_MUSHROOM.getConfiguredResourceKey(), ModBlockProperties.KAIZERS_FINGERS_MUSHROOM_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("kaizers_fingers_mushroom")))));
    public static final DeferredBlock<Block> SARCASTIC_CZECH_MUSHROOM = registerBlock("sarcastic_czech_mushroom", () -> new MushroomBlock(PlantFeatures.SARCASTIC_CZECH_MUSHROOM.getConfiguredResourceKey(), ModBlockProperties.SARCASTIC_CZECH_MUSHROOM_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("sarcastic_czech_mushroom")))));
    public static final DeferredBlock<Block> DARK_CAPPED_MUSHROOM_BLOCK = registerHugeMushroom("dark_capped_mushroom_block", ModBlockProperties.DARK_CAPPED_MUSHROOM_BLOCK_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("dark_capped_mushroom_block"))));
    public static final DeferredBlock<Block> DARK_CAPPED_MUSHROOM_STEM = registerHugeMushroom("dark_capped_mushroom_stem", ModBlockProperties.DARK_CAPPED_MUSHROOM_BLOCK_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("dark_capped_mushroom_stem"))));
    public static final DeferredBlock<Block> DUTCH_CAP_MUSHROOM_BLOCK = registerHugeMushroom("dutch_cap_mushroom_block", ModBlockProperties.DUTCH_CAP_MUSHROOM_BLOCK_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("dutch_cap_mushroom_block"))));
    public static final DeferredBlock<Block> DUTCH_CAP_MUSHROOM_STEM = registerHugeMushroom("dutch_cap_mushroom_stem", ModBlockProperties.DUTCH_CAP_MUSHROOM_BLOCK_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("dutch_cap_mushroom_stem"))));
    public static final DeferredBlock<Block> GRANDMAS_SHOES_MUSHROOM_BLOCK = registerHugeMushroom("grandmas_shoes_mushroom_block", ModBlockProperties.GRANDMAS_SHOES_MUSHROOM_BLOCK_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("grandmas_shoes_mushroom_block"))));
    public static final DeferredBlock<Block> GRANDMAS_SHOES_MUSHROOM_STEM = registerHugeMushroom("grandmas_shoes_mushroom_stem", ModBlockProperties.GRANDMAS_SHOES_MUSHROOM_BLOCK_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("grandmas_shoes_mushroom_stem"))));
    public static final DeferredBlock<Block> KAIZERS_FINGERS_MUSHROOM_BLOCK = registerHugeMushroom("kaizers_fingers_mushroom_block", ModBlockProperties.KAIZERS_FINGERS_MUSHROOM_BLOCK_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("kaizers_fingers_mushroom_block"))));
    public static final DeferredBlock<Block> KAIZERS_FINGERS_MUSHROOM_STEM = registerHugeMushroom("kaizers_fingers_mushroom_stem", ModBlockProperties.KAIZERS_FINGERS_MUSHROOM_BLOCK_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("kaizers_fingers_mushroom_stem"))));
    public static final DeferredBlock<Block> SARCASTIC_CZECH_MUSHROOM_BLOCK = registerHugeMushroom("sarcastic_czech_mushroom_block", ModBlockProperties.SARCASTIC_CZECH_MUSHROOM_BLOCK_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("sarcastic_czech_mushroom_block"))));
    public static final DeferredBlock<Block> SARCASTIC_CZECH_MUSHROOM_STEM = registerHugeMushroom("sarcastic_czech_mushroom_stem", ModBlockProperties.SARCASTIC_CZECH_MUSHROOM_BLOCK_PROPS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("sarcastic_czech_mushroom_stem"))));
    public static final DeferredBlock<Block> GIANT_LILY_PAD = registerSimpleBlock("giant_lily_pad", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> DESERT_SHRUB = registerSimpleBlock("desert_shrub", BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS));
    public static final DeferredBlock<Block> MIRE_CORAL = registerSimpleBlock("mire_coral", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> NETTLE = registerSimpleBlock("nettle", BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS));
    public static final DeferredBlock<Block> NETTLE_FLOWERED = registerSimpleBlock("nettle_flowered", BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS));
    public static final DeferredBlock<Block> SWAMP_PLANT = registerSimpleBlock("swamp_plant", BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS));
    public static final DeferredBlock<Block> FIRE_BLOOM = registerSimpleBlock("fire_bloom", BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS));
    public static final DeferredBlock<Block> FERN = registerSimpleBlock("fern", BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS));
    public static final DeferredBlock<Block> FIDDLE_HEAD = registerSimpleBlock("fiddle_head", BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS));
    public static final DeferredBlock<Block> THORNS = registerBlock("thorns", () -> new VineBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("thorns")))));
    public static final DeferredBlock<Block> MOSS = registerBlock("moss", () -> new MossBlock(BlockBehaviour.Properties.of().strength(0.2F).noCollision().randomTicks().sound(SoundType.VINE).noOcclusion().replaceable().setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("moss")))));
    public static final DeferredBlock<Block> MOULD = registerBlock("mould", () -> new MouldBlock(BlockBehaviour.Properties.of().strength(0.2F).noCollision().randomTicks().sound(SoundType.VINE).noOcclusion().replaceable().setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("mould")))));
    public static final DeferredBlock<Block> MOSS_CULTIVATED = registerBlock("moss_cultivated", () -> new MossCultivatedBlock(BlockBehaviour.Properties.of().strength(0.2F).noCollision().sound(SoundType.VINE).noOcclusion().replaceable().setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("moss_cultivated")))));
    public static final DeferredBlock<Block> MOULD_CULTIVATED = registerBlock("mould_cultivated", () -> new MouldCultivatedBlock(BlockBehaviour.Properties.of().strength(0.2F).noCollision().sound(SoundType.VINE).noOcclusion().replaceable().setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("mould_cultivated")))));
    public static final DeferredBlock<Block> ALGAE = registerBlock(
            "algae",
            () -> new LilyPadBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .instabreak()
                    .sound(SoundType.LILY_PAD)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
                    .setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("algae")))
            )
    );
    public static final DeferredBlock<Block> GLOWSHROOM_BLOCK = registerBlock("glowshroom_block", () -> new GlowshroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.FUNGUS).strength(0.2F).lightLevel((_) -> 15).noOcclusion().setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("glowshroom_block")))));
    public static final DeferredBlock<Block> GLOWSHROOM_STALK = registerBlock("glowshroom_stalk", () -> new GlowshroomStalkBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.FUNGUS).strength(0.2F).instabreak().noOcclusion().randomTicks().setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("glowshroom_stalk")))));
    public static final DeferredBlock<Block> HANGING_WEB = registerSimpleBlock("hanging_web", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).noOcclusion());

    // MARK: Flowers
    public static final DeferredBlock<Block> PETAL_BLACK = registerSimpleBlock("petal_black", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK));
    public static final DeferredBlock<Block> PETAL_RED = registerSimpleBlock("petal_red", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED));
    public static final DeferredBlock<Block> PETAL_BROWN = registerSimpleBlock("petal_brown", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN));
    public static final DeferredBlock<Block> PETAL_BLUE = registerSimpleBlock("petal_blue", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE));
    public static final DeferredBlock<Block> PETAL_PURPLE = registerSimpleBlock("petal_purple", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE));
    public static final DeferredBlock<Block> PETAL_CYAN = registerSimpleBlock("petal_cyan", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN));
    public static final DeferredBlock<Block> PETAL_LIGHT_GRAY = registerSimpleBlock("petal_light_gray", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY));
    public static final DeferredBlock<Block> PETAL_GRAY = registerSimpleBlock("petal_gray", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY));
    public static final DeferredBlock<Block> PETAL_PINK = registerSimpleBlock("petal_pink", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK));
    public static final DeferredBlock<Block> PETAL_YELLOW = registerSimpleBlock("petal_yellow", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> PETAL_LIGHT_BLUE = registerSimpleBlock("petal_light_blue", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> PETAL_MAGENTA = registerSimpleBlock("petal_magenta", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> PETAL_ORANGE = registerSimpleBlock("petal_orange", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE));
    public static final DeferredBlock<Block> PETAL_WHITE = registerSimpleBlock("petal_white", BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE));

    public static final DeferredBlock<Block> EXPLODING_STIGMA = registerSimpleBlock("exploding_stigma", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> STEM = registerSimpleBlock("stem", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN));
    public static final DeferredBlock<Block> STIGMA_BLACK = registerSimpleBlock("stigma_black", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> STIGMA_RED = registerSimpleBlock("stigma_red", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> STIGMA_BROWN = registerSimpleBlock("stigma_brown", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> STIGMA_BLUE = registerSimpleBlock("stigma_blue", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> STIGMA_PURPLE = registerSimpleBlock("stigma_purple", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> STIGMA_CYAN = registerSimpleBlock("stigma_cyan", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> STIGMA_LIGHT_GRAY = registerSimpleBlock("stigma_light_gray", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> STIGMA_GRAY = registerSimpleBlock("stigma_gray", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> STIGMA_PINK = registerSimpleBlock("stigma_pink", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> STIGMA_YELLOW = registerSimpleBlock("stigma_yellow", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> STIGMA_LIGHT_BLUE = registerSimpleBlock("stigma_light_blue", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> STIGMA_MAGENTA = registerSimpleBlock("stigma_magenta", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> STIGMA_ORANGE = registerSimpleBlock("stigma_orange", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> STIGMA_WHITE = registerSimpleBlock("stigma_white", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));

    public static final DeferredBlock<Block> FLOWER_BLACK = registerSimpleBlock("flower_black", ModBlockProperties.FLOWER_BLACK_PROPS);
    public static final DeferredBlock<Block> FLOWER_RED = registerSimpleBlock("flower_red", ModBlockProperties.FLOWER_RED_PROPS);
    public static final DeferredBlock<Block> FLOWER_BROWN = registerSimpleBlock("flower_brown", ModBlockProperties.FLOWER_BROWN_PROPS);
    public static final DeferredBlock<Block> FLOWER_BLUE = registerSimpleBlock("flower_blue", ModBlockProperties.FLOWER_BLUE_PROPS);
    public static final DeferredBlock<Block> FLOWER_PURPLE = registerSimpleBlock("flower_purple", ModBlockProperties.FLOWER_PURPLE_PROPS);
    public static final DeferredBlock<Block> FLOWER_CYAN = registerSimpleBlock("flower_cyan", ModBlockProperties.FLOWER_CYAN_PROPS);
    public static final DeferredBlock<Block> FLOWER_LIGHT_GRAY = registerSimpleBlock("flower_light_gray", ModBlockProperties.FLOWER_LIGHT_GRAY_PROPS);
    public static final DeferredBlock<Block> FLOWER_GRAY = registerSimpleBlock("flower_gray", ModBlockProperties.FLOWER_GRAY_PROPS);
    public static final DeferredBlock<Block> FLOWER_PINK = registerSimpleBlock("flower_pink", ModBlockProperties.FLOWER_PINK_PROPS);
    public static final DeferredBlock<Block> FLOWER_YELLOW = registerSimpleBlock("flower_yellow", ModBlockProperties.FLOWER_YELLOW_PROPS);
    public static final DeferredBlock<Block> FLOWER_LIGHT_BLUE = registerSimpleBlock("flower_light_blue", ModBlockProperties.FLOWER_LIGHT_BLUE_PROPS);
    public static final DeferredBlock<Block> FLOWER_MAGENTA = registerSimpleBlock("flower_magenta", ModBlockProperties.FLOWER_MAGENTA_PROPS);
    public static final DeferredBlock<Block> FLOWER_ORANGE = registerSimpleBlock("flower_orange", ModBlockProperties.FLOWER_ORANGE_PROPS);
    public static final DeferredBlock<Block> FLOWER_WHITE = registerSimpleBlock("flower_white", ModBlockProperties.FLOWER_WHITE_PROPS);
    public static final DeferredBlock<Block> FLOWER_RAINBOW = registerSimpleBlock("flower_rainbow", ModBlockProperties.FLOWER_RAINBOW_PROPS);

    // MARK: Flowers Double Height
    public static final DeferredBlock<Block> BULLRUSH = registerDoublePlant("bullrush", BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));
    public static final DeferredBlock<Block> WEEPING_BLUEBELL = registerDoublePlant("weeping_bluebell", BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));
    public static final DeferredBlock<Block> SUNDEW = registerDoublePlant("sundew", BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));
    public static final DeferredBlock<Block> DROUGHTED_SHRUB = registerDoublePlant("droughted_shrub", BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));
    public static final DeferredBlock<Block> TALL_BLOOM = registerDoublePlant("tall_bloom", BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));
    public static final DeferredBlock<Block> TANGLED_STALK = registerDoublePlant("tangled_stalk", BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));
    public static final DeferredBlock<Block> HIGH_CAPPED_MUSHROOM = registerDoublePlant("high_capped_mushroom", BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));
    public static final DeferredBlock<Block> TALL_FERN = registerDoublePlant("tall_fern", BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));

    // MARK: Utility Blocks
    public static final DeferredBlock<Block> PETRIFIED_CRAFTING_TABLE = registerBlock("petrified_crafting_table", () -> new PetrifiedCraftingTableBlock(ModBlockProperties.PETRIFIED_CRAFTING_TABLE.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("petrified_crafting_table")))));
    public static final DeferredBlock<Block> BAMBOO_CRATE = registerBlockWithoutBlockItem("bamboo_crate", () -> new BambooCrateBlock(ModBlockProperties.BAMBOO_CRATE.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("bamboo_crate")))));
    public static final DeferredBlock<Block> BAMBOO_BRIDGE = registerBlock("bamboo_bridge", () -> new BambooBridge(ModBlockProperties.BAMBOO_BRIDGE.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("bamboo_bridge")))));
    public static final DeferredBlock<Block> BAMBOO_LADDER = registerBlock("bamboo_ladder", () -> new LadderBlock(ModBlockProperties.BAMBOO_LADDER.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("bamboo_ladder")))));
    public static final DeferredBlock<Block> BAMBOO_NERD_POLE = registerBlock("bamboo_nerd_pole", () -> new BambooPole(ModBlockProperties.BAMBOO_NERD_POLE.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("bamboo_nerd_pole")))));
    public static final DeferredBlock<Block> BAMBOO_EXTENDER = registerBlock("bamboo_extender", () -> new BambooExtender(ModBlockProperties.BAMBOO_EXTENDER.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("bamboo_extender")))));
    public static final DeferredBlock<Block> BAMBOO_TORCH = registerBlock("bamboo_torch", () -> new BambooTorchBlock(ModBlockProperties.BAMBOO_TORCH.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("bamboo_torch")))));
    public static final DeferredBlock<Block> BAMBOO_PIPE = registerBlock("bamboo_pipe", () -> new BambooPipe(ModBlockProperties.BAMBOO_PIPE.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("bamboo_pipe")))));
    public static final DeferredBlock<Block> BAMBOO_PIPE_EXTRACT = registerBlock("bamboo_pipe_extract", () -> new BambooPipeExtract(ModBlockProperties.BAMBOO_PIPE_EXTRACT.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("bamboo_pipe_extract")))));
    public static final DeferredBlock<Block> SILO_ROOF = registerBlock("silo_roof", () -> new SiloRoofBlock(ModBlockProperties.SILO_ROOF.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("silo_roof")))));
    public static final DeferredBlock<Block> SILO_TANK = registerBlock("silo_tank", () -> new SiloTankBlock(ModBlockProperties.SILO_TANK.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("silo_tank")))));
    public static final DeferredBlock<Block> SILO_SUPPORTS = registerBlock("silo_supports", () -> new SiloSupportsBlock(ModBlockProperties.SILO_SUPPORTS.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("silo_supports")))));
    public static final DeferredBlock<Block> HONEY_COMB = registerBlock("honey_comb", () -> new HoneyCombBlock(ModBlockProperties.HONEY_COMB.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("honey_comb")))));
    public static final DeferredBlock<Block> COMPOSTER = registerBlock("composter", () -> new erebus.block.ComposterBlock(ModBlockProperties.COMPOSTER.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("composter")))));
    public static final DeferredBlock<Block> BLENDER = registerBlock("blender", () -> new BlenderBlock(ModBlockProperties.BLENDER.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("blender")))));
    public static final DeferredBlock<Block> UMBER_FURNACE = registerBlock("umber_furnace", () -> new UmberFurnaceBlock(ModBlockProperties.UMBER_FURNACE.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("umber_furnace")))));
    public static final DeferredBlock<Block> UMBERSTONE_BUTTON = registerBlock("umberstone_button", () -> new ButtonBlock(BlockSetType.STONE, 10, ModBlockProperties.UMBERSTONE_BUTTON.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("umberstone_button")))));
    public static final DeferredBlock<Block> LIQUIFIER = registerBlockWithoutBlockItem("liquifier", () -> new LiquifierBlock(ModBlockProperties.LIQUIFIER.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("liquifier")))));
    public static final DeferredBlock<Block> GLOW_GEM_ACTIVE = registerBlock("glow_gem_active", () -> new GlowGemActiveBlock(ModBlockProperties.GLOW_GEM_ACTIVE.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("glow_gem_active")))));
    public static final DeferredBlock<Block> GLOW_GEM_INACTIVE = registerBlock("glow_gem_inactive", () -> new GlowGemInactiveBlock(ModBlockProperties.GLOW_GEM_INACTIVE.setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("glow_gem_inactive")))));
    public static final DeferredBlock<Block> MUCUS_BOMB = registerSimpleBlock("mucus_bomb", ModBlockProperties.MUCUS_BOMB);
    public static final DeferredBlock<Block> UMBER_GOLEM_STATUE = registerSimpleBlock("umber_golem_statue", ModBlockProperties.UMBER_GOLEM_STATUE);

    public static final DeferredBlock<Block> ALTAR_BASE = registerBlock("altar_base", () -> new AltarBase(BlockBehaviour.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("altar_base"))).noOcclusion()));
    public static final DeferredBlock<Block> ALTAR_LIGHTNING = registerBlock("altar_lightning", () -> new LightningAltar(BlockBehaviour.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("altar_lightning"))).noOcclusion()));
    public static final DeferredBlock<Block> ALTAR_HEALING = registerBlock("altar_healing", () -> new HealingAltar(BlockBehaviour.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("altar_healing"))).noOcclusion()));
    public static final DeferredBlock<Block> ALTAR_EXPERIENCE = registerBlock("altar_experience", () -> new ExperienceAltar(BlockBehaviour.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("altar_experience"))).noOcclusion()));
    public static final DeferredBlock<Block> ALTAR_REPAIR = registerBlock("altar_repair", () -> new RepairAltar(BlockBehaviour.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("altar_repair"))).noOcclusion()));
    public static final DeferredBlock<Block> OFFERING_ALTAR = registerBlock("offering_altar", () -> new OfferingAltar(BlockBehaviour.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).setId(ResourceKey.create(Registries.BLOCK, Erebus.prefix("offering_altar"))).noOcclusion()));

    // MARK: Antlion Dungeon
    public static final DeferredBlock<Block> CAPSTONE = registerSimpleBlock("capstone", ModBlockProperties.CAPSTONE);
    public static final DeferredBlock<Block> CAPSTONE_MUD = registerSimpleBlock("capstone_mud", ModBlockProperties.CAPSTONE_MUD);
    public static final DeferredBlock<Block> CAPSTONE_IRON = registerSimpleBlock("capstone_iron", ModBlockProperties.CAPSTONE_IRON);
    public static final DeferredBlock<Block> CAPSTONE_GOLD = registerSimpleBlock("capstone_gold", ModBlockProperties.CAPSTONE_GOLD);
    public static final DeferredBlock<Block> CAPSTONE_JADE = registerSimpleBlock("capstone_jade", ModBlockProperties.CAPSTONE_JADE);
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING = registerSimpleBlock("temple_brick_unbreaking", ModBlockProperties.TEMPLE_BRICK_UNBREAKING);
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING_JADE = registerSimpleBlock("temple_brick_unbreaking_jade", ModBlockProperties.TEMPLE_BRICK_UNBREAKING_JADE);
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING_EXO = registerSimpleBlock("temple_brick_unbreaking_exo", ModBlockProperties.TEMPLE_BRICK_UNBREAKING_EXO);
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING_CREAM = registerSimpleBlock("temple_brick_unbreaking_cream", ModBlockProperties.TEMPLE_BRICK_UNBREAKING_CREAM);
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING_EYE = registerSimpleBlock("temple_brick_unbreaking_eye", ModBlockProperties.TEMPLE_BRICK_UNBREAKING_EYE);
    public static final DeferredBlock<Block> TEMPLE_BRICK_UNBREAKING_STRING = registerSimpleBlock("temple_brick_unbreaking_string", ModBlockProperties.TEMPLE_BRICK_UNBREAKING_STRING);
    public static final DeferredBlock<Block> TEMPLE_TELEPORTER = registerSimpleBlock("temple_teleporter", ModBlockProperties.TEMPLE_TELEPORTER);
    public static final DeferredBlock<Block> FORCE_FIELD = registerSimpleBlock("force_field", ModBlockProperties.FORCE_FIELD);
    public static final DeferredBlock<Block> FORCE_LOCK = registerSimpleBlock("force_lock", ModBlockProperties.FORCE_LOCK);

    public static final DeferredBlock<Block> ANT_HILL_BLOCK = registerSimpleBlock("ant_hill_block", ModBlockProperties.ANT_HILL_BLOCK);
}
