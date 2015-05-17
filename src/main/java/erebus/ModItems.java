package erebus;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.block.bamboo.BlockBambooShoot;
import erebus.block.plants.BlockHangerPlants;
import erebus.item.*;
import erebus.item.ItemFood;
import erebus.item.bambucket.ItemBambucket;
import erebus.item.bambucket.ItemBambucketAntiVenom;
import erebus.item.bambucket.ItemBambucketBeetleJuice;
import erebus.item.hearts.*;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketSound;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.lang.reflect.Field;
import java.util.List;

public class ModItems extends Item {

	// BASIC MATERIALS
	public static final Item portalActivator = new ItemPortalActivator().setUnlocalizedName("erebus.portalActivator");
	public static final Item materials = new ModItems().setUnlocalizedName("erebus.materials");
	public static final Item food = new ItemFood().setUnlocalizedName("erebus.food");
	public static final Item smoothie = new ItemFoodSmoothie().setUnlocalizedName("erebus.smoothie");
	public static final Item heartBerries = new ItemFoodHeartBerries(0, 0F, false).setUnlocalizedName("erebus.heartBerries").setTextureName("erebus:heartBerries");
	public static final Item lifeBlood = new ItemFoodHeartBerries(0, 0F, false).setUnlocalizedName("erebus.lifeBlood").setTextureName("erebus:lifeBlood");
	public static final Item turnip = new ItemSeedFood(4, 0.6F, ModBlocks.blockTurnip, Blocks.farmland).setUnlocalizedName("erebus.turnips").setTextureName("erebus:turnips");
	public static final Item sprayCan = new ItemSprayCan().setUnlocalizedName("erebus.sprayCan").setTextureName("erebus:sprayCan");
	public static final Item wandOfAnimation = new ItemWandOfAnimation().setUnlocalizedName("erebus.wandOfAnimation");
	public static final Item hornOfSummoning = new ItemHornOfSummoning().setUnlocalizedName("erebus.hornOfSummoning").setTextureName("erebus:hornOfSummoning");
	public static final Item nectarCollector = new ItemNectarCollector().setUnlocalizedName("erebus.nectarCollector").setTextureName("erebus:nectarCollector");
	public static final Item beeTamingAmulet = new ItemAmuletBeeTaming().setUnlocalizedName("erebus.beeTamingAmulet").setTextureName("erebus:beeTamingAmulet");
	public static final Item homingBeecon = new ItemHomingBeecon().setUnlocalizedName("erebus.homingBeecon").setTextureName("paper");
	public static final Item homingBeeconAdvanced = new ItemHomingBeeconAdvanced().setUnlocalizedName("erebus.homingBeeconAdvanced").setTextureName("paper");
	public static final Item cabbageSeeds = new ItemSeedFood(4, 0.6F, ModBlocks.blockCabbage, Blocks.farmland).setUnlocalizedName("erebus.cabbageSeeds").setTextureName("erebus:cabbageSeeds");
	public static final Item idols = new ItemDungeonIdols();

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
	public static final Item jadeSword = new ItemSword(ModMaterials.toolJADE).setUnlocalizedName("erebus.swordJade").setTextureName("erebus:swordJade");
	public static final Item jadePickaxe = new ItemPickaxeJade().setUnlocalizedName("erebus.pickaxeJade").setTextureName("erebus:pickaxeJade");
	public static final Item jadeAxe = new ItemAxeJade().setUnlocalizedName("erebus.axeJade").setTextureName("erebus:axeJade");
	public static final Item jadeShovel = new ItemSpade(ModMaterials.toolJADE).setUnlocalizedName("erebus.shovelJade").setTextureName("erebus:shovelJade");
	public static final Item jadePaxel = new ItemPaxel(ModMaterials.toolJADEPAXEL).setUnlocalizedName("erebus.paxelJade").setTextureName("erebus:paxelJade");
	public static final Item jadeHoe = new ItemHoe(ModMaterials.toolJADE).setUnlocalizedName("erebus.hoeJade").setTextureName("erebus:hoeJade");

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
	public static final Item fossilClub = new ItemCavemanClub().setFull3D().setUnlocalizedName("erebus.clubBone").setTextureName("erebus:clubBone");
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

	// ANIMATION MAGIC
	public static final Item ritualDagger = new ItemRitualDagger();
	public static final Item jadeHeart = new ItemHeartJade();
	public static final Item soulCrystal = new ItemSoulCrystal();
	public static final Item manaHeart = new ItemHeartMana();
	public static final Item elvenHeart = new ItemHeartElven();
	public static final Item terraHeart = new ItemHeartTerra();
	public static final Item stoneHeart = new ItemHeartStone();
	public static final Item burningHeart = new ItemHeartBurning();
	public static final Item twinHeart = new ItemHeartTwin();

	// MISC ARMOR
	public static final Item reinCompoundGoggles = new ItemCompoundGoggles(ModMaterials.armorREINEXOSKELETON, 2, 0).setUnlocalizedName("erebus.reinCompoundGoggles").setTextureName("erebus:reinCompoundGoggles");
	public static final Item compoundGoggles = new ItemCompoundGoggles(ModMaterials.armorEXOSKELETON, 2, 0).setUnlocalizedName("erebus.compoundGoggles").setTextureName("erebus:compoundGoggles");
	public static final Item sprintLeggings = new ItemLeggingsSprint(ModMaterials.armorREINEXOSPECIAL, 2).setUnlocalizedName("erebus.sprintLeggings").setTextureName("erebus:sprintLeggings");
	public static final Item jumpBoots = new ItemBootsJump(ModMaterials.armorREINEXOSPECIAL, 3).setUnlocalizedName("erebus.jumpBoots").setTextureName("erebus:jumpBoots");
	public static final Item armorGlider = new ItemArmorGlider().setUnlocalizedName("erebus.armorGlider").setTextureName("erebus:armorGlider");
	public static final Item armorGliderPowered = new ItemArmorGlider().setUnlocalizedName("erebus.armorGliderPowered").setTextureName("erebus:armorGliderPowered");
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

	public static final Item bucketAntiVenom = new ItemBucketAntiVenom().setUnlocalizedName("erebus.bucketAntiVenom").setTextureName("erebus:bucketAntiVenom");
	public static final Item bucketBeetleJuice = new ItemBucketOfBeetleJuice().setUnlocalizedName("erebus.bucketBeetleJuice").setTextureName("erebus:bucketBeetleJuice");
	public static final Item bucketHoney = new ItemBucket(ModBlocks.honeyBlock).setMaxStackSize(1).setContainerItem(Items.bucket).setCreativeTab(ModTabs.specials).setUnlocalizedName("erebus.bucketHoney").setTextureName("erebus:bucketHoney");

	public static final Item bottleAntiVenom = new ItemBottleAntiVenom().setUnlocalizedName("erebus.bottleAntiVenom");
	public static final Item smoothieBook = new ItemBookSmoothies().setMaxStackSize(1).setCreativeTab(ModTabs.specials).setUnlocalizedName("erebus.smoothieBook").setTextureName("book_normal");

	public static void init() {
		initCreativeTabs();
		registerItems2();
		registerProperties();
	}

	private static void initCreativeTabs() {
		ModTabs.items.setTab(materials, idols, whetstone, food, smoothie, turnip, cabbageSeeds, encrustedDiamond);
		ModTabs.gears.setTab(jadeHelmet, jadeBody, jadeLegs, jadeBoots, jadeSword, jadePickaxe, jadeAxe, jadeShovel, jadePaxel, jadeHoe);
		ModTabs.gears.setTab(exoskeletonHelmet, exoskeletonBody, exoskeletonLegs, exoskeletonBoots, reinExoskeletonHelmet, reinExoskeletonBody, reinExoskeletonLegs, reinExoskeletonBoots, spiderTShirt);
		ModTabs.gears.setTab(fossilClub, waspSword, waspDagger, maxSpeedBow, wandOfAnimation, scorpionPincer, woodlouseBall, rolledNewspaper, warHammer);
		ModTabs.gears.setTab(mushroomHelm, compoundGoggles, reinCompoundGoggles, armorGlider, armorGliderPowered, sprintLeggings, jumpBoots, blockExtractor, nectarCollector);
		ModTabs.gears.setTab(rhinoExoskeletonHelmet, rhinoExoskeletonBody, rhinoExoskeletonLegs, rhinoExoskeletonBoots);
		ModTabs.specials.setTab(portalActivator, bucketBeetleJuice, bucketHoney, beeTamingAmulet, homingBeecon, homingBeeconAdvanced, antTamingAmulet, sprayCan, hornOfSummoning, flowerSeeds);
	}

	private static void registerItems2() {
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

	@SideOnly(Side.CLIENT)
	public static IIcon[] icons;

	public ModItems() {
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side == 1 && is.getItemDamage() == ModItems.DATA.bambooShoot.ordinal() && player.canPlayerEdit(x, y, z, side, is) && player.canPlayerEdit(x, y + 1, z, side, is)) {
			Block soil = world.getBlock(x, y, z);

			if (soil != null && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, (BlockBambooShoot) ModBlocks.bambooShoot) && world.isAirBlock(x, y + 1, z)) {
				world.setBlock(x, y + 1, z, ModBlocks.bambooShoot);

				if (!player.capabilities.isCreativeMode)
					--is.stackSize;
				return true;
			}
		}

		if (side == 0 && is.getItemDamage() == ModItems.DATA.darkFruitSeeds.ordinal() && player.canPlayerEdit(x, y, z, side, is) && player.canPlayerEdit(x, y - 1, z, side, is)) {
			Block block = world.getBlock(x, y, z);

			if (block != null && block.getMaterial().blocksMovement()) {
				world.setBlock(x, y - 1, z, ModBlocks.hanger, BlockHangerPlants.dataHanger0, 2);

				if (!player.capabilities.isCreativeMode)
					--is.stackSize;
				return true;
			}
		}

		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		if (!world.isRemote) {
			int damage = is.getItemDamage();

			if (damage == ModItems.DATA.bioVelocity.ordinal() || damage == ModItems.DATA.supernaturalvelocity.ordinal()) {
				PotionEffect currentSpeed = player.getActivePotionEffect(Potion.moveSpeed);

				if (currentSpeed == null || damage == ModItems.DATA.bioVelocity.ordinal() && currentSpeed.getAmplifier() < 1 || damage == ModItems.DATA.supernaturalvelocity.ordinal() && currentSpeed.getAmplifier() < 3) {
					player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, damage == ModItems.DATA.bioVelocity.ordinal() ? 280 : 210, damage == ModItems.DATA.bioVelocity.ordinal() ? 1 : 3, true));
					PacketPipeline.sendToAll(new PacketSound(PacketSound.SOUND_VELOCITY_USE, player.posX, player.posY, player.posZ, 1.2F, 1F));
				} else
					return is;
			}

			if (damage == ModItems.DATA.camoPowder.ordinal()) {
				PotionEffect currentVisibility = player.getActivePotionEffect(Potion.invisibility);

				if (currentVisibility == null || damage == ModItems.DATA.camoPowder.ordinal() && currentVisibility.getAmplifier() < 3) {
					player.addPotionEffect(new PotionEffect(Potion.invisibility.id, damage == ModItems.DATA.camoPowder.ordinal() ? 280 : 210, damage == ModItems.DATA.camoPowder.ordinal() ? 1 : 3, true));
					PacketPipeline.sendToAll(new PacketSound(PacketSound.SOUND_CAMO_USE, player.posX, player.posY, player.posZ, 1.2F, 1F));
				} else
					return is;
			} else
				return is;

			if (!player.capabilities.isCreativeMode)
				--is.stackSize;
		}

		return is;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		ModItems.icons = new IIcon[ModItems.DATA.values().length];
		int i = 0;
		for (ModItems.DATA d : ModItems.DATA.values())
			ModItems.icons[i++] = iconRegister.registerIcon("erebus:" + d.name());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		if (meta < 0 || meta >= ModItems.icons.length)
			return null;
		return ModItems.icons[meta];
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < ModItems.DATA.values().length; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return super.getUnlocalizedName() + "." + is.getItemDamage();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack is, int pass) {
		return is.getItemDamage() == ModItems.DATA.whetstonePowder.ordinal();
	}

	public enum DATA {
		plateExo,
		jade,
		shardBone,
		bamboo,
		compoundEyes,
		compoundLens,
		flyWing,
		petrifiedWood,
		bioVelocity,
		elasticFibre,
		waspSting,
		bambooShoot,
		redGem,
		bioLuminescence,
		supernaturalvelocity,
		altarFragment,
		reinforcedPlateExo,
		gliderWing,
		scorpionPincer,
		camoPowder,
		nectar,
		honeyDrip,
		poisonGland,
		mudBrick,
		whetstonePowder,
		dragonflyWing,
		weepingBluePetal,
		papyrus,
		enhancedGliderWing,
		repellent,
		mucusCharge,
		nettleleaves,
		nettleflowers,
		darkFruitSeeds,
		mossBall,
		yellowDottedFungus,
		plateExoRhino,
		rhinoBeetleHorn,
		antPheromones,
		gaeanGem,
		crimsonHeart,
		sapBall,
		ingotAluminium,
		ingotCopper,
		ingotLead,
		ingotSilver,
		ingotTin,
		gneissRock,
		hideShroom,
		rhinoRidingKit,
		beetleTamingAmulet,
		umberGolemCore,
		umberGolemHead,
		umberGolemClaw,
		umberGolemLegs,
		jadeBerries,
		snapperRoot,
		hydrofuge,
		waterRepellent,
		smoothieGlass,
		magmaCrawlerEye,
		stewPot,
		titanStew;

		public ItemStack createStack() {
			return createStack(1);
		}

		public ItemStack createStack(int size) {
			return new ItemStack(materials, size, ordinal());
		}
	}
}