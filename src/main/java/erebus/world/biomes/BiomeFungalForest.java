package erebus.world.biomes;

import net.minecraft.init.Blocks;
import erebus.world.biomes.decorators.BiomeDecoratorFungalForest;

public class BiomeFungalForest extends BiomeBaseErebus {
	public BiomeFungalForest(BiomeProperties properties) {
		super(properties, new BiomeDecoratorFungalForest());

		properties.setBaseBiome("Fungal Forest");
		properties.setTemperature(0.9F);
		properties.setRainDisabled();
		setColors(0x4E8833);
		setWeight(12);
		topBlock = Blocks.GRASS.getDefaultState();
/*
		spawningGradual.add(new SpawnEntry(EntityCrushling.class, 10).setGroupSize(1, 1));
		spawningGradual.add(new SpawnEntry(EntityBlackAnt.class, 15).setGroupSize(3, 5));
		spawningGradual.add(new SpawnEntry(EntityZombieAnt.class, 8).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntityCrushroom.class, 5).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityPunchroom.class, 10).setGroupSize(1, 2));
		*/
	}
}