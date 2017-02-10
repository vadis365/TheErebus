package erebus.blocks;

import com.sun.istack.internal.Nullable;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.items.ItemMaterials.EnumType;
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

import java.util.ArrayList;
import java.util.Random;

public class BlockBamboo extends Block implements IPlantable {

	private static final AxisAlignedBB BAMBOO_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 1.0D, 0.8D);
	private static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);

	BlockBamboo() {
		super(Material.WOOD);
		setDefaultState(blockState.getBaseState().withProperty(AGE, 0));
		setTickRandomly(true);
		setCreativeTab(ModTabs.BLOCKS);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BAMBOO_AABB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess world, BlockPos pos) {
		return BAMBOO_AABB;
	}

	@Nullable
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return getMetaFromState(state) >= 8 && rand.nextInt(17) <= 3 ? null : ModItems.MATERIALS;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return EnumType.BAMBOO.ordinal();
	}

	@Override
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		if (this.getMetaFromState(state) == 0 && RANDOM.nextInt(getMetaFromState(state) >= 8 ? 35 : 20) == 0)
			ret.add(new ItemStack(Item.getItemFromBlock(EnumWood.BAMBOO.getSapling())));
		ret.add(new ItemStack(ModItems.MATERIALS, 1, EnumType.BAMBOO.ordinal()));
		return ret;
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
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (world.getBlockState(pos.down()).getBlock() == EnumWood.BAMBOO.getLog() || checkForDrop(world, pos, state)) {
			if (world.isAirBlock(pos.up())) {
				int growthHeight;

				for (growthHeight = 1; world.getBlockState(pos.down(growthHeight)).getBlock() == this; ++growthHeight);

				if (growthHeight < 8) {
					int stage = state.getValue(AGE);

					if (stage == 15) {
						world.setBlockState(pos.up(), getDefaultState());
						world.setBlockState(pos, state.withProperty(AGE, 0), 4);
					} else
						world.setBlockState(pos, state.withProperty(AGE, stage + 1), 4);
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
		else if (block != Blocks.GRASS && block != Blocks.DIRT)
			return false;

		return false;
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
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
		return EnumPlantType.Plains;
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
        return new ItemStack(Item.getItemFromBlock(EnumWood.BAMBOO.getLog()));
    }

}