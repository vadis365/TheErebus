package erebus.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import erebus.ModMaterials;
import erebus.entity.EntityWasp;

public class ItemWaspSword extends ItemSword {

	public ItemWaspSword() {
		super(ModMaterials.weaponWaspSword);
		maxStackSize = 1;
	}

	@Override
	public boolean hitEntity(ItemStack is, EntityLivingBase entity, EntityLivingBase player) {
		is.damageItem(1, player);
		if (!(entity instanceof EntityWasp))
			entity.addPotionEffect(new PotionEffect(Potion.poison.id, 100, 0));
		return true;
	}

}
