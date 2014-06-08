package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.item.ErebusMaterial.DATA;

public class JadeArmour extends ItemArmor {

	public JadeArmour(int armorType) {
		super(ModMaterials.armorJADE, 2, armorType);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type) {
		if (is.getItem() == ModItems.jadeHelmet || is.getItem() == ModItems.jadeBody || is.getItem() == ModItems.jadeBoots)
			return "erebus:textures/models/armor/jade1.png";
		else
			return "erebus:textures/models/armor/jade2.png";
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.erebusMaterials && material.getItemDamage() == DATA.jade.ordinal();
	}
}