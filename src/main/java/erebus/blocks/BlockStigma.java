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
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStigma extends Block implements IHasCustomItem, ISubBlocksBlock {

	public static final PropertyEnum<EnumType> TYPE = PropertyEnum.create("type", EnumType.class);

	public BlockStigma() {
		super(Material.PLANTS);
		setLightLevel(1.0F);
		setCreativeTab(ModTabs.BLOCKS);
		setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumType.STIGMA_WHITE));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (tab == ModTabs.PLANTS)
			for (EnumType type : EnumType.values())
				list.add(new ItemStack(this, 1, type.ordinal()));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TYPE, EnumType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumType type = state.getValue(TYPE);
		return type.ordinal();
	}

	@Override
	  public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
		//ret.add(new ItemStack(ModItems.FLOWER_BULBS, 1 + new Random().nextInt(3), meta));
		ret.add(new ItemStack(this, 1 + RANDOM.nextInt(3), getMetaFromState(state)));
		return ret;
	}

	@Override
	public ItemBlock getItemBlock() {
		return ItemBlockEnum.create(this, EnumType.class);
	}

	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumType type : EnumType.values())
			models.add(type.getName());
		return models;
	}

	public enum EnumType implements IErebusEnum {
		STIGMA_BLACK,
		STIGMA_RED,
		STIGMA_BROWN,
		STIGMA_BLUE,
		STIGMA_PURPLE,
		STIGMA_CYAN,
		STIGMA_LIGHT_GRAY,
		STIGMA_GRAY,
		STIGMA_PINK,
		STIGMA_YELLOW,
		STIGMA_LIGHT_BLUE,
		STIGMA_MAGENTA,
		STIGMA_ORANGE,
		STIGMA_WHITE;

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModBlocks.GIANT_FLOWER_STIGMA, size, ordinal());
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}
