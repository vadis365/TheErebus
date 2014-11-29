package erebus.world.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import erebus.ModBlocks;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityMosquito;
import erebus.world.ChunkProviderErebus;
import erebus.world.SpawnerErebus.SpawnEntry;
import erebus.world.biomes.decorators.BiomeDecoratorSubmergedSwamp;

public class BiomeSubmergedSwamp extends BiomeBaseErebus {

	public BiomeSubmergedSwamp(int biomeID) {
		super(biomeID, new BiomeDecoratorSubmergedSwamp());

		setBiomeName("Submerged Swamp");
		setColors(0x314D31);
		setTemperatureRainfall(0.75F, 0.85F);
		//TODO Fix the swamp biome weight
		setWeight(20000);
		waterColorMultiplier = 6051893;
		spawningGradual.add(new SpawnEntry(EntityCentipede.class, 10).setGroupSize(4, 8));
		spawningGradual.add(new SpawnEntry(EntityJumpingSpider.class, 10).setGroupSize(2, 6));
		spawningGradual.add(new SpawnEntry(EntityMosquito.class, 20).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityDragonfly.class, 20).setGroupSize(1, 2));
	}

	/*
	 * @Override public void generateBiomeFeatures(World world, Random rand, int
	 * x, int z){}
	 *
	 * @Override public void generateFeature(World world, Random rand, int x,
	 * int z, FeatureType featureType){ if (featureType !=
	 * FeatureType.REDGEM)generateFeature(world,rand,x,z,featureType); }
	 *
	 * @Override public void generateOre(World world, Random rand, int x, int z,
	 * OreType oreType, boolean extraOres){ if (oreType == OreType.FOSSIL){ if
	 * (rand
	 * .nextInt(7)==0)generateOreCluster(1+rand.nextInt(2)*rand.nextInt(2),
	 * ModBlocks .oreFossil,3,9,12,world,rand,x,z,36,112,3); } else
	 * super.generateOre(world,rand,x,z,oreType,extraOres); }
	 */

	@Override
	public Block placeCaveBlock(Block block, int x, int y, int z, Random rand) {
		return block == ModBlocks.umberstone || block == topBlock || block == fillerBlock || block == Blocks.sandstone ? y < ChunkProviderErebus.swampWaterHeight - 1 ? Blocks.flowing_water : Blocks.air : block;
	}
}