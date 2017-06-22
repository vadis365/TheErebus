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
import erebus.items.ItemMaterials;
import erebus.items.block.ItemBlockEnum;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRedGem extends Block implements IHasCustomItem, ISubBlocksBlock {
	
	public static final PropertyEnum<EnumType> TYPE = PropertyEnum.create("type", EnumType.class);

	public BlockRedGem() {
		super(Material.GLASS);
		setHardness(0.3F);
		setLightLevel(1F);
		setSoundType(SoundType.GLASS);
		setCreativeTab(ModTabs.BLOCKS);
		setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumType.RED_GEM));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
		for (EnumType type : EnumType.values())
			list.add(new ItemStack(item, 1, type.ordinal()));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(TYPE) == EnumType.RED_LAMP_ON || state.getValue(TYPE) == EnumType.RED_LAMP_OFF ? 1 : ItemMaterials.EnumType.RED_GEM.ordinal();
	}

	@Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
		if (getMetaFromState(state) == 0)
			return 1 + random.nextInt(2 + fortune);
		return 1;
    }

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(TYPE) == EnumType.RED_GEM ? ModItems.MATERIALS : Item.getItemFromBlock(ModBlocks.RED_GEM);
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
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (state.getValue(TYPE) == EnumType.RED_LAMP_ON)
			return false;
		return true;
    }

	@Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		if (state.getValue(TYPE) == EnumType.RED_GEM || state.getValue(TYPE) == EnumType.RED_LAMP_ON)
			return 15;
		return 0;
    }

	@Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote) {
			if (state.getValue(TYPE) == EnumType.RED_LAMP_OFF && !world.isBlockPowered(pos))
				world.scheduleBlockUpdate(pos, this, 0, 4);
			else if (state.getValue(TYPE) == EnumType.RED_LAMP_ON && world.isBlockPowered(pos))
				world.setBlockState(pos, state.withProperty(TYPE, EnumType.RED_LAMP_OFF), 2);
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!world.isRemote) {
			if (state.getValue(TYPE) == EnumType.RED_LAMP_OFF && !world.isBlockPowered(pos)) {
				world.setBlockState(pos, state.withProperty(TYPE, EnumType.RED_LAMP_ON), 2);
				world.scheduleUpdate(pos, this, 4);
			} else if (state.getValue(TYPE) == EnumType.RED_LAMP_ON && world.isBlockPowered(pos))
				world.setBlockState(pos, state.withProperty(TYPE, EnumType.RED_LAMP_OFF), 2);
		}
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote) {
			if (state.getValue(TYPE) == EnumType.RED_LAMP_OFF && !world.isBlockPowered(pos))
				world.setBlockState(pos, state.withProperty(TYPE, EnumType.RED_LAMP_ON), 2);
		}
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

		RED_GEM,
		RED_LAMP_OFF,
		RED_LAMP_ON;

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModBlocks.RED_GEM, size, ordinal());
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}