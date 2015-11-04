package erebus.entity;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import erebus.ModItems;
import erebus.client.render.entity.AnimationMathHelper;
import erebus.item.ItemMaterials;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityWasp extends EntityMob implements IEntityAdditionalSpawnData {

	public ChunkCoordinates currentFlightTarget;
	public float wingFloat;
	AnimationMathHelper mathWings = new AnimationMathHelper();
	private boolean areAttributesSetup = false;
	public boolean waspFlying;
	public final EntityAINearestAttackableTarget aiNearestAttackableTarget = new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true);
	public final EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityLivingBase.class, 0.3D, true);

	public EntityWasp(World world) {
		super(world);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, aiAttackOnCollide);
		tasks.addTask(2, new EntityAIWander(this, 0.4D));
		tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(4, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(1, aiNearestAttackableTarget);
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityGrasshopper.class, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityBeetle.class, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityBeetleLarva.class, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWorkerBee.class, 0, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(25, new Byte((byte) rand.nextInt(32)));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		areAttributesSetup = true;
		updateBossAttributes();
	}

	protected void updateBossAttributes() {
		if (worldObj != null && !worldObj.isRemote)
			if (getIsBoss() == 1) {
				setSize(2.5F, 2F);
				experienceValue = 25;
				getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.9D);
				getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0D);
				getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
				getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
			} else {
				setSize(1.5F, 1.0F);
				experienceValue = 10;
				getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.75D);
				getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
				getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
				getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
			}
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
	protected void fall(float par1) {
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
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(ItemMaterials.DATA.waspSting.makeStack(), 0.0F);
		if (getIsBoss() == 1)
			entityDropItem(new ItemStack(ModItems.bottleAntiVenom), 0.0F);
	}

	public boolean isFlying() {
		return !onGround;
	}

	public void setWaspFlying(boolean state) {
		waspFlying = state;
	}

	@Override
	public void onUpdate() {
		byte i;
		if (worldObj.isRemote) {
			i = getIsBoss();
			if (i == 1) {
				setSize(2.5F, 2F);
				if (!hasCustomNameTag())
					if (rand.nextBoolean())
						setCustomNameTag("Livid's Bane");
					else
						setCustomNameTag("Hornet of Despair");
			} else
				setSize(1.5F, 1.0F);
		}

		if (!isFlying())
			wingFloat = 0.0F;
		else
			wingFloat = mathWings.swing(4.0F, 0.1F);

		if (motionY < 0.0D)
			motionY *= 0.6D;

		if (!worldObj.isRemote) {
			if (getEntityToAttack() == null) {
				if (rand.nextInt(200) == 0)
					if (!waspFlying)
						setWaspFlying(true);
					else
						setWaspFlying(false);

				if (waspFlying)
					flyAbout();
				else
					land();
			}

			if (getEntityToAttack() != null) {
				currentFlightTarget = new ChunkCoordinates((int) getEntityToAttack().posX, (int) ((int) getEntityToAttack().posY + getEntityToAttack().getEyeHeight()), (int) getEntityToAttack().posZ);
				setWaspFlying(false);
				flyToTarget();
			}
		}
		super.onUpdate();
	}

	public void flyAbout() {
		if (currentFlightTarget != null)
			if (!worldObj.isAirBlock(currentFlightTarget.posX, currentFlightTarget.posY, currentFlightTarget.posZ) || currentFlightTarget.posY < 1)
				currentFlightTarget = null;

		if (currentFlightTarget == null || rand.nextInt(30) == 0 || currentFlightTarget.getDistanceSquared((int) posX, (int) posY, (int) posZ) < 10F)
			currentFlightTarget = new ChunkCoordinates((int) posX + rand.nextInt(7) - rand.nextInt(7), (int) posY + rand.nextInt(6) - 2, (int) posZ + rand.nextInt(7) - rand.nextInt(7));

		flyToTarget();
	}

	public void flyToTarget() {
		if (currentFlightTarget != null) {
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
	public void setAttackTarget(EntityLivingBase entity) {
		setTarget(entity);
		super.setAttackTarget(entity);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			if (entity instanceof EntityLivingBase) {
				byte var2 = 0;
				if (worldObj.difficultySetting == EnumDifficulty.NORMAL)
					var2 = 7;
				else if (worldObj.difficultySetting == EnumDifficulty.HARD)
					var2 = 15;
				if (var2 > 0)
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 20, 0));
			}
			return true;
		}
		return false;
	}

	@Override
	protected void attackEntity(Entity entity, float par2) {
		if (par2 < 1.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY && getEntitySenses().canSee(entity))
			attackEntityAsMob(entity);
	}

	public byte getIsBoss() {
		return dataWatcher.getWatchableObjectByte(25);
	}

	public void setIsBoss(byte boss) {
		dataWatcher.updateObject(25, Byte.valueOf(boss));
		worldObj.setEntityState(this, (byte) 25);
		if (areAttributesSetup)
			updateBossAttributes();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("mobType", getIsBoss());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setIsBoss(nbt.getByte("mobType"));
	}

	@Override
	public void writeSpawnData(ByteBuf data) {
		data.writeByte(getIsBoss());
	}

	@Override
	public void readSpawnData(ByteBuf data) {
		setIsBoss(data.readByte());
	}
}