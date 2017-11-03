package erebus.items;

import erebus.ModBlocks;
import erebus.ModSounds;
import erebus.ModTabs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSprayCan extends Item {

	public ItemSprayCan() {
		setMaxStackSize(9);
		setCreativeTab(ModTabs.GEAR);
	}

	private SoundEvent getSprayCanSound() {
		return ModSounds.SPRAY_CAN_SOUND;
	}

	@Override
	 public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (facing.getIndex() != 1)
			return EnumActionResult.FAIL;
		else if (player.canPlayerEdit(pos, EnumFacing.UP, stack) && player.canPlayerEdit(pos.up(), EnumFacing.UP, stack)) {
			IBlockState state = world.getBlockState(pos);
			if (state.getBlock() != null && state.isSideSolid(world, pos, EnumFacing.UP) && state.getBlock() != ModBlocks.INSECT_REPELLENT) {
				world.setBlock(pos.up(), ModBlocks.INSECT_REPELLENT);
				stack.shrink(1);
				world.playSound(null, pos.up(), getSprayCanSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
				return EnumActionResult.SUCCESS;
			} else
				return EnumActionResult.FAIL;
		} else
			return EnumActionResult.FAIL;
	}
}