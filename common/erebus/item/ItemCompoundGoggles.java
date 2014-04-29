package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import erebus.ModItems;
import erebus.item.ItemErebusMaterial.DATA;

public class ItemCompoundGoggles extends ItemArmor {

	public ItemCompoundGoggles(int i, EnumArmorMaterial enumarmormaterial, int j, int k) {
		super(i, enumarmormaterial, j, k);
	}

	@Deprecated
	@Override
	public String getArmorTexture(ItemStack is, Entity entity, int slot, int layer) {
		if (is.itemID == ModItems.compoundGoggles.itemID)
			return "erebus:textures/models/armor/goggles1.png";
		if (is.itemID == ModItems.reinCompoundGoggles.itemID)
			return "erebus:textures/models/armor/reinGoggles.png";
		else
			return null;
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.itemID == ModItems.erebusMaterials.itemID && material.getItemDamage() == DATA.compoundLens.ordinal();
	}
}