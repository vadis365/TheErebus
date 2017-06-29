package erebus.blocks;

import java.util.Locale;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGlowshroomStalkMain extends Block {
	public static final PropertyEnum<EnumPartType> PART = PropertyEnum.<EnumPartType>create("part", EnumPartType.class);
/*
	private static final AxisAlignedBB[] GLOWSHROOM_AABB = new AxisAlignedBB[] {
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D),
			new AxisAlignedBB(0.3125D, 0.5D, 0.3125D, 0.6875D, 1.0D, 0.6875D),
			new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 1.0D, 0.6875D),
			new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 1.0D, 0.8125D),
			//NORTH
			new AxisAlignedBB(0D, 0.3125D, 0.3125D, 0.6875D, 0.6875D, 0.6875D),
			new AxisAlignedBB(0D, 0.1875D, 0.1875D, 1D, 0.8125D, 0.8125D),
			new AxisAlignedBB(0.3125D, 0.3125D, 0.3125D, 0.6875D, 1.0D, 0.6875D),
			//SOUTH
			new AxisAlignedBB(0.3125D, 0.3125D, 0D, 0.6875D, 0.6875D, 0.6875D),
			new AxisAlignedBB(0.1875D, 0.1875D, 0D, 0.8125D, 0.8125D, 1D),
			new AxisAlignedBB(0.3125D, 0.3125D, 0.3125D, 0.6875D, 1.0D, 0.6875D),
			//EAST NOT DONE
			new AxisAlignedBB(0.3125D, 0.3125D, 0D, 0.6875D, 0.6875D, 0.6875D),
			new AxisAlignedBB(0.1875D, 0.1875D, 0D, 0.8125D, 0.8125D, 1D),
			new AxisAlignedBB(0.3125D, 0.3125D, 0.3125D, 0.6875D, 1.0D, 0.6875D),
			//WEST NOT DONE
			new AxisAlignedBB(0.3125D, 0.3125D, 0D, 0.6875D, 0.6875D, 0.6875D),
			new AxisAlignedBB(0.1875D, 0.1875D, 0D, 0.8125D, 0.8125D, 1D),
			new AxisAlignedBB(0.3125D, 0.3125D, 0.3125D, 0.6875D, 1.0D, 0.6875D)};
*/
	public BlockGlowshroomStalkMain() {
		super(Material.WOOD);
		setTickRandomly(true);
		setHardness(0.2F);
		setSoundType(SoundType.WOOD);
		setCreativeTab(ModTabs.PLANTS);
		setDefaultState(blockState.getBaseState().withProperty(PART, EnumPartType.MAIN));
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { PART });
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(PART, EnumPartType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumPartType part = state.getValue(PART);
		return part.ordinal();
	}
/*
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return GLOWSHROOM_AABB[state.getValue(PART).ordinal()];
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return GLOWSHROOM_AABB[state.getValue(PART).ordinal()];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return GLOWSHROOM_AABB[state.getValue(PART).ordinal()];
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean whatIsThis) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, GLOWSHROOM_AABB[state.getValue(PART).ordinal()]);
	}
*/
	@Override
	public int tickRate(World world) {
		return 5;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (world.isRemote)
			return;

		EnumPartType part = state.getValue(PART);

		switch (part) {
		case MAIN: {
			switch (rand.nextInt(5)) {
			case 0:
				if (world.isAirBlock(pos.down()))
					world.setBlockState(pos.down(), getDefaultState().withProperty(PART, EnumPartType.DOWN_1), 2);
				break;
			case 1:
				if (world.isAirBlock(pos.north()) && world.getBlockState(pos.up()).getBlock() == ModBlocks.GLOWSHROOM_STALK_MAIN)
					world.setBlockState(pos.north(), getDefaultState().withProperty(PART, EnumPartType.NORTH_1), 2);
				break;
			case 2:
				if (world.isAirBlock(pos.south()) && world.getBlockState(pos.up()).getBlock() == ModBlocks.GLOWSHROOM_STALK_MAIN)
					world.setBlockState(pos.south(), getDefaultState().withProperty(PART, EnumPartType.SOUTH_1), 2);
				break;
			case 3:
				if (world.isAirBlock(pos.west()) && world.getBlockState(pos.up()).getBlock() == ModBlocks.GLOWSHROOM_STALK_MAIN)
					world.setBlockState(pos.west(), getDefaultState().withProperty(PART, EnumPartType.WEST_1), 2);
				break;
			case 4:
				if (world.isAirBlock(pos.east()) && world.getBlockState(pos.up()).getBlock() == ModBlocks.GLOWSHROOM_STALK_MAIN)
					world.setBlockState(pos.east(), getDefaultState().withProperty(PART, EnumPartType.EAST_1), 2);
				break;
			}

			if (rand.nextInt(10) == 0)
				if (world.isAirBlock(pos.up()))
					world.setBlockState(pos.up(), ModBlocks.GLOWSHROOM.getDefaultState());
			break;
		}
		case DOWN_1:
			world.setBlockState(pos, getDefaultState().withProperty(PART, EnumPartType.DOWN_2), 2);
			break;
		case DOWN_2:
			world.setBlockState(pos, getDefaultState().withProperty(PART, EnumPartType.DOWN_2), 2);
			break;
		case DOWN_3:
			world.setBlockState(pos, getDefaultState().withProperty(PART, EnumPartType.MAIN), 2);
			break;
		case EAST_1:
			if (rand.nextBoolean()) {
				world.setBlockState(pos, getDefaultState().withProperty(PART, EnumPartType.EAST_2), 2);
				if (world.isAirBlock(pos.east()))
					world.setBlockState(pos.east(), getDefaultState().withProperty(PART, EnumPartType.EAST_1), 2);
			} else if (world.isAirBlock(pos.up())) {
				world.setBlockState(pos, getDefaultState().withProperty(PART, EnumPartType.EAST_3), 2);
				world.setBlockState(pos.up(), ModBlocks.GLOWSHROOM.getDefaultState(), 2);
			}
			break;
		case EAST_2:
			if (world.getBlockState(pos.west()) == getDefaultState().withProperty(PART, EnumPartType.MAIN) && world.getBlockState(pos.east()) != getDefaultState().withProperty(PART, EnumPartType.EAST_3)) {
				world.setBlockState(pos, getDefaultState().withProperty(PART, EnumPartType.MAIN), 2);
				if (world.getBlockState(pos.east()) == getDefaultState().withProperty(PART, EnumPartType.EAST_1))
					world.setBlockState(pos.east(), getDefaultState().withProperty(PART, EnumPartType.EAST_2));
			}
			break;
		case EAST_3:
			break;
		case NORTH_1:
			if (rand.nextBoolean()) {
				world.setBlockState(pos, getDefaultState().withProperty(PART, EnumPartType.NORTH_2), 2);
				if (world.isAirBlock(pos.north()))
					world.setBlockState(pos.north(), getDefaultState().withProperty(PART, EnumPartType.NORTH_1), 2);
			} else if (world.isAirBlock(pos.up())) {
				world.setBlockState(pos, getDefaultState().withProperty(PART, EnumPartType.NORTH_3), 2);
				world.setBlockState(pos.up(), ModBlocks.GLOWSHROOM.getDefaultState(), 2);
			}
			break;
		case NORTH_2:
			if (world.getBlockState(pos.south()) == getDefaultState().withProperty(PART, EnumPartType.MAIN) && world.getBlockState(pos.north()) != getDefaultState().withProperty(PART, EnumPartType.NORTH_3)) {
				world.setBlockState(pos, getDefaultState().withProperty(PART, EnumPartType.MAIN), 2);
				if (world.getBlockState(pos.north()) == getDefaultState().withProperty(PART, EnumPartType.NORTH_1))
					world.setBlockState(pos.north(), getDefaultState().withProperty(PART, EnumPartType.NORTH_2));
			}
			break;
		case NORTH_3:
			break;
		case SOUTH_1:
			if (rand.nextBoolean()) {
				world.setBlockState(pos, getDefaultState().withProperty(PART, EnumPartType.SOUTH_2), 2);
				if (world.isAirBlock(pos.south()))
					world.setBlockState(pos.south(), getDefaultState().withProperty(PART, EnumPartType.SOUTH_1), 2);
			} else if (world.isAirBlock(pos.up())) {
				world.setBlockState(pos, getDefaultState().withProperty(PART, EnumPartType.SOUTH_3), 2);
				world.setBlockState(pos.up(), ModBlocks.GLOWSHROOM.getDefaultState(), 2);
			}
			break;
		case SOUTH_2:
			if (world.getBlockState(pos.north()) == getDefaultState().withProperty(PART, EnumPartType.MAIN) && world.getBlockState(pos.south()) != getDefaultState().withProperty(PART, EnumPartType.SOUTH_3)) {
				world.setBlockState(pos, getDefaultState().withProperty(PART, EnumPartType.MAIN), 2);
				if (world.getBlockState(pos.south()) == getDefaultState().withProperty(PART, EnumPartType.SOUTH_1))
					world.setBlockState(pos.south(), getDefaultState().withProperty(PART, EnumPartType.SOUTH_2));
			}
			break;
		case SOUTH_3:
			break;
		case WEST_1:
			if (rand.nextBoolean()) {
				world.setBlockState(pos, getDefaultState().withProperty(PART, EnumPartType.WEST_2), 2);
				if (world.isAirBlock(pos.west()))
					world.setBlockState(pos.west(), getDefaultState().withProperty(PART, EnumPartType.WEST_1), 2);
			} else if (world.isAirBlock(pos.up())) {
				world.setBlockState(pos, getDefaultState().withProperty(PART, EnumPartType.WEST_3), 2);
				world.setBlockState(pos.up(), ModBlocks.GLOWSHROOM.getDefaultState(), 2);
			}
			break;
		case WEST_2:
			if (world.getBlockState(pos.east()) == getDefaultState().withProperty(PART, EnumPartType.MAIN) && world.getBlockState(pos.west()) != getDefaultState().withProperty(PART, EnumPartType.WEST_3)) {
				world.setBlockState(pos, getDefaultState().withProperty(PART, EnumPartType.MAIN), 2);
				if (world.getBlockState(pos.west()) == getDefaultState().withProperty(PART, EnumPartType.WEST_1))
					world.setBlockState(pos.west(), getDefaultState().withProperty(PART, EnumPartType.WEST_2));
			}
			break;
		case WEST_3:
			break;
		default:
			break;
		}
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		return isValidBlock(world.getBlockState(pos.down())) || isValidBlock(world.getBlockState(pos.up())) || isValidBlock(world.getBlockState(pos.north())) || isValidBlock(world.getBlockState(pos.south())) || isValidBlock(world.getBlockState(pos.west())) || isValidBlock(world.getBlockState(pos.east()));
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		if (world.isRemote)
			return;

		EnumPartType part = state.getValue(PART);
		boolean flag = false;
		if (part == EnumPartType.MAIN)
			if(canPlaceBlockAt(world, pos))
				flag = true;
		if (part == EnumPartType.DOWN_1 || part == EnumPartType.DOWN_2 || part == EnumPartType.DOWN_3)
			if (isValidBlock(world.getBlockState(pos.up())))
				flag = true;
		if (part == EnumPartType.NORTH_1 || part == EnumPartType.NORTH_2 || part == EnumPartType.NORTH_3)
			if (isValidBlock(world.getBlockState(pos.south())))
				flag = true;
		if (part == EnumPartType.SOUTH_1 || part == EnumPartType.SOUTH_2 || part == EnumPartType.SOUTH_3)
			if (isValidBlock(world.getBlockState(pos.north())))
				flag = true;
		if (part == EnumPartType.EAST_1 || part == EnumPartType.EAST_2 || part == EnumPartType.EAST_3)
			if (isValidBlock(world.getBlockState(pos.west())))
				flag = true;
		if (part == EnumPartType.WEST_1 || part == EnumPartType.WEST_2 || part == EnumPartType.WEST_3)
			if (isValidBlock(world.getBlockState(pos.east())))
				flag = true;

		if (!flag) {
			world.playEvent(null, 2001, pos, Block.getIdFromBlock(world.getBlockState(pos).getBlock()) + world.getBlockState(pos).getBlock().getMetaFromState(state) << 12);
			if (state.getValue(PART) == EnumPartType.MAIN)
				Utils.dropStack(world, pos, new ItemStack(Item.getItemFromBlock(this)));
			world.setBlockToAir(pos);
		}
	}

	private boolean isValidBlock(IBlockState state) {
		return state.getMaterial().blocksMovement() || state == this;
	}

	public static enum EnumPartType implements IStringSerializable {
		MAIN,
		DOWN_1,
		DOWN_2,
		DOWN_3,
		NORTH_1,
		NORTH_2,
		NORTH_3,
		SOUTH_1,
		SOUTH_2,
		SOUTH_3,
		EAST_1,
		EAST_2,
		EAST_3,
		WEST_1,
		WEST_2,
		WEST_3;

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}