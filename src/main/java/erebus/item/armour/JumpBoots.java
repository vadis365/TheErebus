package erebus.item.armour;

import erebus.registries.data.ModArmorMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorType;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class JumpBoots extends Item {

	public JumpBoots() {
		super(new Item.Properties().humanoidArmor(ModArmorMaterials.JUMP_BOOTS, ArmorType.BOOTS));
	}

	@Override
	public @NonNull Component getHighlightTip(@NonNull ItemStack item, @NonNull Component displayName) {
		return Component.translatable("tooltip.erebus.jump_boots").withStyle(ChatFormatting.YELLOW);
	}

	@Override
	public void inventoryTick(@NonNull ItemStack itemStack, @NonNull ServerLevel level, @NonNull Entity owner, @Nullable EquipmentSlot slot) {
		if (owner instanceof Player player) {
			ItemStack is = player.getItemBySlot(EquipmentSlot.FEET);
			if (!is.isEmpty() && is.getItem() == this) {
				owner.fallDistance = 0.0F;
			}
		}
	}
}