package erebus.block.bamboo;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import erebus.ModBlocks.IHasCustomItem;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.items.ItemMaterials;
import erebus.tileentity.TileEntityBambooPipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBambooPipe extends BlockDirectional implements ITileEntityProvider, IHasCustomItem {
    public static final PropertyBool CONNECTED_DOWN = PropertyBool.create("connected_down");
    public static final PropertyBool CONNECTED_UP = PropertyBool.create("connected_up");
    public static final PropertyBool CONNECTED_NORTH = PropertyBool.create("connected_north");
    public static final PropertyBool CONNECTED_SOUTH = PropertyBool.create("connected_south");
    public static final PropertyBool CONNECTED_WEST = PropertyBool.create("connected_west");
    public static final PropertyBool CONNECTED_EAST = PropertyBool.create("connected_east");

	public BlockBambooPipe() {
		super(Material.WOOD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECTED_DOWN, Boolean.FALSE).withProperty(CONNECTED_EAST, Boolean.FALSE).withProperty(CONNECTED_NORTH, Boolean.FALSE).withProperty(CONNECTED_SOUTH, Boolean.FALSE).withProperty(CONNECTED_UP, Boolean.FALSE).withProperty(CONNECTED_WEST, Boolean.FALSE));
		setHardness(1.5F);
		setSoundType(SoundType.WOOD);
		setCreativeTab(ModTabs.BLOCKS);
	}


	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (tab == ModTabs.BLOCKS)
			list.add(new ItemStack(this, 1, 0));
	}

	@Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

	@Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(this);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("deprecation")
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		IBlockState iblockstate = world.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();
		return block == this ? false : super.shouldSideBeRendered(state, world, pos, side);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		float minX = 0.3125F, minY = 0.3125F, minZ = 0.3125F;
		float maxX = 0.6875F, maxY = 0.6875F, maxZ = 0.6875F;
		if (state.getValue(FACING) == EnumFacing.UP) maxY = 1.0F;
		if (state.getValue(FACING) == EnumFacing.DOWN) minY = 0.0F;
		if (state.getValue(FACING) == EnumFacing.SOUTH) maxZ = 1.0F;
		if (state.getValue(FACING) == EnumFacing.NORTH) minZ = 0.0F;
		if (state.getValue(FACING) == EnumFacing.WEST) minX = 0.0F;
		if (state.getValue(FACING) == EnumFacing.EAST) maxX = 1.0F;

		if (isSideConnectable (world, pos, EnumFacing.UP)) maxY = 1.0F;
		if (isSideConnectable (world, pos, EnumFacing.DOWN)) minY = 0.0F;
		if (isSideConnectable (world, pos, EnumFacing.SOUTH)) maxZ = 1.0F;
		if (isSideConnectable (world, pos, EnumFacing.NORTH)) minZ = 0.0F;
		if (isSideConnectable (world, pos, EnumFacing.WEST)) minX = 0.0F;
		if (isSideConnectable (world, pos, EnumFacing.EAST)) maxX = 1.0F;
		return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		float minX = 0.3125F, minY = 0.3125F, minZ = 0.3125F;
		float maxX = 0.6875F, maxY = 0.6875F, maxZ = 0.6875F;
		if (state.getValue(FACING) == EnumFacing.UP) maxY = 1.0F;
		if (state.getValue(FACING) == EnumFacing.DOWN) minY = 0.0F;
		if (state.getValue(FACING) == EnumFacing.SOUTH) maxZ = 1.0F;
		if (state.getValue(FACING) == EnumFacing.NORTH) minZ = 0.0F;
		if (state.getValue(FACING) == EnumFacing.WEST) minX = 0.0F;
		if (state.getValue(FACING) == EnumFacing.EAST) maxX = 1.0F;

		if (isSideConnectable (world, pos, EnumFacing.UP)) maxY = 1.0F;
		if (isSideConnectable (world, pos, EnumFacing.DOWN)) minY = 0.0F;
		if (isSideConnectable (world, pos, EnumFacing.SOUTH)) maxZ = 1.0F;
		if (isSideConnectable (world, pos, EnumFacing.NORTH)) minZ = 0.0F;
		if (isSideConnectable (world, pos, EnumFacing.WEST)) minX = 0.0F;
		if (isSideConnectable (world, pos, EnumFacing.EAST)) maxX = 1.0F;
		return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;
		meta = meta | ((EnumFacing) state.getValue(FACING)).getIndex();

		return meta;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, getFacingFromEntity(pos, placer));
	}

	public static EnumFacing getFacingFromEntity(BlockPos pos, EntityLivingBase entity) {
		if (MathHelper.abs((float) entity.posX - (float) pos.getX()) < 2.0F && MathHelper.abs((float) entity.posZ - (float) pos.getZ()) < 2.0F) {
			double eyeHeight = entity.posY + (double) entity.getEyeHeight();
			if (eyeHeight - (double) pos.getY() > 2.0D)
				return EnumFacing.DOWN;
			if ((double) pos.getY() - eyeHeight > 0.0D)
				return EnumFacing.UP;
		}
		return entity.getHorizontalFacing();
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, CONNECTED_DOWN, CONNECTED_UP, CONNECTED_NORTH, CONNECTED_SOUTH, CONNECTED_WEST, CONNECTED_EAST });
	}

    @Override
    public IBlockState getActualState (IBlockState state, IBlockAccess world, BlockPos position) {
		if (state.getValue(FACING) == EnumFacing.UP)
			return state.withProperty(CONNECTED_DOWN, this.isSideConnectable(world, position, EnumFacing.DOWN)).withProperty(CONNECTED_EAST, this.isSideConnectable(world, position, EnumFacing.EAST)).withProperty(CONNECTED_NORTH, this.isSideConnectable(world, position, EnumFacing.NORTH)).withProperty(CONNECTED_SOUTH, this.isSideConnectable(world, position, EnumFacing.SOUTH)).withProperty(CONNECTED_UP, false).withProperty(CONNECTED_WEST, this.isSideConnectable(world, position, EnumFacing.WEST));
		if (state.getValue(FACING) == EnumFacing.DOWN)
			return state.withProperty(CONNECTED_DOWN, false).withProperty(CONNECTED_EAST, this.isSideConnectable(world, position, EnumFacing.EAST)).withProperty(CONNECTED_NORTH, this.isSideConnectable(world, position, EnumFacing.NORTH)).withProperty(CONNECTED_SOUTH, this.isSideConnectable(world, position, EnumFacing.SOUTH)).withProperty(CONNECTED_UP, this.isSideConnectable(world, position, EnumFacing.UP)).withProperty(CONNECTED_WEST, this.isSideConnectable(world, position, EnumFacing.WEST));
		if (state.getValue(FACING) == EnumFacing.SOUTH)
			return state.withProperty(CONNECTED_DOWN, this.isSideConnectable(world, position, EnumFacing.DOWN)).withProperty(CONNECTED_EAST, this.isSideConnectable(world, position, EnumFacing.EAST)).withProperty(CONNECTED_NORTH, this.isSideConnectable(world, position, EnumFacing.NORTH)).withProperty(CONNECTED_SOUTH, false).withProperty(CONNECTED_UP, this.isSideConnectable(world, position, EnumFacing.UP)).withProperty(CONNECTED_WEST, this.isSideConnectable(world, position, EnumFacing.WEST));
		if (state.getValue(FACING) == EnumFacing.NORTH)
			return state.withProperty(CONNECTED_DOWN, this.isSideConnectable(world, position, EnumFacing.DOWN)).withProperty(CONNECTED_EAST, this.isSideConnectable(world, position, EnumFacing.EAST)).withProperty(CONNECTED_NORTH, false).withProperty(CONNECTED_SOUTH, this.isSideConnectable(world, position, EnumFacing.SOUTH)).withProperty(CONNECTED_UP, this.isSideConnectable(world, position, EnumFacing.UP)).withProperty(CONNECTED_WEST, this.isSideConnectable(world, position, EnumFacing.WEST));
		if (state.getValue(FACING) == EnumFacing.WEST)
			return state.withProperty(CONNECTED_DOWN, this.isSideConnectable(world, position, EnumFacing.DOWN)).withProperty(CONNECTED_EAST, this.isSideConnectable(world, position, EnumFacing.EAST)).withProperty(CONNECTED_NORTH, this.isSideConnectable(world, position, EnumFacing.NORTH)).withProperty(CONNECTED_SOUTH, this.isSideConnectable(world, position, EnumFacing.SOUTH)).withProperty(CONNECTED_UP, this.isSideConnectable(world, position, EnumFacing.UP)).withProperty(CONNECTED_WEST, false);
		if (state.getValue(FACING) == EnumFacing.EAST)
			return state.withProperty(CONNECTED_DOWN, this.isSideConnectable(world, position, EnumFacing.DOWN)).withProperty(CONNECTED_EAST, false).withProperty(CONNECTED_NORTH, this.isSideConnectable(world, position, EnumFacing.NORTH)).withProperty(CONNECTED_SOUTH, this.isSideConnectable(world, position, EnumFacing.SOUTH)).withProperty(CONNECTED_UP, this.isSideConnectable(world, position, EnumFacing.UP)).withProperty(CONNECTED_WEST, this.isSideConnectable(world, position, EnumFacing.WEST));

        return state.withProperty(CONNECTED_DOWN, this.isSideConnectable(world, position, EnumFacing.DOWN)).withProperty(CONNECTED_EAST, this.isSideConnectable(world, position, EnumFacing.EAST)).withProperty(CONNECTED_NORTH, this.isSideConnectable(world, position, EnumFacing.NORTH)).withProperty(CONNECTED_SOUTH, this.isSideConnectable(world, position, EnumFacing.SOUTH)).withProperty(CONNECTED_UP, this.isSideConnectable(world, position, EnumFacing.UP)).withProperty(CONNECTED_WEST, this.isSideConnectable(world, position, EnumFacing.WEST));
    }

    private boolean isSideConnectable (IBlockAccess world, BlockPos pos, EnumFacing side) {
    	final IBlockState stateConnection = world.getBlockState(pos.offset(side));
        return (stateConnection == null) ? false : getFluidHandler(world, pos.offset(side)) != null;
    }

    @Nullable
	private IFluidHandler getFluidHandler(IBlockAccess world, BlockPos pos) {
    	TileEntity tile = world.getTileEntity(pos);
    	if(tile == null)
    		return null;
		return tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
    }
    
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBambooPipe();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		} else {
			ItemStack stack = player.getHeldItem(hand);
			if (!stack.isEmpty() && stack.getItem() == ModItems.MATERIALS && stack.getItemDamage() == ItemMaterials.EnumErebusMaterialsType.BAMBOO_PIPE_WRENCH.ordinal()) {
				if (!player.isSneaking()) {
					state = state.cycleProperty(FACING);
					state.cycleProperty(FACING);
					world.setBlockState(pos, state, 3);
					return true;
				}
				else {
					breakBlock(world, pos, state);
					dropBlockAsItem(world, pos, state, 0);
					world.setBlockToAir(pos);
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public ItemBlock getItemBlock() {
		ItemBlock PIPE_ITEM = new ItemBlock(this) {
			@Override
			@SideOnly(Side.CLIENT)
			public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
				list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.bamboopipe").getFormattedText());
			}
		};
		return PIPE_ITEM;
	}
}
