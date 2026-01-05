package erebus.world.feature.tree.decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import erebus.block.plants.DarkFruitVineBlock;
import erebus.registries.world.tree.ModTreeDecorators;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.jetbrains.annotations.NotNull;

public class LeaveDarkFruitVineDecorator extends TreeDecorator {
    public static final MapCodec<LeaveDarkFruitVineDecorator> CODEC = Codec.floatRange(0.0F, 1.0F)
            .fieldOf("probability")
            .xmap(LeaveDarkFruitVineDecorator::new, decorator -> decorator.probability);

    private final float probability;

    public LeaveDarkFruitVineDecorator(float probability) {
        this.probability = probability;
    }

    public static MapCodec<LeaveDarkFruitVineDecorator> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull TreeDecoratorType<?> type() {
        return ModTreeDecorators.LEAVE_DARK_FRUIT_VINE_DECORATOR.get();
    }

    @Override
    public void place(Context context) {
        RandomSource random = context.random();
        context.leaves().forEach(leaf -> {
            if (random.nextFloat() < probability) {
                BlockPos below = leaf.below();
                int length = random.nextInt(13) + 4;

                for (int yOffset = 0; yOffset < length; yOffset++) {
                    BlockPos check = below.below(yOffset);
                    if (context.isAir(check)) {
                        context.setBlock(check, ModBlocks.DARK_FRUIT_VINE.get().defaultBlockState().setValue(DarkFruitVineBlock.AGE, 4));
                    }
                }
            }
        });
    }
}
