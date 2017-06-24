package erebus.items;

import erebus.ModItems;
import erebus.ModTabs;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCompoundGoggles extends ItemArmor {

	public ItemCompoundGoggles(ArmorMaterial material, EntityEquipmentSlot slot) {
		super(material, 2, slot);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, EntityEquipmentSlot slot, String type) {
		if (is.getItem() == ModItems.COMPOUND_GOGGLES)
			return "erebus:textures/models/armor/goggles_1.png";
		if (is.getItem() == ModItems.REIN_COMPOUND_GOGGLES)
			return "erebus:textures/models/armor/rein_goggles.png";
		else
			return null;
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.MATERIALS && material.getItemDamage() == EnumErebusMaterialsType.COMPOUND_LENS.ordinal();
	}
}