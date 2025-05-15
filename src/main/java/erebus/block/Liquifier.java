package erebus.block;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import erebus.block.entity.LiquifierBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class Liquifier extends HorizontalDirectionalBlock implements EntityBlock {

	public static final MapCodec<Liquifier> CODEC = simpleCodec(Liquifier::new);
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");

	public Liquifier(Properties properties) {
		super(properties);
		//super(Material.IRON);
		registerDefaultState(this.stateDefinition.any().setValue(POWERED, false));
		//setHardness(10.0F);
		//setSoundType(SoundType.GLASS);
		//setCreativeTab(ModTabs.BLOCKS);
	}

	@Override
	protected MapCodec<Liquifier> codec() {
		return CODEC;
	}

    @Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new LiquifierBlockEntity(pos, state);
	}

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.MODEL;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, @Nonnull BlockState pState, @Nonnull BlockEntityType<T> pBlockEntityType) {
		return pLevel.isClientSide ? LiquifierBlockEntity::clientTick : LiquifierBlockEntity::serverTick;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction direction = context.getNearestLookingDirection().getOpposite();
		return this.defaultBlockState().setValue(FACING, direction).setValue(POWERED, false);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, POWERED);
	}

/*
	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (!world.isRemote && !player.capabilities.isCreativeMode) {
			TileEntityLiquifier tile = (TileEntityLiquifier) world.getTileEntity(pos);
			if (tile != null) {
				NBTTagCompound nbt = new NBTTagCompound();
				tile.writeToNBT(nbt);
				ItemStack stack = new ItemStack(Item.getItemFromBlock(this), 1, 0);
				if (tile.tank.getFluidAmount() > 0)
					stack.setTagCompound(nbt);
				InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
				InventoryHelper.dropInventoryItems(world, pos, tile);
				world.removeTileEntity(pos);
			}
		}
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(world, pos, state, placer, stack);
		TileEntityLiquifier tile = (TileEntityLiquifier) world.getTileEntity(pos);
		if (!world.isRemote) {
			if (stack.hasTagCompound()) {
				if (tile != null) {
					if (!stack.getTagCompound().hasKey("Empty")) {
						FluidStack fluid = FluidStack.loadFluidStackFromNBT(stack.getTagCompound());
						tile.tank.fillInternal(fluid, true);
					}
				}
			}

			if (state.getValue(POWERED)) {
				tile.setActive(true);
			}
		}
	}

*/

	@Nonnull
	@Override
	public InteractionResult useWithoutItem(BlockState state, @Nonnull Level world, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull BlockHitResult hitResult) {
		if (!world.isClientSide()) {
			if (world.getBlockEntity(pos) instanceof LiquifierBlockEntity liquifier)
				player.openMenu(liquifier, pos);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void onRemove(BlockState state, @Nonnull Level world, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock())) {
			LiquifierBlockEntity tile = (LiquifierBlockEntity) world.getBlockEntity(pos);
			if (tile != null) {
				Containers.dropContents(world, pos, tile);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}
	
	@Override
	public void neighborChanged(BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Block block, @Nonnull BlockPos fromPos, boolean isMoving) {
		if (!level.isClientSide()) {
			LiquifierBlockEntity tile = (LiquifierBlockEntity) level.getBlockEntity(pos);
			boolean flag = level.hasNeighborSignal(pos);
			if (flag != state.getValue(POWERED)) {
				level.setBlock(pos, state.setValue(POWERED, flag), 3);
				if (tile != null)
					tile.setActive(flag);
			}
		}
	}
	
	@Override
	public void tick(@Nonnull BlockState state, ServerLevel world, @Nonnull BlockPos pos, @Nonnull RandomSource rand) {
		if (!world.isClientSide) {
			boolean flag = !world.hasNeighborSignal(pos);
			if (flag != state.getValue(POWERED))
				world.setBlock(pos, state.setValue(POWERED, flag), 4);
		}
	}
/*
	@Override
	public ItemBlock getItemBlock() {
		ItemBlock LIQUIFIER_ITEM = new ItemBlock(this) {
			@Override
			@SideOnly(Side.CLIENT)
			public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
				if(stack.hasTagCompound() && !stack.getTagCompound().hasKey("Empty")) {
					FluidStack fluid = FluidStack.loadFluidStackFromNBT(stack.getTagCompound());
					if(fluid !=null) {
						list.add(TextFormatting.GREEN + "Contains: "+ fluid.getFluid().getLocalizedName(fluid));
						list.add(TextFormatting.BLUE + ""+ fluid.amount +"Mb");
					}
				}
				else {
					list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.liquifier").getFormattedText());
					list.add(TextFormatting.RED + "It's Empty!");
				}
			}
		};
		return LIQUIFIER_ITEM;
	}
	*/
}
