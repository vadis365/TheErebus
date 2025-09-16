package erebus.item.armour;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import erebus.Erebus;
import erebus.registries.ModItems;
import erebus.registries.data.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class SprintLeggings extends ArmorItem { 

	public static final byte MAX_TIER = 9;

	private int armtick;
	private ResourceLocation TEXTURE_1 = Erebus.prefix("textures/models/armor/centipede_layer_1.png");
	private ResourceLocation TEXTURE_2 = Erebus.prefix("textures/models/armor/centipede_layer_2.png");
	private ResourceLocation TEXTURE_3 = Erebus.prefix("textures/models/armor/centipede_layer_3.png");

	public SprintLeggings(Holder<ArmorMaterial> material, ArmorItem.Type type, Properties properties) {
		super(material, type, properties);
	}

	@Override
	public boolean isValidRepairItem(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.BIO_VELOCITY.get();
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nonnull TooltipContext context, @Nonnull List<Component> list, @Nonnull TooltipFlag flag) {
		list.add(Component.translatable("tooltip.erebus.sprint_leggings_tier", " " + (1 + (!stack.has(ModDataComponents.SPRINT_LEGGINGS) ? 0 : stack.get(ModDataComponents.SPRINT_LEGGINGS).tier()))).withStyle(ChatFormatting.YELLOW));
	}

// TODO Hmmm... thonks
	@Nullable
	@Override
	public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean inner) {
		if (stack.is(ModItems.SPRINT_LEGGINGS.get())) {
			if (armtick >= 0 && armtick <= 20)
				return TEXTURE_1;
			if (armtick > 20 && armtick <= 40)
				return TEXTURE_2;
			if (armtick > 40 && armtick <= 60)
				return TEXTURE_3;
		}
		return TEXTURE_1;
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
		if (entity instanceof Player player) {
			if (player.isSprinting() && player.onGround()) {
				int tier = 1 + (!stack.has(ModDataComponents.SPRINT_LEGGINGS) ? 0 : stack.get(ModDataComponents.SPRINT_LEGGINGS).tier());
				float angle = player.getYRot() * ((float) Math.PI / 180F);
				entity.setDeltaMovement(entity.getDeltaMovement().add((double) (-Mth.sin(angle) * tier * 0.0425D), 0.0D, (double) (Mth.cos(angle) * tier * 0.0425D)));
			}
			armtick++;
			if (armtick > 60 || player.isSprinting())
				armtick = 0;
		}
	}

}