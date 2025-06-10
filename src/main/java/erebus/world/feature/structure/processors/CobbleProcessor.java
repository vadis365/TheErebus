package erebus.world.feature.structure.processors;

import com.mojang.serialization.MapCodec;
import erebus.registries.world.structure.ModStructureProcessors;
import erebus.world.util.FeatureUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CobbleProcessor extends StructureProcessor {

    public static final CobbleProcessor INSTANCE = new CobbleProcessor();
    public static final MapCodec<CobbleProcessor> CODEC = MapCodec.unit(() -> INSTANCE);

    @Override
    public @Nullable StructureTemplate.StructureBlockInfo process(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockPos piecePos, StructureTemplate.@NotNull StructureBlockInfo info, StructureTemplate.StructureBlockInfo modifiedBlockInfo, StructurePlaceSettings settings, @Nullable StructureTemplate template) {
        RandomSource random = settings.getRandom(modifiedBlockInfo.pos());
        random.setSeed(random.nextLong() * 2);

        BlockState state = modifiedBlockInfo.state();
        Block block = state.getBlock();

        if (block == Blocks.COBBLESTONE && random.nextBoolean())
            return new StructureTemplate.StructureBlockInfo(modifiedBlockInfo.pos(), Blocks.MOSSY_COBBLESTONE.defaultBlockState(), null);

        if (block == Blocks.COBBLESTONE_STAIRS && random.nextBoolean())
            return new StructureTemplate.StructureBlockInfo(modifiedBlockInfo.pos(), FeatureUtils.transferAllStateKeys(state, Blocks.MOSSY_COBBLESTONE_STAIRS), null);

        if (block == Blocks.COBBLESTONE_SLAB && random.nextBoolean())
            return new StructureTemplate.StructureBlockInfo(modifiedBlockInfo.pos(), FeatureUtils.transferAllStateKeys(state, Blocks.MOSSY_COBBLESTONE_SLAB), null);

        if (block == Blocks.COBBLESTONE_WALL && random.nextBoolean())
            return new StructureTemplate.StructureBlockInfo(modifiedBlockInfo.pos(), FeatureUtils.transferAllStateKeys(state, Blocks.MOSSY_COBBLESTONE_WALL), null);

        return modifiedBlockInfo;
    }

    @Override
    protected @NotNull StructureProcessorType<?> getType() {
        return ModStructureProcessors.COBBLE.get();
    }
}
