package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;

public class ItemBucketOfHoney extends ItemBucket {

	public ItemBucketOfHoney(Block full) {
		super(full);
		setMaxStackSize(1);
	}

	@Override
	public boolean hasContainerItem() {
		return true;
	}

	@Override
	public Item getContainerItem() {
		return Item.bucketEmpty;
	}

	@Override
	public ItemStack getContainerItemStack(ItemStack stack) {
		return new ItemStack(Item.bucketEmpty);
	}
}