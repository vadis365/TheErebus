package erebus.world.biomes.decorators;

import erebus.ModBlocks;
import erebus.blocks.BlockSmallPlant;
import erebus.world.biomes.decorators.data.OreSettings;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.feature.plant.WorldGenGiantMushrooms;
import erebus.world.feature.plant.WorldGenRottenLogs;
import erebus.world.feature.plant.WorldGenGiantMushrooms.MushroomType;
import erebus.world.feature.plant.WorldGenRottenTreeStump;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenBush;


public class BiomeDecoratorFungalForest extends BiomeDecoratorBaseErebus {

	//protected final WorldGenerator genMossPatch = new WorldGenMossPatch(0);
	//protected final WorldGenerator genLichenPatch = new WorldGenMossPatch(1);
	private final WorldGenBush genMushroomsBrown = new WorldGenBush(Blocks.BROWN_MUSHROOM);
	private final WorldGenBush genMushroomsRed = new WorldGenBush(Blocks.RED_MUSHROOM);
	private final WorldGenBigMushroom genBigMushroomRed = new WorldGenBigMushroom(Blocks.BROWN_MUSHROOM_BLOCK);
	private final WorldGenBigMushroom genBigMushroomBrown = new WorldGenBigMushroom(Blocks.RED_MUSHROOM_BLOCK);
	private final WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();

	public static final Block[] MUSHROOMS = { ModBlocks.DUTCH_CAP_MUSHROOM, ModBlocks.KAIZERS_FINGERS_MUSHROOM, ModBlocks.SARCASTIC_CZECH_MUSHROOM, ModBlocks.GRANDMAS_SHOES_MUSHROOM, ModBlocks.DARK_CAPPED_MUSHROOM };

	@Override
	public void decorate() {

		for (attempt = 0; attempt < 10; attempt++) {
			int length = rand.nextInt(5) + 4;
			int baseRadius = rand.nextInt(3) + 2;
			byte direction = (byte) rand.nextInt(2);
			xx = x + 16;
			yy = rand.nextInt(118);
			zz = z + 16;
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos))
				new WorldGenRottenLogs(length, baseRadius, direction).generate(world, rand, pos.up());
		}

		for (attempt = 0; attempt < 10; attempt++) {
			int height = rand.nextInt(11) + 6;
			int baseRadius = rand.nextInt(4) + 3;
			xx = x + offsetXZ();
			yy = rand.nextInt(128);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos))
				new WorldGenRottenTreeStump(height, baseRadius).generate(world, rand, pos.up());
		}

		for (attempt = 0; attempt < 256; attempt++) {
			xx = x + offsetXZ();
			yy = rand.nextInt(128);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos))
				world.setBlockState(pos.up(), MUSHROOMS[rand.nextInt(MUSHROOMS.length)].getDefaultState(), 2);
		}

		for (attempt = 0; attempt < 400; attempt++) {
			int r = rand.nextInt(100);
			if (r < 16)
				genGiantMushrooms.setMushroomType(MushroomType.DUTCH_CAP);
			else if (r < 25)
				genGiantMushrooms.setMushroomType(MushroomType.SARCASTIC_CZECH);
			else if (r < 80)
				genGiantMushrooms.setMushroomType(MushroomType.KAIZERS_FINGERS);
			else if (r < 96)
				genGiantMushrooms.setMushroomType(MushroomType.GRANDMAS_SHOES);
			else
				genGiantMushrooms.setMushroomType(MushroomType.DARK_CAPPED);

			xx = x + offsetXZ();
			yy = 25 + rand.nextInt(50 + rand.nextInt(40));
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			for (int yAttempt = 10; yAttempt > 0; yAttempt--)
				if (checkSurface(SurfaceType.GRASS, pos.down(yAttempt)))
					break;

			if (checkSurface(SurfaceType.GRASS, pos) && genGiantMushrooms.generate(world, rand, pos.up()))
				break;
		}
		
		genMushroomsBrown.generate(world, rand, new BlockPos(x + offsetXZ(), rand.nextInt(128), z + offsetXZ()));
		genMushroomsRed.generate(world, rand, new BlockPos(x + offsetXZ(), rand.nextInt(128), z + offsetXZ()));

		for (attempt = 0; attempt < 40; attempt++) {
			xx = x + offsetXZ();
			yy = 15 + rand.nextInt(90);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos))
				genBigMushroomRed.generate(world, rand, pos.up());
		}

		for (attempt = 0; attempt < 40; attempt++) {
			xx = x + offsetXZ();
			yy = 15 + rand.nextInt(90);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos))
				genBigMushroomBrown.generate(world, rand, pos.up());
		}

		for (attempt = 0; attempt < 100; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();
			
			for (yy = 20; yy < 100; yy += rand.nextBoolean() ? 2 : 1) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos)) {
					if (rand.nextInt(10) == 0 && world.isAirBlock(pos.up(2))) {
						Blocks.DOUBLE_PLANT.placeAt(world, pos.up(), BlockDoublePlant.EnumPlantType.FERN, 2);
					} else
						if (world.isAirBlock(pos.up()))
							world.setBlockState(pos.up(), ModBlocks.SMALL_PLANT.getDefaultState().withProperty(BlockSmallPlant.PLANT_TYPE, BlockSmallPlant.EnumSmallPlantType.FERN), 2);
					break;
				}
			}
		}

		/*
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
		*/
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