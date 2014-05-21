package erebus.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;

public class ItemBucketOfHoney extends ItemBucket {

	public ItemBucketOfHoney(int id, int full) {
		super(id, full);
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