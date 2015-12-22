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
		}
	}

	@Override
	 public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote && riddenByEntity == null)
			setDead();
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
	}

}
