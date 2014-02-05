package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModMaterials;

public class ItemReinExoskeletonArmor extends ItemArmor {

	public ItemReinExoskeletonArmor(int id, int armorType) {
		super(id, ModMaterials.armorREINEXOSKELETON, 2, armorType);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		if (stack.itemID == ModItems.reinExoskeletonLegs.itemID)
			return "erebus:textures/models/armor/reinforcedExoskeleton2.png";
		else
			return "erebus:textures/models/armor/reinforcedExoskeleton1.png";
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.itemID == ModItems.erebusMaterials.itemID && material.getItemDamage() == ItemErebusMaterial.dataReinforcedPlateExo;
	}
}