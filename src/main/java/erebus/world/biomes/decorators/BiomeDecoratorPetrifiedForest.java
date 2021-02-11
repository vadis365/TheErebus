package erebus.world.biomes.decorators;

import erebus.ModBlocks;
import erebus.blocks.BlockDustLayer;
import erebus.blocks.BlockHangingWeb;
import erebus.blocks.BlockMandrake;
import erebus.blocks.BlockPetrifiedWoodRock;
import erebus.core.handler.configs.ConfigHandler;
import erebus.world.biomes.decorators.data.FeatureType;
import erebus.world.biomes.decorators.data.OreSettings;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.feature.decoration.WorldGenPetrifiedTrees;
import erebus.world.feature.decoration.WorldGenRockSpike;
import erebus.world.feature.decoration.WorldGenScorchedWood;
import erebus.world.feature.plant.WorldGenBigLogs;
import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenLakes;


public class BiomeDecoratorPetrifiedForest extends BiomeDecoratorBaseErebus {

	private final WorldGenScorchedWood genScorchedWood = new WorldGenScorchedWood();
	private final WorldGenLakes genLavaLakes = new WorldGenLakes(ModBlocks.FORMIC_ACID);
	private final WorldGenRockSpike rockSpike = new WorldGenRockSpike();
	private static final int[] offsetX = new int[] { -1, 1, 0, 0 };
	private static final int[] offsetZ = new int[] { 0, 0, -1, 1 };

	@Override
	public void populate() {
		for (attempt = 0; attempt < 35; attempt++) {
			xx = x + offsetXZ();
			yy = 15 + rand.nextInt(90);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.VOLCANIC_ROCK, pos))
				genLavaLakes.generate(world, world.rand, pos.up());
		}
	}

	@Override
	public void decorate() {
		// If petrified quartz generation is enabled, the filler is petrified quartz. Else, it is wood rock.
		Block treeFiller = ModBlocks.ORE_PETRIFIED_QUARTZ;
		if (!ConfigHandler.INSTANCE.genPetrifiedQuartz) {
			treeFiller = ModBlocks.PETRIFIED_WOOD_ROCK;
		}

		for (attempt = 0; attempt < 240; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();

			for (yy = 20; yy < 100; yy += rand.nextInt(2) + 1) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (world.getBlockState(pos) == ModBlocks.VOLCANIC_ROCK.getDefaultState() && world.isAirBlock(pos.up())) {
					if (rand.nextInt(3) == 0)
						world.setBlockState(pos, ModBlocks.UMBERGRAVEL.getDefaultState(), 2);
					if (rand.nextInt(3) == 0)
						world.setBlockState(pos, ModBlocks.DUST.getDefaultState(), 2);
					break;
				}
			}
		}

		for (attempt = 0; attempt < 3; attempt++) {
			xx = x + offsetXZ();
			yy = rand.nextInt(100);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			rockSpike.generate(world, rand, pos.up());
		}

		for (attempt = 0; attempt < 5; attempt++) {
			int length = rand.nextInt(5) + 4;
			int baseRadius = rand.nextInt(3) + 2;
			EnumFacing facing = rand.nextBoolean() ? EnumFacing.NORTH : EnumFacing.WEST;
			xx = x + 16;
			yy = rand.nextInt(118);
			zz = z + 16;
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.VOLCANIC_ROCK, pos)) {
				new WorldGenBigLogs(length, baseRadius, facing, ModBlocks.PETRIFIED_BARK_BROWN.getDefaultState().withProperty(BlockPetrifiedWoodRock.AXIS, facing == EnumFacing.NORTH ? EnumFacing.Axis.Z : EnumFacing.Axis.X), ModBlocks.PETRIFIED_LOG_INNER.getDefaultState(), true, treeFiller.getDefaultState()).generate(world, rand, pos.up());
				break;
			}
		}

		for (attempt = 0; attempt < 5; attempt++) {
			int length = rand.nextInt(5) + 4;
			int baseRadius = rand.nextInt(3) + 2;
			EnumFacing facing = rand.nextBoolean() ? EnumFacing.NORTH : EnumFacing.WEST;
			xx = x + 16;
			yy = rand.nextInt(118);
			zz = z + 16;
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.VOLCANIC_ROCK, pos)) {
				new WorldGenBigLogs(length, baseRadius, facing, ModBlocks.PETRIFIED_BARK_RED.getDefaultState().withProperty(BlockPetrifiedWoodRock.AXIS, facing == EnumFacing.NORTH ? EnumFacing.Axis.Z : EnumFacing.Axis.X), ModBlocks.PETRIFIED_LOG_INNER.getDefaultState(), true, treeFiller.getDefaultState()).generate(world, rand, pos.up());
				break;
			}
		}

		for (attempt = 0; attempt < 3; attempt++) {
			int length = rand.nextInt(5) + 4;
			int baseRadius = rand.nextInt(3) + 2;
			EnumFacing facing = rand.nextBoolean() ? EnumFacing.NORTH : EnumFacing.WEST;
			xx = x + 16;
			yy = rand.nextInt(118);
			zz = z + 16;
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.VOLCANIC_ROCK, pos)) {
				new WorldGenBigLogs(length, baseRadius, facing, ModBlocks.PETRIFIED_BARK_BROWN.getDefaultState().withProperty(BlockPetrifiedWoodRock.AXIS, facing == EnumFacing.NORTH ? EnumFacing.Axis.Z : EnumFacing.Axis.X)).generate(world, rand, pos.up());
				break;
			}
		}

		for (attempt = 0; attempt < 3; attempt++) {
			int length = rand.nextInt(5) + 4;
			int baseRadius = rand.nextInt(3) + 2;
			EnumFacing facing = rand.nextBoolean() ? EnumFacing.NORTH : EnumFacing.WEST;
			xx = x + 16;
			yy = rand.nextInt(118);
			zz = z + 16;
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.VOLCANIC_ROCK, pos)) {
				new WorldGenBigLogs(length, baseRadius, facing, ModBlocks.PETRIFIED_BARK_RED.getDefaultState().withProperty(BlockPetrifiedWoodRock.AXIS, facing == EnumFacing.NORTH ? EnumFacing.Axis.Z : EnumFacing.Axis.X)).generate(world, rand, pos.up());
				break;
			}
		}

		for (attempt = 0; attempt < 5; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();
			for (yy = 20; yy < 100; yy += rand.nextBoolean() ? 2 : 1) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.DUST, pos))
					if (world.isAirBlock(pos.up())) {
						world.setBlockState(pos.up(), ModBlocks.CROP_MANDRAKE.getDefaultState().withProperty(BlockMandrake.AGE, Integer.valueOf(rand.nextInt(8))), 2);
						break;
					}
			}
		}

		for (attempt = 0; attempt < 22; attempt++) {
			xx = x + offsetXZ();
			yy = rand.nextInt(120);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.VOLCANIC_ROCK, pos) && !world.isAirBlock(pos.down(2))) {
				genScorchedWood.generate(world, rand, pos);
				if (rand.nextInt(4) != 0)
					break;
			}
		}

		for (attempt = 0; attempt < 30; attempt++) {
			xx = x + offsetXZ();
			yy = rand.nextInt(120);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if ((checkSurface(SurfaceType.DUST, pos) || checkSurface(SurfaceType.VOLCANIC_ROCK, pos))  && !world.isAirBlock(pos.down(2))) {
				new WorldGenPetrifiedTrees(6 + rand.nextInt(5), 1, ModBlocks.PETRIFIED_BARK_BROWN.getDefaultState().withProperty(BlockPetrifiedWoodRock.AXIS, EnumFacing.Axis.Y)).generate(world, rand, pos.up());
				break;
			}
		}

		for (attempt = 0; attempt < 5; attempt++) {
			xx = x + offsetXZ();
			yy = rand.nextInt(120);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.VOLCANIC_ROCK, pos) && !world.isAirBlock(pos.down(2))) {
				new WorldGenPetrifiedTrees(11 + rand.nextInt(4), 2, ModBlocks.PETRIFIED_BARK_BROWN.getDefaultState().withProperty(BlockPetrifiedWoodRock.AXIS, EnumFacing.Axis.Y), ModBlocks.PETRIFIED_LOG_INNER.getDefaultState(), true, treeFiller.getDefaultState()).generate(world, rand, pos.up());
				break;
			}
		}

		for (attempt = 0; attempt < 5; attempt++) {
			xx = x + offsetXZ();
			yy = rand.nextInt(120);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.VOLCANIC_ROCK, pos) && !world.isAirBlock(pos.down(2))) {
				new WorldGenPetrifiedTrees(16 + rand.nextInt(10), 3, ModBlocks.PETRIFIED_BARK_BROWN.getDefaultState().withProperty(BlockPetrifiedWoodRock.AXIS, EnumFacing.Axis.Y), ModBlocks.PETRIFIED_LOG_INNER.getDefaultState(), true, treeFiller.getDefaultState()).generate(world, rand, pos.up());
				break;
			}
		}

		for (attempt = 0; attempt < 30; attempt++) {
			xx = x + offsetXZ();
			yy = rand.nextInt(120);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if ((checkSurface(SurfaceType.DUST, pos) || checkSurface(SurfaceType.VOLCANIC_ROCK, pos))  && !world.isAirBlock(pos.down(2))) {
				new WorldGenPetrifiedTrees(6 + rand.nextInt(5), 1, ModBlocks.PETRIFIED_BARK_RED.getDefaultState().withProperty(BlockPetrifiedWoodRock.AXIS, EnumFacing.Axis.Y)).generate(world, rand, pos.up());
				break;
			}
		}

		for (attempt = 0; attempt < 5; attempt++) {
			xx = x + offsetXZ();
			yy = rand.nextInt(120);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.VOLCANIC_ROCK, pos) && !world.isAirBlock(pos.down(2))) {
				new WorldGenPetrifiedTrees(11 + rand.nextInt(4), 2, ModBlocks.PETRIFIED_BARK_RED.getDefaultState().withProperty(BlockPetrifiedWoodRock.AXIS, EnumFacing.Axis.Y), ModBlocks.PETRIFIED_LOG_INNER.getDefaultState(), true, treeFiller.getDefaultState()).generate(world, rand, pos.up());
				break;
			}
		}

		for (attempt = 0; attempt < 5; attempt++) {
			xx = x + offsetXZ();
			yy = rand.nextInt(120);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.VOLCANIC_ROCK, pos) && !world.isAirBlock(pos.down(2))) {
				new WorldGenPetrifiedTrees(16 + rand.nextInt(10), 3, ModBlocks.PETRIFIED_BARK_RED.getDefaultState().withProperty(BlockPetrifiedWoodRock.AXIS, EnumFacing.Axis.Y), ModBlocks.PETRIFIED_LOG_INNER.getDefaultState(), true, treeFiller.getDefaultState()).generate(world, rand, pos.up());
				break;
			}
		}

		for (attempt = 0; attempt < 240; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();

			for (yy = 20; yy < 100; yy += rand.nextInt(2) + 1) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if ((world.getBlockState(pos) == ModBlocks.VOLCANIC_ROCK.getDefaultState() || world.getBlockState(pos).getBlock() instanceof BlockPetrifiedWoodRock)  && world.isAirBlock(pos.up())) {
					world.setBlockState(pos.up(), ModBlocks.DUST_LAYER.getDefaultState().withProperty(BlockDustLayer.LAYERS, rand.nextInt(3) + 1), 2);
					break;
				}
			}
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
							world.setBlockState(new BlockPos(xx + offsetX[offset], yy - vineY, zz + offsetZ[offset]), ModBlocks.HANGING_WEB.getDefaultState().withProperty(BlockHangingWeb.SOUTH, true), 2);
						if (offset == 2)
							world.setBlockState(new BlockPos(xx + offsetX[offset], yy - vineY, zz + offsetZ[offset]), ModBlocks.HANGING_WEB.getDefaultState().withProperty(BlockHangingWeb.NORTH, true), 2);
						if (offset == 1)
							world.setBlockState(new BlockPos(xx + offsetX[offset], yy - vineY, zz + offsetZ[offset]), ModBlocks.HANGING_WEB.getDefaultState().withProperty(BlockHangingWeb.EAST, true), 2);
						if (offset == 0)
							world.setBlockState(new BlockPos(xx + offsetX[offset], yy - vineY, zz + offsetZ[offset]), ModBlocks.HANGING_WEB.getDefaultState().withProperty(BlockHangingWeb.WEST, true), 2);
					}
			}
		}

	}

	@Override
	public void generateFeature(FeatureType featureType) {
		if (featureType == FeatureType.RED_GEM)
			for (attempt = 0; attempt < 10; attempt++)
				genRedGem.generate(world, rand, new BlockPos(x + offsetXZ(), rand.nextInt(64), z + offsetXZ()));
		else
			super.generateFeature(featureType);
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	protected void modifyOreGen(OreSettings oreGen, OreType oreType, boolean extraOres) {
		switch (oreType) {
			case COAL:
				oreGen.setChance(0F);
				break;
			case GOLD:
				oreGen.setIterations(extraOres ? 1 : 2, extraOres ? 2 : 3);
				break; // less common
			case DIAMOND:
				oreGen.setType(OreType.DIAMOND_ENCRUSTED).setChance(0.4F).setIterations(1, 2).setOreAmount(2).setY(5, 16);
				break; // clusters of 4, ~7 times smaller area thus lowered chance and iterations
			case JADE:
				oreGen.setIterations(0, 2);
				break; // less common
			case FOSSIL:
				oreGen.setChance(0.25F).setIterations(0, 1);
				break; // much more rare
		}
	}
}