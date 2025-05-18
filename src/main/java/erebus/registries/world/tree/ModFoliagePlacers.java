package erebus.registries.world.tree;

import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import erebus.world.feature.tree.foliage.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFoliagePlacers {

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, Erebus.MODID);

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<BalsamFoliagePlacer>> BALSAM_FOLIAGE_PLACER;
    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<BaobabFoliagePlacer>> BAOBAB_FOLIAGE_PLACER;
    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<SingleLeafFoliagePlacer>> SINGLE_LEAF_FOLIAGE_PLACER;
    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<MarshwoodFoliagePlacer>> MARSHWOOD_FOLIAGE_PLACER;

    static {
        BALSAM_FOLIAGE_PLACER = register("balsam_foliage_placer", BalsamFoliagePlacer.CODEC);
        BAOBAB_FOLIAGE_PLACER = register("baobab_foliage_placer", BaobabFoliagePlacer.CODEC);
        SINGLE_LEAF_FOLIAGE_PLACER = register("single_leaf_foliage_placer", SingleLeafFoliagePlacer.CODEC);
        MARSHWOOD_FOLIAGE_PLACER = register("marshwood_foliage_placer", MarshwoodFoliagePlacer.CODEC);
    }

    private static <P extends FoliagePlacer> DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<P>> register(String name, MapCodec<P> codec) {
        return FOLIAGE_PLACERS.register(name, () -> new FoliagePlacerType<>(codec));
    }
}
