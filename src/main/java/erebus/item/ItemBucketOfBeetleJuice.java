package erebus.item;

import erebus.ModTabs;
import erebus.entity.EntityBotFlyLarva;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBucketOfBeetleJuice extends ItemBucketMilk {

	public ItemBucketOfBeetleJuice() {
		setMaxStackSize(1);
		setContainerItem(Items.bucket);
		setCreativeTab(ModTabs.specials);
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