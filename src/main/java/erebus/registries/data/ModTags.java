package erebus.registries.data;

import erebus.Erebus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {

    // MARK: Tool Tags
    public static final TagKey<Block> NEEDS_JADE_TOOL = TagKey.create(BuiltInRegistries.BLOCK.key(), Erebus.prefix("needs_jade_tool"));
    public static final TagKey<Block> INCORRECT_FOR_JADE_TOOL = TagKey.create(BuiltInRegistries.BLOCK.key(), Erebus.prefix("incorrect_for_jade_tool"));

    public static final TagKey<Block> MINEABLE_WITH_PAXEL = TagKey.create(BuiltInRegistries.BLOCK.key(), Erebus.prefix("mineable/paxel"));

    // MARK: World Tags
    public static final TagKey<Biome> IS_EREBUS = TagKey.create(Registries.BIOME, Erebus.prefix("is_erebus"));
    public static final TagKey<Biome> HAS_DRAGONFLY_DUNGEON = TagKey.create(Registries.BIOME, Erebus.prefix("has_dragonfly_dungeon"));
    public static final TagKey<Biome> NO_DRAGONFLY_DUNGEON = TagKey.create(Registries.BIOME, Erebus.prefix("no_dragonfly_dungeon"));
    public static final TagKey<Biome> HAS_LOCUST_SHRINE = TagKey.create(Registries.BIOME, Erebus.prefix("has_locust_shrine"));
    public static final TagKey<Biome> HAS_WASP_DUNGEON = TagKey.create(Registries.BIOME, Erebus.prefix("has_wasp_dungeon"));
    public static final TagKey<Block> UMBERSTONE_ORE_REPLACEABLES = TagKey.create(Registries.BLOCK, Erebus.prefix("umberstone_ore_replaceables"));
    public static final TagKey<Block> BEE_POLLINATION_BLOCKS = TagKey.create(Registries.BLOCK, Erebus.prefix("bee_pollination_blocks"));
    public static final TagKey<Block> EREBUS_CARVER_REPLACEABLES = TagKey.create(Registries.BLOCK, Erebus.prefix("erebus_carver_replaceables"));

    // MARK: Entity
    public static final TagKey<EntityType<?>> CAN_BE_PRESERVED = TagKey.create(Registries.ENTITY_TYPE, Erebus.prefix("can_be_preserved"));
}
