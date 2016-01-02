package erebus.tileentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import erebus.ModBlocks;
import erebus.core.helper.Utils;

public class TileEntitySlidingBlockPuzzle extends TileEntity {

	private ForgeDirection facing;
	private BlockOffset offset;
	private SlidingPuzzle puzzle;
	private final SlidingPiece[] pieces = new SlidingPiece[4], originalPieces = new SlidingPiece[4];

	public ForgeDirection getFacing() {
		return facing;
	}

	public BlockOffset getOffset() {
		return offset;
	}

	public SlidingPuzzle getPuzzle() {
		return puzzle;
	}

	public SlidingPiece[] getPieces() {
		return pieces;
	}

	public void onPuzzleChange() {
		for (int i = 0; i < pieces.length; i++)
			if (!originalPieces[i].equals(pieces[i]))
				return;

		Utils.breakBlockWithParticles(worldObj, xCoord, yCoord, zCoord, 0);
		List<ForgeDirection> neighbouringDirs = new ArrayList<ForgeDirection>();
		neighbouringDirs.add(ForgeDirection.UP);
		neighbouringDirs.add(ForgeDirection.DOWN);
		neighbouringDirs.add(facing.getRotation(ForgeDirection.UP));
		neighbouringDirs.add(facing.getRotation(ForgeDirection.DOWN));
		for (ForgeDirection dir : neighbouringDirs) {
			BlockOffset neighbourOffset = offset.add(dir);
			if (!neighbourOffset.isValid())
				continue;
			TileEntitySlidingBlockPuzzle neighbour = Utils.getTileEntity(worldObj, xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, TileEntitySlidingBlockPuzzle.class);
			if (neighbour != null)
				neighbour.onPuzzleChange();
		}
	}

	public boolean handleClick(float hitX, float hitY, float hitZ) {
		if (hitY > 0.5F)
			switch (facing) {
				case NORTH:
				case SOUTH:
					if (hitX > 0.5F)
						return handleClick(pieces[0]);
					else
						return handleClick(pieces[1]);
				case WEST:
				case EAST:
					if (hitZ > 0.5F)
						return handleClick(pieces[1]);
					else
						return handleClick(pieces[0]);
				default:
					break;
			}
		else
			switch (facing) {
				case NORTH:
				case SOUTH:
					if (hitX > 0.5F)
						return handleClick(pieces[2]);
					else
						return handleClick(pieces[3]);
				case WEST:
				case EAST:
					if (hitZ > 0.5F)
						return handleClick(pieces[3]);
					else
						return handleClick(pieces[2]);
				default:
					break;
			}
		return false;
	}

	private boolean handleClick(SlidingPiece piece) {
		if (piece.isEmpty())
			return false;

		SlidingPiece emptyPiece = getEmptyPieceIfPresent();
		if (emptyPiece != null) {
			if (swapPiecesIfPossible(piece, emptyPiece)) {
				if (!worldObj.isRemote) {
					onPuzzleChange();
					Utils.sendUpdatesToClient(worldObj, getDescriptionPacket());
				}
				return true;
			}
		} else {
			List<ForgeDirection> neighbouringDirs = new ArrayList<ForgeDirection>();
			neighbouringDirs.add(piece.index <= 1 ? ForgeDirection.UP : ForgeDirection.DOWN);
			neighbouringDirs.add(facing.getRotation(piece.index % 2 == 0 ? ForgeDirection.UP : ForgeDirection.DOWN));

			for (ForgeDirection dir : neighbouringDirs) {
				BlockOffset neighbourOffset = offset.add(dir);
				if (!neighbourOffset.isValid())
					continue;

				TileEntitySlidingBlockPuzzle neighbour = Utils.getTileEntity(worldObj, xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, TileEntitySlidingBlockPuzzle.class);
				if (neighbour != null) {
					emptyPiece = neighbour.getEmptyPieceIfPresent();
					if (neighbourOffset.equals(neighbour.offset) && emptyPiece != null)
						if (swapPiecesIfPossible(piece, emptyPiece)) {
							if (!worldObj.isRemote) {
								onPuzzleChange();
								neighbour.onPuzzleChange();
								Utils.sendUpdatesToClient(worldObj, getDescriptionPacket());
								Utils.sendUpdatesToClient(neighbour.worldObj, neighbour.getDescriptionPacket());
							}
							return true;
						}
				}
			}
		}
		return false;
	}

	private SlidingPiece getEmptyPieceIfPresent() {
		for (SlidingPiece p : pieces)
			if (p.isEmpty())
				return p;
		return null;
	}

	private boolean swapPiecesIfPossible(SlidingPiece piece, SlidingPiece emptyPiece) {
		if (piece.x == emptyPiece.x && piece.y != emptyPiece.y || piece.x != emptyPiece.x && piece.y == emptyPiece.y) {
			if (!worldObj.isRemote)
				SlidingPiece.swapUVs(piece, emptyPiece);
			return true;
		}
		return false;
	}

	@Override
	public Packet getDescriptionPacket() {
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, writeData(new NBTTagCompound()));
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		if (pkt.func_148853_f() == 0)
			readData(pkt.func_148857_g());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		readData(nbt);
	}

	private void readData(NBTTagCompound nbt) {
		facing = ForgeDirection.getOrientation(nbt.getByte("Facing"));
		offset = BlockOffset.fromArray(nbt.getIntArray("Offset"));
		puzzle = SlidingPuzzle.values()[nbt.getInteger("Puzzle")];
		for (int i = 0; i < pieces.length; i++)
			pieces[i] = SlidingPiece.fromArray(nbt.getIntArray("Piece" + i));
		for (int i = 0; i < originalPieces.length; i++)
			originalPieces[i] = SlidingPiece.fromArray(nbt.getIntArray("OriginalPiece" + i));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		writeData(nbt);
	}

	private NBTTagCompound writeData(NBTTagCompound nbt) {
		if (facing != null)
			nbt.setByte("Facing", (byte) facing.ordinal());
		if (offset != null)
			nbt.setIntArray("Offset", offset.toArray());
		if (puzzle != null)
			nbt.setInteger("Puzzle", puzzle.ordinal());
		for (int i = 0; i < pieces.length; i++)
			if (pieces[i] != null)
				nbt.setIntArray("Piece" + i, pieces[i].toArray());
		for (int i = 0; i < originalPieces.length; i++)
			if (originalPieces[i] != null)
				nbt.setIntArray("OriginalPiece" + i, originalPieces[i].toArray());
		return nbt;
	}

	private static Map<ForgeDirection, List<BlockOffset>> map = new HashMap<ForgeDirection, List<BlockOffset>>();

	static {
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			List<BlockOffset> offsets = new ArrayList<BlockOffset>();
			switch (dir) {
				case EAST:
					for (int y = 1; y >= -1; y--) {
						offsets.add(new BlockOffset(0, y, +1));
						offsets.add(new BlockOffset(0, y, 0));
						offsets.add(new BlockOffset(0, y, -1));
					}
					break;
				case NORTH:
					for (int y = 1; y >= -1; y--) {
						offsets.add(new BlockOffset(+1, y, 0));
						offsets.add(new BlockOffset(0, y, 0));
						offsets.add(new BlockOffset(-1, y, 0));
					}
					break;
				case SOUTH:
					for (int y = 1; y >= -1; y--) {
						offsets.add(new BlockOffset(-1, y, 0));
						offsets.add(new BlockOffset(0, y, 0));
						offsets.add(new BlockOffset(+1, y, 0));
					}
					break;
				case WEST:
					for (int y = 1; y >= -1; y--) {
						offsets.add(new BlockOffset(0, y, -1));
						offsets.add(new BlockOffset(0, y, 0));
						offsets.add(new BlockOffset(0, y, +1));
					}
					break;

				default:
					break;
			}
			if (!offsets.isEmpty())
				map.put(dir, offsets);
		}
	}

	/**
	 * Creates a puzzle with the centre block at the passed coordinates
	 */
	public static void createPuzzleAt(World world, int x, int y, int z, ForgeDirection facing, SlidingPuzzle puzzle) {
		List<TileEntitySlidingBlockPuzzle> tiles = new ArrayList<TileEntitySlidingBlockPuzzle>();
		List<BlockOffset> offsets = map.get(facing);
		if (offsets == null)
			throw new IllegalArgumentException("Can't generate sliding block puzzle facing " + facing);
		if (facing == ForgeDirection.WEST)
			world.setBlock(x , y, z - 2, ModBlocks.completedPuzzle);
		if (facing == ForgeDirection.EAST)
			world.setBlock(x , y, z + 2, ModBlocks.completedPuzzle);
		if (facing == ForgeDirection.SOUTH)
			world.setBlock(x - 2, y, z, ModBlocks.completedPuzzle);
		if (facing == ForgeDirection.NORTH)
			world.setBlock(x + 2, y, z, ModBlocks.completedPuzzle);
		TileEntityCompletedPuzzle compPuzzle = Utils.getTileEntity(world, x, y + 2, z, TileEntityCompletedPuzzle.class);
		if (compPuzzle != null)
			compPuzzle.setPuzzle(puzzle);

		for (BlockOffset offset : offsets)
			tiles.add(setBlock(world, x, y, z, offset, facing, puzzle));

		List<SlidingPiece> pieces = new ArrayList<SlidingPiece>();
		for (int i = 0; i < tiles.size(); i++) {
			TileEntitySlidingBlockPuzzle tile = tiles.get(i);
			int tX = i % 3;
			int tY = i / 3;

			int xx = 2 * tX;
			int yy = 2 * tY;

			if (facing == ForgeDirection.SOUTH || facing == ForgeDirection.EAST) {
				pieces.add(tile.originalPieces[1] = new SlidingPiece(0, xx, yy));
				pieces.add(tile.originalPieces[0] = new SlidingPiece(1, xx + 1, yy));
				pieces.add(tile.originalPieces[3] = new SlidingPiece(2, xx, yy + 1));
				if (tX == 2 && tY == 2)
					pieces.add(tile.originalPieces[2] = new SlidingPiece(3, -1, -1));
				else
					pieces.add(tile.originalPieces[2] = new SlidingPiece(3, xx + 1, yy + 1));
			} else {
				pieces.add(tile.originalPieces[0] = new SlidingPiece(0, xx, yy));
				pieces.add(tile.originalPieces[1] = new SlidingPiece(1, xx + 1, yy));
				pieces.add(tile.originalPieces[2] = new SlidingPiece(2, xx, yy + 1));
				if (tX == 2 && tY == 2)
					pieces.add(tile.originalPieces[3] = new SlidingPiece(3, -1, -1));
				else
					pieces.add(tile.originalPieces[3] = new SlidingPiece(3, xx + 1, yy + 1));
			}
		}
		for (TileEntitySlidingBlockPuzzle tile : tiles) {
			Random rand = tile.worldObj.rand;

			for (int i = 0; i < tile.pieces.length; i++) {
				int index = rand.nextInt(pieces.size());
				int pieceIndex = i;
				if (facing == ForgeDirection.SOUTH || facing == ForgeDirection.EAST)
					pieceIndex ^= 1;
				tile.pieces[i] = pieces.get(index).copyUV(pieceIndex);
				pieces.remove(index);
			}

			Utils.sendUpdatesToClient(tile.worldObj, tile.getDescriptionPacket());
		}
	}

	private static TileEntitySlidingBlockPuzzle setBlock(World world, int x, int y, int z, BlockOffset offset, ForgeDirection facing, SlidingPuzzle puzzle) {
		x += offset.x;
		y += offset.y;
		z += offset.z;

		world.setBlock(x, y, z, ModBlocks.slidingBlockPuzzle);
		TileEntitySlidingBlockPuzzle tile = Utils.getTileEntity(world, x, y, z, TileEntitySlidingBlockPuzzle.class);
		tile.facing = facing;
		tile.offset = offset;
		tile.puzzle = puzzle;

		return tile;
	}

	public static class SlidingPiece {

		final int index;
		final int x, y;
		int u, v;

		SlidingPiece(int index, int u, int v) {
			this.index = index;
			this.u = u;
			this.v = v;

			x = index % 2;
			y = index / 2;
		}

		public int getU() {
			return u;
		}

		public int getV() {
			return v;
		}

		public boolean isEmpty() {
			return u < 0 && v < 0;
		}

		int[] toArray() {
			return new int[] { index, u, v };
		}

		SlidingPiece copy() {
			return new SlidingPiece(index, u, v);
		}

		SlidingPiece copyUV(int index) {
			return new SlidingPiece(index, u, v);
		}

		static SlidingPiece fromArray(int[] array) {
			return new SlidingPiece(array[0], array[1], array[2]);
		}

		static void swapUVs(SlidingPiece piece0, SlidingPiece piece1) {
			SlidingPiece temp = piece0.copy();
			piece0.u = piece1.u;
			piece0.v = piece1.v;
			piece1.u = temp.u;
			piece1.v = temp.v;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof SlidingPiece) {
				SlidingPiece piece = (SlidingPiece) obj;
				return piece.index == index && piece.u == u && piece.v == v;
			}
			return false;
		}

		@Override
		public String toString() {
			return "SlidingPiece: " + "index:" + index + ", u: " + u + ", v:" + v;
		}
	}

	public static class BlockOffset {

		public final int x, y, z;

		BlockOffset(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		int[] toArray() {
			return new int[] { x, y, z };
		}

		BlockOffset add(ForgeDirection dir) {
			return new BlockOffset(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
		}

		boolean isValid() {
			return x >= -1 && x <= 1 && y >= -1 && y <= 1 && z >= -1 && z <= 1;
		}

		static BlockOffset fromArray(int[] array) {
			return new BlockOffset(array[0], array[1], array[2]);
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof BlockOffset) {
				BlockOffset off = (BlockOffset) obj;
				return off.x == x && off.y == y && off.z == z;
			}
			return false;
		}

		@Override
		public String toString() {
			return "BlockOffset: " + x + ", " + y + ", " + z;
		}
	}

	public static enum SlidingPuzzle {
		TEST("erebus:textures/puzzles/test.png");

		public final String imagePath;

		SlidingPuzzle(String imagePath) {
			this.imagePath = imagePath;
		}
	}
}