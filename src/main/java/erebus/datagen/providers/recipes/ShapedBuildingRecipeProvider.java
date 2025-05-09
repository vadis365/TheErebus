package erebus.datagen.providers.recipes;

import erebus.registries.ModItems;
import erebus.registries.blocks.providers.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.RecipeCategory.BUILDING_BLOCKS;

/**
 * Provider for shaped crafting recipes related to building blocks.
 */
public class ShapedBuildingRecipeProvider extends ErebusRecipeProvider {

    public ShapedBuildingRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries);
    }

    @Override
    public void buildRecipes(@NotNull RecipeOutput output) {
        this.output = output;
        addBasicBlockRecipes();
        addSlabRecipes();
        addStairsRecipes();
        addDoorRecipes();
        addFenceRecipes();
        addFenceGateRecipes();
        addWallRecipes();
        addSpecialBlockRecipes();
    }

    private void addBasicBlockRecipes() {
        twoByTwo(UmberstoneBlocks.UMBERCOBBLE, UmberstoneBlocks.UMBERPAVER, 4);
        twoByTwo(UmberstoneBlocks.UMBERCOBBLE_MOSSY, UmberstoneBlocks.UMBERPAVER_MOSSY, 4);
        twoByTwo(UmberstoneBlocks.UMBERCOBBLE_WEBBED, UmberstoneBlocks.UMBERPAVER_WEBBED, 4);
        twoByTwo(UmberstoneBlocks.UMBERSTONE, UmberstoneBlocks.UMBERSTONE_BRICKS, 4);
        twoByTwo(ModItems.PETRIFIED_WOOD, WoodBlocks.PLANKS_PETRIFIED);
        twoByTwo(WoodBlocks.PLANKS_PETRIFIED, OtherBlocks.PETRIFIED_CRAFTING_TABLE);
        twoByTwo(AmberBlocks.AMBER, AmberBlocks.AMBER_BRICKS, 4);
        twoByTwo(ModItems.MUD_BRICK, UmberstoneBlocks.MUD_BRICKS);

        threeByThree(UmberstoneBlocks.UMBERSTONE, UmberstoneBlocks.UMBERTILE_SMOOTH, 9);
        threeByThree(PlantBlocks.DARK_CAPPED_MUSHROOM, PlantBlocks.DARK_CAPPED_MUSHROOM_BLOCK);
        threeByThree(PlantBlocks.SARCASTIC_CZECH_MUSHROOM, PlantBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK);
        threeByThree(PlantBlocks.GRANDMAS_SHOES_MUSHROOM, PlantBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK);
        threeByThree(PlantBlocks.DUTCH_CAP_MUSHROOM, PlantBlocks.DUTCH_CAP_MUSHROOM_BLOCK);
        threeByThree(PlantBlocks.KAIZERS_FINGERS_MUSHROOM, PlantBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK);
        threeByThree(Blocks.RED_MUSHROOM, Blocks.RED_MUSHROOM_BLOCK);
        threeByThree(Blocks.BROWN_MUSHROOM, Blocks.BROWN_MUSHROOM_BLOCK);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, UmberstoneBlocks.UMBERSTONE_PILLAR, 2)
                .pattern("#")
                .pattern("#")
                .define('#', UmberstoneBlocks.UMBERSTONE)
                .unlockedBy("has_umberstone", has(UmberstoneBlocks.UMBERSTONE))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, OtherBlocks.UMBER_FURNACE)
                .pattern("SSS")
                .pattern("SBS")
                .pattern("SSS")
                .define('S', UmberstoneBlocks.UMBERCOBBLE)
                .define('B', Items.BUCKET)
                .unlockedBy("has_umbercobble", has(UmberstoneBlocks.UMBERCOBBLE))
                .save(output);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, UmberstoneBlocks.MIR_BRICKS, 4)
                .pattern("AB")
                .pattern("BA")
                .define('A', UmberstoneBlocks.MUD_BRICKS)
                .define('B', Blocks.CLAY)
                .unlockedBy("has_mud_bricks", has(UmberstoneBlocks.MUD_BRICKS))
                .save(output, "mir_bricks_bulk");

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, UmberstoneBlocks.MIR_BRICKS, 4)
                .pattern("AB")
                .pattern("BA")
                .define('A', ModItems.MUD_BRICK)
                .define('B', Items.CLAY_BALL)
                .unlockedBy("has_mud_brick", has(ModItems.MUD_BRICK))
                .save(output);
    }

    private void addSlabRecipes() {
        slab(WoodBlocks.PLANKS_BAOBAB, SlabBlocks.SLAB_PLANKS_BAOBAB);
        slab(WoodBlocks.PLANKS_EUCALYPTUS, SlabBlocks.SLAB_PLANKS_EUCALYPTUS);
        slab(WoodBlocks.PLANKS_MAHOGANY, SlabBlocks.SLAB_PLANKS_MAHOGANY);
        slab(WoodBlocks.PLANKS_MOSSBARK, SlabBlocks.SLAB_PLANKS_MOSSBARK);
        slab(WoodBlocks.PLANKS_ASPER, SlabBlocks.SLAB_PLANKS_ASPER);
        slab(WoodBlocks.PLANKS_CYPRESS, SlabBlocks.SLAB_PLANKS_CYPRESS);
        slab(WoodBlocks.PLANKS_BALSAM, SlabBlocks.SLAB_PLANKS_BALSAM);
        slab(WoodBlocks.PLANKS_WHITE, SlabBlocks.SLAB_PLANKS_WHITE);
        slab(WoodBlocks.PLANKS_BAMBOO, SlabBlocks.SLAB_PLANKS_BAMBOO);
        slab(WoodBlocks.PLANKS_ROTTEN, SlabBlocks.SLAB_PLANKS_ROTTEN);
        slab(WoodBlocks.PLANKS_MARSHWOOD, SlabBlocks.SLAB_PLANKS_MARSHWOOD);
        slab(WoodBlocks.PLANKS_SCORCHED, SlabBlocks.SLAB_PLANKS_SCORCHED);
        slab(WoodBlocks.PLANKS_VARNISHED, SlabBlocks.SLAB_PLANKS_VARNISHED);
        slab(WoodBlocks.PLANKS_PETRIFIED, SlabBlocks.SLAB_PLANKS_PETRIFIED);

        slab(UmberstoneBlocks.UMBERSTONE, SlabBlocks.SLAB_UMBERSTONE);
        slab(UmberstoneBlocks.UMBERCOBBLE, SlabBlocks.SLAB_UMBERCOBBLE);
        slab(UmberstoneBlocks.UMBERCOBBLE_MOSSY, SlabBlocks.SLAB_UMBERCOBBLE_MOSSY);
        slab(UmberstoneBlocks.UMBERCOBBLE_WEBBED, SlabBlocks.SLAB_UMBERCOBBLE_WEBBED);
        slab(UmberstoneBlocks.UMBERSTONE_BRICKS, SlabBlocks.SLAB_UMBERSTONE_BRICKS);
        slab(UmberstoneBlocks.UMBERTILE_SMOOTH, SlabBlocks.SLAB_UMBERTILE_SMOOTH);
        slab(UmberstoneBlocks.UMBERTILE_SMOOTH_SMALL, SlabBlocks.SLAB_UMBERTILE_SMOOTH_SMALL);
        slab(UmberstoneBlocks.UMBERPAVER, SlabBlocks.SLAB_UMBERPAVER);
        slab(UmberstoneBlocks.UMBERPAVER_MOSSY, SlabBlocks.SLAB_UMBERPAVER_MOSSY);
        slab(UmberstoneBlocks.UMBERPAVER_WEBBED, SlabBlocks.SLAB_UMBERPAVER_WEBBED);
        slab(AmberBlocks.AMBER, SlabBlocks.SLAB_AMBER);
        slab(AmberBlocks.AMBER_BRICKS, SlabBlocks.SLAB_AMBER_BRICKS);
        slab(UmberstoneBlocks.MUD_BRICKS, SlabBlocks.SLAB_MUD_BRICKS);
        slab(UmberstoneBlocks.MIR_BRICKS, SlabBlocks.SLAB_MIR_BRICKS);
    }

    private void addStairsRecipes() {
        stairs(WoodBlocks.PLANKS_BAOBAB, StairBlocks.STAIRS_BAOBAB);
        stairs(WoodBlocks.PLANKS_EUCALYPTUS, StairBlocks.STAIRS_EUCALYPTUS);
        stairs(WoodBlocks.PLANKS_MAHOGANY, StairBlocks.STAIRS_MAHOGANY);
        stairs(WoodBlocks.PLANKS_MOSSBARK, StairBlocks.STAIRS_MOSSBARK);
        stairs(WoodBlocks.PLANKS_ASPER, StairBlocks.STAIRS_ASPER);
        stairs(WoodBlocks.PLANKS_CYPRESS, StairBlocks.STAIRS_CYPRESS);
        stairs(WoodBlocks.PLANKS_BALSAM, StairBlocks.STAIRS_BALSAM);
        stairs(WoodBlocks.PLANKS_WHITE, StairBlocks.STAIRS_WHITE);
        stairs(WoodBlocks.PLANKS_BAMBOO, StairBlocks.STAIRS_BAMBOO);
        stairs(WoodBlocks.PLANKS_ROTTEN, StairBlocks.STAIRS_ROTTEN);
        stairs(WoodBlocks.PLANKS_MARSHWOOD, StairBlocks.STAIRS_MARSHWOOD);
        stairs(WoodBlocks.PLANKS_SCORCHED, StairBlocks.STAIRS_SCORCHED);
        stairs(WoodBlocks.PLANKS_VARNISHED, StairBlocks.STAIRS_VARNISHED);
        stairs(WoodBlocks.PLANKS_PETRIFIED, StairBlocks.STAIRS_PETRIFIED);

        stairs(UmberstoneBlocks.UMBERSTONE, StairBlocks.STAIRS_UMBERSTONE);
        stairs(UmberstoneBlocks.UMBERCOBBLE, StairBlocks.STAIRS_UMBERCOBBLE);
        stairs(UmberstoneBlocks.UMBERCOBBLE_MOSSY, StairBlocks.STAIRS_UMBERCOBBLE_MOSSY);
        stairs(UmberstoneBlocks.UMBERCOBBLE_WEBBED, StairBlocks.STAIRS_UMBERCOBBLE_WEBBED);
        stairs(UmberstoneBlocks.UMBERSTONE_BRICKS, StairBlocks.STAIRS_UMBERSTONE_BRICKS);
        stairs(UmberstoneBlocks.UMBERTILE_SMOOTH, StairBlocks.STAIRS_UMBERTILE_SMOOTH);
        stairs(UmberstoneBlocks.UMBERTILE_SMOOTH_SMALL, StairBlocks.STAIRS_UMBERTILE_SMOOTH_SMALL);
        stairs(UmberstoneBlocks.UMBERPAVER, StairBlocks.STAIRS_UMBERPAVER);
        stairs(UmberstoneBlocks.UMBERPAVER_MOSSY, StairBlocks.STAIRS_UMBERPAVER_MOSSY);
        stairs(UmberstoneBlocks.UMBERPAVER_WEBBED, StairBlocks.STAIRS_UMBERPAVER_WEBBED);
        stairs(AmberBlocks.AMBER, StairBlocks.STAIRS_AMBER);
        stairs(AmberBlocks.AMBER_BRICKS, StairBlocks.STAIRS_AMBER_BRICKS);
        stairs(UmberstoneBlocks.MUD_BRICKS, StairBlocks.STAIRS_MUD_BRICKS);
        stairs(UmberstoneBlocks.MIR_BRICKS, StairBlocks.STAIRS_MIR_BRICKS);
    }

    private void addDoorRecipes() {
        door(WoodBlocks.PLANKS_BAOBAB, DoorBlocks.DOOR_BAOBAB);
        door(WoodBlocks.PLANKS_EUCALYPTUS, DoorBlocks.DOOR_EUCALYPTUS);
        door(WoodBlocks.PLANKS_MAHOGANY, DoorBlocks.DOOR_MAHOGANY);
        door(WoodBlocks.PLANKS_MOSSBARK, DoorBlocks.DOOR_MOSSBARK);
        door(WoodBlocks.PLANKS_ASPER, DoorBlocks.DOOR_ASPER);
        door(WoodBlocks.PLANKS_CYPRESS, DoorBlocks.DOOR_CYPRESS);
        door(WoodBlocks.PLANKS_BALSAM, DoorBlocks.DOOR_BALSAM);
        door(WoodBlocks.PLANKS_WHITE, DoorBlocks.DOOR_WHITE);
        door(WoodBlocks.PLANKS_ROTTEN, DoorBlocks.DOOR_ROTTEN);
        door(WoodBlocks.PLANKS_MARSHWOOD, DoorBlocks.DOOR_MARSHWOOD);
        door(WoodBlocks.PLANKS_SCORCHED, DoorBlocks.DOOR_SCORCHED);
    }

    private void addFenceRecipes() {
        fence(WoodBlocks.PLANKS_BAOBAB, FenceBlocks.FENCE_BAOBAB);
        fence(WoodBlocks.PLANKS_EUCALYPTUS, FenceBlocks.FENCE_EUCALYPTUS);
        fence(WoodBlocks.PLANKS_MAHOGANY, FenceBlocks.FENCE_MAHOGANY);
        fence(WoodBlocks.PLANKS_MOSSBARK, FenceBlocks.FENCE_MOSSBARK);
        fence(WoodBlocks.PLANKS_ASPER, FenceBlocks.FENCE_ASPER);
        fence(WoodBlocks.PLANKS_CYPRESS, FenceBlocks.FENCE_CYPRESS);
        fence(WoodBlocks.PLANKS_BALSAM, FenceBlocks.FENCE_BALSAM);
        fence(WoodBlocks.PLANKS_WHITE, FenceBlocks.FENCE_WHITE);
        fence(WoodBlocks.PLANKS_ROTTEN, FenceBlocks.FENCE_ROTTEN);
        fence(WoodBlocks.PLANKS_MARSHWOOD, FenceBlocks.FENCE_MARSHWOOD);
        fence(WoodBlocks.PLANKS_SCORCHED, FenceBlocks.FENCE_SCORCHED);
    }

    private void addFenceGateRecipes() {
        fenceGate(WoodBlocks.PLANKS_BAOBAB, FenceBlocks.FENCE_GATE_BAOBAB);
        fenceGate(WoodBlocks.PLANKS_EUCALYPTUS, FenceBlocks.FENCE_GATE_EUCALYPTUS);
        fenceGate(WoodBlocks.PLANKS_MAHOGANY, FenceBlocks.FENCE_GATE_MAHOGANY);
        fenceGate(WoodBlocks.PLANKS_MOSSBARK, FenceBlocks.FENCE_GATE_MOSSBARK);
        fenceGate(WoodBlocks.PLANKS_ASPER, FenceBlocks.FENCE_GATE_ASPER);
        fenceGate(WoodBlocks.PLANKS_CYPRESS, FenceBlocks.FENCE_GATE_CYPRESS);
        fenceGate(WoodBlocks.PLANKS_BALSAM, FenceBlocks.FENCE_GATE_BALSAM);
        fenceGate(WoodBlocks.PLANKS_WHITE, FenceBlocks.FENCE_GATE_WHITE);
        fenceGate(WoodBlocks.PLANKS_ROTTEN, FenceBlocks.FENCE_GATE_ROTTEN);
        fenceGate(WoodBlocks.PLANKS_MARSHWOOD, FenceBlocks.FENCE_GATE_MARSHWOOD);
        fenceGate(WoodBlocks.PLANKS_SCORCHED, FenceBlocks.FENCE_GATE_SCORCHED);
    }

    private void addWallRecipes() {
        wall(UmberstoneBlocks.UMBERSTONE, WallBlocks.WALL_UMBERSTONE);
        wall(UmberstoneBlocks.UMBERCOBBLE, WallBlocks.WALL_UMBERCOBBLE);
        wall(UmberstoneBlocks.UMBERCOBBLE_MOSSY, WallBlocks.WALL_UMBERCOBBLE_MOSSY);
        wall(UmberstoneBlocks.UMBERCOBBLE_WEBBED, WallBlocks.WALL_UMBERCOBBLE_WEBBED);
        wall(UmberstoneBlocks.UMBERSTONE_BRICKS, WallBlocks.WALL_UMBERSTONE_BRICKS);
        wall(UmberstoneBlocks.UMBERTILE_SMOOTH, WallBlocks.WALL_UMBERTILE_SMOOTH);
        wall(UmberstoneBlocks.UMBERTILE_SMOOTH_SMALL, WallBlocks.WALL_UMBERTILE_SMOOTH_SMALL);
        wall(AmberBlocks.AMBER, WallBlocks.WALL_AMBER);
        wall(AmberBlocks.AMBER_BRICKS, WallBlocks.WALL_AMBER_BRICKS);
        wall(UmberstoneBlocks.UMBERPAVER, WallBlocks.WALL_UMBERPAVER);
        wall(UmberstoneBlocks.UMBERPAVER_MOSSY, WallBlocks.WALL_UMBERPAVER_MOSSY);
        wall(UmberstoneBlocks.UMBERPAVER_WEBBED, WallBlocks.WALL_UMBERPAVER_WEBBED);
    }

    private void addSpecialBlockRecipes() {
        surround(WoodBlocks.PLANKS_PETRIFIED, Items.GOLD_INGOT, OtherBlocks.PETRIFIED_WOOD_CHEST);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, OtherBlocks.BAMBOO_BRIDGE, 3)
                .pattern("SSS")
                .pattern("B B")
                .pattern("LLL")
                .define('S', Items.STRING)
                .define('B', ModItems.BAMBOO)
                .define('L', OtherBlocks.BAMBOO_LADDER)
                .unlockedBy("has_bamboo_ladder", has(OtherBlocks.BAMBOO_LADDER))
                .save(output);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, OtherBlocks.BAMBOO_LADDER, 3)
                .pattern("BBB")
                .pattern("S S")
                .pattern("BBB")
                .define('S', Items.STRING)
                .define('B', ModItems.BAMBOO)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, OtherBlocks.BAMBOO_NERD_POLE, 4)
                .pattern("S")
                .pattern("B")
                .pattern("B")
                .define('S', Tags.Items.SLIME_BALLS)
                .define('B', ModItems.BAMBOO)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, OtherBlocks.SILO_SUPPORTS)
                .pattern("SSS")
                .pattern("F F")
                .pattern("F F")
                .define('S', ItemTags.WOODEN_SLABS)
                .define('F', ItemTags.FENCES)
                .unlockedBy("has_fence", has(ItemTags.FENCES))
                .save(output);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, OtherBlocks.SILO_ROOF)
                .pattern(" P ")
                .pattern("PPP")
                .define('P', WoodBlocks.PLANKS_VARNISHED)
                .unlockedBy("has_planks_varnished", has(WoodBlocks.PLANKS_VARNISHED))
                .save(output);

        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, OtherBlocks.TEMPLE_PILLAR)
                .pattern("T")
                .pattern("T")
                .define('T', OtherBlocks.TEMPLE_TILE)
                .unlockedBy("has_temple_tile", has(OtherBlocks.TEMPLE_TILE))
                .save(output);

        twoByTwo(OtherBlocks.TEMPLE_BRICK, OtherBlocks.TEMPLE_TILE, 4);
        twoByTwo(ModItems.TEMPLE_ROCK, OtherBlocks.TEMPLE_BRICK);
        twoByTwo(ModItems.GNEISS_ROCK, OtherBlocks.GNEISS);
    }
}