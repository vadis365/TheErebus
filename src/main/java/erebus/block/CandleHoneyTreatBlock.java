package erebus.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
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

import java.util.Map;

public class CandleHoneyTreatBlock extends AbstractCandleBlock {
    public static final MapCodec<CandleHoneyTreatBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(BuiltInRegistries.BLOCK.byNameCodec().fieldOf("candle").forGetter((candleHoneyTreatBlock) -> candleHoneyTreatBlock.candleBlock)).apply(instance, CandleHoneyTreatBlock::new));
    public static final BooleanProperty LIT = AbstractCandleBlock.LIT;
    protected static final float AABB_OFFSET = 1.0F;
    protected static final VoxelShape HONEY_TREAT_SHAPE = Block.box(1.0F, 0.0F, 1.0F, 15.0F, 8.0F, 15.0F);
    protected static final VoxelShape CANDLE_SHAPE = Block.box(7.0F, 8.0F, 7.0F, 9.0F, 14.0F, 9.0F);
    protected static final VoxelShape SHAPE = Shapes.or(HONEY_TREAT_SHAPE, CANDLE_SHAPE);
    private static final Map<CandleBlock, CandleHoneyTreatBlock> BY_CANDLE = Maps.newHashMap();
    private static final Iterable<Vec3> PARTICLE_OFFSETS = ImmutableList.of(new Vec3(0.5F, 1.0F, 0.5F));
    private final CandleBlock candleBlock;

    @Override
    protected MapCodec<? extends AbstractCandleBlock> codec() {
        return CODEC;
    }

    public CandleHoneyTreatBlock(Block candleBlock) {
        super(Properties.ofFullCopy(ModBlocks.HONEY_TREAT.get()));
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
    protected Iterable<Vec3> getParticleOffsets(BlockState blockState) {
        return PARTICLE_OFFSETS;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
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
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
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
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack(ModBlocks.HONEY_TREAT);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isSolid();
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return HoneyTreatBlock.FULL_TREAT_SIGNAL;
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    public static BlockState byCandle(CandleBlock candle) {
        return BY_CANDLE.get(candle).defaultBlockState();
    }

    public static boolean canLight(BlockState state) {
        return state.is(BlockTags.CANDLE_CAKES, treat -> treat.hasProperty(LIT) && !state.getValue(LIT));
    }
}
