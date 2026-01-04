package erebus.registries.data.tags;

import erebus.Erebus;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static final TagKey<Block> UMBERSTONE_ORE_REPLACEABLES = create("umberstone_ore_replaceables");
    public static final TagKey<Block> BEE_POLLINATION_BLOCKS = create("bee_pollination_blocks");
    public static final TagKey<Block> EREBUS_CARVER_REPLACEABLES = create("erebus_carver_replaceables");

    public static final TagKey<Block> NEEDS_JADE_TOOL = create("needs_jade_tool");
    public static final TagKey<Block> INCORRECT_FOR_JADE_TOOL = create("incorrect_for_jade_tool");

    public static final TagKey<Block> MINEABLE_WITH_PAXEL = create("mineable/paxel");

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, Erebus.prefix(name));
    }
}
