package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import erebus.ModItems;

public class ItemCompoundGoggles extends ItemArmor {

	public ItemCompoundGoggles(int i, EnumArmorMaterial enumarmormaterial, int j, int k) {
		super(i, enumarmormaterial, j, k);
	}

	@Override
	public String getArmorTexture(ItemStack is, Entity entity, int slot, int layer) {
		if (is.itemID == ModItems.compoundGoggles.itemID)
			return "erebus:textures/models/armor/goggles_1.png";
		if (is.itemID == ModItems.reinCompoundGoggles.itemID)
			return "erebus:textures/models/armor/reingoggles_1.png";
		else
			return null;
	}
}