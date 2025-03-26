package erebus.world.tree.decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import erebus.registries.ModBlocks;
import erebus.registries.world.ModTreeDecorators;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class LeaveThornDecorator extends TreeDecorator {
    public static final MapCodec<LeaveThornDecorator> CODEC = Codec
            .floatRange(0.0F, 1.0F)
            .fieldOf("probability")
            .xmap(LeaveThornDecorator::new, thorn -> thorn.probability);
    private final float probability;

    public LeaveThornDecorator(float probability) {
        this.probability = probability;
    }

    private static void addHangingVine(BlockPos pos, BooleanProperty sideProperty, TreeDecorator.Context context) {
        placeThorn(context, pos, sideProperty);
        int i = 4;

        for (BlockPos blockpos = pos.below(); context.isAir(blockpos) && i > 0; --i) {
            placeThorn(context, blockpos, sideProperty);
            blockpos = blockpos.below();
        }
    }

    public static void placeThorn(Context context, BlockPos pos, BooleanProperty sideProperty) {
        context.setBlock(pos, ModBlocks.THORNS.get().defaultBlockState().setValue(sideProperty, true));
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecorators.LEAVE_THORN_DECORATOR.get();
    }

    @Override
    public void place(TreeDecorator.Context context) {
        RandomSource randomsource = context.random();
        context.leaves().forEach((p_226035_) -> {
            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos = p_226035_.west();
                if (context.isAir(blockpos)) {
                    addHangingVine(blockpos, VineBlock.EAST, context);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos1 = p_226035_.east();
                if (context.isAir(blockpos1)) {
                    addHangingVine(blockpos1, VineBlock.WEST, context);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos2 = p_226035_.north();
                if (context.isAir(blockpos2)) {
                    addHangingVine(blockpos2, VineBlock.SOUTH, context);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos3 = p_226035_.south();
                if (context.isAir(blockpos3)) {
                    addHangingVine(blockpos3, VineBlock.NORTH, context);
                }
            }

        });
    }
}
