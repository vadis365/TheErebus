package erebus.world.feature.structure.pieces;

/** A piece whose terrain check can reject placement before writing any blocks. */
public interface TerrainCheckedPiece {
    boolean isRejected();
}
