package erebus.core.capabilities.player;

import erebus.core.capabilities.base.EntityCapability;
import erebus.lib.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PlayerDeathLocationCapability extends EntityCapability<PlayerDeathLocationCapability, IPlayerDeathLocationCapability, EntityPlayer> implements IPlayerDeathLocationCapability {
	@CapabilityInject(IPlayerDeathLocationCapability.class)
	public static final Capability<IPlayerDeathLocationCapability> CAPABILITY_PLAYER_DEATH_LOCATION = null;

	@Override
	public ResourceLocation getID() {
		return new ResourceLocation(Reference.MOD_ID, "player_death_location");
	}

	@Override
	protected PlayerDeathLocationCapability getDefaultCapabilityImplementation() {
		return new PlayerDeathLocationCapability();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Capability<IPlayerDeathLocationCapability> getCapability() {
		return (Capability<IPlayerDeathLocationCapability>) (Capability<?>) CAPABILITY_PLAYER_DEATH_LOCATION;
	}

	@Override
	protected Class<IPlayerDeathLocationCapability> getCapabilityClass() {
		return IPlayerDeathLocationCapability.class;
	}

	@Override
	public boolean isApplicable(Entity entity) {
		return entity instanceof EntityPlayer;
	}

	@Override
	public int getTrackingTime() {
		return 1;
	}

	@Override
	public boolean isPersistent() {
		return true;
	}

	private int graveDimension;
	private String graveDimensionName = "Over world?";
	private int graveLocationX;
	private int graveLocationZ;

	@Override
	public void setGraveDimension(int dimension) {
		this.graveDimension = dimension;
		this.setDirty(true);
	}

	@Override
	public void setGraveDimensionName(String dimensionName) {
		this.graveDimensionName = dimensionName;
		this.setDirty(true);
	}

	@Override
	public void setGraveLocationX(int locationX) {
		this.graveLocationX = locationX;
		this.setDirty(true);
	}

	@Override
	public void setGraveLocationZ(int locationZ) {
		this.graveLocationZ = locationZ;
		this.setDirty(true);
	}

	@Override
	public int getGraveDimension() {
		return this.graveDimension;
	}
	
	@Override
	public String getGraveDimensionName() {
		return this.graveDimensionName;
	}

	@Override
	public int getGraveLocationX() {
		return this.graveLocationX;
	}

	@Override
	public int getGraveLocationZ() {
		return this.graveLocationZ;
	}

	@Override
	public void writeTrackingDataToNBT(NBTTagCompound nbt) {
		this.writeToNBT(nbt);
	}

	@Override
	public void readTrackingDataFromNBT(NBTTagCompound nbt) {
		this.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("graveDimension", this.graveDimension);
		nbt.setString("graveDimensionName", this.graveDimensionName);
		nbt.setInteger("graveLocationX", this.graveLocationX);
		nbt.setInteger("graveLocationZ", this.graveLocationZ);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.graveDimension = nbt.getInteger("graveDimension");
		this.graveDimensionName = nbt.getString("graveDimensionName");
		this.graveLocationX = nbt.getInteger("graveLocationX");
		this.graveLocationZ = nbt.getInteger("graveLocationZ");
		this.setDirty(true);
	}
}
