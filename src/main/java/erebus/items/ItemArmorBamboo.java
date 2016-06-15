package erebus.items;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.items.ItemMaterials.EnumType;

public class ItemArmorBamboo extends ItemArmor {

	public ItemArmorBamboo(EntityEquipmentSlot slot) {
		super(ModMaterials.ARMOR_BAMBOO, 2, slot);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, EntityEquipmentSlot slot, String type) {
		if (is.getItem() == ModItems.BAMBOO_LEGGINGS)
			return "erebus:textures/models/armor/bamboo_2.png";
		else
			return "erebus:textures/models/armor/bamboo_1.png";
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.MATERIALS && material.getItemDamage() == EnumType.BAMBOO.ordinal();
	}
}