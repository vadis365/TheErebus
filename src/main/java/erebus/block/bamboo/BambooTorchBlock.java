package erebus.block.bamboo;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class BambooTorchBlock extends Block {

    public static final EnumProperty<EnumTorchBlockHalf> HALF = EnumProperty.create("half", EnumTorchBlockHalf.class);
    protected static final VoxelShape TORCH = Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);

    public BambooTorchBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(HALF, EnumTorchBlockHalf.UPPER));
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return TORCH;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF);
    }
}
