package erebus;

import java.lang.reflect.Field;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
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
import erebus.item.ItemFoodStagHeart;
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

public class ModItems {

	// BASIC MATERIALS
	public static final Item wandOfPreservation = new ItemWandOfPreservation();
	public static final Item portalActivator = new ItemPortalActivator().setUnlocalizedName("erebus.portalActivator");
	public static final Item materials = new ItemMaterials().setUnlocalizedName("erebus.materials");
	public static final Item food = new ItemErebusFood().setUnlocalizedName("erebus.food");
	public static final Item smoothie = new ItemSmoothie().setUnlocalizedName("erebus.smoothie");
	public static final Item heartBerries = new ItemFoodHeartBerries(0, 0F, false).setUnlocalizedName("erebus.heartBerries").setTextureName("erebus:heart_berries");
	public static final Item lifeBlood = new ItemFoodHeartBerries(0, 0F, false).setUnlocalizedName("erebus.lifeBlood").setTextureName("erebus:life_blood");
	public static final Item turnip = new ItemSeedFood(4, 0.6F, ModBlocks.blockTurnip, Blocks.farmland).setCreativeTab(ModTabs.items).setUnlocalizedName("erebus.turnips").setTextureName("erebus:turnips");
	public static final Item sprayCan = new ItemSprayCan();
	public static final Item wandOfAnimation = new ItemWandOfAnimation();
	public static final Item hornOfSummoning = new ItemHornOfSummoning();
	public static final Item nectarCollector = new ItemNectarCollector();
	public static final Item beeTamingAmulet = new ItemAmuletBeeTaming();
	public static final Item homingBeecon = new ItemHomingBeecon();
	public static final Item homingBeeconAdvanced = new ItemHomingBeeconAdvanced();
	public static final Item deathCompass = new ItemDeathCompass();
	public static final Item cabbageSeeds = new ItemSeedFood(4, 0.1F, ModBlocks.blockCabbage, Blocks.farmland).setCreativeTab(ModTabs.items).setUnlocalizedName("erebus.cabbageSeeds").setTextureName("erebus:cabbage_seeds");
	public static final Item idols = new ItemDungeonIdols();
	public static final Item stagHeartRaw = new ItemFoodStagHeart(0, 0F, false, "raw");
	public static final Item stagHeartCooked = new ItemFoodStagHeart(20, 0.5F, false, "cooked");

	public static final Item flowerSeeds = new ItemFlowerSeeds();
	public static final Item whetstone = new ItemWhetstone().setUnlocalizedName("erebus.whetstone").setTextureName("erebus:whetstone");
	public static final Item encrustedDiamond = new ItemEncrustedDiamond().setUnlocalizedName("erebus.encrustedDiamond").setTextureName("erebus:encrusted_diamond");
	public static final Item antTamingAmulet = new ItemAmuletAntTaming().setUnlocalizedName("erebus.antTamingAmulet").setTextureName("erebus:ant_taming_amulet");
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
	public static final Item jadeHelmet = new ItemArmorJade(0).setUnlocalizedName("erebus.helmetJade").setTextureName("erebus:helmet_jade");
	public static final Item jadeBody = new ItemArmorJade(1).setUnlocalizedName("erebus.chestplateJade").setTextureName("erebus:chestplate_jade");
	public static final Item jadeLegs = new ItemArmorJade(2).setUnlocalizedName("erebus.leggingsJade").setTextureName("erebus:leggings_jade");
	public static final Item jadeBoots = new ItemArmorJade(3).setUnlocalizedName("erebus.bootsJade").setTextureName("erebus:boots_jade");
	public static final Item jadeSword = new ItemJadeSword();
	public static final Item jadePickaxe = new ItemPickaxeJade();
	public static final Item jadeAxe = new ItemAxeJade();
	public static final Item jadeShovel = new ItemJadeShovel();
	public static final Item jadePaxel = new ItemJadePaxel();
	public static final Item jadeHoe = new ItemJadeHoe();

	// EXOSKELETON STUFF
	public static final Item exoskeletonHelmet = new ItemArmorExoskeleton(0).setUnlocalizedName("erebus.helmetExo").setTextureName("erebus:helmet_exo");
	public static final Item exoskeletonBody = new ItemArmorExoskeleton(1).setUnlocalizedName("erebus.chestplateExo").setTextureName("erebus:chestplate_exo");
	public static final Item exoskeletonLegs = new ItemArmorExoskeleton(2).setUnlocalizedName("erebus.leggingsExo").setTextureName("erebus:leggings_exo");
	public static final Item exoskeletonBoots = new ItemArmorExoskeleton(3).setUnlocalizedName("erebus.bootsExo").setTextureName("erebus:boots_exo");

	public static final Item reinExoskeletonHelmet = new ItemArmorReinExoskeleton(0).setUnlocalizedName("erebus.exoHelmetRein").setTextureName("erebus:exo_helmet_rein");
	public static final Item reinExoskeletonBody = new ItemArmorReinExoskeleton(1).setUnlocalizedName("erebus.exoChestplateRein").setTextureName("erebus:exo_chestplate_rein");
	public static final Item reinExoskeletonLegs = new ItemArmorReinExoskeleton(2).setUnlocalizedName("erebus.exoLeggingsRein").setTextureName("erebus:exo_leggings_rein");
	public static final Item reinExoskeletonBoots = new ItemArmorReinExoskeleton(3).setUnlocalizedName("erebus.exoBootsRein").setTextureName("erebus:exo_boots_rein");

	// MISC WEAPONS
	public static final Item cavemanClub = new ItemCavemanClub().setFull3D().setUnlocalizedName("erebus.clubBone").setTextureName("erebus:club_bone");
	public static final Item waspSword = new ItemSwordWasp().setUnlocalizedName("erebus.waspSword");
	public static final Item maxSpeedBow = new ItemBowMaxSpeed().setUnlocalizedName("erebus.maxSpeedBow");
	public static final Item waspDagger = new ItemDaggerWasp().setUnlocalizedName("erebus.waspDagger");
	public static final Item scorpionPincer = new ItemScorpionPincer().setUnlocalizedName("erebus.scorpionPincer");
	public static final Item webSlinger = new ItemWebSlinger().setUnlocalizedName("erebus.webSlinger");
	public static final Item witherWebSlinger = new ItemWitherWebSlinger().setUnlocalizedName("erebus.witherWebSlinger");
	public static final Item blockExtractor = new ItemExtractor().setFull3D().setUnlocalizedName("erebus.blockExtractor").setTextureName("erebus:block_extractor");
	public static final Item woodlouseBall = new ItemWoodlouseBall().setUnlocalizedName("erebus.woodlouseBall");
	public static final Item rolledNewspaper = new ItemRolledNewspaper();
	public static final Item warHammer = new ItemHammerWar();

	// MISC ARMOR
	public static final Item reinCompoundGoggles = new ItemCompoundGoggles(ModMaterials.armorREINEXOSKELETON, 2, 0).setUnlocalizedName("erebus.reinCompoundGoggles").setTextureName("erebus:rein_compound_goggles");
	public static final Item compoundGoggles = new ItemCompoundGoggles(ModMaterials.armorEXOSKELETON, 2, 0).setUnlocalizedName("erebus.compoundGoggles").setTextureName("erebus:compound_goggles");
	public static final Item sprintLeggings = new ItemLeggingsSprint(ModMaterials.armorREINEXOSPECIAL, 2).setUnlocalizedName("erebus.sprintLeggings").setTextureName("erebus:sprint_leggings");
	public static final Item jumpBoots = new ItemBootsJump(ModMaterials.armorREINEXOSPECIAL, 3).setUnlocalizedName("erebus.jumpBoots").setTextureName("erebus:jump_boots");
	public static final Item armorGlider = new ItemArmorGlider().setUnlocalizedName("erebus.armorGlider").setTextureName("erebus:armor_glider");
	public static final ItemArmor armorGliderPowered = (ItemArmor) new ItemArmorGlider().setUnlocalizedName("erebus.armorGliderPowered").setTextureName("erebus:armor_glider_powered");
	public static final Item mushroomHelm = new ItemHelmMushroom(0).setUnlocalizedName("erebus.mushroomHelm").setTextureName("erebus:mushroom_helm");
	public static final Item spiderTShirt = new ItemTShirtSpider(1).setUnlocalizedName("erebus.spiderTShirt").setTextureName("erebus:spider_tshirt");
	public static final Item waterStriders = new ItemArmorWaterStriders(ModMaterials.armorREINEXOSPECIAL, 3).setUnlocalizedName("erebus.waterStriders").setTextureName("erebus:water_striders");
	public static final Item rhinoExoskeletonHelmet = new ItemHelmRhino(0).setUnlocalizedName("erebus.rhinoHelmet").setTextureName("erebus:rhino_helm");
	public static final Item rhinoExoskeletonBody = new ItemArmorRhino(1).setUnlocalizedName("erebus.rhinoChestplate").setTextureName("erebus:rhino_chestplate");
	public static final Item rhinoExoskeletonLegs = new ItemArmorRhino(2).setUnlocalizedName("erebus.rhinoLeggings").setTextureName("erebus:rhino_leggings");
	public static final Item rhinoExoskeletonBoots = new ItemArmorRhino(3).setUnlocalizedName("erebus.rhinoBoots").setTextureName("erebus:rhino_boots");

	// CREATIVE
	public static final Item spawnEggs = new ItemSpawnEggs().setUnlocalizedName("erebus.monsterPlacer").setTextureName("spawn_egg");

	// BUCKETS
	public static final Item bambucket = new ItemBambucket();
	public static final Item bambucketWater = new ItemBambucket(Blocks.flowing_water).setUnlocalizedName("erebus.bambucketWater").setTextureName("erebus:bambucket_water");
	public static final Item bambucketHoney = new ItemBambucket(ModBlocks.honeyBlock).setUnlocalizedName("erebus.bambucketHoney").setTextureName("erebus:bambucket_honey");
	public static final Item bambucketBeetleJuice = new ItemBambucketBeetleJuice();
	public static final Item bambucketAntiVenom = new ItemBambucketAntiVenom();
	public static final Item bambucketMilk = new ItemBambucketMilk();
	public static final Item bambucketFormicAcid = new ItemBambucket(ModBlocks.formicAcid).setUnlocalizedName("erebus.bambucketFormicAcid").setTextureName("erebus:bambucket_formic_acid");

	public static final Item bucketAntiVenom = new ItemBucketAntiVenom().setUnlocalizedName("erebus.bucketAntiVenom").setTextureName("erebus:bucket_antivenom");
	public static final Item bucketBeetleJuice = new ItemBucketOfBeetleJuice().setUnlocalizedName("erebus.bucketBeetleJuice").setTextureName("erebus:bucket_beetle_juice");
	public static final Item bucketHoney = new ItemBucket(ModBlocks.honeyBlock).setMaxStackSize(1).setContainerItem(Items.bucket).setCreativeTab(ModTabs.specials).setUnlocalizedName("erebus.bucketHoney").setTextureName("erebus:bucket_honey");
	public static final Item bucketFormicAcid = new ItemBucket(ModBlocks.formicAcid).setMaxStackSize(1).setContainerItem(Items.bucket).setCreativeTab(ModTabs.specials).setUnlocalizedName("erebus.bucketFormicAcid").setTextureName("erebus:bucket_formic_acid");

	public static final Item bottleAntiVenom = new ItemBottleAntiVenom();

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
				if (fuel.getItem() == materials && fuel.getItemDamage() == DATA.BAMBOO.ordinal())
					return 300;
				return 0;
			}
		});
	}
}