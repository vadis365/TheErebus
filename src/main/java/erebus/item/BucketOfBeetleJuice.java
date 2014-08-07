package erebus.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.entity.EntityBotFlyLarva;

public class BucketOfBeetleJuice extends ItemBucketMilk {

	public BucketOfBeetleJuice() {
		setMaxStackSize(1);
		setContainerItem(Items.bucket);
	}

	@Override
	public ItemStack onEaten(ItemStack is, World world, EntityPlayer player) {
		if (!player.capabilities.isCreativeMode)
			is.stackSize--;

		if (!world.isRemote)
			player.curePotionEffects(new ItemStack(Items.milk_bucket));

		if (player.riddenByEntity != null && player.riddenByEntity instanceof EntityBotFlyLarva)
			if (((EntityBotFlyLarva) player.riddenByEntity).getParasiteCount() > 0)
				((EntityBotFlyLarva) player.riddenByEntity).setABitDead();

		return is.stackSize <= 0 ? new ItemStack(Items.bucket) : is;
	}
}