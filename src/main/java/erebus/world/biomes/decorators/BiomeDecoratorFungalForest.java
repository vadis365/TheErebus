package erebus.world.biomes.decorators;

import erebus.ModBlocks;
import erebus.core.handler.configs.ConfigHandler;
import erebus.world.biomes.decorators.data.OreSettings;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.feature.plant.WorldGenGiantMushrooms;
import erebus.world.feature.plant.WorldGenGiantMushrooms.MushroomType;
import erebus.world.feature.plant.WorldGenMossPatch;
import erebus.world.feature.plant.WorldGenRottenLogs;
import erebus.world.feature.plant.WorldGenRottenTreeStump;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorFungalForest extends BiomeDecoratorBaseErebus {
	protected final WorldGenerator genMossPatch = new WorldGenMossPatch(0);
	protected final WorldGenerator genLichenPatch = new WorldGenMossPatch(1);
	private final WorldGenFlowers genMushroomsBrown = new WorldGenFlowers(Blocks.brown_mushroom);
	private final WorldGenFlowers genMushroomsRed = new WorldGenFlowers(Blocks.red_mushroom);
	private final WorldGenBigMushroom genBigMushroomRed = new WorldGenBigMushroom(0);
	private final WorldGenBigMushroom genBigMushroomBrown = new WorldGenBigMushroom(1);
	private final WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();

	public static final Block[] mushrooms = { ModBlocks.dutchCap, ModBlocks.kaizerfinger, ModBlocks.bundleshroom, ModBlocks.greenMushroom, ModBlocks.bulbCapped };

	@Override
	public void decorate() {

		for (attempt = 0; attempt < 10; attempt++) {
			int length = rand.nextInt(5) + 4;
			int baseRadius = rand.nextInt(3) + 2;
			byte direction = (byte) rand.nextInt(2);
			xx = x + 16;
			yy = rand.nextInt(118);
			zz = z + 16;

			if (checkSurface(SurfaceType.GRASS, xx, yy, zz))
				new WorldGenRottenLogs(length, baseRadius, direction).generate(world, rand, xx, yy, zz);
		}

		for (attempt = 0; attempt < 10; attempt++) {
			int height = rand.nextInt(11) + 5;
			int baseRadius = rand.nextInt(4) + 3;
			xx = x + offsetXZ();
			yy = rand.nextInt(128);
			zz = z + offsetXZ();
			if (checkSurface(SurfaceType.GRASS, xx, yy, zz))
				new WorldGenRottenTreeStump(height, baseRadius).generate(world, rand, xx, yy, zz);
		}

		for (attempt = 0; attempt < 256; attempt++) {
			xx = x + offsetXZ();
			yy = rand.nextInt(128);
			zz = z + offsetXZ();

			if (checkSurface(SurfaceType.GRASS, xx, yy, zz))
				world.setBlock(xx, yy, zz, mushrooms[rand.nextInt(mushrooms.length)], 0, 2);
		}

		for (attempt = 0; attempt < 200; attempt++) {
			int r = rand.nextInt(100);
			if (r < 16)
				genGiantMushrooms.setMushroomType(MushroomType.DUTCH_CAP);
			else if (r < 80)
				genGiantMushrooms.setMushroomType(MushroomType.KAIZERS_FINGERS);
			else if (r < 96)
				genGiantMushrooms.setMushroomType(MushroomType.GRANDMAS_SHOES);
			else
				genGiantMushrooms.setMushroomType(MushroomType.BULB_CAPPED);

			xx = x + offsetXZ();
			yy = 25 + rand.nextInt(50 + rand.nextInt(40));
			zz = z + offsetXZ();

			for (int yAttempt = 0; yAttempt < 10; yAttempt++)
				if (checkSurface(SurfaceType.GRASS, xx, --yy, zz))
					break;

			if (checkSurface(SurfaceType.GRASS, xx, yy, zz) && genGiantMushrooms.generate(world, rand, xx, yy, zz) && rand.nextInt(5) == 0)
				break;
		}

		for (attempt = 0; attempt < 100; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();

			for (yy = 20; yy < 100; yy += rand.nextBoolean() ? 2 : 1)
				if (checkSurface(SurfaceType.MIXED, xx, yy, zz)) {
					if (rand.nextInt(8) == 0 && world.isAirBlock(xx, yy + 1, zz)) {
						world.setBlock(xx, yy, zz, Blocks.double_plant, 3, 2);
						world.setBlock(xx, yy + 1, zz, Blocks.double_plant, 10, 2);
					} else
						world.setBlock(xx, yy, zz, ModBlocks.fern, 0, 2);

					break;
				}
		}

		for (attempt = 0; attempt < 20; attempt++) {
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();

			if (checkSurface(SurfaceType.GRASS, xx, yy, zz) && world.isAirBlock(xx, yy + 1, zz)) {
				world.setBlock(xx, yy, zz, ModBlocks.tangledStalk, 0, 2);
				world.setBlock(xx, yy + 1, zz, ModBlocks.tangledStalk, 8, 2);
			}
		}

		for (attempt = 0; attempt < 20; attempt++) {
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();

			if (checkSurface(SurfaceType.GRASS, xx, yy, zz) && world.isAirBlock(xx, yy + 1, zz)) {
				world.setBlock(xx, yy, zz, ModBlocks.highCapped, 0, 2);
				world.setBlock(xx, yy + 1, zz, ModBlocks.highCapped, 8, 2);
			}
		}

		for (attempt = 0; attempt < 10; attempt++) {
			xx = x + offsetXZ();
			yy = 30 + rand.nextInt(80);
			zz = z + offsetXZ();

			if (world.isAirBlock(xx, yy, zz))
				genMossPatch.generate(world, rand, xx, yy, zz);
		}

		for (attempt = 0; attempt < 10; attempt++) {
			xx = x + offsetXZ();
			yy = 30 + rand.nextInt(80);
			zz = z + offsetXZ();

			if (world.isAirBlock(xx, yy, zz))
				genLichenPatch.generate(world, rand, xx, yy, zz);
		}

		genMushroomsBrown.generate(world, rand, x + offsetXZ(), rand.nextInt(128), z + offsetXZ());
		genMushroomsRed.generate(world, rand, x + offsetXZ(), rand.nextInt(128), z + offsetXZ());

		for (attempt = 0; attempt < 100; attempt++) {
			xx = x + offsetXZ();
			yy = 15 + rand.nextInt(90);
			zz = z + offsetXZ();

			if (checkSurface(SurfaceType.GRASS, xx, yy, zz))
				genBigMushroomRed.generate(world, rand, xx, yy, zz);
		}

		for (attempt = 0; attempt < 100; attempt++) {
			xx = x + offsetXZ();
			yy = 15 + rand.nextInt(90);
			zz = z + offsetXZ();

			if (checkSurface(SurfaceType.GRASS, xx, yy, zz))
				genBigMushroomBrown.generate(world, rand, xx, yy, zz);
		}

		// TODO OK this may need moving to it's own class to make it generate looking nice
		if (ConfigHandler.INSTANCE.glowshrooms) {
			for (attempt = 0; attempt < 10; attempt++) {
				xx = x + offsetXZ();
				yy = 30 + rand.nextInt(90);
				zz = z + offsetXZ();

				if (world.getBlock(xx, yy, zz) == ModBlocks.umberstone && world.isAirBlock(xx, yy - 1, zz))
					world.setBlock(xx, yy - 1, zz, ModBlocks.glowshroomStalkMain, 4, 2);
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
				break; // ~3 times smaller area, thus lower chance and iterations
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
}