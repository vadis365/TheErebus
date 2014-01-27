package erebus.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import erebus.ErebusMod;
import erebus.entity.EntityWaspDagger;

public class ItemWaspDagger extends ItemSword {
	public ItemWaspDagger(int id) {
		super(id, ErebusMod.weaponWaspDagger);
		maxStackSize = 16;
		setTextureName("paper");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		if (!player.capabilities.isCreativeMode)
			is.stackSize--;
		world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!world.isRemote)
			world.spawnEntityInWorld(new EntityWaspDagger(world, player));
		return is;
	}

	@Override
	public boolean hitEntity(ItemStack is, EntityLivingBase entity, EntityLivingBase player) {
		is.damageItem(2, player);
		return true;
	}
}