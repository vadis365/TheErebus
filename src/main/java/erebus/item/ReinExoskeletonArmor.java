package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.item.ErebusMaterial.DATA;

public class ReinExoskeletonArmor extends ItemArmor
{

	public ReinExoskeletonArmor(int armorType)
	{
		super(ModMaterials.armorREINEXOSKELETON, 2, armorType);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (stack.getItem() == ModItems.reinExoskeletonLegs)
		{
			return "erebus:textures/models/armor/reinforcedExoskeleton2.png";
		} else
		{
			return "erebus:textures/models/armor/reinforcedExoskeleton1.png";
		}
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material)
	{
		return material.getItem() == ModItems.erebusMaterials && material.getItemDamage() == DATA.reinforcedPlateExo.ordinal();
	}
}