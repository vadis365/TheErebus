package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;

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
		return Items.bucket;
	}
}