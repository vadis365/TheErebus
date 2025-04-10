package erebus.world.feature.bush;

import erebus.registries.ModBlocks;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

public class HeartBerryBushFeature extends ErebusBushFeature {

    public HeartBerryBushFeature() {
        super("heart_berry_bush", ModBlocks.HEART_BERRY_BUSH);
    }

    @Override
    public List<Block> plantedOn() {
        return List.of(Blocks.GRASS_BLOCK);
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    }
}
