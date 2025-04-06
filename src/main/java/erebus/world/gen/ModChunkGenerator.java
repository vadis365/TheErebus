package erebus.world.gen;

import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.Erebus;
import erebus.registries.ModBlocks;
import erebus.world.gen.warp.ModNoiseInterpolator;
import erebus.world.gen.warp.NoiseModifier;
import erebus.world.gen.warp.NoiseSlider;
import erebus.world.gen.warp.TerrainWarper;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

public class ModChunkGenerator extends NoiseBasedChunkGenerator {

    public static final ResourceKey<MapCodec<? extends ChunkGenerator>> CHUNK_GENERATOR_KEY = ResourceKey.create(Registries.CHUNK_GENERATOR, Erebus.prefix("chunk_generator"));

    public static final MapCodec<ModChunkGenerator> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BiomeSource.CODEC.fieldOf("biome_source").forGetter(o -> o.biomeSource),
            NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter(o -> o.settings)
    ).apply(instance, instance.stable(ModChunkGenerator::new)));

    protected final Holder<NoiseGeneratorSettings> settings;
    protected final Climate.Sampler sampler;
    protected final TerrainWarper warper;
    private final BlockState defaultBlock;
    private final BlockState defaultFluid;
    private final int cellWidth;
    private final int cellHeight;

    public ModChunkGenerator(BiomeSource biomeSource, Holder<NoiseGeneratorSettings> settings) {
        super(biomeSource, settings);

        this.settings = settings;
        if (settings.isBound()) {
            NoiseSettings noise = settings.value().noiseSettings();
            defaultBlock = settings.value().defaultBlock();
            defaultFluid = settings.value().defaultFluid();
            cellWidth = noise.getCellWidth();
            cellHeight = noise.getCellHeight();

            NoiseSlider topSlide = new NoiseSlider(-10.0D, 3, 0);
            NoiseSlider bottomSlide = new NoiseSlider(15.0D, 3, 0);
            BlendedNoise blend = BlendedNoise.createUnseeded(1.0F, 1.0F, 80.0F, 160.0F, 0.0D);
            this.warper = new TerrainWarper(cellWidth, cellHeight, noise.height() / cellHeight, biomeSource, noise, topSlide, bottomSlide, blend, NoiseModifier.PASS);
        } else {
            this.defaultBlock = ModBlocks.UMBERSTONE.get().defaultBlockState();
            this.defaultFluid = Blocks.WATER.defaultBlockState();
            this.cellWidth = 0;
            this.cellHeight = 0;
            this.warper = null;
        }

        this.sampler = new Climate.Sampler(
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                List.of()
        );
    }

    public static void bootstrap(BootstrapContext<MapCodec<? extends ChunkGenerator>> context) {
        context.register(CHUNK_GENERATOR_KEY, CODEC);
    }

    @Override
    protected MapCodec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public CompletableFuture<ChunkAccess> createBiomes(RandomState randomState, Blender blender, StructureManager structureManager, ChunkAccess chunk) {
        return CompletableFuture.supplyAsync(() -> {
            chunk.fillBiomesFromNoise(getBiomeSource(), Climate.empty());
            return chunk;
        }, Util.backgroundExecutor());
    }

    public ChunkAccess doFill(Blender blender, StructureManager structureManager, RandomState random, ChunkAccess access, int min, int max) {
        int cellCountX = 16 / this.cellWidth;
        int cellCountZ = 16 / this.cellWidth;
        Heightmap oceanfloor = access.getOrCreateHeightmapUnprimed(Heightmap.Types.OCEAN_FLOOR_WG);
        Heightmap surface = access.getOrCreateHeightmapUnprimed(Heightmap.Types.WORLD_SURFACE_WG);
        ChunkPos chunkpos = access.getPos();
        int minX = chunkpos.getMinBlockX();
        int minZ = chunkpos.getMinBlockZ();
        ModNoiseInterpolator interpolator = new ModNoiseInterpolator(cellCountX, max, cellCountZ, chunkpos, min, this::fillNoiseColumn);
        List<ModNoiseInterpolator> list = Lists.newArrayList(interpolator);
        list.forEach(ModNoiseInterpolator::initialiseFirstX);

        for (int cellX = 0; cellX < cellCountX; cellX++) {
            int advX = cellX;
            list.forEach((noiseint) -> noiseint.advanceX(advX));

            for (int cellZ = 0; cellZ < cellCountZ; cellZ++) {
                int sections = access.getSectionsCount() - 1;
                LevelChunkSection section = access.getSection(sections);

                for (int cellY = max - 1; cellY >= 0; cellY--) {
                    int advY = cellY;
                    int advZ = cellZ;
                    list.forEach((noiseint) -> noiseint.selectYZ(advY, advZ));

                    for (int height = this.cellHeight - 1; height >= 0; height--) {
                        int minheight = (min + cellY) * this.cellHeight + height;
                        int mincellY = minheight & 15;
                        int minindexY = access.getSectionIndex(minheight);

                        if (sections != minindexY) {
                            sections = minindexY;
                            section = access.getSection(minindexY);
                        }

                        double heightdiv = (double) height / (double) this.cellHeight;
                        list.forEach((noiseint) -> noiseint.updateY(heightdiv));

                        for (int widthX = 0; widthX < this.cellWidth; widthX++) {
                            int minwidthX = minX + cellX * this.cellWidth + widthX;
                            int mincellX = minwidthX & 15;
                            double widthdivX = (double) widthX / (double) this.cellWidth;
                            list.forEach((noiseint) -> noiseint.updateX(widthdivX));

                            for (int widthZ = 0; widthZ < this.cellWidth; widthZ++) {
                                int minwidthZ = minZ + cellZ * this.cellWidth + widthZ;
                                int mincellZ = minwidthZ & 15;
                                double widthdivZ = (double) widthZ / (double) this.cellWidth;
                                double noiseval = interpolator.updateZ(widthdivZ);
                                BlockState state = this.generateBaseState(noiseval, minheight);

                                if (state != Blocks.AIR.defaultBlockState()) {
                                    section.setBlockState(mincellX, mincellY, mincellZ, state, false);
                                    oceanfloor.update(mincellX, minheight, mincellZ, state);
                                    surface.update(mincellX, minheight, mincellZ, state);
                                }
                            }
                        }
                    }
                }
            }

            list.forEach(ModNoiseInterpolator::swapSlices);
        }

        return access;
    }

    @Override
    public OptionalInt iterateNoiseColumn(LevelHeightAccessor level, RandomState random, int x, int z, MutableObject<NoiseColumn> column, Predicate<BlockState> stoppingState) {
        NoiseSettings noise = this.settings.value().noiseSettings().clampToHeightAccessor(level);
        int min = Math.floorDiv(noise.minY(), this.cellHeight);
        int max = Math.floorDiv(noise.height(), this.cellHeight);

        if (max <= 0) {
            return OptionalInt.empty();
        } else {
            BlockState[] states = null;
            if (column != null) {
                states = new BlockState[max * noise.getCellHeight()];
                column.setValue(new NoiseColumn(noise.minY(), states));
            }
            int xDiv = Math.floorDiv(x, this.cellWidth);
            int zDiv = Math.floorDiv(z, this.cellWidth);
            int xMod = Math.floorMod(x, this.cellWidth);
            int zMod = Math.floorMod(z, this.cellWidth);
            int xMin = xMod / this.cellWidth;
            int zMin = zMod / this.cellWidth;
            double[][] columns = new double[][]{
                    this.makeAndFillNoiseColumn(xDiv, zDiv, min, max),
                    this.makeAndFillNoiseColumn(xDiv, zDiv + 1, min, max),
                    this.makeAndFillNoiseColumn(xDiv + 1, zDiv, min, max),
                    this.makeAndFillNoiseColumn(xDiv + 1, zDiv + 1, min, max)
            };

            for (int cell = max - 1; cell >= 0; cell--) {
                double d10 = columns[0][cell];
                double d20 = columns[1][cell];
                double d30 = columns[2][cell];
                double d40 = columns[3][cell];
                double d11 = columns[0][cell + 1];
                double d21 = columns[1][cell + 1];
                double d31 = columns[2][cell + 1];
                double d41 = columns[3][cell + 1];

                for (int height = this.cellHeight - 1; height >= 0; height--) {
                    double dcell = height / (double) this.cellHeight;
                    double lcell = Mth.lerp3(dcell, xMin, zMin, d10, d11, d30, d31, d20, d21, d40, d41);
                    int layer = cell * this.cellHeight + height;
                    int maxlayer = layer + min * this.cellHeight;
                    BlockState state = this.generateBaseState(lcell, layer);
                    if (states != null) {
                        states[layer] = state;
                    }

                    if (stoppingState != null && stoppingState.test(state)) {
                        return OptionalInt.of(maxlayer + 1);
                    }
                }
            }

            return OptionalInt.empty();
        }
    }

    private double[] makeAndFillNoiseColumn(int x, int z, int min, int max) {
        double[] columns = new double[max + 1];
        this.fillNoiseColumn(columns, x, z, min, max);
        return columns;
    }

    private void fillNoiseColumn(double[] columns, int x, int z, int min, int max) {
        this.warper.fillNoiseColumn(columns, x, z, sampler, this.getSeaLevel(), min, max);
    }

    private BlockState generateBaseState(double a, double b) {
        BlockState state;

        if (a > 0.0D) {
            state = this.defaultBlock;
        } else if (b < this.getSeaLevel()) {
            state = this.defaultFluid;
        } else {
            state = Blocks.AIR.defaultBlockState();
        }

        return state;
    }
}
