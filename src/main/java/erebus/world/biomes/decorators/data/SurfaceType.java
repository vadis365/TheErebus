package erebus.world.biomes.decorators.data;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public enum SurfaceType {
	GRASS,
	DIRT,
	SAND,
	MIXED;

	public boolean matchBlock(IBlockState block) {
		switch (this) {
			case GRASS:
				return block == Blocks.GRASS.getDefaultState()  || block == Blocks.MYCELIUM.getDefaultState() ;
			case DIRT:
				return block == Blocks.DIRT.getDefaultState();
			case SAND:
				return block == Blocks.SAND.getDefaultState();
			case MIXED:
				return block == Blocks.GRASS.getDefaultState() || block == Blocks.DIRT.getDefaultState() || block == Blocks.SAND.getDefaultState();
			default:
				return false;
		}
	}
}
