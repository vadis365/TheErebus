package erebus.item.armour;

import erebus.Erebus;
import erebus.registries.data.ModArmorMaterials;
import erebus.registries.data.ModEquipmentAssets;
import erebus.registries.data.tags.ModItemTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;

public final class GliderItem extends Item {
    private final boolean powered;

    public GliderItem(boolean powered) {
        super(new Properties().humanoidArmor(ModArmorMaterials.REIN_EXOSKELETON, ArmorType.CHESTPLATE)
                .component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.CHEST)
                        .setEquipSound(ModArmorMaterials.REIN_EXOSKELETON.equipSound())
                        .setAsset(powered ? ModEquipmentAssets.POWERED_GLIDER : ModEquipmentAssets.GLIDER).build())
                .repairable(ModItemTags.REPAIRS_GLIDER)
                .setId(ResourceKey.create(Registries.ITEM, Erebus.prefix(powered ? "glider_chestplate_powered" : "glider_chestplate"))));
        this.powered = powered;
    }

    public boolean powered() {
        return powered;
    }
}
