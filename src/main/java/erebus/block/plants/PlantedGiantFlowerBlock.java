package erebus.block.plants;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.world.feature.plant.config.GiantFlowerFeatureConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public final class PlantedGiantFlowerBlock extends VegetationBlock implements BonemealableBlock {
    public static final MapCodec<PlantedGiantFlowerBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.intRange(0, 14).fieldOf("color").forGetter(block -> block.color), propertiesCodec()
    ).apply(instance, PlantedGiantFlowerBlock::new));

    private static final VoxelShape SHAPE = Block.column(9, 0, 10);
    private final int color;

    public PlantedGiantFlowerBlock(int color, Properties properties) {
        super(properties.randomTicks().lightLevel(state -> 7).offsetType(OffsetType.XZ));
        this.color = color;
    }

    @Override
    protected @NonNull MapCodec<PlantedGiantFlowerBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NonNull VoxelShape getShape(BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPE.move(state.getOffset(pos));
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos) {
        return state.is(Blocks.GRASS_BLOCK) || state.is(BlockTags.DIRT) || state.is(Blocks.PODZOL) || state.is(Blocks.FARMLAND);
    }

    @Override
    protected void randomTick(@NonNull BlockState state, ServerLevel level, BlockPos pos, @NonNull RandomSource random) {
        if (level.getMaxLocalRawBrightness(pos.above()) >= 9 && random.nextInt(7) == 0) performBonemeal(level, random, pos, state);
    }

    @Override
    public boolean isValidBonemealTarget(@NonNull LevelReader level, @NonNull BlockPos pos, @NonNull BlockState state) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(@NonNull Level level, RandomSource random, @NonNull BlockPos pos, @NonNull BlockState state) {
        return random.nextFloat() < 0.4F;
    }

    @Override
    public void performBonemeal(@NonNull ServerLevel level, @NonNull RandomSource random, BlockPos pos, @NonNull BlockState state) {
        for (var check : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 11, 4))) {
            if (!level.isInWorldBounds(check) || !level.getWorldBorder().isWithinBounds(check) || !level.hasChunkAt(check) || (!check.equals(pos) && !level.isEmptyBlock(check))) return;
        }
        int primary = color == 14 ? random.nextInt(14) : color;
        int secondary = color == 14 ? random.nextInt(14) : color;
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
        var feature = new GiantFlowerFeatureConfiguration(primary, secondary);
        if (!feature.place(NoneFeatureConfiguration.INSTANCE, level, level.getChunkSource().getGenerator(), random, pos))
            level.setBlock(pos, state, 3);
    }
}
