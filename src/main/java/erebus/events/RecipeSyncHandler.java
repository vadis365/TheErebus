package erebus.events;

import erebus.Erebus;
import erebus.registries.ModCustomRecipes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public final class RecipeSyncHandler {
    private RecipeSyncHandler() {
    }

    @SubscribeEvent
    public static void syncRecipes(OnDatapackSyncEvent event) {
        event.sendRecipes(ModCustomRecipes.SMOOTHIE_RECIPE.get(), ModCustomRecipes.OFFERING_ALTAR_RECIPE.get());
    }
}
