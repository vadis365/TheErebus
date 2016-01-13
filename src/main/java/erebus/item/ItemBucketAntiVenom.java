package erebus.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ModTabs;

public class ItemBucketAntiVenom extends ItemBucketMilk {

	public ItemBucketAntiVenom() {
		setMaxStackSize(1);
		setContainerItem(Items.bucket);
		setCreativeTab(ModTabs.specials);
	}

	@Override
	@SuppressWarnings("unchecked")
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		if (!player.capabilities.isCreativeMode) {
			stack.stackSize--;
			if(!player.getEntityData().hasKey("antivenomDuration") || player.getEntityData().getInteger("antivenomDuration") < 180)
				player.getEntityData().setInteger("antivenomDuration", 180);
			if (stack.stackSize <= 0)
				return new ItemStack(Items.bucket);
			else
				player.inventory.addItemStackToInventory(new ItemStack(Items.bucket));
		}
		return stack;
	}
}