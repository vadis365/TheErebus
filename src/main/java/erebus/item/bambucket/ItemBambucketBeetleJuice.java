package erebus.item.bambucket;

import erebus.ModItems;
import erebus.entity.EntityBotFlyLarva;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBambucketBeetleJuice extends ItemBambucketDrinkable {

	public ItemBambucketBeetleJuice() {
		super("erebus:bambucketOfBeetleJuice");
		setTextureName("erebus:bambucketBeetleJuice");
		setUnlocalizedName("erebus.bambucketBeetleJuice");
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