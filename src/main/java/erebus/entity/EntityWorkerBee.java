package erebus.entity;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.client.render.entity.AnimationMathHelper;
import erebus.core.helper.Utils;
import erebus.entity.ai.EntityAIPolinate;
import erebus.item.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

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
		tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityWasp.class, 0.3D, true));
		tasks.addTask(5, new EntityAITempt(this, 0.5D, Items.sugar, false));
		tasks.addTask(6, new EntityAIWander(this, 0.4D));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityWasp.class, 0, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(22, new Integer(2));
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
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.75D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness(1.0F);
		if (light >= 0F)
			return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 3;
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
	public boolean isOnLadder() {
		Block block = worldObj.getBlock((int) posX, (int) posY - 1, (int) posZ);
		Block block2 = worldObj.getBlock((int) posX, (int) posY, (int) posZ);
		if (isCollidedHorizontally)
			if (block != ModBlocks.stiga || !block.hasTileEntity(worldObj.getBlockMetadata((int) posX, (int) posY - 1, (int) posZ)))
				if (block2 == ModBlocks.erebusFlower || block2 == Blocks.air) {
					posY += 1;
					return true;
				}
		return false;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:waspsound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:wasphurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		entityDropItem(ItemMaterials.DATA.nectar.makeStack(getNectarPoints() + 2), 0.0F);
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
			motionY *= 0.6D;

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

			if (getTameState() == 1 && beeCollecting) {
				currentFlightTarget = new ChunkCoordinates(getDropPointX(), getDropPointY(), getDropPointZ());
				flyToTarget();
			}

			if (MathHelper.floor_double(posX) == getDropPointX() && MathHelper.floor_double(posY) == getDropPointY() + 1 && MathHelper.floor_double(posZ) == getDropPointZ() && getNectarPoints() > 0) {
				addHoneyToInventory(getDropPointX(), getDropPointY(), getDropPointZ());
				setBeeCollecting(false);
			}
		}
		super.onUpdate();
	}

	private void addHoneyToInventory(int x, int y, int z) {
		if (Utils.addItemStackToInventory(Utils.getTileEntity(worldObj, x, y, z, IInventory.class), ItemMaterials.DATA.nectar.makeStack(2)))
			setNectarPoints(getNectarPoints() - 2);
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
		if (currentFlightTarget != null && currentFlightTarget.posX != getDropPointX() && currentFlightTarget.posY != getDropPointY() && currentFlightTarget.posZ != getDropPointZ())
			if (!worldObj.isAirBlock(currentFlightTarget.posX, currentFlightTarget.posY, currentFlightTarget.posZ) || currentFlightTarget.posY < 1)
				currentFlightTarget = null;

		if (getTameState() == 0)
			if (currentFlightTarget == null || rand.nextInt(30) == 0 || currentFlightTarget.getDistanceSquared((int) posX, (int) posY, (int) posZ) < 10F)
				currentFlightTarget = new ChunkCoordinates((int) posX + rand.nextInt(7) - rand.nextInt(7), (int) posY + rand.nextInt(6) - 2, (int) posZ + rand.nextInt(7) - rand.nextInt(7));

		if (getTameState() == 1)
			if (currentFlightTarget == null || rand.nextInt(30) == 0 || currentFlightTarget.getDistanceSquared((int) posX, (int) posY, (int) posZ) < 10F)
				currentFlightTarget = new ChunkCoordinates(getDropPointX() + rand.nextInt(32) - rand.nextInt(32), getDropPointY() + rand.nextInt(8) - 2, getDropPointZ() + rand.nextInt(32) - rand.nextInt(32));

		flyToTarget();
	}

	public void flyToTarget() {
		if (currentFlightTarget != null && getEntityToAttack() == null && worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY) + 1, MathHelper.floor_double(posZ)) == ModBlocks.erebusFlower && worldObj.isAirBlock(currentFlightTarget.posX, currentFlightTarget.posY + 1, currentFlightTarget.posZ) || currentFlightTarget != null && getEntityToAttack() == null && worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY) + 1, MathHelper.floor_double(posZ)) == ModBlocks.erebusFlower && worldObj.isAirBlock(currentFlightTarget.posX, currentFlightTarget.posY + 2, currentFlightTarget.posZ)) {
			if (worldObj.getBlock(currentFlightTarget.posX, currentFlightTarget.posY, currentFlightTarget.posZ) == ModBlocks.stiga || Utils.getTileEntity(worldObj, currentFlightTarget.posX, currentFlightTarget.posY, currentFlightTarget.posZ, IInventory.class) != null) {
				if (worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(currentFlightTarget.posX, currentFlightTarget.posY + 1, currentFlightTarget.posZ, currentFlightTarget.posX + 1, currentFlightTarget.posY + 2, currentFlightTarget.posZ + 1)).isEmpty())
					setPosition(currentFlightTarget.posX, currentFlightTarget.posY + 1, currentFlightTarget.posZ);
			} else
				currentFlightTarget = null;
		} else if (currentFlightTarget != null) {
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
		// Nothing to see here - yet
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack is = player.inventory.getCurrentItem();
		if (!worldObj.isRemote && is != null && is.getItem() == ModItems.nectarCollector)
			if (getNectarPoints() > 0) {
				entityDropItem(ItemMaterials.DATA.nectar.makeStack(2), 0.0F);
				is.damageItem(1, player);
				setNectarPoints(getNectarPoints() - 2);
				setTarget(null);
				setAttackTarget((EntityLivingBase) null);
				return true;
			}

		if (is != null && is.getItem() == ModItems.beeTamingAmulet && is.hasTagCompound() && is.stackTagCompound.hasKey("homeX")) {
			setDropPoint(is.getTagCompound().getInteger("homeX"), is.getTagCompound().getInteger("homeY"), is.getTagCompound().getInteger("homeZ"));
			setTameState((byte) 1);
			playTameEffect(true);
			player.swingItem();
			setTarget(null);
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
		setTarget(entity);
		super.setAttackTarget(entity);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 4);
	}

	public void setNectarPoints(int count) {
		dataWatcher.updateObject(22, Integer.valueOf(count));
	}

	public int getNectarPoints() {
		return dataWatcher.getWatchableObjectInt(22);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
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
		setDropPoint(nbt.getInteger("dropPointX"), nbt.getInteger("dropPointY"), nbt.getInteger("dropPointZ"));
	}
}