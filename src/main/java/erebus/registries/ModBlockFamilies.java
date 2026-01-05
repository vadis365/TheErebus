package erebus.registries;

import com.google.common.collect.Maps;
import erebus.registries.blocks.ModBlocks;
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

    public static final BlockFamily ASPER = familyBuilder(ModBlocks.PLANKS_ASPER)
            .fence(ModBlocks.FENCE_ASPER.get())
            .fenceGate(ModBlocks.FENCE_GATE_ASPER.get())
            .slab(ModBlocks.SLAB_PLANKS_ASPER.get())
            .stairs(ModBlocks.STAIRS_ASPER.get())
            .door(ModBlocks.DOOR_ASPER.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily BALSAM = familyBuilder(ModBlocks.PLANKS_BALSAM)
            .fence(ModBlocks.FENCE_BALSAM.get())
            .fenceGate(ModBlocks.FENCE_GATE_BALSAM.get())
            .slab(ModBlocks.SLAB_PLANKS_BALSAM.get())
            .stairs(ModBlocks.STAIRS_BALSAM.get())
            .door(ModBlocks.DOOR_BALSAM.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily BAOBAB = familyBuilder(ModBlocks.PLANKS_BAOBAB)
            .fence(ModBlocks.FENCE_BAOBAB.get())
            .fenceGate(ModBlocks.FENCE_GATE_BAOBAB.get())
            .slab(ModBlocks.SLAB_PLANKS_BAOBAB.get())
            .stairs(ModBlocks.STAIRS_BAOBAB.get())
            .door(ModBlocks.DOOR_BAOBAB.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily BAMBOO = familyBuilder(ModBlocks.PLANKS_BAMBOO)
            .fence(ModBlocks.FENCE_BAMBOO.get())
            .fenceGate(ModBlocks.FENCE_GATE_BAMBOO.get())
            .slab(ModBlocks.SLAB_PLANKS_BAMBOO.get())
            .stairs(ModBlocks.STAIRS_BAMBOO.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily CYPRESS = familyBuilder(ModBlocks.PLANKS_CYPRESS)
            .fence(ModBlocks.FENCE_CYPRESS.get())
            .fenceGate(ModBlocks.FENCE_GATE_CYPRESS.get())
            .slab(ModBlocks.SLAB_PLANKS_CYPRESS.get())
            .stairs(ModBlocks.STAIRS_CYPRESS.get())
            .door(ModBlocks.DOOR_CYPRESS.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily EUCALYPTUS = familyBuilder(ModBlocks.PLANKS_EUCALYPTUS)
            .fence(ModBlocks.FENCE_EUCALYPTUS.get())
            .fenceGate(ModBlocks.FENCE_GATE_EUCALYPTUS.get())
            .slab(ModBlocks.SLAB_PLANKS_EUCALYPTUS.get())
            .stairs(ModBlocks.STAIRS_EUCALYPTUS.get())
            .door(ModBlocks.DOOR_EUCALYPTUS.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily MAHOGANY = familyBuilder(ModBlocks.PLANKS_MAHOGANY)
            .fence(ModBlocks.FENCE_MAHOGANY.get())
            .fenceGate(ModBlocks.FENCE_GATE_MAHOGANY.get())
            .slab(ModBlocks.SLAB_PLANKS_MAHOGANY.get())
            .stairs(ModBlocks.STAIRS_MAHOGANY.get())
            .door(ModBlocks.DOOR_MAHOGANY.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily MARSHWOOD = familyBuilder(ModBlocks.PLANKS_MARSHWOOD)
            .fence(ModBlocks.FENCE_MARSHWOOD.get())
            .fenceGate(ModBlocks.FENCE_GATE_MARSHWOOD.get())
            .slab(ModBlocks.SLAB_PLANKS_MARSHWOOD.get())
            .stairs(ModBlocks.STAIRS_MARSHWOOD.get())
            .door(ModBlocks.DOOR_MARSHWOOD.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily MOSSBARK = familyBuilder(ModBlocks.PLANKS_MOSSBARK)
            .fence(ModBlocks.FENCE_MOSSBARK.get())
            .fenceGate(ModBlocks.FENCE_GATE_MOSSBARK.get())
            .slab(ModBlocks.SLAB_PLANKS_MOSSBARK.get())
            .stairs(ModBlocks.STAIRS_MOSSBARK.get())
            .door(ModBlocks.DOOR_MOSSBARK.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily ROTTEN = familyBuilder(ModBlocks.PLANKS_ROTTEN)
            .fence(ModBlocks.FENCE_ROTTEN.get())
            .fenceGate(ModBlocks.FENCE_GATE_ROTTEN.get())
            .slab(ModBlocks.SLAB_PLANKS_ROTTEN.get())
            .stairs(ModBlocks.STAIRS_ROTTEN.get())
            .door(ModBlocks.DOOR_ROTTEN.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily SCORCHED = familyBuilder(ModBlocks.PLANKS_SCORCHED)
            .fence(ModBlocks.FENCE_SCORCHED.get())
            .fenceGate(ModBlocks.FENCE_GATE_SCORCHED.get())
            .slab(ModBlocks.SLAB_PLANKS_SCORCHED.get())
            .stairs(ModBlocks.STAIRS_SCORCHED.get())
            .door(ModBlocks.DOOR_SCORCHED.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily WHITE = familyBuilder(ModBlocks.PLANKS_WHITE)
            .stairs(ModBlocks.STAIRS_WHITE.get())
            .fence(ModBlocks.FENCE_WHITE.get())
            .fenceGate(ModBlocks.FENCE_GATE_WHITE.get())
            .door(ModBlocks.DOOR_WHITE.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily VARNISHED = familyBuilder(ModBlocks.PLANKS_VARNISHED)
            .stairs(ModBlocks.STAIRS_VARNISHED.get())
            .fence(ModBlocks.FENCE_VARNISHED.get())
            .fenceGate(ModBlocks.FENCE_GATE_VARNISHED.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily PETRIFIED = familyBuilder(ModBlocks.PLANKS_PETRIFIED)
            .stairs(ModBlocks.STAIRS_PETRIFIED.get())
            .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN)
            .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
            .getFamily();

    public static final BlockFamily UMBERSTONE = familyBuilder(ModBlocks.UMBERSTONE)
            .slab(ModBlocks.SLAB_UMBERSTONE.get())
            .stairs(ModBlocks.STAIRS_UMBERSTONE.get())
            .wall(ModBlocks.WALL_UMBERSTONE.get())
            .getFamily();

    public static final BlockFamily UMBERCOBBLE = familyBuilder(ModBlocks.UMBERCOBBLE)
            .slab(ModBlocks.SLAB_UMBERCOBBLE.get())
            .stairs(ModBlocks.STAIRS_UMBERCOBBLE.get())
            .getFamily();

    public static final BlockFamily UMBERCOBBLE_MOSSY = familyBuilder(ModBlocks.UMBERCOBBLE_MOSSY)
            .slab(ModBlocks.SLAB_UMBERCOBBLE_MOSSY.get())
            .stairs(ModBlocks.STAIRS_UMBERCOBBLE_MOSSY.get())
            .getFamily();

    public static final BlockFamily UMBERCOBBLE_WEBBED = familyBuilder(ModBlocks.UMBERCOBBLE_WEBBED)
            .slab(ModBlocks.SLAB_UMBERCOBBLE_WEBBED.get())
            .stairs(ModBlocks.STAIRS_UMBERCOBBLE_WEBBED.get())
            .getFamily();

    public static final BlockFamily UMBERSTONE_BRICKS = familyBuilder(ModBlocks.UMBERSTONE_BRICKS)
            .slab(ModBlocks.SLAB_UMBERSTONE_BRICKS.get())
            .stairs(ModBlocks.STAIRS_UMBERSTONE_BRICKS.get())
            .getFamily();

    public static final BlockFamily UMBERTILE_SMOOTH = familyBuilder(ModBlocks.UMBERTILE_SMOOTH)
            .slab(ModBlocks.SLAB_UMBERTILE_SMOOTH.get())
            .stairs(ModBlocks.STAIRS_UMBERTILE_SMOOTH.get())
            .getFamily();

    public static final BlockFamily UMBERTILE_SMOOTH_SMALL = familyBuilder(ModBlocks.UMBERTILE_SMOOTH_SMALL)
            .slab(ModBlocks.SLAB_UMBERTILE_SMOOTH_SMALL.get())
            .stairs(ModBlocks.STAIRS_UMBERTILE_SMOOTH_SMALL.get())
            .getFamily();

    public static final BlockFamily UMBERPAVER = familyBuilder(ModBlocks.UMBERPAVER)
            .slab(ModBlocks.SLAB_UMBERPAVER.get())
            .stairs(ModBlocks.STAIRS_UMBERPAVER.get())
            .getFamily();

    public static final BlockFamily UMBERPAVER_MOSSY = familyBuilder(ModBlocks.UMBERPAVER_MOSSY)
            .slab(ModBlocks.SLAB_UMBERPAVER_MOSSY.get())
            .stairs(ModBlocks.STAIRS_UMBERPAVER_MOSSY.get())
            .getFamily();

    public static final BlockFamily UMBERPAVER_WEBBED = familyBuilder(ModBlocks.UMBERPAVER_WEBBED)
            .slab(ModBlocks.SLAB_UMBERPAVER_WEBBED.get())
            .stairs(ModBlocks.STAIRS_UMBERPAVER_WEBBED.get())
            .getFamily();

    public static final BlockFamily AMBER = familyBuilder(ModBlocks.AMBER)
            .slab(ModBlocks.SLAB_AMBER.get())
            .stairs(ModBlocks.STAIRS_AMBER.get())
            .getFamily();

    public static final BlockFamily AMBER_BRICKS = familyBuilder(ModBlocks.AMBER_BRICKS)
            .slab(ModBlocks.SLAB_AMBER_BRICKS.get())
            .stairs(ModBlocks.STAIRS_AMBER_BRICKS.get())
            .getFamily();

    public static final BlockFamily MIR_BRICKS = familyBuilder(ModBlocks.MIR_BRICKS)
            .slab(ModBlocks.SLAB_MIR_BRICKS.get())
            .stairs(ModBlocks.STAIRS_MIR_BRICKS.get())
            .getFamily();

    public static final BlockFamily MUD_BRICKS = familyBuilder(ModBlocks.MUD_BRICKS)
            .slab(ModBlocks.SLAB_MUD_BRICKS.get())
            .stairs(ModBlocks.STAIRS_MUD_BRICKS.get())
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
