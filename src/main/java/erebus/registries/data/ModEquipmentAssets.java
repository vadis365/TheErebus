package erebus.registries.data;

import erebus.Erebus;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

public class ModEquipmentAssets {
    public static ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID = ResourceKey.createRegistryKey(Erebus.prefix("equipment_assets"));
    public static ResourceKey<EquipmentAsset> JADE = register("jade");
    public static ResourceKey<EquipmentAsset> EXOSKELETON = register("exoskeleton");
    public static ResourceKey<EquipmentAsset> REINFORCED_EXOSKELETON = register("reinforced_exoskeleton");
    public static ResourceKey<EquipmentAsset> RHINO = register("rhino");
    public static ResourceKey<EquipmentAsset> BAMBOO = register("bamboo");
    public static ResourceKey<EquipmentAsset> REINFORCED_COMPOUND_GOGGLES = register("reinforced_compound_goggles");
    public static ResourceKey<EquipmentAsset> MUSHROOM_HELM = register("mushroom_helm");
    public static ResourceKey<EquipmentAsset> SPIDER_T_SHIRT = register("spider_t_shirt");
    public static ResourceKey<EquipmentAsset> WATER_STRIDERS = register("water_striders");
    public static ResourceKey<EquipmentAsset> JUMP_BOOTS = register("jump_boots");
    public static ResourceKey<EquipmentAsset> SPRINT_LEGGINGS = register("sprint_leggings");


    private static ResourceKey<EquipmentAsset> register(String name) {
        return ResourceKey.create(ROOT_ID, Erebus.prefix(name));
    }
}
