package erebus.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.entity.EntityWasp;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemSwordWasp extends ItemSword {

	public ItemSwordWasp() {
		super(ModMaterials.weaponWaspSword);
		setMaxStackSize(1);
		setCreativeTab(ModTabs.gears);
	}

	@Override
	public boolean hitEntity(ItemStack is, EntityLivingBase entity, EntityLivingBase player) {
		is.damageItem(1, player);
		if (!(entity instanceof EntityWasp))
			entity.addPotionEffect(new PotionEffect(Potion.poison.id, 100, 0));
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
	}
}