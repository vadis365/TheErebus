package erebus.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import erebus.ModMaterials;

public class WarHammer extends ItemSword {

	public WarHammer() {
		super(ModMaterials.weaponWarHammer);
		maxStackSize = 1;
	}

	@Override
	public boolean hitEntity(ItemStack is, EntityLivingBase entity, EntityLivingBase player) {
		is.damageItem(1, player);
		return true;
	}

}
