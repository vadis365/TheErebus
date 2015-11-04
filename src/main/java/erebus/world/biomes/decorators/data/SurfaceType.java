package erebus.world.biomes.decorators.data;

import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public enum SurfaceType {
	GRASS,
	DIRT,
	SAND,
	MIXED,
	UMBERSTONE;

	public boolean matchBlock(Block block) {
		switch (this) {
			case GRASS:
				return block == Blocks.grass || block == Blocks.mycelium;
			case DIRT:
				return block == Blocks.dirt;
			case SAND:
				return block == Blocks.sand;
			case MIXED:
				return block == Blocks.grass || block == Blocks.dirt || block == Blocks.sand;
			case UMBERSTONE:
				return block == ModBlocks.umberstone;
			default:
				return false;
		}
	}
}
