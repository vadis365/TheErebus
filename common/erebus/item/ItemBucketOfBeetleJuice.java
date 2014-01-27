package erebus.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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

		return is.stackSize <= 0 ? new ItemStack(Item.bucketEmpty) : is;
	}
}
