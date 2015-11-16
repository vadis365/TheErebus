package erebus.world.biomes.decorators;

import java.util.Random;

import erebus.world.biomes.decorators.data.FeatureType;
import erebus.world.biomes.decorators.data.OreSettings;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.feature.decoration.WorldGenRedGem;
import net.minecraft.world.World;

public abstract class BiomeDecoratorBaseErebus {

	protected World world;
	protected Random rand;
	protected int x, z;
	protected int xx, yy, zz, attempt;
	private boolean isDecorating = false;

	private static final OreSettings oreGen = new OreSettings();

	protected static final WorldGenRedGem genRedGem = new WorldGenRedGem();

	protected BiomeDecoratorBaseErebus() {
	}

	public final void populate(World world, Random rand, int x, int z) {
		this.world = world;
		this.rand = rand;
		this.x = x;
		this.z = z;
		populate();
	}

	public final void decorate(World world, Random rand, int x, int z) {
		if (isDecorating)
			throw new RuntimeException("Already decorating Erebus!");
		isDecorating = true;
		this.world = world;
		this.rand = rand;
		this.x = x;
		this.z = z;

		for (FeatureType featureType : FeatureType.values())
			generateFeature(featureType);

		boolean extraOres = OreType.LEAD.isEnabled() || OreType.SILVER.isEnabled() || OreType.COPPER.isEnabled() || OreType.TIN.isEnabled() || OreType.ALUMINIUM.isEnabled();

		for (OreType oreType : OreType.values())
			if (oreType.isEnabled()) {
				oreType.setupDefault(oreGen, extraOres);
				modifyOreGen(oreGen, oreType, extraOres);
				oreGen.generate(world, rand, x, z);
			}

		decorate();

		isDecorating = false;
	}

	protected void populate() {
	}

	protected void decorate() {
	}

	protected void modifyOreGen(OreSettings oreGen, OreType oreType, boolean extraOres) {
	}

	protected void generateFeature(FeatureType featureType) {
		switch (featureType) {
			case REDGEM:
				for (attempt = 0; attempt < 5; attempt++)
					genRedGem.generate(world, rand, x + offsetXZ(), 64 + rand.nextInt(60), z + offsetXZ());
				break;
		}
	}

	protected final int offsetXZ() {
		return rand.nextInt(16) + 8;
	}

	protected boolean checkSurface(SurfaceType surfaceType, int x, int y, int z) {
		return surfaceType.matchBlock(world.getBlock(x, y - 1, z)) && world.isAirBlock(x, y, z);
	}

	public static class BiomeDecoratorEmpty extends BiomeDecoratorBaseErebus {
	}
}