package erebus.item.armour;

import erebus.registries.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
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

import javax.annotation.Nonnull;
import java.util.List;

public class JumpBoots extends ArmorItem {

	public JumpBoots(Holder<ArmorMaterial> material, ArmorItem.Type type, Properties properties) {
		super(material, type, properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nonnull TooltipContext context, @Nonnull List<Component> list, @Nonnull TooltipFlag flag) {
		list.add(Component.translatable("tooltip.erebus.jump_boots").withStyle(ChatFormatting.YELLOW));
	}

	@Override
	public boolean isValidRepairItem(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.ELASTIC_FIBER.get();
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
		if (entity instanceof Player player) {
			ItemStack is = player.getItemBySlot(EquipmentSlot.FEET);
			if (!is.isEmpty() && is.getItem() == this) {
				entity.fallDistance = 0.0F;
			}
		}
	}
}