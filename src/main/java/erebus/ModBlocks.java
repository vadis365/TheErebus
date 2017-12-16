package erebus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import erebus.block.altars.AltarBase;
import erebus.block.altars.HealingAltar;
import erebus.block.altars.LightningAltar;
import erebus.block.altars.OfferingAltar;
import erebus.block.altars.RepairAltar;
import erebus.block.altars.XPAltar;
import erebus.block.bamboo.BlockBambooBridge;
import erebus.block.bamboo.BlockBambooCrate;
import erebus.block.bamboo.BlockBambooLadder;
import erebus.block.bamboo.BlockBambooPole;
import erebus.block.bamboo.BlockBambooTorch;
import erebus.block.bamboo.BlockExtenderThingy;
import erebus.block.silo.BlockSiloRoof;
import erebus.block.silo.BlockSiloSupports;
import erebus.block.silo.BlockSiloTank;
import erebus.blocks.BlockAlgae;
import erebus.blocks.BlockAmber;
import erebus.blocks.BlockAnthillBlock;
import erebus.blocks.BlockAntlionSpawner;
import erebus.blocks.BlockBerryBush;
import erebus.blocks.BlockBones;
import erebus.blocks.BlockBossEgg;
import erebus.blocks.BlockCabbage;
import erebus.blocks.BlockCapstone;
import erebus.blocks.BlockDarkFruitVine;
import erebus.blocks.BlockDoorErebus;
import erebus.blocks.BlockDoubleHeightPlant;
import erebus.blocks.BlockDragonflySpawner;
import erebus.blocks.BlockErebusHoney;
import erebus.blocks.BlockErebusMushroomHuge;
import erebus.blocks.BlockForceField;
import erebus.blocks.BlockForceLock;
import erebus.blocks.BlockFormicAcid;
import erebus.blocks.BlockGaeanKeystone;
import erebus.blocks.BlockGhostSand;
import erebus.blocks.BlockGiantFlower;
import erebus.blocks.BlockGiantLilyPad;
import erebus.blocks.BlockGlowshroom;
import erebus.blocks.BlockGlowshroomStalkMain;
import erebus.blocks.BlockGneiss;
import erebus.blocks.BlockGneissVent;
import erebus.blocks.BlockHollowLog;
import erebus.blocks.BlockHoneyComb;
import erebus.blocks.BlockInsectRepellent;
import erebus.blocks.BlockLeavesErebus;
import erebus.blocks.BlockLightningSpeed;
import erebus.blocks.BlockMagmaCrawlerSpawner;
import erebus.blocks.BlockMud;
import erebus.blocks.BlockMushroomSmall;
import erebus.blocks.BlockOreErebus;
import erebus.blocks.BlockOreErebus2;
import erebus.blocks.BlockPetrifiedChest;
import erebus.blocks.BlockPetrifiedCraftingTable;
import erebus.blocks.BlockPlanksErebus;
import erebus.blocks.BlockPlantedGiantFlower;
import erebus.blocks.BlockPricklyPear;
import erebus.blocks.BlockQuickSand;
import erebus.blocks.BlockRedGem;
import erebus.blocks.BlockSaplingErebus;
import erebus.blocks.BlockSimple;
import erebus.blocks.BlockSlabErebus;
import erebus.blocks.BlockSmallPlant;
import erebus.blocks.BlockSpiderSpawner;
import erebus.blocks.BlockStairsErebus;
import erebus.blocks.BlockStigma;
import erebus.blocks.BlockSwampVent;
import erebus.blocks.BlockTempleBrickUnbreaking;
import erebus.blocks.BlockTempleTeleporter;
import erebus.blocks.BlockThorns;
import erebus.blocks.BlockTurnip;
import erebus.blocks.BlockUmberGolemStatue;
import erebus.blocks.BlockUmberGravel;
import erebus.blocks.BlockUmberPaver;
import erebus.blocks.BlockUmberPaver.EnumUmberPaverType;
import erebus.blocks.BlockUmberstone;
import erebus.blocks.BlockUmberstone.EnumType;
import erebus.blocks.BlockUmberstonePillar;
import erebus.blocks.BlockVelocity;
import erebus.blocks.BlockWallErebus;
import erebus.blocks.BlockWallPlants;
import erebus.blocks.BlockWallPlantsCultivated;
import erebus.blocks.BlockWaspNest;
import erebus.blocks.BlockWaspSpawner;
import erebus.blocks.BlockWitherWeb;
import erebus.blocks.BlockWoodFenceGate;
import erebus.blocks.BlockZombieAntSpawner;
import erebus.blocks.EnumWood;
import erebus.blocks.ErebusPortal;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import erebus.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {

	private static final List<Block> BLOCKS = new LinkedList<Block>();
	public static final List<ItemBlock> ITEM_BLOCKS = new ArrayList<ItemBlock>();

	public static final Block UMBERSTONE = new BlockUmberstone();
	public static final Block PLANKS = new BlockPlanksErebus();
	public static final Block PORTAL = new ErebusPortal();
	public static final Block GAEAN_KEYSTONE = new BlockGaeanKeystone();

	public static final Block UMBERGRAVEL = new BlockUmberGravel();
	public static final Block UMBERPAVER = new BlockUmberPaver();
	public static final Block UMBERSTONE_PILLAR = new BlockUmberstonePillar();

	public static final Block ORE_IRON = new BlockOreErebus(1);
	public static final Block ORE_GOLD = new BlockOreErebus(2);
	public static final Block ORE_COAL = new BlockOreErebus2(0, Items.COAL, 0, 1, 1, 0, 2);
	public static final Block ORE_DIAMOND = new BlockOreErebus2(2, Items.DIAMOND, 0, 1, 1, 3, 7);
	public static final Block ORE_EMERALD = new BlockOreErebus2(2, Items.EMERALD, 0, 1, 1, 3, 7);
	public static final Block ORE_LAPIS = new BlockOreErebus2(1, Items.DYE, 4, 4, 9, 2, 5);
	public static final Block ORE_QUARTZ = new BlockOreErebus2(0, Items.QUARTZ, 0, 1, 1, 2, 5);
	public static final Block ORE_COPPER = new BlockOreErebus(1);
	public static final Block ORE_SILVER = new BlockOreErebus(2);
	public static final Block ORE_TIN = new BlockOreErebus(1);
	public static final Block ORE_LEAD = new BlockOreErebus(1);
	public static final Block ORE_ALUMINIUM = new BlockOreErebus(1);
	public static final Block ORE_JADE = new BlockOreErebus2(2, ModItems.MATERIALS, EnumErebusMaterialsType.JADE.ordinal(), 1, 1, 3, 7);
	public static final Block ORE_ENCRUSTED_DIAMOND = new BlockOreErebus2(2, Items.DIAMOND, 0, 1, 1, 3, 7);
	public static final Block ORE_FOSSIL = new BlockOreErebus2(0, ModItems.MATERIALS, EnumErebusMaterialsType.SHARD_BONE.ordinal(), 1, 1, 0, 2);
	public static final Block ORE_GNEISS = new BlockOreErebus2(0, ModItems.MATERIALS, EnumErebusMaterialsType.GNEISS_ROCK.ordinal(), 1, 1, 0, 2);
	public static final Block ORE_PETRIFIED_WOOD = new BlockOreErebus2(0, ModItems.MATERIALS, EnumErebusMaterialsType.PETRIFIED_WOOD.ordinal(), 1, 1, 0, 2);
	public static final Block ORE_TEMPLE = new BlockOreErebus(0); // what does this drop?
	public static final Block JADE_BLOCK = new BlockSimple(Material.ROCK, SoundType.STONE).setHardness(5.0F).setResistance(10.0F).setCreativeTab(ModTabs.BLOCKS);
	public static final Block AMBER = new BlockAmber();
	public static final Block MUD = new BlockMud();
	public static final Block QUICK_SAND = new BlockQuickSand();
	public static final Block RED_GEM = new BlockRedGem();
	public static final Block SWAMP_VENT = new BlockSwampVent();
	public static final Block GHOST_SAND = new BlockGhostSand();

	public static final Block CROP_TURNIP = new BlockTurnip();
	public static final Block CROP_CABBAGE = new BlockCabbage().setCreativeTab(ModTabs.PLANTS);
	public static final Block JADE_BERRY_BUSH = new BlockBerryBush("JADE");
	public static final Block HEART_BERRY_BUSH = new BlockBerryBush("HEART");
	public static final Block SWAMP_BERRY_BUSH = new BlockBerryBush("SWAMP");
	public static final Block DARK_FRUIT_VINE = new BlockDarkFruitVine();
	public static final Block PRICKLY_PEAR = new BlockPricklyPear();
	public static final Block GIANT_FLOWER = new BlockGiantFlower().setCreativeTab(ModTabs.PLANTS);
	public static final Block GIANT_FLOWER_STIGMA = new BlockStigma().setCreativeTab(ModTabs.PLANTS);
	public static final Block PLANTED_FLOWER = new BlockPlantedGiantFlower();
	public static final Block SMALL_PLANT = new BlockSmallPlant();
	public static final Block THORNS = new BlockThorns();
	public static final BlockDoubleHeightPlant DOUBLE_PLANT = new BlockDoubleHeightPlant();
	public static final Block WALL_PLANTS = new BlockWallPlants();
	public static final Block WALL_PLANTS_CULTIVATED = new BlockWallPlantsCultivated();
	public static final Block ALGAE = new BlockAlgae();

	//MUSHROOMS
	public static final Block DARK_CAPPED_MUSHROOM = new BlockMushroomSmall(false);
	public static final Block SARCASTIC_CZECH_MUSHROOM = new BlockMushroomSmall(true);
	public static final Block GRANDMAS_SHOES_MUSHROOM = new BlockMushroomSmall(true);
	public static final Block DUTCH_CAP_MUSHROOM = new BlockMushroomSmall(true);
	public static final Block KAIZERS_FINGERS_MUSHROOM = new BlockMushroomSmall(false);

	public static final Block DARK_CAPPED_MUSHROOM_BLOCK = new BlockErebusMushroomHuge(MapColor.OBSIDIAN, DARK_CAPPED_MUSHROOM);
	public static final Block SARCASTIC_CZECH_MUSHROOM_BLOCK = new BlockErebusMushroomHuge(MapColor.RED, SARCASTIC_CZECH_MUSHROOM);
	public static final Block GRANDMAS_SHOES_MUSHROOM_BLOCK = new BlockErebusMushroomHuge(MapColor.GREEN, GRANDMAS_SHOES_MUSHROOM );
	public static final Block DUTCH_CAP_MUSHROOM_BLOCK = new BlockErebusMushroomHuge(MapColor.YELLOW, DUTCH_CAP_MUSHROOM);
	public static final Block KAIZERS_FINGERS_MUSHROOM_BLOCK = new BlockErebusMushroomHuge(MapColor.BROWN, KAIZERS_FINGERS_MUSHROOM);

	//GLOWSHROOMS
	public static final Block GLOWSHROOM = new BlockGlowshroom();
	public static final Block GLOWSHROOM_STALK_MAIN = new BlockGlowshroomStalkMain();
	
	//OTHER
	public static final Block WITHER_WEB = new BlockWitherWeb();
	public static final Block SILK = new BlockSimple(Material.CLOTH, SoundType.CLOTH).setHardness(0.2F).setCreativeTab(ModTabs.BLOCKS);
	public static final Block MIR_BRICK = new BlockSimple(Material.ROCK, "pickaxe", 1, SoundType.STONE).setHardness(1.5F).setCreativeTab(ModTabs.BLOCKS);
	public static final Block PLANKS_PETRIFIED_WOOD = new BlockSimple(Material.ROCK, SoundType.WOOD).setHardness(2.0F).setCreativeTab(ModTabs.BLOCKS);
	public static final Block REIN_EXO = new BlockSimple(Material.ROCK, SoundType.STONE).setHardness(1.5F).setResistance(2000.0F).setCreativeTab(ModTabs.BLOCKS);
	public static final Block MUD_BRICK = new BlockSimple(Material.ROCK, SoundType.STONE).setHardness(0.8F).setResistance(1.0F).setCreativeTab(ModTabs.BLOCKS);
	public static final Block TEMPLE_BRICK = new BlockSimple(Material.ROCK, SoundType.STONE).setHardness(2.0F).setCreativeTab(ModTabs.BLOCKS);
	public static final Block TEMPLE_PILLAR = new BlockSimple(Material.ROCK, SoundType.STONE).setHardness(2.0F).setCreativeTab(ModTabs.BLOCKS);
	public static final Block TEMPLE_TILE = new BlockSimple(Material.ROCK, SoundType.STONE).setHardness(2.0F).setCreativeTab(ModTabs.BLOCKS);
	public static final Block VOLCANIC_ROCK = new BlockSimple(Material.ROCK, SoundType.STONE).setResistance(20.0F).setCreativeTab(ModTabs.BLOCKS);

	public static final Block GNEISS = new BlockGneiss();
	public static final Block GNEISS_VENT = new BlockGneissVent();
	public static final Block HOLLOW_LOG = new BlockHollowLog();
	
	//UTILS BLOCKS
	public static final Block PETRIFIED_CRAFTING_TABLE = new BlockPetrifiedCraftingTable();
	public static final Block BAMBOO_CRATE = new BlockBambooCrate();
	public static final Block BAMBOO_BRIDGE = new BlockBambooBridge();
	public static final Block BAMBOO_LADDER = new BlockBambooLadder();
	public static final Block BAMBOO_NERD_POLE = new BlockBambooPole();
	public static final Block BAMBOO_EXTENDER = new BlockExtenderThingy();
	public static final Block BAMBOO_TORCH = new BlockBambooTorch();
	public static final Block SILO_ROOF = new BlockSiloRoof(Material.WOOD);
	public static final Block SILO_TANK = new BlockSiloTank(Material.IRON);
	public static final Block SILO_SUPPORTS = new BlockSiloSupports(Material.WOOD);
	public static final Block HONEY_COMB = new BlockHoneyComb();
	public static final Block UMBER_GOLEM_STATUE = new BlockUmberGolemStatue();
	public static final Block INSECT_REPELLENT = new BlockInsectRepellent();
	public static final Block PETRIFIED_WOOD_CHEST = new BlockPetrifiedChest(BlockPetrifiedChest.PETRIFIED_CHEST);

	// VELOCITY BLOCKS
	public static final Block VELOCITY_BLOCK = new BlockVelocity();
	public static final Block LIGHTNING_SPEED_BLOCK = new BlockLightningSpeed();

	// ALTARS
	public static final Block ALTAR_BASE = new AltarBase();
	public static final Block ALTAR_LIGHTNING = new LightningAltar();
	public static final Block ALTAR_HEALING = new HealingAltar();
	public static final Block ALTAR_XP = new XPAltar();
	public static final Block ALTAR_REPAIR = new RepairAltar();
	public static final Block ALTAR_OFFERING = new OfferingAltar();

	// DUNGEONS
	public static final Block SPIDER_SPAWNER = new BlockSpiderSpawner("erebus:scytodes");
	public static final Block JUMPING_SPIDER_SPAWNER  = new BlockSpiderSpawner("erebus:jumping_spider");
	public static final Block TARANTULA_SPAWNER = new BlockSpiderSpawner("erebus:tarantula");
	public static final Block WASP_SPAWNER = new BlockWaspSpawner("erebus:wasp");
	public static final Block ANTLION_SPAWNER = new BlockAntlionSpawner("erebus:antlion");
	public static final Block DRAGON_FLY_SPAWNER = new BlockDragonflySpawner("erebus:dragon_fly");
	public static final Block GIANT_LILY_PAD = new BlockGiantLilyPad(Material.WOOD, "axe", 1);
	public static final Block ZOMBIE_ANT_SPAWNER = new BlockZombieAntSpawner("erebus:zombie_ant");
	public static final Block ZOMBIE_ANT_SOLDIER_SPAWNER = new BlockZombieAntSpawner("erebus:zombie_ant_soldier");
	public static final Block WASP_NEST = new BlockWaspNest();
	public static final Block STAIRS_WASP_NEST = BlockStairsErebus.createWaspStairs(WASP_NEST.getDefaultState());
	//public static final Block magmaCrawler_SPAWNER = new BlockMagmaCrawlerSpawner("erebus:magmaCrawler");
	public static final Block ANTLION_EGG = new BlockBossEgg();
	public static final Block TARANTULA_EGG = new BlockBossEgg();
	public static final Block CAPSTONE = new BlockCapstone();
	public static final Block ANT_HILL_BLOCK = new BlockAnthillBlock();
	public static final Block FORCE_FIELD = new BlockForceField();
	public static final Block FORCE_LOCK = new BlockForceLock();
	public static final Block TEMPLE_BRICK_UNBREAKING = new BlockTempleBrickUnbreaking();
	public static final Block TEMPLE_TELEPORTER = new BlockTempleTeleporter();
	public static final Block BLOCK_OF_BONES = new BlockBones();
	public static final Block MAGMA_CRAWLER_SPAWNER = new BlockMagmaCrawlerSpawner("erebus:magma_crawler");

	// WALLS
	public static final Block WALL_UMBERSTONE = new BlockWallErebus(UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERSTONE));
	public static final Block WALL_UMBERCOBBLE = new BlockWallErebus(UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERCOBBLE));
	public static final Block WALL_UMBERCOBBLE_MOSSY = new BlockWallErebus(UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERCOBBLE_MOSSY));
	public static final Block WALL_UMBERCOBBLE_WEBBED = new BlockWallErebus(UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERCOBBLE_WEBBED));
	public static final Block WALL_UMBERSTONE_BRICKS = new BlockWallErebus(UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERSTONE_BRICKS));
	public static final Block WALL_UMBERTILE_SMOOTH = new BlockWallErebus(UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERTILE_SMOOTH));
	public static final Block WALL_UMBERTILE_SMOOTH_SMALL = new BlockWallErebus(UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERTILE_SMOOTH_SMALL));
	public static final Block WALL_AMBER = new BlockWallErebus(AMBER.getDefaultState());
	public static final Block WALL_UMBERPAVER = new BlockWallErebus(UMBERPAVER.getDefaultState().withProperty(BlockUmberPaver.TYPE, EnumUmberPaverType.UMBERPAVER));
	public static final Block WALL_UMBERPAVER_MOSSY = new BlockWallErebus(UMBERPAVER.getDefaultState().withProperty(BlockUmberPaver.TYPE, EnumUmberPaverType.UMBERPAVER_MOSSY));
	public static final Block WALL_UMBERPAVER_WEBBED = new BlockWallErebus(UMBERPAVER.getDefaultState().withProperty(BlockUmberPaver.TYPE, EnumUmberPaverType.UMBERPAVER_WEBBED));

	//SLABS
	public static final Block SLAB_UMBERSTONE = new BlockSlabErebus(UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERSTONE), "pickaxe", 0);
	public static final Block SLAB_UMBERCOBBLE = new BlockSlabErebus(UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERCOBBLE), "pickaxe", 0);
	public static final Block SLAB_UMBERCOBBLE_MOSSY= new BlockSlabErebus(UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERCOBBLE_MOSSY), "pickaxe", 0);
	public static final Block SLAB_UMBERCOBBLE_WEBBED = new BlockSlabErebus(UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERCOBBLE_WEBBED), "pickaxe", 0);
	public static final Block SLAB_UMBERSTONE_BRICKS = new BlockSlabErebus(UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERSTONE_BRICKS), "pickaxe", 0);
	public static final Block SLAB_UMBERTILE_SMOOTH = new BlockSlabErebus(UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERTILE_SMOOTH), "pickaxe", 0);
	public static final Block SLAB_UMBERTILE_SMOOTH_SMALL = new BlockSlabErebus(UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERTILE_SMOOTH_SMALL), "pickaxe", 0);
	public static final Block SLAB_UMBERPAVER = new BlockSlabErebus(UMBERPAVER.getDefaultState().withProperty(BlockUmberPaver.TYPE, EnumUmberPaverType.UMBERPAVER), "pickaxe", 0);
	public static final Block SLAB_UMBERPAVER_MOSSY = new BlockSlabErebus(UMBERPAVER.getDefaultState().withProperty(BlockUmberPaver.TYPE, EnumUmberPaverType.UMBERPAVER_MOSSY), "pickaxe", 0);
	public static final Block SLAB_UMBERPAVER_WEBBED = new BlockSlabErebus(UMBERPAVER.getDefaultState().withProperty(BlockUmberPaver.TYPE, EnumUmberPaverType.UMBERPAVER_WEBBED), "pickaxe", 0);

	//FLUIDS
	public static final Block FORMIC_ACID = new BlockFormicAcid();
	public static final Block HONEY = new BlockErebusHoney();

	public static void init() {
		try {
			for (Field field : ModBlocks.class.getDeclaredFields()) {
				Object obj = field.get(null);
				if (obj instanceof Block) {
					Block block = (Block) obj;
					String name = field.getName().toLowerCase(Locale.ENGLISH);
					registerBlock(name, block);
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		EnumWood.init();
	}

	public static void registerBlock(String name, Block block) {
		BLOCKS.add(block);
		block.setRegistryName(Reference.MOD_ID, name).setUnlocalizedName(Reference.MOD_ID + "." + name);

		ItemBlock item;
		if (block instanceof IHasCustomItem)
			item = ((IHasCustomItem) block).getItemBlock();
		else
			item = new ItemBlock(block);
		ITEM_BLOCKS.add(item);
		item.setRegistryName(Reference.MOD_ID, name).setUnlocalizedName(Reference.MOD_ID + "." + name);
	}
	
	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	public static class RegistrationHandlerBlocks {

		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();
			for (Block block : BLOCKS) {
				registry.register(block);
			}
		}

		@SubscribeEvent
		public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();
				for (ItemBlock item : ITEM_BLOCKS) {
				registry.register(item);
			}
		}
		
		@SideOnly(Side.CLIENT)
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			for (Block block : BLOCKS)
				if (block instanceof ISubBlocksBlock) {
					List<String> models = ((ISubBlocksBlock) block).getModels();
					for (int i = 0; i < models.size(); i++)
						ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), i, new ModelResourceLocation(Reference.MOD_ID + ":blocks/" + models.get(i), "inventory"));
				} else {
					ResourceLocation name = block.getRegistryName();
					ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Reference.MOD_ID + ":blocks/" + name.getResourcePath(), "inventory"));
					if (block instanceof BlockLeavesErebus)
						ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(new IProperty[] { BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE }).build());
					if (block instanceof BlockSaplingErebus)
						ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(new IProperty[] { BlockSapling.TYPE }).build());
					if (block instanceof BlockDoorErebus)
						ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(new IProperty[] { BlockDoor.POWERED }).build());
					if (block instanceof BlockWoodFenceGate)
						ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(new IProperty[] { BlockWoodFenceGate.POWERED }).build());
					if (block instanceof BlockFluidClassic)
						ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(new IProperty[] { BlockFluidClassic.LEVEL }).build());
				}
		}
	}

	public static interface IHasCustomItem {
		ItemBlock getItemBlock();
	}

	public static interface ISubBlocksBlock {
		List<String> getModels();
	}
}