package erebus.entity;

import erebus.item.ItemMaterials;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCrushroom extends EntityMob implements IRangedAttackMob, IBossDisplayData {

	private final EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 0.75D, 40, 12.0F);

	public EntityCrushroom(World world) {
		super(world);
		setSize(3.3F, 4F);
		tasks.addTask(0, new EntityAIWander(this, 0.5D));
		tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(2, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(21, new Integer(0));
		dataWatcher.addObject(22, new Byte((byte) 0));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public void onLivingUpdate() {
		worldObj.spawnParticle("reddust", posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height - 0.25D, posZ + (rand.nextDouble() - 0.5D) * width, 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble());

		if (!worldObj.isRemote && getAttackTarget() != null) {
			faceEntity(getAttackTarget(), 10.0F, 20.0F);
			double distance = getDistance(getAttackTarget().posX, getAttackTarget().boundingBox.minY, getAttackTarget().posZ);

			if (distance > 5.0D && distance <= 12) {
				tasks.addTask(3, aiArrowAttack);
				if (getSmashCount() >= 1) {
					setSmashCount(getSmashCount() - 1);
					setStanding((byte) 3);
					if (getSmashCount() == 0)
						setStanding((byte) 0);
				}
			}

			if (distance <= 5.0D) {
				tasks.removeTask(aiArrowAttack);
				if (getSmashCount() < 20 && getStanding() != 3) {
					setSmashCount(getSmashCount() + 1);
					setStanding((byte) 2);
				}

				if (getSmashCount() >= 20 && getStanding() == 2) {
					setStanding((byte) 3);
					meleeAttackPlayer();
				}

				if (getSmashCount() >= 1 && getStanding() == 3) {
					setSmashCount(getSmashCount() - 1);
					if (getSmashCount() == 0)
						setStanding((byte) 2);
				}
			}
		}

		if (!worldObj.isRemote && getAttackTarget() == null) {
			setStanding((byte) 1);
			tasks.removeTask(aiArrowAttack);
		}

		super.onLivingUpdate();
	}

	private void setStanding(byte state) {
		dataWatcher.updateObject(22, state);
	}

	public byte getStanding() {
		return dataWatcher.getWatchableObjectByte(22);
	}

	private void setSmashCount(int count) {
		dataWatcher.updateObject(21, count);
	}

	public int getSmashCount() {
		return dataWatcher.getWatchableObjectInt(21);
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
	protected String getLivingSound() {
		return "erebus:sporelingliving";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:sporelinghurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:sporelingdeath";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		playSound("mob.irongolem.walk", 1.0F, 0.5F);
	}

	@Override
	protected float getSoundPitch() {
		return 0.1F;
	}

	@Override
	protected void dropFewItems(boolean recentHit, int fortune) {
		int amount = rand.nextInt(3);
		if (fortune > 0)
			amount += rand.nextInt(fortune + 1);
		entityDropItem(ItemMaterials.DATA.hideShroom.makeStack(amount), 0.0F);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean canAttackClass(Class entity) {
		return EntityCrushroom.class != entity && EntityCrushling.class != entity && EntityZombieAnt.class != entity && EntityPunchroom.class != entity;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entity, float range) {
		EntitySporeBall sporeball = new EntitySporeBall(worldObj, this);
		double distanceX = entity.posX - posX;
		double distanceY = entity.posY + height - 4D - sporeball.posY;
		double distanceZ = entity.posZ - posZ;
		float height = MathHelper.sqrt_double(distanceX * distanceX + distanceZ * distanceZ) * 0.2F;
		sporeball.setThrowableHeading(distanceX, distanceY + height, distanceZ, 1.6F, 12.0F);
		playSound("erebus:spraycansound", 0.5F, 0.1F / (getRNG().nextFloat() * 0.4F + 0.8F));
		worldObj.spawnEntityInWorld(sporeball);
	}

	private void meleeAttackPlayer() {
		if (!worldObj.isRemote && getAttackTarget().boundingBox.maxY >= boundingBox.minY - 1.0D && getAttackTarget().boundingBox.minY <= boundingBox.maxY && getSmashCount() == 20) {
			playSound("erebus:blamsound", 0.5F, 1.0F);
			spawnBlamParticles();
			getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), 6.0F);
			getAttackTarget().addVelocity(-MathHelper.sin(rotationYaw * 3.141593F / 180.0F) * 0.5D, 0.2D, MathHelper.cos(rotationYaw * 3.141593F / 180.0F) * 0.5D);
		}
	}

	public void spawnBlamParticles() {
		PacketPipeline.sendToAllAround(this, 64D, new PacketParticle(this, ParticleType.CRUSHROOM_BLAM));
	}
}