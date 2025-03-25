package erebus.registries.data;

import erebus.Erebus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {

    // MARK: Tool Tags
    public static final TagKey<Block> NEEDS_JADE_TOOL = TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "needs_jade_tool"));
    public static final TagKey<Block> INCORRECT_FOR_JADE_TOOL = TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "incorrect_for_jade_tool"));

    public static final TagKey<Block> MINEABLE_WITH_PAXEL = TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "mineable/paxel"));


    public static void init() {}
}
