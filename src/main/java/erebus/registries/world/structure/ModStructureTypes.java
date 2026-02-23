package erebus.registries.world.structure;

import erebus.Erebus;
import erebus.world.feature.structure.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModStructureTypes {

    public static DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, Erebus.MODID);

    public static final DeferredHolder<StructureType<?>, StructureType<AntlionDungeon>> ANTLION_DUNGEON = STRUCTURE_TYPES.register("antlion_dungeon", () -> () -> AntlionDungeon.CODEC);
    public static final DeferredHolder<StructureType<?>, StructureType<AntlionLair>> ANTLION_LAIR = STRUCTURE_TYPES.register("antlion_lair", () -> () -> AntlionLair.CODEC);
    public static final DeferredHolder<StructureType<?>, StructureType<DragonflyDungeon>> DRAGONFLY_DUNGEON = STRUCTURE_TYPES.register("dragonfly_dungeon", () -> () -> DragonflyDungeon.CODEC);
    public static final DeferredHolder<StructureType<?>, StructureType<DungPile>> DUNG_PILE = STRUCTURE_TYPES.register("dung_pile", () -> () -> DungPile.CODEC);
    public static final DeferredHolder<StructureType<?>, StructureType<LocustShrine>> LOCUST_SHRINE = STRUCTURE_TYPES.register("locust_shrine", () -> () -> LocustShrine.CODEC);
    public static final DeferredHolder<StructureType<?>, StructureType<SpiderDungeon>> SPIDER_DUNGEON = STRUCTURE_TYPES.register("spider_dungeon", () -> () -> SpiderDungeon.CODEC);
    public static final DeferredHolder<StructureType<?>, StructureType<SwampHut>> SWAMP_HUT = STRUCTURE_TYPES.register("swamp_hut", () -> () -> SwampHut.CODEC);
    public static final DeferredHolder<StructureType<?>, StructureType<WaspDungeon>> WASP_DUNGEON = STRUCTURE_TYPES.register("wasp_dungeon", () -> () -> WaspDungeon.CODEC);
    public static final DeferredHolder<StructureType<?>, StructureType<TarantulaDungeon>> TARANTULA_DUNGEON = STRUCTURE_TYPES.register("tarantula_dungeon", () -> () -> TarantulaDungeon.CODEC);
}
