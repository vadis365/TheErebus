package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.SiloTankBlockEntity;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class SiloTankBlock extends Block implements EntityBlock {

    public static final MapCodec<SiloTankBlock> CODEC = simpleCodec(SiloTankBlock::new);
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    protected static final VoxelShape SILO_TANK_AABB = Block.box(2D, 0D, 2D, 14D, 16D, 14D);

    public SiloTankBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(ACTIVE, false));
    }

    @Override
    protected @NonNull MapCodec<SiloTankBlock> codec() {
        return CODEC;
    }

    @Override
    public @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter worldIn, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SILO_TANK_AABB;
    }

    @Override
    public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new SiloTankBlockEntity(pos, state);
    }

    @Override
    public BlockState getStateForPlacement(@NonNull BlockPlaceContext context) {
        return this.defaultBlockState().setValue(ACTIVE, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    @Override
    protected @NonNull BlockState updateShape(@NonNull BlockState state, @NonNull LevelReader level, @NonNull ScheduledTickAccess ticks, @NonNull BlockPos pos, @NonNull Direction directionToNeighbour, @NonNull BlockPos neighbourPos, @NonNull BlockState neighbourState, @NonNull RandomSource random) {
        boolean canSurvive = canSurvive(state, level, pos);
        return !canSurvive ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }

    @Override
    public void neighborChanged(@NonNull BlockState state, Level level, @NonNull BlockPos pos, @NonNull Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        if (!level.isClientSide()) {
            boolean complete = isSiloComplete(level, pos);
            if (state.getValue(ACTIVE) && !complete) level.destroyBlock(pos, true);
            else if (state.getValue(ACTIVE) != complete) level.setBlock(pos, state.setValue(ACTIVE, complete), 3);
        }
    }

    public boolean isSiloComplete(LevelReader level, BlockPos pos) {
        BlockState supports = level.getBlockState(pos.below());
        BlockState redGem = level.getBlockState(pos.below(2));
        BlockState roof = level.getBlockState(pos.above());
        return supports.is(ModBlocks.SILO_SUPPORTS.get()) && redGem.is(ModBlocks.RED_GEM_BLOCK.get()) && roof.is(ModBlocks.SILO_ROOF.get());
    }

    @Override
    protected boolean canSurvive(@NonNull BlockState state, LevelReader level, @NonNull BlockPos pos) {
        if (level.getBlockState(pos).is(this) && level.getBlockState(pos).getValue(ACTIVE))
            return isSiloComplete(level, pos);
        return level.getBlockState(pos.below()).is(ModBlocks.SILO_SUPPORTS.get());
    }

    @Override
    public @NonNull RenderShape getRenderShape(@NonNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @NonNull InteractionResult useItemOn(@NonNull ItemStack stack, @NonNull BlockState state, Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hit) {
        if (stack.is(ModBlocks.SILO_ROOF.asItem()) || stack.is(ModItems.ANT_TAMING_AMULET)) return InteractionResult.PASS;
        if (!isSiloComplete(level, pos)) return InteractionResult.PASS;
        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof SiloTankBlockEntity tank) player.openMenu(tank);
        return InteractionResult.SUCCESS;
    }
}
