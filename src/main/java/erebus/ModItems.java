package erebus;

import java.lang.reflect.Field;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.item.AntTamingAmulet;
import erebus.item.ArmorGlider;
import erebus.item.BeeTamingAmulet;
import erebus.item.BlockExtractor;
import erebus.item.BottleAntiVenom;
import erebus.item.BucketAntiVenom;
import erebus.item.BucketOfBeetleJuice;
import erebus.item.CavemanClub;
import erebus.item.Compost;
import erebus.item.CompoundGoggles;
import erebus.item.EncrustedDiamond;
import erebus.item.ExoskeletonArmor;
import erebus.item.FlowerSeeds;
import erebus.item.Food;
import erebus.item.HeartBerries;
import erebus.item.HomingBeecon;
import erebus.item.HomingBeeconAdvanced;
import erebus.item.HornOfSummoning;
import erebus.item.ItemDoorErebus;
import erebus.item.JadeArmour;
import erebus.item.JadeAxe;
import erebus.item.JadePickaxe;
import erebus.item.JumpBoots;
import erebus.item.Materials;
import erebus.item.Materials.DATA;
import erebus.item.MaxSpeedBow;
import erebus.item.MushroomHelm;
import erebus.item.NectarCollector;
import erebus.item.Paxel;
import erebus.item.Planticide;
import erebus.item.PortalActivator;
import erebus.item.ReinExoskeletonArmor;
import erebus.item.RolledNewspaper;
import erebus.item.ScorpionPincer;
import erebus.item.Smoothie;
import erebus.item.SpawnEggs;
import erebus.item.SpiderTShirt;
import erebus.item.SprayCan;
import erebus.item.SprintLeggings;
import erebus.item.WandOfAnimation;
import erebus.item.WaspDagger;
import erebus.item.WaspSword;
import erebus.item.WaterStriders;
import erebus.item.WebSlinger;
import erebus.item.Whetstone;
import erebus.item.WitherWebSlinger;
import erebus.item.WoodlouseBall;
import erebus.item.bambucket.Bambucket;
import erebus.item.bambucket.BambucketAntiVenom;
import erebus.item.bambucket.BambucketBeetleJuice;
import erebus.item.hearts.BurningHeart;
import erebus.item.hearts.ElvenHeart;
import erebus.item.hearts.JadeHeart;
import erebus.item.hearts.ManaHeart;
import erebus.item.hearts.RitualDagger;
import erebus.item.hearts.SoulCrystal;
import erebus.item.hearts.StoneHeart;
import erebus.item.hearts.TerraHeart;
import erebus.item.hearts.TwinHeart;

public class ModItems {

	// BASIC MATERIALS
	public static final Item portalActivator = new PortalActivator().setUnlocalizedName("erebus.portalActivator");
	public static final Item materials = new Materials().setUnlocalizedName("erebus.materials");
	public static final Item food = new Food().setUnlocalizedName("erebus.food");
	public static final Item smoothie = new Smoothie().setUnlocalizedName("erebus.smoothie");
	public static final Item heartBerries = new HeartBerries(0, 0F, false).setUnlocalizedName("erebus.heartBerries").setTextureName("erebus:heartBerries");;
	public static final Item turnip = new ItemSeedFood(4, 0.6F, ModBlocks.blockTurnip, Blocks.farmland).setUnlocalizedName("erebus.turnips").setTextureName("erebus:turnips");
	public static final Item sprayCan = new SprayCan().setUnlocalizedName("erebus.sprayCan").setTextureName("erebus:sprayCan");
	public static final Item wandOfAnimation = new WandOfAnimation().setUnlocalizedName("erebus.wandOfAnimation");
	public static final Item hornOfSummoning = new HornOfSummoning().setUnlocalizedName("erebus.hornOfSummoning").setTextureName("erebus:hornOfSummoning");
	public static final Item nectarCollector = new NectarCollector().setUnlocalizedName("erebus.nectarCollector").setTextureName("erebus:nectarCollector");
	public static final Item beeTamingAmulet = new BeeTamingAmulet().setUnlocalizedName("erebus.beeTamingAmulet").setTextureName("erebus:beeTamingAmulet");
	public static final Item homingBeecon = new HomingBeecon().setUnlocalizedName("erebus.homingBeecon").setTextureName("paper");
	public static final Item homingBeeconAdvanced = new HomingBeeconAdvanced().setUnlocalizedName("erebus.homingBeeconAdvanced").setTextureName("paper");

	public static final Item flowerSeeds = new FlowerSeeds().setUnlocalizedName("erebus.flowerSeeds");
	public static final Item whetstone = new Whetstone().setUnlocalizedName("erebus.whetstone").setTextureName("erebus:whetstone");
	public static final Item encrustedDiamond = new EncrustedDiamond().setUnlocalizedName("erebus.encrustedDiamond").setTextureName("erebus:encrustedDiamond");
	public static final Item antTamingAmulet = new AntTamingAmulet().setUnlocalizedName("erebus.antTamingAmulet").setTextureName("erebus:antTamingAmulet");
	public static final Item compost = new Compost();
	public static final Item planticide = new Planticide();

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
	public static final Item jadeHelmet = new JadeArmour(0).setUnlocalizedName("erebus.helmetJade").setTextureName("erebus:helmetJade");
	public static final Item jadeBody = new JadeArmour(1).setUnlocalizedName("erebus.chestplateJade").setTextureName("erebus:chestplateJade");
	public static final Item jadeLegs = new JadeArmour(2).setUnlocalizedName("erebus.leggingsJade").setTextureName("erebus:leggingsJade");
	public static final Item jadeBoots = new JadeArmour(3).setUnlocalizedName("erebus.bootsJade").setTextureName("erebus:bootsJade");
	public static final Item jadeSword = new ItemSword(ModMaterials.toolJADE).setUnlocalizedName("erebus.swordJade").setTextureName("erebus:swordJade");
	public static final Item jadePickaxe = new JadePickaxe().setUnlocalizedName("erebus.pickaxeJade").setTextureName("erebus:pickaxeJade");
	public static final Item jadeAxe = new JadeAxe().setUnlocalizedName("erebus.axeJade").setTextureName("erebus:axeJade");
	public static final Item jadeShovel = new ItemSpade(ModMaterials.toolJADE).setUnlocalizedName("erebus.shovelJade").setTextureName("erebus:shovelJade");
	public static final Item jadePaxel = new Paxel(ModMaterials.toolJADEPAXEL).setUnlocalizedName("erebus.paxelJade").setTextureName("erebus:paxelJade");
	public static final Item jadeHoe = new ItemHoe(ModMaterials.toolJADE).setUnlocalizedName("erebus.hoeJade").setTextureName("erebus:hoeJade");

	// EXOSKELETON STUFF
	public static final Item exoskeletonHelmet = new ExoskeletonArmor(0).setUnlocalizedName("erebus.helmetExo").setTextureName("erebus:helmetExo");
	public static final Item exoskeletonBody = new ExoskeletonArmor(1).setUnlocalizedName("erebus.chestplateExo").setTextureName("erebus:chestplateExo");
	public static final Item exoskeletonLegs = new ExoskeletonArmor(2).setUnlocalizedName("erebus.leggingsExo").setTextureName("erebus:leggingsExo");
	public static final Item exoskeletonBoots = new ExoskeletonArmor(3).setUnlocalizedName("erebus.bootsExo").setTextureName("erebus:bootsExo");

	public static final Item reinExoskeletonHelmet = new ReinExoskeletonArmor(0).setUnlocalizedName("erebus.exoHelmetRein").setTextureName("erebus:exoHelmetRein");
	public static final Item reinExoskeletonBody = new ReinExoskeletonArmor(1).setUnlocalizedName("erebus.exoChestplateRein").setTextureName("erebus:exoChestplateRein");
	public static final Item reinExoskeletonLegs = new ReinExoskeletonArmor(2).setUnlocalizedName("erebus.exoLeggingsRein").setTextureName("erebus:exoLeggingsRein");
	public static final Item reinExoskeletonBoots = new ReinExoskeletonArmor(3).setUnlocalizedName("erebus.exoBootsRein").setTextureName("erebus:exoBootsRein");

	// MISC WEAPONS
	public static final Item fossilClub = new CavemanClub().setFull3D().setUnlocalizedName("erebus.clubBone").setTextureName("erebus:clubBone");
	public static final Item waspSword = new WaspSword().setUnlocalizedName("erebus.waspSword").setTextureName("paper");
	public static final Item maxSpeedBow = new MaxSpeedBow().setUnlocalizedName("erebus.maxSpeedBow").setTextureName("erebus:maxSpeedBow");
	public static final Item waspDagger = new WaspDagger().setUnlocalizedName("erebus.waspDagger");
	public static final Item scorpionPincer = new ScorpionPincer().setUnlocalizedName("erebus.scorpionPincer").setTextureName("paper");
	public static final Item webSlinger = new WebSlinger().setUnlocalizedName("erebus.webSlinger");
	public static final Item witherWebSlinger = new WitherWebSlinger().setUnlocalizedName("erebus.witherWebSlinger");
	public static final Item blockExtractor = new BlockExtractor().setFull3D().setUnlocalizedName("erebus.blockExtractor").setTextureName("erebus:blockExtractor");
	public static final Item woodlouseBall = new WoodlouseBall().setUnlocalizedName("erebus.woodlouseBall");
	public static final Item rolledNewspaper = new RolledNewspaper();

	// ANIMATION MAGIC
	public static final Item ritualDagger = new RitualDagger();
	public static final Item jadeHeart = new JadeHeart();
	public static final Item soulCrystal = new SoulCrystal();
	public static final Item manaHeart = new ManaHeart();
	public static final Item elvenHeart = new ElvenHeart();
	public static final Item terraHeart = new TerraHeart();
	public static final Item stoneHeart = new StoneHeart();
	public static final Item burningHeart = new BurningHeart();
	public static final Item twinHeart = new TwinHeart();

	// MISC ARMOR
	public static final Item reinCompoundGoggles = new CompoundGoggles(ModMaterials.armorREINEXOSKELETON, 2, 0).setUnlocalizedName("erebus.reinCompoundGoggles").setTextureName("erebus:reinCompoundGoggles");
	public static final Item compoundGoggles = new CompoundGoggles(ModMaterials.armorEXOSKELETON, 2, 0).setUnlocalizedName("erebus.compoundGoggles").setTextureName("erebus:compoundGoggles");
	public static final Item sprintLeggings = new SprintLeggings(ModMaterials.armorREINEXOSPECIAL, 2).setUnlocalizedName("erebus.sprintLeggings").setTextureName("erebus:sprintLeggings");
	public static final Item jumpBoots = new JumpBoots(ModMaterials.armorREINEXOSPECIAL, 3).setUnlocalizedName("erebus.jumpBoots").setTextureName("erebus:jumpBoots");
	public static final Item armorGlider = new ArmorGlider().setUnlocalizedName("erebus.armorGlider").setTextureName("erebus:armorGlider");
	public static final Item armorGliderPowered = new ArmorGlider().setUnlocalizedName("erebus.armorGliderPowered").setTextureName("erebus:armorGliderPowered");
	public static final Item mushroomHelm = new MushroomHelm(0).setUnlocalizedName("erebus.mushroomHelm").setTextureName("erebus:mushroomHelm");
	public static final Item spiderTShirt = new SpiderTShirt(1).setUnlocalizedName("erebus.spiderTShirt").setTextureName("erebus:spiderTShirtItem");
	public static final Item waterStriders = new WaterStriders(ModMaterials.armorREINEXOSPECIAL, 3).setUnlocalizedName("erebus.waterStriders").setTextureName("erebus:waterStriders");
	
	// CREATIVE
	public static final Item spawnEggs = new SpawnEggs().setUnlocalizedName("erebus.monsterPlacer").setTextureName("spawn_egg");

	// BUCKETS
	public static final Item bambucket = new Bambucket();
	public static final Item bambucketWater = new Bambucket(Blocks.flowing_water).setUnlocalizedName("erebus.bambucketWater").setTextureName("erebus:bambucketWater");
	public static final Item bambucketHoney = new Bambucket(ModBlocks.honeyBlock).setUnlocalizedName("erebus.bambucketHoney").setTextureName("erebus:bambucketHoney");
	public static final Item bambucketBeetleJuice = new BambucketBeetleJuice();
	public static final Item bambucketAntiVenom = new BambucketAntiVenom();

	public static final Item bucketAntiVenom = new BucketAntiVenom().setUnlocalizedName("erebus.bucketAntiVenom").setTextureName("erebus:bucketAntiVenom");
	public static final Item bucketBeetleJuice = new BucketOfBeetleJuice().setUnlocalizedName("erebus.bucketBeetleJuice").setTextureName("erebus:bucketBeetleJuice");
	public static final Item bucketHoney = new ItemBucket(ModBlocks.honeyBlock).setMaxStackSize(1).setContainerItem(Items.bucket).setCreativeTab(ModTabs.specials).setUnlocalizedName("erebus.bucketHoney").setTextureName("erebus:bucketHoney");

	public static final Item bottleAntiVenom = new BottleAntiVenom().setUnlocalizedName("erebus.bottleAntiVenom");

	public static void init() {
		initCreativeTabs();
		registerItems();
		registerProperties();
	}

	private static void initCreativeTabs() {
		ModTabs.items.setTab(materials, whetstone, food, smoothie, turnip, encrustedDiamond);
		ModTabs.gears.setTab(jadeHelmet, jadeBody, jadeLegs, jadeBoots, jadeSword, jadePickaxe, jadeAxe, jadeShovel, jadePaxel, jadeHoe);
		ModTabs.gears.setTab(exoskeletonHelmet, exoskeletonBody, exoskeletonLegs, exoskeletonBoots, reinExoskeletonHelmet, reinExoskeletonBody, reinExoskeletonLegs, reinExoskeletonBoots, spiderTShirt);
		ModTabs.gears.setTab(fossilClub, waspSword, waspDagger, maxSpeedBow, wandOfAnimation, scorpionPincer, woodlouseBall, rolledNewspaper);
		ModTabs.gears.setTab(mushroomHelm, compoundGoggles, reinCompoundGoggles, armorGlider, armorGliderPowered, sprintLeggings, jumpBoots, blockExtractor, nectarCollector);
		ModTabs.specials.setTab(portalActivator, bucketBeetleJuice, bucketHoney, beeTamingAmulet, homingBeecon, homingBeeconAdvanced, antTamingAmulet, sprayCan, hornOfSummoning, flowerSeeds);
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
				return fuel.getItem() == materials && fuel.getItemDamage() == DATA.bamboo.ordinal() ? 300 : 0;
			}
		});
	}
}