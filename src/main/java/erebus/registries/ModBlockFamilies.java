package erebus.registries;

import com.google.common.collect.Maps;
import erebus.registries.blocks.providers.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ModBlockFamilies {

    private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();
    private static final String RECIPE_GROUP_PREFIX_WOODEN = "wooden";
    private static final String RECIPE_UNLOCKED_BY_HAS_PLANKS = "has_planks";

    public static final BlockFamily ASPER = familyBuilder(WoodBlocks.PLANKS_ASPER)
            .fence(FenceBlocks.FENCE_ASPER.get())
            .fenceGate(FenceBlocks.FENCE_GATE_ASPER.get())
            .slab(SlabBlocks.ASPER.get())
            .stairs(StairBlocks.ASPER.get())
            .door(DoorBlocks.ASPER.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily BALSAM = familyBuilder(WoodBlocks.PLANKS_BALSAM)
            .fence(FenceBlocks.FENCE_BALSAM.get())
            .fenceGate(FenceBlocks.FENCE_GATE_BALSAM.get())
            .slab(SlabBlocks.BALSAM.get())
            .stairs(StairBlocks.BALSAM.get())
            .door(DoorBlocks.BALSAM.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily BAOBAB = familyBuilder(WoodBlocks.PLANKS_BAOBAB)
            .fence(FenceBlocks.FENCE_BAOBAB.get())
            .fenceGate(FenceBlocks.FENCE_GATE_BAOBAB.get())
            .slab(SlabBlocks.BAOBAB.get())
            .stairs(StairBlocks.BAOBAB.get())
            .door(DoorBlocks.BAOBAB.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily BAMBOO = familyBuilder(WoodBlocks.PLANKS_BAMBOO)
            .fence(FenceBlocks.FENCE_BAMBOO.get())
            .fenceGate(FenceBlocks.FENCE_GATE_BAMBOO.get())
            .slab(SlabBlocks.BAMBOO.get())
            .stairs(StairBlocks.BAMBOO.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily CYPRESS = familyBuilder(WoodBlocks.PLANKS_CYPRESS)
            .fence(FenceBlocks.FENCE_CYPRESS.get())
            .fenceGate(FenceBlocks.FENCE_GATE_CYPRESS.get())
            .slab(SlabBlocks.CYPRESS.get())
            .stairs(StairBlocks.CYPRESS.get())
            .door(DoorBlocks.CYPRESS.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily EUCALYPTUS = familyBuilder(WoodBlocks.PLANKS_EUCALYPTUS)
            .fence(FenceBlocks.FENCE_EUCALYPTUS.get())
            .fenceGate(FenceBlocks.FENCE_GATE_EUCALYPTUS.get())
            .slab(SlabBlocks.EUCALYPTUS.get())
            .stairs(StairBlocks.EUCALYPTUS.get())
            .door(DoorBlocks.EUCALYPTUS.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily MAHOGANY = familyBuilder(WoodBlocks.PLANKS_MAHOGANY)
            .fence(FenceBlocks.FENCE_MAHOGANY.get())
            .fenceGate(FenceBlocks.FENCE_GATE_MAHOGANY.get())
            .slab(SlabBlocks.MAHOGANY.get())
            .stairs(StairBlocks.MAHOGANY.get())
            .door(DoorBlocks.MAHOGANY.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily MARSHWOOD = familyBuilder(WoodBlocks.PLANKS_MARSHWOOD)
            .fence(FenceBlocks.FENCE_MARSHWOOD.get())
            .fenceGate(FenceBlocks.FENCE_GATE_MARSHWOOD.get())
            .slab(SlabBlocks.MARSHWOOD.get())
            .stairs(StairBlocks.MARSHWOOD.get())
            .door(DoorBlocks.MARSHWOOD.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily MOSSBARK = familyBuilder(WoodBlocks.PLANKS_MOSSBARK)
            .fence(FenceBlocks.FENCE_MOSSBARK.get())
            .fenceGate(FenceBlocks.FENCE_GATE_MOSSBARK.get())
            .slab(SlabBlocks.MOSSBARK.get())
            .stairs(StairBlocks.MOSSBARK.get())
            .door(DoorBlocks.MOSSBARK.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily ROTTEN = familyBuilder(WoodBlocks.PLANKS_ROTTEN)
            .fence(FenceBlocks.FENCE_ROTTEN.get())
            .fenceGate(FenceBlocks.FENCE_GATE_ROTTEN.get())
            .slab(SlabBlocks.ROTTEN.get())
            .stairs(StairBlocks.ROTTEN.get())
            .door(DoorBlocks.ROTTEN.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily SCORCHED = familyBuilder(WoodBlocks.PLANKS_SCORCHED)
            .fence(FenceBlocks.FENCE_SCORCHED.get())
            .fenceGate(FenceBlocks.FENCE_GATE_SCORCHED.get())
            .slab(SlabBlocks.SCORCHED.get())
            .stairs(StairBlocks.SCORCHED.get())
            .door(DoorBlocks.SCORCHED.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    private static BlockFamily.Builder familyBuilder(Supplier<Block> base) {
        BlockFamily.Builder builder = new BlockFamily.Builder(base.get());
        BlockFamily family = MAP.put(base.get(), builder.getFamily());

        if(family != null) {
            throw new IllegalStateException("Duplicate family definition for %s".formatted(BuiltInRegistries.BLOCK.getKey(base.get())));
        }

        return builder;
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }
}
