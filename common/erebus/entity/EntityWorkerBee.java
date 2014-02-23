package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.client.render.entity.AnimationMathHelper;
import erebus.entity.ai.EntityAIPolinate;
import erebus.item.ItemErebusMaterial;

public class EntityWorkerBee extends EntityTameable {
	public ChunkCoordinates currentFlightTarget;
	public float wingFloat;
	private final AnimationMathHelper mathWings = new AnimationMathHelper();
	public boolean beeFlying;
	public boolean beePollinating;
	public boolean beeCollecting;

	public EntityWorkerBee(World world) {
		super(world);
		setSize(1.5F, 1.0F);
		tasks.addTask(1, new EntityAIPolinate(this, 10));
		tasks.addTask(2, new EntityAISwimming(this));
		tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.3D, true));
		tasks.addTask(4, new EntityAITempt(this, 0.5D, Item.sugar.itemID, false));
		tasks.addTask(5, new EntityAIWander(this, 0.4D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(7, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(22, new Integer(0));
		dataWatcher.addObject(23, new Byte((byte) 0));
		dataWatcher.addObject(24, new Integer(0));
		dataWatcher.addObject(25, new Integer(0));
		dataWatcher.addObject(26, new Integer(0));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.75D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}
	
	@Override
	protected boolean canDespawn() {
		if (getTameState() != 0)
			return false;
		else
			return true;
	}
	
	@Override
    public boolean allowLeashing() {
        return !canDespawn() && super.allowLeashing();
    }

	@Override
	protected void fall(float par1) {
	}

	@Override
	protected String getLivingSound() {
		return "erebus:WaspSound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:WaspHurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void playStepSound(int x, int y, int z, int blockID) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		entityDropItem(new ItemStack(ModItems.erebusMaterials, getNectarPoints(), ItemErebusMaterial.dataNectar), 0.0F);
		entityDropItem(new ItemStack(ModItems.erebusMaterials, rand.nextInt(3) + 1, ItemErebusMaterial.dataExoPlate), 0.0F);
	}

	public boolean isFlying() {
		return !onGround;
	}

	@Override
	public void onUpdate() {
		if (!isFlying())
			wingFloat = 0.0F;
		else
			wingFloat = mathWings.swing(4.0F, 0.1F);

		if (motionY < 0.0D)
			motionY *= 0.5D;

		if (!worldObj.isRemote) {
			if (getEntityToAttack() == null && !beePollinating && !beeCollecting) {
				if (rand.nextInt(200) == 0)
					if (!beeFlying)
						setBeeFlying(true);
					else
						setBeeFlying(false);

				if (beeFlying)
					flyAbout();
				else
					land();
			}

			if (getEntityToAttack() != null) {
				currentFlightTarget = new ChunkCoordinates((int) getEntityToAttack().posX, (int) ((int) getEntityToAttack().posY + getEntityToAttack().getEyeHeight()), (int) getEntityToAttack().posZ);
				setBeeFlying(false);
				flyToTarget();
			}
			
			if (getTameState() == 1 && beeCollecting)
				currentFlightTarget = new ChunkCoordinates(getDropPointX(), getDropPointY(),getDropPointZ());
				flyToTarget();
				if((int)posX==getDropPointX() && (int)posY==getDropPointY()+1 && (int)posZ==getDropPointZ() && getNectarPoints() >0) {
					entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataNectar), 0.0F);
					setNectarPoints(getNectarPoints() - 1);
					setBeeCollecting(false);
				}
		}
		super.onUpdate();
	}

	public void setBeeFlying(boolean state) {
		beeFlying = state;
	}

	public void setBeePollinating(boolean state) {
		beePollinating = state;
	}
	
	public void setBeeCollecting(boolean state) {
		beeCollecting = state;	
	}

	public void flyAbout() {
		if (currentFlightTarget != null && currentFlightTarget.posX !=getDropPointX() && currentFlightTarget.posY !=getDropPointY() && currentFlightTarget.posZ !=getDropPointZ() )
			if (!worldObj.isAirBlock(currentFlightTarget.posX, currentFlightTarget.posY, currentFlightTarget.posZ)|| currentFlightTarget.posY < 1)
				currentFlightTarget = null;
		
		if (getTameState()==0)
			if (currentFlightTarget == null|| rand.nextInt(30) == 0 || currentFlightTarget.getDistanceSquared((int) posX, (int) posY, (int) posZ) < 10F)
				currentFlightTarget = new ChunkCoordinates((int) posX + rand.nextInt(3) - rand.nextInt(3), (int) posY + rand.nextInt(6) - 2, (int) posZ + rand.nextInt(3) - rand.nextInt(3));
		
		if (getTameState()==1)
			if (currentFlightTarget == null|| rand.nextInt(30) == 0 || currentFlightTarget.getDistanceSquared((int) posX, (int) posY, (int) posZ) < 10F)
				currentFlightTarget = new ChunkCoordinates(getDropPointX() + rand.nextInt(24) - rand.nextInt(24), getDropPointY() + rand.nextInt(6) - 2, getDropPointZ() + rand.nextInt(24) - rand.nextInt(24));

		flyToTarget();
	}

	public void flyToTarget() {
		if (currentFlightTarget != null && getEntityToAttack() == null &&  isCollidedHorizontally && worldObj.isAirBlock(currentFlightTarget.posX, currentFlightTarget.posY + 1, currentFlightTarget.posZ))
			if(worldObj.getBlockId(currentFlightTarget.posX, currentFlightTarget.posY, currentFlightTarget.posZ) == ModBlocks.erebusFlower.blockID || worldObj.getBlockId(currentFlightTarget.posX, currentFlightTarget.posY, currentFlightTarget.posZ) == ModBlocks.honeyCombBlock.blockID) {
				if (worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(currentFlightTarget.posX, currentFlightTarget.posY + 1, currentFlightTarget.posZ, currentFlightTarget.posX + 1, currentFlightTarget.posY + 2, currentFlightTarget.posZ + 1)).isEmpty())
					setPosition(currentFlightTarget.posX, currentFlightTarget.posY + 1, currentFlightTarget.posZ);
			}
			else {
				currentFlightTarget = null;
				setBeePollinating(false);
				setBeeCollecting(false);
				setBeeFlying(true);
				
				}
		else if (currentFlightTarget != null && getEntityToAttack() == null) {
			double targetX = currentFlightTarget.posX + 0.5D - posX;
			double targetY = currentFlightTarget.posY + 1D - posY;
			double targetZ = currentFlightTarget.posZ + 0.5D - posZ;
			motionX += (Math.signum(targetX) * 0.5D - motionX) * 0.10000000149011612D;
			motionY += (Math.signum(targetY) * 0.699999988079071D - motionY) * 0.10000000149011612D;
			motionZ += (Math.signum(targetZ) * 0.5D - motionZ) * 0.10000000149011612D;
			float angle = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
			float rotation = MathHelper.wrapAngleTo180_float(angle - rotationYaw);
			moveForward = 0.5F;
			rotationYaw += rotation;
		}
	}

	private void land() {
		//Nothing to see here - yet
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack is = player.inventory.getCurrentItem();
		if (!worldObj.isRemote && is != null && is.itemID == ModItems.nectarCollector.itemID)
			if (getNectarPoints() > 0) {
				entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataNectar), 0.0F);
				is.damageItem(1, player);
				setNectarPoints(getNectarPoints() - 1);
				setAttackTarget((EntityLivingBase) null);
				return true;
			}
		
		if (is != null && is.itemID == ModItems.beeTamingAmulet.itemID && is.hasTagCompound() && is.stackTagCompound.hasKey("homeX")) {
			setDropPoint(is.getTagCompound().getInteger("homeX"),is.getTagCompound().getInteger("homeY"),is.getTagCompound().getInteger("homeZ"));
			setTameState((byte) 1);
			playTameEffect(true);
			player.swingItem();
			setAttackTarget((EntityLivingBase) null);
			return true;
		}
		return super.interact(player);
	}
	
	public void setDropPoint(int x, int y, int z) {
		dataWatcher.updateObject(24, Integer.valueOf(x));
		dataWatcher.updateObject(25, Integer.valueOf(y));
		dataWatcher.updateObject(26, Integer.valueOf(z));
	}
	
	public int getDropPointX() {
		return dataWatcher.getWatchableObjectInt(24);
	}
	
	public int getDropPointY() {
		return dataWatcher.getWatchableObjectInt(25);
	}
	
	public int getDropPointZ() {
		return dataWatcher.getWatchableObjectInt(26);
	}

	public void setTameState(byte state) {
		dataWatcher.updateObject(23, Byte.valueOf(state));
	}

	public byte getTameState() {
		return dataWatcher.getWatchableObjectByte(23);
	}

	@Override
	public void setAttackTarget(EntityLivingBase entity) {
		super.setAttackTarget(entity);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
	}

	public void setNectarPoints(int count) {
		dataWatcher.updateObject(22, Integer.valueOf(count));
	}

	public int getNectarPoints() {
		return dataWatcher.getWatchableObjectInt(22);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("nectarPoints", getNectarPoints());
		nbt.setByte("tameState", getTameState());
		nbt.setInteger("dropPointX", getDropPointX());
		nbt.setInteger("dropPointY", getDropPointY());
		nbt.setInteger("dropPointZ", getDropPointZ());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setNectarPoints(nbt.getInteger("nectarPoints"));
		setTameState(nbt.getByte("tameState"));
		setDropPoint(nbt.getInteger("dropPointX"),nbt.getInteger("dropPointY"),nbt.getInteger("dropPointZ"));
	}
}