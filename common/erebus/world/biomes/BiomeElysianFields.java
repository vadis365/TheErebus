package erebus.world.biomes;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityFly;
import erebus.entity.EntityGlowWorm;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityMoth;
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

		spawnableCreatureList.add(new SpawnEntry(EntityGrasshopper.class,14,4,8));
		spawnableCreatureList.add(new SpawnEntry(EntityGlowWorm.class,14,4,8));

		spawnableCaveCreatureList.add(new SpawnEntry(EntityWorkerBee.class,14,1,2));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityMoth.class,10,2,3));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityFly.class,10,2,2));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityBeetle.class,6,1,2));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityBeetleLarva.class,4,2,3));
	}
}
// @formatter:on