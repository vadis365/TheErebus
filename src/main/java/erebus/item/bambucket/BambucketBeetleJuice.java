package erebus.item.bambucket;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.entity.EntityBotFlyLarva;

public class BambucketBeetleJuice extends BambucketDrinkable {

	public BambucketBeetleJuice() {
		super("erebus:bambucketOfBeetleJuice");
		setUnlocalizedName("erebus.bambucketBeetleJuice");
		setTextureName("erebus:bambucketBeetleJuice");
	}

	@Override
	public ItemStack applyEffects(ItemStack stack, World world, EntityPlayer player) {
		if (!player.capabilities.isCreativeMode) {
			stack.stackSize--;
			player.inventory.addItemStackToInventory(new ItemStack(ModItems.bambucket));
		}
		if (!world.isRemote)
			player.curePotionEffects(new ItemStack(Items.milk_bucket));
		if (player.riddenByEntity != null && player.riddenByEntity instanceof EntityBotFlyLarva)
			if (((EntityBotFlyLarva) player.riddenByEntity).getParasiteCount() > 0)
				((EntityBotFlyLarva) player.riddenByEntity).setABitDead();

		return stack;
	}
}