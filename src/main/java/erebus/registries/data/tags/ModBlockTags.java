package erebus.registries.data.tags;

import erebus.Erebus;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static final TagKey<Block> ORES_ALUMINUM = common("ores/aluminum");
    public static final TagKey<Block> ORES_LEAD = common("ores/lead");
    public static final TagKey<Block> ORES_SILVER = common("ores/silver");
    public static final TagKey<Block> ORES_TIN = common("ores/tin");
    public static final TagKey<Block> ORES_JADE = common("ores/jade");
    public static final TagKey<Block> ORES_PETRIFIED_WOOD = common("ores/petrified_wood");
    public static final TagKey<Block> ORES_FOSSIL = common("ores/fossil");
    public static final TagKey<Block> ORES_GNEISS = common("ores/gneiss");
    public static final TagKey<Block> STORAGE_BLOCKS_JADE = common("storage_blocks/jade");
    public static final TagKey<Block> DUNGEON_GUARDIAN_IMMUNE = create("dungeon_guardian_immune");
    public static final TagKey<Block> UMBERSTONE_ORE_REPLACEABLES = create("umberstone_ore_replaceables");
    public static final TagKey<Block> BEE_POLLINATION_BLOCKS = create("bee_pollination_blocks");
    public static final TagKey<Block> EREBUS_CARVER_REPLACEABLES = create("erebus_carver_replaceables");

    public static final TagKey<Block> NEEDS_JADE_TOOL = create("needs_jade_tool");
    public static final TagKey<Block> INCORRECT_FOR_JADE_TOOL = create("incorrect_for_jade_tool");

    public static final TagKey<Block> MINEABLE_WITH_PAXEL = create("mineable/paxel");

    private static TagKey<Block> common(String name) {
        return TagKey.create(Registries.BLOCK, net.minecraft.resources.Identifier.fromNamespaceAndPath("c", name));
    }

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, Erebus.prefix(name));
    }
}
