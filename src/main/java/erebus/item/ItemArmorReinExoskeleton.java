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

public class ItemArmorReinExoskeleton extends ItemArmor {

	public ItemArmorReinExoskeleton(int armorType) {
		super(ModMaterials.armorREINEXOSKELETON, 2, armorType);
		setCreativeTab(ModTabs.gears);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if (stack.getItem() == ModItems.reinExoskeletonLegs)
			return "erebus:textures/models/armor/reinforcedExoskeleton2.png";
		else
			return "erebus:textures/models/armor/reinforcedExoskeleton1.png";
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.materials && material.getItemDamage() == DATA.reinforcedPlateExo.ordinal();
	}
}