package erebus.world.carver;

import erebus.Erebus;
import erebus.registries.data.ModTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.TrapezoidFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CanyonCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;

public class ModCarvers {

    public static final ResourceKey<ConfiguredWorldCarver<?>> CAVE = createKey("cave");
    public static final ResourceKey<ConfiguredWorldCarver<?>> CANYON = createKey("canyon");

    private static ResourceKey<ConfiguredWorldCarver<?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_CARVER, Erebus.prefix(name));
    }

    public static void bootstrap(BootstrapContext<ConfiguredWorldCarver<?>> context) {
        HolderGetter<Block> blockRegistry = context.lookup(Registries.BLOCK);

        context.register(
                CAVE,
                ModWorldCarvers.CAVE.get()
                        .configured(
                                new ErebusCaveCarverConfiguration(
                                        0.2F,
                                        UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.belowTop(1)),
                                        ConstantFloat.of(0.5F),
                                        VerticalAnchor.aboveBottom(10),
                                        blockRegistry.getOrThrow(ModTags.EREBUS_CARVER_REPLACEABLES),
                                        ConstantFloat.of(1.0F),
                                        ConstantFloat.of(1.0F),
                                        ConstantFloat.of(-0.7F)
                                )
                        )
        );

        context.register(
                CANYON,
                ModWorldCarvers.CANYON.get()
                        .configured(
                                new ErebusCanyonCarverConfiguration(
                                        0.01F,
                                        UniformHeight.of(VerticalAnchor.absolute(10), VerticalAnchor.absolute(67)),
                                        ConstantFloat.of(3.0F),
                                        VerticalAnchor.aboveBottom(8),
                                        CarverDebugSettings.of(false, Blocks.WARPED_BUTTON.defaultBlockState()),
                                        blockRegistry.getOrThrow(ModTags.EREBUS_CARVER_REPLACEABLES),
                                        UniformFloat.of(-0.125F, 0.125F),
                                        new CanyonCarverConfiguration.CanyonShapeConfiguration(
                                                UniformFloat.of(0.75F, 1.0F), TrapezoidFloat.of(0.0F, 6.0F, 2.0F), 3, UniformFloat.of(0.75F, 1.0F), 1.0F, 0.0F
                                        )
                                )
                        )
        );
    }
}
