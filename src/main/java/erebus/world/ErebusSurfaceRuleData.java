package erebus.world;

import com.google.common.collect.ImmutableList;
import erebus.registries.ModBlocks;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ErebusSurfaceRuleData extends SurfaceRuleData {
    public static final SurfaceRules.RuleSource UMBERSTONE = blockState(ModBlocks.UMBERSTONE);
    public static final SurfaceRules.RuleSource BEDROCK = blockState(Blocks.BEDROCK);

    private static SurfaceRules.RuleSource blockState(DeferredBlock<Block> block) {
        return SurfaceRules.state(block.get().defaultBlockState());
    }

    private static SurfaceRules.RuleSource blockState(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    public static SurfaceRules.RuleSource erebus() {
        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();

        // Filler
        builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK));
        builder.add(SurfaceRules.ifTrue(ErebusSurfaceRules.simplexGradient("umberstone", VerticalAnchor.absolute(40), VerticalAnchor.absolute(45), 0.06), UMBERSTONE));


        return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
    }
}
