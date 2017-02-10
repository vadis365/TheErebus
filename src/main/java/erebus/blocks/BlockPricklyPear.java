package erebus.blocks;

import com.sun.istack.internal.Nullable;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.items.ItemErebusFood.EnumFoodType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class BlockPricklyPear extends Block implements IPlantable {

	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 11);
	protected static final AxisAlignedBB PRICKLY_PEAR_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D);

	public BlockPricklyPear() {
		super(Material.CACTUS);
		setDefaultState(blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
		setCreativeTab(ModTabs.BLOCKS);
		setTickRandomly(true);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return PRICKLY_PEAR_AABB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess world, BlockPos pos) {
		return PRICKLY_PEAR_AABB;
	}

	@Nullable
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return getMetaFromState(state) <= 10 ? Item.getItemFromBlock(this) : ModItems.EREBUS_FOOD;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state) <= 10 ? 0 : EnumFoodType.PRICKLY_PEAR_RAW.ordinal();
	}

	@Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state,
			Random rand) {
		if (world.getBlockState(pos.down()).getBlock() == ModBlocks.PRICKLY_PEAR || checkForDrop(world, pos, state)) {
			if (world.isAirBlock(pos.up())) {
				int growthHeight;

				for (growthHeight = 1; world.getBlockState(pos.down(growthHeight)).getBlock() == this; ++growthHeight);

				if (growthHeight < 3) {
					int stage = state.getValue(AGE);

					if (stage == 10) {
						world.setBlockState(pos.up(), getDefaultState());
						if (world.getBlockState(pos).getBlock() == this && world.getBlockState(pos.down()).getBlock() == this)
							world.setBlockState(pos.up(), state.withProperty(AGE, 11), 4);
						else
							world.setBlockState(pos, state.withProperty(AGE, 0), 4);
					} else if (stage < 10) {
						world.setBlockState(pos, state.withProperty(AGE, stage + 1), 4);
					}
				}
			}
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.down());
		Block block = state.getBlock();
		if (block.canSustainPlant(state, world, pos.down(), EnumFacing.UP, this))
			return true;
		if (block == this)
			return true;
		else if (block != Blocks.GRASS && block != Blocks.DIRT && block != Blocks.SAND)
			return false;

		return false;
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos from) {
		checkForDrop(world, pos, state);
	}

	protected final boolean checkForDrop(World world, BlockPos pos, IBlockState state) {
		if (canBlockStay(world, pos))
			return true;
		else {
			dropBlockAsItem(world, pos, state, 0);
			world.setBlockToAir(pos);
			return false;
		}
	}

	public boolean canBlockStay(World world, BlockPos pos) {
		return canPlaceBlockAt(world, pos);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(AGE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(AGE);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Desert;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return getDefaultState();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, AGE);
	}

	@Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this));
    }

}
