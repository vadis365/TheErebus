package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityArmchairMount extends Entity {

	public EntityArmchairMount (World world) {
		super(world);
		setSize(0.0F, 0.0F);
	}

	@Override
	public void updateRiderPosition() {
		super.updateRiderPosition();
		if (riddenByEntity instanceof EntityLivingBase) {
			riddenByEntity.setPosition(posX, posY + 1D, posZ);

			if(getChairAngle() == 2) {
				((EntityLivingBase) riddenByEntity).renderYawOffset = 180F;
				((EntityLivingBase) riddenByEntity).rotationYawHead = 180F;
			}

			if(getChairAngle() == 3) {
				((EntityLivingBase) riddenByEntity).renderYawOffset = 0F;
				((EntityLivingBase) riddenByEntity).rotationYawHead = 0F;
			}

			if(getChairAngle() == 4) {
				((EntityLivingBase) riddenByEntity).renderYawOffset = 90F;
				((EntityLivingBase) riddenByEntity).rotationYawHead = 90F;
			}

			if(getChairAngle() == 5) {
				((EntityLivingBase) riddenByEntity).renderYawOffset = -90F;
				((EntityLivingBase) riddenByEntity).rotationYawHead = -90F;
			}
		}
	}
	
	@Override
	protected void entityInit() {
		dataWatcher.addObject(30, new Byte((byte) 0));
	}

	@Override
	 public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote && riddenByEntity == null)
			setDead();
	}
	
	public void setChairAngle(Byte angle) {
		dataWatcher.updateObject(30, Byte.valueOf(angle));
	}

	public byte getChairAngle() {
		return dataWatcher.getWatchableObjectByte(30);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
	}

}
