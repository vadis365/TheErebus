package erebus.world.biomes.decorators.data;

import net.minecraft.block.Block;
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
				return block == Blocks.grass.getDefaultState()  || block == Blocks.mycelium.getDefaultState() ;
			case DIRT:
				return block == Blocks.dirt.getDefaultState();
			case SAND:
				return block == Blocks.sand.getDefaultState();
			case MIXED:
				return block == Blocks.grass.getDefaultState() || block == Blocks.dirt.getDefaultState() || block == Blocks.sand.getDefaultState();
			default:
				return false;
		}
	}
}
