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

    // Vanilla
    private static final RuleSource SANDSTONE = makeStateRule(Blocks.SANDSTONE);
    private static final RuleSource SAND = makeStateRule(Blocks.SAND);
    private static final RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final RuleSource DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);

    private static RuleSource makeStateRule(Block block) {
        return state(block.defaultBlockState());
    }

    private static RuleSource makeStateRule(Supplier<? extends Block> block) {
        return makeStateRule(block.get());
    }

    public static RuleSource erebus() {
        ConditionSource groundLevel = yBlockCheck(VerticalAnchor.absolute(97), 2);
        ConditionSource generatedHeightLimit = yBlockCheck(VerticalAnchor.absolute(256), 0);
        ConditionSource infestedStoneHeight = yBlockCheck(VerticalAnchor.absolute(63), -1);
        ConditionSource conditionSource = yBlockCheck(VerticalAnchor.absolute(74), 1);
        ConditionSource conditionSource1 = yBlockCheck(VerticalAnchor.absolute(60), 0);
        ConditionSource conditionSource2 = yBlockCheck(VerticalAnchor.absolute(63), 0);
        ConditionSource waterCondition = waterBlockCheck(-1, 0);
        ConditionSource grassLayer = waterBlockCheck(0, 0);
        ConditionSource waterCondition2 = waterBlockCheck(-6, -1);
        ConditionSource hole = hole();
        ConditionSource steep = steep();

        RuleSource placeGrassOnDirt = sequence(ifTrue(grassLayer, UMBERSTONE), UMBERSTONE);
        RuleSource placeSandstoneOnSand = sequence(ifTrue(ON_CEILING, SANDSTONE), SAND);
        RuleSource placeStoneOnGravel = sequence(ifTrue(ON_CEILING, UMBERSTONE), UMBERGRAVEL);

        SurfaceRules.ConditionSource noise = SurfaceRules.noiseCondition(Noises.SURFACE, -0.909, -0.5454);
        SurfaceRules.ConditionSource noise1 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.1818, 0.1818);
        SurfaceRules.ConditionSource noise2 = SurfaceRules.noiseCondition(Noises.SURFACE, 0.5454, 0.909);

        RuleSource decorateSwamp = sequence(
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
                                                        placeGrassOnDirt
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
                                                        placeGrassOnDirt
                                                )
                                        )
                                )
                        )
                )
        );

        ImmutableList.Builder<RuleSource> builder = ImmutableList.builder();

        builder.add(ifTrue(verticalGradient(
                                "bedrock_floor",
                                VerticalAnchor.bottom(),
                                VerticalAnchor.aboveBottom(5)
                        ),
                BEDROCK)
        );
        builder.add(ifTrue(abovePreliminarySurface(), decorateSwamp));
        builder.add(ifTrue(verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), DEEPSLATE));

        return sequence(builder.build().toArray(RuleSource[]::new));
    }
}
