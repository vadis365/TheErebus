package erebus.block.bamboo;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModBlocks;
import erebus.ModBlocks.IHasCustomItem;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.items.ItemMaterials;
import erebus.tileentity.TileEntityBambooPipeExtract;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
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

public class BlockBambooPipeExtract extends BlockDirectional implements ITileEntityProvider, IHasCustomItem {
    public static final PropertyBool CONNECTED_DOWN = PropertyBool.create("connected_down");
    public static final PropertyBool CONNECTED_UP = PropertyBool.create("connected_up");
    public static final PropertyBool CONNECTED_NORTH = PropertyBool.create("connected_north");
    public static final PropertyBool CONNECTED_SOUTH = PropertyBool.create("connected_south");
    public static final PropertyBool CONNECTED_WEST = PropertyBool.create("connected_west");
    public static final PropertyBool CONNECTED_EAST = PropertyBool.create("connected_east");

	public BlockBambooPipeExtract() {
		super(Material.WOOD);
		setDefaultState(this.getBlockState().getBaseState().withProperty(CONNECTED_DOWN, Boolean.FALSE).withProperty(CONNECTED_EAST, Boolean.FALSE).withProperty(CONNECTED_NORTH, Boolean.FALSE).withProperty(CONNECTED_SOUTH, Boolean.FALSE).withProperty(CONNECTED_UP, Boolean.FALSE).withProperty(CONNECTED_WEST, Boolean.FALSE));
		setHardness(1.5F);
		setSoundType(SoundType.WOOD);
		setCreativeTab(ModTabs.BLOCKS);
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
    public IBlockState getActualState (IBlockState state, IBlockAccess world, BlockPos position) {
        return state.withProperty(CONNECTED_DOWN, this.isSideConnectable(world, position, EnumFacing.DOWN)).withProperty(CONNECTED_EAST, this.isSideConnectable(world, position, EnumFacing.EAST)).withProperty(CONNECTED_NORTH, this.isSideConnectable(world, position, EnumFacing.NORTH)).withProperty(CONNECTED_SOUTH, this.isSideConnectable(world, position, EnumFacing.SOUTH)).withProperty(CONNECTED_UP, this.isSideConnectable(world, position, EnumFacing.UP)).withProperty(CONNECTED_WEST, this.isSideConnectable(world, position, EnumFacing.WEST));
    }

    private boolean isSideConnectable (IBlockAccess world, BlockPos pos, EnumFacing side) {
    	IBlockState state = world.getBlockState(pos);
    	final IBlockState stateConnection = world.getBlockState(pos.offset(side));
        return stateConnection == null ? false : getFluidHandler(world, pos.offset(side)) != null;
    }

    @Nullable
	private IFluidHandler getFluidHandler(IBlockAccess world, BlockPos pos) {
    	TileEntity tile = world.getTileEntity(pos);
    	if(tile == null)
    		return null;
		return tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
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
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		} else {
			ItemStack stack = player.getHeldItem(hand);
			if (!stack.isEmpty() && stack.getItem() == ModItems.MATERIALS && stack.getItemDamage() == ItemMaterials.EnumErebusMaterialsType.BAMBOO_PIPE_WRENCH.ordinal()) {
				state = state.cycleProperty(FACING);
				state.cycleProperty(FACING);
				world.setBlockState(pos, state, 3);
			}
			else {
				IBlockState activeState = ModBlocks.BAMBOO_PIPE_EXTRACT_ACTIVE.getDefaultState().withProperty(BlockBambooPipeExtractActive.FACING, state.getValue(FACING));
				world.setBlockState(pos, activeState, 3);
				world.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, 0.6F);
			}
			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBambooPipeExtract();
	}

	@Override
	public ItemBlock getItemBlock() {
		ItemBlock EXTRACT_PIPE_ITEM = new ItemBlock(this) {
			@Override
			@SideOnly(Side.CLIENT)
			public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
				list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.bambooPipeExtract").getFormattedText());
			}
		};
		return EXTRACT_PIPE_ITEM;
	}

}
