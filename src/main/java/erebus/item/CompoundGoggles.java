package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import erebus.ModItems;
import erebus.item.ErebusMaterial.DATA;

public class CompoundGoggles extends ItemArmor {

	public CompoundGoggles(ArmorMaterial enumarmormaterial, int j, int k) {
		super(enumarmormaterial, j, k);
	}

	@Override
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type) {
		if (is.getItem() == ModItems.compoundGoggles)
			return "erebus:textures/models/armor/goggles1.png";
		if (is.getItem() == ModItems.reinCompoundGoggles)
			return "erebus:textures/models/armor/reinGoggles.png";
		else
			return null;
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.erebusMaterials && material.getItemDamage() == DATA.compoundLens.ordinal();
	}
}