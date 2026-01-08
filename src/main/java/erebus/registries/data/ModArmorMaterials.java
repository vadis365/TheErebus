package erebus.registries.data;

import com.google.common.collect.Maps;
import erebus.registries.data.tags.ModItemTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.Map;

public class ModArmorMaterials {

    // MARK: Jade Armor Material
    public static final ArmorMaterial JADE = new ArmorMaterial(
            25,
            makeDefense(
                    3, 5, 7, 2, 4
            ),
            5,
            SoundEvents.ARMOR_EQUIP_IRON,
            2.0F,
            0.0F,
            ModItemTags.REPAIRS_JADE_ARMOR,
            ModEquipmentAssets.JADE
    );

    // MARK: Exoskeleton Armor Material
    public static final ArmorMaterial EXOSKELETON = new ArmorMaterial(
            25,
            makeDefense(
                    2, 2, 3, 2, 4
            ),
            5,
            SoundEvents.ARMOR_EQUIP_IRON,
            0.0F,
            0.0F,
            ModItemTags.REPAIRS_EXOSKELETON_ARMOR,
            ModEquipmentAssets.EXOSKELETON
    );

    // MARK: Reinforced Exoskeleton Armor Material
    public static final ArmorMaterial REIN_EXOSKELETON = new ArmorMaterial(
            25,
            makeDefense(
                    3, 6, 8, 3, 4
            ),
            5,
            SoundEvents.ARMOR_EQUIP_IRON,
            3.0F,
            0.0F,
            ModItemTags.REPAIRS_REINFORCED_EXOSKELETON_ARMOR,
            ModEquipmentAssets.REINFORCED_EXOSKELETON
    );

    // MARK: Rhino Armor Material
    public static final ArmorMaterial RHINO = new ArmorMaterial(
            25,
            makeDefense(
                    3, 5, 7, 2, 4
            ),
            5,
            SoundEvents.ARMOR_EQUIP_IRON,
            4.0F,
            0.0F,
            ModItemTags.REPAIRS_RHINO_ARMOR,
            ModEquipmentAssets.RHINO
    );

    // MARK: Bamboo Armor Material
    public static final ArmorMaterial BAMBOO = new ArmorMaterial(
            15,
            makeDefense(
                    2, 3, 4, 2, 4
            ),
            2,
            SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0F,
            0.0F,
            ModItemTags.REPAIRS_BAMBOO_ARMOR,
            ModEquipmentAssets.BAMBOO
    );

    // MARK: Reinforced Compound Goggles

    public static final ArmorMaterial REIN_COMPOUND_GOGGLES = new ArmorMaterial(
            33,
            makeDefense(
                    0, 0, 0, 3, 0
            ),
            3,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            0.0F,
            0.0F,
            ModItemTags.REPAIRS_REINFORCED_COMPOUND_GOGGLES,
            ModEquipmentAssets.REINFORCED_COMPOUND_GOGGLES
    );

    // MARK: Compound Goggles

    public static final ArmorMaterial GOGGLES = new ArmorMaterial(
            33,
            makeDefense(
                    0, 0, 0, 3, 0
            ),
            3,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            0.0F,
            0.0F,
            ModItemTags.REPAIRS_REINFORCED_COMPOUND_GOGGLES,
            ModEquipmentAssets.REINFORCED_COMPOUND_GOGGLES
    );

    // MARK: Mushroom Helm

    public static final ArmorMaterial MUSHROOM_HELM = new ArmorMaterial(
            33,
            makeDefense(
                    0, 0, 0, 3, 0
            ),
            3,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            3.0F,
            0.0F,
            ModItemTags.REPAIRS_MUSHROOM_HELM,
            ModEquipmentAssets.MUSHROOM_HELM
    );

    // MARK: Spider T-Shirt

    public static final ArmorMaterial SPIDER_T_SHIRT = new ArmorMaterial(
            33,
            makeDefense(
                    0, 0, 8, 0, 0
            ),
            3,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            3.0F,
            0.0F,
            ModItemTags.REPAIRS_SPIDER_T_SHIRT,
            ModEquipmentAssets.SPIDER_T_SHIRT
    );

    // MARK: Water Striders

    public static final ArmorMaterial WATER_STRIDERS = new ArmorMaterial(
            33,
            makeDefense(
                    3, 0, 0, 0, 0
            ),
            3,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            3.0F,
            0.0F,
            ModItemTags.REPAIRS_WATER_STRIDERS,
            ModEquipmentAssets.WATER_STRIDERS
    );

    // MARK: Jump Boots

    public static final ArmorMaterial JUMP_BOOTS = new ArmorMaterial(
            33,
            makeDefense(
                    3, 0, 0, 0, 0
            ),
            3,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            3.0F,
            0.0F,
            ModItemTags.REPAIRS_JUMP_BOOTS,
            ModEquipmentAssets.JUMP_BOOTS
    );

    // MARK: Sprint Leggings

    public static final ArmorMaterial SPRINT = new ArmorMaterial(
            33,
            makeDefense(
                    0, 6, 0, 0, 0
            ),
            3,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            3.0F,
            0.0F,
            ModItemTags.REPAIRS_SPRINT_LEGGINGS,
            ModEquipmentAssets.SPRINT_LEGGINGS
    );

    private static Map<ArmorType, Integer> makeDefense(int boots, int legs, int chest, int helm, int body) {
        return Maps.newEnumMap(
                Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body)
        );
    }
}
