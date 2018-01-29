package erebus.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.annotation.Nullable;

import erebus.ModBlocks;
import erebus.ModBlocks.IHasCustomItem;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.ModTabs;
import erebus.api.IErebusEnum;
import erebus.items.block.ItemBlockEnum;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWallPlants extends Block implements IShearable, IHasCustomItem, ISubBlocksBlock {

	public static final PropertyEnum<EnumWallPlantType> TYPE = PropertyEnum.<EnumWallPlantType>create("type", EnumWallPlantType.class);
	protected static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.2D, 1.0D);
	protected static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(0.0D, 0.8D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.8D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.2D, 1.0D, 1.0D);
	protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.2D);
	protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.8D, 1.0D, 1.0D, 1.0D);

	public BlockWallPlants() {
		super(Material.PLANTS);
		setHardness(0.2F);
		setTickRandomly(true);
		setCreativeTab(ModTabs.PLANTS);
		setSoundType(SoundType.PLANT);
		setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumWallPlantType.MOSS_DOWN));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (tab == ModTabs.PLANTS) {
			list.add(new ItemStack(this, 1, EnumWallPlantType.MOSS_DOWN.ordinal()));
			list.add(new ItemStack(this, 1, EnumWallPlantType.MOULD_DOWN.ordinal()));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TYPE, EnumWallPlantType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumWallPlantType type = ((EnumWallPlantType)state.getValue(TYPE));
		return type.ordinal();
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch ((EnumWallPlantType)state.getValue(TYPE)) {
		default:
		case MOSS_EAST:
			return EAST_AABB;
		case MOSS_WEST:
			return WEST_AABB;
		case MOSS_SOUTH:
			return SOUTH_AABB;
		case MOSS_NORTH:
			return NORTH_AABB;
		case MOSS_UP:
			return UP_AABB;
		case MOSS_DOWN:
			return DOWN_AABB;
		case MOULD_EAST:
			return EAST_AABB;
		case MOULD_WEST:
			return WEST_AABB;
		case MOULD_SOUTH:
			return SOUTH_AABB;
		case MOULD_NORTH:
			return NORTH_AABB;
		case MOULD_UP:
			return UP_AABB;
		case MOULD_DOWN:
			return DOWN_AABB;
		}
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	private boolean canPlaceAt(World world, BlockPos pos, EnumFacing facing) {
		BlockPos blockPos = pos.offset(facing.getOpposite());
		boolean flag = facing.getAxis().isHorizontal();
		return flag && world.isSideSolid(blockPos, facing, true) && canPlaceOn(world, blockPos) || ((facing.equals(EnumFacing.DOWN) || facing.equals(EnumFacing.UP)) && canPlaceOn(world, blockPos));
	}

	private boolean canPlaceOn(World world, BlockPos pos) {
		return isValidBlock(world.getBlockState(pos).getBlock());
	}

	private boolean isValidBlock(Block block) {
		return block == EnumWood.ROTTEN.getLog() || block == ModBlocks.UMBERSTONE;
	}


	@Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {

    	if (meta <= 5) {
			if (facing.equals(EnumFacing.UP) && world.isSideSolid(pos.down(), EnumFacing.UP))
				meta = 0;

			if (facing.equals(EnumFacing.DOWN) && world.isSideSolid(pos.up(), EnumFacing.DOWN))
				meta = 1;

			if (facing.equals(EnumFacing.SOUTH) && world.isSideSolid(pos.north(), EnumFacing.SOUTH))
				meta = 2;

			if (facing.equals(EnumFacing.NORTH) && world.isSideSolid(pos.south(), EnumFacing.NORTH))
				meta = 3;

			if (facing.equals(EnumFacing.EAST) && world.isSideSolid(pos.west(), EnumFacing.EAST))
				meta = 4;

			if (facing.equals(EnumFacing.WEST) && world.isSideSolid(pos.east(), EnumFacing.WEST))
				meta = 5;
		}

		else if (meta > 5) {
			if (facing.equals(EnumFacing.UP) && world.isSideSolid(pos.down(), EnumFacing.UP))
				meta = 6;

			if (facing.equals(EnumFacing.DOWN) && world.isSideSolid(pos.up(), EnumFacing.DOWN))
				meta = 7;

			if (facing.equals(EnumFacing.SOUTH) && world.isSideSolid(pos.north(), EnumFacing.SOUTH))
				meta = 8;

			if (facing.equals(EnumFacing.NORTH) && world.isSideSolid(pos.south(), EnumFacing.NORTH))
				meta = 9;

			if (facing.equals(EnumFacing.EAST) && world.isSideSolid(pos.west(), EnumFacing.EAST))
				meta = 10;

			if (facing.equals(EnumFacing.WEST) && world.isSideSolid(pos.east(), EnumFacing.WEST))
				meta = 11;
		}

    	return getStateFromMeta(meta);
    }


	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		if (world.isRemote)
			return;

		EnumWallPlantType type = state.getValue(TYPE);
		boolean flag = false;

		if (type.equals(EnumWallPlantType.MOSS_DOWN) || type.equals(EnumWallPlantType.MOULD_DOWN))
			if (world.isSideSolid(pos.up(), EnumFacing.DOWN))
				flag = true;
		if (type.equals(EnumWallPlantType.MOSS_UP) || type.equals(EnumWallPlantType.MOULD_UP))
			if (world.isSideSolid(pos.down(), EnumFacing.UP))
				flag = true;
		if (type.equals(EnumWallPlantType.MOSS_NORTH) || type.equals(EnumWallPlantType.MOULD_NORTH))
			if (world.isSideSolid(pos.south(), EnumFacing.NORTH))
				flag = true;
		if (type.equals(EnumWallPlantType.MOSS_SOUTH) || type.equals(EnumWallPlantType.MOULD_SOUTH))
			if (world.isSideSolid(pos.north(), EnumFacing.SOUTH))
				flag = true;
		if (type.equals(EnumWallPlantType.MOSS_WEST) || type.equals(EnumWallPlantType.MOULD_WEST))
			if (world.isSideSolid(pos.east(), EnumFacing.WEST))
				flag = true;
		if (type.equals(EnumWallPlantType.MOSS_EAST) || type.equals(EnumWallPlantType.MOULD_EAST))
			if (world.isSideSolid(pos.west(), EnumFacing.EAST))
				flag = true;

		if (!flag)
			if (!world.isRemote) {
				world.playEvent(null, 2001, pos, Block.getIdFromBlock(world.getBlockState(pos).getBlock()) + world.getBlockState(pos).getBlock().getMetaFromState(state) << 12);
				world.setBlockToAir(pos);
			}
	}

	@Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		int typeMeta = state.getValue(TYPE).ordinal();
		return new ItemStack(Item.getItemFromBlock(this), 1, typeMeta <= 5 ? EnumWallPlantType.MOSS_DOWN.ordinal() : EnumWallPlantType.MOULD_DOWN.ordinal());
    }

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		EnumWallPlantType type = world.getBlockState(pos).getValue(TYPE);
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		switch (type) {
		case MOSS_EAST:
		case MOSS_WEST:
		case MOSS_SOUTH:
		case MOSS_NORTH:
		case MOSS_UP:
		case MOSS_DOWN:
			ret.add(new ItemStack(this, 1, EnumWallPlantType.MOSS_DOWN.ordinal()));
			break;
		case MOULD_EAST:
		case MOULD_WEST:
		case MOULD_SOUTH:
		case MOULD_NORTH:
		case MOULD_UP:
		case MOULD_DOWN:
			ret.add(new ItemStack(this, 1, EnumWallPlantType.MOULD_DOWN.ordinal()));
			break;
		default:
			ret.add(new ItemStack(this, 1, EnumWallPlantType.MOSS_DOWN.ordinal()));
			break;
		}
		return ret;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (world.isRemote)
			return;
		EnumWallPlantType type = world.getBlockState(pos).getValue(TYPE);
		int attempt = 0;
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		if (rand.nextInt(2) == 0) {
			byte radius = 4;
			int distance = 5;
			int xx;
			int yy;
			int zz;
			for (xx = x - radius; xx <= x + radius; ++xx)
				for (zz = z - radius; zz <= z + radius; ++zz)
					for (yy = y - radius; yy <= y + radius; ++yy)
						if (world.getBlockState(new BlockPos(xx, zz, yy)) == this) {
							--distance;
							if (distance <= 0)
								return;
						}

			xx = x + rand.nextInt(3) - 1;
			yy = y + rand.nextInt(3) - 1;
			zz = z + rand.nextInt(3) - 1;
			if (world.isAirBlock(new BlockPos(xx, yy, zz)))
				for (attempt = 0; attempt < 6; attempt++) {
					int offset = 1;
					int randomiseSide = rand.nextInt(6);

					switch (type) {
					
					case MOSS_EAST:
					case MOSS_WEST:
					case MOSS_SOUTH:
					case MOSS_NORTH:
					case MOSS_UP:
					case MOSS_DOWN:
						switch (randomiseSide) {
							case 0:
								if (world.isSideSolid(new BlockPos(xx, yy + offset, zz), EnumFacing.DOWN) && isValidBlock(world.getBlockState(new BlockPos(xx, yy + offset, zz)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantType.MOSS_DOWN), 2);
								break;
							case 1:
								if (world.isSideSolid(new BlockPos(xx, yy - offset, zz), EnumFacing.UP) && isValidBlock(world.getBlockState(new BlockPos(xx, yy - offset, zz)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantType.MOSS_UP), 2);
								break;
							case 2:
								if (world.isSideSolid(new BlockPos(xx, yy, zz + offset), EnumFacing.NORTH) && isValidBlock(world.getBlockState(new BlockPos(xx, yy, zz + offset)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz),getDefaultState().withProperty(TYPE, EnumWallPlantType.MOSS_NORTH), 2);
								break;
							case 3:
								if (world.isSideSolid(new BlockPos(xx, yy, zz - offset), EnumFacing.SOUTH) && isValidBlock(world.getBlockState(new BlockPos(xx, yy, zz - offset)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantType.MOSS_SOUTH), 2);
								break;
							case 4:
								if (world.isSideSolid(new BlockPos(xx + offset, yy, zz), EnumFacing.WEST) && isValidBlock(world.getBlockState(new BlockPos(xx + offset, yy, zz)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantType.MOSS_WEST), 2);
								break;
							case 5:
								if (world.isSideSolid(new BlockPos(xx - offset, yy, zz), EnumFacing.EAST) && isValidBlock(world.getBlockState(new BlockPos(xx - offset, yy, zz)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantType.MOSS_EAST), 2);
								break;
						}
					break;
					case MOULD_EAST:
					case MOULD_WEST:
					case MOULD_SOUTH:
					case MOULD_NORTH:
					case MOULD_UP:
					case MOULD_DOWN:
						switch (randomiseSide) {
							case 0:
								if (world.isSideSolid(new BlockPos(xx, yy + offset, zz), EnumFacing.DOWN) && isValidBlock(world.getBlockState(new BlockPos(xx, yy + offset, zz)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantType.MOULD_DOWN), 2);
								break;
							case 1:
								if (world.isSideSolid(new BlockPos(xx, yy - offset, zz), EnumFacing.UP) && isValidBlock(world.getBlockState(new BlockPos(xx, yy - offset, zz)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantType.MOULD_UP), 2);
								break;
							case 2:
								if (world.isSideSolid(new BlockPos(xx, yy, zz + offset), EnumFacing.NORTH) && isValidBlock(world.getBlockState(new BlockPos(xx, yy, zz + offset)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantType.MOULD_NORTH), 2);
								break;
							case 3:
								if (world.isSideSolid(new BlockPos(xx, yy, zz - offset), EnumFacing.SOUTH) && isValidBlock(world.getBlockState(new BlockPos(xx, yy, zz - offset)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantType.MOULD_SOUTH), 2);
								break;
							case 4:
								if (world.isSideSolid(new BlockPos(xx + offset, yy, zz), EnumFacing.WEST) && isValidBlock(world.getBlockState(new BlockPos(xx + offset, yy, zz)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantType.MOULD_WEST), 2);
								break;
							case 5:
								if (world.isSideSolid(new BlockPos(xx - offset, yy, zz), EnumFacing.EAST) && isValidBlock(world.getBlockState(new BlockPos(xx - offset, yy, zz)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantType.MOULD_EAST), 2);
								break;
						}
						break;
					}
				}
			if (rand.nextInt(25) == 0)
				world.setBlockToAir(pos);
		}
	}

	public static enum EnumWallPlantType implements IErebusEnum {
		MOSS_UP,
		MOSS_DOWN,
		MOSS_SOUTH,
		MOSS_NORTH,
		MOSS_EAST,
		MOSS_WEST,
		MOULD_UP,
		MOULD_DOWN,
		MOULD_SOUTH,
		MOULD_NORTH,
		MOULD_EAST,
		MOULD_WEST;

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModBlocks.WALL_PLANTS, size, ordinal());
		}
	}
	
	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumWallPlantType type : EnumWallPlantType.values())
			models.add(type.getName());
		return models;
	}

	@Override
	public ItemBlock getItemBlock() {
		return ItemBlockEnum.create(this, EnumWallPlantType.class);
	}

}