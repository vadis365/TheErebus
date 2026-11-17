package erebus.registries.data;

import erebus.Erebus;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public final class ModLootTables {
    public static final ResourceKey<LootTable> FERN_SEEDS = createKey("harvesting/fern_seeds");
    public static final ResourceKey<LootTable> ROTTEN_LOG = createKey("chests/rotten_log");
    public static final ResourceKey<LootTable> ANTLION_DUNGEON = createKey("chests/antlion_dungeon");
    public static final ResourceKey<LootTable> ANTLION_LAIR = createKey("chests/antlion_lair");
    public static final ResourceKey<LootTable> DRAGONFLY_DUNGEON = createKey("chests/dragonfly_dungeon");
    public static final ResourceKey<LootTable> DUNG_PILE = createKey("chests/dung_pile");
    public static final ResourceKey<LootTable> LOCUST_SHRINE = createKey("chests/locust_shrine");
    public static final ResourceKey<LootTable> SPIDER_DUNGEON = createKey("chests/spider_dungeon");
    public static final ResourceKey<LootTable> TARANTULA_DUNGEON = createKey("chests/tarantula_dungeon");

    private ModLootTables() {
    }

    private static ResourceKey<LootTable> createKey(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, Erebus.prefix(name));
    }
}
