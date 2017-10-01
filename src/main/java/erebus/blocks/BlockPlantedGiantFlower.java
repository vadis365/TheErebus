package erebus.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModBlocks.IHasCustomItem;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.ModTabs;
import erebus.api.IErebusEnum;
import erebus.items.block.ItemBlockEnum;
import erebus.world.feature.plant.WorldGenGiantFlowers;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPlantedGiantFlower extends BlockBush implements IGrowable, IHasCustomItem, ISubBlocksBlock {

	public static final PropertyEnum<EnumFlowerType> TYPE = PropertyEnum.create("type", EnumFlowerType.class);
	protected static final AxisAlignedBB FLOWER_AABB = new AxisAlignedBB(0.1D, 0D, 0.1D, 0.9D, 1D, 0.9D);

	public BlockPlantedGiantFlower() {
		setHardness(0.0F);
		setSoundType(SoundType.PLANT);
		setLightLevel(0.5F);
		setCreativeTab(ModTabs.BLOCKS);
		setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumFlowerType.FLOWER_WHITE));
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FLOWER_AABB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}
/*
	@Override
	@SideOnly(Side.CLIENT)
	public Item getDrop(World world, int x, int y, int z) {
		return ModItems.flowerSeeds;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(ModItems.flowerSeeds, 1, meta));
		return ret;
	}
*/
	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (tab == ModTabs.PLANTS)
			for (EnumFlowerType type : EnumFlowerType.values())
				list.add(new ItemStack(this, 1, type.ordinal()));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TYPE, EnumFlowerType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumFlowerType type = state.getValue(TYPE);
		return type.ordinal();
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		if (state.getBlock() == this) {
			IBlockState soil = world.getBlockState(pos.down());
			return soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this);
		}
		return canSustainFlower(world.getBlockState(pos.down()));
	}

    protected boolean canSustainFlower(IBlockState state) {
        return state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.FARMLAND;
    }

	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote) {
			super.updateTick(world, pos, state, rand);

			if (world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
				grow(world, rand, pos, state);
			}
		}
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return (double)rand.nextFloat() < 0.4D;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		int meta = getMetaFromState(state);
		WorldGenerator worldGen = new WorldGenGiantFlowers();
		if (meta >= 0 && meta <= 13)
			((WorldGenGiantFlowers) worldGen).setFlowerColor(meta);
		world.setBlockToAir(pos);
		if (!worldGen.generate(world, rand, pos))
			world.setBlockState(pos, this.getDefaultState().withProperty(TYPE, EnumFlowerType.values()[meta]), 3);
	}

	@Override
	public ItemBlock getItemBlock() {
		return ItemBlockEnum.create(this, EnumFlowerType.class);
	}

	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumFlowerType type : EnumFlowerType.values())
			models.add(type.getName());
		return models;
	}

	public enum EnumFlowerType implements IErebusEnum {
		FLOWER_BLACK,
		FLOWER_RED,
		FLOWER_BROWN,
		FLOWER_BLUE,
		FLOWER_PURPLE,
		FLOWER_CYAN,
		FLOWER_LIGHT_GRAY,
		FLOWER_GRAY,
		FLOWER_PINK,
		FLOWER_YELLOW,
		FLOWER_LIGHT_BLUE,
		FLOWER_MAGENTA,
		FLOWER_ORANGE,
		FLOWER_WHITE,
		FLOWER_RAINBOW;

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModBlocks.PLANTED_FLOWER, size, ordinal());
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}