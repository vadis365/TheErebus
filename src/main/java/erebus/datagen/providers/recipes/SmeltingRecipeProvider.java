package erebus.datagen.providers.recipes;

import erebus.registries.ModBlocks;
import erebus.registries.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

/**
 * Provider for smelting recipes (furnace, blast furnace).
 */
public class SmeltingRecipeProvider extends ErebusRecipeProvider {

    public SmeltingRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries);
    }

    @Override
    public void buildRecipes() {
        addOreSmeltingRecipes();
        addGenericSmelting();
    }

    private void addOreSmeltingRecipes() {
        ore(ModBlocks.ORE_COAL, Items.COAL, "coal");
        ore(ModBlocks.ORE_IRON, Items.IRON_INGOT, "iron");
        ore(ModBlocks.ORE_GOLD, Items.GOLD_INGOT, "gold");
        ore(ModBlocks.ORE_LAPIS, Items.LAPIS_LAZULI, "lapis");
        ore(ModBlocks.ORE_DIAMOND, Items.DIAMOND, "diamond");
        ore(ModBlocks.ORE_EMERALD, Items.EMERALD, "emerald");
        ore(ModBlocks.ORE_QUARTZ, Items.QUARTZ, "quartz");
        ore(ModBlocks.ORE_PETRIFIED_QUARTZ, Items.QUARTZ, "quartz");
        ore(ModBlocks.ORE_JADE, ModItems.JADE, "jade");
        ore(ModBlocks.ORE_FOSSIL, ModItems.SHARD_BONE, "shard_bone");
        ore(ModBlocks.ORE_GNEISS, ModItems.GNEISS_ROCK, "gneiss");
        ore(ModBlocks.ORE_PETRIFIED_WOOD, ModItems.PETRIFIED_WOOD, "petrified_wood");
        ore(ModBlocks.ORE_ENCRUSTED_DIAMOND, Items.DIAMOND, "diamond");
    }

    private void addGenericSmelting() {
        smelting(ModBlocks.AMBER, ModBlocks.AMBER_GLASS);
        smelting(ModBlocks.UMBERSTONE, ModBlocks.UMBERPAVER);
        smelting(ModBlocks.MUD, ModBlocks.MUD_BRICKS);
        smelting(ModItems.NECTAR, ModItems.HONEY_DRIP);
        smelting(ModBlocks.LOG_ASPER, Items.CHARCOAL);
        smelting(ModBlocks.LOG_BALSAM, Items.CHARCOAL);
        smelting(ModBlocks.LOG_BAOBAB, Items.CHARCOAL);
        smelting(ModBlocks.LOG_CYPRESS, Items.CHARCOAL);
        smelting(ModBlocks.LOG_EUCALYPTUS, Items.CHARCOAL);
        smelting(ModBlocks.LOG_MARSHWOOD, Items.CHARCOAL);
        smelting(ModBlocks.LOG_SCORCHED, Items.CHARCOAL);
        smelting(ModBlocks.LOG_MOSSBARK, Items.CHARCOAL);
        smelting(ModBlocks.LOG_MAHOGANY, Items.CHARCOAL);
    }
}