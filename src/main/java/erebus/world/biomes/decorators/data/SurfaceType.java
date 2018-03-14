package erebus.world.biomes.decorators.data;

import erebus.ModBlocks;
import net.minecraft.block.BlockSand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public enum SurfaceType {

	GRASS,
	DIRT,
	SAND,
	MIXED,
	UMBERSTONE,
	VOLCANIC_ROCK;

	public boolean matchBlock(IBlockState block) {
		switch (this) {
			case GRASS:
				return block == Blocks.GRASS.getDefaultState() || block == Blocks.MYCELIUM.getDefaultState();
			case DIRT:
				return block == Blocks.DIRT.getDefaultState();
			case SAND:
				return block == Blocks.SAND.getDefaultState() || block == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
			case MIXED:
				return block == Blocks.GRASS.getDefaultState() || block == Blocks.DIRT.getDefaultState() || block == Blocks.SAND.getDefaultState() || block == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
			case UMBERSTONE:
				return block == ModBlocks.UMBERSTONE.getDefaultState();
			case VOLCANIC_ROCK:
				return block == ModBlocks.VOLCANIC_ROCK.getDefaultState();
				default:
				return false;
		}
	}
}
