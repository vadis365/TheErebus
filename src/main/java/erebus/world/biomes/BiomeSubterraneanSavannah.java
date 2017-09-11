package erebus.world.biomes;

import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityFly;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityWasp;
import erebus.world.SpawnerErebus.SpawnEntry;
import erebus.world.biomes.decorators.BiomeDecoratorSubterraneanSavannah;
import net.minecraft.init.Blocks;

public class BiomeSubterraneanSavannah extends BiomeBaseErebus {
	public BiomeSubterraneanSavannah(BiomeProperties properties) {
		super(properties, new BiomeDecoratorSubterraneanSavannah());

		properties.setBaseBiome("Subterranean Savannah");
		properties.setTemperature(0.95F);
		properties.setRainDisabled();
		setColors(0xB6B957, 0xA3A52D);
		setFog(140, 116, 9);
		//setWeight(20);
/*
		spawningGradual.add(new SpawnEntry(EntityGlowWorm.class, 8).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntityScytodes.class, 20).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntityTarantula.class, 10).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntityScorpion.class, 10).setGroupSize(1, 3));
		spawningGradual.add(new SpawnEntry(EntityChameleonTick.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityMosquito.class, 60).setGroupSize(1, 3));

*/
		spawningGradual.add(new SpawnEntry(EntityFly.class, 15).setGroupSize(8, 8));
		spawningGradual.add(new SpawnEntry(EntityWasp.class, 20).setGroupSize(4, 8));
		spawningGradual.add(new SpawnEntry(EntityBeetleLarva.class, 25).setGroupSize(2, 4));
		spawningGradual.add(new SpawnEntry(EntityBeetle.class, 20).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityGrasshopper.class, 10).setGroupSize(4, 8));

		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
	}
}