package erebus.world.biomes;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import erebus.world.feature.structure.WorldGenMushroomHouse;

// @formatter:off
public class BiomeFungalForest extends BiomeBaseErebus{
	public BiomeFungalForest(int biomeID){
		super(biomeID);
		
		setBiomeName("Fungal Forest");
		setColors(0x4E8833);
		setTemperatureRainfall(0.9F, 0.95F);
		setWeight(12);
	}
	
	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){
		
		for(int attempt=0; attempt<200; attempt++){
			int yy=15+rand.nextInt(90);
			new WorldGenMushroomHouse().generate(world,rand,x,yy,z);
		}
		
		new WorldGenFlowers(Block.mushroomBrown.blockID).generate(world,rand,x+getRandomXZOffset(rand),rand.nextInt(128),z+getRandomXZOffset(rand));
		new WorldGenFlowers(Block.mushroomRed.blockID).generate(world,rand,x+getRandomXZOffset(rand),rand.nextInt(128),z+getRandomXZOffset(rand));

		for(int attempt=0; attempt<12; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=15+rand.nextInt(90),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenBigMushroom(0).generate(world,rand,xx,yy,zz);
			}
		}

		for(int attempt=0; attempt<20; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=15+rand.nextInt(90),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenBigMushroom(1).generate(world,rand,xx,yy,zz);
			}
		}
	}
}
//@formatter:on