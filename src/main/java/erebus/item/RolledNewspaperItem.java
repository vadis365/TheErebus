package erebus.item;

import erebus.Erebus;
import erebus.registries.data.ModToolMaterials;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public final class RolledNewspaperItem extends Item {
    public RolledNewspaperItem() {
        super(new Properties() {
            @Override
            public @NonNull Properties enchantable(int value) {
                return this;
            }
        }.sword(ModToolMaterials.ROLLED_NEWSPAPER, 3, -2.4F)
                .delayedComponent(DataComponents.ENCHANTMENTS, registries -> {
                    var enchantments = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                    enchantments.set(registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.BANE_OF_ARTHROPODS), 5);
                    return enchantments.toImmutable();
                })
                .setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("rolled_newspaper"))));
    }

    public static void enchant(ItemStack stack, HolderLookup.Provider registries) {
        if (!stack.isEnchanted()) stack.enchant(registries.lookupOrThrow(Registries.ENCHANTMENT)
                .getOrThrow(Enchantments.BANE_OF_ARTHROPODS), 5);
    }

    @Override
    public void onCraftedPostProcess(@NonNull ItemStack stack, Level level) {
        enchant(stack, level.registryAccess());
    }

    @Override
    public void inventoryTick(@NonNull ItemStack stack, ServerLevel level, @NonNull Entity owner, @Nullable EquipmentSlot slot) {
        enchant(stack, level.registryAccess());
    }
}
