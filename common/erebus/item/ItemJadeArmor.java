package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import erebus.ErebusMod;
import erebus.ModItems;

public class ItemJadeArmor extends ItemArmor {

	public ItemJadeArmor(int id, int armorType) {
		super(id, ErebusMod.armorJADE, 2, armorType);
	}

	@Override
	public String getArmorTexture(ItemStack is, Entity entity, int slot, int layer) {
		if (is.itemID == ModItems.jadeHelmet.itemID || is.itemID == ModItems.jadeBody.itemID || is.itemID == ModItems.jadeBoots.itemID)
			return "erebus:textures/models/armor/jade_1.png";
		else
			return "erebus:textures/models/armor/jade_2.png";
	}
}