package erebus;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.MinecraftForge;
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
import erebus.item.ItemErebusFlowerSeeds;
import erebus.item.ItemErebusFood;
import erebus.item.ItemErebusMaterial;
import erebus.item.ItemErebusMaterial.DATA;
import erebus.item.ItemErebusSpecial;
import erebus.item.ItemExoskeletonArmor;
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
import erebus.item.block.ItemBlockDoorAmber;

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
		portalActivator = new ItemPortalActivator(ConfigHandler.portalActivatorID).setUnlocalizedName("portalActivator").setTextureName("erebus:portalActivator");
		erebusMaterials = new ItemErebusMaterial(ConfigHandler.erebusMaterialsID).setUnlocalizedName("erebusMaterials");
		erebusSpecialItem = new ItemErebusSpecial(ConfigHandler.erebusSpecialItemID).setUnlocalizedName("erebusSpecialItem");
		erebusFood = new ItemErebusFood(ConfigHandler.erebusFoodID).setUnlocalizedName("erebusFood");
		if (ConfigHandler.lead || ConfigHandler.silver || ConfigHandler.copper || ConfigHandler.tin || ConfigHandler.aluminium)
			metalIngot = new ItemMetalIngots(ConfigHandler.metalIngotID);
		bamBucket = new ItemBambucket(ConfigHandler.bamBucketID).setUnlocalizedName("bamBucket");
		turnip = new ItemSeedFood(ConfigHandler.turnipID, 4, 0.6F, ModBlocks.blockTurnip.blockID, Block.tilledField.blockID).setUnlocalizedName("turnips").setTextureName("erebus:turnips");
		sprayCan = new ItemSprayCan(ConfigHandler.sprayCanID).setUnlocalizedName("sprayCan").setTextureName("erebus:sprayCan");
		wandOfAnimation = new ItemWandOfAnimation(ConfigHandler.wandOfAnimationID).setUnlocalizedName("wandOfAnimation");
		bucketOfBeetleJuice = new ItemBucketOfBeetleJuice(ConfigHandler.bucketOfBeetleJuiceID).setUnlocalizedName("bucketOfBeetleJuice").setTextureName("erebus:bucketOfBeetleJuice");
		hornOfSummoning = new ItemHornOfSummoning(ConfigHandler.hornOfSummoningID).setUnlocalizedName("hornOfSummoning").setTextureName("erebus:hornOfSummoning");
		nectarCollector = new ItemNectarCollector(ConfigHandler.nectarCollectorID).setUnlocalizedName("nectarCollector").setTextureName("erebus:nectarCollector");
		beeTamingAmulet = new ItemBeeTamingAmulet(ConfigHandler.beeTamingAmuletID).setUnlocalizedName("beeTamingAmulet").setTextureName("erebus:beeTamingAmulet");
		doorAmberItem = new ItemBlockDoorAmber(ConfigHandler.doorAmberItemID, ModBlocks.doorAmber).setUnlocalizedName("doorAmberItem").setTextureName("erebus:doorAmber");
		bucketHoney = new ItemBucketOfHoney(ConfigHandler.bucketHoneyID, ModBlocks.erebusHoneyBlock.blockID).setUnlocalizedName("bucketHoney").setTextureName("erebus:bucketHoney");
		homingBeecon = new ItemHomingBeecon(ConfigHandler.homingBeeconID).setUnlocalizedName("homingBeecon").setTextureName("paper");
		flowerSeeds = new ItemErebusFlowerSeeds(ConfigHandler.flowerSeedsID).setUnlocalizedName("erebusFlowerSeeds");
		whetstone = new ItemWhetstone(ConfigHandler.whetstoneID).setUnlocalizedName("whetstone").setTextureName("erebus:whetstone");
		encrustedDiamond = new EncrustedDiamond(ConfigHandler.encrustedDiamondID).setUnlocalizedName("encrustedDiamond").setTextureName("erebus:encrustedDiamond");

		jadeHelmet = new ItemJadeArmor(ConfigHandler.jadeHelmetID, 0).setUnlocalizedName("helmetJade").setTextureName("erebus:helmetJade");
		jadeBody = new ItemJadeArmor(ConfigHandler.jadeBodyID, 1).setUnlocalizedName("chestplateJade").setTextureName("erebus:chestplateJade");
		jadeLegs = new ItemJadeArmor(ConfigHandler.jadeLegsID, 2).setUnlocalizedName("leggingsJade").setTextureName("erebus:leggingsJade");
		jadeBoots = new ItemJadeArmor(ConfigHandler.jadeBootsID, 3).setUnlocalizedName("bootsJade").setTextureName("erebus:bootsJade");
		jadeSword = new ItemSword(ConfigHandler.jadeSwordID, ModMaterials.toolJADE).setUnlocalizedName("swordJade").setTextureName("erebus:swordJade");
		jadePickaxe = new ItemPickaxe(ConfigHandler.jadePickaxeID, ModMaterials.toolJADE).setUnlocalizedName("pickaxeJade").setTextureName("erebus:pickaxeJade");
		jadeAxe = new ItemAxe(ConfigHandler.jadeAxeID, ModMaterials.toolJADE).setUnlocalizedName("axeJade").setTextureName("erebus:axeJade");
		jadeShovel = new ItemSpade(ConfigHandler.jadeShovelID, ModMaterials.toolJADE).setUnlocalizedName("shovelJade").setTextureName("erebus:shovelJade");
		jadePaxel = new ItemPaxel(ConfigHandler.jadePaxelID, ModMaterials.toolJADEPAXEL).setUnlocalizedName("paxelJade").setTextureName("erebus:paxelJade");
		jadeHoe = new ItemHoe(ConfigHandler.jadeHoeID, ModMaterials.toolJADE).setUnlocalizedName("hoeJade").setTextureName("erebus:hoeJade");

		exoskeletonHelmet = new ItemExoskeletonArmor(ConfigHandler.exoskeletonHelmetID, 0).setUnlocalizedName("helmetExo").setTextureName("erebus:helmetExo");
		exoskeletonBody = new ItemExoskeletonArmor(ConfigHandler.exoskeletonBodyID, 1).setUnlocalizedName("chestplateExo").setTextureName("erebus:chestplateExo");
		exoskeletonLegs = new ItemExoskeletonArmor(ConfigHandler.exoskeletonLegsID, 2).setUnlocalizedName("leggingsExo").setTextureName("erebus:leggingsExo");
		exoskeletonBoots = new ItemExoskeletonArmor(ConfigHandler.exoskeletonBootsID, 3).setUnlocalizedName("bootsExo").setTextureName("erebus:bootsExo");

		reinExoskeletonHelmet = new ItemReinExoskeletonArmor(ConfigHandler.reinExoskeletonHelmetID, 0).setUnlocalizedName("exoHelmetRein").setTextureName("erebus:exoHelmetRein");
		reinExoskeletonBody = new ItemReinExoskeletonArmor(ConfigHandler.reinExoskeletonBodyID, 1).setUnlocalizedName("exoChestplateRein").setTextureName("erebus:exoChestplateRein");
		reinExoskeletonLegs = new ItemReinExoskeletonArmor(ConfigHandler.reinExoskeletonLegsID, 2).setUnlocalizedName("exoLeggingsRein").setTextureName("erebus:exoLeggingsRein");
		reinExoskeletonBoots = new ItemReinExoskeletonArmor(ConfigHandler.reinExoskeletonBootsID, 3).setUnlocalizedName("exoBootsRein").setTextureName("erebus:exoBootsRein");

		fossilClub = new ItemCavemanClub(ConfigHandler.fossilClubID).setFull3D().setUnlocalizedName("clubBone").setTextureName("erebus:clubBone");
		waspSword = new ItemWaspSword(ConfigHandler.waspSwordID).setUnlocalizedName("waspSword").setTextureName("paper");
		maxSpeedBow = new ItemMaxSpeedBow(ConfigHandler.maxSpeedBowID).setUnlocalizedName("maxSpeedBow").setTextureName("erebus:maxSpeedBow");
		waspDagger = new ItemWaspDagger(ConfigHandler.waspDaggerID).setUnlocalizedName("waspDagger");
		scorpionPincer = new ItemScorpionPincer(ConfigHandler.scorpionPincerID).setUnlocalizedName("scorpionPincer").setTextureName("paper");
		webSlinger = new ItemWebSlinger(ConfigHandler.webSlingerID).setUnlocalizedName("webSlinger").setTextureName("paper");
		blockExtractor = new ItemBlockExtractor(ConfigHandler.blockExtractorID).setFull3D().setUnlocalizedName("blockExtractor").setTextureName("erebus:blockExtractor");
		woodlouseBall = new ItemWoodlouseBall(ConfigHandler.woodlouseBallID).setUnlocalizedName("woodlouseBall");
		rolledNewspaper = new ItemRolledNewspaper(ConfigHandler.rolledNewspaperID).setUnlocalizedName("rolledNewspaper").setTextureName("erebus:rolledNewspaper");

		reinCompoundGoggles = new ItemCompoundGoggles(ConfigHandler.reinCompoundGogglesID, ModMaterials.armorREINEXOSKELETON, 2, 0).setUnlocalizedName("reinCompoundGoggles").setTextureName("erebus:reinCompoundGoggles");
		compoundGoggles = new ItemCompoundGoggles(ConfigHandler.compoundGogglesID, ModMaterials.armorEXOSKELETON, 2, 0).setUnlocalizedName("compoundGoggles").setTextureName("erebus:compoundGoggles");
		sprintLeggings = new ItemSprintLeggings(ConfigHandler.sprintLeggingsID, ModMaterials.armorREINEXOSPECIAL, 2).setUnlocalizedName("sprintLeggings").setTextureName("erebus:sprintLeggings");
		jumpBoots = new ItemJumpBoots(ConfigHandler.jumpBootsID, ModMaterials.armorREINEXOSPECIAL, 3).setUnlocalizedName("jumpBoots").setTextureName("erebus:jumpBoots");
		armorGlider = new ItemArmorGlider(ConfigHandler.armorGliderID, 1).setUnlocalizedName("armorGlider").setTextureName("erebus:armorGlider");
		armorGliderPowered = new ItemArmorGlider(ConfigHandler.armorGliderPoweredID, 1).setUnlocalizedName("armorGliderPowered").setTextureName("erebus:armorGliderPowered");
		lightCrown = new ItemCrownOfLuminosity(ConfigHandler.lightCrownID, 0).setUnlocalizedName("lightCrown").setTextureName("erebus:lightCrown");

		spawnEggs = new ItemSpawnEggs(ConfigHandler.spawnEggsID).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
	}

	private static void initCreativeTabs() {
		Erebus.tabErebusItem.add(erebusMaterials, whetstone, erebusFood, turnip, encrustedDiamond);
		if (ConfigHandler.lead || ConfigHandler.silver || ConfigHandler.copper || ConfigHandler.tin || ConfigHandler.aluminium)
			Erebus.tabErebusItem.add(metalIngot);

		Erebus.tabErebusGear.add(jadeHelmet, jadeBody, jadeLegs, jadeBoots, jadeSword, jadePickaxe, jadeAxe, jadeShovel, jadePaxel, jadeHoe);
		Erebus.tabErebusGear.add(exoskeletonHelmet, exoskeletonBody, exoskeletonLegs, exoskeletonBoots, reinExoskeletonHelmet, reinExoskeletonBody, reinExoskeletonLegs, reinExoskeletonBoots);
		Erebus.tabErebusGear.add(fossilClub, waspSword, waspDagger, maxSpeedBow, wandOfAnimation, scorpionPincer, webSlinger, woodlouseBall, rolledNewspaper);
		Erebus.tabErebusGear.add(lightCrown, compoundGoggles, reinCompoundGoggles, armorGlider, armorGliderPowered, sprintLeggings, jumpBoots, blockExtractor, nectarCollector);
		Erebus.tabErebusSpecial.add(portalActivator, bamBucket, bucketOfBeetleJuice, bucketHoney, erebusSpecialItem, beeTamingAmulet, homingBeecon, sprayCan, hornOfSummoning, flowerSeeds, spawnEggs);

		// Special Case
		Erebus.tabErebusBlock.add(doorAmberItem);
	}

	private static void registerItems() {
		GameRegistry.registerItem(portalActivator, "erebus.portalActivator");
		GameRegistry.registerItem(erebusMaterials, "erebus.erebusMaterials");
		GameRegistry.registerItem(erebusFood, "erebus.erebusFood");
		GameRegistry.registerItem(erebusSpecialItem, "erebus.erebusSpecialItem");
		if (ConfigHandler.lead || ConfigHandler.silver || ConfigHandler.copper || ConfigHandler.tin || ConfigHandler.aluminium)
			GameRegistry.registerItem(metalIngot, "erebus.metalIngot");
		GameRegistry.registerItem(bamBucket, "erebus.bamBucket");
		GameRegistry.registerItem(turnip, "erebus.turnips");
		GameRegistry.registerItem(sprayCan, "erebus.sprayCan");
		GameRegistry.registerItem(wandOfAnimation, "erebus.wandOfAnimation");
		GameRegistry.registerItem(bucketOfBeetleJuice, "erebus.bucketOfBeetleJuice");
		GameRegistry.registerItem(hornOfSummoning, "erebus.hornOfSummoning");
		GameRegistry.registerItem(nectarCollector, "erebus.nectarCollector");
		GameRegistry.registerItem(beeTamingAmulet, "erebus.beeTamingAmulet");
		GameRegistry.registerItem(bucketHoney, "erebus.bucketHoney");
		GameRegistry.registerItem(homingBeecon, "erebus.homingBeecon");
		GameRegistry.registerItem(flowerSeeds, "erebus.erebusFlowerSeeds");
		GameRegistry.registerItem(whetstone, "erebus.whetstone");
		GameRegistry.registerItem(encrustedDiamond, "erebus.encrustedDiamond");

		GameRegistry.registerItem(jadeHelmet, "erebus.helmetJade");
		GameRegistry.registerItem(jadeBody, "erebus.chestplateJade");
		GameRegistry.registerItem(jadeLegs, "erebus.leggingsJade");
		GameRegistry.registerItem(jadeBoots, "erebus.bootsJade");
		GameRegistry.registerItem(jadeSword, "erebus.swordJade");
		GameRegistry.registerItem(jadePickaxe, "erebus.pickaxeJade");
		GameRegistry.registerItem(jadeAxe, "erebus.axeJade");
		GameRegistry.registerItem(jadeShovel, "erebus.shovelJade");
		GameRegistry.registerItem(jadePaxel, "erebus.paxelJade");
		GameRegistry.registerItem(jadeHoe, "erebus.hoeJade");

		GameRegistry.registerItem(exoskeletonHelmet, "erebus.helmetExo");
		GameRegistry.registerItem(exoskeletonBody, "erebus.chestplateExo");
		GameRegistry.registerItem(exoskeletonLegs, "erebus.leggingsExo");
		GameRegistry.registerItem(exoskeletonBoots, "erebus.bootsExo");

		GameRegistry.registerItem(reinExoskeletonHelmet, "erebus.exoHelmetRein");
		GameRegistry.registerItem(reinExoskeletonBody, "erebus.exoChestplateRein");
		GameRegistry.registerItem(reinExoskeletonLegs, "erebus.exoLeggingsRein");
		GameRegistry.registerItem(reinExoskeletonBoots, "erebus.exoBootsRein");

		GameRegistry.registerItem(fossilClub, "erebus.clubBone");
		GameRegistry.registerItem(waspSword, "erebus.waspSword");
		GameRegistry.registerItem(maxSpeedBow, "erebus.maxSpeedBow");
		GameRegistry.registerItem(waspDagger, "erebus.waspDagger");
		GameRegistry.registerItem(scorpionPincer, "erebus.scorpionPincer");
		GameRegistry.registerItem(webSlinger, "erebus.webSlinger");
		GameRegistry.registerItem(blockExtractor, "erebus.blockExtractor");
		GameRegistry.registerItem(woodlouseBall, "erebus.woodlouseBall");
		GameRegistry.registerItem(rolledNewspaper, "erebus.rolledNewspaper");

		GameRegistry.registerItem(compoundGoggles, "erebus.compoundGoggles");
		GameRegistry.registerItem(sprintLeggings, "erebus.sprintLeggings");
		GameRegistry.registerItem(jumpBoots, "erebus.jumpBoots");
		GameRegistry.registerItem(armorGlider, "erebus.armorGlider");
		GameRegistry.registerItem(armorGliderPowered, "erebus.armorGliderPowered");
		GameRegistry.registerItem(lightCrown, "erebus.lightCrown");

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.WATER, new ItemStack(bamBucket, 1, 1), new ItemStack(bamBucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("honey", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(bamBucket, 1, 3), new ItemStack(bamBucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("honey", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(bucketHoney), new ItemStack(Item.bucketEmpty));
	}

	private static void registerProperties() {
		MinecraftForge.setToolClass(jadeAxe, "axe", 2);
		MinecraftForge.setToolClass(jadePickaxe, "pickaxe", 2);
		MinecraftForge.setToolClass(jadeShovel, "shovel", 2);

		GameRegistry.registerFuelHandler(new IFuelHandler() {

			@Override
			public int getBurnTime(ItemStack fuel) {
				return fuel.itemID == erebusMaterials.itemID && fuel.getItemDamage() == DATA.bamboo.ordinal() ? 300 : 0;
			}
		});
	}
}