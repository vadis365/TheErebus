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
import erebus.item.ArmorGlider;
import erebus.item.Bambucket;
import erebus.item.BeeTamingAmulet;
import erebus.item.BlockExtractor;
import erebus.item.BucketOfBeetleJuice;
import erebus.item.BucketOfHoney;
import erebus.item.CavemanClub;
import erebus.item.CompoundGoggles;
import erebus.item.CrownOfLuminosity;
import erebus.item.EncrustedDiamond;
import erebus.item.ErebusFood;
import erebus.item.ErebusMaterial;
import erebus.item.ErebusMaterial.DATA;
import erebus.item.ErebusSpecial;
import erebus.item.ExoskeletonArmor;
import erebus.item.FlowerSeeds;
import erebus.item.HomingBeecon;
import erebus.item.HornOfSummoning;
import erebus.item.JadeArmour;
import erebus.item.JadeAxe;
import erebus.item.JadePickaxe;
import erebus.item.JumpBoots;
import erebus.item.MaxSpeedBow;
import erebus.item.MetalIngots;
import erebus.item.MushroomHelm;
import erebus.item.NectarCollector;
import erebus.item.Paxel;
import erebus.item.PortalActivator;
import erebus.item.ReinExoskeletonArmor;
import erebus.item.RhinoArmor;
import erebus.item.RhinoHelm;
import erebus.item.RitualDagger;
import erebus.item.RolledNewspaper;
import erebus.item.ScorpionPincer;
import erebus.item.SpawnEggs;
import erebus.item.SprayCan;
import erebus.item.SprintLeggings;
import erebus.item.WandOfAnimation;
import erebus.item.WaspDagger;
import erebus.item.WaspSword;
import erebus.item.WebSlinger;
import erebus.item.Whetstone;
import erebus.item.WoodlouseBall;
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
	public static Item rhinoExoskeletonHelmet, rhinoExoskeletonBody, rhinoExoskeletonLegs, rhinoExoskeletonBoots;
	
	// MISC WEAPONS
	public static Item fossilClub, waspSword, maxSpeedBow, waspDagger, scorpionPincer, webSlinger, doorAmberItem, woodlouseBall, rolledNewspaper;
	public static Item ritualDagger;

	// MISC ARMOR
	public static Item reinCompoundGoggles, compoundGoggles, sprintLeggings, jumpBoots, armorGlider, armorGliderPowered, lightCrown, mushroomHelm;

	// CREATIVE
	public static Item spawnEggs;

	public static void init() {
		initItems();
		initCreativeTabs();
		registerItems();
		registerProperties();
	}

	private static void initItems() {
		portalActivator = new PortalActivator().setUnlocalizedName("portalActivator").setTextureName("erebus:portalActivator");
		erebusMaterials = new ErebusMaterial().setUnlocalizedName("erebusMaterials");
		erebusSpecialItem = new ErebusSpecial().setUnlocalizedName("erebusSpecialItem");
		erebusFood = new ErebusFood().setUnlocalizedName("erebusFood");
		metalIngot = new MetalIngots();
		bamBucket = new Bambucket().setUnlocalizedName("bamBucket");
		turnip = new ItemSeedFood(4, 0.6F, ModBlocks.blockTurnip, Blocks.farmland).setUnlocalizedName("turnips").setTextureName("erebus:turnips");
		sprayCan = new SprayCan().setUnlocalizedName("sprayCan").setTextureName("erebus:sprayCan");
		wandOfAnimation = new WandOfAnimation().setUnlocalizedName("wandOfAnimation");
		bucketOfBeetleJuice = new BucketOfBeetleJuice().setUnlocalizedName("bucketOfBeetleJuice").setTextureName("erebus:bucketOfBeetleJuice");
		hornOfSummoning = new HornOfSummoning().setUnlocalizedName("hornOfSummoning").setTextureName("erebus:hornOfSummoning");
		nectarCollector = new NectarCollector().setUnlocalizedName("nectarCollector").setTextureName("erebus:nectarCollector");
		beeTamingAmulet = new BeeTamingAmulet().setUnlocalizedName("beeTamingAmulet").setTextureName("erebus:beeTamingAmulet");
		doorAmberItem = new ItemBlockDoorAmber(ModBlocks.doorAmber).setUnlocalizedName("doorAmberItem").setTextureName("erebus:doorAmber");
		bucketHoney = new BucketOfHoney(ModBlocks.erebusHoneyBlock).setUnlocalizedName("bucketHoney").setTextureName("erebus:bucketHoney");
		homingBeecon = new HomingBeecon().setUnlocalizedName("homingBeecon").setTextureName("paper");
		flowerSeeds = new FlowerSeeds().setUnlocalizedName("flowerSeeds");
		whetstone = new Whetstone().setUnlocalizedName("whetstone").setTextureName("erebus:whetstone");
		encrustedDiamond = new EncrustedDiamond().setUnlocalizedName("encrustedDiamond").setTextureName("erebus:encrustedDiamond");

		jadeHelmet = new JadeArmour(0).setUnlocalizedName("helmetJade").setTextureName("erebus:helmetJade");
		jadeBody = new JadeArmour(1).setUnlocalizedName("chestplateJade").setTextureName("erebus:chestplateJade");
		jadeLegs = new JadeArmour(2).setUnlocalizedName("leggingsJade").setTextureName("erebus:leggingsJade");
		jadeBoots = new JadeArmour(3).setUnlocalizedName("bootsJade").setTextureName("erebus:bootsJade");
		jadeSword = new ItemSword(ModMaterials.toolJADE).setUnlocalizedName("swordJade").setTextureName("erebus:swordJade");
		jadePickaxe = new JadePickaxe().setUnlocalizedName("pickaxeJade").setTextureName("erebus:pickaxeJade");
		jadeAxe = new JadeAxe().setUnlocalizedName("axeJade").setTextureName("erebus:axeJade");
		jadeShovel = new ItemSpade(ModMaterials.toolJADE).setUnlocalizedName("shovelJade").setTextureName("erebus:shovelJade");
		jadePaxel = new Paxel(ModMaterials.toolJADEPAXEL).setUnlocalizedName("paxelJade").setTextureName("erebus:paxelJade");
		jadeHoe = new ItemHoe(ModMaterials.toolJADE).setUnlocalizedName("hoeJade").setTextureName("erebus:hoeJade");

		exoskeletonHelmet = new ExoskeletonArmor(0).setUnlocalizedName("helmetExo").setTextureName("erebus:helmetExo");
		exoskeletonBody = new ExoskeletonArmor(1).setUnlocalizedName("chestplateExo").setTextureName("erebus:chestplateExo");
		exoskeletonLegs = new ExoskeletonArmor(2).setUnlocalizedName("leggingsExo").setTextureName("erebus:leggingsExo");
		exoskeletonBoots = new ExoskeletonArmor(3).setUnlocalizedName("bootsExo").setTextureName("erebus:bootsExo");

		reinExoskeletonHelmet = new ReinExoskeletonArmor(0).setUnlocalizedName("exoHelmetRein").setTextureName("erebus:exoHelmetRein");
		reinExoskeletonBody = new ReinExoskeletonArmor(1).setUnlocalizedName("exoChestplateRein").setTextureName("erebus:exoChestplateRein");
		reinExoskeletonLegs = new ReinExoskeletonArmor(2).setUnlocalizedName("exoLeggingsRein").setTextureName("erebus:exoLeggingsRein");
		reinExoskeletonBoots = new ReinExoskeletonArmor(3).setUnlocalizedName("exoBootsRein").setTextureName("erebus:exoBootsRein");

		rhinoExoskeletonHelmet = new RhinoHelm(0).setUnlocalizedName("rhinoHelmet").setTextureName("erebus:rhinoHelm");
		rhinoExoskeletonBody = new RhinoArmor(1).setUnlocalizedName("rhinoChestplate").setTextureName("erebus:rhinoChestplate");
		rhinoExoskeletonLegs = new RhinoArmor(2).setUnlocalizedName("rhinoLeggings").setTextureName("erebus:rhinoLeggings");
		rhinoExoskeletonBoots = new RhinoArmor(3).setUnlocalizedName("rhinoBoots").setTextureName("erebus:rhinoBoots");
	
		fossilClub = new CavemanClub().setFull3D().setUnlocalizedName("clubBone").setTextureName("erebus:clubBone");
		waspSword = new WaspSword().setUnlocalizedName("waspSword").setTextureName("paper");
		maxSpeedBow = new MaxSpeedBow().setUnlocalizedName("maxSpeedBow").setTextureName("erebus:maxSpeedBow");
		waspDagger = new WaspDagger().setUnlocalizedName("waspDagger");
		scorpionPincer = new ScorpionPincer().setUnlocalizedName("scorpionPincer").setTextureName("paper");
		webSlinger = new WebSlinger().setUnlocalizedName("webSlinger").setTextureName("paper");
		blockExtractor = new BlockExtractor().setFull3D().setUnlocalizedName("blockExtractor").setTextureName("erebus:blockExtractor");
		woodlouseBall = new WoodlouseBall().setUnlocalizedName("woodlouseBall");
		rolledNewspaper = new RolledNewspaper().setUnlocalizedName("rolledNewspaper").setTextureName("erebus:rolledNewspaper");
		ritualDagger = new RitualDagger();

		reinCompoundGoggles = new CompoundGoggles(ModMaterials.armorREINEXOSKELETON, 2, 0).setUnlocalizedName("reinCompoundGoggles").setTextureName("erebus:reinCompoundGoggles");
		compoundGoggles = new CompoundGoggles(ModMaterials.armorEXOSKELETON, 2, 0).setUnlocalizedName("compoundGoggles").setTextureName("erebus:compoundGoggles");
		sprintLeggings = new SprintLeggings(ModMaterials.armorREINEXOSPECIAL, 2).setUnlocalizedName("sprintLeggings").setTextureName("erebus:sprintLeggings");
		jumpBoots = new JumpBoots(ModMaterials.armorREINEXOSPECIAL, 3).setUnlocalizedName("jumpBoots").setTextureName("erebus:jumpBoots");
		armorGlider = new ArmorGlider().setUnlocalizedName("armorGlider").setTextureName("erebus:armorGlider");
		armorGliderPowered = new ArmorGlider().setUnlocalizedName("armorGliderPowered").setTextureName("erebus:armorGliderPowered");
		lightCrown = new CrownOfLuminosity(0).setUnlocalizedName("lightCrown").setTextureName("erebus:lightCrown");
		mushroomHelm = new MushroomHelm(0).setUnlocalizedName("mushroomHelm").setTextureName("erebus:mushroomHelm");

		spawnEggs = new SpawnEggs().setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
	}

	private static void initCreativeTabs() {
		ModTabs.items.setTab(erebusMaterials, whetstone, erebusFood, turnip, encrustedDiamond);
		if (ConfigHandler.lead || ConfigHandler.silver || ConfigHandler.copper || ConfigHandler.tin || ConfigHandler.aluminium)
			ModTabs.items.setTab(metalIngot);

		ModTabs.gears.setTab(jadeHelmet, jadeBody, jadeLegs, jadeBoots, jadeSword, jadePickaxe, jadeAxe, jadeShovel, jadePaxel, jadeHoe);
		ModTabs.gears.setTab(exoskeletonHelmet, exoskeletonBody, exoskeletonLegs, exoskeletonBoots, reinExoskeletonHelmet, reinExoskeletonBody, reinExoskeletonLegs, reinExoskeletonBoots);
		ModTabs.gears.setTab(rhinoExoskeletonHelmet, rhinoExoskeletonBody, rhinoExoskeletonLegs, rhinoExoskeletonBoots);
		ModTabs.gears.setTab(fossilClub, waspSword, waspDagger, maxSpeedBow, wandOfAnimation, scorpionPincer, webSlinger, woodlouseBall, rolledNewspaper);
		ModTabs.gears.setTab(mushroomHelm, lightCrown, compoundGoggles, reinCompoundGoggles, armorGlider, armorGliderPowered, sprintLeggings, jumpBoots, blockExtractor, nectarCollector);
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