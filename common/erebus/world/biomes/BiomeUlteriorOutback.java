package erebus.world.biomes;
import net.minecraft.block.Block;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityRhinoBeetle;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScytodes;
import erebus.entity.EntitySolifuge;
import erebus.world.biomes.decorators.BiomeDecoratorBaseErebus.BiomeDecoratorEmpty;

// @formatter:off
public class BiomeUlteriorOutback extends BiomeBaseErebus{
	public BiomeUlteriorOutback(int biomeID){
		super(biomeID,new BiomeDecoratorEmpty());

		setBiomeName("Ulterior Outback");
		setColors(0xEEAA55);
		setFog(234,194,114);
		setTemperatureRainfall(1.1F,0.2F);
		setWeight(15);

		spawnableMonsterList.add(new SpawnEntry(EntityScytodes.class,30,1,4));
		spawnableMonsterList.add(new SpawnEntry(EntityScorpion.class,10,2,2));
		spawnableMonsterList.add(new SpawnEntry(EntitySolifuge.class,8,1,2));
		spawnableMonsterList.add(new SpawnEntry(EntityBlackWidow.class,5,1,1));
		
		spawnableCaveCreatureList.add(new SpawnEntry(EntityBeetle.class,8,1,2));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityRhinoBeetle.class,5,1,1));

		topBlock = (byte)Block.sand.blockID;
	}

	/*@Override
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
		
		if (rand.nextBoolean()){
			for(int attempt = 0, xx, yy, zz, id; attempt < 20; attempt++){
				xx = x + getRandomXZOffset(rand);
				yy = 20 + rand.nextInt(80);
				zz = z + getRandomXZOffset(rand);
				
				if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz) == Block.grass.blockID){
					new WorldGenAcaciaTree().generate(world,rand,xx,yy,zz);
					if (rand.nextBoolean())break;
				}
			}
		}
		
		for(int attempt = 0, xx, yy, zz; attempt < 420; attempt++){
			xx = x + getRandomXZOffset(rand);
			yy = 20 + rand.nextInt(80);
			zz = z + getRandomXZOffset(rand);

			if (world.getBlockId(xx,yy-1,zz) == Block.sand.blockID && world.isAirBlock(xx,yy,zz) && world.isAirBlock(xx,yy+1,zz)){
				world.setBlock(xx,yy,zz,ModBlocks.doubleHeightPlant.blockID,3,2);
				world.setBlock(xx,yy+1,zz,ModBlocks.doubleHeightPlant.blockID,3+8,2);
			}
		}

		for(int attempt = 0, xx, zz, id; attempt < 194; attempt++){
			xx = x + getRandomXZOffset(rand);
			zz = z + getRandomXZOffset(rand);

			for(int yy = 20; yy < 100; yy += rand.nextBoolean() ? 2 : 1){
				if ((((id = world.getBlockId(xx,yy - 1,zz)) == Block.sand.blockID) || id == Block.grass.blockID) && world.isAirBlock(xx,yy,zz)){
					if (rand.nextInt(10) == 0 && world.isAirBlock(xx,yy+1,zz)){
						world.setBlock(xx,yy,zz,ModBlocks.doubleHeightPlant.blockID,4,2);
						world.setBlock(xx,yy+1,zz,ModBlocks.doubleHeightPlant.blockID,4+8,2);
					}
					else world.setBlock(xx,yy,zz,ModBlocks.erebusGrass.blockID,1,2);
					
					break;
				}
			}
		}

		if (rand.nextBoolean()){
			for(int attempt = 0, xx, yy, zz; attempt < 180; attempt++){
				xx = x + getRandomXZOffset(rand);
				yy = 20 + rand.nextInt(80);
				zz = z + getRandomXZOffset(rand);

				if (world.getBlockId(xx,yy - 1,zz) == Block.grass.blockID && world.isAirBlock(xx,yy,zz)){
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
	}*/
}
// @formatter:on
