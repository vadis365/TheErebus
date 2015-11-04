package erebus.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.item.ItemMaterials.DATA;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemArmorExoskeleton extends ItemArmor {

	public ItemArmorExoskeleton(int armorType) {
		super(ModMaterials.armorEXOSKELETON, 2, armorType);
		setCreativeTab(ModTabs.gears);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if (stack.getItem() == ModItems.exoskeletonLegs)
			return "erebus:textures/models/armor/exoskeleton2.png";
		else
			return "erebus:textures/models/armor/exoskeleton1.png";
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.materials && material.getItemDamage() == DATA.plateExo.ordinal();
	}
}