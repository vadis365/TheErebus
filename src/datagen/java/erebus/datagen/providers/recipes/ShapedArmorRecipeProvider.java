package erebus.datagen.providers.recipes;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.tags.ModItemTags;
import erebus.registries.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

import static net.minecraft.data.recipes.RecipeCategory.COMBAT;

/**
 * Provider for shaped crafting recipes related to armor and equipment.
 */
public class ShapedArmorRecipeProvider extends ErebusRecipeProvider {

    public ShapedArmorRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        addJadeArmorRecipes();
        addExoskeletonArmorRecipes();
        addReinforcedExoskeletonArmorRecipes();
        addRhinoExoskeletonArmorRecipes();
        addSpecialArmorRecipes();
        addBambooArmorRecipes();
        addShieldRecipes();
    }

    private void addJadeArmorRecipes() {
        jadeArmor(ModItems.JADE_HELMET, "helmet", "MMM", "M M");
        jadeArmor(ModItems.JADE_CHESTPLATE, "chestplate", "M M", "MMM", "MMM");
        jadeArmor(ModItems.JADE_LEGGINGS, "leggings", "MMM", "M M", "M M");
        jadeArmor(ModItems.JADE_BOOTS, "boots", "M M", "M M");
    }

    private void jadeArmor(ItemLike result, String kind, String... pattern) {
        var recipe = shaped(COMBAT, result);
        for (var row : pattern) recipe.pattern(row);
        recipe.define('M', ModItemTags.GEMS_JADE).unlockedBy("has_material", has(ModItemTags.GEMS_JADE))
                .save(output, kind + "_" + result.asItem().getDescriptionId().toLowerCase(java.util.Locale.ROOT));
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
        shaped(COMBAT, ModItems.RHINO_EXOSKELETON_HELMET)
                .pattern("H H")
                .pattern("PPP")
                .pattern("P P")
                .define('H', ModItems.RHINO_BEETLE_HORN)
                .define('P', ModItems.PLATE_EXO_RHINO)
                .unlockedBy("has_rhino_plate", has(ModItems.PLATE_EXO_RHINO))
                .save(output, "minecraft:helmet_item.erebus.rhino_exoskeleton_helmet");
        chestplate(ModItems.PLATE_EXO_RHINO, ModItems.RHINO_EXOSKELETON_CHESTPLATE);
        leggings(ModItems.PLATE_EXO_RHINO, ModItems.RHINO_EXOSKELETON_LEGGINGS);
        boots(ModItems.PLATE_EXO_RHINO, ModItems.RHINO_EXOSKELETON_BOOTS);
    }

    private void addSpecialArmorRecipes() {
        surround(ModItems.COMPOUND_EYES, ModBlocks.AMBER_GLASS, ModItems.COMPOUND_LENS);

        shaped(COMBAT, ModItems.COMPOUND_GOGGLES)
                .pattern("EEE")
                .pattern("LEL")
                .define('E', ModItems.PLATE_EXO)
                .define('L', ModItems.COMPOUND_LENS)
                .unlockedBy("has_compound_lens", has(ModItems.COMPOUND_LENS))
                .save(output);

        shaped(COMBAT, ModItems.REIN_COMPOUND_GOGGLES)
                .pattern("RRR")
                .pattern("RGR")
                .define('R', ModItems.REINFORCED_PLATE_EXO)
                .define('G', ModItems.COMPOUND_GOGGLES)
                .unlockedBy("has_compound_goggles", has(ModItems.COMPOUND_GOGGLES))
                .save(output);

        shaped(COMBAT, ModItems.JUMP_BOOTS)
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

        shaped(COMBAT, ModItems.GLIDER_CHESTPLATE)
                .pattern("WCW")
                .define('W', ModItems.GLIDER_WING)
                .define('C', ModItems.REIN_EXOSKELETON_CHESTPLATE)
                .unlockedBy("has_glider_wing", has(ModItems.GLIDER_WING))
                .unlockedBy("has_rein_exo_chest", has(ModItems.REIN_EXOSKELETON_CHESTPLATE))
                .save(output);

        shaped(COMBAT, ModItems.GLIDER_CHESTPLATE_POWERED)
                .pattern("W W")
                .pattern("FGF")
                .pattern(" V ")
                .define('W', ModItems.ENHANCED_GLIDER_WING)
                .define('F', ModItems.ELASTIC_FIBER)
                .define('G', ModItems.GLIDER_CHESTPLATE)
                .define('V', ModBlocks.VELOCITY_BLOCK)
                .unlockedBy("has_enhanced_glider_wing", has(ModItems.ENHANCED_GLIDER_WING))
                .unlockedBy("has_elastic_fiber", has(ModItems.ELASTIC_FIBER))
                .unlockedBy("has_glider_chestplate", has(ModItems.GLIDER_CHESTPLATE))
                .unlockedBy("has_velocity_block", has(ModBlocks.VELOCITY_BLOCK))
                .save(output);

        surround(ModItems.WATER_REPELLENT, ModItems.REIN_EXOSKELETON_BOOTS, ModItems.WATER_STRIDERS);

        shaped(COMBAT, ModItems.MUSHROOM_HELMET)
                .pattern("HHH")
                .pattern("HPH")
                .define('H', ModItems.HIDE_SHROOM)
                .define('P', Blocks.PUMPKIN)
                .unlockedBy("has_shroom_hide", has(ModItems.HIDE_SHROOM))
                .save(output);
    }

    private void addBambooArmorRecipes() {
        helmet(ModBlocks.PLANKS_BAMBOO, ModItems.BAMBOO_HELMET);
        chestplate(ModBlocks.PLANKS_BAMBOO, ModItems.BAMBOO_CHESTPLATE);
        leggings(ModBlocks.PLANKS_BAMBOO, ModItems.BAMBOO_LEGGINGS);
        boots(ModBlocks.PLANKS_BAMBOO, ModItems.BAMBOO_BOOTS);
    }

    private void addShieldRecipes() {
        shield(ModItems.PLATE_EXO, ModItems.EXOSKELETON_SHIELD);
        shaped(COMBAT, ModItems.JADE_SHIELD).pattern("XIX").pattern("XXX").pattern(" X ")
                .define('X', ModItemTags.GEMS_JADE).define('I', Tags.Items.INGOTS_IRON)
                .unlockedBy("has_material", has(ModItemTags.GEMS_JADE)).save(output);
        shield(ModItems.REINFORCED_PLATE_EXO, ModItems.REIN_EXOSKELETON_SHIELD);
        shield(ModItems.PLATE_EXO_RHINO, ModItems.RHINO_EXOSKELETON_SHIELD);
        shaped(COMBAT, ModItems.BAMBOO_SHIELD)
                .pattern("BIB")
                .pattern("BBB")
                .pattern(" B ")
                .define('B', ModItems.BAMBOO)
                .define('I', Tags.Items.INGOTS_IRON)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);
    }

    private void shield(ItemLike material, ItemLike result) {
        shaped(COMBAT, result).pattern("XIX").pattern("XXX").pattern(" X ")
                .define('X', material).define('I', Tags.Items.INGOTS_IRON)
                .unlockedBy("has_material", has(material)).save(output);
    }
}
