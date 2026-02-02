package erebus.item.armour;

import erebus.Erebus;
import erebus.registries.data.ModArmorMaterials;
import erebus.registries.data.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorType;
import org.jspecify.annotations.NonNull;

public class SprintLeggings extends Item {
	private int armTick;

	public SprintLeggings() {
		super(new Item.Properties().humanoidArmor(ModArmorMaterials.SPRINT, ArmorType.LEGGINGS).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("sprint_leggings"))));
	}

	@Override
	public @NonNull Component getHighlightTip(ItemStack stack, @NonNull Component displayName) {
		return Component.translatable("tooltip.erebus.sprint_leggings_tier", " " + (1 + (!stack.has(ModDataComponents.SPRINT_LEGGINGS) ? 0 : stack.get(ModDataComponents.SPRINT_LEGGINGS).tier()))).withStyle(ChatFormatting.YELLOW);
	}

	@Override
	public void inventoryTick(@NonNull ItemStack stack, @NonNull ServerLevel level, @NonNull Entity owner, @org.jspecify.annotations.Nullable EquipmentSlot slot) {
		if (owner instanceof Player player) {
			if (player.isSprinting() && player.onGround()) {
				int tier = 1 + (!stack.has(ModDataComponents.SPRINT_LEGGINGS) ? 0 : stack.get(ModDataComponents.SPRINT_LEGGINGS).tier());
				float angle = player.getYRot() * ((float) Math.PI / 180F);
				owner.setDeltaMovement(owner.getDeltaMovement().add(-Mth.sin(angle) * tier * 0.0425D, 0.0D, Mth.cos(angle) * tier * 0.0425D));
			}
			armTick++;
			if (armTick > 60 || player.isSprinting())
				armTick = 0;
		}
	}
}
