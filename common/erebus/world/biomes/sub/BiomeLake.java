package erebus.world.biomes.sub;
import java.util.Random;
import erebus.world.biomes.BiomeUndergroundJungle;
import net.minecraft.block.Block;
import net.minecraft.world.World;

// @formatter:off
public class BiomeLake extends BiomeUndergroundJungle{
	public BiomeLake(int biomeID){
		super(biomeID);
		
		setBiomeName("Underground Jungle - Lake");
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){System.out.println("gen at "+x+","+z);
		for(int xx = x; xx < x+16; xx++){
			for(int zz = z; zz < z+16; zz++){
				for(int yy = 10; yy < 28; yy++){
					if (world.getBlockId(xx,yy,zz) == 0){
						world.setBlock(xx,yy,zz,Block.waterStill.blockID);
						if (world.getBlockId(xx,yy-1,zz) == Block.grass.blockID)world.setBlock(xx,yy-1,zz,Block.dirt.blockID);
					}
				}
			}
		}
	}
}
// @formatter:on