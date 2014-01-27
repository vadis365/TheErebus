package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import erebus.ErebusMod;
import erebus.ModItems;

public class ItemReinExoskeletonArmor extends ItemArmor {

	public ItemReinExoskeletonArmor(int id, int armorType) {
		super(id, ErebusMod.armorREINEXOSKELETON, 2, armorType);
	}

	@Override
	public String getArmorTexture(ItemStack is, Entity entity, int slot, int layer) {
		if (is.itemID == ModItems.reinExoskeletonHelmet.itemID || is.itemID == ModItems.reinExoskeletonBody.itemID || is.itemID == ModItems.reinExoskeletonBoots.itemID)
			return "erebus:textures/models/armor/reinforcedExoskeleton_1.png";
		else
			return "erebus:textures/models/armor/reinforcedExoskeleton_2.png";
	}
}