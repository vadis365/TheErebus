package erebus.world;

import com.google.common.collect.ImmutableList;
import erebus.registries.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

import java.util.function.Supplier;

import static net.minecraft.world.level.levelgen.SurfaceRules.*;

public class ModSurfaceRules {

    // Erebus
    private static final RuleSource UMBERSTONE = makeStateRule(ModBlocks.UMBERSTONE);
    private static final RuleSource UMBERGRAVEL = makeStateRule(ModBlocks.UMBERGRAVEL);
    private static final RuleSource UMBERCOBBLE = makeStateRule(ModBlocks.UMBERCOBBLE);
    private static final RuleSource MUD = makeStateRule(ModBlocks.MUD);

    // Vanilla
    private static final RuleSource SANDSTONE = makeStateRule(Blocks.SANDSTONE);
    private static final RuleSource SAND = makeStateRule(Blocks.SAND);
    private static final RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final RuleSource DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);
    private static final RuleSource GRASS = makeStateRule(Blocks.GRASS_BLOCK);
    private static final RuleSource DIRT = makeStateRule(Blocks.DIRT);

    private static final ConditionSource groundLevel = yBlockCheck(VerticalAnchor.absolute(97), 2);
    private static final ConditionSource generatedHeightLimit = yBlockCheck(VerticalAnchor.absolute(256), 0);
    private static final ConditionSource infestedStoneHeight = yBlockCheck(VerticalAnchor.absolute(63), -1);
    private static final ConditionSource conditionSource = yBlockCheck(VerticalAnchor.absolute(74), 1);
    private static final ConditionSource conditionSource1 = yBlockCheck(VerticalAnchor.absolute(60), 0);
    private static final ConditionSource conditionSource2 = yBlockCheck(VerticalAnchor.absolute(63), 0);
    private static final ConditionSource waterCondition = waterBlockCheck(-1, 0);
    private static final ConditionSource grassLayer = waterBlockCheck(0, 0);
    private static final ConditionSource waterCondition2 = waterBlockCheck(-6, -1);
    private static final ConditionSource hole = hole();
    private static final ConditionSource steep = steep();

    private static final RuleSource placeGrassOnDirt = sequence(ifTrue(grassLayer, GRASS), DIRT);
    private static final RuleSource placeSandstoneOnSand = sequence(ifTrue(ON_CEILING, SANDSTONE), SAND);
    private static final RuleSource placeStoneOnGravel = sequence(ifTrue(ON_CEILING, UMBERSTONE), UMBERGRAVEL);

    private static final SurfaceRules.ConditionSource noise = SurfaceRules.noiseCondition(Noises.SURFACE, -0.909, -0.5454);
    private static final SurfaceRules.ConditionSource noise1 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.1818, 0.1818);
    private static final SurfaceRules.ConditionSource noise2 = SurfaceRules.noiseCondition(Noises.SURFACE, 0.5454, 0.909);

    public static RuleSource erebus() {
        ImmutableList.Builder<RuleSource> builder = ImmutableList.builder();

        builder.add(
                addBedrock(true),
                addBedrock(false),
                UMBERSTONE,
                decorateElysianFields(),
                ifTrue(abovePreliminarySurface(), decorateSwamp()),
                ifTrue(verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), DEEPSLATE)
        );

        return sequence(builder.build().toArray(RuleSource[]::new));
    }

    private static RuleSource addBedrock(boolean isRoof) {
        if (isRoof)
            return ifTrue(not(verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK);
        else
            return ifTrue(verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK);
    }

    private static RuleSource decorateElysianFields() {
        return ifTrue(
                isBiome(ModBiomes.ELYSIAN_FIELDS.getResourceKey()),
                sequence(placeGrassOnDirt)
        );
    }

    private static RuleSource decorate() {
        return null;
    }

    private static RuleSource decorateSwamp() {
        return sequence(
                ifTrue(
                        ON_FLOOR,
                        sequence(
                                ifTrue(
                                        isBiome(ModBiomes.SUBMERGED_SWAMP.getResourceKey()),
                                        ifTrue(
                                                groundLevel,
                                                sequence(
                                                        ifTrue(noise, COARSE_DIRT),
                                                        ifTrue(noise1, COARSE_DIRT),
                                                        ifTrue(noise2, COARSE_DIRT),
                                                        placeGrassOnDirt,
                                                        MUD
                                                )
                                        )
                                ),
                                ifTrue(
                                        isBiome(ModBiomes.FUNGAL_FOREST.getResourceKey()),

                                        ifTrue(
                                                groundLevel,
                                                sequence(
                                                        ifTrue(noise, COARSE_DIRT),
                                                        ifTrue(noise1, COARSE_DIRT),
                                                        ifTrue(noise2, COARSE_DIRT),
                                                        placeGrassOnDirt,
                                                        MUD
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static RuleSource makeStateRule(Block block) {
        return state(block.defaultBlockState());
    }

    private static RuleSource makeStateRule(Supplier<? extends Block> block) {
        return makeStateRule(block.get());
    }
}
