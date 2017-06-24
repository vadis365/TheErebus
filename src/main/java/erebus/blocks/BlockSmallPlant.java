package erebus.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModBlocks.IHasCustomItem;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.api.IErebusEnum;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import erebus.items.block.ItemBlockEnum;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSmallPlant extends BlockTallGrass implements IHasCustomItem, ISubBlocksBlock {
	public static final PropertyEnum<EnumSmallPlantType> PLANT_TYPE = PropertyEnum.create("type", EnumSmallPlantType.class);

	public BlockSmallPlant() {
		setHardness(0.0F);
		setSoundType(SoundType.PLANT);
		setCreativeTab(ModTabs.PLANTS);
		setDefaultState(blockState.getBaseState().withProperty(PLANT_TYPE, EnumSmallPlantType.NETTLE));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { PLANT_TYPE });
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
		for (EnumSmallPlantType type : EnumSmallPlantType.values())
			list.add(new ItemStack(item, 1, type.ordinal()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (state.getValue(PLANT_TYPE) == EnumSmallPlantType.FIRE_BLOOM)
			world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5D, pos.getY() + 1D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D, new int[0]);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(PLANT_TYPE, EnumSmallPlantType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumSmallPlantType type = state.getValue(PLANT_TYPE);
		return type.ordinal();
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		if (rand.nextInt(25) == 0) {
			int xx;
			int yy;
			int zz;
			BlockPos newPos = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);
			xx = pos.getX() + rand.nextInt(3) - 1;
			yy = pos.getY() + rand.nextInt(2) - rand.nextInt(2);
			zz = pos.getZ() + rand.nextInt(3) - 1;
			if (world.isAirBlock(newPos) && canBlockStay(world, newPos, state)) {
				if (state.getValue(PLANT_TYPE) == EnumSmallPlantType.NETTLE && rand.nextInt(3) == 0)
					world.setBlockState(newPos, this.getDefaultState().withProperty(PLANT_TYPE, EnumSmallPlantType.NETTLE_FLOWERED));
				if (state.getValue(PLANT_TYPE) == EnumSmallPlantType.NETTLE_FLOWERED)
					world.setBlockState(newPos, this.getDefaultState().withProperty(PLANT_TYPE, EnumSmallPlantType.NETTLE));
			}
		}
	}

	@Override
	  public NonNullList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		if (state.getValue(PLANT_TYPE) == EnumSmallPlantType.NETTLE)
			return NonNullList.withSize(1, new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.NETTLE_LEAVES.ordinal()));
		else if (state.getValue(PLANT_TYPE) == EnumSmallPlantType.NETTLE_FLOWERED)
			return NonNullList.withSize(1, new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.NETTLE_FLOWERS.ordinal()));
		else if (state.getValue(PLANT_TYPE) == EnumSmallPlantType.SWAMP_PLANT)
			return NonNullList.withSize(1, new ItemStack(ModItems.CABBAGE_SEEDS, 1, 0));
		else if (state.getValue(PLANT_TYPE) == EnumSmallPlantType.DESERT_SHRUB)
			return NonNullList.withSize(1, new ItemStack(this, 1, getMetaFromState(state)));
		else if (state.getValue(PLANT_TYPE) == EnumSmallPlantType.FIRE_BLOOM)
			return NonNullList.withSize(1, new ItemStack(this, 1, getMetaFromState(state)));
		else if (state.getValue(PLANT_TYPE) == EnumSmallPlantType.FIDDLE_HEAD)
			return NonNullList.withSize(1, new ItemStack(Items.MELON_SEEDS));
		else
	        return NonNullList.create();
	}

	@Override
	public ItemBlock getItemBlock() {
		return ItemBlockEnum.create(this, EnumSmallPlantType.class);
	}

	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumSmallPlantType type : EnumSmallPlantType.values())
			models.add(type.getName());
		return models;
	}

	public enum EnumSmallPlantType implements IErebusEnum {
		NETTLE,
		NETTLE_FLOWERED,
		SWAMP_PLANT,
		DESERT_SHRUB,
		FIRE_BLOOM,
		FIDDLE_HEAD,
		FERN;

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModBlocks.SMALL_PLANT, size, ordinal());
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}