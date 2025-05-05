package erebus.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ModCropBlock extends CropBlock {
    private final Supplier<? extends Item> SEED;
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(0.0F, 0.0F, 0.0F, 16.0F, 2.0F, 16.0F),
            Block.box(0.0F, 0.0F, 0.0F, 16.0F, 4.0F, 16.0F),
            Block.box(0.0F, 0.0F, 0.0F, 16.0F, 6.0F, 16.0F),
            Block.box(0.0F, 0.0F, 0.0F, 16.0F, 8.0F, 16.0F)
    };

    public ModCropBlock(Properties properties, Supplier<? extends Item> seed) {
        super(properties);
        SEED = seed;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return SEED.get();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE_BY_AGE[getAge(state)];
    }
}
