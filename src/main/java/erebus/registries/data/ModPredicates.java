package erebus.registries.data;

import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import erebus.datagen.loot.predicates.DragonflyPredicate;
import erebus.datagen.loot.predicates.WaspPredicate;
import net.minecraft.advancements.criterion.EntitySubPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPredicates {

    public static final DeferredRegister<MapCodec<? extends EntitySubPredicate>> PREDICATES = DeferredRegister.create(BuiltInRegistries.ENTITY_SUB_PREDICATE_TYPE, Erebus.MODID);

    public static final DeferredHolder<MapCodec<? extends EntitySubPredicate>, MapCodec<DragonflyPredicate>> DRAGONFLY = PREDICATES.register("dragonfly", () -> DragonflyPredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends EntitySubPredicate>, MapCodec<WaspPredicate>> WASP = PREDICATES.register("wasp", () -> WaspPredicate.CODEC);
}
