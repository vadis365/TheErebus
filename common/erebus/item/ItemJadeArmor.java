package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.item.ItemErebusMaterial.DATA;

public class ItemJadeArmor extends ItemArmor {

	public ItemJadeArmor(int id, int armorType) {
		super(id, ModMaterials.armorJADE, 2, armorType);
	}

	@Deprecated
	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, int layer) {
		if (is.itemID == ModItems.jadeHelmet.itemID || is.itemID == ModItems.jadeBody.itemID || is.itemID == ModItems.jadeBoots.itemID)
			return "erebus:textures/models/armor/jade1.png";
		else
			return "erebus:textures/models/armor/jade2.png";
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.itemID == ModItems.erebusMaterials.itemID && material.getItemDamage() == DATA.jade.ordinal();
	}
}