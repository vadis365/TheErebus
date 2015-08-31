package erebus.item;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.item.ItemMaterials.DATA;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemArmorJade extends ItemArmor {

	public ItemArmorJade(int armorType) {
		super(ModMaterials.armorJADE, 2, armorType);
		setCreativeTab(ModTabs.gears);
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
		return material.getItem() == ModItems.materials && material.getItemDamage() == DATA.jade.ordinal();
	}
}