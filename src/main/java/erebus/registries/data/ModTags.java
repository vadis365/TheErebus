package erebus.registries.data;

import erebus.Erebus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {

    // MARK: Tool Tags
    public static final TagKey<Block> NEEDS_JADE_TOOL = TagKey.create(BuiltInRegistries.BLOCK.key(), Erebus.prefix("needs_jade_tool"));
    public static final TagKey<Block> INCORRECT_FOR_JADE_TOOL = TagKey.create(BuiltInRegistries.BLOCK.key(), Erebus.prefix("incorrect_for_jade_tool"));

    public static final TagKey<Block> MINEABLE_WITH_PAXEL = TagKey.create(BuiltInRegistries.BLOCK.key(), Erebus.prefix("mineable/paxel"));

    // MARK: World Tags
    public static final TagKey<Biome> IS_EREBUS = TagKey.create(Registries.BIOME, Erebus.prefix("is_erebus"));
    public static final TagKey<Block> UMBERSTONE_ORE_REPLACEABLES = TagKey.create(Registries.BLOCK, Erebus.prefix("umberstone_ore_replaceables"));
    public static final TagKey<Block> BEE_POLLINATION_BLOCKS = TagKey.create(Registries.BLOCK, Erebus.prefix("bee_pollination_blocks"));
    public static final TagKey<Block> EREBUS_CARVER_REPLACEABLES = TagKey.create(Registries.BLOCK, Erebus.prefix("erebus_carver_replaceables"));

    public static final TagKey<Biome> HAS_ANTLION_DUNGEON = TagKey.create(Registries.BIOME, Erebus.prefix("has_antlion_dungeon"));
    public static final TagKey<Biome> HAS_ANTLION_LAIR = TagKey.create(Registries.BIOME, Erebus.prefix("has_antlion_lair"));
    public static final TagKey<Biome> HAS_DRAGONFLY_DUNGEON = TagKey.create(Registries.BIOME, Erebus.prefix("has_dragonfly_dungeon"));
    public static final TagKey<Biome> HAS_DUNG_PILE = TagKey.create(Registries.BIOME, Erebus.prefix("has_dung_pile"));
    public static final TagKey<Biome> HAS_LOCUST_SHRINE = TagKey.create(Registries.BIOME, Erebus.prefix("has_locust_shrine"));
    public static final TagKey<Biome> HAS_SPIDER_DUNGEON = TagKey.create(Registries.BIOME, Erebus.prefix("has_spider_dungeon"));
    public static final TagKey<Biome> HAS_SWAMP_HUT = TagKey.create(Registries.BIOME, Erebus.prefix("has_swamp_hut"));
    public static final TagKey<Biome> HAS_WASP_DUNGEON = TagKey.create(Registries.BIOME, Erebus.prefix("has_wasp_dungeon"));
    public static final TagKey<Biome> HAS_GIANT_FLOWERS = TagKey.create(Registries.BIOME, Erebus.prefix("has_giant_flowers"));
    public static final TagKey<Biome> HAS_ROTTEN_STUMPS = TagKey.create(Registries.BIOME, Erebus.prefix("has_rotten_stumps"));
    public static final TagKey<Biome> HAS_BIG_LOGS = TagKey.create(Registries.BIOME, Erebus.prefix("has_big_logs"));


    // MARK: Entity
    public static final TagKey<EntityType<?>> CAN_BE_PRESERVED = TagKey.create(Registries.ENTITY_TYPE, Erebus.prefix("can_be_preserved"));
    
    // MARK: Compostable Items
    public static final TagKey<Item> COMPOSTABLE = TagKey.create(BuiltInRegistries.ITEM.key(), Erebus.prefix("compostable"));
}
