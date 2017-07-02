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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWallPlantsCultivated extends Block implements IShearable, IHasCustomItem, ISubBlocksBlock {

	public static final PropertyEnum<EnumWallPlantCultivatedType> TYPE = PropertyEnum.<EnumWallPlantCultivatedType>create("type", EnumWallPlantCultivatedType.class);
	protected static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.2D, 1.0D);
	protected static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(0.0D, 0.8D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.8D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.2D, 1.0D, 1.0D);
	protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.2D);
	protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.8D, 1.0D, 1.0D, 1.0D);

	public BlockWallPlantsCultivated() {
		super(Material.PLANTS);
		setHardness(0.2F);
		setTickRandomly(true);
		setCreativeTab(ModTabs.PLANTS);
		setSoundType(SoundType.PLANT);
		setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumWallPlantCultivatedType.MOSS_DOWN_CULTIVATED));
	}

	@Override
	public int tickRate(World world) {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
		list.add(new ItemStack(item, 1, EnumWallPlantCultivatedType.MOSS_DOWN_CULTIVATED.ordinal()));
		list.add(new ItemStack(item, 1, EnumWallPlantCultivatedType.MOULD_DOWN_CULTIVATED.ordinal()));
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
		return getDefaultState().withProperty(TYPE, EnumWallPlantCultivatedType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumWallPlantCultivatedType type = ((EnumWallPlantCultivatedType)state.getValue(TYPE));
		return type.ordinal();
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch ((EnumWallPlantCultivatedType)state.getValue(TYPE)) {
		default:
		case MOSS_EAST_CULTIVATED:
			return EAST_AABB;
		case MOSS_WEST_CULTIVATED:
			return WEST_AABB;
		case MOSS_SOUTH_CULTIVATED:
			return SOUTH_AABB;
		case MOSS_NORTH_CULTIVATED:
			return NORTH_AABB;
		case MOSS_UP_CULTIVATED:
			return UP_AABB;
		case MOSS_DOWN_CULTIVATED:
			return DOWN_AABB;
		case MOULD_EAST_CULTIVATED:
			return EAST_AABB;
		case MOULD_WEST_CULTIVATED:
			return WEST_AABB;
		case MOULD_SOUTH_CULTIVATED:
			return SOUTH_AABB;
		case MOULD_NORTH_CULTIVATED:
			return NORTH_AABB;
		case MOULD_UP_CULTIVATED:
			return UP_AABB;
		case MOULD_DOWN_CULTIVATED:
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
		return block == EnumWood.ROTTEN.getLog();
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
				meta = 1;
		}

    	return getStateFromMeta(meta);
    }


	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		if (world.isRemote)
			return;

		EnumWallPlantCultivatedType type = state.getValue(TYPE);
		boolean flag = false;

		if (type.equals(EnumWallPlantCultivatedType.MOSS_DOWN_CULTIVATED) || type.equals(EnumWallPlantCultivatedType.MOULD_DOWN_CULTIVATED))
			if (world.isSideSolid(pos.up(), EnumFacing.DOWN))
				flag = true;
		if (type.equals(EnumWallPlantCultivatedType.MOSS_UP_CULTIVATED) || type.equals(EnumWallPlantCultivatedType.MOULD_UP_CULTIVATED))
			if (world.isSideSolid(pos.down(), EnumFacing.UP))
				flag = true;
		if (type.equals(EnumWallPlantCultivatedType.MOSS_NORTH_CULTIVATED) || type.equals(EnumWallPlantCultivatedType.MOULD_NORTH_CULTIVATED))
			if (world.isSideSolid(pos.south(), EnumFacing.NORTH))
				flag = true;
		if (type.equals(EnumWallPlantCultivatedType.MOSS_SOUTH_CULTIVATED) || type.equals(EnumWallPlantCultivatedType.MOULD_SOUTH_CULTIVATED))
			if (world.isSideSolid(pos.north(), EnumFacing.SOUTH))
				flag = true;
		if (type.equals(EnumWallPlantCultivatedType.MOSS_WEST_CULTIVATED) || type.equals(EnumWallPlantCultivatedType.MOULD_WEST_CULTIVATED))
			if (world.isSideSolid(pos.east(), EnumFacing.WEST))
				flag = true;
		if (type.equals(EnumWallPlantCultivatedType.MOSS_EAST_CULTIVATED) || type.equals(EnumWallPlantCultivatedType.MOULD_EAST_CULTIVATED))
			if (world.isSideSolid(pos.west(), EnumFacing.EAST))
				flag = true;

		if (!flag)
			if (!world.isRemote) {
				world.playEvent(null, 2001, pos, Block.getIdFromBlock(world.getBlockState(pos).getBlock()) + world.getBlockState(pos).getBlock().getMetaFromState(state) << 12);
				world.setBlockToAir(pos);
			}
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
		EnumWallPlantCultivatedType type = world.getBlockState(pos).getValue(TYPE);
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		switch (type) {
		case MOSS_EAST_CULTIVATED:
		case MOSS_WEST_CULTIVATED:
		case MOSS_SOUTH_CULTIVATED:
		case MOSS_NORTH_CULTIVATED:
		case MOSS_UP_CULTIVATED:
		case MOSS_DOWN_CULTIVATED:
			ret.add(new ItemStack(this, 1, EnumWallPlantCultivatedType.MOSS_DOWN_CULTIVATED.ordinal()));
			break;
		case MOULD_EAST_CULTIVATED:
		case MOULD_WEST_CULTIVATED:
		case MOULD_SOUTH_CULTIVATED:
		case MOULD_NORTH_CULTIVATED:
		case MOULD_UP_CULTIVATED:
		case MOULD_DOWN_CULTIVATED:
			ret.add(new ItemStack(this, 1, EnumWallPlantCultivatedType.MOULD_DOWN_CULTIVATED.ordinal()));
			break;
		default:
			ret.add(new ItemStack(this, 1, EnumWallPlantCultivatedType.MOSS_DOWN_CULTIVATED.ordinal()));
			break;
		}
		return ret;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (world.isRemote)
			return;
		EnumWallPlantCultivatedType type = world.getBlockState(pos).getValue(TYPE);
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
					
					case MOSS_EAST_CULTIVATED:
					case MOSS_WEST_CULTIVATED:
					case MOSS_SOUTH_CULTIVATED:
					case MOSS_NORTH_CULTIVATED:
					case MOSS_UP_CULTIVATED:
					case MOSS_DOWN_CULTIVATED:
						switch (randomiseSide) {
							case 0:
								if (world.isSideSolid(new BlockPos(xx, yy + offset, zz), EnumFacing.DOWN) && isValidBlock(world.getBlockState(new BlockPos(xx, yy + offset, zz)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantCultivatedType.MOSS_DOWN_CULTIVATED), 2);
								break;
							case 1:
								if (world.isSideSolid(new BlockPos(xx, yy - offset, zz), EnumFacing.UP) && isValidBlock(world.getBlockState(new BlockPos(xx, yy - offset, zz)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantCultivatedType.MOSS_UP_CULTIVATED), 2);
								break;
							case 2:
								if (world.isSideSolid(new BlockPos(xx, yy, zz + offset), EnumFacing.NORTH) && isValidBlock(world.getBlockState(new BlockPos(xx, yy, zz + offset)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz),getDefaultState().withProperty(TYPE, EnumWallPlantCultivatedType.MOSS_NORTH_CULTIVATED), 2);
								break;
							case 3:
								if (world.isSideSolid(new BlockPos(xx, yy, zz - offset), EnumFacing.SOUTH) && isValidBlock(world.getBlockState(new BlockPos(xx, yy, zz - offset)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantCultivatedType.MOSS_SOUTH_CULTIVATED), 2);
								break;
							case 4:
								if (world.isSideSolid(new BlockPos(xx + offset, yy, zz), EnumFacing.WEST) && isValidBlock(world.getBlockState(new BlockPos(xx + offset, yy, zz)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantCultivatedType.MOSS_WEST_CULTIVATED), 2);
								break;
							case 5:
								if (world.isSideSolid(new BlockPos(xx - offset, yy, zz), EnumFacing.EAST) && isValidBlock(world.getBlockState(new BlockPos(xx - offset, yy, zz)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantCultivatedType.MOSS_EAST_CULTIVATED), 2);
								break;
						}
					break;
					case MOULD_EAST_CULTIVATED:
					case MOULD_WEST_CULTIVATED:
					case MOULD_SOUTH_CULTIVATED:
					case MOULD_NORTH_CULTIVATED:
					case MOULD_UP_CULTIVATED:
					case MOULD_DOWN_CULTIVATED:
						switch (randomiseSide) {
							case 0:
								if (world.isSideSolid(new BlockPos(xx, yy + offset, zz), EnumFacing.DOWN) && isValidBlock(world.getBlockState(new BlockPos(xx, yy + offset, zz)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantCultivatedType.MOULD_DOWN_CULTIVATED), 2);
								break;
							case 1:
								if (world.isSideSolid(new BlockPos(xx, yy - offset, zz), EnumFacing.UP) && isValidBlock(world.getBlockState(new BlockPos(xx, yy - offset, zz)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantCultivatedType.MOULD_UP_CULTIVATED), 2);
								break;
							case 2:
								if (world.isSideSolid(new BlockPos(xx, yy, zz + offset), EnumFacing.NORTH) && isValidBlock(world.getBlockState(new BlockPos(xx, yy, zz + offset)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantCultivatedType.MOULD_NORTH_CULTIVATED), 2);
								break;
							case 3:
								if (world.isSideSolid(new BlockPos(xx, yy, zz - offset), EnumFacing.SOUTH) && isValidBlock(world.getBlockState(new BlockPos(xx, yy, zz - offset)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantCultivatedType.MOULD_SOUTH_CULTIVATED), 2);
								break;
							case 4:
								if (world.isSideSolid(new BlockPos(xx + offset, yy, zz), EnumFacing.WEST) && isValidBlock(world.getBlockState(new BlockPos(xx + offset, yy, zz)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantCultivatedType.MOULD_WEST_CULTIVATED), 2);
								break;
							case 5:
								if (world.isSideSolid(new BlockPos(xx - offset, yy, zz), EnumFacing.EAST) && isValidBlock(world.getBlockState(new BlockPos(xx - offset, yy, zz)).getBlock()))
									world.setBlockState(new BlockPos(xx, yy, zz), getDefaultState().withProperty(TYPE, EnumWallPlantCultivatedType.MOULD_EAST_CULTIVATED), 2);
								break;
						}
						break;
					}
				}
			if (rand.nextInt(25) == 0)
				world.setBlockToAir(pos);
		}
	}

	public static enum EnumWallPlantCultivatedType implements IErebusEnum {
		MOSS_UP_CULTIVATED,
		MOSS_DOWN_CULTIVATED,
		MOSS_SOUTH_CULTIVATED,
		MOSS_NORTH_CULTIVATED,
		MOSS_EAST_CULTIVATED,
		MOSS_WEST_CULTIVATED,
		MOULD_UP_CULTIVATED,
		MOULD_DOWN_CULTIVATED,
		MOULD_SOUTH_CULTIVATED,
		MOULD_NORTH_CULTIVATED,
		MOULD_EAST_CULTIVATED,
		MOULD_WEST_CULTIVATED;

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModBlocks.WALL_PLANTS_CULTIVATED, size, ordinal());
		}
	}
	
	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumWallPlantCultivatedType type : EnumWallPlantCultivatedType.values())
			models.add(type.getName());
		return models;
	}

	@Override
	public ItemBlock getItemBlock() {
		return ItemBlockEnum.create(this, EnumWallPlantCultivatedType.class);
	}

}