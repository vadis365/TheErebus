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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGlowshroomStalkMain extends Block {
	public static final PropertyEnum<EnumPartType> PART = PropertyEnum.<EnumPartType>create("part", EnumPartType.class);

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
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
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
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		EnumPartType part = state.getValue(PART);
		float widthMin = 0, heightMin = 0, depthMin = 0;
		float widthMax = 0, heightMax = 0, depthMax = 0;

		switch (part) {
			case MAIN:
				widthMin= 0F;
				heightMin = 0F;
				depthMin= 0F;
				widthMax= 0F;
				heightMax = 0F;
				depthMax= 0F;
				break;
		//down
			case DOWN_1:
				widthMin = 0.3125F;
				heightMin = 0.5F;
				depthMin = 0.3125F;
				widthMax = 0.3125F;
				heightMax = 0F;
				depthMax = 0.3125F;
				break;
			case DOWN_2:
				widthMin= 0.3125F;
				heightMin = 0F;
				depthMin = 0.3125F;
				widthMax = 0.3125F;
				heightMax = 0F;
				depthMax = 0.3125F;
				break;
			case DOWN_3:
				widthMin= 0.1875F;
				heightMin = 0F;
				depthMin= 0.1875F;
				widthMax= 0.1875F;
				heightMax = 0F;
				depthMax= 0.1875F;
				break;
		//north		
			case NORTH_1:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0.3125F;
				heightMax = 0.3125F;
				depthMax = 0F;
				break;
			case NORTH_2:
				widthMin = 0.1875F;
				heightMin = 0.1875F;
				depthMin = 0F;
				widthMax = 0.1875F;
				heightMax = 0.1875F;
				depthMax = 0F;
				break;
			case NORTH_3:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0.3125F;
				heightMax = 0F;
				depthMax = 0F;
				break;
				
		//south
			case SOUTH_1:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0F;
				widthMax = 0.3125F;
				heightMax = 0.3125F;
				depthMax = 0.3125F;
				break;
			case SOUTH_2:
				widthMin = 0.1875F;
				heightMin = 0.1875F;
				depthMin = 0F;
				widthMax = 0.1875F;
				heightMax = 0.1875F;
				depthMax = 0F;
				break;
			case SOUTH_3:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0F;
				widthMax = 0.3125F;
				heightMax = 0F;
				depthMax = 0.3125F;
				break;
		//west
			case WEST_1:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0F;
				heightMax = 0.3125F;
				depthMax = 0.3125F;
				break;
			case WEST_2:
				widthMin = 0F;
				heightMin = 0.1875F;
				depthMin = 0.1875F;
				widthMax = 0F;
				heightMax = 0.1875F;
				depthMax = 0.1875F;
				break;
			case WEST_3:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0F;
				heightMax = 0F;
				depthMax = 0.3125F;
				break;
		//east
			case EAST_1:
				widthMin = 0F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0.3125F;
				heightMax = 0.3125F;
				depthMax = 0.3125F;
				break;
			case EAST_2:
				widthMin = 0F;
				heightMin = 0.1875F;
				depthMin = 0.1875F;
				widthMax = 0F;
				heightMax = 0.1875F;
				depthMax = 0.1875F;
				break;
			case EAST_3:
				widthMin = 0F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0.3125F;
				heightMax = 0F;
				depthMax = 0.3125F;
				break;		
		}
		return new AxisAlignedBB(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		EnumPartType part = state.getValue(PART);
		float widthMin = 0, heightMin = 0, depthMin = 0;
		float widthMax = 0, heightMax = 0, depthMax = 0;

		switch (part) {
			case MAIN:
				widthMin= 0F;
				heightMin = 0F;
				depthMin= 0F;
				widthMax= 0F;
				heightMax = 0F;
				depthMax= 0F;
				break;
		//down
			case DOWN_1:
				widthMin = 0.3125F;
				heightMin = 0.5F;
				depthMin = 0.3125F;
				widthMax = 0.3125F;
				heightMax = 0F;
				depthMax = 0.3125F;
				break;
			case DOWN_2:
				widthMin = 0.3125F;
				heightMin = 0F;
				depthMin = 0.3125F;
				widthMax = 0.3125F;
				heightMax = 0F;
				depthMax = 0.3125F;
				break;
			case DOWN_3:
				widthMin = 0.1875F;
				heightMin = 0F;
				depthMin = 0.1875F;
				widthMax = 0.1875F;
				heightMax = 0F;
				depthMax = 0.1875F;
				break;
		//north		
			case NORTH_1:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0.3125F;
				heightMax = 0.3125F;
				depthMax = 0F;
				break;
			case NORTH_2:
				widthMin = 0.1875F;
				heightMin = 0.1875F;
				depthMin = 0F;
				widthMax = 0.1875F;
				heightMax = 0.1875F;
				depthMax = 0F;
				break;
			case NORTH_3:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0.3125F;
				heightMax = 0F;
				depthMax = 0F;
				break;
				
		//south
			case SOUTH_1:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0F;
				widthMax = 0.3125F;
				heightMax = 0.3125F;
				depthMax = 0.3125F;
				break;
			case SOUTH_2:
				widthMin = 0.1875F;
				heightMin = 0.1875F;
				depthMin = 0F;
				widthMax = 0.1875F;
				heightMax = 0.1875F;
				depthMax = 0F;
				break;
			case SOUTH_3:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0F;
				widthMax = 0.3125F;
				heightMax = 0F;
				depthMax = 0.3125F;
				break;
		//west
			case WEST_1:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0F;
				heightMax = 0.3125F;
				depthMax = 0.3125F;
				break;
			case WEST_2:
				widthMin = 0F;
				heightMin = 0.1875F;
				depthMin = 0.1875F;
				widthMax = 0F;
				heightMax = 0.1875F;
				depthMax = 0.1875F;
				break;
			case WEST_3:
				widthMin = 0.3125F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0F;
				heightMax = 0F;
				depthMax = 0.3125F;
				break;
		//east
			case EAST_1:
				widthMin = 0F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0.3125F;
				heightMax = 0.3125F;
				depthMax = 0.3125F;
				break;
			case EAST_2:
				widthMin = 0F;
				heightMin = 0.1875F;
				depthMin = 0.1875F;
				widthMax = 0F;
				heightMax = 0.1875F;
				depthMax = 0.1875F;
				break;
			case EAST_3:
				widthMin = 0F;
				heightMin = 0.3125F;
				depthMin = 0.3125F;
				widthMax = 0.3125F;
				heightMax = 0F;
				depthMax = 0.3125F;
				break;		
		}
		return new AxisAlignedBB(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
	}

	@Override
	public int tickRate(World world) {
		return 10;
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
			world.setBlockState(pos, getDefaultState().withProperty(PART, EnumPartType.DOWN_3), 2);
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
		case EAST_3:
		case NORTH_3:
		case SOUTH_3:
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
		return state.getMaterial().blocksMovement() || state.getBlock() == this;
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