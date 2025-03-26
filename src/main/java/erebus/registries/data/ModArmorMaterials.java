package erebus.registries.data;

import erebus.Erebus;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class ModArmorMaterials {

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(BuiltInRegistries.ARMOR_MATERIAL, Erebus.MODID);

    // MARK: Jade Armor Material
    public static final Holder<ArmorMaterial> JADE_ARMOR_MATERIAL = ARMOR_MATERIALS.register(
            "jade",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.LEGGINGS, 5);
                        map.put(ArmorItem.Type.CHESTPLATE, 7);
                        map.put(ArmorItem.Type.HELMET, 2);
                        // map.put(ArmorItem.Type.BODY, 4); Horse Armor
                    }),
                    15,
                    SoundEvents.ARMOR_EQUIP_IRON,
                    () -> Ingredient.of(Tags.Items.INGOTS_IRON),
                    List.of(
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "jade")
                            )
                    ),
                    2,
                    0
            )
    );

    // MARK: Exoskeleton Armor Material
    public static final Holder<ArmorMaterial> EXOSKELETON_ARMOR_MATERIAL = ARMOR_MATERIALS.register(
            "exoskeleton",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 2);
                        map.put(ArmorItem.Type.LEGGINGS, 2);
                        map.put(ArmorItem.Type.CHESTPLATE, 3);
                        map.put(ArmorItem.Type.HELMET, 2);
                        // map.put(ArmorItem.Type.BODY, 4); Horse Armor
                    }),
                    15,
                    SoundEvents.ARMOR_EQUIP_CHAIN,
                    () -> Ingredient.of(Tags.Items.INGOTS_IRON),
                    List.of(
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "exoskeleton")
                            )
                    ),
                    0,
                    0
            )
    );

    // MARK: Reinforced Exoskeleton Armor Material
    public static final Holder<ArmorMaterial> REIN_EXOSKELETON_ARMOR_MATERIAL = ARMOR_MATERIALS.register(
            "rein_exoskeleton",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.LEGGINGS, 6);
                        map.put(ArmorItem.Type.CHESTPLATE, 8);
                        map.put(ArmorItem.Type.HELMET, 3);
                        // map.put(ArmorItem.Type.BODY, 4); Horse Armor
                    }),
                    33,
                    SoundEvents.ARMOR_EQUIP_DIAMOND,
                    () -> Ingredient.of(Tags.Items.INGOTS_IRON),
                    List.of(
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "rein_exoskeleton")
                            )
                    ),
                    3,
                    0
            )
    );

    // MARK: Rhino Armor Material
    public static final Holder<ArmorMaterial> RHINO_ARMOR_MATERIAL = ARMOR_MATERIALS.register(
            "rhino",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.LEGGINGS, 5);
                        map.put(ArmorItem.Type.CHESTPLATE, 7);
                        map.put(ArmorItem.Type.HELMET, 2);
                        // map.put(ArmorItem.Type.BODY, 4); Horse Armor
                    }),
                    10,
                    SoundEvents.ARMOR_EQUIP_DIAMOND,
                    () -> Ingredient.of(Tags.Items.INGOTS_IRON),
                    List.of(
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "rhino")
                            )
                    ),
                    4,
                    0
            )
    );

    // MARK: Bamboo Armor Material
    public static final Holder<ArmorMaterial> BAMBOO_ARMOR_MATERIAL = ARMOR_MATERIALS.register(
            "bamboo",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 2);
                        map.put(ArmorItem.Type.LEGGINGS, 3);
                        map.put(ArmorItem.Type.CHESTPLATE, 4);
                        map.put(ArmorItem.Type.HELMET, 2);
                        // map.put(ArmorItem.Type.BODY, 4); Horse Armor
                    }),
                    15,
                    SoundEvents.ARMOR_EQUIP_GENERIC,
                    () -> Ingredient.of(Tags.Items.INGOTS_IRON),
                    List.of(
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "bamboo")
                            )
                    ),
                    2,
                    0
            )
    );

    // MARK: Reinforced Compound Goggles

    public static final Holder<ArmorMaterial> REIN_COMPOUND_GOGGLES_ARMOR_MATERIAL = ARMOR_MATERIALS.register(
            "rein_goggles",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.HELMET, 3);
                    }),
                    33,
                    SoundEvents.ARMOR_EQUIP_DIAMOND,
                    () -> Ingredient.of(Tags.Items.INGOTS_IRON),
                    List.of(
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "rein_goggles")
                            )
                    ),
                    3,
                    0
            )
    );

    // MARK: Compound Goggles

    public static final Holder<ArmorMaterial> GOGGLES_ARMOR_MATERIAL = ARMOR_MATERIALS.register(
            "goggles",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.HELMET, 3);
                        // map.put(ArmorItem.Type.BODY, 4); Horse Armor
                    }),
                    33,
                    SoundEvents.ARMOR_EQUIP_DIAMOND,
                    () -> Ingredient.of(Tags.Items.INGOTS_IRON),
                    List.of(
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "goggles")
                            )
                    ),
                    3,
                    0
            )
    );

    // MARK: Mushroom Helm

    public static final Holder<ArmorMaterial> MUSHROOM_HELM_ARMOR_MATERIAL = ARMOR_MATERIALS.register(
            "mushroom_helm",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.HELMET, 3);
                    }),
                    33,
                    SoundEvents.ARMOR_EQUIP_DIAMOND,
                    () -> Ingredient.of(Tags.Items.INGOTS_IRON),
                    List.of(
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "mushroom_helm")
                            )
                    ),
                    3,
                    0
            )
    );

    // MARK: Spider T-Shirt

    public static final Holder<ArmorMaterial> SPIDER_T_SHIRT_ARMOR_MATERIAL = ARMOR_MATERIALS.register(
            "spider_t_shirt",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.CHESTPLATE, 8);
                    }),
                    33,
                    SoundEvents.ARMOR_EQUIP_DIAMOND,
                    () -> Ingredient.of(Tags.Items.INGOTS_IRON),
                    List.of(
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "spider_t_shirt")
                            )
                    ),
                    3,
                    0
            )
    );

    // MARK: Water Striders

    public static final Holder<ArmorMaterial> WATER_STRIDERS_ARMOR_MATERIAL = ARMOR_MATERIALS.register(
            "water_striders",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 3);
                    }),
                    33,
                    SoundEvents.ARMOR_EQUIP_DIAMOND,
                    () -> Ingredient.of(Tags.Items.INGOTS_IRON),
                    List.of(
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "striders")
                            )
                    ),
                    3,
                    0
            )
    );

    // MARK: Jump Boots

    public static final Holder<ArmorMaterial> JUMP_BOOTS_ARMOR_MATERIAL = ARMOR_MATERIALS.register(
            "jump_boots",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 3);
                    }),
                    33,
                    SoundEvents.ARMOR_EQUIP_DIAMOND,
                    () -> Ingredient.of(Tags.Items.INGOTS_IRON),
                    List.of(
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "jump_boots")
                            )
                    ),
                    3,
                    0
            )
    );

    // MARK: Sprint Leggings

    public static final Holder<ArmorMaterial> CENTIPEDE_ARMOR_MATERIAL = ARMOR_MATERIALS.register(
            "centipede",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.LEGGINGS, 6);
                    }),
                    33,
                    SoundEvents.ARMOR_EQUIP_DIAMOND,
                    () -> Ingredient.of(Tags.Items.INGOTS_IRON),
                    List.of(
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "centipede")
                            )
                    ),
                    3,
                    0
            )
    );

    public static void register(IEventBus bus) {
        ARMOR_MATERIALS.register(bus);
    }
}
