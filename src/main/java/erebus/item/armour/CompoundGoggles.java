package erebus.item.armour;

import erebus.Erebus;
import erebus.registries.data.ModArmorMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import org.jspecify.annotations.NonNull;

public class CompoundGoggles extends Item {
    public CompoundGoggles(ArmorMaterial armorMaterial) {
        super(new Item.Properties().humanoidArmor(armorMaterial, ArmorType.HELMET).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix(armorMaterial == ModArmorMaterials.REIN_COMPOUND_GOGGLES ? "rein_compound_goggles" : "compound_goggles"))));
    }

	@Override
	public @NonNull Component getHighlightTip(@NonNull ItemStack item, @NonNull Component displayName) {
		return Component.translatable("tooltip.erebus.night_vision").withStyle(ChatFormatting.YELLOW);
	}
}
