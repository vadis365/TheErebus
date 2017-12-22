package erebus.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPreservedBlock extends TileEntity {

	private NBTTagCompound entityNBT;
	private Entity cachedRenderEntity;
	public byte rotation = 0;

	public Entity getRenderEntity() {
		if (cachedRenderEntity == null && entityNBT != null)
			cachedRenderEntity = EntityList.createEntityFromNBT(entityNBT, getWorld());
		return cachedRenderEntity;
	}

    public void markForUpdate() {
    	if (this != null && !getWorld().isRemote) {
			final IBlockState state = getWorld().getBlockState(getPos());
			getWorld().notifyBlockUpdate(getPos(), state, state, 8);
			markDirty();
    	}
    }

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new SPacketUpdateTileEntity(getPos(), 0, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		super.onDataPacket(net, packet);
		readFromNBT(packet.getNbtCompound());
		markForUpdate();
		return;
	}

	@Override
    public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = new NBTTagCompound();
        return writeToNBT(nbt);
    }

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		entityNBT = data.getCompoundTag("EntityNBT");
		rotation = data.getByte("rotation");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setTag("EntityNBT", entityNBT);
		data.setByte("rotation", rotation);
		return data;
	}

	public NBTTagCompound getEntityNBT() {
		return entityNBT;
	}

	public void setEntityNBT(NBTTagCompound entityNBT) {
		this.entityNBT = entityNBT;
		markForUpdate();
	}

	public void spawnTrappedEntity() {
		if (getWorld().isRemote || entityNBT == null)
			return;

		Entity entity = EntityList.createEntityFromNBT(entityNBT, getWorld());
		entity.setLocationAndAngles(getPos().getX() + 0.5D, getPos().getY(), getPos().getZ() + 0.5D, 0.0F, 0.0F);
		getWorld().spawnEntity(entity);
	}
}