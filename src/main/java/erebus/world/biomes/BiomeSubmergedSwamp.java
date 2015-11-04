package erebus.world.biomes;

import java.util.Random;

import erebus.ModBlocks;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBloodSnail;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityLeech;
import erebus.entity.EntityMosquito;
import erebus.entity.EntityPondSkater;
import erebus.entity.EntitySnapper;
import erebus.entity.EntityWisp;
import erebus.world.ChunkProviderErebus;
import erebus.world.SpawnerErebus.SpawnEntry;
import erebus.world.biomes.decorators.BiomeDecoratorSubmergedSwamp;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class BiomeSubmergedSwamp extends BiomeBaseErebus {

	public BiomeSubmergedSwamp(int biomeID) {
		super(biomeID, new BiomeDecoratorSubmergedSwamp());

		setBiomeName("Submerged Swamp");
		setColors(0x314D31);
		setTemperatureRainfall(0.75F, 0.85F);
		setWeight(20);
		waterColorMultiplier = 6051893;
		spawningGradual.add(new SpawnEntry(EntityCentipede.class, 10).setGroupSize(4, 8));
		spawningGradual.add(new SpawnEntry(EntityJumpingSpider.class, 10).setGroupSize(2, 4));
		spawningGradual.add(new SpawnEntry(EntityMosquito.class, 20).setGroupSize(3, 4));
		spawningGradual.add(new SpawnEntry(EntityDragonfly.class, 20).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityPondSkater.class, 100).setGroupSize(3, 5));
		spawningGradual.add(new SpawnEntry(EntityLeech.class, 20).setGroupSize(3, 5));
		spawningGradual.add(new SpawnEntry(EntitySnapper.class, 20).setGroupSize(2, 3));
		spawningGradual.add(new SpawnEntry(EntityWisp.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityBloodSnail.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityBeetleLarva.class, 25).setGroupSize(2, 4));
		spawningGradual.add(new SpawnEntry(EntityBeetle.class, 20).setGroupSize(1, 2));
	}

	@Override
	public Block placeCaveBlock(Block block, int x, int y, int z, Random rand) {
		return block == ModBlocks.umberstone || block == topBlock || block == fillerBlock || block == Blocks.sandstone ? y < ChunkProviderErebus.swampWaterHeight - 1 ? Blocks.flowing_water : Blocks.air : block;
	}
}