package erebus;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import erebus.items.ItemAmuletAntTaming;
import erebus.items.ItemArmorBamboo;
import erebus.items.ItemArmorExoskeleton;
import erebus.items.ItemArmorJade;
import erebus.items.ItemArmorReinExoskeleton;
import erebus.items.ItemArmorRhino;
import erebus.items.ItemArmorWaterStriders;
import erebus.items.ItemAxeJade;
import erebus.items.ItemBootsJump;
import erebus.items.ItemCompoundGoggles;
import erebus.items.ItemErebusFood;
import erebus.items.ItemErebusShield;
import erebus.items.ItemFoodHeartBerries;
import erebus.items.ItemFoodStagHeart;
import erebus.items.ItemHelmMushroom;
import erebus.items.ItemHelmRhino;
import erebus.items.ItemJadeHoe;
import erebus.items.ItemJadePaxel;
import erebus.items.ItemJadeShovel;
import erebus.items.ItemJadeSword;
import erebus.items.ItemLeggingsSprint;
import erebus.items.ItemMaterials;
import erebus.items.ItemNectarCollector;
import erebus.items.ItemPickaxeJade;
import erebus.items.ItemSpiderTShirt;
import erebus.items.ItemWoodlouseBall;
import erebus.items.ShieldTypeBasic;
import erebus.lib.Reference;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeedFood;
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

	// MISC ARMOR
	public static final Item REIN_COMPOUND_GOGGLES = new ItemCompoundGoggles(ModMaterials.ARMOR_REIN_EXOSKELETON, EntityEquipmentSlot.HEAD);
	public static final Item COMPOUND_GOGGLES = new ItemCompoundGoggles(ModMaterials.ARMOR_EXOSKELETON, EntityEquipmentSlot.HEAD);
	public static final Item SPRINT_LEGGINGS = new ItemLeggingsSprint();
	public static final Item JUMP_BOOTS = new ItemBootsJump();
	
	/* TODO FIX ERRORS
	public static final Item GLIDER_CHESTPLATE = new ItemArmorGlider();
	public static final Item GLIDER_CHESTPLATE_POWERED =  new ItemArmorGlider();
	*/
	public static final Item MUSHROOM_HELMET = new ItemHelmMushroom(EntityEquipmentSlot.HEAD);
	public static final Item SPIDER_T_SHIRT = new ItemSpiderTShirt(EntityEquipmentSlot.CHEST);
	public static final Item WATER_STRIDERS = new ItemArmorWaterStriders(EntityEquipmentSlot.FEET);
	
	public static final ItemErebusShield BAMBOO_SHIELD = new ItemErebusShield();
	public static final ItemErebusShield EXOSKELETON_SHIELD = new ItemErebusShield();
	public static final ItemErebusShield JADE_SHIELD = new ItemErebusShield();
	public static final ItemErebusShield REIN_EXOSKELETON_SHIELD = new ItemErebusShield();
	public static final ItemErebusShield RHINO_EXOSKELETON_SHIELD = new ItemErebusShield();

	// RANDOM STUFF
	public static final Item NECTAR_COLLECTOR = new ItemNectarCollector();
	public static final Item ANT_TAMING_AMULET = new ItemAmuletAntTaming();
	public static final Item WOODLOUSE_BALL = new ItemWoodlouseBall();

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
				null, null, ModMaterials.ARMOR_BAMBOO.getDurability(EntityEquipmentSlot.CHEST)));

		EXOSKELETON_SHIELD.setShieldType(new ShieldTypeBasic(
				ItemMaterials.EnumErebusMaterialsType.SHIELD_EXO_PLATE_FACE.createStack(),
				ItemMaterials.EnumErebusMaterialsType.PLATE_EXO.createStack(),
				null, null, ModMaterials.ARMOR_EXOSKELETON.getDurability(EntityEquipmentSlot.CHEST)));

		JADE_SHIELD.setShieldType(new ShieldTypeBasic(
				ItemMaterials.EnumErebusMaterialsType.SHIELD_JADE_FACE.createStack(),
				ItemMaterials.EnumErebusMaterialsType.JADE.createStack(),
				null, null, ModMaterials.ARMOR_JADE.getDurability(EntityEquipmentSlot.CHEST)));

		REIN_EXOSKELETON_SHIELD.setShieldType(new ShieldTypeBasic(
				ItemMaterials.EnumErebusMaterialsType.SHIELD_REIN_EXO_FACE.createStack(),
				ItemMaterials.EnumErebusMaterialsType.REINFORCED_PLATE_EXO.createStack(),
				null, null, ModMaterials.ARMOR_EXOSKELETON.getDurability(EntityEquipmentSlot.CHEST)));

		RHINO_EXOSKELETON_SHIELD.setShieldType(new ShieldTypeBasic(
				ItemMaterials.EnumErebusMaterialsType.SHIELD_RHINO_EXO_FACE.createStack(),
				ItemMaterials.EnumErebusMaterialsType.PLATE_EXO_RHINO.createStack(),
				null, null, ModMaterials.ARMOR_REIN_EXOSKELETON.getDurability(EntityEquipmentSlot.CHEST)));
	}

	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	public static class RegistrationHandlerBlocks {

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
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
				else {
					ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString(), "inventory"));
				}
		}
	}

	public static interface ISubItemsItem {
		List<String> getModels();
	}
}