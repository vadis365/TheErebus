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
import erebus.world.feature.plant.WorldGenGiantFlowers;
import erebus.world.feature.tree.WorldGenCypressTree;

// @formatter:off
public class BiomeElysianFields extends BiomeBaseErebus{
	public BiomeElysianFields(int biomeID){
		super(biomeID);
		
		setBiomeName("Elysian Fields");
		setColors(0xC6FF54);
		setTemperatureRainfall(0.85F, 0.5F);
		setWeight(200);
		
		spawnableCreatureList.add(new SpawnEntry(EntityGrasshopper.class,14,4,8));
		spawnableCreatureList.add(new SpawnEntry(EntityGlowWorm.class,14,4,8));
		
		spawnableMonsterList.add(new SpawnEntry(EntityWorkerBee.class,14,4,8));
		spawnableMonsterList.add(new SpawnEntry(EntityMoth.class,5,4,4));
		
		spawnableCaveCreatureList.add(new SpawnEntry(EntityFly.class,10,2,2));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityBeetle.class,6,1,2));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityBeetleLarva.class,4,2,3));
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){
		for(int attempt = 0, xx, yy, zz; attempt < 200; attempt++){
			xx = x+getRandomXZOffset(rand);
			zz = z+getRandomXZOffset(rand);
			yy = 20+rand.nextInt(80);
			
			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz) == Block.grass.blockID)
				new WorldGenCypressTree().generate(world,rand,xx,yy,zz);
		}
		
		for(int attempt = 0, xx, yy, zz; attempt<85; attempt++){
			xx = x+getRandomXZOffset(rand);
			yy = 15+rand.nextInt(90);
			zz = z+getRandomXZOffset(rand);

			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz) == Block.grass.blockID){
				new WorldGenGiantFlowers().generate(world,rand,xx,yy,zz);
			}
		}
	}
}
// @formatter:on