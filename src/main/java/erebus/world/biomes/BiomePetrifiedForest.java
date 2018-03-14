package erebus.world.biomes;

import erebus.ModBlocks;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityChameleonTick;
import erebus.entity.EntityFly;
import erebus.entity.EntityGlowWorm;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityMoth;
import erebus.entity.EntityScytodes;
import erebus.world.SpawnerErebus.SpawnEntry;
import erebus.world.biomes.decorators.BiomeDecoratorPetrifiedForest;

public class BiomePetrifiedForest extends BiomeBaseErebus {
	public BiomePetrifiedForest(BiomeProperties properties) {
		super(properties, new BiomeDecoratorPetrifiedForest());

		properties.setBaseBiome("Petrified Forest");
		properties.setTemperature(1.1F);
		properties.setRainDisabled();
		setColors(0xC1B668);
		setFog(234, 194, 114);

		spawningGradual.add(new SpawnEntry(EntityFly.class, 10).setGroupSize(8, 8));
		spawningGradual.add(new SpawnEntry(EntityBotFly.class, 10).setGroupSize(2, 3));
		spawningGradual.add(new SpawnEntry(EntityChameleonTick.class, 30).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityBlackWidow.class, 5).setGroupSize(1, 1));
		spawningGradual.add(new SpawnEntry(EntityGlowWorm.class, 8).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntityMoth.class, 15).setGroupSize(2, 3));
		spawningGradual.add(new SpawnEntry(EntityScytodes.class, 20).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntityJumpingSpider.class, 10).setGroupSize(2, 4));

		topBlock = ModBlocks.VOLCANIC_ROCK.getDefaultState();
		fillerBlock = ModBlocks.VOLCANIC_ROCK.getDefaultState();
	}
}