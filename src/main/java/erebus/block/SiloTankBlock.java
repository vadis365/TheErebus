package erebus.block;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.NotNull;

import com.mojang.serialization.MapCodec;

import erebus.block.entity.SiloTankBlockEntity;
import erebus.registries.ModItems;
import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SiloTankBlock extends Block implements EntityBlock {
	
	public static final MapCodec<SiloTankBlock> CODEC = simpleCodec(SiloTankBlock::new);
	protected static final VoxelShape SILO_TANK_AABB = Block.box(2D, 0D, 2D, 14D, 16D, 14D);
	public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
	
	public SiloTankBlock(Properties properties) {
		super(properties);
		registerDefaultState(this.stateDefinition.any().setValue(ACTIVE, false));
	}
	
	@Override
	protected @NotNull MapCodec<SiloTankBlock> codec() {
		return CODEC;
	}

	@Nonnull
	@Override
	public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		return SILO_TANK_AABB;
	}

    @Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new SiloTankBlockEntity(pos, state);
	}
    
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(ACTIVE, false);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ACTIVE);
	}
	
	@Override
	protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
		boolean canSurvive = false;
		if (canSurvive(state, level, pos))
			canSurvive = true;
		return !canSurvive ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, pos, facingPos);
	}
	
	@Override
	public void neighborChanged(@NotNull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Block block, @Nonnull BlockPos fromPos, boolean isMoving) {
		if (!level.isClientSide()) {
			if (isSiloComplete(level, pos))
				level.setBlock(pos, this.defaultBlockState().setValue(ACTIVE, true), 3);
			else
				level.setBlock(pos, this.defaultBlockState().setValue(ACTIVE, false), 3);
			}
	}

	public boolean isSiloComplete(Level level, BlockPos pos) {
		BlockState supports = level.getBlockState(pos.below());
		BlockState redGem = level.getBlockState(pos.below(2));
		BlockState roof = level.getBlockState(pos.above());
		return supports.is(OtherBlocks.SILO_SUPPORTS.get()) && redGem.is(OtherBlocks.RED_GEM_BLOCK.get()) && roof.is(OtherBlocks.SILO_ROOF.get());
	}

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return level.getBlockState(pos.below()).is(OtherBlocks.SILO_SUPPORTS.get());
	}

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.MODEL;
	}

    @Override
	public @NotNull ItemInteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
    	BlockEntity blockEntity = level.getBlockEntity(pos);
    	if (level.isClientSide()) {
			return ItemInteractionResult.SUCCESS;
    	} else if (blockEntity instanceof SiloTankBlockEntity siloTank) {
			if (!stack.isEmpty() && stack.is(OtherBlocks.SILO_ROOF.asItem()) || !stack.isEmpty() && stack.getItem() == ModItems.ANT_TAMING_AMULET.get())
				return ItemInteractionResult.FAIL;
			player.openMenu(siloTank, pos);
		}
    	return ItemInteractionResult.SUCCESS;
	}

	@Override
	public void onRemove(@NotNull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
		SiloTankBlockEntity tile = (SiloTankBlockEntity) level.getBlockEntity(pos);
		if (tile != null)
			Containers.dropContents(level, pos, tile);
		level.levelEvent(2001, pos, Block.getId(state));
		super.onRemove(state, level, pos, newState, isMoving);
	}
}