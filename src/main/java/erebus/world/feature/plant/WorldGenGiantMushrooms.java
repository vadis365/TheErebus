package erebus.world.feature.plant;

import java.util.ArrayList;
import java.util.List;

import erebus.ModBlocks;
import erebus.core.helper.MathUtil;
import erebus.world.feature.WorldGenErebus;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;

public class WorldGenGiantMushrooms extends WorldGenErebus {
	private static final int stalkMeta = 10;
	private static final int bulbFullMeta = 14;
	private static final Block tempBlock = Blocks.bedrock;

	public enum MushroomType {
		BULB_CAPPED(ModBlocks.bigBulbCappedMushroom),
		GRANDMAS_SHOES(ModBlocks.bigGreenMushroom),
		SARCASTIC_CZECH(ModBlocks.bigBundleMushroom),
		KAIZERS_FINGERS(ModBlocks.bigKaiserfingerMushroom),
		DUTCH_CAP(ModBlocks.bigDutchCapMushroom);

		public final Block block;

		MushroomType(Block block) {
			this.block = block;
		}
	}

	private MushroomType mushroomType = MushroomType.BULB_CAPPED;
	private final List<ChunkCoordinates> bulbs = new ArrayList<ChunkCoordinates>();

	public void setMushroomType(MushroomType type) {
		mushroomType = type;
	}

	@Override
	protected boolean generate(int x, int y, int z) {
		bulbs.clear();
		Block mushroom = mushroomType.block;
		boolean res = false;

		switch (mushroomType) {
			case BULB_CAPPED:
				res = genBulbCapped(x, y, z, mushroom);
				break;
			case KAIZERS_FINGERS:
				res = genKaizersFingers(x, y, z, mushroom);
				break;
			case DUTCH_CAP:
				res = genDutchCap(x - 1, y, z, mushroom);
				break;
			case GRANDMAS_SHOES:
				res = genGrandmasShoes(x - 1, y, z, mushroom);
				break;
			default:
				break;
		}

		if (res) {
			generateBulbs(x, z, mushroom);
			return true;
		} else
			return false;
	}

	/*
	 * MUSHROOM TYPE - BULB CAPPED
	 */

	private boolean genBulbCapped(int x, int y, int z, Block mushroom) {
		int stalkHeight = 3 + rand.nextInt(3 + rand.nextInt(2));
		int sideHeight = 1 + rand.nextInt(stalkHeight > 3 ? 3 : 2);

		if (!checkAirCube(x, y, z, x, y + stalkHeight - sideHeight, z) || !checkAirCube(x - 2, y + stalkHeight - sideHeight + 1, z - 2, x + 2, y + stalkHeight + 1, z + 2))
			return false;

		setBlockPillar(x, z, y, y + stalkHeight, mushroom, stalkMeta);
		y += stalkHeight + 1;

		for (int px = -1; px <= 1; px++)
			for (int pz = -1; pz <= 1; pz++)
				bulbs.add(new ChunkCoordinates(x + px, y, z + pz));

		for (int py = 1; py <= sideHeight; py++)
			for (int off = -1; off <= 1; off++) {
				bulbs.add(new ChunkCoordinates(x + 2, y - py, z + off));
				bulbs.add(new ChunkCoordinates(x - 2, y - py, z + off));
				bulbs.add(new ChunkCoordinates(x + off, y - py, z + 2));
				bulbs.add(new ChunkCoordinates(x + off, y - py, z - 2));
			}

		return true;
	}

	/*
	 * MUSHROOM TYPE - KAIZERS FINGERS
	 */

	private boolean genKaizersFingers(int x, int y, int z, Block mushroom) {
		int mainShroomHeight = 4 + rand.nextInt(4);

		if (!checkAirCube(x - 1, y + 3, z - 1, x + 1, y + mainShroomHeight + 1, z + 1) || !checkAirCube(x - 4, y, z - 4, x + 4, y + 2, z + 4) || !checkSolidCube(x - 4, y - 1, z - 4, x + 4, y - 1, z + 4))
			return false;

		for (int py = 0, sidesPlaced = 0; py <= mainShroomHeight; py++) {
			setBlock(x, y + py, z, mushroom, stalkMeta);

			if (py >= 2 && py < mainShroomHeight - 1 && rand.nextInt(4 + sidesPlaced * 2) == 0) {
				int dir = rand.nextInt(4);
				setBlock(x + Direction.offsetX[dir], y + py, z + Direction.offsetZ[dir], mushroom, bulbFullMeta);
				++sidesPlaced;
			}
		}

		bulbs.add(new ChunkCoordinates(x, y + mainShroomHeight + 1, z));
		for (int a = 0; a < 4; a++)
			bulbs.add(new ChunkCoordinates(x + Direction.offsetX[a], y + mainShroomHeight, z + Direction.offsetZ[a]));

		List<ChunkCoordinates> connectList = new ArrayList<ChunkCoordinates>();
		connectList.add(new ChunkCoordinates(x, y - 1, z));

		for (int smallShroomAttempt = 0, xx, zz; smallShroomAttempt < 4 + rand.nextInt(7); smallShroomAttempt++) {
			xx = x + rand.nextInt(4) - rand.nextInt(4);
			zz = z + rand.nextInt(4) - rand.nextInt(4);
			if (!isAir(xx, y, zz) || !isAir(xx - 1, y, zz) || !isAir(xx + 1, y, zz) || !isAir(xx, y, zz - 1) || !isAir(xx, y, zz + 1))
				continue;

			int smallShroomHeight = rand.nextBoolean() ? 1 : 1 + rand.nextInt(2);
			setBlockPillar(xx, zz, y, y + smallShroomHeight - 1, mushroom, stalkMeta);
			setBlock(xx, y + smallShroomHeight, zz, mushroom, bulbFullMeta);
			connectList.add(new ChunkCoordinates(xx, y - 1, zz));
		}

		int coordAmt = connectList.size();

		for (int connectionAttempt = 0, dir, xx, zz; connectionAttempt < 48; connectionAttempt++) {
			ChunkCoordinates coords1 = connectList.get(rand.nextInt(coordAmt));
			ChunkCoordinates coords2 = rand.nextInt(3) != 0 ? connectList.get(0) : connectList.get(rand.nextInt(coordAmt));
			if (coords1 == coords2)
				continue;

			double dist = MathUtil.distance(coords1.posX - coords2.posX, coords1.posZ - coords2.posZ);
			if (dist < 1D)
				continue;

			dir = rand.nextInt(4);
			xx = coords1.posX + Direction.offsetX[dir];
			zz = coords1.posZ + Direction.offsetZ[dir];

			if (MathUtil.distance(xx - coords2.posX, zz - coords2.posZ) < dist) {
				setBlock(xx, y - 1, zz, mushroom, stalkMeta);
				if (rand.nextInt(16) == 0)
					setBlock(xx, y, zz, mushroom, stalkMeta);
				coords1.posX = xx;
				coords1.posZ = zz;
			}
		}

		return true;
	}

	/*
	 * MUSHROOM TYPE - DUTCH CAP
	 */

	private boolean genDutchCap(int x, int y, int z, Block mushroom) {
		int height = 9 + rand.nextInt(8);

		if (!checkAirCube(x - 2, y, z - 2, x + 3, y + 4, z + 3) || !checkAirCube(x - 3, y + 5, z - 3, x + 5, y + height + 1, z + 5) || !checkAirCube(x - 4, y + height + 2, z - 4, x + 7, y + height + 4, z + 7) || !checkSolidCube(x - 2, y - 1, z - 2, x + 3, y - 1, z + 3))
			return false;

		for (int a = 0; a < 2; a++)
			for (int b = 0; b < 2; b++) {
				setBlockPillar(x - 1 + 3 * a, z + b, y, y + 1, mushroom, stalkMeta);
				setBlockPillar(x + b, z - 1 + 3 * a, y, y + 1, mushroom, stalkMeta);

				setBlock(x - 1 + 3 * a, y, z - 1 + 3 * b, mushroom, stalkMeta);
				setBlock(x - 2 + 5 * a, y, z + b, mushroom, stalkMeta);
				setBlock(x + b, y, z - 2 + 5 * a, mushroom, stalkMeta);
			}

		setBlockCube(x, y, z, x + 1, y + height, z + 1, mushroom, stalkMeta);

		for (int py = 4; py <= height; py++) {
			boolean isTop = py >= height - 1;

			if (rand.nextInt(3) == 0 || isTop)
				for (int attempt = 0; attempt < (isTop ? 2 : 1); attempt++) {
					int branchAddX = 0, branchAddZ = 0;

					for (int branchAttempt = 0; branchAttempt < 12 && branchAddX == 0 && branchAddZ == 0; branchAttempt++) {
						branchAddX = rand.nextInt(3) != 0 ? 0 : rand.nextInt(3) - 1;
						branchAddZ = rand.nextInt(3) != 0 ? 0 : rand.nextInt(3) - 1;
					}

					if (branchAddX == 0 && branchAddZ == 0)
						continue;
					if (!checkAirCube(x + branchAddX * 2, y + py - 2, z + branchAddZ * 2, x + branchAddX * 2 + 1, y + py, z + branchAddZ * 2 + 1))
						continue;

					int branchSize = isTop ? 3 + rand.nextInt(2) : 2 + rand.nextInt(2 + rand.nextInt(2));

					for (int branch = 1; branch <= branchSize; branch++) {
						setBlockRect(x + branchAddX * branch, z + branchAddZ * branch, x + branchAddX * branch + 1, z + branchAddZ * branch + 1, y + py - 1 + branch, mushroom, stalkMeta);
						if (isTop)
							setBlockRect(x + branchAddX * branch, z + branchAddZ * branch, x + branchAddX * branch + 1, z + branchAddZ * branch + 1, y + py + branch, mushroom, stalkMeta);
					}

					int branchTopX = x + branchAddX * branchSize, branchTopY = y + py + branchSize, branchTopZ = z + branchAddZ * branchSize;

					if (isTop) {
						setBlockRect(branchTopX, branchTopZ, branchTopX + 1, branchTopZ + 1, branchTopY + 1, mushroom, stalkMeta);
						branchTopY += 2;

						for (int a = 0; a < 2; a++)
							for (int b = 0; b < 2; b++) {
								if (isAir(branchTopX + a, branchTopY, branchTopZ + b))
									setBlock(branchTopX + a, branchTopY, branchTopZ + b, mushroom, bulbFullMeta);
								if (isAir(branchTopX - 1 + 3 * a, branchTopY - 1, branchTopZ + b))
									setBlock(branchTopX - 1 + 3 * a, branchTopY - 1, branchTopZ + b, mushroom, bulbFullMeta);
								if (isAir(branchTopX + b, branchTopY - 1, branchTopZ - 1 + 3 * a))
									setBlock(branchTopX + b, branchTopY - 1, branchTopZ - 1 + 3 * a, mushroom, bulbFullMeta);
								if (isAir(branchTopX - 1 + 3 * a, branchTopY - 2, branchTopZ - 1 + 3 * b))
									setBlock(branchTopX - 1 + 3 * a, branchTopY - 2, branchTopZ - 1 + 3 * b, mushroom, bulbFullMeta);
								if (isAir(branchTopX - 2 + 5 * a, branchTopY - 2, branchTopZ + b))
									setBlock(branchTopX - 2 + 5 * a, branchTopY - 2, branchTopZ + b, mushroom, bulbFullMeta);
								if (isAir(branchTopX + b, branchTopY - 2, branchTopZ - 2 + 5 * a))
									setBlock(branchTopX + b, branchTopY - 2, branchTopZ - 2 + 5 * a, mushroom, bulbFullMeta);
							}
					} else
						for (int a = 0; a < 2; a++)
							for (int b = 0; b < 2; b++) {
								if (isAir(branchTopX + a, branchTopY, branchTopZ + b))
									setBlock(branchTopX + a, branchTopY, branchTopZ + b, mushroom, bulbFullMeta);
								if (isAir(branchTopX - 1 + 3 * a, branchTopY - 1, branchTopZ + b))
									setBlock(branchTopX - 1 + 3 * a, branchTopY - 1, branchTopZ + b, mushroom, bulbFullMeta);
								if (isAir(branchTopX + b, branchTopY - 1, branchTopZ - 1 + 3 * a))
									setBlock(branchTopX + b, branchTopY - 1, branchTopZ - 1 + 3 * a, mushroom, bulbFullMeta);
							}
				}
		}

		return true;
	}

	/*
	 * MUSHROOM TYPE - GRANDMA'S SHOES
	 */

	private boolean genGrandmasShoes(int x, int y, int z, Block mushroom) {
		int height = 5 + rand.nextInt(8), splits = rand.nextInt(height > 8 ? 3 : 2), splitSize = splits == 0 ? height : (int) Math.ceil(height / (1F + splits)), splitDir = splits != 0 ? rand.nextInt(4) : -1;

		int splitOffX = splitDir == -1 ? 0 : Direction.offsetX[splitDir], splitOffZ = splitDir == -1 ? 0 : Direction.offsetZ[splitDir];
		if (!checkAirCube(x - 1, y, z - 1, x + 2, y + height - 2, z + 2) || !checkAirCube(x - 3 + splitOffX, z - 3 + splitOffZ, y + height - 1, x + 3 + splitOffX, y + height + 1, z + 3 + splitOffZ) || !checkSolidCube(x - 1, y - 1, z - 1, x + 3, y - 1, z + 3))
			return false;

		for (int a = 0; a < 2; a++)
			for (int b = 0; b < 2; b++) {
				setBlockRect(x - 1 + 3 * b, z, x - 1 + 3 * b, z + 1, y, mushroom, stalkMeta);
				setBlockRect(x, z - 1 + 3 * b, x + 1, z - 1 + 3 * b, y, mushroom, stalkMeta);
			}

		for (int py = 0, split = splitSize; py <= height; py++) {
			setBlockRect(x, z, x + 1, z + 1, y + py, mushroom, stalkMeta);

			if (--split < 0 && py < height) {
				x += splitOffX;
				z += splitOffZ;
				split = splitSize - 1;
			}
		}

		for (int a = 0; a < 2; a++) {
			for (int b = 0; b < 2; b++) {
				bulbs.add(new ChunkCoordinates(x + a, y + height + 1, z + b));
				bulbs.add(new ChunkCoordinates(x - 1 + 3 * a, y + height + 1, z + b));
				bulbs.add(new ChunkCoordinates(x + b, y + height + 1, z - 1 + 3 * a));
				setBlock(x - 1 + 3 * a, y + height, z - 1 + 3 * b, mushroom, 5); // top
				// only
				bulbs.add(new ChunkCoordinates(x - 2 + 5 * a, y + height - 1, z - 2 + 5 * b));
			}

			for (int b = 0; b < 4; b++) {
				bulbs.add(new ChunkCoordinates(x - 2 + 5 * a, y + height, z - 1 + b));
				bulbs.add(new ChunkCoordinates(x - 1 + b, y + height, z - 2 + 5 * a));
				bulbs.add(new ChunkCoordinates(x - 3 + 7 * a, y + height - 1, z - 1 + b));
				bulbs.add(new ChunkCoordinates(x - 1 + b, y + height - 1, z - 3 + 7 * a));
			}
		}

		return true;
	}

	/*
	 * BULB METADATA
	 */

	private void generateBulbs(int centerX, int centerZ, Block mushroom) {
		for (ChunkCoordinates bulb : bulbs)
			setBlock(bulb.posX, bulb.posY, bulb.posZ, tempBlock);
		for (ChunkCoordinates bulb : bulbs)
			setMetadata(bulb.posX, bulb.posY, bulb.posZ, getBulbMetadata(centerX, centerZ, bulb));
		for (ChunkCoordinates bulb : bulbs)
			setBlock(bulb.posX, bulb.posY, bulb.posZ, mushroom, getMetadata(bulb.posX, bulb.posY, bulb.posZ));
		bulbs.clear();
	}

	private int getBulbMetadata(int centerX, int centerZ, ChunkCoordinates bulb) {
		boolean posX = getBlock(bulb.posX + 1, bulb.posY, bulb.posZ) == tempBlock, negX = getBlock(bulb.posX - 1, bulb.posY, bulb.posZ) == tempBlock, posZ = getBlock(bulb.posX, bulb.posY, bulb.posZ + 1) == tempBlock, negZ = getBlock(bulb.posX, bulb.posY, bulb.posZ - 1) == tempBlock;

		if (posX && negX && posZ && negZ)
			return 5; // if surrounded, use top only

		if (posX && negX)
			if (bulb.posZ > centerZ)
				return 8; // use top and south
			else
				return 2; // use top and north

		if (posZ && negZ)
			if (bulb.posX > centerX)
				return 6; // use top and east
			else
				return 4; // use top and west

		if (posX && posZ)
			return 1; // use top, north and west
		if (negX && negZ)
			return 9; // use top, south and east
		if (negX && posZ)
			return 3; // use top, north and east
		if (posX && negZ)
			return 7; // use top, south and west

		int sides = (posX ? 1 : 0) + (negX ? 1 : 0) + (posZ ? 1 : 0) + (negZ ? 1 : 0);
		if (sides > 1)
			return 0; // go away, you're no longer needed here

		boolean posXposZ = getBlock(bulb.posX + 1, bulb.posY, bulb.posZ + 1) == tempBlock, negXposZ = getBlock(bulb.posX - 1, bulb.posY, bulb.posZ + 1) == tempBlock, posXnegZ = getBlock(bulb.posX + 1, bulb.posY, bulb.posZ - 1) == tempBlock, negXnegZ = getBlock(bulb.posX - 1, bulb.posY, bulb.posZ - 1) == tempBlock;

		int corners = (posXposZ ? 1 : 0) + (negXposZ ? 1 : 0) + (posXnegZ ? 1 : 0) + (negXnegZ ? 1 : 0);

		if (sides == 0 && corners == 0)
			return 14; // use full cap for lonely shrooms
		if (posXposZ && negXposZ || negXnegZ && posXnegZ || posXposZ && posXnegZ || negXposZ && negXnegZ)
			return 14; // use full cap for + pattern

		if (sides == 0 && corners == 2)
			for (int dir = 0, meta; dir < 4; dir++) {
				meta = getMetadata(bulb.posX + Direction.offsetX[dir], bulb.posY - 1, bulb.posZ + Direction.offsetZ[dir]);
				if (meta != 0)
					return meta; // use meta of cap above and to the side - either 2 side cap or full cap
			}

		if (sides == 1 && corners == 1 || sides == 0 && corners == 2)
			if (bulb.posX > centerX) {
				if (bulb.posZ > centerZ)
					return 9; // use top, south and east
				else
					return 3; // use top, north and east
			} else if (bulb.posZ > centerZ)
				return 7; // use top, south and west
			else
				return 1; // use top, north and west

		return 0;
	}
}