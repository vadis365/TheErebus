package erebus.world.biomes;
import net.minecraft.block.Block;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityChameleonTick;
import erebus.entity.EntityFly;
import erebus.entity.EntityRhinoBeetle;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScytodes;
import erebus.entity.EntitySolifuge;
import erebus.entity.EntityTarantula;
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
		spawnableMonsterList.add(new SpawnEntry(EntitySolifuge.class,5,1,3));
		spawnableMonsterList.add(new SpawnEntry(EntityCentipede.class,10,4,8));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityBotFly.class,10,2,3));
		spawnableMonsterList.add(new SpawnEntry(EntityTarantula.class,8,4,8));
		spawnableMonsterList.add(new SpawnEntry(EntityChameleonTick.class,10,1,2));
		
		spawnableCaveCreatureList.add(new SpawnEntry(EntityRhinoBeetle.class,5,1,1));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityBeetleLarva.class,8,2,4));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityFly.class,10,2,2));
		
		topBlock = (byte)Block.sand.blockID;
	}
}
// @formatter:on
