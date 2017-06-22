package erebus.world.feature.plant;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import erebus.ModBlocks;
import erebus.blocks.ErebusHugeMushroom;
import erebus.core.helper.MathUtil;
import erebus.world.feature.WorldGenErebus;
import erebus.world.feature.util.OldForgeDirection;

public class WorldGenGiantMushrooms extends WorldGenErebus {
	private static final int stalkMeta = 10;
	private static final int bulbFullMeta = 14;
	//private static final Block tempBlock = Blocks.BEDROCK;
	public static final int[] offsetX = new int[] {0, -1, 0, 1};
    public static final int[] offsetZ = new int[] {1, 0, -1, 0};

	public enum MushroomType {
		DARK_CAPPED(ModBlocks.DARK_CAPPED_MUSHROOM, ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK),
		GRANDMAS_SHOES(ModBlocks.GRANDMAS_SHOES_MUSHROOM, ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK),
		SARCASTIC_CZECH(ModBlocks.SARCASTIC_CZECH_MUSHROOM, ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK),
		KAIZERS_FINGERS(ModBlocks.KAIZERS_FINGERS_MUSHROOM, ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK),
		DUTCH_CAP(ModBlocks.DUTCH_CAP_MUSHROOM, ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK);


		public final Block mushroom, block;

		MushroomType(Block mushroom, Block log) {
			this.block = log;
			this.mushroom = mushroom;
		}

		public static MushroomType getFromShroom(Block block) {
			for (MushroomType type : values())
				if (type.mushroom == block)
					return type;
			return null;
		}
	}

	private MushroomType mushroomType = MushroomType.DARK_CAPPED;
	private final List<BlockPos> bulbs = new ArrayList<BlockPos>();

	public void setMushroomType(MushroomType type) {
		mushroomType = type;
	}

	@Override
	protected boolean generate(BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		bulbs.clear();
		Block mushroom = mushroomType.block;
		boolean res = false;

		switch (mushroomType) {
			case DARK_CAPPED:
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
			case SARCASTIC_CZECH:
				res = genSarcasticCzech(x - 1, y, z, mushroom);
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
				bulbs.add(new BlockPos(x + px, y, z + pz));

		for (int py = 1; py <= sideHeight; py++)
			for (int off = -1; off <= 1; off++) {
				bulbs.add(new BlockPos(x + 2, y - py, z + off));
				bulbs.add(new BlockPos(x - 2, y - py, z + off));
				bulbs.add(new BlockPos(x + off, y - py, z + 2));
				bulbs.add(new BlockPos(x + off, y - py, z - 2));
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
				setBlock(x + offsetX[dir], y + py, z + offsetZ[dir], mushroom, bulbFullMeta);
				++sidesPlaced;
			}
		}

		bulbs.add(new BlockPos(x, y + mainShroomHeight + 1, z));
		for (int a = 0; a < 4; a++)
			bulbs.add(new BlockPos(x + offsetX[a], y + mainShroomHeight, z + offsetZ[a]));

		List<BlockPos> connectList = new ArrayList<BlockPos>();
		connectList.add(new BlockPos(x, y - 1, z));

		for (int smallShroomAttempt = 0, xx, zz; smallShroomAttempt < 4 + rand.nextInt(7); smallShroomAttempt++) {
			xx = x + rand.nextInt(4) - rand.nextInt(4);
			zz = z + rand.nextInt(4) - rand.nextInt(4);
			if (!isAir(xx, y, zz) || !isAir(xx - 1, y, zz) || !isAir(xx + 1, y, zz) || !isAir(xx, y, zz - 1) || !isAir(xx, y, zz + 1))
				continue;

			int smallShroomHeight = rand.nextBoolean() ? 1 : 1 + rand.nextInt(2);
			setBlockPillar(xx, zz, y, y + smallShroomHeight - 1, mushroom, stalkMeta);
			setBlock(xx, y + smallShroomHeight, zz, mushroom, bulbFullMeta);
			connectList.add(new BlockPos(xx, y - 1, zz));
		}

		int coordAmt = connectList.size();

		for (int connectionAttempt = 0, dir, xx, zz; connectionAttempt < 48; connectionAttempt++) {
			BlockPos coords1 = connectList.get(rand.nextInt(coordAmt));
			BlockPos coords2 = rand.nextInt(3) != 0 ? connectList.get(0) : connectList.get(rand.nextInt(coordAmt));
			if (coords1 == coords2)
				continue;

			double dist = MathUtil.distance(coords1.getX() - coords2.getX(), coords1.getZ() - coords2.getZ());
			if (dist < 1D)
				continue;

			dir = rand.nextInt(4);
			xx = coords1.getX() + offsetX[dir];
			zz = coords1.getZ() + offsetZ[dir];

			if (MathUtil.distance(xx - coords2.getX(), zz - coords2.getZ()) < dist) {
				setBlock(xx, y - 1, zz, mushroom, stalkMeta);
				if (rand.nextInt(16) == 0)
					setBlock(xx, y, zz, mushroom, stalkMeta);
				coords1.add(offsetX[dir], 0, offsetZ[dir]);
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

		int splitOffX = splitDir == -1 ? 0 : offsetX[splitDir], splitOffZ = splitDir == -1 ? 0 : offsetZ[splitDir];
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
				bulbs.add(new BlockPos(x + a, y + height + 1, z + b));
				bulbs.add(new BlockPos(x - 1 + 3 * a, y + height + 1, z + b));
				bulbs.add(new BlockPos(x + b, y + height + 1, z - 1 + 3 * a));
				setBlock(x - 1 + 3 * a, y + height, z - 1 + 3 * b, mushroom, 5); // top
				// only
				bulbs.add(new BlockPos(x - 2 + 5 * a, y + height - 1, z - 2 + 5 * b));
			}

			for (int b = 0; b < 4; b++) {
				bulbs.add(new BlockPos(x - 2 + 5 * a, y + height, z - 1 + b));
				bulbs.add(new BlockPos(x - 1 + b, y + height, z - 2 + 5 * a));
				bulbs.add(new BlockPos(x - 3 + 7 * a, y + height - 1, z - 1 + b));
				bulbs.add(new BlockPos(x - 1 + b, y + height - 1, z - 3 + 7 * a));
			}
		}

		return true;
	}

	/*
	 * MUSHROOM TYPE - SARCASTIC CZECH
	 */
	private boolean genSarcasticCzech(int x, int y, int z, Block mushroom) {
		int height = 2 + rand.nextInt(3);
		int armLength = 4 + rand.nextInt(3);

		if (!checkAirCube(x, y, z, x, y + height, z) || !checkAirCube(x - armLength, y + height, z - armLength, x + armLength, y + height + 1, z + armLength))
			return false;
	
		setBlockPillar(x, z, y, y + height, mushroom, stalkMeta);
		setBlockPillar(x + 1, z, y, y + height, mushroom, stalkMeta);
		setBlockPillar(x, z + 1, y, y + height, mushroom, stalkMeta);
		setBlockPillar(x + 1, z + 1, y, y + height, mushroom, stalkMeta);
		y += height;
		//temp fix until gany fixes (t)his shit :P
		for (OldForgeDirection[] dirs : new OldForgeDirection[][] { new OldForgeDirection[] { OldForgeDirection.EAST, OldForgeDirection.SOUTH }, new OldForgeDirection[] { OldForgeDirection.EAST, OldForgeDirection.NORTH }, new OldForgeDirection[] { OldForgeDirection.WEST, OldForgeDirection.SOUTH }, new OldForgeDirection[] { OldForgeDirection.WEST, OldForgeDirection.NORTH } }) {
			int xx = x + dirs[0].offsetX;
			int yy = y;
			int zz = z + dirs[0].offsetZ;
			for (int i = 0; i < armLength; i++) {
				if (i % 2 == 0)
					yy++;
				else {
					OldForgeDirection dir = dirs[rand.nextInt(dirs.length)];
					xx += dir.offsetX;
					zz += dir.offsetZ;
				}
				setBlock(xx, yy, zz, mushroom, stalkMeta);
			}

			setBlock(xx, yy + 1, zz, mushroom, bulbFullMeta);
			for (int i = -1; i <= 1; i++)
				for (int j = -1; j <= 1; j++)
					setBlock(xx + i, yy, zz + j, mushroom, bulbFullMeta);
		}

		setBlockRect(x, z, x + 1, z + 1, y + 1, mushroom, bulbFullMeta);
		setBlockRect(x - 1, z - 1, x + 2, z + 2, y, mushroom, bulbFullMeta);
		return true;
	}

	/*
	 * BULB METADATA
	 */

	private void generateBulbs(int centerX, int centerZ, Block mushroom) {
		for (BlockPos bulb : bulbs)
			setBlock(bulb.getX(), bulb.getY(), bulb.getZ(), mushroom);
		for (BlockPos bulb : bulbs)
			setMetadata(bulb.getX(), bulb.getY(), bulb.getZ(), getBulbMetadata(centerX, centerZ, bulb));

		//for (BlockPos bulb : bulbs) {
		//	int meta = getMetadata(bulb.getX(), bulb.getY(), bulb.getZ());
		//	System.out.println("Meta should ALSO not be 0 here: " + meta);
		//	setBlock(bulb.getX(), bulb.getY(), bulb.getZ(), mushroom, getMetadata(bulb.getX(), bulb.getY(), bulb.getZ()));
		//}
		bulbs.clear();
	}

	private int getBulbMetadata(int centerX, int centerZ, BlockPos bulb) {
		boolean posX = getBlock(bulb.getX() + 1, bulb.getY(), bulb.getZ()) instanceof ErebusHugeMushroom, negX = getBlock(bulb.getX() - 1, bulb.getY(), bulb.getZ()) instanceof ErebusHugeMushroom, posZ = getBlock(bulb.getX(), bulb.getY(), bulb.getZ() + 1) instanceof ErebusHugeMushroom, negZ = getBlock(bulb.getX(), bulb.getY(), bulb.getZ() - 1) instanceof ErebusHugeMushroom;

		if (posX && negX && posZ && negZ)
			return 5; // if surrounded, use top only

		if (posX && negX)
			if (bulb.getZ() > centerZ)
				return 8; // use top and south
			else
				return 2; // use top and north

		if (posZ && negZ)
			if (bulb.getX() > centerX)
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

		boolean posXposZ = getBlock(bulb.getX() + 1, bulb.getY(), bulb.getZ() + 1) instanceof ErebusHugeMushroom, negXposZ = getBlock(bulb.getX() - 1, bulb.getY(), bulb.getZ() + 1) instanceof ErebusHugeMushroom, posXnegZ = getBlock(bulb.getX() + 1, bulb.getY(), bulb.getZ() - 1) instanceof ErebusHugeMushroom, negXnegZ = getBlock(bulb.getX() - 1, bulb.getY(), bulb.getZ() - 1) instanceof ErebusHugeMushroom;

		int corners = (posXposZ ? 1 : 0) + (negXposZ ? 1 : 0) + (posXnegZ ? 1 : 0) + (negXnegZ ? 1 : 0);

		if (sides == 0 && corners == 0)
			return 14; // use full cap for lonely shrooms
		if (posXposZ && negXposZ || negXnegZ && posXnegZ || posXposZ && posXnegZ || negXposZ && negXnegZ)
			return 14; // use full cap for + pattern

		if (sides == 0 && corners == 2)
			for (int dir = 0, meta; dir < 4; dir++) {
				meta = getMetadata(bulb.getX() + offsetX[dir], bulb.getY() - 1, bulb.getZ() + offsetZ[dir]);
				if (meta != 0)
					return meta; // use meta of cap above and to the side - either 2 side cap or full cap
			}

		if (sides == 1 && corners == 1 || sides == 0 && corners == 2)
			if (bulb.getX() > centerX) {
				if (bulb.getZ() > centerZ)
					return 9; // use top, south and east
				else
					return 3; // use top, north and east
			} else if (bulb.getZ() > centerZ)
				return 7; // use top, south and west
			else
				return 1; // use top, north and west

		return 0;
	}
}