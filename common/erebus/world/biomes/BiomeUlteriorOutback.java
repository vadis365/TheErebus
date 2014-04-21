package erebus.world.biomes;
import net.minecraft.block.Block;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityRhinoBeetle;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScytodes;
import erebus.entity.EntitySolifuge;
import erebus.world.biomes.decorators.BiomeDecoratorUlteriorOutback;

// @formatter:off
public class BiomeUlteriorOutback extends BiomeBaseErebus{
	public BiomeUlteriorOutback(int biomeID){
		super(biomeID,new BiomeDecoratorUlteriorOutback());

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
}
// @formatter:on
