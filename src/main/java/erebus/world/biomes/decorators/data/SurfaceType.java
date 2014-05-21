package erebus.world.biomes.decorators.data;
import net.minecraft.block.Block;

public enum SurfaceType{
	GRASS, DIRT, SAND, MIXED;
	
	public boolean matchBlock(int blockID){
		switch(this){
			case GRASS: return blockID == Block.grass.blockID;
			case DIRT: return blockID == Block.dirt.blockID;
			case SAND: return blockID == Block.sand.blockID;
			case MIXED: return blockID == Block.grass.blockID || blockID == Block.dirt.blockID || blockID == Block.sand.blockID;
			default: return false;
		}
	}
}
