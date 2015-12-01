package erebus.tileentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import erebus.ModBlocks;
import erebus.core.helper.Utils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntitySlidingBlockPuzzle extends TileEntity {

	private ForgeDirection facing;
	private BlockOffset offset;
	private SlidingPuzzle puzzle;
	private final SlidingPiece[] pieces = new SlidingPiece[4];

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

	public boolean handleClick(float hitX, float hitY, float hitZ) {
		System.out.println(worldObj.isRemote + " - " + hitX + ", " + hitY + ", " + hitZ);

		if (hitY > 0.5F)
			switch (facing) {
				case EAST:
					break;
				case NORTH:
					break;
				case SOUTH:
					break;
				case WEST:
					if (hitZ > 0.5F)
						return handleClick(pieces[1]);
					else
						return handleClick(pieces[0]);
				default:
					break;
			}
		else
			switch (facing) {
				case EAST:
					break;
				case NORTH:
					break;
				case SOUTH:
					break;
				case WEST:
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
				if (!worldObj.isRemote)
					sendUpdatesToClient();
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
								sendUpdatesToClient();
								neighbour.sendUpdatesToClient();
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
		int x = piece.index % 2;
		int y = piece.index / 2;
		int eX = emptyPiece.index % 2;
		int eY = emptyPiece.index / 2;
		if (x == eX && y != eY || x != eX && y == eY) {
			if (!worldObj.isRemote)
				SlidingPiece.swapPieces(piece, emptyPiece);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private void sendUpdatesToClient() {
		List<EntityPlayerMP> players = worldObj.playerEntities;
		for (EntityPlayerMP player : players)
			player.playerNetServerHandler.sendPacket(getDescriptionPacket());
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
			pieces[i] = SlidingPiece.fromArray(i, nbt.getIntArray("Piece" + i));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		writeData(nbt);
	}

	private NBTTagCompound writeData(NBTTagCompound nbt) {
		nbt.setByte("Facing", (byte) facing.ordinal());
		nbt.setIntArray("Offset", offset.toArray());
		nbt.setInteger("Puzzle", puzzle.ordinal());
		for (int i = 0; i < pieces.length; i++)
			nbt.setIntArray("Piece" + i, pieces[i].toArray());
		return nbt;
	}

	private static Map<ForgeDirection, List<BlockOffset>> map = new HashMap<ForgeDirection, List<BlockOffset>>();

	static {
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			List<BlockOffset> offsets = new ArrayList<BlockOffset>();
			switch (dir) {
				case EAST:
					for (int y = 1; y >= -1; y--) {
						offsets.add(new BlockOffset(0, y, -1));
						offsets.add(new BlockOffset(0, y, 0));
						offsets.add(new BlockOffset(0, y, +1));
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
						offsets.add(new BlockOffset(+1, y, 0));
						offsets.add(new BlockOffset(0, y, 0));
						offsets.add(new BlockOffset(-1, y, 0));
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

		for (BlockOffset offset : offsets)
			tiles.add(setBlock(world, x, y, z, offset, facing, puzzle));

		int index = 0;
		for (TileEntitySlidingBlockPuzzle tile : tiles) {
			int tX = index % 3;
			int tY = index / 3;

			int xx = 2 * tX;
			int yy = 2 * tY;

			tile.pieces[0] = new SlidingPiece(0, xx, yy);
			tile.pieces[1] = new SlidingPiece(1, xx + 1, yy);
			tile.pieces[2] = new SlidingPiece(2, xx, yy + 1);
			if (tX == 2 && tY == 2)
				tile.pieces[3] = new SlidingPiece(3, xx + 1, yy + 1, -1, -1);
			else
				tile.pieces[3] = new SlidingPiece(3, xx + 1, yy + 1);

			index++;
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
		tile.markDirty();

		return tile;
	}

	public static class SlidingPiece {

		final int index, x, y;
		int u, v;

		SlidingPiece(int index, int x, int y, int u, int v) {
			this.index = index;
			this.x = x;
			this.y = y;
			this.u = u;
			this.v = v;
		}

		SlidingPiece(int index, int x, int y) {
			this(index, x, y, x, y);
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
			return new int[] { x, y, u, v };
		}

		SlidingPiece copy() {
			return new SlidingPiece(index, x, y, u, v);
		}

		static SlidingPiece fromArray(int index, int[] array) {
			return new SlidingPiece(index, array[0], array[1], array[2], array[3]);
		}

		static void swapPieces(SlidingPiece piece0, SlidingPiece piece1) {
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
				return piece.x == x && piece.y == y && piece.u == u && piece.v == v;
			}
			return false;
		}

		@Override
		public String toString() {
			return "SlidingPiece: " + x + ", " + y + " - " + u + ", " + v;
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