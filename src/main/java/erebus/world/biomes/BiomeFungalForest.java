package erebus.world.biomes;

import net.minecraft.init.Blocks;
import erebus.entity.EntityBlackAnt;
import erebus.entity.EntityCrushling;
import erebus.entity.EntityCrushroom;
import erebus.entity.EntityPunchroom;
import erebus.entity.EntityStagBeetle;
import erebus.entity.EntityZombieAnt;
import erebus.entity.EntityZombieAntSoldier;
import erebus.world.SpawnerErebus.SpawnEntry;
import erebus.world.biomes.decorators.BiomeDecoratorFungalForest;

public class BiomeFungalForest extends BiomeBaseErebus {
	public BiomeFungalForest(int biomeID) {
		super(biomeID, new BiomeDecoratorFungalForest());

		setBiomeName("Fungal Forest");
		setColors(0x4E8833);
		setTemperatureRainfall(0.9F, 0.95F);
		setWeight(12);
		topBlock = Blocks.grass;

		spawningGradual.add(new SpawnEntry(EntityCrushling.class, 10).setGroupSize(1, 1));
		spawningGradual.add(new SpawnEntry(EntityBlackAnt.class, 15).setGroupSize(3, 5));
		spawningGradual.add(new SpawnEntry(EntityZombieAnt.class, 8).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntityCrushroom.class, 5).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityPunchroom.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityZombieAntSoldier.class, 8).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityStagBeetle.class, 8).setGroupSize(1, 1));
	}
}