package erebus.world.feature.plant;

import java.util.Random;

import erebus.ModBlocks;
import erebus.blocks.BlockGiantFlower;
import erebus.blocks.BlockGiantFlower.EnumType;
import erebus.blocks.BlockStigma;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGiantFlowers extends WorldGenerator {
	private int primaryPetalColor = -1, secondaryPetalColor = -1;
	private static final int[] offsetX = new int[] { -1, 1, 0, 0 };
	private static final int[] offsetZ = new int[] { 0, 0, -1, 1 };

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		StemShape stemShape = StemShape.values()[rand.nextInt(StemShape.values().length)];
		PetalShape petalShape = PetalShape.values()[rand.nextInt(PetalShape.values().length)];
		int stemHeight = rand.nextInt(6) + 2;

		if (primaryPetalColor == -1)
			primaryPetalColor = rand.nextInt(13);
		if (secondaryPetalColor == -1)
			secondaryPetalColor = petalShape.canHaveSecondaryColor && rand.nextInt(8) == 0 ? rand.nextInt(13) : primaryPetalColor;

		// CHECK AIR

		for (int yy = y + 2; yy <= y + stemHeight; yy++)
			if (!world.isAirBlock(new BlockPos(x, yy, z)))
				return false;

		for (int xx = x - stemShape.rad; xx <= x + stemShape.rad; xx++)
			for (int zz = z - stemShape.rad; zz <= z + stemShape.rad; zz++) {
				for (int yy = y; yy <= y + stemShape.height; yy++)
					if (!world.isAirBlock(new BlockPos(xx, yy, zz)))
						return false;

				if (world.isAirBlock(new BlockPos(xx, y - 1, zz)))
					return false;
			}

		for (int xx = x - petalShape.rad; xx <= x + petalShape.rad; xx++)
			for (int zz = z - petalShape.rad; zz <= z + petalShape.rad; zz++)
				for (int yy = y + stemHeight + 1; yy <= y + stemHeight + 1 + petalShape.height; yy++)
					if (!world.isAirBlock(new BlockPos(xx, yy, zz)))
						return false;

		// GENERATE STEM

		switch (stemShape) {
			case SMALL_X:
				for (int a = 0; a < 2; a++)
					for (int b = 0; b < 2; b++)
						world.setBlockState(new BlockPos(x - 1 + a * 2, y, z - 1 + b * 2), ModBlocks.GIANT_FLOWER.getDefaultState(), 2);
				break;

			case SMALL_PLUS:
				for (int a = 0; a < 4; a++)
					world.setBlockState(new BlockPos(x + offsetX[a], y, z + offsetZ[a]), ModBlocks.GIANT_FLOWER.getDefaultState(), 2);
				break;

			case LARGE_PLUS:
				for (int a = 0; a < 4; a++) {
					world.setBlockState(new BlockPos(x + offsetX[a] * 2, y, z + offsetZ[a] * 2), ModBlocks.GIANT_FLOWER.getDefaultState(), 2);
					world.setBlockState(new BlockPos(x + offsetX[a], y + 1, z + offsetZ[a]), ModBlocks.GIANT_FLOWER.getDefaultState(), 2);
				}
				break;
		}

		for (int yy = y; yy <= y + stemHeight; yy++)
			world.setBlockState(new BlockPos(x, yy, z), ModBlocks.GIANT_FLOWER.getDefaultState(), 2);

		// GENERATE PETALS

		y += stemHeight + 1;

		switch (petalShape) {
			case DENSE_HEMISPHERE:

				// LAYER 0 & 1

				world.setBlockState(new BlockPos(x, y, z), ModBlocks.GIANT_FLOWER.getDefaultState(), 2);

				for (int a = 0; a < 4; a++) {
					world.setBlockState(new BlockPos(x + offsetX[a], y, z + offsetZ[a]), ModBlocks.GIANT_FLOWER.getDefaultState(), 2);
					world.setBlockState(new BlockPos(x + offsetX[a] * 2, y + 1, z + offsetZ[a] * 2), ModBlocks.GIANT_FLOWER.getDefaultState(), 2);
					if (rand.nextInt(3) == 0)
						world.setBlockState(new BlockPos(x + offsetX[a] * 3, y + 2, z + offsetZ[a] * 3), ModBlocks.GIANT_FLOWER.getDefaultState(), 2);
				}

				for (int xx = x - 1; xx <= x + 1; xx++)
					for (int zz = z - 1; zz <= z + 1; zz++)
						world.setBlockState(new BlockPos(xx, y + 1, zz), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);

				// LAYER 2

				for (int a = 0; a < 3; a++) {
					for (int b = 0; b < 2; b++) {
						world.setBlockState(new BlockPos(x - 2 + b * 4, y + 2, z - 1 + a), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
						world.setBlockState(new BlockPos(x - 1 + a, y + 2, z - 2 + b * 4), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
					}
					world.setBlockState(new BlockPos(x - 1 + a, y + 2, z), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
				}

				for (int a = 0; a < 2; a++)
					world.setBlockState(new BlockPos(x, y + 2, z - 1 + a * 2), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
				// LAYER 3

				if (rand.nextInt(10) == 0)
					world.setBlockState(new BlockPos(x, y + 3, z), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.EXPLODING_STIGMA), 2);
				else
					world.setBlockState(new BlockPos(x, y + 3, z), ModBlocks.GIANT_FLOWER_STIGMA.getDefaultState().withProperty(BlockStigma.TYPE, BlockStigma.EnumType.values()[petalShape.canHaveSecondaryColor ? 14 :primaryPetalColor]), 3);

				for (int a = 0; a < 3; a++)
					for (int b = 0; b < 2; b++) {
						world.setBlockState(new BlockPos(x - 3 + b * 6, y + 3, z - 1 + a), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
						world.setBlockState(new BlockPos(x - 1 + a, y + 3, z - 3 + b * 6), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
					}

				for (int a = -1; a <= 1; a++)
					for (int b = -1; b <= 1; b++) {
						if (a == 0 && b == 0)
							continue;
						world.setBlockState(new BlockPos(x + a * 2, y + 3, z + b * 2), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
					}

				// LAYER 4

				boolean petalFormation = rand.nextBoolean();

				for (int a = 0; a < 2; a++)
					for (int b = 0; b < 2; b++)
						if (petalFormation) {
							world.setBlockState(new BlockPos(x - 3 + a * 6, y + 4, z - 1 + b * 2), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
							world.setBlockState(new BlockPos(x - 1 + a * 2, y + 4, z - 3 + b * 6), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
						} else {
							world.setBlockState(new BlockPos(x - 2 + a * 4, y + 4, z - 2 + b * 4), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
							world.setBlockState(new BlockPos(x + offsetX[a * 2 + b] * 3, y + 4, z + offsetZ[a * 2 + b] * 3), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
						}

				break;

			case DISPERSE_HEMISPHERE:
				world.setBlockState(new BlockPos(x, y, z), ModBlocks.GIANT_FLOWER.getDefaultState(), 2);

				if (rand.nextInt(10) == 0)
					world.setBlockState(new BlockPos(x, y + 1, z), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.EXPLODING_STIGMA), 2);
				else
					world.setBlockState(new BlockPos(x, y + 1, z), ModBlocks.GIANT_FLOWER_STIGMA.getDefaultState().withProperty(BlockStigma.TYPE, BlockStigma.EnumType.values()[petalShape.canHaveSecondaryColor ? 14 :primaryPetalColor]), 3);

				for (int a = 0; a < 4; a++)
					for (int b = 1; b <= 3; b++)
						world.setBlockState(new BlockPos(x + offsetX[a] * b, y + b - 1, z + offsetZ[a] * b), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[secondaryPetalColor]), 2);

				for (int a = 0; a < 2; a++)
					for (int b = 0; b < 2; b++) {
						world.setBlockState(new BlockPos(x - 1 + a * 2, y + 1, z - 1 + b * 2), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
						world.setBlockState(new BlockPos(x - 2 + a * 4, y + 2, z - 2 + b * 4), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
					}

				break;

			case UMBRELLA:
				world.setBlockState(new BlockPos(x, y, z), ModBlocks.GIANT_FLOWER.getDefaultState(), 2);
				for (int a = 0; a < 4; a++)
					world.setBlockState(new BlockPos(x + offsetX[a], y, z + offsetZ[a]), ModBlocks.GIANT_FLOWER.getDefaultState(), 2);

				world.setBlockState(new BlockPos(x, y + 1, z), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);

				if (rand.nextInt(10) == 0)
					world.setBlockState(new BlockPos(x, y + 2, z), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.EXPLODING_STIGMA), 2);
				else
					world.setBlockState(new BlockPos(x, y + 2, z), ModBlocks.GIANT_FLOWER_STIGMA.getDefaultState().withProperty(BlockStigma.TYPE, BlockStigma.EnumType.values()[petalShape.canHaveSecondaryColor ? 14 :primaryPetalColor]), 3);

				for (int a = 0; a < 3; a++) {
					world.setBlockState(new BlockPos(x - 3 + a, y + 1, z), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
					world.setBlockState(new BlockPos(x + 3 - a, y + 1, z), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
					world.setBlockState(new BlockPos(x, y + 1, z - 3 + a), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
					world.setBlockState(new BlockPos(x, y + 1, z + 3 - a), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
				}

				boolean reversedUmbrella = rand.nextInt(3) == 0;

				for (int a = 0; a < 2; a++)
					for (int b = 0; b < 2; b++) {
						world.setBlockState(new BlockPos(x - 1 + a * 2, y + 1, z - 1 + b * 2), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[secondaryPetalColor]), 2);
						world.setBlockState(new BlockPos(x - 2 + a * 4, y + 1, z - 2 + b * 4), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[secondaryPetalColor]), 2);
						world.setBlockState(new BlockPos(x - 3 + a * 6, y + (reversedUmbrella ? 2 : 0), z - 3 + b * 6), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[secondaryPetalColor]), 2);
						world.setBlockState(new BlockPos(x + offsetX[a * 2 + b] * 4, y + (reversedUmbrella ? 2 : 0), z + offsetZ[a * 2 + b] * 4), ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.values()[primaryPetalColor]), 2);
					}
		}

		primaryPetalColor = secondaryPetalColor = -1;

		return true;
	}

	public void setFlowerColor(int color) {
		setFlowerColor(color, color);
	}

	public void setFlowerColor(int primary, int secondary) {
		primaryPetalColor = primary;
		secondaryPetalColor = secondary;
	}

	private enum StemShape {
		SMALL_X(1, 1),
		SMALL_PLUS(1, 1),
		LARGE_PLUS(2, 2);

		final byte rad, height;

		StemShape(int rad, int height) {
			this.rad = (byte) rad;
			this.height = (byte) height;
		}
	}

	private enum PetalShape {
		DENSE_HEMISPHERE(3, 5, false),
		DISPERSE_HEMISPHERE(3, 3, true),
		UMBRELLA(4, 3, true);

		final byte rad, height;
		final boolean canHaveSecondaryColor;

		PetalShape(int rad, int height, boolean canHaveSecondaryColor) {
			this.rad = (byte) rad;
			this.height = (byte) height;
			this.canHaveSecondaryColor = canHaveSecondaryColor;
		}
	}
}