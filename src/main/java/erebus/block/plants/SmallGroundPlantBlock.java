package erebus.block.plants;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public class SmallGroundPlantBlock extends VegetationBlock {
    public static final MapCodec<SmallGroundPlantBlock> CODEC = simpleCodec(SmallGroundPlantBlock::new);
    private static final VoxelShape SHAPE = Block.column(9.6, 0.0, 9.6);

    public SmallGroundPlantBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull MapCodec<? extends SmallGroundPlantBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPE.move(state.getOffset(pos));
    }
}
