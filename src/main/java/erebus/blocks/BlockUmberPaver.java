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
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockUmberPaver extends Block implements IHasCustomItem, ISubBlocksBlock {

	public static final PropertyEnum<EnumUmberPaverType> TYPE = PropertyEnum.create("type", EnumUmberPaverType.class);

	public BlockUmberPaver() {
		super(Material.ROCK);
		setHardness(3.5F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 0);
		setCreativeTab(ModTabs.BLOCKS);
		setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumUmberPaverType.UMBERPAVER));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TYPE, EnumUmberPaverType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumUmberPaverType type = state.getValue(TYPE);
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
			for (EnumUmberPaverType type : EnumUmberPaverType.values())
				list.add(new ItemStack(this, 1, type.ordinal()));
	}

	@Override
	public ItemBlock getItemBlock() {
		return ItemBlockEnum.create(this, EnumUmberPaverType.class);
	}

	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumUmberPaverType type : EnumUmberPaverType.values())
			models.add(type.getName());
		return models;
	}

	public enum EnumUmberPaverType implements IErebusEnum {

		UMBERPAVER,
		UMBERPAVER_MOSSY,
		UMBERPAVER_WEBBED;

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModBlocks.UMBERPAVER, size, ordinal());
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}