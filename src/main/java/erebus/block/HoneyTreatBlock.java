package erebus.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HoneyTreatBlock extends Block {

    public static MapCodec<HoneyTreatBlock> CODEC = simpleCodec(HoneyTreatBlock::new);
    public static final int MAX_BITES = 6;
    public static final IntegerProperty BITES = BlockStateProperties.BITES;
    public static final int FULL_TREAT_SIGNAL = getOutputSignal(0);
    protected static final float AABB_OFFSET = 1.0F;
    protected static final float AABB_SIZE_PER_BITE = 2.0F;
    protected static final VoxelShape[] SHAPE_BY_BITE = new VoxelShape[]{Block.box(1.0F, 0.0F, 1.0F, 15.0F, 8.0F, 15.0F), Block.box(3.0F, 0.0F, 1.0F, 15.0F, 8.0F, 15.0F), Block.box(5.0F, 0.0F, 1.0F, 15.0F, 8.0F, 15.0F), Block.box(7.0F, 0.0F, 1.0F, 15.0F, 8.0F, 15.0F), Block.box(9.0F, 0.0F, 1.0F, 15.0F, 8.0F, 15.0F), Block.box(11.0F, 0.0F, 1.0F, 15.0F, 8.0F, 15.0F), Block.box(13.0F, 0.0F, 1.0F, 15.0F, 8.0F, 15.0F)};

    public HoneyTreatBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(BITES, 0));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_BITE[state.getValue(BITES)];
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        Item item = stack.getItem();
        if(stack.is(ItemTags.CANDLES) && state.getValue(BITES) == 0) {
            Block block = Block.byItem(item);
            if(block instanceof CandleBlock) {
                CandleBlock candle = (CandleBlock) block;
                stack.consume(1, player);
                level.playSound(null, pos, SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.setBlockAndUpdate(pos, CandleHoneyTreatBlock.byCandle(candle));
                level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                player.awardStat(Stats.ITEM_USED.get(item));
                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected static InteractionResult eat(LevelAccessor level, BlockPos pos, BlockState state, Player player) {
        if (!player.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            player.awardStat(Stats.EAT_CAKE_SLICE);
            player.getFoodData().eat(2, 0.1F);
            int bites = state.getValue(BITES);
            level.gameEvent(player, GameEvent.EAT, pos);

            if(bites < 6) {
                level.setBlock(pos, state.setValue(BITES, bites + 1), 3);
            } else {
                level.removeBlock(pos, false);
                level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return facing == Direction.DOWN && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, neighborState, level, pos, neighborPos);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isSolid();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BITES);
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return getOutputSignal(state.getValue(BITES));
    }

    public static int getOutputSignal(int eaten) {
        return (7 - eaten) * 2;
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }
}
