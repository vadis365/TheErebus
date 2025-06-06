package erebus.world.carver;

import erebus.Erebus;
import erebus.registries.data.ModTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;

public class ModCarvers {

    public static final ResourceKey<ConfiguredWorldCarver<?>> EREBUS_CAVE = createKey("erebus_cave");

    private static ResourceKey<ConfiguredWorldCarver<?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_CARVER, Erebus.prefix(name));
    }

    public static void bootstrap(BootstrapContext<ConfiguredWorldCarver<?>> context) {
        HolderGetter<Block> blockRegistry = context.lookup(Registries.BLOCK);

        context.register(
                EREBUS_CAVE,
                ModWorldCarvers.EREBUS_CAVE.get()
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
    }
}
