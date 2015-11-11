package erebus.world.biomes;

import net.minecraft.init.Blocks;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityChameleonTick;
import erebus.entity.EntityFly;
import erebus.entity.EntityHoneyPotAnt;
import erebus.entity.EntityMidgeSwarm;
import erebus.entity.EntityRhinoBeetle;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScytodes;
import erebus.entity.EntitySolifuge;
import erebus.entity.EntityTarantula;
import erebus.world.SpawnerErebus.SpawnEntry;
import erebus.world.biomes.decorators.BiomeDecoratorUlteriorOutback;

public class BiomeUlteriorOutback extends BiomeBaseErebus {
	public BiomeUlteriorOutback(int biomeID) {
		super(biomeID, new BiomeDecoratorUlteriorOutback());

		setBiomeName("Ulterior Outback");
		setColors(0xC1B668);
		setFog(234, 194, 114);
		setTemperatureRainfall(1.1F, 0.2F);
		setWeight(15);

		spawningGradual.add(new SpawnEntry(EntityScytodes.class, 20).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntityScorpion.class, 10).setGroupSize(2, 2));
		spawningGradual.add(new SpawnEntry(EntitySolifuge.class, 10).setGroupSize(1, 3));
		spawningGradual.add(new SpawnEntry(EntityBlackWidow.class, 5).setGroupSize(1, 1));
		spawningGradual.add(new SpawnEntry(EntityCentipede.class, 10).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntityBotFly.class, 10).setGroupSize(2, 3));
		spawningGradual.add(new SpawnEntry(EntityTarantula.class, 10).setGroupSize(1, 3));
		spawningGradual.add(new SpawnEntry(EntityChameleonTick.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityMidgeSwarm.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityRhinoBeetle.class, 10).setGroupSize(1, 1));
		spawningGradual.add(new SpawnEntry(EntityBeetleLarva.class, 15).setGroupSize(2, 4));
		spawningGradual.add(new SpawnEntry(EntityFly.class, 10).setGroupSize(2, 2));
		spawningGradual.add(new SpawnEntry(EntityHoneyPotAnt.class, 10).setGroupSize(2, 4));

		topBlock = Blocks.sand;
		topBlockMeta = 1;
	}
}