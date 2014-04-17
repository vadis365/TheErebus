package erebus.world.biomes;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.world.feature.decoration.WorldGenRedGem;
import erebus.world.feature.tree.WorldGenEucalyptusTree;
import erebus.world.feature.util.FeatureType;

// @formatter:off
public class BiomeUlteriorOutback extends BiomeBaseErebus{
	public BiomeUlteriorOutback(int biomeID){
		super(biomeID);

		setBiomeName("Ulterior Outback");
		setColors(0xEEAA55);
		setFog(234,194,114);
		setTemperatureRainfall(1.1F,0.2F);
		setWeight(15);

		// TODO grab from #59

		topBlock = (byte)Block.sand.blockID;
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){
		for(int attempt = 0, xx, zz; attempt < 112; attempt++){
			xx = x + getRandomXZOffset(rand);
			zz = z + getRandomXZOffset(rand);

			for(int yy = 20; yy < 100; yy += rand.nextInt(2) + 1){
				if (world.getBlockId(xx,yy,zz) == Block.sand.blockID && world.isAirBlock(xx,yy + 1,zz)){
					world.setBlock(xx,yy,zz,Block.grass.blockID);
					break;
				}
			}
		}
		
		for(int attempt = 0, xx, yy, zz, id; attempt < 420; attempt++){
			xx = x + getRandomXZOffset(rand);
			yy = 20 + rand.nextInt(80);
			zz = z + getRandomXZOffset(rand);

			if (!world.isAirBlock(xx,yy,zz) || !world.isAirBlock(xx,yy+1,zz))continue;
			id = world.getBlockId(xx,yy - 1,zz);
			
			if (id == Block.grass.blockID){
				world.setBlock(xx,yy,zz,ModBlocks.doubleHeightPlant.blockID,4,2);
				world.setBlock(xx,yy+1,zz,ModBlocks.doubleHeightPlant.blockID,4+8,2);
			}
			else if (id == Block.sand.blockID){
				world.setBlock(xx,yy,zz,ModBlocks.doubleHeightPlant.blockID,3,2);
				world.setBlock(xx,yy+1,zz,ModBlocks.doubleHeightPlant.blockID,3+8,2);
			}
		}

		for(int attempt = 0, xx, zz, id; attempt < 164; attempt++){
			xx = x + getRandomXZOffset(rand);
			zz = z + getRandomXZOffset(rand);

			for(int yy = 20; yy < 100; yy += rand.nextInt(2) + 1){
				if (world.isAirBlock(xx,yy,zz) && (((id = world.getBlockId(xx,yy - 1,zz)) == Block.sand.blockID) || id == Block.grass.blockID)){
					world.setBlock(xx,yy,zz,ModBlocks.erebusGrass.blockID,1,2);
					break;
				}
			}
		}

		if (rand.nextBoolean()){
			for(int attempt = 0, xx, yy, zz; attempt < 180; attempt++){
				xx = x + getRandomXZOffset(rand);
				yy = 20 + rand.nextInt(80);
				zz = z + getRandomXZOffset(rand);

				if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy - 1,zz) == Block.grass.blockID){
					if (new WorldGenEucalyptusTree().generate(world,rand,xx,yy,zz) && rand.nextBoolean()) break;
				}
			}
		}
	}

	@Override
	public void generateFeature(World world, Random rand, int x, int z, FeatureType featureType){
		if (featureType == FeatureType.REDGEM){
			for(int attempt = 0; attempt < 8; attempt++){
				new WorldGenRedGem().generate(world,rand,x + getRandomXZOffset(rand),64 + rand.nextInt(60),z + getRandomXZOffset(rand));
			}
		}
		else generateFeature(world,rand,x,z,featureType);
	}
}
// @formatter:on
