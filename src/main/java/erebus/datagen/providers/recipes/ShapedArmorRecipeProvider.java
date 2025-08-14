package erebus.datagen.providers.recipes;

import erebus.registries.ModItems;
import erebus.registries.blocks.providers.AmberBlocks;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.blocks.providers.WoodBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.RecipeCategory.COMBAT;

/**
 * Provider for shaped crafting recipes related to armor and equipment.
 */
public class ShapedArmorRecipeProvider extends ErebusRecipeProvider {

    public ShapedArmorRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries);
    }

    @Override
    public void buildRecipes(@NotNull RecipeOutput output) {
        this.output = output;
        addJadeArmorRecipes();
        addExoskeletonArmorRecipes();
        addReinforcedExoskeletonArmorRecipes();
        addRhinoExoskeletonArmorRecipes();
        addSpecialArmorRecipes();
        addBambooArmorRecipes();
    }

    private void addJadeArmorRecipes() {
        helmet(ModItems.JADE, ModItems.JADE_HELMET);
        chestplate(ModItems.JADE, ModItems.JADE_CHESTPLATE);
        leggings(ModItems.JADE, ModItems.JADE_LEGGINGS);
        boots(ModItems.JADE, ModItems.JADE_BOOTS);
    }

    private void addExoskeletonArmorRecipes() {
        helmet(ModItems.PLATE_EXO, ModItems.EXOSKELETON_HELMET);
        chestplate(ModItems.PLATE_EXO, ModItems.EXOSKELETON_CHESTPLATE);
        leggings(ModItems.PLATE_EXO, ModItems.EXOSKELETON_LEGGINGS);
        boots(ModItems.PLATE_EXO, ModItems.EXOSKELETON_BOOTS);
    }

    private void addReinforcedExoskeletonArmorRecipes() {
        helmet(ModItems.REINFORCED_PLATE_EXO, ModItems.REIN_EXOSKELETON_HELMET);
        chestplate(ModItems.REINFORCED_PLATE_EXO, ModItems.REIN_EXOSKELETON_CHESTPLATE);
        leggings(ModItems.REINFORCED_PLATE_EXO, ModItems.REIN_EXOSKELETON_LEGGINGS);
        boots(ModItems.REINFORCED_PLATE_EXO, ModItems.REIN_EXOSKELETON_BOOTS);
    }

    private void addRhinoExoskeletonArmorRecipes() {
        helmet(ModItems.PLATE_EXO_RHINO, ModItems.RHINO_EXOSKELETON_HELMET);
        chestplate(ModItems.PLATE_EXO_RHINO, ModItems.RHINO_EXOSKELETON_CHESTPLATE);
        leggings(ModItems.PLATE_EXO_RHINO, ModItems.RHINO_EXOSKELETON_LEGGINGS);
        boots(ModItems.PLATE_EXO_RHINO, ModItems.RHINO_EXOSKELETON_BOOTS);
    }

    private void addSpecialArmorRecipes() {
        surround(ModItems.COMPOUND_EYES, AmberBlocks.AMBER, ModItems.COMPOUND_LENS);

        ShapedRecipeBuilder.shaped(COMBAT, ModItems.COMPOUND_GOGGLES)
                .pattern("EEE")
                .pattern("LEL")
                .define('E', ModItems.PLATE_EXO)
                .define('L', ModItems.COMPOUND_LENS)
                .unlockedBy("has_compound_lens", has(ModItems.COMPOUND_LENS))
                .save(output);

        ShapedRecipeBuilder.shaped(COMBAT, ModItems.REIN_COMPOUND_GOGGLES)
                .pattern("RRR")
                .pattern("RGR")
                .define('R', ModItems.REINFORCED_PLATE_EXO)
                .define('G', ModItems.COMPOUND_GOGGLES)
                .unlockedBy("has_compound_goggles", has(ModItems.COMPOUND_GOGGLES))
                .save(output);

        ShapedRecipeBuilder.shaped(COMBAT, ModItems.JUMP_BOOTS)
                .pattern("W W")
                .pattern("FBF")
                .pattern("F F")
                .define('W', ModItems.FLY_WING)
                .define('F', ModItems.ELASTIC_FIBER)
                .define('B', ModItems.REIN_EXOSKELETON_BOOTS)
                .unlockedBy("has_elastic_fiber", has(ModItems.ELASTIC_FIBER))
                .unlockedBy("has_rein_exo_boots", has(ModItems.REIN_EXOSKELETON_BOOTS))
                .unlockedBy("has_fly_wing", has(ModItems.FLY_WING))
                .save(output);

        surround(ModItems.BIO_VELOCITY, ModItems.REIN_EXOSKELETON_LEGGINGS, ModItems.SPRINT_LEGGINGS);

        ShapedRecipeBuilder.shaped(COMBAT, ModItems.GLIDER_CHESTPLATE)
                .pattern("WCW")
                .define('W', ModItems.GLIDER_WING)
                .define('C', ModItems.REIN_EXOSKELETON_CHESTPLATE)
                .unlockedBy("has_glider_wing", has(ModItems.GLIDER_WING))
                .unlockedBy("has_rein_exo_chest", has(ModItems.REIN_EXOSKELETON_CHESTPLATE))
                .save(output);

        ShapedRecipeBuilder.shaped(COMBAT, ModItems.GLIDER_CHESTPLATE_POWERED)
                .pattern("W W")
                .pattern("FGF")
                .pattern(" V ")
                .define('W', ModItems.ENHANCED_GLIDER_WING)
                .define('F', ModItems.ELASTIC_FIBER)
                .define('G', ModItems.GLIDER_CHESTPLATE)
                .define('V', OtherBlocks.VELOCITY_BLOCK)
                .unlockedBy("has_enhanced_glider_wing", has(ModItems.ENHANCED_GLIDER_WING))
                .unlockedBy("has_elastic_fiber", has(ModItems.ELASTIC_FIBER))
                .unlockedBy("has_glider_chestplate", has(ModItems.GLIDER_CHESTPLATE))
                .unlockedBy("has_velocity_block", has(OtherBlocks.VELOCITY_BLOCK))
                .save(output);

        surround(ModItems.WATER_REPELLENT, ModItems.REIN_EXOSKELETON_BOOTS, ModItems.WATER_STRIDERS);

        ShapedRecipeBuilder.shaped(COMBAT, ModItems.MUSHROOM_HELMET)
                .pattern("HHH")
                .pattern("HPH")
                .define('H', ModItems.HIDE_SHROOM)
                .define('P', Blocks.PUMPKIN)
                .unlockedBy("has_compound_goggles", has(ModItems.COMPOUND_GOGGLES))
                .save(output);
    }

    private void addBambooArmorRecipes() {
        helmet(WoodBlocks.PLANKS_BAMBOO, ModItems.BAMBOO_HELMET);
        chestplate(WoodBlocks.PLANKS_BAMBOO, ModItems.BAMBOO_CHESTPLATE);
        leggings(WoodBlocks.PLANKS_BAMBOO, ModItems.BAMBOO_LEGGINGS);
        boots(WoodBlocks.PLANKS_BAMBOO, ModItems.BAMBOO_BOOTS);

        ShapedRecipeBuilder.shaped(COMBAT, ModItems.BAMBOO_SHIELD)
                .pattern("BIB")
                .pattern("BBB")
                .pattern(" B ")
                .define('B', ModItems.BAMBOO)
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);
    }
}