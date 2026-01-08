package erebus.datagen.providers.recipes;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

import static net.minecraft.data.recipes.RecipeCategory.BUILDING_BLOCKS;

/**
 * Provider for shaped crafting recipes related to building blocks.
 */
public class ShapedBuildingRecipeProvider extends ErebusRecipeProvider {

    public ShapedBuildingRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
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
        twoByTwo(ModBlocks.UMBERCOBBLE, ModBlocks.UMBERPAVER, 4);
        twoByTwo(ModBlocks.UMBERCOBBLE_MOSSY, ModBlocks.UMBERPAVER_MOSSY, 4);
        twoByTwo(ModBlocks.UMBERCOBBLE_WEBBED, ModBlocks.UMBERPAVER_WEBBED, 4);
        twoByTwo(ModBlocks.UMBERSTONE, ModBlocks.UMBERSTONE_BRICKS, 4);
        twoByTwo(ModItems.PETRIFIED_WOOD, ModBlocks.PLANKS_PETRIFIED);
        twoByTwo(ModBlocks.PLANKS_PETRIFIED, ModBlocks.PETRIFIED_CRAFTING_TABLE);
        twoByTwo(ModBlocks.AMBER, ModBlocks.AMBER_BRICKS, 4);
        twoByTwo(ModItems.MUD_BRICK, ModBlocks.MUD_BRICKS);

        threeByThree(ModBlocks.UMBERSTONE, ModBlocks.UMBERTILE_SMOOTH, 9);
        threeByThree(ModBlocks.DARK_CAPPED_MUSHROOM, ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK);
        threeByThree(ModBlocks.SARCASTIC_CZECH_MUSHROOM, ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK);
        threeByThree(ModBlocks.GRANDMAS_SHOES_MUSHROOM, ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK);
        threeByThree(ModBlocks.DUTCH_CAP_MUSHROOM, ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK);
        threeByThree(ModBlocks.KAIZERS_FINGERS_MUSHROOM, ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK);
        threeByThree(Blocks.RED_MUSHROOM, Blocks.RED_MUSHROOM_BLOCK);
        threeByThree(Blocks.BROWN_MUSHROOM, Blocks.BROWN_MUSHROOM_BLOCK);

        shaped(BUILDING_BLOCKS, ModBlocks.UMBERSTONE_PILLAR, 2)
                .pattern("#")
                .pattern("#")
                .define('#', ModBlocks.UMBERSTONE)
                .unlockedBy("has_umberstone", has(ModBlocks.UMBERSTONE))
                .save(output);

        shaped(RecipeCategory.DECORATIONS, ModBlocks.UMBER_FURNACE)
                .pattern("SSS")
                .pattern("SBS")
                .pattern("SSS")
                .define('S', ModBlocks.UMBERCOBBLE)
                .define('B', Items.BUCKET)
                .unlockedBy("has_umbercobble", has(ModBlocks.UMBERCOBBLE))
                .save(output);

        shaped(BUILDING_BLOCKS, ModBlocks.MIR_BRICKS, 4)
                .pattern("AB")
                .pattern("BA")
                .define('A', ModBlocks.MUD_BRICKS)
                .define('B', Blocks.CLAY)
                .unlockedBy("has_mud_bricks", has(ModBlocks.MUD_BRICKS))
                .save(output, "mir_bricks_bulk");

        shaped(BUILDING_BLOCKS, ModBlocks.MIR_BRICKS, 4)
                .pattern("AB")
                .pattern("BA")
                .define('A', ModItems.MUD_BRICK)
                .define('B', Items.CLAY_BALL)
                .unlockedBy("has_mud_brick", has(ModItems.MUD_BRICK))
                .save(output);
    }

    private void addSlabRecipes() {
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
    }

    private void addStairsRecipes() {
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
    }

    private void addDoorRecipes() {
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
    }

    private void addFenceRecipes() {
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
    }

    private void addFenceGateRecipes() {
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
    }

    private void addWallRecipes() {
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
    }

    private void addSpecialBlockRecipes() {
        surround(ModBlocks.PLANKS_PETRIFIED, Items.GOLD_INGOT, ModBlocks.CHEST_PETRIFIED);

        shaped(BUILDING_BLOCKS, ModBlocks.BAMBOO_BRIDGE, 3)
                .pattern("SSS")
                .pattern("B B")
                .pattern("LLL")
                .define('S', Items.STRING)
                .define('B', ModItems.BAMBOO)
                .define('L', ModBlocks.BAMBOO_LADDER)
                .unlockedBy("has_bamboo_ladder", has(ModBlocks.BAMBOO_LADDER))
                .save(output);

        shaped(BUILDING_BLOCKS, ModBlocks.BAMBOO_LADDER, 3)
                .pattern("BBB")
                .pattern("S S")
                .pattern("BBB")
                .define('S', Items.STRING)
                .define('B', ModItems.BAMBOO)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);

        shaped(BUILDING_BLOCKS, ModBlocks.BAMBOO_NERD_POLE, 4)
                .pattern("S")
                .pattern("B")
                .pattern("B")
                .define('S', Tags.Items.SLIME_BALLS)
                .define('B', ModItems.BAMBOO)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);

        shaped(BUILDING_BLOCKS, ModBlocks.SILO_SUPPORTS)
                .pattern("SSS")
                .pattern("F F")
                .pattern("F F")
                .define('S', ItemTags.WOODEN_SLABS)
                .define('F', ItemTags.FENCES)
                .unlockedBy("has_fence", has(ItemTags.FENCES))
                .save(output);

        shaped(BUILDING_BLOCKS, ModBlocks.SILO_ROOF)
                .pattern(" P ")
                .pattern("PPP")
                .define('P', ModBlocks.PLANKS_VARNISHED)
                .unlockedBy("has_planks_varnished", has(ModBlocks.PLANKS_VARNISHED))
                .save(output);

        shaped(BUILDING_BLOCKS, ModBlocks.TEMPLE_PILLAR)
                .pattern("T")
                .pattern("T")
                .define('T', ModBlocks.TEMPLE_TILE)
                .unlockedBy("has_temple_tile", has(ModBlocks.TEMPLE_TILE))
                .save(output);

        twoByTwo(ModBlocks.TEMPLE_BRICK, ModBlocks.TEMPLE_TILE, 4);
        twoByTwo(ModItems.TEMPLE_ROCK, ModBlocks.TEMPLE_BRICK);
        twoByTwo(ModItems.GNEISS_ROCK, ModBlocks.GNEISS);
    }
}
