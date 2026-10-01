package erebus.registries.world;

import erebus.Erebus;
import erebus.world.OreCountPlacement;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModOrePlacements {
    public static final DeferredRegister<PlacementModifierType<?>> TYPES = DeferredRegister.create(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, Erebus.MODID);
    public static final DeferredHolder<PlacementModifierType<?>, PlacementModifierType<OreCountPlacement>> ORE_COUNT = TYPES.register("ore_count", () -> () -> OreCountPlacement.CODEC);

    private ModOrePlacements() {
    }
}
