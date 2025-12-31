package erebus.item.armour;

import erebus.Erebus;
import erebus.registries.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;

public class CompoundGoggles extends ArmorItem {

	private final ResourceLocation BASIC = Erebus.prefix("textures/models/armor/goggles_layer_1.png");
	private final ResourceLocation REINFORCED = Erebus.prefix("textures/models/armor/rein_goggles_layer_1.png");

	public CompoundGoggles(net.minecraft.world.item.equipment.ArmorMaterial material, ArmorItem.Type type, Properties properties) {
		super(material, type, properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nonnull TooltipContext context, @Nonnull List<Component> list, @Nonnull TooltipFlag flag) {
		list.add(Component.translatable("tooltip.erebus.night_vision").withStyle(ChatFormatting.YELLOW));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean inner) {
		if (stack.getItem() == ModItems.REIN_COMPOUND_GOGGLES.get())
			return REINFORCED;
		else
			return BASIC;
	}

	@Override
	public boolean isValidRepairItem(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.COMPOUND_LENS.get();
	}
}