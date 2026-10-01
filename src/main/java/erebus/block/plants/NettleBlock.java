package erebus.block.plants;

import com.mojang.serialization.MapCodec;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class NettleBlock extends VegetationBlock {
    public static final MapCodec<NettleBlock> CODEC = simpleCodec(NettleBlock::new);

    public NettleBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull MapCodec<NettleBlock> codec() {
        return CODEC;
    }

    @Override
    protected void randomTick(BlockState state, @NonNull ServerLevel level, @NonNull BlockPos pos, @NonNull RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
            return;
        }
        if (random.nextInt(25) != 0) return;
        BlockPos target = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
        random.nextInt(3);
        random.nextInt(2);
        random.nextInt(2);
        random.nextInt(3);
        if (level.isOutsideBuildHeight(target) || !level.isEmptyBlock(target) || !state.canSurvive(level, target)) return;
        if (state.is(ModBlocks.NETTLE_FLOWERED)) {
            level.setBlockAndUpdate(target, ModBlocks.NETTLE.get().defaultBlockState());
        } else if (random.nextInt(3) == 0) {
            level.setBlockAndUpdate(target, ModBlocks.NETTLE_FLOWERED.get().defaultBlockState());
        }
    }
}
