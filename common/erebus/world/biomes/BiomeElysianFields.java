package erebus.world.biomes;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityFly;
import erebus.entity.EntityGlowWorm;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityMoth;
import erebus.entity.EntityRhinoBeetle;
import erebus.entity.EntityTitanBeetle;
import erebus.entity.EntityVelvetWorm;
import erebus.entity.EntityWorkerBee;
import erebus.world.biomes.decorators.BiomeDecoratorElysianFields;

// @formatter:off
public class BiomeElysianFields extends BiomeBaseErebus{
	public BiomeElysianFields(int biomeID){
		super(biomeID,new BiomeDecoratorElysianFields());

		setBiomeName("Elysian Fields");
		setColors(0xC6FF54);
		setTemperatureRainfall(0.85F,0.5F);
		setWeight(20);

		spawnableCaveCreatureList.add(new SpawnEntry(EntityGrasshopper.class,4,1,3));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityGlowWorm.class,4,1,3));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityTitanBeetle.class,4,1,1));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityWorkerBee.class,14,1,2));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityMoth.class,10,2,3));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityFly.class,10,2,2));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityBeetle.class,6,1,2));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityBeetleLarva.class,4,2,3));
		
		spawnableMonsterList.add(new SpawnEntry(EntityDragonfly.class,5,1,3));
		spawnableMonsterList.add(new SpawnEntry(EntityVelvetWorm.class,10,1,2));
	}
}
// @formatter:on