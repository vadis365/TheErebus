package erebus.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jspecify.annotations.NonNull;

public class BarkLogBlock extends RotatedPillarBlock {
    public static final BooleanProperty ALL_BARK = BooleanProperty.create("all_bark");

    public BarkLogBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(ALL_BARK, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ALL_BARK);
    }
}
