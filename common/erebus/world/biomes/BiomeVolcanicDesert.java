package erebus.world.biomes;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenLakes;
import erebus.ModBlocks;
import erebus.entity.EntityAntlion;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityFireAnt;
import erebus.entity.EntityFly;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScytodes;
import erebus.entity.EntitySolifuge;
import erebus.world.feature.decoration.WorldGenRedGem;
import erebus.world.feature.decoration.WorldGenScorchedWood;
import erebus.world.feature.structure.WorldGenAntlionLair;
import erebus.world.feature.util.FeatureType;

// @formatter:off
public class BiomeVolcanicDesert extends BiomeBaseErebus{
	public BiomeVolcanicDesert(int biomeID){
		super(biomeID);
		
		setBiomeName("Volcanic Desert");
		setColors(0xA6BB4E,0x91A922);
		setFog(255,231,10);
		setTemperatureRainfall(1.9F,0.2F);
		setWeight(15);

		spawnableMonsterList.add(new SpawnEntry(EntityScorpion.class,30,1,8));
		spawnableMonsterList.add(new SpawnEntry(EntitySolifuge.class,30,1,8));
		spawnableMonsterList.add(new SpawnEntry(EntityFireAnt.class,30,1,8));
		spawnableMonsterList.add(new SpawnEntry(EntityBlackWidow.class,5,1,1));
		spawnableMonsterList.add(new SpawnEntry(EntityScytodes.class,35,1,4));
		spawnableMonsterList.add(new SpawnEntry(EntityJumpingSpider.class,10,1,4));
		spawnableMonsterList.add(new SpawnEntry(EntityAntlion.class,30,1,8));

		spawnableCaveCreatureList.add(new SpawnEntry(EntityBotFly.class,10,2,3));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityFly.class,10,8,8));

		topBlock=(byte)Block.sand.blockID;
		fillerBlock=(byte)Block.sandStone.blockID;
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){
		for(int attempt=0; attempt<35; attempt++){
			int xx=x+getRandomXZOffset(rand);
			int yy=15+rand.nextInt(90);
			int zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.sand.blockID){
				new WorldGenLakes(Block.lavaMoving.blockID).generate(world,world.rand,xx,yy,zz);
			}
		}

		for(int attempt=0; attempt<10; attempt++){
			int xx=x+getRandomXZOffset(rand);
			int yy=rand.nextInt(120);
			int zz=z+getRandomXZOffset(rand);
			
			if (world.getBlockId(xx,yy,zz)==ModBlocks.umberstone.blockID && world.isAirBlock(xx,yy-1,zz)){
				world.setBlock(xx,yy,zz,Block.lavaMoving.blockID);
				world.scheduledUpdatesAreImmediate=true;
				Block.blocksList[Block.lavaMoving.blockID].updateTick(world,xx,yy,zz,rand);
				world.scheduledUpdatesAreImmediate=false;
			}
		}

		for(int attempt=0; attempt<22; attempt++){
			int xx=x+getRandomXZOffset(rand);
			int yy=rand.nextInt(120);
			int zz=z+getRandomXZOffset(rand);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.sand.blockID && !world.isAirBlock(xx,yy-2,zz)){
				new WorldGenScorchedWood().generate(world,rand,xx,yy,zz);
				if (rand.nextInt(4)!=0) break;
			}
		}

		if (rand.nextInt(34)==0){
			WorldGenAntlionLair genAntlionLair=new WorldGenAntlionLair();
			
			for(int attempt=0; attempt<15; attempt++){
				if (genAntlionLair.generate(world,rand,x+5+rand.nextInt(6)+8,15+rand.nextInt(35),z+5+rand.nextInt(6)+8)) break;
			}
		}
	}
	
	@Override
	public void generateFeature(World world, Random rand, int x, int z, FeatureType featureType){
		if (featureType==FeatureType.REDGEM){
			for(int attempt=0; attempt<10; attempt++){
				new WorldGenRedGem().generate(world,rand,x+getRandomXZOffset(rand),rand.nextInt(64),z+getRandomXZOffset(rand));
			}
		}
		else super.generateFeature(world,rand,x,z,featureType);
	}
}
// @formatter:on