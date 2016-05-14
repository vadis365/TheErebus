package erebus;

import java.lang.reflect.Field;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import erebus.item.ItemMaterials;
import erebus.item.ItemMaterials.ITEM_DATA;

public class ModItems {

	// BASIC MATERIALS
	public static final Item materials = new ItemMaterials();
	/*
	public static final Item portalActivator = new ItemPortalActivator().setUnlocalizedName("erebus.portalActivator");
	
	public static final Item food = new ItemErebusFood().setUnlocalizedName("erebus.food");
	public static final Item smoothie = new ItemFoodSmoothie().setUnlocalizedName("erebus.smoothie");
	public static final Item heartBerries = new ItemFoodHeartBerries(0, 0F, false).setUnlocalizedName("erebus.heartBerries");
	public static final Item lifeBlood = new ItemFoodHeartBerries(0, 0F, false).setUnlocalizedName("erebus.lifeBlood");
//	public static final Item turnip = new ItemSeedFood(4, 0.6F, ModBlocks.blockTurnip, Blocks.farmland).setCreativeTab(ModTabs.items).setUnlocalizedName("erebus.turnips");
	public static final Item sprayCan = new ItemSprayCan().setUnlocalizedName("erebus.sprayCan");
	public static final Item wandOfAnimation = new ItemWandOfAnimation().setUnlocalizedName("erebus.wandOfAnimation");
	public static final Item hornOfSummoning = new ItemHornOfSummoning().setUnlocalizedName("erebus.hornOfSummoning");
	public static final Item nectarCollector = new ItemNectarCollector().setUnlocalizedName("erebus.nectarCollector");
	public static final Item beeTamingAmulet = new ItemAmuletBeeTaming().setUnlocalizedName("erebus.beeTamingAmulet");
	public static final Item homingBeecon = new ItemHomingBeecon().setUnlocalizedName("erebus.homingBeecon");
	public static final Item homingBeeconAdvanced = new ItemHomingBeeconAdvanced().setUnlocalizedName("erebus.homingBeeconAdvanced")	public static final Item cabbageSeeds = new ItemSeedFood(4, 0.1F, ModBlocks.blockCabbage, Blocks.farmland).setCreativeTab(ModTabs.items).setUnlocalizedName("erebus.cabbageSeeds");
	public static final Item idols = new ItemDungeonIdols();
	public static final Item soulCrystal = new ItemSoulCrystal();

	public static final Item flowerSeeds = new ItemFlowerSeeds().setUnlocalizedName("erebus.flowerSeeds");
	public static final Item whetstone = new ItemWhetstone().setUnlocalizedName("erebus.whetstone");
	public static final Item encrustedDiamond = new ItemEncrustedDiamond().setUnlocalizedName("erebus.encrustedDiamond");
	public static final Item antTamingAmulet = new ItemAmuletAntTaming().setUnlocalizedName("erebus.antTamingAmulet");
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
	public static final Item jadeHelmet = new ItemArmorJade(0).setUnlocalizedName("erebus.helmetJade");
	public static final Item jadeBody = new ItemArmorJade(1).setUnlocalizedName("erebus.chestplateJade");
	public static final Item jadeLegs = new ItemArmorJade(2).setUnlocalizedName("erebus.leggingsJade");
	public static final Item jadeBoots = new ItemArmorJade(3).setUnlocalizedName("erebus.bootsJade");
	public static final Item jadeSword = new ItemSword(ModMaterials.toolJADE).setCreativeTab(ModTabs.gears).setUnlocalizedName("erebus.swordJade");
	public static final Item jadePickaxe = new ItemPickaxeJade().setCreativeTab(ModTabs.gears).setUnlocalizedName("erebus.pickaxeJade");
	public static final Item jadeAxe = new ItemAxeJade().setUnlocalizedName("erebus.axeJade");
	public static final Item jadeShovel = new ItemSpade(ModMaterials.toolJADE).setCreativeTab(ModTabs.gears).setUnlocalizedName("erebus.shovelJade");
	public static final Item jadePaxel = new ItemPaxel(ModMaterials.toolJADEPAXEL).setUnlocalizedName("erebus.paxelJade");
	public static final Item jadeHoe = new ItemHoe(ModMaterials.toolJADE).setCreativeTab(ModTabs.gears).setUnlocalizedName("erebus.hoeJade");

	// EXOSKELETON STUFF
	public static final Item exoskeletonHelmet = new ItemArmorExoskeleton(0).setUnlocalizedName("erebus.helmetExo");
	public static final Item exoskeletonBody = new ItemArmorExoskeleton(1).setUnlocalizedName("erebus.chestplateExo");
	public static final Item exoskeletonLegs = new ItemArmorExoskeleton(2).setUnlocalizedName("erebus.leggingsExo");
	public static final Item exoskeletonBoots = new ItemArmorExoskeleton(3).setUnlocalizedName("erebus.bootsExo");

	public static final Item reinExoskeletonHelmet = new ItemArmorReinExoskeleton(0).setUnlocalizedName("erebus.exoHelmetRein");
	public static final Item reinExoskeletonBody = new ItemArmorReinExoskeleton(1).setUnlocalizedName("erebus.exoChestplateRein");
	public static final Item reinExoskeletonLegs = new ItemArmorReinExoskeleton(2).setUnlocalizedName("erebus.exoLeggingsRein");
	public static final Item reinExoskeletonBoots = new ItemArmorReinExoskeleton(3).setUnlocalizedName("erebus.exoBootsRein");

	// MISC WEAPONS
	public static final Item cavemanClub = new ItemCavemanClub().setFull3D().setUnlocalizedName("erebus.clubBone");
	public static final Item waspSword = new ItemSwordWasp().setUnlocalizedName("erebus.waspSword");
	public static final Item maxSpeedBow = new ItemBowMaxSpeed().setUnlocalizedName("erebus.maxSpeedBow");
	public static final Item waspDagger = new ItemDaggerWasp().setUnlocalizedName("erebus.waspDagger");
	public static final Item scorpionPincer = new ItemScorpionPincer().setUnlocalizedName("erebus.scorpionPincer");
	public static final Item webSlinger = new ItemWebSlinger().setUnlocalizedName("erebus.webSlinger");
	public static final Item witherWebSlinger = new ItemWitherWebSlinger().setUnlocalizedName("erebus.witherWebSlinger");
	public static final Item blockExtractor = new ItemExtractor().setFull3D().setUnlocalizedName("erebus.blockExtractor");
	public static final Item woodlouseBall = new ItemWoodlouseBall().setUnlocalizedName("erebus.woodlouseBall");
	public static final Item rolledNewspaper = new ItemRolledNewspaper();
	public static final Item warHammer = new ItemHammerWar().setUnlocalizedName("erebus.warHammer");

	// MISC ARMOR
	public static final Item reinCompoundGoggles = new ItemCompoundGoggles(ModMaterials.armorREINEXOSKELETON, 2, 0).setUnlocalizedName("erebus.reinCompoundGoggles");
	public static final Item compoundGoggles = new ItemCompoundGoggles(ModMaterials.armorEXOSKELETON, 2, 0).setUnlocalizedName("erebus.compoundGoggles");
	public static final Item sprintLeggings = new ItemLeggingsSprint(ModMaterials.armorREINEXOSPECIAL, 2).setUnlocalizedName("erebus.sprintLeggings");
	public static final Item jumpBoots = new ItemBootsJump(ModMaterials.armorREINEXOSPECIAL, 3).setUnlocalizedName("erebus.jumpBoots");
	public static final Item armorGlider = new ItemArmorGlider().setUnlocalizedName("erebus.armorGlider");
	public static final Item armorGliderPowered = new ItemArmorGlider().setUnlocalizedName("erebus.armorGliderPowered");
	public static final Item mushroomHelm = new ItemHelmMushroom(0).setUnlocalizedName("erebus.mushroomHelm");
	public static final Item spiderTShirt = new ItemTShirtSpider(1).setUnlocalizedName("erebus.spiderTShirt");
	public static final Item waterStriders = new ItemArmorWaterStriders(ModMaterials.armorREINEXOSPECIAL, 3).setUnlocalizedName("erebus.waterStriders");
	public static final Item rhinoExoskeletonHelmet = new ItemHelmRhino(0).setUnlocalizedName("erebus.rhinoHelmet");
	public static final Item rhinoExoskeletonBody = new ItemArmorRhino(1).setUnlocalizedName("erebus.rhinoChestplate");
	public static final Item rhinoExoskeletonLegs = new ItemArmorRhino(2).setUnlocalizedName("erebus.rhinoLeggings");
	public static final Item rhinoExoskeletonBoots = new ItemArmorRhino(3).setUnlocalizedName("erebus.rhinoBoots");

	// CREATIVE
	public static final Item spawnEggs = new ItemSpawnEggs().setUnlocalizedName("erebus.monsterPlacer");

	// BUCKETS
	public static final Item bambucket = new ItemBambucket();
	public static final Item bambucketWater = new ItemBambucket(Blocks.flowing_water).setUnlocalizedName("erebus.bambucketWater");
	public static final Item bambucketHoney = new ItemBambucket(ModBlocks.honeyBlock).setUnlocalizedName("erebus.bambucketHoney");
	public static final Item bambucketBeetleJuice = new ItemBambucketBeetleJuice();
	public static final Item bambucketAntiVenom = new ItemBambucketAntiVenom();

	public static final Item bucketAntiVenom = new ItemBucketAntiVenom().setUnlocalizedName("erebus.bucketAntiVenom");
	public static final Item bucketBeetleJuice = new ItemBucketOfBeetleJuice().setUnlocalizedName("erebus.bucketBeetleJuice");
	public static final Item bucketHoney = new ItemBucket(ModBlocks.honeyBlock).setMaxStackSize(1).setContainerItem(Items.bucket).setCreativeTab(ModTabs.specials).setUnlocalizedName("erebus.bucketHoney");

	public static final Item bottleAntiVenom = new ItemBottleAntiVenom().setUnlocalizedName("erebus.bottleAntiVenom");
	public static final Item smoothieBook = new ItemBookSmoothies().setMaxStackSize(1).setCreativeTab(ModTabs.specials).setUnlocalizedName("erebus.smoothieBook");
*/
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
		//String name = item.getUnlocalizedName();
		//String[] strings = name.split("\\.");
		GameRegistry.register(item);
		//GameRegistry.registerItem(item, strings[strings.length - 1]);
	}

	private static void registerProperties() {
		GameRegistry.registerFuelHandler(new IFuelHandler() {

			@Override
			public int getBurnTime(ItemStack fuel) {
				return fuel.getItem() == materials && fuel.getItemDamage() == ITEM_DATA.bamboo.ordinal() ? 300 : 0;
			}
		});
	}
}