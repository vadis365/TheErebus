package erebus.registries.world;

import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import erebus.world.feature.tree.foliage.BalsamFoliagePlacer;
import erebus.world.feature.tree.foliage.BaobabFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFoliagePlacers {

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, Erebus.MODID);

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<BalsamFoliagePlacer>> BALSAM_FOLIAGE_PLACER = register("balsam_foliage_placer", BalsamFoliagePlacer.CODEC);
    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<BaobabFoliagePlacer>> BAOBAB_FOLIAGE_PLACER = register("baobab_foliage_placer", BaobabFoliagePlacer.CODEC);

    private static <P extends FoliagePlacer> DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<P>> register(String name, MapCodec<P> codec) {
        return FOLIAGE_PLACERS.register(name, () -> new FoliagePlacerType<>(codec));
    }
}
