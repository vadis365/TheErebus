package erebus.items;

import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.entity.EntityWasp;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;

public class ItemWaspSword extends ItemSword {

	public ItemWaspSword() {
		super(ModMaterials.WEAPON_WASP_SWORD);
		setMaxStackSize(1);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	public boolean hitEntity(ItemStack is, EntityLivingBase entity, EntityLivingBase player) {
		is.damageItem(1, player);
		if (!(entity instanceof EntityWasp))
			entity.addPotionEffect(new PotionEffect(new PotionEffect(MobEffects.POISON, 100, 0)));
		return true;
	}
}