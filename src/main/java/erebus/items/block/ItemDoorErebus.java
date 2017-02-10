package erebus.items.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoor;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDoorErebus extends ItemBlock {

	public ItemDoorErebus(Block block) {
		super(block);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (facing != EnumFacing.UP)
			return EnumActionResult.FAIL;
		else {
			IBlockState iblockstate = world.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (!block.isReplaceable(world, pos))
				pos = pos.offset(facing);

			if (player.canPlayerEdit(pos, facing, player.getHeldItem(hand)) && this.block.canPlaceBlockAt(world, pos)) {
				EnumFacing enumfacing = EnumFacing.fromAngle(player.rotationYaw);
				int i = enumfacing.getFrontOffsetX();
				int j = enumfacing.getFrontOffsetZ();
				boolean flag = i < 0 && hitZ < 0.5F || i > 0 && hitZ > 0.5F || j < 0 && hitX > 0.5F || j > 0 && hitX < 0.5F;
				ItemDoor.placeDoor(world, pos, enumfacing, this.block, flag);
				SoundType soundtype = this.block.getSoundType();
				world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
				player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount() - 1);
				return EnumActionResult.SUCCESS;
			} else
				return EnumActionResult.FAIL;
		}
	}
}