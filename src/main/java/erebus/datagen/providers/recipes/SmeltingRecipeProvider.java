package erebus.datagen.providers.recipes;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

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

        // Logs -> charcoal MUST have unique recipe IDs (otherwise they all collide as "minecraft:charcoal")
        smeltToCharcoal(ModBlocks.LOG_ASPER);
        smeltToCharcoal(ModBlocks.LOG_BALSAM);
        smeltToCharcoal(ModBlocks.LOG_BAOBAB);
        smeltToCharcoal(ModBlocks.LOG_CYPRESS);
        smeltToCharcoal(ModBlocks.LOG_EUCALYPTUS);
        smeltToCharcoal(ModBlocks.LOG_MARSHWOOD);
        smeltToCharcoal(ModBlocks.LOG_SCORCHED);
        smeltToCharcoal(ModBlocks.LOG_MOSSBARK);
        smeltToCharcoal(ModBlocks.LOG_MAHOGANY);
    }

    private void smeltToCharcoal(ItemLike log) {
        String ingredientPath = BuiltInRegistries.ITEM.getKey(log.asItem()).getPath();

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(log), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.CHARCOAL, 0.15F, 200)
                .unlockedBy("has_%s".formatted(ingredientPath), has(log))
                .save(output, "charcoal_from_%s".formatted(ingredientPath));
    }
}
