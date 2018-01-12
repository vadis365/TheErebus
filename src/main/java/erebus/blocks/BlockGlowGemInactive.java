package erebus.blocks;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGlowGemInactive extends BlockGlowGemActive {

	public BlockGlowGemInactive() {
		super();
		setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumGemDirection.DOWN_NORTH));
		setLightLevel(0F);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		} else {
			IBlockState activeState = ModBlocks.GLOW_GEM_ACTIVE.getDefaultState().withProperty(BlockGlowGemActive.TYPE, state.getValue(TYPE));
			world.setBlockState(pos, activeState, 3);
			world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, 0.6F);
			return true;
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.GLOW_GEM_ACTIVE.getDefaultState().withProperty(BlockGlowGemActive.TYPE, BlockGlowGemActive.EnumGemDirection.DOWN_NORTH).getBlock());
	}

}