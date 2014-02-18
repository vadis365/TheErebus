package erebus.world.biomes;

import java.util.Random;
import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTallGrass;

public class BiomeUndergroundJungleSubTest extends BiomeUndergroundJungle{
	public BiomeUndergroundJungleSubTest(int biomeID){
		super(biomeID);
		
		setBiomeName("Underground Jungle - Sub_Test");
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){
		for(int attempt=0; attempt<150; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=15+rand.nextInt(90),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenTallGrass(ModBlocks.fern.blockID,1).generate(world,rand,xx,yy,zz);
			}
		}
	}
}
