package erebus.block.silo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.annotation.Nullable;

import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModBlocks.IHasCustomItem;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.ModTabs;
import erebus.api.IErebusEnum;
import erebus.core.helper.Utils;
import erebus.items.block.ItemBlockEnum;
import erebus.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSiloTank extends BlockContainer implements IHasCustomItem, ISubBlocksBlock {
	public static final PropertyEnum<EnumSiloType> ACTIVE = PropertyEnum.create("active", EnumSiloType.class);
	protected static final AxisAlignedBB SILO_TANK_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1D, 0.875D);

	public BlockSiloTank(Material material) {
		super(material);
		setDefaultState(blockState.getBaseState().withProperty(ACTIVE, EnumSiloType.SILO_INACTIVE));
		setCreativeTab(ModTabs.BLOCKS);
		setHardness(3F);
		setSoundType(SoundType.METAL);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SILO_TANK_AABB;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return SILO_TANK_AABB;
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
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if (isSiloComplete(world, pos)) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile != null && tile instanceof TileEntitySiloTank) {
				((TileEntitySiloTank) tile).setActive(true);
				world.setBlockState(pos, this.getDefaultState().withProperty(ACTIVE, EnumSiloType.SILO_ACTIVE), 3);
				return;
			}
		}
	}

	@Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		if (isSiloComplete(world, pos)) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile != null && tile instanceof TileEntitySiloTank) {
				((TileEntitySiloTank) tile).setActive(true);
				world.setBlockState(pos, this.getDefaultState().withProperty(ACTIVE, EnumSiloType.SILO_ACTIVE), 3);
			}
		} else if (!isSiloComplete(world, pos)) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile != null && tile instanceof TileEntitySiloTank) {
				((TileEntitySiloTank) tile).setActive(false);
				world.setBlockState(pos, this.getDefaultState().withProperty(ACTIVE, EnumSiloType.SILO_INACTIVE), 3);
				breakBlock(world, pos, state);
				dropBlockAsItem(world, pos, state, 0);
			}
		}
	}

	@Override
	 public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		TileEntity tile = world.getTileEntity(pos);
		if (tile != null && tile instanceof TileEntitySiloTank) {
			ItemStack current = player.inventory.getCurrentItem();
			if (!current.isEmpty() && current.getItem() == Item.getItemFromBlock(ModBlocks.SILO_ROOF))
				return false;
			if (isSiloComplete(world, pos)) {
				player.openGui(Erebus.INSTANCE, CommonProxy.GuiID.SILO_INVENTORY.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
				return true;
			}
		}
		return true;
	}

	public boolean isSiloComplete(World world, BlockPos pos) {
		IBlockState stateDown = world.getBlockState(pos.down());
		IBlockState stateUp = world.getBlockState(pos.up());
		return stateDown.getBlock() == ModBlocks.SILO_SUPPORTS && stateUp.getBlock() == ModBlocks.SILO_ROOF;
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)  {
		return world.getBlockState(pos.down()).getBlock() == ModBlocks.SILO_SUPPORTS;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntitySiloTank tile = Utils.getTileEntity(world, pos, TileEntitySiloTank.class);
		if (tile != null)
			for (int i = 0; i < tile.getSizeInventory(); i++) {
				ItemStack is = tile.getStackInSlot(i);
				if (!is.isEmpty())
					Utils.dropStack(world, pos, is);
			}
		world.setBlockToAir(pos);
		super.breakBlock(world, pos, state);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntitySiloTank();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { ACTIVE });
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (tab == ModTabs.BLOCKS)
			list.add(new ItemStack(this, 1, EnumSiloType.SILO_INACTIVE.ordinal()));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(ACTIVE, EnumSiloType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumSiloType type = state.getValue(ACTIVE);
		return type.ordinal();
	}

	@Override
	public ItemBlock getItemBlock() {
		return ItemBlockEnum.create(this, EnumSiloType.class);
	}

	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumSiloType type : EnumSiloType.values())
			models.add(type.getName());
		return models;
	}

	public enum EnumSiloType implements IErebusEnum {

		SILO_INACTIVE,
		SILO_ACTIVE;

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModBlocks.SILO_TANK, size, ordinal());
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}