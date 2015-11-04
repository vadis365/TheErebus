package erebus.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModMaterials;
import erebus.ModTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTShirtSpider extends ItemArmor {

	public ItemTShirtSpider(int armorType) {
		super(ModMaterials.armorREINEXOSKELETON, 2, armorType);
		setCreativeTab(ModTabs.gears);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type) {
		return "erebus:textures/models/armor/spiderTShirt.png";
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