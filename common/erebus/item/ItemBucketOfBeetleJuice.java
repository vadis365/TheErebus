package erebus.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import erebus.entity.EntityBotFlyLarva;

public class ItemBucketOfBeetleJuice extends ItemBucketMilk {

	public ItemBucketOfBeetleJuice(int id) {
		super(id);
		setMaxStackSize(1);
	}

	@Override
	public ItemStack onEaten(ItemStack is, World world, EntityPlayer player) {
		if (!player.capabilities.isCreativeMode)
			is.stackSize--;

		if (!world.isRemote)
			player.curePotionEffects(new ItemStack(Item.bucketMilk));
		
			if (player.riddenByEntity != null && player.riddenByEntity instanceof EntityBotFlyLarva)
				player.riddenByEntity.attackEntityFrom(DamageSource.generic, 8);
			
		return is.stackSize <= 0 ? new ItemStack(Item.bucketEmpty) : is;
	}
}
