package erebus.world.feature.bush;

import erebus.world.feature.ErebusFeature;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;
import java.util.function.Supplier;

public class ErebusBushFeature extends ErebusFeature {

    private final Supplier<Block> BUSH;

    public ErebusBushFeature(String key, Supplier<Block> bush) {
        super(key);
        this.BUSH = bush;
    }

    public List<Block> plantedOn() {
        return List.of(Blocks.GRASS_BLOCK);
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return patchWithFilter(5, BlockPredicate.matchesBlocks(plantedOn()));
    }

    public Block getBush() {
        return BUSH.get();
    }
}
