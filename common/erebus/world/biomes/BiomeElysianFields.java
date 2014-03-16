package erebus.world.biomes;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityFly;
import erebus.entity.EntityGlowWorm;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityMoth;
import erebus.entity.EntityWorkerBee;
import erebus.world.feature.decoration.WorldGenRedGem;
import erebus.world.feature.plant.WorldGenGiantFlowers;
import erebus.world.feature.tree.WorldGenCypressTree;
import erebus.world.feature.util.FeatureType;
import erebus.world.feature.util.OreType;

// @formatter:off
public class BiomeElysianFields extends BiomeBaseErebus{
	public BiomeElysianFields(int biomeID){
		super(biomeID);

		setBiomeName("Elysian Fields");
		setColors(0xC6FF54);
		setTemperatureRainfall(0.85F,0.5F);
		setWeight(20);

		spawnableCreatureList.add(new SpawnEntry(EntityGrasshopper.class,14,4,8));
		spawnableCreatureList.add(new SpawnEntry(EntityGlowWorm.class,14,4,8));

		spawnableCaveCreatureList.add(new SpawnEntry(EntityWorkerBee.class,14,1,2));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityMoth.class,10,2,3));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityFly.class,10,2,2));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityBeetle.class,6,1,2));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityBeetleLarva.class,4,2,3));
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){
		for(int attempt = 0, xx, yy, zz; attempt < 200; attempt++){
			xx = x + getRandomXZOffset(rand);
			zz = z + getRandomXZOffset(rand);
			yy = 20 + rand.nextInt(80);

			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy - 1,zz) == Block.grass.blockID){
				new WorldGenCypressTree().generate(world,rand,xx,yy,zz);
			}
		}

		if (rand.nextBoolean()){
			for(int attempt = 0, xx, yy, zz; attempt < 65; attempt++){
				xx = x + getRandomXZOffset(rand);
				yy = 15 + rand.nextInt(90);
				zz = z + getRandomXZOffset(rand);

				if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy - 1,zz) == Block.grass.blockID){
					new WorldGenGiantFlowers().generate(world,rand,xx,yy,zz);
				}
			}
		}
	}

	@Override
	public void generateFeature(World world, Random rand, int x, int z, FeatureType featureType){
		if (featureType == FeatureType.REDGEM){
			for(int attempt = 0; attempt < 2 + rand.nextInt(2); attempt++){
				new WorldGenRedGem().generate(world,rand,x + getRandomXZOffset(rand),64 + rand.nextInt(60),z + getRandomXZOffset(rand));
			}
		}
		else generateFeature(world,rand,x,z,featureType);
	}
	
	@Override
	public void generateOre(World world, Random rand, int x, int z, OreType oreType, boolean extraOres){
		if (oreType != OreType.FOSSIL)super.generateOre(world,rand,x,z,oreType,extraOres);
	}
}
// @formatter:on