package erebus.item.armour;

import erebus.Erebus;
import erebus.registries.data.ModArmorMaterials;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorType;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public final class MushroomHelmetItem extends Item {
    public MushroomHelmetItem() {
        super(new Properties().humanoidArmor(ModArmorMaterials.MUSHROOM_HELM, ArmorType.HELMET).durability(40)
                .setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("mushroom_helmet"))));
    }

    @Override
    public void inventoryTick(@NonNull ItemStack stack, @NonNull ServerLevel level, @NonNull Entity entity, @Nullable EquipmentSlot slot) {
        if (slot == EquipmentSlot.HEAD && entity instanceof Player player && player.getFoodData().needsFood()
                && stack.getDamageValue() < stack.getMaxDamage()) {
            player.getFoodData().eat(1, 0.2F);
            stack.hurtAndBreak(1, player, EquipmentSlot.HEAD);
        }
    }
}
