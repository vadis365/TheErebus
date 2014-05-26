package erebus;

import java.lang.reflect.Field;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.core.handler.ConfigHandler;
import erebus.item.EncrustedDiamond;
import erebus.item.ItemArmorGlider;
import erebus.item.ItemBambucket;
import erebus.item.ItemBeeTamingAmulet;
import erebus.item.ItemBlockExtractor;
import erebus.item.ItemBucketOfBeetleJuice;
import erebus.item.ItemBucketOfHoney;
import erebus.item.ItemCavemanClub;
import erebus.item.ItemCompoundGoggles;
import erebus.item.ItemCrownOfLuminosity;
import erebus.item.ItemErebusFood;
import erebus.item.ItemErebusMaterial;
import erebus.item.ItemErebusMaterial.DATA;
import erebus.item.ItemErebusSpecial;
import erebus.item.ItemExoskeletonArmor;
import erebus.item.ItemFlowerSeeds;
import erebus.item.ItemHomingBeecon;
import erebus.item.ItemHornOfSummoning;
import erebus.item.ItemJadeArmor;
import erebus.item.ItemJumpBoots;
import erebus.item.ItemMaxSpeedBow;
import erebus.item.ItemMetalIngots;
import erebus.item.ItemNectarCollector;
import erebus.item.ItemPaxel;
import erebus.item.ItemPortalActivator;
import erebus.item.ItemReinExoskeletonArmor;
import erebus.item.ItemRolledNewspaper;
import erebus.item.ItemScorpionPincer;
import erebus.item.ItemSpawnEggs;
import erebus.item.ItemSprayCan;
import erebus.item.ItemSprintLeggings;
import erebus.item.ItemWandOfAnimation;
import erebus.item.ItemWaspDagger;
import erebus.item.ItemWaspSword;
import erebus.item.ItemWebSlinger;
import erebus.item.ItemWhetstone;
import erebus.item.ItemWoodlouseBall;
import erebus.item.JadeAxe;
import erebus.item.JadePickaxe;
import erebus.item.block.ItemBlockDoorAmber;
import erebus.lib.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {

	// BASIC MATERIALS
	public static Item portalActivator, erebusMaterials, erebusFood, metalIngot, bamBucket, turnip, sprayCan, wandOfAnimation;
	public static Item bucketOfBeetleJuice, hornOfSummoning, erebusSpecialItem, blockExtractor, nectarCollector, beeTamingAmulet, bucketHoney;
	public static Item homingBeecon, flowerSeeds, whetstone, encrustedDiamond;

	// JADE STUFF
	public static Item jadeHelmet, jadeBody, jadeLegs, jadeBoots, jadeSword, jadePickaxe, jadeAxe, jadeShovel, jadePaxel, jadeHoe;

	// EXOSKELETON STUFF
	public static Item exoskeletonHelmet, exoskeletonBody, exoskeletonLegs, exoskeletonBoots;
	public static Item reinExoskeletonHelmet, reinExoskeletonBody, reinExoskeletonLegs, reinExoskeletonBoots;

	// MISC WEAPONS
	public static Item fossilClub, waspSword, maxSpeedBow, waspDagger, scorpionPincer, webSlinger, doorAmberItem, woodlouseBall, rolledNewspaper;

	// MISC ARMOR
	public static Item reinCompoundGoggles, compoundGoggles, sprintLeggings, jumpBoots, armorGlider, armorGliderPowered, lightCrown;

	// CREATIVE
	public static Item spawnEggs;

	public static void init() {
		initItems();
		initCreativeTabs();
		registerItems();
		registerProperties();
	}

	private static void initItems() {
		portalActivator = new ItemPortalActivator().setUnlocalizedName("portalActivator").setTextureName("erebus:portalActivator");
		erebusMaterials = new ItemErebusMaterial().setUnlocalizedName("erebusMaterials");
		erebusSpecialItem = new ItemErebusSpecial().setUnlocalizedName("erebusSpecialItem");
		erebusFood = new ItemErebusFood().setUnlocalizedName("erebusFood");
		metalIngot = new ItemMetalIngots();
		bamBucket = new ItemBambucket().setUnlocalizedName("bamBucket");
		turnip = new ItemSeedFood(4, 0.6F, ModBlocks.blockTurnip, Blocks.farmland).setUnlocalizedName("turnips").setTextureName("erebus:turnips");
		sprayCan = new ItemSprayCan().setUnlocalizedName("sprayCan").setTextureName("erebus:sprayCan");
		wandOfAnimation = new ItemWandOfAnimation().setUnlocalizedName("wandOfAnimation");
		bucketOfBeetleJuice = new ItemBucketOfBeetleJuice().setUnlocalizedName("bucketOfBeetleJuice").setTextureName("erebus:bucketOfBeetleJuice");
		hornOfSummoning = new ItemHornOfSummoning().setUnlocalizedName("hornOfSummoning").setTextureName("erebus:hornOfSummoning");
		nectarCollector = new ItemNectarCollector().setUnlocalizedName("nectarCollector").setTextureName("erebus:nectarCollector");
		beeTamingAmulet = new ItemBeeTamingAmulet().setUnlocalizedName("beeTamingAmulet").setTextureName("erebus:beeTamingAmulet");
		doorAmberItem = new ItemBlockDoorAmber(ModBlocks.doorAmber).setUnlocalizedName("doorAmberItem").setTextureName("erebus:doorAmber");
		bucketHoney = new ItemBucketOfHoney(ModBlocks.erebusHoneyBlock).setUnlocalizedName("bucketHoney").setTextureName("erebus:bucketHoney");
		homingBeecon = new ItemHomingBeecon().setUnlocalizedName("homingBeecon").setTextureName("paper");
		flowerSeeds = new ItemFlowerSeeds().setUnlocalizedName("flowerSeeds");
		whetstone = new ItemWhetstone().setUnlocalizedName("whetstone").setTextureName("erebus:whetstone");
		encrustedDiamond = new EncrustedDiamond().setUnlocalizedName("encrustedDiamond").setTextureName("erebus:encrustedDiamond");

		jadeHelmet = new ItemJadeArmor(0).setUnlocalizedName("helmetJade").setTextureName("erebus:helmetJade");
		jadeBody = new ItemJadeArmor(1).setUnlocalizedName("chestplateJade").setTextureName("erebus:chestplateJade");
		jadeLegs = new ItemJadeArmor(2).setUnlocalizedName("leggingsJade").setTextureName("erebus:leggingsJade");
		jadeBoots = new ItemJadeArmor(3).setUnlocalizedName("bootsJade").setTextureName("erebus:bootsJade");
		jadeSword = new ItemSword(ModMaterials.toolJADE).setUnlocalizedName("swordJade").setTextureName("erebus:swordJade");
		jadePickaxe = new JadePickaxe().setUnlocalizedName("pickaxeJade").setTextureName("erebus:pickaxeJade");
		jadeAxe = new JadeAxe().setUnlocalizedName("axeJade").setTextureName("erebus:axeJade");
		jadeShovel = new ItemSpade(ModMaterials.toolJADE).setUnlocalizedName("shovelJade").setTextureName("erebus:shovelJade");
		jadePaxel = new ItemPaxel(ModMaterials.toolJADEPAXEL).setUnlocalizedName("paxelJade").setTextureName("erebus:paxelJade");
		jadeHoe = new ItemHoe(ModMaterials.toolJADE).setUnlocalizedName("hoeJade").setTextureName("erebus:hoeJade");

		exoskeletonHelmet = new ItemExoskeletonArmor(0).setUnlocalizedName("helmetExo").setTextureName("erebus:helmetExo");
		exoskeletonBody = new ItemExoskeletonArmor(1).setUnlocalizedName("chestplateExo").setTextureName("erebus:chestplateExo");
		exoskeletonLegs = new ItemExoskeletonArmor(2).setUnlocalizedName("leggingsExo").setTextureName("erebus:leggingsExo");
		exoskeletonBoots = new ItemExoskeletonArmor(3).setUnlocalizedName("bootsExo").setTextureName("erebus:bootsExo");

		reinExoskeletonHelmet = new ItemReinExoskeletonArmor(0).setUnlocalizedName("exoHelmetRein").setTextureName("erebus:exoHelmetRein");
		reinExoskeletonBody = new ItemReinExoskeletonArmor(1).setUnlocalizedName("exoChestplateRein").setTextureName("erebus:exoChestplateRein");
		reinExoskeletonLegs = new ItemReinExoskeletonArmor(2).setUnlocalizedName("exoLeggingsRein").setTextureName("erebus:exoLeggingsRein");
		reinExoskeletonBoots = new ItemReinExoskeletonArmor(3).setUnlocalizedName("exoBootsRein").setTextureName("erebus:exoBootsRein");

		fossilClub = new ItemCavemanClub().setFull3D().setUnlocalizedName("clubBone").setTextureName("erebus:clubBone");
		waspSword = new ItemWaspSword().setUnlocalizedName("waspSword").setTextureName("paper");
		maxSpeedBow = new ItemMaxSpeedBow().setUnlocalizedName("maxSpeedBow").setTextureName("erebus:maxSpeedBow");
		waspDagger = new ItemWaspDagger().setUnlocalizedName("waspDagger");
		scorpionPincer = new ItemScorpionPincer().setUnlocalizedName("scorpionPincer").setTextureName("paper");
		webSlinger = new ItemWebSlinger().setUnlocalizedName("webSlinger").setTextureName("paper");
		blockExtractor = new ItemBlockExtractor().setFull3D().setUnlocalizedName("blockExtractor").setTextureName("erebus:blockExtractor");
		woodlouseBall = new ItemWoodlouseBall().setUnlocalizedName("woodlouseBall");
		rolledNewspaper = new ItemRolledNewspaper().setUnlocalizedName("rolledNewspaper").setTextureName("erebus:rolledNewspaper");

		reinCompoundGoggles = new ItemCompoundGoggles(ModMaterials.armorREINEXOSKELETON, 2, 0).setUnlocalizedName("reinCompoundGoggles").setTextureName("erebus:reinCompoundGoggles");
		compoundGoggles = new ItemCompoundGoggles(ModMaterials.armorEXOSKELETON, 2, 0).setUnlocalizedName("compoundGoggles").setTextureName("erebus:compoundGoggles");
		sprintLeggings = new ItemSprintLeggings(ModMaterials.armorREINEXOSPECIAL, 2).setUnlocalizedName("sprintLeggings").setTextureName("erebus:sprintLeggings");
		jumpBoots = new ItemJumpBoots(ModMaterials.armorREINEXOSPECIAL, 3).setUnlocalizedName("jumpBoots").setTextureName("erebus:jumpBoots");
		armorGlider = new ItemArmorGlider(1).setUnlocalizedName("armorGlider").setTextureName("erebus:armorGlider");
		armorGliderPowered = new ItemArmorGlider(1).setUnlocalizedName("armorGliderPowered").setTextureName("erebus:armorGliderPowered");
		lightCrown = new ItemCrownOfLuminosity(0).setUnlocalizedName("lightCrown").setTextureName("erebus:lightCrown");

		spawnEggs = new ItemSpawnEggs().setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
	}

	private static void initCreativeTabs() {
		ModTabs.items.setTab(erebusMaterials, whetstone, erebusFood, turnip, encrustedDiamond);
		if (ConfigHandler.lead || ConfigHandler.silver || ConfigHandler.copper || ConfigHandler.tin || ConfigHandler.aluminium)
			ModTabs.items.setTab(metalIngot);

		ModTabs.gears.setTab(jadeHelmet, jadeBody, jadeLegs, jadeBoots, jadeSword, jadePickaxe, jadeAxe, jadeShovel, jadePaxel, jadeHoe);
		ModTabs.gears.setTab(exoskeletonHelmet, exoskeletonBody, exoskeletonLegs, exoskeletonBoots, reinExoskeletonHelmet, reinExoskeletonBody, reinExoskeletonLegs, reinExoskeletonBoots);
		ModTabs.gears.setTab(fossilClub, waspSword, waspDagger, maxSpeedBow, wandOfAnimation, scorpionPincer, webSlinger, woodlouseBall, rolledNewspaper);
		ModTabs.gears.setTab(lightCrown, compoundGoggles, reinCompoundGoggles, armorGlider, armorGliderPowered, sprintLeggings, jumpBoots, blockExtractor, nectarCollector);
		ModTabs.specials.setTab(portalActivator, bamBucket, bucketOfBeetleJuice, bucketHoney, erebusSpecialItem, beeTamingAmulet, homingBeecon, sprayCan, hornOfSummoning, flowerSeeds, spawnEggs);

		// Special Case
		ModTabs.blocks.setTab(doorAmberItem);
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

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.WATER, new ItemStack(bamBucket, 1, 1), new ItemStack(bamBucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("honey", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(bamBucket, 1, 3), new ItemStack(bamBucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("honey", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(bucketHoney), new ItemStack(Items.bucket));
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
				return fuel.getItem() == erebusMaterials && fuel.getItemDamage() == DATA.bamboo.ordinal() ? 300 : 0;
			}
		});
	}
}