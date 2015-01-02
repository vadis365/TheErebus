
package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.item.Materials.DATA;

public class RhinoArmor extends ItemArmor {

	public RhinoArmor(int armorType) {
		super(ModMaterials.armorRHINO, 2, armorType);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if (stack.getItem() == ModItems.rhinoExoskeletonLegs)
			return "erebus:textures/models/armor/rhino2.png";
		else
			return "erebus:textures/models/armor/rhino1.png";
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.materials && material.getItemDamage() == DATA.plateExoRhino.ordinal();
	}
}
