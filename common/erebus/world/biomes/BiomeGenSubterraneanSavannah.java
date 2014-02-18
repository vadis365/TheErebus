package erebus.world.biomes;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import erebus.ModBlocks;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityFly;
import erebus.entity.EntityGlowWorm;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScytodes;
import erebus.entity.EntitySolifuge;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityWasp;
import erebus.world.feature.decoration.WorldGenRottenAcacia;
import erebus.world.feature.decoration.WorldGenSavannaRock;
import erebus.world.feature.plant.WorldGenBamboo;
import erebus.world.feature.tree.WorldGenAsperTree;
import erebus.world.feature.tree.WorldGenSavannaTree;
import erebus.world.feature.util.FeatureType;

//@formatter:off
public class BiomeGenSubterraneanSavannah extends BiomeGenBaseErebus{
	public BiomeGenSubterraneanSavannah(int biomeID){
		super(biomeID);
		
		setBiomeName("Subterranean Savannah");
		setColors(0xB6B957,0xA3A52D);
		setTemperatureRainfall(0.95F, 0.05F);
		setWeight(22);
		
		spawnableCreatureList.add(new SpawnListEntry(EntityGrasshopper.class,14,4,8));
		spawnableCreatureList.add(new SpawnListEntry(EntityGlowWorm.class,14,4,8));

		spawnableMonsterList.add(new SpawnListEntry(EntityScorpion.class,10,4,8));
		spawnableMonsterList.add(new SpawnListEntry(EntityWasp.class,30,4,8));
		spawnableMonsterList.add(new SpawnListEntry(EntityScytodes.class,35,1,4));
		spawnableMonsterList.add(new SpawnListEntry(EntityJumpingSpider.class,10,1,4));
		spawnableMonsterList.add(new SpawnListEntry(EntityTarantula.class,18,4,8));
		spawnableMonsterList.add(new SpawnListEntry(EntitySolifuge.class,5,1,3));

		spawnableCaveCreatureList.add(new SpawnListEntry(EntityFly.class,10,8,8));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityBeetle.class,8,1,2));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityBeetleLarva.class,8,2,4));

		topBlock=(byte)Block.grass.blockID;
		fillerBlock=(byte)Block.dirt.blockID;
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){
		for(int attempt=0; attempt<65; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=15+rand.nextInt(90),
				zz=z+getRandomXZOffset(rand);

			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenSavannaTree().generate(world,rand,xx,yy,zz);
			}
		}

		if (rand.nextInt(3)==0){
			for(int yy=100; yy>20; yy--){
				int xx=x+getRandomXZOffset(rand),
					zz=z+getRandomXZOffset(rand);

				if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
					new WorldGenSavannaRock().generate(world,rand,xx,yy,zz);
				}
			}
		}

		if (rand.nextInt(26)==0){
			for(int yy=100; yy>20; yy--){
				int xx=x+getRandomXZOffset(rand),
					zz=z+getRandomXZOffset(rand);
				
				if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
					new WorldGenBamboo(7).generate(world,rand,xx,yy,zz);
				}
			}
		}
		
		for(int attempt=0; attempt<10; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=rand.nextInt(120),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenAsperTree().generate(world,rand,xx,yy,zz);
			}
		}

		for(int attempt=0; attempt<28; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=15+rand.nextInt(90),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenRottenAcacia().generate(world,rand,xx,yy,zz);
			}
		}

		for(int attempt=0; attempt<180; attempt++){
			int xx=x+getRandomXZOffset(rand),
				yy=15+rand.nextInt(90),
				zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID){
				new WorldGenTallGrass(ModBlocks.erebusGrass.blockID,1).generate(world,rand,xx,yy,zz);
			}
		}
	}
	
	@Override
	public void generateFeature(World world, Random rand, int x, int z, FeatureType featureType){
		if ((featureType==FeatureType.AMBER_GROUND||featureType==FeatureType.AMBER_UMBERSTONE)&&rand.nextInt(4)!=0)return;
		super.generateFeature(world,rand,x,z,featureType);
	}
}
//@formatter:on