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
        ore(ModBlocks.ORE_COAL, Items.COAL, "coal", 0.1F);
        ore(ModBlocks.ORE_IRON, Items.IRON_INGOT, "iron", 0.7F);
        ore(ModBlocks.ORE_GOLD, Items.GOLD_INGOT, "gold", 1.0F);
        ore(ModBlocks.ORE_LAPIS, Items.LAPIS_LAZULI, "lapis", 0.2F);
        ore(ModBlocks.ORE_DIAMOND, Items.DIAMOND, "diamond", 1.0F);
        ore(ModBlocks.ORE_EMERALD, Items.EMERALD, "emerald", 1.0F);
        ore(ModBlocks.ORE_QUARTZ, Items.QUARTZ, "quartz", 1.0F);
        ore(ModBlocks.ORE_PETRIFIED_QUARTZ, Items.QUARTZ, "quartz", 1.0F);
        ore(ModBlocks.ORE_JADE, ModItems.JADE, "jade", 1.0F);
        ore(ModBlocks.ORE_FOSSIL, ModItems.SHARD_BONE, "shard_bone", 0.1F);
        ore(ModBlocks.ORE_GNEISS, ModItems.GNEISS_ROCK, "gneiss", 0.1F);
        ore(ModBlocks.ORE_PETRIFIED_WOOD, ModItems.PETRIFIED_WOOD, "petrified_wood", 0.1F);
        ore(ModBlocks.ORE_ENCRUSTED_DIAMOND, Items.DIAMOND, "diamond", 1.0F, 2);
        ore(ModBlocks.ORE_COPPER, Items.COPPER_INGOT, "copper", 1.0F);
        ore(ModBlocks.ORE_SILVER, ModItems.INGOT_SILVER, "silver", 1.0F);
        ore(ModBlocks.ORE_LEAD, ModItems.INGOT_LEAD, "lead", 1.0F);
        ore(ModBlocks.ORE_TIN, ModItems.INGOT_TIN, "tin", 1.0F);
        ore(ModBlocks.ORE_ALUMINUM, ModItems.INGOT_ALUMINUM, "aluminum", 1.0F);
    }

    private void addGenericSmelting() {
        materialSmelting(ModBlocks.AMBER, ModBlocks.AMBER_GLASS, 0.3F);
        materialSmelting(ModBlocks.UMBERCOBBLE, ModBlocks.UMBERSTONE, 0.2F);
        smelting(ModBlocks.UMBERSTONE, ModBlocks.UMBERPAVER);
        materialSmelting(ModBlocks.MUD, ModItems.MUD_BRICK, 0.2F);
        materialSmelting(ModItems.NECTAR, ModItems.HONEY_DRIP, 0.2F);

        // Logs -> charcoal MUST have unique recipe IDs (otherwise they all collide as "minecraft:charcoal")
        smeltToCharcoal(ModBlocks.LOG_ASPER);
        smeltToCharcoal(ModBlocks.LOG_BALSAM);
        smeltToCharcoal(ModBlocks.LOG_BALSAM_RESINLESS);
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

    private void materialSmelting(ItemLike ingredient, ItemLike result, float experience) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.BUILDING_BLOCKS,
                        CookingBookCategory.BLOCKS, result, experience, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(output);
    }
}
