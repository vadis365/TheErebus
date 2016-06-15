package erebus;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import erebus.items.ItemArmorBamboo;
import erebus.items.ItemArmorExoskeleton;
import erebus.items.ItemArmorJade;
import erebus.items.ItemArmorReinExoskeleton;
import erebus.items.ItemArmorRhino;
import erebus.items.ItemArmorWaterStriders;
import erebus.items.ItemAxeJade;
import erebus.items.ItemBootsJump;
import erebus.items.ItemCompoundGoggles;
import erebus.items.ItemHelmMushroom;
import erebus.items.ItemHelmRhino;
import erebus.items.ItemJadeHoe;
import erebus.items.ItemJadePaxel;
import erebus.items.ItemJadeShovel;
import erebus.items.ItemJadeSword;
import erebus.items.ItemLeggingsSprint;
import erebus.items.ItemMaterials;
import erebus.items.ItemPickaxeJade;
import erebus.items.ItemSpiderTShirt;
import erebus.lib.Reference;

public class ModItems {

	public static final Item MATERIALS = new ItemMaterials();
	
	
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
	public static final Item WATER_STRIDERS = new ItemArmorWaterStriders(EntityEquipmentSlot.LEGS);
	


	public static void init() {
		try {
			for (Field field : ModItems.class.getDeclaredFields()) {
				Object obj = field.get(null);
				if (obj instanceof Item) {
					Item item = (Item) obj;
					String name = field.getName().toLowerCase(Locale.ENGLISH);

					GameRegistry.register(item.setRegistryName(Reference.MOD_ID, name).setUnlocalizedName(Reference.MOD_ID + "." + name));
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static void registerRenderers() {
		try {
			for (Field field : ModItems.class.getDeclaredFields()) {
				Object obj = field.get(null);
				if (obj instanceof Item) {
					Item item = (Item) obj;

					if (item instanceof ISubItemsItem) {
						List<String> models = ((ISubItemsItem) item).getModels();
						for (int i = 0; i < models.size(); i++)
							ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(Reference.MOD_ID + ":" + models.get(i), "inventory"));
					} else {
						String name = field.getName().toLowerCase(Locale.ENGLISH);
						ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + name, "inventory"));
					}
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static interface ISubItemsItem {
		List<String> getModels();
	}
}