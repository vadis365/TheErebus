package erebus;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import erebus.items.ItemAmuletAntTaming;
import erebus.items.ItemAmuletBeeTaming;
import erebus.items.ItemAntiVenomBottle;
import erebus.items.ItemArmorBamboo;
import erebus.items.ItemArmorExoskeleton;
import erebus.items.ItemArmorGlider;
import erebus.items.ItemArmorJade;
import erebus.items.ItemArmorReinExoskeleton;
import erebus.items.ItemArmorRhino;
import erebus.items.ItemArmorWaterStriders;
import erebus.items.ItemAxeJade;
import erebus.items.ItemBambucket;
import erebus.items.ItemBootsJump;
import erebus.items.ItemCompost;
import erebus.items.ItemCompoundGoggles;
import erebus.items.ItemDeathCompass;
import erebus.items.ItemDungeonIdols;
import erebus.items.ItemErebusFood;
import erebus.items.ItemErebusShield;
import erebus.items.ItemFlowerSeeds;
import erebus.items.ItemFoodHeartBerries;
import erebus.items.ItemFoodStagHeart;
import erebus.items.ItemHelmMushroom;
import erebus.items.ItemHelmRhino;
import erebus.items.ItemHomingBeecon;
import erebus.items.ItemHomingBeeconAdvanced;
import erebus.items.ItemJadeHoe;
import erebus.items.ItemJadePaxel;
import erebus.items.ItemJadeShovel;
import erebus.items.ItemJadeSword;
import erebus.items.ItemLeggingsSprint;
import erebus.items.ItemMaterials;
import erebus.items.ItemNectarCollector;
import erebus.items.ItemPickaxeJade;
import erebus.items.ItemPlanticide;
import erebus.items.ItemPortalActivator;
import erebus.items.ItemRolledNewspaper;
import erebus.items.ItemScorpionPincer;
import erebus.items.ItemSmoothie;
import erebus.items.ItemSpiderTShirt;
import erebus.items.ItemSprayCan;
import erebus.items.ItemWandOfAnimation;
import erebus.items.ItemWandOfPreservation;
import erebus.items.ItemWarHammer;
import erebus.items.ItemWaspDagger;
import erebus.items.ItemWaspSword;
import erebus.items.ItemWebSlinger;
import erebus.items.ItemWebSlingerWither;
import erebus.items.ItemWhetstone;
import erebus.items.ItemWoodlouseBall;
import erebus.items.ShieldTypeBasic;
import erebus.lib.Reference;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	private static final List<Item> ITEMS = new LinkedList<Item>();
	public static final Item MATERIALS = new ItemMaterials();
	public static final Item EREBUS_FOOD = new ItemErebusFood();
	public static final Item HEART_BERRIES = new ItemFoodHeartBerries(0, 0F, false);
	public static final Item LIFE_BLOOD = new ItemFoodHeartBerries(0, 0F, false);
	public static final Item TURNIP = new ItemSeedFood(4, 0.6F, ModBlocks.CROP_TURNIP, Blocks.FARMLAND).setCreativeTab(ModTabs.PLANTS);
	public static final Item CABBAGE_SEEDS = new ItemSeedFood(4, 0.1F, ModBlocks.CROP_CABBAGE, Blocks.FARMLAND).setCreativeTab(ModTabs.PLANTS);
	public static final Item STAG_HEART_RAW = new ItemFoodStagHeart(0, 0F, false, "raw");
	public static final Item STAG_HEART_COOKED = new ItemFoodStagHeart(20, 0.5F, false, "cooked");
	public static final Item SMOOTHIE = new ItemSmoothie();
	public static final Item IDOLS = new ItemDungeonIdols();
	
	// JADE STUFF
	public static final Item JADE_HELMET = new ItemArmorJade(EntityEquipmentSlot.HEAD);
	public static final Item JADE_CHESTPLATE = new ItemArmorJade(EntityEquipmentSlot.CHEST);
	public static final Item JADE_LEGGINGS = new ItemArmorJade(EntityEquipmentSlot.LEGS);
	public static final Item JADE_BOOTS = new ItemArmorJade(EntityEquipmentSlot.FEET);
	public static final Item JADE_SWORD = new ItemJadeSword();
	public static final Item JADE_PICKAXE = new ItemPickaxeJade();
	public static final Item JADE_AXE = new ItemAxeJade();
	public static final Item JADE_SHOVEL = new ItemJadeShovel();
	public static final Item JADE_PAXEL = new ItemJadePaxel();
	public static final Item JADE_HOE = new ItemJadeHoe();

	// EXOSKELETON STUFF
	public static final Item EXOSKELETON_HELMET = new ItemArmorExoskeleton(EntityEquipmentSlot.HEAD);
	public static final Item EXOSKELETON_CHESTPLATE = new ItemArmorExoskeleton(EntityEquipmentSlot.CHEST);
	public static final Item EXOSKELETON_LEGGINGS = new ItemArmorExoskeleton(EntityEquipmentSlot.LEGS);
	public static final Item EXOSKELETON_BOOTS = new ItemArmorExoskeleton(EntityEquipmentSlot.FEET);

	public static final Item REIN_EXOSKELETON_HELMET = new ItemArmorReinExoskeleton(EntityEquipmentSlot.HEAD);
	public static final Item REIN_EXOSKELETON_CHESTPLATE = new ItemArmorReinExoskeleton(EntityEquipmentSlot.CHEST);
	public static final Item REIN_EXOSKELETON_LEGGINGS = new ItemArmorReinExoskeleton(EntityEquipmentSlot.LEGS);
	public static final Item REIN_EXOSKELETON_BOOTS = new ItemArmorReinExoskeleton(EntityEquipmentSlot.FEET);

	public static final Item RHINO_EXOSKELETON_HELMET = new ItemHelmRhino(EntityEquipmentSlot.HEAD);
	public static final Item RHINO_EXOSKELETON_CHESTPLATE = new ItemArmorRhino(EntityEquipmentSlot.CHEST);
	public static final Item RHINO_EXOSKELETON_LEGGINGS = new ItemArmorRhino(EntityEquipmentSlot.LEGS);
	public static final Item RHINO_EXOSKELETON_BOOTS = new ItemArmorRhino(EntityEquipmentSlot.FEET);
	
	// BAMBOO STUFF
	public static final Item BAMBOO_HELMET = new ItemArmorBamboo(EntityEquipmentSlot.HEAD);
	public static final Item BAMBOO_CHESTPLATE = new ItemArmorBamboo(EntityEquipmentSlot.CHEST);
	public static final Item BAMBOO_LEGGINGS = new ItemArmorBamboo(EntityEquipmentSlot.LEGS);
	public static final Item BAMBOO_BOOTS = new ItemArmorBamboo(EntityEquipmentSlot.FEET);
	public static final Item BAMBUCKET = new ItemBambucket();

	// MISC ARMOR
	public static final Item REIN_COMPOUND_GOGGLES = new ItemCompoundGoggles(ModMaterials.ARMOR_REIN_EXOSKELETON, EntityEquipmentSlot.HEAD);
	public static final Item COMPOUND_GOGGLES = new ItemCompoundGoggles(ModMaterials.ARMOR_EXOSKELETON, EntityEquipmentSlot.HEAD);
	public static final Item SPRINT_LEGGINGS = new ItemLeggingsSprint();
	public static final Item JUMP_BOOTS = new ItemBootsJump();
	public static final ItemArmor GLIDER_CHESTPLATE = (ItemArmor)new ItemArmorGlider();
	public static final ItemArmor GLIDER_CHESTPLATE_POWERED = (ItemArmor)new ItemArmorGlider();

	public static final Item MUSHROOM_HELMET = new ItemHelmMushroom(EntityEquipmentSlot.HEAD);
	public static final Item SPIDER_T_SHIRT = new ItemSpiderTShirt(EntityEquipmentSlot.CHEST);
	public static final Item WATER_STRIDERS = new ItemArmorWaterStriders(EntityEquipmentSlot.FEET);

	// SHIELDS
	public static final ItemErebusShield BAMBOO_SHIELD = new ItemErebusShield();
	public static final ItemErebusShield EXOSKELETON_SHIELD = new ItemErebusShield();
	public static final ItemErebusShield JADE_SHIELD = new ItemErebusShield();
	public static final ItemErebusShield REIN_EXOSKELETON_SHIELD = new ItemErebusShield();
	public static final ItemErebusShield RHINO_EXOSKELETON_SHIELD = new ItemErebusShield();
	
	// MISC WEAPONS
	public static final Item ROLLED_NEWSPAPER = new ItemRolledNewspaper();
	public static final ItemWaspSword WASP_SWORD = new ItemWaspSword();
	public static final ItemWaspDagger WASP_DAGGER = new ItemWaspDagger();
	public static final ItemScorpionPincer ENHANCED_SCORPION_PINCER = new ItemScorpionPincer();
	public static final ItemWarHammer WAR_HAMMER = new ItemWarHammer();
	public static final ItemWebSlinger WEB_SLINGER = new ItemWebSlinger();
	public static final ItemWebSlinger WEB_SLINGER_WITHER = new ItemWebSlingerWither();

	// RANDOM STUFF
	public static final Item NECTAR_COLLECTOR = new ItemNectarCollector();
	public static final Item ANT_TAMING_AMULET = new ItemAmuletAntTaming();
	public static final Item BEE_TAMING_AMULET = new ItemAmuletBeeTaming();
	public static final Item WOODLOUSE_BALL = new ItemWoodlouseBall();
	public static final Item WAND_OF_ANIMATION = new ItemWandOfAnimation();
	public static final Item ANTI_VENOM_BOTTLE = new ItemAntiVenomBottle();
	public static final Item FLOWER_SEED = new ItemFlowerSeeds();
	public static final Item DEATH_COMPASS = new ItemDeathCompass();
	public static final Item SPRAY_CAN = new ItemSprayCan();
	public static final Item WHETSTONE = new ItemWhetstone();
	public static final Item PORTAL_ACTIVATOR = new ItemPortalActivator();
	public static final Item HOMING_BEECON = new ItemHomingBeecon();
	public static final Item HOMING_BEECON_ADVANCED = new ItemHomingBeeconAdvanced();
	public static final Item WAND_OF_PRESERVATION = new ItemWandOfPreservation();
	public static final Item COMPOST = new ItemCompost();
	public static final Item PLANTICIDE = new ItemPlanticide();

	public static void init() {
		try {
			for (Field field : ModItems.class.getDeclaredFields()) {
				Object obj = field.get(null);
				if (obj instanceof Item) {
					Item item = (Item) obj;
					ITEMS.add(item);
					String name = field.getName().toLowerCase(Locale.ENGLISH);
					item.setRegistryName(Reference.MOD_ID, name).setUnlocalizedName(Reference.MOD_ID + "." + name);
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		setupShieldStats();
	}

	public static void setupShieldStats() {
		BAMBOO_SHIELD.setShieldType(new ShieldTypeBasic(
				ItemMaterials.EnumErebusMaterialsType.SHIELD_BAMBOO_FACE.createStack(),
				ItemMaterials.EnumErebusMaterialsType.BAMBOO.createStack(),
				null, null, ModMaterials.ARMOR_BAMBOO.getDurability(EntityEquipmentSlot.CHEST) * 2));

		EXOSKELETON_SHIELD.setShieldType(new ShieldTypeBasic(
				ItemMaterials.EnumErebusMaterialsType.SHIELD_EXO_PLATE_FACE.createStack(),
				ItemMaterials.EnumErebusMaterialsType.PLATE_EXO.createStack(),
				null, null, ModMaterials.ARMOR_EXOSKELETON.getDurability(EntityEquipmentSlot.CHEST) * 2));

		JADE_SHIELD.setShieldType(new ShieldTypeBasic(
				ItemMaterials.EnumErebusMaterialsType.SHIELD_JADE_FACE.createStack(),
				ItemMaterials.EnumErebusMaterialsType.JADE.createStack(),
				null, null, ModMaterials.ARMOR_JADE.getDurability(EntityEquipmentSlot.CHEST) * 2));

		REIN_EXOSKELETON_SHIELD.setShieldType(new ShieldTypeBasic(
				ItemMaterials.EnumErebusMaterialsType.SHIELD_REIN_EXO_FACE.createStack(),
				ItemMaterials.EnumErebusMaterialsType.REINFORCED_PLATE_EXO.createStack(),
				null, null, ModMaterials.ARMOR_REIN_EXOSKELETON.getDurability(EntityEquipmentSlot.CHEST) * 2));

		RHINO_EXOSKELETON_SHIELD.setShieldType(new ShieldTypeBasic(
				ItemMaterials.EnumErebusMaterialsType.SHIELD_RHINO_EXO_FACE.createStack(),
				ItemMaterials.EnumErebusMaterialsType.PLATE_EXO_RHINO.createStack(),
				null, null, ModMaterials.ARMOR_REIN_EXOSKELETON.getDurability(EntityEquipmentSlot.CHEST) * 2));
	}

	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	public static class RegistrationHandlerBlocks {

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			ModItems.init();
			final IForgeRegistry<Item> registry = event.getRegistry();
				for (Item item : ITEMS) {
				registry.register(item);
			}
		}

		@SideOnly(Side.CLIENT)
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			for (Item item : ITEMS)
				if (item instanceof ISubItemsItem) {
					List<String> models = ((ISubItemsItem) item).getModels();
					for (int i = 0; i < models.size(); i++)
						ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(Reference.MOD_ID + ":" + models.get(i), "inventory"));
				}
				else if (item instanceof ItemErebusShield) {
					ModelResourceLocation shield = new ModelResourceLocation("minecraft:shield", "inventory");
			        ModelLoader.setCustomMeshDefinition(item, stack -> shield);
			        ModelBakery.registerItemVariants(item, shield);
				}
				else if (item instanceof ItemBambucket) {
					ModelResourceLocation bambucket = new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, "bambucket"), "inventory");
			        ModelLoader.setCustomMeshDefinition(item, stack -> bambucket);
			        ModelBakery.registerItemVariants(item, bambucket);
				}
				else {
					ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString(), "inventory"));
				}
		}
	}

	public static interface ISubItemsItem {
		List<String> getModels();
	}
}