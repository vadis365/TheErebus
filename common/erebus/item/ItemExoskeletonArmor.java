package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import erebus.ErebusMod;
import erebus.ModItems;

public class ItemExoskeletonArmor extends ItemArmor {

	public ItemExoskeletonArmor(int id, int armorType) {
		super(id, ErebusMod.armorEXOSKELETON, 2, armorType);
	}

	@Override
	public String getArmorTexture(ItemStack is, Entity entity, int slot, int layer) {
		if (is.itemID == ModItems.exoskeletonHelmet.itemID || is.itemID == ModItems.exoskeletonBody.itemID || is.itemID == ModItems.exoskeletonBoots.itemID)
			return "erebus:textures/models/armor/exoskeleton_1.png";
		else
			return "erebus:textures/models/armor/exoskeleton_2.png";
	}
}