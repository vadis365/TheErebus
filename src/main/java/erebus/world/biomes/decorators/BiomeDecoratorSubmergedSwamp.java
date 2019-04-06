package erebus.world.biomes.decorators;

import erebus.ModBlocks;
import erebus.blocks.BlockDoubleHeightPlant;
import erebus.blocks.BlockLogErebus;
import erebus.blocks.BlockSmallPlant;
import erebus.blocks.EnumWood;
import erebus.core.handler.configs.ConfigHandler;
import erebus.world.ChunkProviderErebus;
import erebus.world.biomes.decorators.data.FeatureType;
import erebus.world.biomes.decorators.data.OreSettings;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.feature.decoration.WorldGenGasVents;
import erebus.world.feature.decoration.WorldGenPonds;
import erebus.world.feature.decoration.WorldGenQuickSand;
import erebus.world.feature.decoration.WorldGenRottenAcacia;
import erebus.world.feature.plant.WorldGenAlgae;
import erebus.world.feature.plant.WorldGenBigLogs;
import erebus.world.feature.plant.WorldGenMossPatch;
import erebus.world.feature.plant.WorldGenSwampBush;
import erebus.world.feature.plant.WorldGenVinesErebus;
import erebus.world.feature.structure.WorldGenDragonflyDungeon;
import erebus.world.feature.tree.WorldGenMarshwoodTree;
import erebus.world.feature.tree.WorldGenMossbarkTree;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockVine;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenerator;


public class BiomeDecoratorSubmergedSwamp extends BiomeDecoratorBaseErebus {
	private final WorldGenDragonflyDungeon genGiantLilyPad = new WorldGenDragonflyDungeon();
	private final WorldGenerator genTreeMarshwood = new WorldGenMarshwoodTree();
	private final WorldGenerator genTreeMossbark = new WorldGenMossbarkTree();
	protected final WorldGenSwampBush genSwampBush = new WorldGenSwampBush();
	private final WorldGenVinesErebus genVines = new WorldGenVinesErebus(35, 5);
	private final WorldGenPonds genPonds = new WorldGenPonds();
	private final WorldGenQuickSand genQuickSand = new WorldGenQuickSand();
	protected final WorldGenerator genMossPatch = new WorldGenMossPatch(0);
	private final WorldGenRottenAcacia genRottenAcacia = new WorldGenRottenAcacia();
	protected final WorldGenGasVents genGasVent = new WorldGenGasVents();
	private final WorldGenAlgae genAlgae = new WorldGenAlgae();

	private final WorldGenReed genReed = new WorldGenReed();
	private static final int[] offsetX = new int[] { -1, 1, 0, 0 };
	private static final int[] offsetZ = new int[] { 0, 0, -1, 1 };

	@Override
	protected void populate() {
		for (attempt = 0; attempt < 800; attempt++) {
			xx = x + 16;
			yy = rand.nextInt(120);
			zz = z + 16;
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.MIXED, pos)) {
				genPonds.prepare((rand.nextDouble() + 0.7D) * 1.5D);
				genPonds.generate(world, rand, pos.up());
			}
		}
	}

	@Override
	public void decorate() {

		for (int attempt = 0; attempt < 15; attempt++)
			if (genGiantLilyPad.generate(world, rand, new BlockPos(x + offsetXZ(), ChunkProviderErebus.swampWaterHeight, z + offsetXZ())))
				break;
		
		// Water
		for (int attempt = 0; attempt < 5; attempt++) {
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			genAlgae.generate(world, rand, pos);
		}

		for (attempt = 0; attempt < 2; attempt++) {
			xx = x + offsetXZ();
			yy = ChunkProviderErebus.swampWaterHeight;
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			genReed.generate(world, rand, pos);
		}

		for (attempt = 0; attempt < 30; attempt++) {
			xx = x + offsetXZ();
			yy = ChunkProviderErebus.swampWaterHeight - 4;
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			genVines.generate(world, rand, pos);
		}

		for (attempt = 0; attempt < 10; attempt++) {
			xx = x + offsetXZ();
			yy = ChunkProviderErebus.swampWaterHeight + rand.nextInt(36 - ChunkProviderErebus.swampWaterHeight);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (world.isAirBlock(pos))
				genMossPatch.generate(world, rand, pos);
		}

		// Ground
		for (attempt = 0; attempt < 240; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();

			for (yy = 20; yy < 100; yy += rand.nextInt(2) + 1) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (world.getBlockState(pos) == Blocks.GRASS.getDefaultState() && world.isAirBlock(pos.up())) {
					if (rand.nextInt(3) == 0)
						world.setBlockState(pos, ModBlocks.MUD.getDefaultState(), 2);
					break;
				}
			}
		}

		for (attempt = 0; attempt < 600; attempt++) {
			xx = x + rand.nextInt(5) + 12;
			yy = 15 + rand.nextInt(90);
			zz = z + rand.nextInt(5) + 12;
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos) && checkSurface(SurfaceType.GRASS, pos.east(2)) && checkSurface(SurfaceType.GRASS, pos.west(2)) && checkSurface(SurfaceType.GRASS, pos.north(2)) && checkSurface(SurfaceType.GRASS, pos.south(2)))
				genTreeMarshwood.generate(world, rand, pos.up());
		}
		
		if (rand.nextBoolean())
			for (attempt = 0; attempt < 50; attempt++) {
				xx = x + offsetXZ();
				yy = 20 + rand.nextInt(80);
				zz = z + offsetXZ();
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos)) {
					genTreeMossbark.generate(world, rand, pos.up());
					break;
				}
			}

		for (attempt = 0; attempt < 10; attempt++) {
			xx = x + offsetXZ();
			yy = 30 + rand.nextInt(80);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (world.isAirBlock(pos))
				genMossPatch.generate(world, rand, pos);
		}

		if (ConfigHandler.INSTANCE.generateVents) {
			if (rand.nextBoolean())
				for (attempt = 0; attempt < rand.nextInt(4); attempt++) {
					xx = x + offsetXZ();
					yy = 25 + rand.nextInt(75);
					zz = z + offsetXZ();

					for (; yy > 20; yy--) {
						BlockPos pos = new BlockPos(xx, yy, zz);
						if (checkSurface(SurfaceType.GRASS, pos)) {
							genGasVent.generate(world, rand, pos);
							break;
						}
					}
				}
		}

		if (rand.nextInt(10) == 0)
			for (attempt = 0; attempt < rand.nextInt(4); attempt++) {
				xx = x + offsetXZ();
				yy = 25 + rand.nextInt(75);
				zz = z + offsetXZ();
				for (; yy > 20; yy--) {
					BlockPos pos = new BlockPos(xx, yy, zz);
					if (checkSurface(SurfaceType.GRASS, pos)) {
						genSwampBush.generate(world, rand, pos.up());
						break;
					} else if (checkSurface(SurfaceType.UMBERSTONE, pos) && world.isAirBlock(pos.up())) {
						genSwampBush.generate(world, rand, pos.up());
						break;
					}
				}
			}

		for (attempt = 0; attempt < 8; attempt++) {
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos) && world.isAirBlock(pos.up()) && world.isAirBlock(pos.up(2))) {
				ModBlocks.DOUBLE_PLANT.placeAt(world, pos.up(), BlockDoubleHeightPlant.EnumPlantType.SUNDEW, 2);
			}
		}

		for (attempt = 0; attempt < rand.nextInt(3); attempt++) {
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(25) * (1 + rand.nextInt(3)); // more likely in lower levels
			zz = z + offsetXZ();

			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.MIXED, pos))
				genRottenAcacia.generate(world, rand, pos.up());
		}

		int offset;
		for (attempt = 0; attempt < 800; attempt++) {
			xx = x + offsetXZ();
			yy = 30 + rand.nextInt(80);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (world.isAirBlock(pos)) {
				offset = rand.nextInt(4);

				if (!world.getBlockState(new BlockPos(xx + offsetX[offset], yy, zz + offsetZ[offset])).isNormalCube())
					continue;

				for (int vineY = rand.nextInt(30); vineY > 0; vineY--)
					if (world.isAirBlock(new BlockPos(xx + offsetX[offset], yy - vineY, zz + offsetZ[offset]))) {
						if (offset == 3)
							world.setBlockState(new BlockPos(xx + offsetX[offset], yy - vineY, zz + offsetZ[offset]), Blocks.VINE.getDefaultState().withProperty(BlockVine.SOUTH, true), 3);
						if (offset == 2)
							world.setBlockState(new BlockPos(xx + offsetX[offset], yy - vineY, zz + offsetZ[offset]), Blocks.VINE.getDefaultState().withProperty(BlockVine.NORTH, true), 3);
						if (offset == 1)
							world.setBlockState(new BlockPos(xx + offsetX[offset], yy - vineY, zz + offsetZ[offset]), Blocks.VINE.getDefaultState().withProperty(BlockVine.EAST, true), 3);
						if (offset == 0)
							world.setBlockState(new BlockPos(xx + offsetX[offset], yy - vineY, zz + offsetZ[offset]), Blocks.VINE.getDefaultState().withProperty(BlockVine.WEST, true), 3);
					}
			}
		}

		for (attempt = 0; attempt < 20; attempt++) {
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

		for (attempt = 0; attempt < 16; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();
			for (yy = 20; yy < 100; yy += rand.nextBoolean() ? 2 : 1) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos))
					if (world.isAirBlock(pos.up())) {
						world.setBlockState(pos.up(), ModBlocks.SMALL_PLANT.getDefaultState().withProperty(BlockSmallPlant.PLANT_TYPE, BlockSmallPlant.EnumSmallPlantType.FIDDLE_HEAD), 2);
						break;
					}
			}
		}

		for (attempt = 0; attempt < 40; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();
			for (yy = 20; yy < 100; yy += rand.nextBoolean() ? 2 : 1) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos))
					if (world.isAirBlock(pos.up())) {
						world.setBlockState(pos.up(), ModBlocks.SMALL_PLANT.getDefaultState().withProperty(BlockSmallPlant.PLANT_TYPE, BlockSmallPlant.EnumSmallPlantType.SWAMP_PLANT), 2);
						break;
					}
			}
		}

		for (attempt = 0; attempt < 10; attempt++) {
			xx = x + offsetXZ();
			yy = rand.nextInt(120);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);

			if (checkSurface(SurfaceType.GRASS, pos))
				genQuickSand.generate(world, rand, pos);
		}

		for (attempt = 0; attempt < 40; attempt++) {
			int length = rand.nextInt(5) + 4;
			int baseRadius = rand.nextInt(3) + 2;
			EnumFacing facing = rand.nextBoolean() ? EnumFacing.NORTH : EnumFacing.WEST;
			xx = x + 16;
			yy = rand.nextInt(118);
			zz = z + 16;
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos))
				new WorldGenBigLogs(length, baseRadius, facing, EnumWood.ROTTEN.getLog().getDefaultState().withProperty(BlockLogErebus.LOG_AXIS, facing == EnumFacing.NORTH ? BlockLogErebus.EnumAxis.Z : BlockLogErebus.EnumAxis.X)).generate(world, rand, pos.up());
		}
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	protected void modifyOreGen(OreSettings oreGen, OreType oreType, boolean extraOres) {
		switch (oreType) {
			case COAL:
				oreGen.setChance(0.85F).setIterations(extraOres ? 2 : 3).setOreAmount(7, 10).setY(5, 56);
				break; // less common, lowered amount too, also ~2 times smaller area
			case EMERALD:
				oreGen.setIterations(1, 3);
				break; // one more vein
			case DIAMOND:
				oreGen.setIterations(3, 4);
				break; // one more vein
			case PETRIFIED_WOOD:
				oreGen.setIterations(extraOres ? 1 : 2, extraOres ? 2 : 3).setY(20, 64).setCheckArea(3);
				break; // more common, but ~1.5 times smaller area
			case FOSSIL:
				oreGen.setChance(0.25F);
				break; // more rare
		}
	}

	@Override
	public void generateFeature(FeatureType featureType) {
		if (featureType == FeatureType.RED_GEM)
			for (attempt = 0; attempt < 8; attempt++)
				genRedGem.generate(world, rand, new BlockPos(x + offsetXZ(), 64 + rand.nextInt(60), z + offsetXZ()));
		else
			super.generateFeature(featureType);
	}
}