package erebus.registries.world.structure;

import erebus.Erebus;
import erebus.world.feature.structure.pieces.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModStructurePieces {
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECES = DeferredRegister.create(Registries.STRUCTURE_PIECE, Erebus.MODID);

    public static final DeferredHolder<StructurePieceType, StructurePieceType> ANTLION_LAIR = register("antlion_lair", AntlionLairPiece::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> DRAGONFLY_DUNGEON = register("dragonfly_dungeon", DragonflyDungeonPiece::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> DUNG_PILE = register("dung_pile", DungPilePiece::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> LOCUST_SHRINE = register("locust_shrine", LocustShrinePiece::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> SPIDER_DUNGEON = register("spider_dungeon", SpiderDungeonPiece::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> SWAMP_HUT = register("swamp_hut", SwampHutPiece::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> WASP_DUNGEON = register("wasp_dungeon", WaspDungeonPiece::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> GIANT_FLOWERS = register("giant_flowers", GiantFlowersPiece::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> ROTTEN_STUMPS = register("rotten_stumps", RottenStumpsPiece::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> BIG_LOGS = register("big_logs", BigLogsPiece::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> TARANTULA_DUNGEON = register("tarantula_dungeon", TarantulaDungeonPiece::new);

    private static DeferredHolder<StructurePieceType, StructurePieceType> register(String name, StructurePieceType type) {
        return STRUCTURE_PIECES.register(name, () -> type);
    }
}
