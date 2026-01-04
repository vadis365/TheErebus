package erebus.datagen.providers.recipes;

import erebus.registries.blocks.providers.*;
import erebus.registries.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;

/**
 * Provider for smelting recipes (furnace, blast furnace).
 */
public class SmeltingRecipeProvider extends ErebusRecipeProvider {

    public SmeltingRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        addOreSmeltingRecipes();
        addGenericSmelting();
    }

    private void addOreSmeltingRecipes() {
        ore(OreBlocks.COAL, Items.COAL, "coal");
        ore(OreBlocks.IRON, Items.IRON_INGOT, "iron");
        ore(OreBlocks.GOLD, Items.GOLD_INGOT, "gold");
        ore(OreBlocks.LAPIS, Items.LAPIS_LAZULI, "lapis");
        ore(OreBlocks.DIAMOND, Items.DIAMOND, "diamond");
        ore(OreBlocks.EMERALD, Items.EMERALD, "emerald");
        ore(OreBlocks.QUARTZ, Items.QUARTZ, "quartz");
        ore(OreBlocks.PETRIFIED_QUARTZ, Items.QUARTZ, "quartz");
        ore(OreBlocks.JADE, ModItems.JADE, "jade");
        ore(OreBlocks.FOSSIL, ModItems.SHARD_BONE, "shard_bone");
        ore(OreBlocks.GNEISS, ModItems.GNEISS_ROCK, "gneiss");
        ore(OreBlocks.PETRIFIED_WOOD, ModItems.PETRIFIED_WOOD, "petrified_wood");
        ore(OreBlocks.ENCRUSTED_DIAMOND, Items.DIAMOND, "diamond");
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