package erebus.item;

import erebus.ModItems;
import erebus.ModTabs;
import erebus.item.ItemMaterials.DATA;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemCompoundGoggles extends ItemArmor {

	public ItemCompoundGoggles(ArmorMaterial enumarmormaterial, int j, int k) {
		super(enumarmormaterial, j, k);
		setCreativeTab(ModTabs.gears);
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
		return material.getItem() == ModItems.materials && material.getItemDamage() == DATA.compoundLens.ordinal();
	}
}