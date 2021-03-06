package erebus.world.biomes;

import erebus.entity.EntityBlackAnt;
import erebus.entity.EntityCrushroom;
import erebus.entity.EntityFungalWeevil;
import erebus.entity.EntityPunchroom;
import erebus.entity.EntityStagBeetle;
import erebus.entity.EntityZombieAnt;
import erebus.entity.EntityZombieAntSoldier;
import erebus.world.SpawnerErebus.SpawnEntry;
import erebus.world.biomes.decorators.BiomeDecoratorFungalForest;
import net.minecraft.init.Blocks;

public class BiomeFungalForest extends BiomeBaseErebus {
	public BiomeFungalForest(BiomeProperties properties) {
		super(properties, new BiomeDecoratorFungalForest());

		properties.setBaseBiome("Fungal Forest");
		properties.setTemperature(0.9F);
		properties.setRainDisabled();
		setColors(0x4E8833);
		setFog(203, 209, 218);
		topBlock = Blocks.GRASS.getDefaultState();

		spawningGradual.add(new SpawnEntry(EntityFungalWeevil.class, 10).setGroupSize(1, 1));
		spawningGradual.add(new SpawnEntry(EntityCrushroom.class, 5).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityBlackAnt.class, 15).setGroupSize(3, 5));
		spawningGradual.add(new SpawnEntry(EntityPunchroom.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityStagBeetle.class, 8).setGroupSize(1, 1));
		spawningGradual.add(new SpawnEntry(EntityZombieAnt.class, 8).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntityZombieAntSoldier.class, 8).setGroupSize(1, 2));
	}
}