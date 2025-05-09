package erebus.datagen.providers.recipes;

import erebus.registries.ModItems;
import erebus.registries.blocks.providers.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * Provider for smelting recipes (furnace, blast furnace).
 */
public class SmeltingRecipeProvider extends ErebusRecipeProvider {

    public SmeltingRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries);
    }

    @Override
    public void buildRecipes(@NotNull RecipeOutput output) {
        this.output = output;
        addOreSmeltingRecipes();
        addGenericSmelting();
    }

    private void addOreSmeltingRecipes() {
        ore(OreBlocks.ORE_COAL, Items.COAL, "coal");
        ore(OreBlocks.ORE_IRON, Items.IRON_INGOT, "iron");
        ore(OreBlocks.ORE_GOLD, Items.GOLD_INGOT, "gold");
        ore(OreBlocks.ORE_LAPIS, Items.LAPIS_LAZULI, "lapis");
        ore(OreBlocks.ORE_DIAMOND, Items.DIAMOND, "diamond");
        ore(OreBlocks.ORE_EMERALD, Items.EMERALD, "emerald");
        ore(OreBlocks.ORE_QUARTZ, Items.QUARTZ, "quartz");
        ore(OreBlocks.ORE_PETRIFIED_QUARTZ, Items.QUARTZ, "quartz");
        ore(OreBlocks.ORE_JADE, ModItems.JADE, "jade");
        ore(OreBlocks.ORE_FOSSIL, ModItems.SHARD_BONE, "shard_bone");
        ore(OreBlocks.ORE_GNEISS, ModItems.GNEISS_ROCK, "gneiss");
        ore(OreBlocks.ORE_PETRIFIED_WOOD, ModItems.PETRIFIED_WOOD, "petrified_wood");
        ore(OreBlocks.ORE_ENCRUSTED_DIAMOND, Items.DIAMOND, "diamond");
    }

    private void addGenericSmelting() {
        smelting(AmberBlocks.AMBER, AmberBlocks.AMBER_GLASS);
        smelting(UmberstoneBlocks.UMBERSTONE, UmberstoneBlocks.UMBERPAVER);
        smelting(OtherBlocks.MUD, UmberstoneBlocks.MUD_BRICKS);
        smelting(ModItems.NECTAR, ModItems.HONEY_DRIP);
        smelting(WoodBlocks.LOG_ASPER, Items.CHARCOAL);
        smelting(WoodBlocks.LOG_BALSAM, Items.CHARCOAL);
        smelting(WoodBlocks.LOG_BAOBAB, Items.CHARCOAL);
        smelting(WoodBlocks.LOG_CYPRESS, Items.CHARCOAL);
        smelting(WoodBlocks.LOG_EUCALYPTUS, Items.CHARCOAL);
        smelting(WoodBlocks.LOG_MARSHWOOD, Items.CHARCOAL);
        smelting(WoodBlocks.LOG_SCORCHED, Items.CHARCOAL);
        smelting(WoodBlocks.LOG_MOSSBARK, Items.CHARCOAL);
        smelting(WoodBlocks.LOG_MAHOGANY, Items.CHARCOAL);
    }
}