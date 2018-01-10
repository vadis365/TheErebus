package erebus.block.bamboo;

import java.util.Random;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.items.ItemMaterials;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBambooPipeExtractActive extends BlockBambooPipeExtract {

	public BlockBambooPipeExtractActive() {
		super();
		setDefaultState(this.getBlockState().getBaseState().withProperty(CONNECTED_DOWN, Boolean.FALSE).withProperty(CONNECTED_EAST, Boolean.FALSE).withProperty(CONNECTED_NORTH, Boolean.FALSE).withProperty(CONNECTED_SOUTH, Boolean.FALSE).withProperty(CONNECTED_UP, Boolean.FALSE).withProperty(CONNECTED_WEST, Boolean.FALSE));
		setHardness(1.5F);
		setSoundType(SoundType.WOOD);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.BAMBOO_PIPE_EXTRACT);
	}

	@Override
	public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(ModBlocks.BAMBOO_PIPE_EXTRACT));
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		} else {
			ItemStack stack = player.getHeldItem(hand);
			if (!stack.isEmpty() && stack.getItem() == ModItems.MATERIALS && stack.getItemDamage() == ItemMaterials.EnumErebusMaterialsType.BAMBOO_PIPE_WRENCH.ordinal()) {
				if (!player.isSneaking()) {
					state = state.cycleProperty(FACING);
					state.cycleProperty(FACING);
					world.setBlockState(pos, state, 3);
					return true;
				}
				else {
					breakBlock(world, pos, state);
					dropBlockAsItem(world, pos, state, 0);
					world.setBlockToAir(pos);
					return true;
				}
			} else {
				IBlockState activeState = ModBlocks.BAMBOO_PIPE_EXTRACT.getDefaultState().withProperty(BlockBambooPipeExtract.FACING, state.getValue(FACING));
				world.setBlockState(pos, activeState, 3);
				world.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, 0.5F);
				return true;
			}
		}
	}
}
