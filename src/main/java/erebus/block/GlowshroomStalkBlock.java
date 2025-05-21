package erebus.block;

import erebus.block.types.EnumPartType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class GlowshroomStalkBlock extends Block {

    public static final EnumProperty<EnumPartType> PART = EnumProperty.create("part", EnumPartType.class);

    public GlowshroomStalkBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(PART, EnumPartType.MAIN));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PART);
    }
}
