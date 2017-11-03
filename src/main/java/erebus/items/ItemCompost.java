package erebus.items;

import erebus.ModTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCompost extends Item {

	public ItemCompost() {
		setCreativeTab(ModTabs.ITEMS);
	}

	@Override
	 public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (ItemDye.applyBonemeal(stack, world, pos, player, hand)) {
			if (!world.isRemote)
				world.playEvent(2005, pos, 0);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
}