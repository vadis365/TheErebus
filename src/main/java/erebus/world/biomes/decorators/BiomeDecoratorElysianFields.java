package erebus.world.biomes.decorators;

import erebus.ModBlocks;
import erebus.world.biomes.decorators.data.FeatureType;
import erebus.world.biomes.decorators.data.OreSettings;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.feature.decoration.WorldGenPonds;
import erebus.world.feature.plant.WorldGenGiantFlowers;
import erebus.world.feature.plant.WorldGenMossPatch;
import erebus.world.feature.plant.WorldGenNettlePatch;
import erebus.world.feature.tree.WorldGenCypressTree;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorElysianFields extends BiomeDecoratorBaseErebus {
	protected final WorldGenPonds genPonds = new WorldGenPonds();
	protected final WorldGenNettlePatch genNettle = new WorldGenNettlePatch();

	protected final WorldGenerator genTreeCypress = new WorldGenCypressTree();
	protected final WorldGenerator genGiantFlowers = new WorldGenGiantFlowers();

	protected final WorldGenerator genMossPatch = new WorldGenMossPatch(0);

	protected boolean generateFlowers = true;

	@Override
	protected void populate() {
		if (rand.nextInt(3) == 0)
			for (attempt = 0; attempt < 25; attempt++) {
				xx = x + 16;
				yy = 20 + rand.nextInt(90);
				zz = z + 16;

				if (checkSurface(SurfaceType.GRASS, xx, yy, zz)) {
					genPonds.prepare((rand.nextDouble() + 0.7D) * 1.5D);
					genPonds.generate(world, rand, xx, yy, zz);
					if (rand.nextBoolean())
						break;
				}
			}
	}

	@Override
	public void decorate() {
		for (attempt = 0; attempt < 105; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();
			yy = 20 + rand.nextInt(80);

			if (checkSurface(SurfaceType.GRASS, xx, yy, zz))
				genTreeCypress.generate(world, rand, xx, yy, zz);
		}

		if (generateFlowers && rand.nextBoolean())
			for (int attempt = 0, xx, yy, zz; attempt < 65; attempt++) {
				xx = x + offsetXZ();
				yy = 15 + rand.nextInt(90);
				zz = z + offsetXZ();

				if (checkSurface(SurfaceType.GRASS, xx, yy, zz))
					genGiantFlowers.generate(world, rand, xx, yy, zz);
			}

		if (rand.nextInt(6) == 0)
			for (attempt = 0; attempt < rand.nextInt(4); attempt++) {
				xx = x + offsetXZ();
				yy = 25 + rand.nextInt(75);
				zz = z + offsetXZ();

				for (; yy > 20; yy--)
					if (checkSurface(SurfaceType.GRASS, xx, yy, zz)) {
						genNettle.generate(world, rand, xx, yy, zz);
						break;
					}
			}

		for (attempt = 0; attempt < 35; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();

			for (yy = rand.nextInt(3) == 0 ? 40 + rand.nextInt(35) : 22; yy < 100; yy += rand.nextBoolean() ? 2 : 1)
				if (checkSurface(SurfaceType.MIXED, xx, yy, zz)) {
					if (rand.nextInt(10) == 0 && world.isAirBlock(xx, yy + 1, zz)) {
						world.setBlock(xx, yy, zz, Blocks.double_plant, 2, 2);
						world.setBlock(xx, yy + 1, zz, Blocks.double_plant, 10, 2);
					} else
						world.setBlock(xx, yy, zz, Blocks.tallgrass, 1, 2);

					break;
				}
		}

		for (attempt = 0; attempt < 12; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();

			for (yy = 20; yy < 100; yy += rand.nextBoolean() ? 2 : 1)
				if (checkSurface(SurfaceType.MIXED, xx, yy, zz)) {
					if (rand.nextInt(10) == 0 && world.isAirBlock(xx, yy + 1, zz)) {
						world.setBlock(xx, yy, zz, Blocks.double_plant, 3, 2);
						world.setBlock(xx, yy + 1, zz, Blocks.double_plant, 10, 2);
					} else
						world.setBlock(xx, yy, zz, ModBlocks.fern, 0, 2);

					break;
				}
		}

		for (attempt = 0; attempt < 5; attempt++) {
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();

			if (world.getBlock(xx, yy - 1, zz) == Blocks.grass && world.isAirBlock(xx, yy, zz) && world.isAirBlock(xx, yy + 1, zz)) {
				world.setBlock(xx, yy, zz, ModBlocks.weepingBlue, 0, 2);
				world.setBlock(xx, yy + 1, zz, ModBlocks.weepingBlue, 8, 2);
			}
		}

		for (attempt = 0; attempt < 10; attempt++) {
			xx = x + offsetXZ();
			yy = 30 + rand.nextInt(90);
			zz = z + offsetXZ();

			if (!world.getBlock(xx, yy, zz).isNormalCube())
				continue;

			for (int hangerY = rand.nextInt(20); hangerY > 0; hangerY--)
				if (world.isAirBlock(xx, yy - hangerY, zz))
					world.setBlock(xx, yy - hangerY, zz, ModBlocks.hanger, 4, 2);
		}

		for (attempt = 0; attempt < 15; attempt++) {
			xx = x + offsetXZ();
			yy = 30 + rand.nextInt(80);
			zz = z + offsetXZ();

			if (world.isAirBlock(xx, yy, zz))
				genMossPatch.generate(world, rand, xx, yy, zz);
		}

		for (attempt = 0; attempt < 15; attempt++) {
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();

			if (world.getBlock(xx, yy - 1, zz) == Blocks.grass && world.isAirBlock(xx, yy, zz) && world.isAirBlock(xx, yy + 1, zz)) {
				world.setBlock(xx, yy, zz, ModBlocks.waterFlower, 0, 2);
				world.setBlock(xx, yy + 1, zz, ModBlocks.waterFlower, 8, 2);
			}
		}
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	protected void modifyOreGen(OreSettings oreGen, OreType oreType, boolean extraOres) {
		switch (oreType) {
			case COAL:
				oreGen.setIterations(extraOres ? 2 : 3, extraOres ? 3 : 4).setY(5, 48);
				break; // ~2.5 times smaller area, thus less iterations
			case IRON:
				oreGen.setChance(0.75F).setIterations(extraOres ? 2 : 3, extraOres ? 4 : 5).setY(5, 42);
				break; // ~3 times smaller area, thus lower chance and
			// iterations
			case GOLD:
				oreGen.setIterations(extraOres ? 2 : 3);
				break; // 2 veins less
			case EMERALD:
				oreGen.setIterations(2, 4).setCheckArea(2);
				break; // 2 veins more
			case JADE:
				oreGen.setIterations(2, 5);
				break; // 1 vein more
			case PETRIFIED_WOOD:
				oreGen.setChance(0F);
				break;
			case FOSSIL:
				oreGen.setChance(0.25F).setOreAmount(5, 8);
				break; // double chance, lower amount per vein
		}
	}

	@Override
	public void generateFeature(FeatureType featureType) {
		if (featureType == FeatureType.REDGEM)
			for (attempt = 0; attempt < 2 + rand.nextInt(2); attempt++)
				genRedGem.generate(world, rand, x + offsetXZ(), 64 + rand.nextInt(60), z + offsetXZ());
		else
			super.generateFeature(featureType);
	}

	/*
	 * SUB-BIOME - ELYSIAN FOREST
	 */

	public static class BiomeDecoratorElysianForest extends BiomeDecoratorElysianFields {
		private final WorldGenerator genOakTree = new WorldGenTrees(false, 5, 0, 0, false);
		private final WorldGenerator genBirchTree = new WorldGenTrees(false, 5, 2, 2, false);

		public BiomeDecoratorElysianForest() {
			generateFlowers = false;
		}

		@Override
		protected void populate() {
			/*
			 * if (rand.nextInt(4) == 0){ for(attempt = 0; attempt < 45; attempt++){ xx = x + 16; yy = rand.nextInt(120); zz = z + 16; if (checkSurface(SurfaceType.GRASS,xx,yy,zz)){ genPonds.prepare((rand.nextDouble() + 0.7D) * 1.5D); genPonds.generate(world,rand,xx,yy,zz); if (rand.nextBoolean()){ break; } } } }
			 */

			super.populate();
		}

		@Override
		public void decorate() {
			// System.out.println("decorating at "+x+","+z);

			for (attempt = 0; attempt < 400; attempt++) {
				xx = x + offsetXZ();
				zz = z + offsetXZ();
				yy = 20 + rand.nextInt(80);

				if (checkSurface(SurfaceType.GRASS, xx, yy, zz))
					if (rand.nextBoolean())
						genOakTree.generate(world, rand, xx, yy, zz);
					else
						genBirchTree.generate(world, rand, xx, yy, zz);
			}

			for (attempt = 0; attempt < 300; attempt++) {
				xx = x + offsetXZ();
				zz = z + offsetXZ();
				yy = 20 + rand.nextInt(80);

				if (checkSurface(SurfaceType.GRASS, xx, yy, zz))
					genTreeCypress.generate(world, rand, xx, yy, zz);
			}

			super.decorate();
		}
	}
}