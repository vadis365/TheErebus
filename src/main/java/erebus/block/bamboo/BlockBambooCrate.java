package erebus.block.bamboo;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.annotation.Nullable;

import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModBlocks.IHasCustomItem;
import erebus.ModTabs;
import erebus.api.IErebusEnum;
import erebus.blocks.EnumWood;
import erebus.core.helper.Utils;
import erebus.proxy.CommonProxy;
import erebus.tileentity.TileEntityBambooCrate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBambooCrate extends BlockContainer implements IHasCustomItem {
	public static final PropertyEnum<EnumCrateType> CRATE_TYPE = PropertyEnum.create("crate_type", EnumCrateType.class);
	public BlockBambooCrate() {
		super(Material.WOOD);
		setHardness(2.0F);
		setHarvestLevel("axe", 0);
		setSoundType(SoundType.WOOD);
		setCreativeTab(ModTabs.BLOCKS);
		setDefaultState(blockState.getBaseState().withProperty(CRATE_TYPE, EnumCrateType.DEFAULT));
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityBambooCrate();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { CRATE_TYPE });
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (tab == ModTabs.BLOCKS)
			list.add(new ItemStack(this, 1, EnumCrateType.DEFAULT.ordinal()));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(CRATE_TYPE, EnumCrateType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumCrateType type = state.getValue(CRATE_TYPE);
		return type.ordinal();
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return true;
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

	@Nullable
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if (isCrate(world, pos)) {
			// BOTTOM
			if (isCrate(world, pos.up()) && isCrate(world, pos.add(1, 1, 0)) && isCrate(world, pos.add(1, 1, 1)) && isCrate(world, pos.add(0, 1, 1)))
				if (isCrate(world, pos.add(1, 0, 0)) && isCrate(world, pos.add(1, 0, 1)) && isCrate(world, pos.add(0, 0, 1))) {
					world.setBlockState(pos, this.getDefaultState().withProperty(CRATE_TYPE, EnumCrateType.BTL), 3); // BTL
					return;
				}

			if (isCrate(world, pos.up()) && isCrate(world, pos.add(- 1, 1, 0)) && isCrate(world, pos.add(- 1, 1, 1)) && isCrate(world, pos.add(0, 1, 1)))
				if (isCrate(world, pos.add(- 1, 0, 0)) && isCrate(world, pos.add(- 1, 0, 1)) && isCrate(world, pos.add(0, 0, 1))) {
					world.setBlockState(pos, this.getDefaultState().withProperty(CRATE_TYPE, EnumCrateType.BTR), 3); // BTR
					return;
				}

			if (isCrate(world, pos.up()) && isCrate(world, pos.add(1, 1, 0)) && isCrate(world, pos.add(1, 1, - 1)) && isCrate(world, pos.add(0, 1, - 1)))
				if (isCrate(world, pos.add(1, 0, 0)) && isCrate(world, pos.add(1, 0, - 1)) && isCrate(world, pos.add(0, 0, - 1))) {
					world.setBlockState(pos, this.getDefaultState().withProperty(CRATE_TYPE, EnumCrateType.BBL), 3); // BBL
					return;
				}

			if (isCrate(world, pos.up()) && isCrate(world, pos.add(- 1, 1, 0)) && isCrate(world, pos.add(- 1, 1, - 1)) && isCrate(world, pos.add(0, 1, - 1)))
				if (isCrate(world, pos.add(- 1, 0, 0)) && isCrate(world, pos.add(- 1, 0, - 1)) && isCrate(world, pos.add(0, 0, - 1))) {
					world.setBlockState(pos, this.getDefaultState().withProperty(CRATE_TYPE, EnumCrateType.BBR), 3); // BBR
					return;
				}

			// TOP
			if (isCrate(world, pos.down()) && isCrate(world, pos.add(1, - 1, 0)) && isCrate(world, pos.add(1, - 1, 1)) && isCrate(world, pos.add(0, - 1, 1)))
				if (isCrate(world, pos.add(1, 0, 0)) && isCrate(world, pos.add(1, 0, 1)) && isCrate(world, pos.add(0, 0, 1))) {
					world.setBlockState(pos, this.getDefaultState().withProperty(CRATE_TYPE, EnumCrateType.TTL), 3); // TTL
					return;
				}

			if (isCrate(world, pos.down()) && isCrate(world, pos.add(- 1, - 1, 0)) && isCrate(world, pos.add(- 1, - 1, 1)) && isCrate(world, pos.add(0, - 1, 1)))
				if (isCrate(world, pos.add(- 1, 0, 0)) && isCrate(world, pos.add(- 1, 0, 1)) && isCrate(world, pos.add(0, 0, 1))) {
					world.setBlockState(pos, this.getDefaultState().withProperty(CRATE_TYPE, EnumCrateType.TTR), 3); // TTR
					return;
				}

			if (isCrate(world, pos.down()) && isCrate(world,pos.add( 1, - 1, 0)) && isCrate(world, pos.add(1, - 1, - 1)) && isCrate(world, pos.add(0, - 1, - 1)))
				if (isCrate(world, pos.add(1, 0, 0)) && isCrate(world, pos.add(1, 0, - 1)) && isCrate(world, pos.add(0, 0, - 1))) {
					world.setBlockState(pos, this.getDefaultState().withProperty(CRATE_TYPE, EnumCrateType.TBL), 3); // TBL
					return;
				}

			if (isCrate(world, pos.down()) && isCrate(world, pos.add(- 1, - 1, 0)) && isCrate(world, pos.add(- 1, - 1, - 1)) && isCrate(world, pos.add(0, - 1, - 1)))
				if (isCrate(world, pos.add(- 1, 0, 0)) && isCrate(world, pos.add(- 1, 0, - 1)) && isCrate(world, pos.add(0, 0, - 1))) {
					world.setBlockState(pos, this.getDefaultState().withProperty(CRATE_TYPE, EnumCrateType.TBR), 3); // TBR
					return;
				}
		}
	}

	@Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		if (block == this)
			for (int i = -1; i <= 1; i++)
				for (int j = -1; j <= 1; j++)
					for (int k = -1; k <= 1; k++)
						if (world.getBlockState(pos.add(i, k, j)).getBlock() == this)
							onBlockAdded(world, pos.add(i, k, j), world.getBlockState(pos.add(i, k, j)));
	}

	private boolean isCrate(World world, BlockPos pos) {
		return world.getBlockState(pos).getBlock() == this;
	}

	@Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
		for (EnumFacing dir : EnumFacing.VALUES) {
			IBlockState state = world.getBlockState(pos.add(dir.getFrontOffsetX(), dir.getFrontOffsetY(), dir.getFrontOffsetZ()));
			if (state.getBlock() == this) {
				int meta = state.getBlock().getMetaFromState(state);
				if (meta != 0)
					return false;
				if (world.getBlockState(pos.add(dir.getOpposite().getFrontOffsetX(), dir.getOpposite().getFrontOffsetY(), dir.getOpposite().getFrontOffsetZ())).getBlock() == this)
					return false;
			}
		}
		return true;
	}

	@Override
	 public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		TileEntityBambooCrate tileCrate = Utils.getTileEntity(world, pos, TileEntityBambooCrate.class);
		if (tileCrate != null)
			if (state.getBlock().getMetaFromState(state) == 0) {
				ItemStack current = player.inventory.getCurrentItem();
				if (!current.isEmpty() && current.getItem() == Item.getItemFromBlock(this))
					return false;
				player.openGui(Erebus.INSTANCE, CommonProxy.GuiID.BAMBOO_CRATE.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
			} else
				for (int i = -1; i <= 1; i++)
					for (int j = -1; j <= 1; j++)
						for (int k = -1; k <= 1; k++)
							if (world.getBlockState(pos.add(i, k, j)).getBlock() == this) {
								IBlockState crateState = world.getBlockState(pos.add(i, k, j));
								if (crateState.getBlock().getMetaFromState(crateState) == 1) {
									player.openGui(Erebus.INSTANCE, CommonProxy.GuiID.COLOSSAL_CRATE.ordinal(), world, pos.getX() + i, pos.getY() + k, pos.getZ() + j);
									return true;
								}
							}
		return true;
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (!world.isRemote && !player.capabilities.isCreativeMode) {
			TileEntityBambooCrate tile = Utils.getTileEntity(world, pos, TileEntityBambooCrate.class);
			if (tile !=null) {
				NBTTagCompound nbt = new NBTTagCompound();
				tile.writeToNBT(nbt);
				ItemStack stack = new ItemStack(Item.getItemFromBlock(this), 1, 0);
				for (int i = 0; i < tile.getSizeInventory(); ++i) {
					ItemStack itemstack = tile.getStackInSlot(i);
					if (!itemstack.isEmpty()) {
						stack.setTagCompound(nbt);
						break;
					}
				}
				InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
				world.removeTileEntity(pos);
			}
		}
	}

	@Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(world, pos, state, placer, stack);
		if(!world.isRemote && stack.hasTagCompound() && stack.getTagCompound().hasKey("Items")) {
			TileEntityBambooCrate tile = Utils.getTileEntity(world, pos, TileEntityBambooCrate.class);
			if (tile !=null) {
				NBTTagList tags = stack.getTagCompound().getTagList("Items", 10);
				tile.inventory = NonNullList.<ItemStack>withSize(tile.getSizeInventory(), ItemStack.EMPTY);

				for (int i = 0; i < tags.tagCount(); i++) {
					NBTTagCompound data = tags.getCompoundTagAt(i);
					int j = data.getByte("Slot") & 255;

					if (j >= 0 && j < tile.inventory.size())
						tile.inventory.set(j, new ItemStack(data));
				}
			}
			world.notifyBlockUpdate(pos, state, state, 3);
		}
	}

	@Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
		resetCrates(world, pos, state.getBlock().getMetaFromState(state));
		world.playEvent(2001, pos, Block.getStateId(EnumWood.BAMBOO.getLog().getDefaultState()));
		super.breakBlock(world, pos, state);
	}

	private void resetCrates(World world, BlockPos pos, int meta) {
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++)
				for (int k = -1; k <= 1; k++)
					if (world.getBlockState(pos.add(i, k, j)).getBlock() == this) {
						IBlockState crateState = world.getBlockState(pos.add(i, k, j));
						if (crateState.getBlock().getMetaFromState(crateState) != 0)
							world.setBlockState(pos.add(i, k, j), this.getStateFromMeta(0), 3);
					}
	}

	@Override
	public ItemBlock getItemBlock() {
		ItemBlock CRATE_ITEM = new ItemBlock(this.getDefaultState().withProperty(CRATE_TYPE, EnumCrateType.DEFAULT).getBlock()) {
			@Override
			@SideOnly(Side.CLIENT)
			public void addInformation(ItemStack stack, @Nullable World world, List<String> list, ITooltipFlag flag) {
				list.add("Stores Items When Broken");
				if (stack.hasTagCompound() && stack.getTagCompound().getTagList("Items", 10) != null) {
					NBTTagList tags = stack.getTagCompound().getTagList("Items", 10);

					for (int i = 0; i < tags.tagCount(); i++) {
						NBTTagCompound data = tags.getCompoundTagAt(i);
						int j = data.getByte("Slot") & 255;
						list.add("Slot " + (j + 1) + ": " + TextFormatting.GREEN + new ItemStack(data).getDisplayName() + " x " + new ItemStack(data).getCount());
					}
				}
			}
		};

		return CRATE_ITEM;
	}

	public enum EnumCrateType implements IErebusEnum {
		DEFAULT,
		BTL,
		BTR,
		BBL,
		BBR,
		TTL,
		TTR,
		TBL,
		TBR;

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModBlocks.BAMBOO_CRATE, size, ordinal());
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}