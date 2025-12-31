package erebus.block.plants;

import com.mojang.serialization.MapCodec;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class DarkFruitVineBlock extends BushBlock {
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 6);
    public static final MapCodec<DarkFruitVineBlock> CODEC = simpleCodec(DarkFruitVineBlock::new);
    /*private static final VoxelShape AGE_0 = Block.box(0.375D, 0.875D, 0.375D, 0.625D, 1.0D, 0.625D);
    private static final VoxelShape AGE_1 = Block.box(0.1875D, 0.625D, 0.1875D, 0.8125D, 1.0D, 0.8125D);
    private static final VoxelShape AGE_2 = Block.box(0.1875D, 0.375D, 0.1875D, 0.8125D, 1.0D, 0.8125D);
    private static final VoxelShape AGE_3 = Block.box(0.1875D, 0.1875D, 0.1875D, 0.8125D, 1.0D, 0.8125D);
    private static final VoxelShape AGE_4 = Block.box(0.1875D, 0.0D, 0.1875D, 0.8125D, 1.0D, 0.8125D);
    private static final VoxelShape AGE_5 = Block.box(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
    private static final VoxelShape AGE_6 = Block.box(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D);*/

    public DarkFruitVineBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(AGE, 0));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int age = state.getValue(AGE);

        if (age == 5) {
            ItemStack stack = new ItemStack(ModItems.DARK_FRUIT.get());
            popResource(level, pos, stack);
            level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.5F, 2.0F);
            level.setBlockAndUpdate(pos, state.setValue(AGE, 4));
            return InteractionResult.SUCCESS;
        }

        if (age == 6) {
            ItemStack stack = new ItemStack(ModItems.DARK_FRUIT_SEEDS.get());
            popResource(level, pos, stack);
            level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.5F, 2.0F);
            level.setBlockAndUpdate(pos, state.setValue(AGE, 4));
            return InteractionResult.SUCCESS;
        }

        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return isValidBlock(state) && canSurvive(state, (LevelReader) level, pos);
    }

    private boolean isValidBlock(BlockState state) {
        //noinspection SuspiciousMethodCalls
        return !state.getProperties().contains(BlockBehaviour.Properties.of().noCollision()) || state.is(this);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return isValidBlock(level.getBlockState(pos.above()));
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int age = state.getValue(AGE);

        if (level.getBlockState(pos.below()).is(Blocks.AIR) && canSurvive(state, level, pos.below())) {
            if (age == 6) level.setBlockAndUpdate(pos.below(), state.setValue(AGE, 0));
        }

        if (age == 0) level.setBlockAndUpdate(pos, state.setValue(AGE, 1));
        if (age == 1) level.setBlockAndUpdate(pos, state.setValue(AGE, 2));
        if (age == 2) level.setBlockAndUpdate(pos, state.setValue(AGE, 3));
        if (age == 3) level.setBlockAndUpdate(pos, state.setValue(AGE, 4));

        if (age == 4 && random.nextInt(50) == 0) level.setBlockAndUpdate(pos, state.setValue(AGE, 5));
        if (age == 5 && random.nextInt(10) == 0) level.setBlockAndUpdate(pos, state.setValue(AGE, 6));
        if (age == 6 && random.nextInt(10) == 0) level.setBlockAndUpdate(pos, state.setValue(AGE, 4));
    }

    /*@Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        VoxelShape shape = Shapes.empty();
        shape = switch (state.getValue(AGE)) {
            case 0 -> AGE_0;
            case 1 -> AGE_1;
            case 2 -> AGE_2;
            case 3 -> AGE_3;
            case 4 -> AGE_4;
            case 5 -> AGE_5;
            case 6 -> AGE_6;
            default -> shape;
        };

        return shape.isEmpty() ? Shapes.block() : shape;
    }*/

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected @NotNull MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }
}
