package erebus.tileentity;

import erebus.core.helper.Utils;
import erebus.tileentity.TileEntitySlidingBlockPuzzle.SlidingPuzzle;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCompletedPuzzle extends TileEntity {

	private SlidingPuzzle puzzle;

	public void setPuzzle(SlidingPuzzle puzzle) {
		this.puzzle = puzzle;
		Utils.sendUpdatesToClient(worldObj, getDescriptionPacket());
	}

	public SlidingPuzzle getPuzzle() {
		return puzzle;
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
		puzzle = SlidingPuzzle.values()[nbt.getInteger("Puzzle")];
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		writeData(nbt);
	}

	private NBTTagCompound writeData(NBTTagCompound nbt) {
		if (puzzle != null)
			nbt.setInteger("Puzzle", puzzle.ordinal());
		return nbt;
	}
}