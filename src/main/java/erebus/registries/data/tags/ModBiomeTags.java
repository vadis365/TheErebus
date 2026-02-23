package erebus.registries.data.tags;

import erebus.Erebus;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomeTags {
    public static final TagKey<Biome> IS_EREBUS = create("is_erebus");

    public static final TagKey<Biome> IS_FUNGAL_FOREST = create("is_fungal_forest");

    public static final TagKey<Biome> HAS_ANTLION_DUNGEON = create("has_antlion_dungeon");
    public static final TagKey<Biome> HAS_ANTLION_LAIR = create("has_antlion_lair");
    public static final TagKey<Biome> HAS_DRAGONFLY_DUNGEON = create("has_dragonfly_dungeon");
    public static final TagKey<Biome> HAS_DUNG_PILE = create("has_dung_pile");
    public static final TagKey<Biome> HAS_LOCUST_SHRINE = create("has_locust_shrine");
    public static final TagKey<Biome> HAS_SPIDER_DUNGEON = create("has_spider_dungeon");
    public static final TagKey<Biome> HAS_SWAMP_HUT = create("has_swamp_hut");
    public static final TagKey<Biome> HAS_WASP_DUNGEON = create("has_wasp_dungeon");
    public static final TagKey<Biome> HAS_GIANT_FLOWERS = create("has_giant_flowers");
    public static final TagKey<Biome> HAS_ROTTEN_STUMPS = create("has_rotten_stumps");
    public static final TagKey<Biome> HAS_BIG_LOGS = create("has_big_logs");
    public static final TagKey<Biome> HAS_TARANTULA_DUNGEON = create("has_tarantula_dungeon");

    private static TagKey<Biome> create(String name) {
        return TagKey.create(Registries.BIOME, Erebus.prefix(name));
    }
}
