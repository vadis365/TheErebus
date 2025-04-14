package erebus.datagen.providers;

import erebus.registries.ModBlocks;
import erebus.registries.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.RecipeCategory.*;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private RecipeOutput output;

    public ModRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        this.output = output;

        addCookingRecipes();
        addOreSmeltingRecipes();
        addGenericSmelting();

        addShapedCraftingRecipes();
        addShapelessCraftingRecipes();
    }

    private void addCookingRecipes() {
        cook(ModItems.BEETLE_LARVA_RAW, ModItems.BEETLE_LARVA_COOKED);
        cook(ModItems.GRASSHOPPER_LEG_RAW, ModItems.GRASSHOPPER_LEG_COOKED);
        cook(ModItems.TARANTULA_LEG_RAW, ModItems.TARANTULA_LEG_COOKED);
        cook(ModItems.TITAN_CHOP_RAW, ModItems.TITAN_CHOP_COOKED);
        cook(ModItems.PRICKLY_PEAR_RAW, ModItems.PRICKLY_PEAR_COOKED);
        cook(ModItems.TITAN_STEW, ModItems.TITAN_STEW_COOKED);
        cook(ModItems.STAG_HEART_RAW, ModItems.STAG_HEART_COOKED);
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

    private void addShapedCraftingRecipes() {
        twoByTwo(ModBlocks.UMBERCOBBLE, ModBlocks.UMBERPAVER, 4);
        twoByTwo(ModBlocks.UMBERCOBBLE_MOSSY, ModBlocks.UMBERPAVER_MOSSY, 4);
        twoByTwo(ModBlocks.UMBERCOBBLE_WEBBED, ModBlocks.UMBERPAVER_WEBBED, 4);
        twoByTwo(ModBlocks.UMBERSTONE, ModBlocks.UMBERSTONE_BRICKS, 4);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ModBlocks.UMBERTILE_SMOOTH, 9)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModBlocks.UMBERSTONE)
                .unlockedBy("has_umberstone", has(ModBlocks.UMBERSTONE))
                .save(output);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ModBlocks.UMBERSTONE_PILLAR, 2)
                .pattern("#")
                .pattern("#")
                .define('#', ModBlocks.UMBERSTONE)
                .unlockedBy("has_umberstone", has(ModBlocks.UMBERSTONE))
                .save(output);

        ShapedRecipeBuilder.shaped(DECORATIONS, ModBlocks.UMBER_FURNACE)
                .pattern("SSS")
                .pattern("SBS")
                .pattern("SSS")
                .define('S', ModBlocks.UMBERCOBBLE)
                .define('B', Items.BUCKET)
                .unlockedBy("has_umbercobble", has(ModBlocks.UMBERCOBBLE))
                .save(output);

        slab(ModBlocks.PLANKS_BAOBAB, ModBlocks.SLAB_PLANKS_BAOBAB);
        slab(ModBlocks.PLANKS_EUCALYPTUS, ModBlocks.SLAB_PLANKS_EUCALYPTUS);
        slab(ModBlocks.PLANKS_MAHOGANY, ModBlocks.SLAB_PLANKS_MAHOGANY);
        slab(ModBlocks.PLANKS_MOSSBARK, ModBlocks.SLAB_PLANKS_MOSSBARK);
        slab(ModBlocks.PLANKS_ASPER, ModBlocks.SLAB_PLANKS_ASPER);
        slab(ModBlocks.PLANKS_CYPRESS, ModBlocks.SLAB_PLANKS_CYPRESS);
        slab(ModBlocks.PLANKS_BALSAM, ModBlocks.SLAB_PLANKS_BALSAM);
        slab(ModBlocks.PLANKS_WHITE, ModBlocks.SLAB_PLANKS_WHITE);
        slab(ModBlocks.PLANKS_BAMBOO, ModBlocks.SLAB_PLANKS_BAMBOO);
        slab(ModBlocks.PLANKS_ROTTEN, ModBlocks.SLAB_PLANKS_ROTTEN);
        slab(ModBlocks.PLANKS_MARSHWOOD, ModBlocks.SLAB_PLANKS_MARSHWOOD);
        slab(ModBlocks.PLANKS_SCORCHED, ModBlocks.SLAB_PLANKS_SCORCHED);
        slab(ModBlocks.PLANKS_VARNISHED, ModBlocks.SLAB_PLANKS_VARNISHED);
        slab(ModBlocks.PLANKS_PETRIFIED, ModBlocks.SLAB_PLANKS_PETRIFIED);

        slab(ModBlocks.UMBERSTONE, ModBlocks.SLAB_UMBERSTONE);
        slab(ModBlocks.UMBERCOBBLE, ModBlocks.SLAB_UMBERCOBBLE);
        slab(ModBlocks.UMBERCOBBLE_MOSSY, ModBlocks.SLAB_UMBERCOBBLE_MOSSY);
        slab(ModBlocks.UMBERCOBBLE_WEBBED, ModBlocks.SLAB_UMBERCOBBLE_WEBBED);
        slab(ModBlocks.UMBERSTONE_BRICKS, ModBlocks.SLAB_UMBERSTONE_BRICKS);
        slab(ModBlocks.UMBERTILE_SMOOTH, ModBlocks.SLAB_UMBERTILE_SMOOTH);
        slab(ModBlocks.UMBERTILE_SMOOTH_SMALL, ModBlocks.SLAB_UMBERTILE_SMOOTH_SMALL);
        slab(ModBlocks.UMBERPAVER, ModBlocks.SLAB_UMBERPAVER);
        slab(ModBlocks.UMBERPAVER_MOSSY, ModBlocks.SLAB_UMBERPAVER_MOSSY);
        slab(ModBlocks.UMBERPAVER_WEBBED, ModBlocks.SLAB_UMBERPAVER_WEBBED);
        slab(ModBlocks.AMBER, ModBlocks.SLAB_AMBER);
        slab(ModBlocks.AMBER_BRICKS, ModBlocks.SLAB_AMBER_BRICKS);
        slab(ModBlocks.MUD_BRICKS, ModBlocks.SLAB_MUD_BRICKS);
        slab(ModBlocks.MIR_BRICKS, ModBlocks.SLAB_MIR_BRICKS);

        stairs(ModBlocks.PLANKS_BAOBAB, ModBlocks.STAIRS_BAOBAB);
        stairs(ModBlocks.PLANKS_EUCALYPTUS, ModBlocks.STAIRS_EUCALYPTUS);
        stairs(ModBlocks.PLANKS_MAHOGANY, ModBlocks.STAIRS_MAHOGANY);
        stairs(ModBlocks.PLANKS_MOSSBARK, ModBlocks.STAIRS_MOSSBARK);
        stairs(ModBlocks.PLANKS_ASPER, ModBlocks.STAIRS_ASPER);
        stairs(ModBlocks.PLANKS_CYPRESS, ModBlocks.STAIRS_CYPRESS);
        stairs(ModBlocks.PLANKS_BALSAM, ModBlocks.STAIRS_BALSAM);
        stairs(ModBlocks.PLANKS_WHITE, ModBlocks.STAIRS_WHITE);
        stairs(ModBlocks.PLANKS_BAMBOO, ModBlocks.STAIRS_BAMBOO);
        stairs(ModBlocks.PLANKS_ROTTEN, ModBlocks.STAIRS_ROTTEN);
        stairs(ModBlocks.PLANKS_MARSHWOOD, ModBlocks.STAIRS_MARSHWOOD);
        stairs(ModBlocks.PLANKS_SCORCHED, ModBlocks.STAIRS_SCORCHED);
        stairs(ModBlocks.PLANKS_VARNISHED, ModBlocks.STAIRS_VARNISHED);
        stairs(ModBlocks.PLANKS_PETRIFIED, ModBlocks.STAIRS_PETRIFIED);

        stairs(ModBlocks.UMBERSTONE, ModBlocks.STAIRS_UMBERSTONE);
        stairs(ModBlocks.UMBERCOBBLE, ModBlocks.STAIRS_UMBERCOBBLE);
        stairs(ModBlocks.UMBERCOBBLE_MOSSY, ModBlocks.STAIRS_UMBERCOBBLE_MOSSY);
        stairs(ModBlocks.UMBERCOBBLE_WEBBED, ModBlocks.STAIRS_UMBERCOBBLE_WEBBED);
        stairs(ModBlocks.UMBERSTONE_BRICKS, ModBlocks.STAIRS_UMBERSTONE_BRICKS);
        stairs(ModBlocks.UMBERTILE_SMOOTH, ModBlocks.STAIRS_UMBERTILE_SMOOTH);
        stairs(ModBlocks.UMBERTILE_SMOOTH_SMALL, ModBlocks.STAIRS_UMBERTILE_SMOOTH_SMALL);
        stairs(ModBlocks.UMBERPAVER, ModBlocks.STAIRS_UMBERPAVER);
        stairs(ModBlocks.UMBERPAVER_MOSSY, ModBlocks.STAIRS_UMBERPAVER_MOSSY);
        stairs(ModBlocks.UMBERPAVER_WEBBED, ModBlocks.STAIRS_UMBERPAVER_WEBBED);
        stairs(ModBlocks.AMBER, ModBlocks.STAIRS_AMBER);
        stairs(ModBlocks.AMBER_BRICKS, ModBlocks.STAIRS_AMBER_BRICKS);
        stairs(ModBlocks.MUD_BRICKS, ModBlocks.STAIRS_MUD_BRICKS);
        stairs(ModBlocks.MIR_BRICKS, ModBlocks.STAIRS_MIR_BRICKS);

        door(ModBlocks.PLANKS_BAOBAB, ModBlocks.DOOR_BAOBAB);
        door(ModBlocks.PLANKS_EUCALYPTUS, ModBlocks.DOOR_EUCALYPTUS);
        door(ModBlocks.PLANKS_MAHOGANY, ModBlocks.DOOR_MAHOGANY);
        door(ModBlocks.PLANKS_MOSSBARK, ModBlocks.DOOR_MOSSBARK);
        door(ModBlocks.PLANKS_ASPER, ModBlocks.DOOR_ASPER);
        door(ModBlocks.PLANKS_CYPRESS, ModBlocks.DOOR_CYPRESS);
        door(ModBlocks.PLANKS_BALSAM, ModBlocks.DOOR_BALSAM);
        door(ModBlocks.PLANKS_WHITE, ModBlocks.DOOR_WHITE);
        door(ModBlocks.PLANKS_ROTTEN, ModBlocks.DOOR_ROTTEN);
        door(ModBlocks.PLANKS_MARSHWOOD, ModBlocks.DOOR_MARSHWOOD);
        door(ModBlocks.PLANKS_SCORCHED, ModBlocks.DOOR_SCORCHED);

        fence(ModBlocks.PLANKS_BAOBAB, ModBlocks.FENCE_BAOBAB);
        fence(ModBlocks.PLANKS_EUCALYPTUS, ModBlocks.FENCE_EUCALYPTUS);
        fence(ModBlocks.PLANKS_MAHOGANY, ModBlocks.FENCE_MAHOGANY);
        fence(ModBlocks.PLANKS_MOSSBARK, ModBlocks.FENCE_MOSSBARK);
        fence(ModBlocks.PLANKS_ASPER, ModBlocks.FENCE_ASPER);
        fence(ModBlocks.PLANKS_CYPRESS, ModBlocks.FENCE_CYPRESS);
        fence(ModBlocks.PLANKS_BALSAM, ModBlocks.FENCE_BALSAM);
        fence(ModBlocks.PLANKS_WHITE, ModBlocks.FENCE_WHITE);
        fence(ModBlocks.PLANKS_ROTTEN, ModBlocks.FENCE_ROTTEN);
        fence(ModBlocks.PLANKS_MARSHWOOD, ModBlocks.FENCE_MARSHWOOD);
        fence(ModBlocks.PLANKS_SCORCHED, ModBlocks.FENCE_SCORCHED);

        fenceGate(ModBlocks.PLANKS_BAOBAB, ModBlocks.FENCE_GATE_BAOBAB);
        fenceGate(ModBlocks.PLANKS_EUCALYPTUS, ModBlocks.FENCE_GATE_EUCALYPTUS);
        fenceGate(ModBlocks.PLANKS_MAHOGANY, ModBlocks.FENCE_GATE_MAHOGANY);
        fenceGate(ModBlocks.PLANKS_MOSSBARK, ModBlocks.FENCE_GATE_MOSSBARK);
        fenceGate(ModBlocks.PLANKS_ASPER, ModBlocks.FENCE_GATE_ASPER);
        fenceGate(ModBlocks.PLANKS_CYPRESS, ModBlocks.FENCE_GATE_CYPRESS);
        fenceGate(ModBlocks.PLANKS_BALSAM, ModBlocks.FENCE_GATE_BALSAM);
        fenceGate(ModBlocks.PLANKS_WHITE, ModBlocks.FENCE_GATE_WHITE);
        fenceGate(ModBlocks.PLANKS_ROTTEN, ModBlocks.FENCE_GATE_ROTTEN);
        fenceGate(ModBlocks.PLANKS_MARSHWOOD, ModBlocks.FENCE_GATE_MARSHWOOD);
        fenceGate(ModBlocks.PLANKS_SCORCHED, ModBlocks.FENCE_GATE_SCORCHED);

        wall(ModBlocks.UMBERSTONE, ModBlocks.WALL_UMBERSTONE);
        wall(ModBlocks.UMBERCOBBLE, ModBlocks.WALL_UMBERCOBBLE);
        wall(ModBlocks.UMBERCOBBLE_MOSSY, ModBlocks.WALL_UMBERCOBBLE_MOSSY);
        wall(ModBlocks.UMBERCOBBLE_WEBBED, ModBlocks.WALL_UMBERCOBBLE_WEBBED);
        wall(ModBlocks.UMBERSTONE_BRICKS, ModBlocks.WALL_UMBERSTONE_BRICKS);
        wall(ModBlocks.UMBERTILE_SMOOTH, ModBlocks.WALL_UMBERTILE_SMOOTH);
        wall(ModBlocks.UMBERTILE_SMOOTH_SMALL, ModBlocks.WALL_UMBERTILE_SMOOTH_SMALL);
        wall(ModBlocks.AMBER, ModBlocks.WALL_AMBER);
        wall(ModBlocks.AMBER_BRICKS, ModBlocks.WALL_AMBER_BRICKS);
        wall(ModBlocks.UMBERPAVER, ModBlocks.WALL_UMBERPAVER);
        wall(ModBlocks.UMBERPAVER_MOSSY, ModBlocks.WALL_UMBERPAVER_MOSSY);
        wall(ModBlocks.UMBERPAVER_WEBBED, ModBlocks.WALL_UMBERPAVER_WEBBED);

        twoByTwo(ModItems.PETRIFIED_WOOD, ModBlocks.PLANKS_PETRIFIED);
        twoByTwo(ModBlocks.PLANKS_PETRIFIED, ModBlocks.PETRIFIED_CRAFTING_TABLE);

        surround(ModBlocks.PLANKS_PETRIFIED, Items.GOLD_INGOT, ModBlocks.PETRIFIED_WOOD_CHEST);

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

        helmet(ModItems.JADE, ModItems.JADE_HELMET);
        chestplate(ModItems.JADE, ModItems.JADE_CHESTPLATE);
        leggings(ModItems.JADE, ModItems.JADE_LEGGINGS);
        boots(ModItems.JADE, ModItems.JADE_BOOTS);

        helmet(ModItems.PLATE_EXO, ModItems.EXOSKELETON_HELMET);
        chestplate(ModItems.PLATE_EXO, ModItems.EXOSKELETON_CHESTPLATE);
        leggings(ModItems.PLATE_EXO, ModItems.EXOSKELETON_LEGGINGS);
        boots(ModItems.PLATE_EXO, ModItems.EXOSKELETON_BOOTS);

        helmet(ModItems.REINFORCED_PLATE_EXO, ModItems.REIN_EXOSKELETON_HELMET);
        chestplate(ModItems.REINFORCED_PLATE_EXO, ModItems.REIN_EXOSKELETON_CHESTPLATE);
        leggings(ModItems.REINFORCED_PLATE_EXO, ModItems.REIN_EXOSKELETON_LEGGINGS);
        boots(ModItems.REINFORCED_PLATE_EXO, ModItems.REIN_EXOSKELETON_BOOTS);

        helmet(ModItems.PLATE_EXO_RHINO, ModItems.RHINO_EXOSKELETON_HELMET);
        chestplate(ModItems.PLATE_EXO_RHINO, ModItems.RHINO_EXOSKELETON_CHESTPLATE);
        leggings(ModItems.PLATE_EXO_RHINO, ModItems.RHINO_EXOSKELETON_LEGGINGS);
        boots(ModItems.PLATE_EXO_RHINO, ModItems.RHINO_EXOSKELETON_BOOTS);

        surround(ModItems.COMPOUND_EYES, ModBlocks.AMBER, ModItems.COMPOUND_LENS);

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
                .define('V', ModBlocks.VELOCITY)
                .unlockedBy("has_enhanced_glider_wing", has(ModItems.ENHANCED_GLIDER_WING))
                .unlockedBy("has_elastic_fiber", has(ModItems.ELASTIC_FIBER))
                .unlockedBy("has_glider_chestplate", has(ModItems.GLIDER_CHESTPLATE))
                .unlockedBy("has_velocity_block", has(ModBlocks.VELOCITY))
                .save(output);

        surround(ModItems.WATER_REPELLENT, ModItems.REIN_EXOSKELETON_BOOTS, ModItems.WATER_STRIDERS);

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

        ShapedRecipeBuilder.shaped(MISC, ModItems.GLIDER_WING)
                .pattern("SSS")
                .pattern("WWW")
                .pattern("WWW")
                .define('S', Tags.Items.RODS_WOODEN)
                .define('W', ModItems.FLY_WING)
                .unlockedBy("has_fly_wing", has(ModItems.FLY_WING))
                .save(output);

        ShapedRecipeBuilder.shaped(MISC, ModItems.ENHANCED_GLIDER_WING)
                .pattern("BBB")
                .pattern("WWW")
                .pattern("WWW")
                .define('B', ModItems.BAMBOO)
                .define('W', ModItems.DRAGONFLY_WING)
                .unlockedBy("has_dragonfly_wing", has(ModItems.DRAGONFLY_WING))
                .save(output);

        ShapedRecipeBuilder.shaped(COMBAT, ModItems.MUSHROOM_HELMET)
                .pattern("HHH")
                .pattern("HPH")
                .define('H', ModItems.HIDE_SHROOM)
                .define('P', Blocks.PUMPKIN)
                .unlockedBy("has_compound_goggles", has(ModItems.COMPOUND_GOGGLES))
                .save(output);

        threeByThree(ModBlocks.DARK_CAPPED_MUSHROOM, ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK);
        threeByThree(ModBlocks.SARCASTIC_CZECH_MUSHROOM, ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK);
        threeByThree(ModBlocks.GRANDMAS_SHOES_MUSHROOM, ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK);
        threeByThree(ModBlocks.DUTCH_CAP_MUSHROOM, ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK);
        threeByThree(ModBlocks.KAIZERS_FINGERS_MUSHROOM, ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK);
        threeByThree(Blocks.RED_MUSHROOM, Blocks.RED_MUSHROOM_BLOCK);
        threeByThree(Blocks.BROWN_MUSHROOM, Blocks.BROWN_MUSHROOM_BLOCK);

        ShapedRecipeBuilder.shaped(MISC, ModItems.BAMBUCKET)
                .pattern(" S ")
                .pattern("B B")
                .pattern(" B ")
                .define('S', Items.STRING)
                .define('B', ModItems.BAMBOO)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);

        helmet(ModBlocks.PLANKS_BAMBOO, ModItems.BAMBOO_HELMET);
        chestplate(ModBlocks.PLANKS_BAMBOO, ModItems.BAMBOO_CHESTPLATE);
        leggings(ModBlocks.PLANKS_BAMBOO, ModItems.BAMBOO_LEGGINGS);
        boots(ModBlocks.PLANKS_BAMBOO, ModItems.BAMBOO_BOOTS);

        ShapedRecipeBuilder.shaped(COMBAT, ModItems.BAMBOO_SHIELD)
                .pattern("BIB")
                .pattern("BBB")
                .pattern(" B ")
                .define('B', ModItems.BAMBOO)
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ModBlocks.BAMBOO_BRIDGE, 3)
                .pattern("SSS")
                .pattern("B B")
                .pattern("LLL")
                .define('S', Items.STRING)
                .define('B', ModItems.BAMBOO)
                .define('L', ModBlocks.BAMBOO_LADDER)
                .unlockedBy("has_bamboo_ladder", has(ModBlocks.BAMBOO_LADDER))
                .save(output);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ModBlocks.BAMBOO_LADDER, 3)
                .pattern("BBB")
                .pattern("S S")
                .pattern("BBB")
                .define('S', Items.STRING)
                .define('B', ModItems.BAMBOO)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ModBlocks.BAMBOO_NERD_POLE, 4)
                .pattern("S")
                .pattern("B")
                .pattern("B")
                .define('S', Tags.Items.SLIME_BALLS)
                .define('B', ModItems.BAMBOO)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ModBlocks.BAMBOO_EXTENDER)
                .pattern("BSB")
                .pattern("PDP")
                .pattern("BRB")
                .define('B', ModItems.BAMBOO)
                .define('S', Items.STRING)
                .define('P', ModBlocks.PLANKS_BAMBOO)
                .define('D', Blocks.DISPENSER)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .unlockedBy("has_dispenser", has(Blocks.DISPENSER))
                .save(output);

        ShapedRecipeBuilder.shaped(MISC, ModBlocks.BAMBOO_TORCH, 4)
                .pattern("C")
                .pattern("B")
                .pattern("B")
                .define('C', ItemTags.COALS)
                .define('B', ModItems.BAMBOO)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);

        ShapedRecipeBuilder.shaped(MISC, ModBlocks.BAMBOO_CRATE)
                .pattern("BPB")
                .pattern("P P")
                .pattern("BPB")
                .define('B', ModItems.BAMBOO)
                .define('P', ModBlocks.PLANKS_BAMBOO)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);

        ShapedRecipeBuilder.shaped(MISC, ModBlocks.BAMBOO_PIPE)
                .pattern("  B")
                .pattern("HBS")
                .pattern("B  ")
                .define('B', ModItems.BAMBOO)
                .define('H', ModItems.HYDROFUGE)
                .define('S', Items.STRING)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);

        ShapedRecipeBuilder.shaped(MISC, ModItems.BAMBOO_PIPE_WRENCH)
                .pattern("B B")
                .pattern(" P ")
                .pattern(" B ")
                .define('B', ModItems.BAMBOO)
                .define('P', ModBlocks.PLANKS_BAMBOO)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);

        ShapedRecipeBuilder.shaped(FOOD, ModItems.HONEY_SANDWICH, 2)
                .pattern(" B ")
                .pattern("HHH")
                .pattern(" B ")
                .define('B', Items.BREAD)
                .define('H', ModItems.HONEY_DRIP)
                .unlockedBy("has_honey_drip", has(ModItems.HONEY_DRIP))
                .save(output);

        ShapedRecipeBuilder.shaped(FOOD, ModBlocks.HONEY_TREAT)
                .pattern("SHS")
                .pattern("HBH")
                .pattern("SHS")
                .define('S', Items.SUGAR)
                .define('H', ModItems.HONEY_DRIP)
                .define('B', Items.BREAD)
                .unlockedBy("has_honey_drip", has(ModItems.HONEY_DRIP))
                .save(output);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ModBlocks.MIR_BRICKS, 4)
                .pattern("AB")
                .pattern("BA")
                .define('A', ModBlocks.MUD_BRICKS)
                .define('B', Blocks.CLAY)
                .unlockedBy("has_mud_bricks", has(ModBlocks.MUD_BRICKS))
                .save(output, "mir_bricks_bulk");

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ModBlocks.MIR_BRICKS, 4)
                .pattern("AB")
                .pattern("BA")
                .define('A', ModItems.MUD_BRICK)
                .define('B', Items.CLAY_BALL)
                .unlockedBy("has_mud_brick", has(ModItems.MUD_BRICK))
                .save(output);

        twoByTwo(ModBlocks.AMBER, ModBlocks.AMBER_BRICKS, 4);

        ShapedRecipeBuilder.shaped(COMBAT, Items.ARROW, 4)
                .pattern("F")
                .pattern("S")
                .pattern("W")
                .define('F', Items.FLINT)
                .define('S', Items.STICK)
                .define('W', ModItems.FLY_WING)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(output, "arrow_fly_wing");

        ShapedRecipeBuilder.shaped(COMBAT, Items.ARROW, 4)
                .pattern("F")
                .pattern("S")
                .pattern("W")
                .define('F', ModItems.SHARD_BONE)
                .define('S', Items.STICK)
                .define('W', ModItems.FLY_WING)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(output, "arrow_fly_wing_bone_shard");

        ShapedRecipeBuilder.shaped(COMBAT, Items.ARROW, 4)
                .pattern("F")
                .pattern("S")
                .pattern("W")
                .define('F', ModItems.SHARD_BONE)
                .define('S', Items.STICK)
                .define('W', Items.FEATHER)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(output, "arrow_bone_shard");

        ShapedRecipeBuilder.shaped(MISC, ModItems.SPRAY_CAN, 9)
                .pattern(" B ")
                .pattern("IRI")
                .pattern("III")
                .define('B', ItemTags.BUTTONS)
                .define('I', Items.IRON_INGOT)
                .define('R', ModItems.REPELLENT)
                .unlockedBy("has_repellent", has(ModItems.REPELLENT))
                .save(output);

        surround(ModItems.ALTAR_FRAGMENT, Blocks.OBSIDIAN, ModBlocks.ALTAR_BASE);

        ShapedRecipeBuilder.shaped(MISC, ModBlocks.GLOWING_JAR)
                .pattern("III")
                .pattern("GBG")
                .pattern("GGG")
                .define('B', ModItems.BIO_LUMINESCENCE)
                .define('I', Items.IRON_INGOT)
                .define('G', ModBlocks.AMBER_GLASS)
                .unlockedBy("has_bio_luminescence", has(ModItems.BIO_LUMINESCENCE))
                .save(output);

        threeByThree(ModItems.BIO_VELOCITY, ModBlocks.VELOCITY);
        twoByTwo(ModItems.MUD_BRICK, ModBlocks.MUD_BRICKS);

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

        ShapedRecipeBuilder.shaped(MISC, ModBlocks.FLUID_JAR)
                .pattern("PPP")
                .pattern("GBG")
                .pattern("GGG")
                .define('P', ModBlocks.PLANKS_VARNISHED)
                .define('B', Items.BUCKET)
                .define('G', ModBlocks.AMBER_GLASS)
                .unlockedBy("has_amber_glass", has(ModBlocks.AMBER_GLASS))
                .save(output);

        ShapedRecipeBuilder.shaped(MISC, ModItems.MUCUS_CHARGE)
                .pattern("SSS")
                .pattern("SRS")
                .pattern("SSS")
                .define('S', Tags.Items.SLIME_BALLS)
                .define('R', ModItems.REPELLENT)
                .unlockedBy("has_repellent", has(ModItems.REPELLENT))
                .save(output);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ModBlocks.HONEY_COMB)
                .pattern("NPN")
                .pattern("PCP")
                .pattern("NPN")
                .define('N', ModItems.NECTAR)
                .define('P', ModItems.PAPYRUS)
                .define('C', Blocks.CHEST);

        threeByThree(ModBlocks.FIRE_BLOOM, Items.BLAZE_POWDER);
        threeByThree(ModBlocks.MOSS_DOWN, ModItems.MOSS_BALL);

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

        ShapedRecipeBuilder.shaped(MISC, ModBlocks.GAEAN_KEYSTONE)
                .pattern("V V")
                .pattern("SOS")
                .pattern("SSS")
                .define('V', Blocks.VINE)
                .define('S', ItemTags.STONE_BRICKS)
                .define('O', Blocks.OBSIDIAN)
                .unlockedBy("has_vine", has(Blocks.VINE))
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

        ShapedRecipeBuilder.shaped(MISC, ModBlocks.COMPOSTER)
                .pattern("PSP")
                .pattern("PGP")
                .pattern("PSP")
                .define('P', ModBlocks.PLANKS_VARNISHED)
                .define('S', ModBlocks.SLAB_PLANKS_VARNISHED)
                .define('G', Tags.Items.DYES_GREEN)
                .unlockedBy("has_planks_varnished", has(ModBlocks.PLANKS_VARNISHED))
                .save(output);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ModBlocks.SILO_SUPPORTS)
                .pattern("SSS")
                .pattern("F F")
                .pattern("F F")
                .define('S', ItemTags.WOODEN_SLABS)
                .define('F', ItemTags.FENCES)
                .unlockedBy("has_fence", has(ItemTags.FENCES))
                .save(output);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ModBlocks.SILO_TANK)
                .pattern("IPI")
                .pattern("BCB")
                .pattern("IPI")
                .define('I', Items.IRON_INGOT)
                .define('P', ModBlocks.PLANKS_VARNISHED)
                .define('B', Blocks.IRON_BLOCK)
                .define('C', ModBlocks.PETRIFIED_WOOD_CHEST)
                .unlockedBy("has_petrified_wood_chest", has(ModBlocks.PETRIFIED_WOOD_CHEST))
                .save(output);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ModBlocks.SILO_ROOF)
                .pattern(" P ")
                .pattern("PPP")
                .define('P', ModBlocks.PLANKS_VARNISHED)
                .unlockedBy("has_planks_varnished", has(ModBlocks.PLANKS_VARNISHED))
                .save(output);

        ShapedRecipeBuilder.shaped(MISC, ModBlocks.ALTAR_OFFERING)
                .pattern("SGS")
                .pattern("BOB")
                .pattern("SBS")
                .define('S', Tags.Items.STONES)
                .define('G', Tags.Items.INGOTS_GOLD)
                .define('O', Tags.Items.OBSIDIANS)
                .define('B', ItemTags.STONE_BRICKS)
                .unlockedBy("has_obsidian", has(Tags.Items.OBSIDIANS))
                .save(output);

        twoByTwo(ModBlocks.TEMPLE_BRICK, ModBlocks.TEMPLE_TILE, 4);
        twoByTwo(ModItems.TEMPLE_ROCK, ModBlocks.TEMPLE_BRICK);
        twoByTwo(ModItems.GNEISS_ROCK, ModBlocks.GNEISS);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ModBlocks.TEMPLE_PILLAR)
                .pattern("T")
                .pattern("T")
                .define('T', ModBlocks.TEMPLE_TILE)
                .unlockedBy("has_temple_tile", has(ModBlocks.TEMPLE_TILE))
                .save(output);

        threeByThree(ModItems.JADE_BERRIES, ModItems.JADE);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ModBlocks.CULTIVATED_MOSS_DOWN)
                .pattern("GSG")
                .pattern("SMS")
                .pattern("GSG")
                .define('S', ModItems.SUPERNATURAL_VELOCITY)
                .define('M', ModBlocks.MOSS_DOWN)
                .define('G', Tags.Items.DYES_GREEN)
                .unlockedBy("has_supernatural_velocity", has(ModItems.SUPERNATURAL_VELOCITY))
                .save(output);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ModBlocks.CULTIVATED_MOULD_DOWN)
                .pattern("LSL")
                .pattern("SMS")
                .pattern("LSL")
                .define('S', ModItems.SUPERNATURAL_VELOCITY)
                .define('M', ModBlocks.MOULD_DOWN)
                .define('L', Items.LAPIS_LAZULI)
                .unlockedBy("has_supernatural_velocity", has(ModItems.SUPERNATURAL_VELOCITY))
                .save(output);

        surround(ModItems.HYDROFUGE, ModItems.REPELLENT, ModItems.WATER_REPELLENT);

        twoByTwo(ModItems.HIDE_SHROOM, Items.LEATHER, 8);
        twoByTwo(ModItems.PLATE_ZOMBIE_ANT, Items.ROTTEN_FLESH);

        ShapedRecipeBuilder.shaped(MISC, ModItems.WHETSTONE)
                .pattern("SSS")
                .pattern("PPP")
                .pattern("UUU")
                .define('S', ItemTags.SAND)
                .define('U', ModBlocks.UMBERSTONE)
                .define('P', ModItems.PETRIFIED_WOOD)
                .unlockedBy("has_petrified_wood", has(ModItems.PETRIFIED_WOOD))
                .save(output);

        ShapedRecipeBuilder.shaped(MISC, ModItems.AMBER_STAR)
                .pattern(" R ")
                .pattern("RGR")
                .pattern(" R ")
                .define('R', ModItems.RESIN)
                .define('G', ModBlocks.AMBER_GLASS)
                .unlockedBy("has_resin", has(ModItems.RESIN))
                .save(output);

        ShapedRecipeBuilder.shaped(MISC, ModItems.BEETLE_RIDING_KIT)
                .pattern(" SP")
                .pattern("CCC")
                .pattern("LLL")
                .define('S', Items.STRING)
                .define('P', ModItems.PLATE_EXO)
                .define('C', ItemTags.WOOL_CARPETS)
                .define('L', Items.LAPIS_LAZULI)
                .unlockedBy("has_plate_exo", has(ModItems.PLATE_EXO))
                .save(output);

        ShapedRecipeBuilder.shaped(MISC, ModItems.BEETLE_TAMING_AMULET)
                .pattern(" N ")
                .pattern("NJN")
                .pattern(" A ")
                .define('N', Tags.Items.NUGGETS_GOLD)
                .define('J', ModItems.JADE)
                .define('A', ModItems.ALTAR_FRAGMENT)
                .unlockedBy("has_jade", has(ModItems.JADE))
                .save(output);

        ShapedRecipeBuilder.shaped(MISC, ModItems.UMBERGOLEM_HEAD)
                .pattern("SSS")
                .pattern("SHS")
                .pattern("SMS")
                .define('S', Tags.Items.STONES)
                .define('H', ModItems.REIN_COMPOUND_GOGGLES)
                .define('M', ModItems.STAG_BEETLE_MANDIBLES)
                .unlockedBy("has_rein_compound_goggles", has(ModItems.REIN_COMPOUND_GOGGLES))
                .save(output);

        surround(ModItems.ALTAR_FRAGMENT, ModItems.RED_GEM, ModItems.UMBERGOLEM_CORE);

        ShapedRecipeBuilder.shaped(MISC, ModItems.UMBERGOLEM_LEGS)
                .pattern("SSS")
                .pattern("S S")
                .pattern("P P")
                .define('S', Tags.Items.STONES)
                .define('P', ModItems.REINFORCED_PLATE_EXO)
                .unlockedBy("has_reinforced_place_exo", has(ModItems.REINFORCED_PLATE_EXO))
                .save(output);

        ShapedRecipeBuilder.shaped(MISC, ModItems.UMBERGOLEM_CLAW)
                .pattern("SSP")
                .pattern("S  ")
                .define('S', Tags.Items.STONES)
                .define('P', ModItems.SCORPION_PINCER)
                .unlockedBy("has_scorpion_pincer", has(ModItems.SCORPION_PINCER))
                .save(output);

        ShapedRecipeBuilder.shaped(MISC, ModItems.UMBERGOLEM_CLAW)
                .pattern("P  ")
                .pattern("S  ")
                .pattern("SS ")
                .define('S', Tags.Items.STONES)
                .define('P', ModItems.SCORPION_PINCER)
                .unlockedBy("has_scorpion_pincer", has(ModItems.SCORPION_PINCER))
                .save(output, "umbergolem_claw_vertical");

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ModBlocks.UMBER_GOLEM_STATUE)
                .pattern(" H ")
                .pattern("CTC")
                .pattern(" L ")
                .define('H', ModItems.UMBERGOLEM_HEAD)
                .define('C', ModItems.UMBERGOLEM_CLAW)
                .define('T', ModItems.UMBERGOLEM_CORE)
                .define('L', ModItems.UMBERGOLEM_LEGS)
                .unlockedBy("has_umbergolem_head", has(ModItems.UMBERGOLEM_HEAD))
                .unlockedBy("has_umbergolem_claw", has(ModItems.UMBERGOLEM_CLAW))
                .unlockedBy("has_umbergolem_core", has(ModItems.UMBERGOLEM_CORE))
                .unlockedBy("has_umbergolem_legs", has(ModItems.UMBERGOLEM_LEGS))
                .save(output);

        surround(ModBlocks.MUD, ModBlocks.UMBER_GOLEM_STATUE, ModItems.MUD_UMBERGOLEM);
        surround(Blocks.IRON_BLOCK, ModBlocks.UMBER_GOLEM_STATUE, ModItems.IRON_UMBERGOLEM);
        surround(Blocks.GOLD_BLOCK, ModBlocks.UMBER_GOLEM_STATUE, ModItems.GOLD_UMBERGOLEM);
        surround(ModBlocks.JADE_BLOCK, ModBlocks.UMBER_GOLEM_STATUE, ModItems.JADE_UMBERGOLEM);

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

    private void addShapelessCraftingRecipes() {
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_ASPER, ModBlocks.PLANKS_ASPER, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_BAOBAB, ModBlocks.PLANKS_BAOBAB, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_EUCALYPTUS, ModBlocks.PLANKS_EUCALYPTUS, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_MAHOGANY, ModBlocks.PLANKS_MAHOGANY, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_MOSSBARK, ModBlocks.PLANKS_MOSSBARK, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_CYPRESS, ModBlocks.PLANKS_CYPRESS, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_BALSAM, ModBlocks.PLANKS_BALSAM, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_BAMBOO, ModBlocks.PLANKS_BAMBOO, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_ROTTEN, ModBlocks.PLANKS_ROTTEN, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_MARSHWOOD, ModBlocks.PLANKS_MARSHWOOD, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_SCORCHED, ModBlocks.PLANKS_SCORCHED, 4);
        ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, ModBlocks.PLANKS_VARNISHED)
                .requires(ItemTags.PLANKS)
                .requires(Tags.Items.SLIME_BALLS)
                .requires(ModItems.REPELLENT)
                .unlockedBy("has_repellent", has(ModItems.REPELLENT))
                .save(output);

        shapeless(BUILDING_BLOCKS, ModItems.RED_GEM, Items.REDSTONE, 2);
        shapeless(MISC, ModBlocks.UMBERSTONE, ModBlocks.UMBERSTONE_BUTTON, 1);

        ShapelessRecipeBuilder.shapeless(FOOD, ModItems.BAMBOO_SOUP)
                .requires(Items.BOWL)
                .requires(ModItems.BAMBOO_SHOOT)
                .requires(ModItems.BAMBOO)
                .unlockedBy("has_bowl", has(Items.BOWL))
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .unlockedBy("has_bamboo_shoot", has(ModItems.BAMBOO_SHOOT))
                .save(output);

        ShapelessRecipeBuilder.shapeless(FOOD, ModItems.LARVAE_ON_STICK)
                .requires(Tags.Items.RODS_WOODEN)
                .requires(ModItems.BEETLE_LARVA_COOKED, 3)
                .unlockedBy("has_cooked_beetle_larvae", has(ModItems.BEETLE_LARVA_COOKED))
                .save(output);

        ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, ModBlocks.SILK)
                .requires(Items.STRING, 9)
                .unlockedBy("has_string", has(Items.STRING))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, Items.BONE_MEAL)
                .requires(ModItems.SHARD_BONE)
                .unlockedBy("has_shard_bone", has(ModItems.SHARD_BONE))
                .save(output);

        ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, ModBlocks.REIN_EXO)
                .requires(ModItems.REINFORCED_PLATE_EXO, 4)
                .unlockedBy("has_reinforced_plate_exo", has(ModItems.REINFORCED_PLATE_EXO))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, Items.BOOK)
                .requires(ModItems.PLATE_EXO)
                .requires(Items.PAPER, 3)
                .unlockedBy("has_plate_exo", has(ModItems.PLATE_EXO))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, Items.PAPER, 4)
                .requires(ModItems.PAPYRUS, 2)
                .unlockedBy("has_papyrus", has(ModItems.PAPYRUS))
                .save(output);

        nineBlockStorageRecipes(output, MISC, ModItems.JADE, BUILDING_BLOCKS, ModBlocks.JADE_BLOCK);

        ShapelessRecipeBuilder.shapeless(MISC, ModItems.PLANTICIDE, 2)
                .requires(ModItems.POISON_GLAND)
                .requires(Tags.Items.SLIME_BALLS)
                .requires(Tags.Items.DYES_WHITE)
                .unlockedBy("has_poison_gland", has(ModItems.POISON_GLAND))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, ModItems.STEW_POT)
                .requires(Items.CAULDRON)
                .requires(Tags.Items.RODS_WOODEN)
                .unlockedBy("has_cauldron", has(Items.CAULDRON))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, ModItems.TITAN_STEW)
                .requires(ModItems.STEW_POT)
                .requires(ModItems.TITAN_CHOP_RAW)
                .requires(Items.POTATO)
                .requires(Items.CARROT)
                .requires(ModItems.CABBAGE)
                .requires(Tags.Items.MUSHROOMS)
                .requires(Tags.Items.MUSHROOMS)
                .unlockedBy("has_stew_pot", has(ModItems.STEW_POT))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, ModItems.TITAN_STEW, 1)
                .requires(ModItems.STEW_POT)
                .requires(Items.BEEF, 2)
                .requires(Items.POTATO)
                .requires(Items.CARROT)
                .requires(ModItems.CABBAGE)
                .requires(Tags.Items.MUSHROOMS)
                .requires(Tags.Items.MUSHROOMS)
                .unlockedBy("has_stew_pot", has(ModItems.STEW_POT))
                .save(output, "stew_pot_no_titan_chop");

        ShapelessRecipeBuilder.shapeless(MISC, ModItems.SMOOTHIE_GLASS)
                .requires(Items.GLASS_BOTTLE, 3)
                .unlockedBy("has_glass_bottle", has(Items.GLASS_BOTTLE))
                .save(output);

        ShapelessRecipeBuilder.shapeless(COMBAT, ModItems.WEB_SLINGER_WITHER)
                .requires(ModItems.WEB_SLINGER)
                .requires(Blocks.SOUL_SAND)
                .requires(ModItems.POISON_GLAND)
                .requires(ModBlocks.WITHER_WEB, 3)
                .unlockedBy("has_web_slinger", has(ModItems.WEB_SLINGER))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, ModBlocks.LIGHTNING_SPEED)
                .requires(ModBlocks.VELOCITY)
                .requires(ModItems.SUPERNATURAL_VELOCITY, 8)
                .unlockedBy("has_velocity_block", has(ModBlocks.VELOCITY))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, ModItems.REINFORCED_PLATE_EXO)
                .requires(ModItems.PLATE_EXO, 9)
                .unlockedBy("has_plate_exo", has(ModItems.PLATE_EXO))
                .save(output);

        ShapelessRecipeBuilder.shapeless(COMBAT, ModItems.WASP_DAGGER)
                .requires(Tags.Items.RODS_WOODEN)
                .requires(ModItems.WASP_STING)
                .unlockedBy("has_wasp_sting", has(ModItems.WASP_STING))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, ModBlocks.BAMBOO_PIPE_EXTRACT)
                .requires(Items.LEVER)
                .requires(ModBlocks.BAMBOO_PIPE)
                .unlockedBy("has_bamboo_pipe", has(ModBlocks.BAMBOO_PIPE))
                .save(output);
    }

    private void smelting(ItemLike ingredient, ItemLike result) {
        smeltingResultFromBase(output, ingredient, result);
    }

    private void cook(ItemLike ingredient, ItemLike result) {
        simpleCookingRecipe(output, "smoking", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, ingredient, result, 0.35F);
        simpleCookingRecipe(output, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, ingredient, result, 0.35F);
    }

    private void ore(ItemLike ore, ItemLike ingot, String group) {
        oreSmelting(output, List.of(ore), MISC, ingot, 0.25F, 200, group);
        oreBlasting(output, List.of(ore), MISC, ingot, 0.25F, 100, group);
    }

    private void shapeless(@SuppressWarnings("SameParameterValue") RecipeCategory category, ItemLike ingredient, ItemLike result, int amount) {
        ShapelessRecipeBuilder.shapeless(category, result, amount)
                .requires(ingredient)
                .unlockedBy("has_%s".formatted(ingredient.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(ingredient))
                .save(output);
    }

    private void stairs(ItemLike material, ItemLike result) {
        stairBuilder(result, Ingredient.of(material));
    }

    private void slab(ItemLike material, ItemLike result) {
        slabBuilder(BUILDING_BLOCKS, result, Ingredient.of(material));
    }

    private void door(ItemLike material, ItemLike result) {
        doorBuilder(result, Ingredient.of(material));
    }

    private void fence(ItemLike material, ItemLike result) {
        fenceBuilder(result, Ingredient.of(material));
    }

    private void fenceGate(ItemLike material, ItemLike result) {
        fenceGateBuilder(result, Ingredient.of(material));
    }

    private void wall(ItemLike material, ItemLike result) {
        wallBuilder(BUILDING_BLOCKS, result, Ingredient.of(material));
    }

    private void twoByTwo(ItemLike material, ItemLike result) {
        twoByTwo(material, result, 1);
    }

    private void twoByTwo(ItemLike material, ItemLike result, int amount) {
        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, result, amount)
                .pattern("##")
                .pattern("##")
                .define('#', material)
                .unlockedBy("has_%s".formatted(material.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(material))
                .save(output);
    }

    private void threeByThree(ItemLike material, ItemLike result) {
        threeByThree(material, result, 1);
    }

    private void threeByThree(ItemLike material, ItemLike result, int amount) {
        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, result, amount)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', material)
                .unlockedBy("has_%s".formatted(material.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(material))
                .save(output);
    }

    private void helmet(ItemLike material, ItemLike result) {
        ShapedRecipeBuilder.shaped(COMBAT, result)
                .pattern("MMM")
                .pattern("M M")
                .define('M', material)
                .unlockedBy("has_jade", has(material))
                .save(output);
    }

    private void chestplate(ItemLike material, ItemLike result) {
        ShapedRecipeBuilder.shaped(COMBAT, result)
                .pattern("M M")
                .pattern("MMM")
                .pattern("MMM")
                .define('M', material)
                .unlockedBy("has_jade", has(material))
                .save(output);
    }

    private void leggings(ItemLike material, ItemLike result) {
        ShapedRecipeBuilder.shaped(COMBAT, result)
                .pattern("MMM")
                .pattern("M M")
                .pattern("M M")
                .define('M', material)
                .unlockedBy("has_jade", has(material))
                .save(output);
    }

    private void boots(ItemLike material, ItemLike result) {
        ShapedRecipeBuilder.shaped(COMBAT, result)
                .pattern("M M")
                .pattern("M M")
                .define('M', material)
                .unlockedBy("has_jade", has(material))
                .save(output);
    }

    private void surround(ItemLike outer, ItemLike inner, ItemLike result) {
        ShapedRecipeBuilder.shaped(MISC, result)
                .pattern("OOO")
                .pattern("OIO")
                .pattern("OOO")
                .define('O', outer)
                .define('I', inner)
                .unlockedBy("has_outer", has(outer))
                .unlockedBy("has_inner", has(inner))
                .save(output);
    }
}
