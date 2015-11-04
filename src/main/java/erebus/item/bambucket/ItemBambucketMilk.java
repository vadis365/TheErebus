package erebus.item.bambucket;

import erebus.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBambucketMilk extends ItemBambucketDrinkable {

	public ItemBambucketMilk() {
		super("erebus:bambucketMilk");
		setUnlocalizedName("erebus.bambucketMilk");
		setTextureName("erebus:bambucketMilk");
	}

	@Override
	public ItemStack applyEffects(ItemStack stack, World world, EntityPlayer player) {
		if (!player.capabilities.isCreativeMode)
			stack.stackSize--;

		if (!world.isRemote)
			player.curePotionEffects(new ItemStack(Items.milk_bucket));

		return stack.stackSize <= 0 ? new ItemStack(ModItems.bambucket) : stack;
	}
}