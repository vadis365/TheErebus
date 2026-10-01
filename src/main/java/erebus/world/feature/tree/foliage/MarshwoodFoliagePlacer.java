package erebus.world.feature.tree.foliage;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.block.plants.DarkFruitVineBlock;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.tree.ModFoliagePlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;


public class MarshwoodFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<MarshwoodFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance -> foliagePlacerParts(instance).apply(instance, MarshwoodFoliagePlacer::new));

    public MarshwoodFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    public static FoliageAttachment hangerAttachment(BlockPos pos) {
        return new FoliageAttachment(pos.immutable(), -1, false);
    }

    public static boolean isHanger(FoliageAttachment attachment) {
        return attachment.radiusOffset() == -1;
    }

    private static void placeHanger(WorldGenLevel level, FoliageSetter setter, RandomSource random, BlockPos origin) {
        if (random.nextInt(4) == 0) return;
        int length = random.nextInt(13) + 4;
        var vine = ModBlocks.DARK_FRUIT_VINE.get().defaultBlockState().setValue(DarkFruitVineBlock.AGE, 4);
        for (int i = 0; i < length; i++) {
            BlockPos target = origin.below(i);
            if (level.isOutsideBuildHeight(target) || !level.getBlockState(target).isAir()) break;
            setter.set(target, vine);
        }
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return ModFoliagePlacers.MARSHWOOD_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(@NotNull WorldGenLevel level, @NotNull FoliageSetter setter, @NotNull RandomSource random, @NotNull TreeConfiguration config, int maxHeight, @NotNull FoliageAttachment attachment, int height, int radius, int offset) {
        BlockPos pos = attachment.pos();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        if (isHanger(attachment)) {
            placeHanger(level, setter, random, pos);
            return;
        }

        for (int xOff = x - radius; xOff <= x + radius; xOff++) {
            for (int zOff = z - radius; zOff <= z + radius; zOff++) {
                for (int yOff = y; yOff > y - height; yOff--) {
                    double dSq = Math.pow(xOff - x, 2) + Math.pow(zOff - z, 2) + Math.pow(yOff - y, 2);
                    long rounded = Math.round(Math.sqrt(dSq));
                    if (rounded <= radius) {
                        if (rounded == 0) {
                            setter.set(new BlockPos(xOff, yOff, zOff), ModBlocks.LOG_MARSHWOOD.get().defaultBlockState());
                        } else {
                            tryPlaceLeaf(level, setter, random, config, new BlockPos(xOff, yOff, zOff));
                        }
                    }

                    if (rounded == 0) {
                        if (tryPlaceLeaf(level, setter, random, config, new BlockPos(xOff, yOff - 2, zOff))) {
                            placeHanger(level, setter, random, new BlockPos(xOff, yOff - 3, zOff));
                        }
                    }
                }
            }
        }
    }

    @Override
    public int foliageHeight(@NotNull RandomSource random, int i, @NotNull TreeConfiguration config) {
        return 3;
    }

    @Override
    protected boolean shouldSkipLocation(@NotNull RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return localX == range && localZ == range && (random.nextInt(2) == 0 || localY == 0);
    }
}
