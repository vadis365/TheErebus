package erebus.world;

import com.google.common.collect.ImmutableList;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

import java.util.function.Supplier;

import static net.minecraft.world.level.levelgen.SurfaceRules.*;

public class ModSurfaceRules {

    // Erebus
    private static final RuleSource MUD = makeStateRule(ModBlocks.MUD);
    private static final RuleSource UMBERSTONE = makeStateRule(ModBlocks.UMBERSTONE);
    private static final RuleSource DUST_LAYER = makeStateRule(ModBlocks.DUST_LAYER);
    private static final RuleSource DUST = makeStateRule(ModBlocks.DUST);
    private static final RuleSource VOLCANIC_ROCK = makeStateRule(ModBlocks.VOLCANIC_ROCK);

    // Vanilla
    private static final RuleSource SAND = makeStateRule(Blocks.SAND);
    private static final RuleSource SANDSTONE = makeStateRule(Blocks.SANDSTONE);
    private static final RuleSource RED_SAND = makeStateRule(Blocks.RED_SAND);
    private static final RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final RuleSource GRASS = makeStateRule(Blocks.GRASS_BLOCK);
    private static final RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final RuleSource WATER = makeStateRule(Blocks.WATER);

    public static RuleSource erebus() {
        ImmutableList.Builder<RuleSource> builder = ImmutableList.builder();

        builder.add(
                addBedrock(true),
                addBedrock(false),
                ifTrue(
                        yBlockCheck(VerticalAnchor.belowTop(5), 0),
                        UMBERSTONE
                ),
                sequence(
                        decorateElysianFields(),
                        decorateElysianForest(),
                        decorateFungalForest(),
                        decoratePetrifiedForest(),
                        decorateSubmergedSwamp(),
                        decorateSubterraneanSavannah(),
                        decorateUlteriorOutback(),
                        decorateUndergroundJungle(),
                        decorateVolcanicDesert()
                )
        );

        return sequence(builder.build().toArray(RuleSource[]::new));
    }

    private static RuleSource addBedrock(boolean isRoof) {
        if (isRoof)
            return ifTrue(not(verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK);
        else
            return ifTrue(verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK);
    }

    // MARK: Elysian Fields
    private static RuleSource decorateElysianFields() {
        return ifTrue(
                isBiome(ModBiomes.ELYSIAN_FIELDS.getResourceKey()),
                sequence(
                        placeBlock(GRASS, false),
                        placeBlock(DIRT, true)
                )
        );
    }

    // MARK: Elysian Forest
    private static RuleSource decorateElysianForest() {
        return ifTrue(
                isBiome(ModBiomes.ELYSIAN_FOREST.getResourceKey()),
                sequence(
                        placeBlock(GRASS, false),
                        placeBlock(DIRT, true)
                )
        );
    }

    // MARK: Fungal Forest
    private static RuleSource decorateFungalForest() {
        return ifTrue(
                isBiome(ModBiomes.FUNGAL_FOREST.getResourceKey()),
                sequence(
                        placeBlock(GRASS, false),
                        placeBlock(DIRT, true)
                )
        );
    }

    // MARK: Petrified Forest
    private static RuleSource decoratePetrifiedForest() {
        return ifTrue(
                isBiome(ModBiomes.PETRIFIED_FOREST.getResourceKey()),
                ifTrue(
                        stoneDepthCheck(0, true, 0, CaveSurface.FLOOR),
                        sequence(
                                ifTrue(
                                        noiseCondition(Noises.POWDER_SNOW, 0.45, 0.58),
                                        DUST_LAYER
                                ),
                                placeBlock(VOLCANIC_ROCK, false),
                                placeBlock(VOLCANIC_ROCK, true)
                        )
                )
        );
    }

    // MARK: Submerged Swamp
    private static RuleSource decorateSubmergedSwamp() {
        return ifTrue(
                isBiome(ModBiomes.SUBMERGED_SWAMP.getResourceKey()),
                sequence(
                        placeBlock(GRASS, false),
                        placeBlock(DIRT, true)
                        /*ifTrue(
                                stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                                ifTrue(
                                        yBlockCheck(VerticalAnchor.absolute(0), 0),
                                        ifTrue(
                                                not(yBlockCheck(VerticalAnchor.absolute(63), 0)),
                                                ifTrue(
                                                        noiseCondition(Noises.SWAMP, 0, 1.7976931348623157e+308),
                                                        WATER
                                                )
                                        )
                                )
                        ),
                        ifTrue(
                                stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                                ifTrue(
                                        waterBlockCheck(-1, 0),
                                        sequence(
                                                MUD,
                                                ifTrue(
                                                        waterBlockCheck(0, 0),
                                                        placeGrass()
                                                ),
                                                placeDirtUnderGrass()
                                        )
                                )
                        )*/
                )
        );
    }

    // MARK: Subterranean Savannah
    private static RuleSource decorateSubterraneanSavannah() {
        return ifTrue(
                isBiome(ModBiomes.SUBTERRANEAN_SAVANNAH.getResourceKey()),
                sequence(
                        placeBlock(GRASS, false),
                        placeBlock(DIRT, true)
                )
        );
    }

    // MARK: Ulterior Outback
    private static RuleSource decorateUlteriorOutback() {
        return ifTrue(
                isBiome(ModBiomes.ULTERIOR_OUTBACK.getResourceKey()),
                sequence(
                        ifTrue(
                                noiseCondition(Noises.NETHER_STATE_SELECTOR, 0.0, 1.8D),
                                RED_SAND
                        ),
                        ifTrue(
                                noiseCondition(Noises.NETHER_STATE_SELECTOR, 0.0, 1.8D),
                                DIRT
                        )
                )
        );
    }

    // MARK: Underground Jungle
    private static RuleSource decorateUndergroundJungle() {
        return ifTrue(
                isBiome(ModBiomes.UNDERGROUND_JUNGLE.getResourceKey()),
                sequence(
                        placeBlock(GRASS, false),
                        placeBlock(DIRT, true)
                )
        );
    }

    // MARK: Volcanic Dessert
    private static RuleSource decorateVolcanicDesert() {
        return ifTrue(
                isBiome(ModBiomes.VOLCANIC_DESERT.getResourceKey()),
                sequence(
                        placeBlock(SAND, false),
                        placeBlock(SANDSTONE, true)
                )
        );
    }

    private static RuleSource placeBlock(RuleSource source, boolean addSurfaceDepth) {
        return ifTrue(
                stoneDepthCheck(0, addSurfaceDepth, 0, CaveSurface.FLOOR),
                source
        );
    }

    private static RuleSource makeStateRule(Block block) {
        return state(block.defaultBlockState());
    }

    private static RuleSource makeStateRule(Supplier<? extends Block> block) {
        return makeStateRule(block.get());
    }
}
