package erebus.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
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
import org.jspecify.annotations.NonNull;

import java.util.Map;

public class CandleHoneyTreatBlock extends AbstractCandleBlock {
    public static final MapCodec<CandleHoneyTreatBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(BuiltInRegistries.BLOCK.byNameCodec().fieldOf("candle").forGetter((candleHoneyTreatBlock) -> candleHoneyTreatBlock.candleBlock), propertiesCodec()).apply(instance, CandleHoneyTreatBlock::new));
    public static final BooleanProperty LIT = AbstractCandleBlock.LIT;
    protected static final VoxelShape HONEY_TREAT_SHAPE = Block.box(1.0F, 0.0F, 1.0F, 15.0F, 8.0F, 15.0F);
    protected static final VoxelShape CANDLE_SHAPE = Block.box(7.0F, 8.0F, 7.0F, 9.0F, 14.0F, 9.0F);
    protected static final VoxelShape SHAPE = Shapes.or(HONEY_TREAT_SHAPE, CANDLE_SHAPE);
    private static final Map<CandleBlock, CandleHoneyTreatBlock> BY_CANDLE = Maps.newHashMap();
    private static final Iterable<Vec3> PARTICLE_OFFSETS = ImmutableList.of(new Vec3(0.5F, 1.0F, 0.5F));
    private final CandleBlock candleBlock;

    public CandleHoneyTreatBlock(Block candleBlock, Properties properties) {
        super(properties);
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
    protected @NonNull MapCodec<? extends AbstractCandleBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NonNull Iterable<Vec3> getParticleOffsets(@NonNull BlockState blockState) {
        return PARTICLE_OFFSETS;
    }

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected @NonNull InteractionResult useItemOn(ItemStack stack, @NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hitResult) {
        if(!stack.is(Items.FLINT_AND_STEEL) && !stack.is(Items.FIRE_CHARGE)) {
            if(candleHit(hitResult) && stack.isEmpty() && state.getValue(LIT)) {
                extinguish(player, state, level, pos);
                return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
            } else {
                return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
            }
        } else {
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    protected @NonNull InteractionResult useWithoutItem(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull BlockHitResult hitResult) {
        InteractionResult result = HoneyTreatBlock.eat(level, pos, ModBlocks.HONEY_TREAT.get().defaultBlockState(), player);
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
    public @NonNull ItemStack getCloneItemStack(@NonNull LevelReader level, @NonNull BlockPos pos, @NonNull BlockState state, boolean includeData) {
        return new ItemStack(ModBlocks.HONEY_TREAT);
    }

    @Override
    protected @NonNull BlockState updateShape(@NonNull BlockState state, @NonNull LevelReader level, @NonNull ScheduledTickAccess ticks, @NonNull BlockPos pos, @NonNull Direction direction, @NonNull BlockPos neighborPos, @NonNull BlockState neighborState, @NonNull RandomSource random) {
        return direction == Direction.DOWN && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, ticks, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected boolean canSurvive(@NonNull BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isSolid();
    }

    @Override
    protected int getAnalogOutputSignal(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Direction direction) {
        return HoneyTreatBlock.FULL_TREAT_SIGNAL;
    }

    @Override
    protected boolean hasAnalogOutputSignal(@NonNull BlockState state) {
        return true;
    }

    @Override
    protected boolean isPathfindable(@NonNull BlockState state, @NonNull PathComputationType pathComputationType) {
        return false;
    }

    public static BlockState byCandle(CandleBlock candle) {
        return BY_CANDLE.get(candle).defaultBlockState();
    }
}
