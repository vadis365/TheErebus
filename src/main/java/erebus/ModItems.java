package erebus;

import java.lang.reflect.Field;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.block.BlockSlabPlanks;
import erebus.item.ItemAmuletAntTaming;
import erebus.item.ItemAmuletBeeTaming;
import erebus.item.ItemArmorExoskeleton;
import erebus.item.ItemArmorGlider;
import erebus.item.ItemArmorJade;
import erebus.item.ItemArmorReinExoskeleton;
import erebus.item.ItemArmorRhino;
import erebus.item.ItemArmorWaterStriders;
import erebus.item.ItemAxeJade;
import erebus.item.ItemBootsJump;
import erebus.item.ItemBottleAntiVenom;
import erebus.item.ItemBowMaxSpeed;
import erebus.item.ItemBucketAntiVenom;
import erebus.item.ItemBucketOfBeetleJuice;
import erebus.item.ItemCavemanClub;
import erebus.item.ItemCompost;
import erebus.item.ItemCompoundGoggles;
import erebus.item.ItemDaggerWasp;
import erebus.item.ItemDeathCompass;
import erebus.item.ItemDoorErebus;
import erebus.item.ItemDungeonIdols;
import erebus.item.ItemEncrustedDiamond;
import erebus.item.ItemErebusFood;
import erebus.item.ItemExtractor;
import erebus.item.ItemFlowerSeeds;
import erebus.item.ItemFoodHeartBerries;
import erebus.item.ItemHammerWar;
import erebus.item.ItemHelmMushroom;
import erebus.item.ItemHelmRhino;
import erebus.item.ItemHomingBeecon;
import erebus.item.ItemHomingBeeconAdvanced;
import erebus.item.ItemHornOfSummoning;
import erebus.item.ItemJadeHoe;
import erebus.item.ItemJadePaxel;
import erebus.item.ItemJadeShovel;
import erebus.item.ItemJadeSword;
import erebus.item.ItemLeggingsSprint;
import erebus.item.ItemMaterials;
import erebus.item.ItemMaterials.DATA;
import erebus.item.ItemNectarCollector;
import erebus.item.ItemPickaxeJade;
import erebus.item.ItemPlanticide;
import erebus.item.ItemPortalActivator;
import erebus.item.ItemRolledNewspaper;
import erebus.item.ItemScorpionPincer;
import erebus.item.ItemSmoothie;
import erebus.item.ItemSoulCrystal;
import erebus.item.ItemSpawnEggs;
import erebus.item.ItemSprayCan;
import erebus.item.ItemSwordWasp;
import erebus.item.ItemTShirtSpider;
import erebus.item.ItemWandOfAnimation;
import erebus.item.ItemWandOfPreservation;
import erebus.item.ItemWebSlinger;
import erebus.item.ItemWhetstone;
import erebus.item.ItemWitherWebSlinger;
import erebus.item.ItemWoodlouseBall;
import erebus.item.bambucket.ItemBambucket;
import erebus.item.bambucket.ItemBambucketAntiVenom;
import erebus.item.bambucket.ItemBambucketBeetleJuice;
import erebus.item.bambucket.ItemBambucketMilk;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;

public class ModItems {

	// BASIC MATERIALS
	public static final Item wandOfPreservation = new ItemWandOfPreservation();
	public static final Item portalActivator = new ItemPortalActivator().setUnlocalizedName("erebus.portalActivator");
	public static final Item materials = new ItemMaterials().setUnlocalizedName("erebus.materials");
	public static final Item food = new ItemErebusFood().setUnlocalizedName("erebus.food");
	public static final Item smoothie = new ItemSmoothie().setUnlocalizedName("erebus.smoothie");
	public static final Item heartBerries = new ItemFoodHeartBerries(0, 0F, false).setUnlocalizedName("erebus.heartBerries").setTextureName("erebus:heartBerries");
	public static final Item lifeBlood = new ItemFoodHeartBerries(0, 0F, false).setUnlocalizedName("erebus.lifeBlood").setTextureName("erebus:lifeBlood");
	public static final Item turnip = new ItemSeedFood(4, 0.6F, ModBlocks.blockTurnip, Blocks.farmland).setCreativeTab(ModTabs.items).setUnlocalizedName("erebus.turnips").setTextureName("erebus:turnips");
	public static final Item sprayCan = new ItemSprayCan().setUnlocalizedName("erebus.sprayCan").setTextureName("erebus:sprayCan");
	public static final Item wandOfAnimation = new ItemWandOfAnimation().setUnlocalizedName("erebus.wandOfAnimation");
	public static final Item hornOfSummoning = new ItemHornOfSummoning().setUnlocalizedName("erebus.hornOfSummoning").setTextureName("erebus:hornOfSummoning");
	public static final Item nectarCollector = new ItemNectarCollector().setUnlocalizedName("erebus.nectarCollector").setTextureName("erebus:nectarCollector");
	public static final Item beeTamingAmulet = new ItemAmuletBeeTaming().setUnlocalizedName("erebus.beeTamingAmulet").setTextureName("erebus:beeTamingAmulet");
	public static final Item homingBeecon = new ItemHomingBeecon();
	public static final Item homingBeeconAdvanced = new ItemHomingBeeconAdvanced();
	public static final Item deathCompass = new ItemDeathCompass();
	public static final Item cabbageSeeds = new ItemSeedFood(4, 0.1F, ModBlocks.blockCabbage, Blocks.farmland).setCreativeTab(ModTabs.items).setUnlocalizedName("erebus.cabbageSeeds").setTextureName("erebus:cabbageSeeds");
	public static final Item idols = new ItemDungeonIdols();
	public static final Item soulCrystal = new ItemSoulCrystal();

	public static final Item flowerSeeds = new ItemFlowerSeeds().setUnlocalizedName("erebus.flowerSeeds");
	public static final Item whetstone = new ItemWhetstone().setUnlocalizedName("erebus.whetstone").setTextureName("erebus:whetstone");
	public static final Item encrustedDiamond = new ItemEncrustedDiamond().setUnlocalizedName("erebus.encrustedDiamond").setTextureName("erebus:encrustedDiamond");
	public static final Item antTamingAmulet = new ItemAmuletAntTaming().setUnlocalizedName("erebus.antTamingAmulet").setTextureName("erebus:antTamingAmulet");
	public static final Item compost = new ItemCompost();
	public static final Item planticide = new ItemPlanticide();

	// DOORS
	public static final Item doorAmber = new ItemDoorErebus(ModBlocks.doorAmber);
	public static final Item doorBaobab = new ItemDoorErebus(ModBlocks.doorBaobab);
	public static final Item doorEucalyptus = new ItemDoorErebus(ModBlocks.doorEucalyptus);
	public static final Item doorMahogany = new ItemDoorErebus(ModBlocks.doorMahogany);
	public static final Item doorMossbark = new ItemDoorErebus(ModBlocks.doorMossbark);
	public static final Item doorAsper = new ItemDoorErebus(ModBlocks.doorAsper);
	public static final Item doorCypress = new ItemDoorErebus(ModBlocks.doorCypress);
	public static final Item doorRotten = new ItemDoorErebus(ModBlocks.doorRotten);
	public static final Item doorPetrified = new ItemDoorErebus(ModBlocks.doorPetrified);
	public static final Item doorScorched = new ItemDoorErebus(ModBlocks.doorScorched);
	public static final Item doorMarshwood = new ItemDoorErebus(ModBlocks.doorMarshwood);

	// JADE STUFF
	public static final Item jadeHelmet = new ItemArmorJade(0).setUnlocalizedName("erebus.helmetJade").setTextureName("erebus:helmetJade");
	public static final Item jadeBody = new ItemArmorJade(1).setUnlocalizedName("erebus.chestplateJade").setTextureName("erebus:chestplateJade");
	public static final Item jadeLegs = new ItemArmorJade(2).setUnlocalizedName("erebus.leggingsJade").setTextureName("erebus:leggingsJade");
	public static final Item jadeBoots = new ItemArmorJade(3).setUnlocalizedName("erebus.bootsJade").setTextureName("erebus:bootsJade");
	public static final Item jadeSword = new ItemJadeSword();
	public static final Item jadePickaxe = new ItemPickaxeJade();
	public static final Item jadeAxe = new ItemAxeJade();
	public static final Item jadeShovel = new ItemJadeShovel();
	public static final Item jadePaxel = new ItemJadePaxel();
	public static final Item jadeHoe = new ItemJadeHoe();

	// EXOSKELETON STUFF
	public static final Item exoskeletonHelmet = new ItemArmorExoskeleton(0).setUnlocalizedName("erebus.helmetExo").setTextureName("erebus:helmetExo");
	public static final Item exoskeletonBody = new ItemArmorExoskeleton(1).setUnlocalizedName("erebus.chestplateExo").setTextureName("erebus:chestplateExo");
	public static final Item exoskeletonLegs = new ItemArmorExoskeleton(2).setUnlocalizedName("erebus.leggingsExo").setTextureName("erebus:leggingsExo");
	public static final Item exoskeletonBoots = new ItemArmorExoskeleton(3).setUnlocalizedName("erebus.bootsExo").setTextureName("erebus:bootsExo");

	public static final Item reinExoskeletonHelmet = new ItemArmorReinExoskeleton(0).setUnlocalizedName("erebus.exoHelmetRein").setTextureName("erebus:exoHelmetRein");
	public static final Item reinExoskeletonBody = new ItemArmorReinExoskeleton(1).setUnlocalizedName("erebus.exoChestplateRein").setTextureName("erebus:exoChestplateRein");
	public static final Item reinExoskeletonLegs = new ItemArmorReinExoskeleton(2).setUnlocalizedName("erebus.exoLeggingsRein").setTextureName("erebus:exoLeggingsRein");
	public static final Item reinExoskeletonBoots = new ItemArmorReinExoskeleton(3).setUnlocalizedName("erebus.exoBootsRein").setTextureName("erebus:exoBootsRein");

	// MISC WEAPONS
	public static final Item cavemanClub = new ItemCavemanClub().setFull3D().setUnlocalizedName("erebus.clubBone").setTextureName("erebus:clubBone");
	public static final Item waspSword = new ItemSwordWasp().setUnlocalizedName("erebus.waspSword").setTextureName("paper");
	public static final Item maxSpeedBow = new ItemBowMaxSpeed().setUnlocalizedName("erebus.maxSpeedBow").setTextureName("erebus:maxSpeedBow");
	public static final Item waspDagger = new ItemDaggerWasp().setUnlocalizedName("erebus.waspDagger");
	public static final Item scorpionPincer = new ItemScorpionPincer().setUnlocalizedName("erebus.scorpionPincer").setTextureName("paper");
	public static final Item webSlinger = new ItemWebSlinger().setUnlocalizedName("erebus.webSlinger");
	public static final Item witherWebSlinger = new ItemWitherWebSlinger().setUnlocalizedName("erebus.witherWebSlinger");
	public static final Item blockExtractor = new ItemExtractor().setFull3D().setUnlocalizedName("erebus.blockExtractor").setTextureName("erebus:blockExtractor");
	public static final Item woodlouseBall = new ItemWoodlouseBall().setUnlocalizedName("erebus.woodlouseBall");
	public static final Item rolledNewspaper = new ItemRolledNewspaper();
	public static final Item warHammer = new ItemHammerWar().setUnlocalizedName("erebus.warHammer");

	// MISC ARMOR
	public static final Item reinCompoundGoggles = new ItemCompoundGoggles(ModMaterials.armorREINEXOSKELETON, 2, 0).setUnlocalizedName("erebus.reinCompoundGoggles").setTextureName("erebus:reinCompoundGoggles");
	public static final Item compoundGoggles = new ItemCompoundGoggles(ModMaterials.armorEXOSKELETON, 2, 0).setUnlocalizedName("erebus.compoundGoggles").setTextureName("erebus:compoundGoggles");
	public static final Item sprintLeggings = new ItemLeggingsSprint(ModMaterials.armorREINEXOSPECIAL, 2).setUnlocalizedName("erebus.sprintLeggings").setTextureName("erebus:sprintLeggings");
	public static final Item jumpBoots = new ItemBootsJump(ModMaterials.armorREINEXOSPECIAL, 3).setUnlocalizedName("erebus.jumpBoots").setTextureName("erebus:jumpBoots");
	public static final Item armorGlider = new ItemArmorGlider().setUnlocalizedName("erebus.armorGlider").setTextureName("erebus:armorGlider");
	public static final ItemArmor armorGliderPowered = (ItemArmor) new ItemArmorGlider().setUnlocalizedName("erebus.armorGliderPowered").setTextureName("erebus:armorGliderPowered");
	public static final Item mushroomHelm = new ItemHelmMushroom(0).setUnlocalizedName("erebus.mushroomHelm").setTextureName("erebus:mushroomHelm");
	public static final Item spiderTShirt = new ItemTShirtSpider(1).setUnlocalizedName("erebus.spiderTShirt").setTextureName("erebus:spiderTShirtItem");
	public static final Item waterStriders = new ItemArmorWaterStriders(ModMaterials.armorREINEXOSPECIAL, 3).setUnlocalizedName("erebus.waterStriders").setTextureName("erebus:waterStriders");
	public static final Item rhinoExoskeletonHelmet = new ItemHelmRhino(0).setUnlocalizedName("erebus.rhinoHelmet").setTextureName("erebus:rhinoHelm");
	public static final Item rhinoExoskeletonBody = new ItemArmorRhino(1).setUnlocalizedName("erebus.rhinoChestplate").setTextureName("erebus:rhinoChestplate");
	public static final Item rhinoExoskeletonLegs = new ItemArmorRhino(2).setUnlocalizedName("erebus.rhinoLeggings").setTextureName("erebus:rhinoLeggings");
	public static final Item rhinoExoskeletonBoots = new ItemArmorRhino(3).setUnlocalizedName("erebus.rhinoBoots").setTextureName("erebus:rhinoBoots");

	// CREATIVE
	public static final Item spawnEggs = new ItemSpawnEggs().setUnlocalizedName("erebus.monsterPlacer").setTextureName("spawn_egg");

	// BUCKETS
	public static final Item bambucket = new ItemBambucket();
	public static final Item bambucketWater = new ItemBambucket(Blocks.flowing_water).setUnlocalizedName("erebus.bambucketWater").setTextureName("erebus:bambucketWater");
	public static final Item bambucketHoney = new ItemBambucket(ModBlocks.honeyBlock).setUnlocalizedName("erebus.bambucketHoney").setTextureName("erebus:bambucketHoney");
	public static final Item bambucketBeetleJuice = new ItemBambucketBeetleJuice();
	public static final Item bambucketAntiVenom = new ItemBambucketAntiVenom();
	public static final Item bambucketMilk = new ItemBambucketMilk();

	public static final Item bucketAntiVenom = new ItemBucketAntiVenom().setUnlocalizedName("erebus.bucketAntiVenom").setTextureName("erebus:bucketAntiVenom");
	public static final Item bucketBeetleJuice = new ItemBucketOfBeetleJuice().setUnlocalizedName("erebus.bucketBeetleJuice").setTextureName("erebus:bucketBeetleJuice");
	public static final Item bucketHoney = new ItemBucket(ModBlocks.honeyBlock).setMaxStackSize(1).setContainerItem(Items.bucket).setCreativeTab(ModTabs.specials).setUnlocalizedName("erebus.bucketHoney").setTextureName("erebus:bucketHoney");

	public static final Item bottleAntiVenom = new ItemBottleAntiVenom().setUnlocalizedName("erebus.bottleAntiVenom");

	public static void init() {
		registerItems();
		registerProperties();
	}

	private static void registerItems() {
		try {
			for (Field f : ModItems.class.getDeclaredFields()) {
				Object obj = f.get(null);
				if (obj instanceof Item)
					registerItem((Item) obj);
				else if (obj instanceof Item[])
					for (Item item : (Item[]) obj)
						registerItem(item);
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private static void registerItem(Item item) {
		String name = item.getUnlocalizedName();
		String[] strings = name.split("\\.");
		GameRegistry.registerItem(item, strings[strings.length - 1]);
	}

	private static void registerProperties() {
		GameRegistry.registerFuelHandler(new IFuelHandler() {

			@Override
			public int getBurnTime(ItemStack fuel) {
				if (fuel.getItem() == materials && fuel.getItemDamage() == DATA.bamboo.ordinal())
					return 300;
				else if (Block.getBlockFromItem(fuel.getItem()) instanceof BlockSlabPlanks)
					return 150;
				else
					return 0;
			}
		});
	}
}