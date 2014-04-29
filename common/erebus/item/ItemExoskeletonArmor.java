package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.item.ItemErebusMaterial.DATA;

public class ItemExoskeletonArmor extends ItemArmor {

	public ItemExoskeletonArmor(int id, int armorType) {
		super(id, ModMaterials.armorEXOSKELETON, 2, armorType);
	}

	@Deprecated
	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		if (stack.itemID == ModItems.exoskeletonLegs.itemID)
			return "erebus:textures/models/armor/exoskeleton2.png";
		else
			return "erebus:textures/models/armor/exoskeleton1.png";
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.itemID == ModItems.erebusMaterials.itemID && material.getItemDamage() == DATA.plateExo.ordinal();
	}
}