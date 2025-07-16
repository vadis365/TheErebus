package erebus.inventory.server;

import erebus.entity.BlackAnt;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class BlackAntSimpleContainer extends SimpleContainer {

	public BlackAntSimpleContainer(int size) {
		super(size);
	}

	public BlackAntSimpleContainer(ItemStack... items) {
		super(items);
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		if (slot == BlackAnt.TOOL_SLOT)
			return stack.getItem() == Items.SHEARS || stack.getItem() instanceof BucketItem || stack.getItem() instanceof HoeItem || stack.getItem() == Items.BONE;
		return true;
	}

}
