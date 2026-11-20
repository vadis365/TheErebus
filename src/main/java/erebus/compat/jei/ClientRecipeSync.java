package erebus.compat.jei;

import erebus.Erebus;
import net.minecraft.world.item.crafting.RecipeMap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;

@EventBusSubscriber(modid = Erebus.MODID, value = Dist.CLIENT)
public final class ClientRecipeSync {
    private static RecipeMap recipes = RecipeMap.EMPTY;

    private ClientRecipeSync() {
    }

    static RecipeMap recipes() {
        return recipes;
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void receiveRecipes(RecipesReceivedEvent event) {
        recipes = event.getRecipeMap();
    }

    @SubscribeEvent
    public static void clearRecipes(ClientPlayerNetworkEvent.LoggingOut event) {
        recipes = RecipeMap.EMPTY;
    }
}
