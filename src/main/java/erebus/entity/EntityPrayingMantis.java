package erebus.entity;

import erebus.entity.ai.EntityErebusAIAttackOnCollide;
import erebus.item.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityPrayingMantis extends EntityMob {

	private int attackAnimation;

	public EntityPrayingMantis(World world) {
		super(world);
		isImmuneToFire = true;
		setSize(2.0F, 2.5F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityErebusAIAttackOnCollide(this, EntityPlayer.class, 0.6D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.3D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(20, 0.0F);
		dataWatcher.addObject(22, new Byte((byte) 0));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(24.0D);
	}

	@Override
	protected String getLivingSound() {
		return "erebus:mantissound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:mantishurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		worldObj.playSoundAtEntity(this, "mob.zombie.step", 0.15F, 1.0F);
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(ItemMaterials.DATA.camoPowder.makeStack(), 0.0F);
	}

	@Override
	public void onUpdate() {
		if (!worldObj.isRemote && getAttackTarget() != null) {
			double d1 = getDistance(getAttackTarget().posX, getAttackTarget().boundingBox.minY, getAttackTarget().posZ);
			if (d1 >= 4.0D)
				dataWatcher.updateObject(20, (24.0F - (float) d1) * 0.0208333F);
			if (d1 < 4.0D)
				dataWatcher.updateObject(20, (24.0F - (float) d1) * 0.041666F);
		}
		if (!worldObj.isRemote && getAttackTarget() == null || dataWatcher.getWatchableObjectFloat(20) > 0.8F)
			dataWatcher.updateObject(20, 1.0F);
		if (!worldObj.isRemote) {
			if (attackAnimation < 5 && dataWatcher.getWatchableObjectByte(22) == 0)
				setAttackAnimation(attackAnimation + 1, (byte) 0);
			if (attackAnimation == 5)
				setAttackAnimation(0, (byte) 1);

		}
		super.onUpdate();
	}

	public void setAttackAnimation(int count, byte action) {
		attackAnimation = count;
		dataWatcher.updateObject(22, action);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		dataWatcher.updateObject(20, nbt.getFloat("Alpha"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setFloat("Alpha", dataWatcher.getWatchableObjectFloat(20));
	}
}