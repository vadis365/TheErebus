package erebus.world;

import erebus.registries.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

import java.util.function.Supplier;

public class ModSurfaceRules {

    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final SurfaceRules.RuleSource UMBERSTONE = makeStateRule(ModBlocks.UMBERSTONE);
    private static final SurfaceRules.RuleSource UMBERGRAVEL = makeStateRule(ModBlocks.UMBERGRAVEL);
    private static final SurfaceRules.RuleSource UMBERCOBBLE = makeStateRule(ModBlocks.UMBERCOBBLE);

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    private static SurfaceRules.RuleSource makeStateRule(Supplier<? extends Block> block) {
        return makeStateRule(block.get());
    }

    public static SurfaceRules.RuleSource erebus() {
        SurfaceRules.ConditionSource surfacerules$conditionsource = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(31), 0);
        SurfaceRules.ConditionSource surfacerules$conditionsource1 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(32), 0);
        SurfaceRules.ConditionSource surfacerules$conditionsource2 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(30), 0);
        SurfaceRules.ConditionSource surfacerules$conditionsource3 = SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.absolute(35), 0));
        SurfaceRules.ConditionSource surfacerules$conditionsource4 = SurfaceRules.yBlockCheck(VerticalAnchor.belowTop(5), 0);
        SurfaceRules.ConditionSource surfacerules$conditionsource5 = SurfaceRules.hole();
        SurfaceRules.ConditionSource surfacerules$conditionsource6 = SurfaceRules.noiseCondition(Noises.SOUL_SAND_LAYER, -0.012);
        SurfaceRules.ConditionSource surfacerules$conditionsource7 = SurfaceRules.noiseCondition(Noises.GRAVEL_LAYER, -0.012);
        SurfaceRules.ConditionSource surfacerules$conditionsource8 = SurfaceRules.noiseCondition(Noises.PATCH, -0.012);
        SurfaceRules.ConditionSource surfacerules$conditionsource9 = SurfaceRules.noiseCondition(Noises.NETHERRACK, 0.54);
        SurfaceRules.ConditionSource surfacerules$conditionsource10 = SurfaceRules.noiseCondition(Noises.NETHER_WART, 1.17);
        SurfaceRules.ConditionSource surfacerules$conditionsource11 = SurfaceRules.noiseCondition(Noises.NETHER_STATE_SELECTOR, 0.0F);
        SurfaceRules.RuleSource surfacerules$rulesource = SurfaceRules.ifTrue(
                surfacerules$conditionsource8,
                SurfaceRules.ifTrue(
                        surfacerules$conditionsource2,
                        SurfaceRules.ifTrue(
                                surfacerules$conditionsource3, UMBERCOBBLE
                        )
                )
        );
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.verticalGradient(
                                "bedrock_floor",
                                VerticalAnchor.bottom(),
                                VerticalAnchor.aboveBottom(5)
                        ),
                        BEDROCK
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.not(
                                SurfaceRules.verticalGradient(
                                        "bedrock_roof",
                                        VerticalAnchor.belowTop(5),
                                        VerticalAnchor.top()
                                )
                        ),
                        BEDROCK
                ),
                SurfaceRules.ifTrue(
                        surfacerules$conditionsource4, UMBERSTONE
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.ELYSIAN_FIELDS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_CEILING,
                                        UMBERCOBBLE
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.sequence(
                                                surfacerules$rulesource,
                                                SurfaceRules.ifTrue(
                                                        surfacerules$conditionsource11,
                                                        UMBERCOBBLE
                                                ),
                                                UMBERCOBBLE)
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.SOUL_SAND_VALLEY),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_CEILING,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(
                                                        surfacerules$conditionsource11,
                                                        UMBERCOBBLE
                                                ),
                                                UMBERCOBBLE)
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.sequence(
                                                surfacerules$rulesource,
                                                SurfaceRules.ifTrue(
                                                        surfacerules$conditionsource11,
                                                        UMBERCOBBLE
                                                ),
                                                UMBERCOBBLE)
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.not(surfacerules$conditionsource1),
                                        SurfaceRules.ifTrue(
                                                surfacerules$conditionsource5,
                                                UMBERCOBBLE)
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(ModBiomes.FUNGAL_FOREST),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.not(surfacerules$conditionsource9),
                                                SurfaceRules.ifTrue(
                                                        surfacerules$conditionsource,
                                                        SurfaceRules.sequence(
                                                                SurfaceRules.ifTrue(
                                                                        surfacerules$conditionsource10,
                                                                        UMBERCOBBLE
                                                                ), UMBERCOBBLE)
                                                )
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(ModBiomes.PETRIFIED_FOREST),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.not(surfacerules$conditionsource9),
                                                SurfaceRules.ifTrue(
                                                        surfacerules$conditionsource,
                                                        SurfaceRules.sequence(
                                                                SurfaceRules.ifTrue(
                                                                        surfacerules$conditionsource10,
                                                                        UMBERCOBBLE
                                                                ),
                                                                UMBERCOBBLE)
                                                )
                                        )
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.SUBMERGED_SWAMP),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.ifTrue(
                                                surfacerules$conditionsource6,
                                                SurfaceRules.sequence(
                                                        SurfaceRules.ifTrue(
                                                                SurfaceRules.not(surfacerules$conditionsource5),
                                                                SurfaceRules.ifTrue(
                                                                        surfacerules$conditionsource2,
                                                                        SurfaceRules.ifTrue(
                                                                                surfacerules$conditionsource3,
                                                                                UMBERCOBBLE
                                                                        )
                                                                )
                                                        ),
                                                        UMBERSTONE)
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(
                                                surfacerules$conditionsource,
                                                SurfaceRules.ifTrue(
                                                        surfacerules$conditionsource3,
                                                        SurfaceRules.ifTrue(
                                                                surfacerules$conditionsource7,
                                                                SurfaceRules.sequence(
                                                                        SurfaceRules.ifTrue(
                                                                                surfacerules$conditionsource1,
                                                                                UMBERGRAVEL
                                                                        ),
                                                                        SurfaceRules.ifTrue(
                                                                                SurfaceRules.not(surfacerules$conditionsource5),
                                                                                UMBERGRAVEL
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                ),
                UMBERSTONE);
    }
}
