package erebus.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

import java.util.function.Supplier;

public class ModBerryBushBlock extends BushBlock implements BonemealableBlock {
    public static final MapCodec<ModBerryBushBlock> CODEC = simpleCodec(ModBerryBushBlock::new);

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);
    private static final VoxelShape SAPLING_SHAPE = Block.box(3.0F, 0.0F, 3.0F, 13.0F, 8.0F, 13.0F);
    private static final VoxelShape MID_GROWTH_SHAPE = Block.box(1.0F, 0.0F, 1.0F, 15.0F, 16.0F, 15.0F);

    public ModBerryBushBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(AGE, 0));
    }

    public ModBerryBushBlock(Supplier<? extends Item> berry, Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }


    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return currentAge(state) < 3;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int age = Math.min(3, currentAge(state) + 1);
        level.setBlock(pos, state.setValue(AGE, age), 2);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return currentAge(state) < 3;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(currentAge(state) < 3 && level.getRawBrightness(pos.above(), 0) >= 9 && CommonHooks.canCropGrow(level, pos, state, random.nextInt(5) == 0)) {
            BlockState growthAge = state.setValue(AGE, currentAge(state) + 1);
            level.setBlock(pos, growthAge, 2);
            CommonHooks.fireCropGrowPost(level, pos, state);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(growthAge));
        }
    }

    private int currentAge(BlockState state) {
        return state.getValue(AGE);
    }
}
