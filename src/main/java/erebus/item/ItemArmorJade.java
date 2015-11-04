package erebus.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.core.helper.Utils;
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
	public boolean getIsRepairable(ItemStack stack, ItemStack material) {
		return Utils.isItemOre(material, "gemJade");
	}
}