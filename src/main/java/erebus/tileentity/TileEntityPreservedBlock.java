package erebus.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPreservedBlock extends TileEntity {

	private NBTTagCompound entityNBT;
	private Entity cachedRenderEntity;

	public Entity getRenderEntity() {
		if (cachedRenderEntity == null && entityNBT != null)
			cachedRenderEntity = EntityList.createEntityFromNBT(entityNBT, worldObj);
		return cachedRenderEntity;
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		if (packet.func_148853_f() == 0)
			entityNBT = packet.func_148857_g().getCompoundTag("EntityNBT");
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setTag("EntityNBT", entityNBT);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setTag("EntityNBT", entityNBT);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		entityNBT = nbt.getCompoundTag("EntityNBT");
	}

	public NBTTagCompound getEntityNBT() {
		return entityNBT;
	}

	public void setEntityNBT(NBTTagCompound entityNBT) {
		this.entityNBT = entityNBT;
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	public void spawnTrappedEntity() {
		if (worldObj.isRemote || entityNBT == null)
			return;

		Entity entity = EntityList.createEntityFromNBT(entityNBT, worldObj);
		entity.setLocationAndAngles(xCoord + 0.5, yCoord, zCoord + 0.5, 0.0F, 0.0F);
		worldObj.spawnEntityInWorld(entity);
	}
}