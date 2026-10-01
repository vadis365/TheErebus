package erebus.registries.world;

import com.google.common.collect.ImmutableSet;
import erebus.Erebus;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPOIs {

    public static final DeferredRegister<PoiType> POI = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, Erebus.MODID);
    public static final DeferredHolder<PoiType, PoiType> GAEAN_KEYSTONE = POI.register("gaean_keystone", () -> new PoiType(ImmutableSet.copyOf(ModBlocks.GAEAN_KEYSTONE.value().getStateDefinition().getPossibleStates()), 0, 1));
}
