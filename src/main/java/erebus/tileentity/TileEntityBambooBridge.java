package erebus.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityBambooBridge extends TileEntity {

	public boolean renderSide1, renderSide2;

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	public void setRenderSide1(boolean side1) {
		renderSide1 = side1;
	}

	public void setRenderSide2(boolean side2) {
		renderSide2 = side2;
	}

	public boolean getRenderSide1() {
		return renderSide1;
	}

	public boolean getRenderSide2() {
		return renderSide2;
	}

	protected void writeTileToNBT(NBTTagCompound nbt) {
		nbt.setBoolean("renderSide1", renderSide1);
		nbt.setBoolean("renderSide2", renderSide2);
	}

	protected void readTileFromNBT(NBTTagCompound nbt) {
		renderSide1 = nbt.getBoolean("renderSide1");
		renderSide2 = nbt.getBoolean("renderSide2");
	}

	@Override
	public final NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		writeTileToNBT(nbt);
		return nbt;
	}

	@Override
	public final void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		readTileFromNBT(nbt);
	}

	@Override
    public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = new NBTTagCompound();
        return writeToNBT(nbt);
    }

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new SPacketUpdateTileEntity(pos, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		readFromNBT(packet.getNbtCompound());
	}
}