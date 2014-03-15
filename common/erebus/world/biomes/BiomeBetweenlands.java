package erebus.world.biomes;
import java.util.Random;
import net.minecraft.world.World;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityMosquito;

// @formatter:off
public class BiomeBetweenlands extends BiomeBaseErebus{
	public BiomeBetweenlands(int biomeID){
		super(biomeID);
		
		setBiomeName("Betweenlands");
		setColors(0x314D31);
		setTemperatureRainfall(0.75F, 0.85F);
		setWeight(15);
		
		spawnableMonsterList.add(new SpawnEntry(EntityCentipede.class,10,4,8));
		spawnableMonsterList.add(new SpawnEntry(EntityJumpingSpider.class,10,2,6));
		
		spawnableCaveCreatureList.add(new SpawnEntry(EntityMosquito.class,20,1,2));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityDragonfly.class,20,1,2));
	}

	@Override
	public void generateBiomeFeatures(World world, Random rand, int x, int z){}
}
//@formatter:on