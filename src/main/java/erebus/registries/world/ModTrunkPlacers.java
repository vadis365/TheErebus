package erebus.registries.world;

import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import erebus.world.feature.tree.trunkplacer.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTrunkPlacers {

    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, Erebus.MODID);

    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<AsperTrunkPlacer>> ASPER_TRUNK_PLACER = register("asper_trunk_placer", AsperTrunkPlacer.CODEC);
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<BalsamTrunkPlacer>> BALSAM_TRUNK_PLACER = register("balsam_trunk_placer", BalsamTrunkPlacer.CODEC);
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<BaobabTrunkPlacer>> BAOBAB_TRUNK_PLACER = register("baobab_trunk_placer", BaobabTrunkPlacer.CODEC);
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<CypressTrunkPlacer>> CYPRESS_TRUNK_PLACER = register("cypress_trunk_placer", CypressTrunkPlacer.CODEC);
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<EucalyptusTrunkPlacer>> EUCALYPTUS_TRUNK_PLACER = register("eucalyptus_trunk_placer", EucalyptusTrunkPlacer.CODEC);
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<GiantEucalyptusTrunkPlacer>> GIANT_EUCALYPTUS_TRUNK_PLACER = register("giant_eucalyptus_trunk_placer", GiantEucalyptusTrunkPlacer.CODEC);
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<MarshwoodTrunkPlacer>> MARSHWOOD_TRUNK_PLACER = register("marshwood_trunk_placer", MarshwoodTrunkPlacer.CODEC);
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<MossbarkTrunkPlacer>> MOSSBARK_TRUNK_PLACER = register("mossbark_trunk_placer", MossbarkTrunkPlacer.CODEC);

    private static <P extends TrunkPlacer> DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<P>> register(String name, MapCodec<P> codec) {
        return TRUNK_PLACERS.register(name, () -> new TrunkPlacerType<>(codec));
    }
}
