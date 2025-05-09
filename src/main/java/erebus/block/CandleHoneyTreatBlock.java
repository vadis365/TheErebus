package erebus.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class CandleHoneyTreatBlock extends AbstractCandleBlock {
    public static final MapCodec<CandleHoneyTreatBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(BuiltInRegistries.BLOCK.byNameCodec().fieldOf("candle").forGetter((candleHoneyTreatBlock) -> candleHoneyTreatBlock.candleBlock)).apply(instance, CandleHoneyTreatBlock::new));
    public static final BooleanProperty LIT = AbstractCandleBlock.LIT;
    protected static final VoxelShape HONEY_TREAT_SHAPE = Block.box(1.0F, 0.0F, 1.0F, 15.0F, 8.0F, 15.0F);
    protected static final VoxelShape CANDLE_SHAPE = Block.box(7.0F, 8.0F, 7.0F, 9.0F, 14.0F, 9.0F);
    protected static final VoxelShape SHAPE = Shapes.or(HONEY_TREAT_SHAPE, CANDLE_SHAPE);
    private static final Map<CandleBlock, CandleHoneyTreatBlock> BY_CANDLE = Maps.newHashMap();
    private static final Iterable<Vec3> PARTICLE_OFFSETS = ImmutableList.of(new Vec3(0.5F, 1.0F, 0.5F));
    private final CandleBlock candleBlock;

    public CandleHoneyTreatBlock(Block candleBlock) {
        super(Properties.ofFullCopy(OtherBlocks.HONEY_TREAT.get()));
        registerDefaultState(getStateDefinition().any().setValue(LIT, false));
        if(candleBlock instanceof CandleBlock candleblock) {
            BY_CANDLE.put(candleblock, this);
            this.candleBlock = candleblock;
        } else {
            String className = String.valueOf(CandleBlock.class);
            throw new IllegalArgumentException("Expected block to be of %s was %s".formatted(className, String.valueOf(candleBlock.getClass())));
        }
    }

    @Override
    protected @NotNull MapCodec<? extends AbstractCandleBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull Iterable<Vec3> getParticleOffsets(@NotNull BlockState blockState) {
        return PARTICLE_OFFSETS;
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if(!stack.is(Items.FLINT_AND_STEEL) && !stack.is(Items.FIRE_CHARGE)) {
            if(candleHit(hitResult) && stack.isEmpty() && state.getValue(LIT)) {
                extinguish(player, state, level, pos);
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            } else {
                return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
            }
        } else {
            return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
        }
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        InteractionResult result = HoneyTreatBlock.eat(level, pos, OtherBlocks.HONEY_TREAT.get().defaultBlockState(), player);
        if(result.consumesAction()) dropResources(state, level, pos);
        return result;
    }

    private static boolean candleHit(BlockHitResult hit) {
        return hit.getLocation().y - hit.getBlockPos().getY() > 0.5F;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state) {
        return new ItemStack(OtherBlocks.HONEY_TREAT);
    }

    @Override
    protected @NotNull BlockState updateShape(@NotNull BlockState state, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockPos neighborPos) {
        return direction == Direction.DOWN && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isSolid();
    }

    @Override
    protected int getAnalogOutputSignal(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        return HoneyTreatBlock.FULL_TREAT_SIGNAL;
    }

    @Override
    protected boolean hasAnalogOutputSignal(@NotNull BlockState state) {
        return true;
    }

    @Override
    protected boolean isPathfindable(@NotNull BlockState state, @NotNull PathComputationType pathComputationType) {
        return false;
    }

    public static BlockState byCandle(CandleBlock candle) {
        return BY_CANDLE.get(candle).defaultBlockState();
    }
}
