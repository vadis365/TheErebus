package erebus.items;

import erebus.ModMaterials;
import erebus.ModTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSpiderTShirt extends ItemArmor {

	public ItemSpiderTShirt(EntityEquipmentSlot slot) {
		super(ModMaterials.ARMOR_REIN_EXOSKELETON, 2, slot);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, EntityEquipmentSlot slot, String type) {
		return "erebus:textures/models/armor/spider_t_shirt.png";
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return false;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		// un-implemented feature to go here....
	}
}