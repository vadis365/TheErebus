package erebus.datagen.providers.recipes;

import erebus.registries.blocks.providers.UmberstoneBlocks;
import erebus.registries.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.RecipeCategory.*;

/**
 * Provider for shaped crafting recipes related to tools and weapons.
 */
public class ShapedToolsAndWeaponsRecipeProvider extends ErebusRecipeProvider {

    public ShapedToolsAndWeaponsRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries);
    }

    @Override
    public void buildRecipes(@NotNull RecipeOutput output) {
        this.output = output;
        addJadeToolRecipes();
        addSpecialToolRecipes();
    }

    private void addJadeToolRecipes() {
        ShapedRecipeBuilder.shaped(TOOLS, ModItems.JADE_PICKAXE)
                .pattern("JJJ")
                .pattern(" S ")
                .pattern(" S ")
                .define('J', ModItems.JADE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_jade", has(ModItems.JADE))
                .save(output);

        ShapedRecipeBuilder.shaped(TOOLS, ModItems.JADE_SHOVEL)
                .pattern(" J ")
                .pattern(" S ")
                .pattern(" S ")
                .define('J', ModItems.JADE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_jade", has(ModItems.JADE))
                .save(output);

        ShapedRecipeBuilder.shaped(TOOLS, ModItems.JADE_AXE)
                .pattern("JJ ")
                .pattern("JS ")
                .pattern(" S ")
                .define('J', ModItems.JADE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_jade", has(ModItems.JADE))
                .save(output);

        ShapedRecipeBuilder.shaped(TOOLS, ModItems.JADE_HOE)
                .pattern("JJ ")
                .pattern(" S ")
                .pattern(" S ")
                .define('J', ModItems.JADE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_jade", has(ModItems.JADE))
                .save(output);

        ShapedRecipeBuilder.shaped(TOOLS, ModItems.JADE_SWORD)
                .pattern(" J ")
                .pattern(" J ")
                .pattern(" S ")
                .define('J', ModItems.JADE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_jade", has(ModItems.JADE))
                .save(output);

        ShapedRecipeBuilder.shaped(TOOLS, ModItems.JADE_PAXEL)
                .pattern("AHP")
                .pattern(" S ")
                .pattern(" S ")
                .define('A', ModItems.JADE_AXE)
                .define('H', ModItems.JADE_SHOVEL)
                .define('P', ModItems.JADE_PICKAXE)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_jade", has(ModItems.JADE))
                .save(output);
    }

    private void addSpecialToolRecipes() {
        ShapedRecipeBuilder.shaped(COMBAT, ModItems.ENHANCED_SCORPION_PINCER)
                .pattern("I I")
                .pattern("EIE")
                .pattern("EPE")
                .define('I', Items.IRON_INGOT)
                .define('E', ModItems.REINFORCED_PLATE_EXO)
                .define('P', ModItems.SCORPION_PINCER)
                .unlockedBy("has_scorpion_pincer", has(ModItems.SCORPION_PINCER))
                .save(output);

        ShapedRecipeBuilder.shaped(COMBAT, ModItems.ROLLED_NEWSPAPER)
                .pattern("PWP")
                .pattern("PBP")
                .pattern("PWP")
                .define('P', ModItems.PAPYRUS)
                .define('B', Tags.Items.DYES_BLACK)
                .define('W', ModItems.WHETSTONE_POWDER)
                .unlockedBy("has_papyrus", has(ModItems.PAPYRUS))
                .unlockedBy("has_whetstone_powder", has(ModItems.WHETSTONE_POWDER))
                .save(output);

        ShapedRecipeBuilder.shaped(TOOLS, ModItems.HOMING_BEECON)
                .pattern("GNG")
                .pattern("NCN")
                .pattern("GNG")
                .define('G', Items.GOLD_INGOT)
                .define('N', ModItems.NECTAR)
                .define('C', Items.COMPASS)
                .unlockedBy("has_nectar", has(ModItems.NECTAR))
                .save(output);

        ShapedRecipeBuilder.shaped(TOOLS, ModItems.NECTAR_COLLECTOR)
                .pattern("  B")
                .pattern(" S ")
                .pattern("S  ")
                .define('B', Items.BOWL)
                .define('S', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(output);

        ShapedRecipeBuilder.shaped(TOOLS, ModItems.PORTAL_ACTIVATOR)
                .pattern("VSG")
                .pattern("VSS")
                .pattern("IVV")
                .define('V', Blocks.VINE)
                .define('S', Tags.Items.RODS_WOODEN)
                .define('I', Items.GOLD_INGOT)
                .define('G', ModItems.GAEAN_GEM)
                .unlockedBy("has_gaean_gem", has(ModItems.GAEAN_GEM))
                .save(output);

        ShapedRecipeBuilder.shaped(TOOLS, ModItems.ANT_TAMING_AMULET)
                .pattern("PGP")
                .pattern("GOG")
                .pattern("PGP")
                .define('P', ModItems.ANT_PHEROMONES)
                .define('G', Items.GOLD_INGOT)
                .define('O', Blocks.OBSIDIAN)
                .unlockedBy("has_ant_pheremones", has(ModItems.ANT_PHEROMONES))
                .save(output);

        ShapedRecipeBuilder.shaped(MISC, ModItems.WHETSTONE)
                .pattern("SSS")
                .pattern("PPP")
                .pattern("UUU")
                .define('S', ItemTags.SAND)
                .define('U', UmberstoneBlocks.UMBERSTONE)
                .define('P', ModItems.PETRIFIED_WOOD)
                .unlockedBy("has_petrified_wood", has(ModItems.PETRIFIED_WOOD))
                .save(output);

        ShapedRecipeBuilder.shaped(TOOLS, ModItems.WAND_OF_ANIMATION)
                .pattern(" GC")
                .pattern(" SG")
                .pattern("G  ")
                .define('G', Tags.Items.INGOTS_GOLD)
                .define('C', ModItems.SOUL_CRYSTAL)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_soul_crystal", has(ModItems.SOUL_CRYSTAL))
                .save(output);
    }
}
