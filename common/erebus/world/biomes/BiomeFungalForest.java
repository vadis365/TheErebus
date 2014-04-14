package erebus.world.biomes;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import erebus.world.feature.plant.WorldGenBigMushroomErebusMany;

// @formatter:off
public class BiomeFungalForest extends BiomeBaseErebus{
	public BiomeFungalForest(int biomeID){
		super(biomeID);
		
		setBiomeName("Fungal Forest");
		setColors(0x4E8833);
		setTemperatureRainfall(0.9F, 0.95F);
		setWeight(100);
	}
	
	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z) {
		
		for(int attempt=0; attempt<12; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=15+rand.nextInt(90),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenBigMushroomErebusMany(0).generate(world,rand,xx,yy,zz);
			}
		}

		for(int attempt=0; attempt<20; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=15+rand.nextInt(90),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenBigMushroomErebusMany(1).generate(world,rand,xx,yy,zz);
			}
		}
		
		for(int attempt=0; attempt<20; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=15+rand.nextInt(90),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenBigMushroomErebusMany(2).generate(world,rand,xx,yy,zz);
			}
		}
		
		for(int attempt=0; attempt<20; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=15+rand.nextInt(90),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenBigMushroomErebusMany(3).generate(world,rand,xx,yy,zz);
			}
		}
		
		for(int attempt=0; attempt<20; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=15+rand.nextInt(90),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenBigMushroomErebusMany(4).generate(world,rand,xx,yy,zz);
			}
		}
	}
}
//@formatter:on