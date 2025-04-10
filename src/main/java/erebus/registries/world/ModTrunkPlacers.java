package erebus.registries.world;

import erebus.Erebus;
import erebus.world.feature.tree.trunkplacer.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTrunkPlacers {

    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, Erebus.MODID);

    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<AsperTrunkPlacer>> ASPER_TRUNK_PLACER = TRUNK_PLACERS.register("asper_trunk_placer", () -> new TrunkPlacerType<>(AsperTrunkPlacer.CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<BalsamTrunkPlacer>> BALSAM_TRUNK_PLACER = TRUNK_PLACERS.register("balsam_trunk_placer", () -> new TrunkPlacerType<>(BalsamTrunkPlacer.CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<BaobabTrunkPlacer>> BAOBAB_TRUNK_PLACER = TRUNK_PLACERS.register("baobab_trunk_placer", () -> new TrunkPlacerType<>(BaobabTrunkPlacer.CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<CypressTrunkPlacer>> CYPRESS_TRUNK_PLACER = TRUNK_PLACERS.register("cypress_trunk_placer", () -> new TrunkPlacerType<>(CypressTrunkPlacer.CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<EucalyptusTrunkPlacer>> EUCALYPTUS_TRUNK_PLACER = TRUNK_PLACERS.register("eucalyptus_trunk_placer", () -> new TrunkPlacerType<>(EucalyptusTrunkPlacer.CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<GiantEucalyptusTrunkPlacer>> GIANT_EUCALYPTUS_TRUNK_PLACER = TRUNK_PLACERS.register("giant_eucalyptus_trunk_placer", () -> new TrunkPlacerType<>(GiantEucalyptusTrunkPlacer.CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<MarshwoodTrunkPlacer>> MARSHWOOD_TRUNK_PLACER = TRUNK_PLACERS.register("marshwood_trunk_placer", () -> new TrunkPlacerType<>(MarshwoodTrunkPlacer.CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<MossbarkTrunkPlacer>> MOSSBARK_TRUNK_PLACER = TRUNK_PLACERS.register("mossbark_trunk_placer", () -> new TrunkPlacerType<>(MossbarkTrunkPlacer.CODEC));

    public static void register(IEventBus bus) {
        TRUNK_PLACERS.register(bus);
    }
}
