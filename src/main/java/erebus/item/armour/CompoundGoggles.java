package erebus.item.armour;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import org.jspecify.annotations.NonNull;

public class CompoundGoggles extends Item {
	public CompoundGoggles(ArmorMaterial armorMaterial) {
		super(new Item.Properties().humanoidArmor(armorMaterial, ArmorType.HELMET));
	}

	@Override
	public @NonNull Component getHighlightTip(@NonNull ItemStack item, @NonNull Component displayName) {
		return Component.translatable("tooltip.erebus.night_vision").withStyle(ChatFormatting.YELLOW);
	}
}