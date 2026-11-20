package erebus.world.feature.misc;

import erebus.block.HangingWebBlock;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import erebus.world.feature.ErebusFeature;
import erebus.world.biome.util.TerrainBuilder;
import net.minecraft.core.QuartPos;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TransitionLandmark extends Feature<NoneFeatureConfiguration> {
    public enum Kind {
        ROOT_CROSSINGS("root_crossings", ModBiomes.ROOTWATER_MARSH_KEY, 3),
        SCORCHED_OUTCROPS("scorched_outcrops", ModBiomes.CINDER_SCRUB_KEY, 3),
        GLOWROT_LOGS("glowrot_logs", ModBiomes.GLOWROT_GROVES_KEY, 5),
        AMBER_BANKS("amber_banks", ModBiomes.AMBER_THICKETS_KEY, 5),
        FOSSIL_STANDS("fossil_stands", ModBiomes.FOSSIL_BARRENS_KEY, 3),
        ROOTWATER_POOLS("rootwater_pools", ModBiomes.ROOTWATER_MARSH_KEY, 3);

        public final String id;
        private final ResourceKey<Biome> biome;
        public final ErebusFeature feature;

        Kind(String id, ResourceKey<Biome> biome, int rarity) {
            this.id = id;
            this.biome = biome;
            this.feature = new ErebusFeature(id) {
                @Override
                public List<PlacementModifier> getPlacementModifiers() {
                    // The chunk origin is kept so the feature can bound all of its writes.
                    var chance = RarityFilter.onAverageOnceEvery(rarity);
                    if (biome.equals(ModBiomes.GLOWROT_GROVES_KEY)) {
                        var upper = VerticalAnchor.absolute(TerrainBuilder.GLOWROT_UPPER_START_Y + QuartPos.SIZE);
                        return List.of(chance, HeightRangePlacement.uniform(upper, upper), BiomeFilter.biome());
                    }
                    return List.of(chance, BiomeFilter.biome());
                }
            };
        }
    }

    private final Kind kind;

    public TransitionLandmark(Kind kind) {
        super(NoneFeatureConfiguration.CODEC);
        this.kind = kind;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var random = context.random();
        int chunkX = context.origin().getX() & ~15;
        int chunkZ = context.origin().getZ() & ~15;
        for (int attempt = 0; attempt < 10; attempt++) {
            int x = chunkX + 4 + random.nextInt(8);
            int z = chunkZ + 4 + random.nextInt(8);
            int startY = level.getMinY() + 22 + random.nextInt(58);
            if (kind == Kind.GLOWROT_LOGS) startY = Math.max(startY, TerrainBuilder.GLOWROT_UPPER_START_Y);
            Direction along = random.nextBoolean() ? Direction.EAST : Direction.SOUTH;
            for (int y = startY; y < level.getMinY() + 100; y++) {
                var floor = new BlockPos(x, y, z);
                if (!ground(level, floor) || !air(level, floor.above())) continue;
                boolean placed = switch (kind) {
                    case ROOT_CROSSINGS -> pool(context, floor, along, true, false);
                    case ROOTWATER_POOLS -> pool(context, floor, along, false, false);
                    case AMBER_BANKS -> pool(context, floor, along, false, true);
                    case SCORCHED_OUTCROPS -> outcrop(level, floor, along);
                    case GLOWROT_LOGS -> glowrot(level, floor, along);
                    case FOSSIL_STANDS -> fossils(context, floor, along);
                };
                if (placed) return true;
            }
        }
        return false;
    }

    private boolean pool(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos floor,
                         Direction along, boolean crossing, boolean amber) {
        var level = context.level();
        var across = along.getClockWise();
        Map<BlockPos, BlockState> plan = new LinkedHashMap<>();
        // Validate the basin and its enclosing banks before replacing any blocks.
        for (int x = -3; x <= 3; x++) {
            for (int z = -2; z <= 2; z++) {
                var pos = floor.relative(along, x).relative(across, z);
                if (!ground(level, pos) || !air(level, pos.above()) || !ground(level, pos.below())) return false;
                if (Math.abs(x) == 3 || Math.abs(z) == 2) continue;
                if (Math.abs(x) == 2 && z == 1) continue;
                boolean bridge = crossing && z == 0;
                if (bridge && !air(level, pos.above(2))) return false;
                plan.put(pos.below(), (amber ? ModBlocks.UMBERSTONE : ModBlocks.MUD).get().defaultBlockState());
                plan.put(pos, bridge ? log(Math.abs(x) == 2 ? Blocks.MANGROVE_ROOTS : ModBlocks.LOG_ASPER.get(), along)
                        : Blocks.WATER.defaultBlockState());
            }
        }
        if (crossing && (!air(level, floor.relative(along, -3).above(2))
                || !air(level, floor.relative(along, 3).above(2)))) return false;
        if (amber) {
            for (int i = -2; i <= 2; i++) {
                var bank = floor.relative(along, i).relative(across, 2);
                plan.put(bank, ModBlocks.UMBERSTONE.get().defaultBlockState());
                plan.put(bank.above(), (i == 0 ? ModBlocks.AMBER.get() : ModBlocks.UMBERSTONE.get()).defaultBlockState());
            }
            plan.put(floor.relative(along, -3).relative(across, 2).above(), Blocks.MANGROVE_ROOTS.defaultBlockState());
        } else {
            for (int i : new int[]{-2, 2}) {
                var shore = floor.relative(along, i).relative(across, -2);
                if (context.random().nextBoolean()) plan.put(shore.above(), Blocks.SUGAR_CANE.defaultBlockState());
            }
        }
        return commit(level, plan);
    }

    private boolean outcrop(WorldGenLevel level, BlockPos floor, Direction along) {
        Map<BlockPos, BlockState> plan = new LinkedHashMap<>();
        for (int i = -1; i <= 2; i++) {
            var ridge = floor.relative(along, i);
            int height = i == 0 || i == 2 ? 2 : 1;
            if (!column(level, plan, ridge, ModBlocks.GNEISS.get().defaultBlockState(), height)) return false;
        }
        for (int i = 0; i <= 1; i++) {
            var trunk = floor.relative(along, i).relative(along.getClockWise());
            if (!column(level, plan, trunk, log(ModBlocks.LOG_SCORCHED.get(), along), 1)) return false;
        }
        return commit(level, plan);
    }

    private boolean glowrot(WorldGenLevel level, BlockPos floor, Direction along) {
        Map<BlockPos, BlockState> plan = new LinkedHashMap<>();
        for (int i = -2; i <= 3; i++) {
            var trunk = floor.relative(along, i);
            if (!column(level, plan, trunk, log(ModBlocks.LOG_ROTTEN.get(), along), i == -2 || i == 3 ? 1 : 2)) return false;
            var shade = trunk.relative(along.getClockWise()).above();
            if (i != 1 && air(level, shade)) {
                plan.put(shade, ModBlocks.GLOWSHROOM_STALK.get().defaultBlockState());
            }
        }
        // The stepped end forms a hollow against the taller trunk.
        plan.put(floor.relative(along, -2).above(2), ModBlocks.GLOWSHROOM_STALK.get().defaultBlockState());
        if (!air(level, floor.relative(along, -2).above(2))) return false;
        return commit(level, plan);
    }

    private boolean fossils(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos floor, Direction along) {
        var level = context.level();
        Map<BlockPos, BlockState> plan = new LinkedHashMap<>();
        var left = floor.relative(along, -1);
        var right = floor.relative(along, 2);
        var stump = floor.relative(along.getClockWise());
        if (!column(level, plan, left, ModBlocks.PETRIFIED_BARK_BROWN.get().defaultBlockState(), 3)
                || !column(level, plan, right, ModBlocks.PETRIFIED_BARK_RED.get().defaultBlockState(), 2)
                || !column(level, plan, stump, ModBlocks.PETRIFIED_WOOD_ROCK.get().defaultBlockState(), 1)) return false;
        plan.put(left, ModBlocks.PETRIFIED_WOOD_ROCK.get().defaultBlockState());
        plan.put(right, ModBlocks.PETRIFIED_WOOD_ROCK.get().defaultBlockState());
        var dust = stump.relative(along).above();
        if (air(level, dust) && ground(level, dust.below())) plan.put(dust, ModBlocks.DUST_LAYER.get().defaultBlockState());
        if (context.random().nextInt(4) == 0 && air(level, left.relative(along).above())
                && air(level, left.relative(along).above(2))) {
            plan.put(left.relative(along).above(2), log(ModBlocks.PETRIFIED_BARK_BROWN.get(), along));
            plan.put(left.relative(along).above(), ModBlocks.HANGING_WEB.get().defaultBlockState()
                    .setValue(HangingWebBlock.FACING, along.getClockWise()));
        }
        return commit(level, plan);
    }

    private boolean column(WorldGenLevel level, Map<BlockPos, BlockState> plan, BlockPos floor, BlockState state, int height) {
        if (!ground(level, floor)) return false;
        for (int y = 1; y <= height; y++) {
            var pos = floor.above(y);
            if (!air(level, pos)) return false;
            plan.put(pos, state);
        }
        return true;
    }

    private boolean commit(WorldGenLevel level, Map<BlockPos, BlockState> plan) {
        if (plan.keySet().stream().anyMatch(pos -> !level.getBiome(pos).is(kind.biome))) return false;
        plan.forEach((pos, state) -> {
            if (state.is(Blocks.SUGAR_CANE) && !state.canSurvive(level, pos)) return;
            level.setBlock(pos, state, Block.UPDATE_CLIENTS);
            if (state.is(Blocks.WATER)) level.scheduleTick(pos, state.getFluidState().getType(), 0);
        });
        return true;
    }

    private static boolean ground(WorldGenLevel level, BlockPos pos) {
        if (level.isOutsideBuildHeight(pos)) return false;
        var state = level.getBlockState(pos);
        return (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.SAND)
                || state.is(Blocks.RED_SAND) || state.is(Blocks.COARSE_DIRT) || state.is(ModBlocks.UMBERSTONE) || state.is(ModBlocks.MUD)
                || state.is(ModBlocks.VOLCANIC_ROCK) || state.is(ModBlocks.GNEISS))
                && state.isFaceSturdy(level, pos, Direction.UP);
    }

    private static boolean air(WorldGenLevel level, BlockPos pos) {
        return !level.isOutsideBuildHeight(pos) && level.isEmptyBlock(pos);
    }

    private static BlockState log(Block block, Direction along) {
        var state = block.defaultBlockState();
        return state.trySetValue(BlockStateProperties.AXIS, along.getAxis());
    }
}
