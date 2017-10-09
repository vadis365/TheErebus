package erebus.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGneiss extends Block implements IHasCustomItem, ISubBlocksBlock {

	public static final PropertyEnum<EnumGneissType> TYPE = PropertyEnum.create("type", EnumGneissType.class);

	public BlockGneiss() {
		super(Material.ROCK);
		setHardness(30F);
		setResistance(6000000.0F);
		setSoundType(SoundType.STONE);
		setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumGneissType.GNEISS));
		setCreativeTab(ModTabs.BLOCKS);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TYPE, EnumGneissType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumGneissType type = state.getValue(TYPE);
		return type.ordinal();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (tab == ModTabs.BLOCKS)
			for (EnumGneissType type : EnumGneissType.values())
				list.add(new ItemStack(this, 1, type.ordinal()));
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote)
			world.setBlockState(pos, Blocks.FLOWING_LAVA.getDefaultState(), 11);
	}

	@Override
	public ItemBlock getItemBlock() {
		return ItemBlockEnum.create(this, EnumGneissType.class);
	}

	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumGneissType type : EnumGneissType.values())
			models.add(type.getName());
		return models;
	}

	public enum EnumGneissType implements IErebusEnum {

		GNEISS,
		GNEISS_CARVED,
		GNEISS_RELIEF,
		GNEISS_BRICKS,
		GNEISS_SMOOTH,
		GNEISS_TILES,
		GNEISS_TILES_CRACKED;

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModBlocks.GNEISS, size, ordinal());
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}