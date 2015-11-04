package erebus.entity;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.entity.ai.EntityAITarantulaMinibossAttack;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityTarantulaMiniboss extends EntityMob implements IBossDisplayData {
	public int deathTicks;

	public EntityTarantulaMiniboss(World world) {
		super(world);
		setSize(4.0F, 1.2F);
		experienceValue = 100;
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAITarantulaMinibossAttack(this, EntityPlayer.class, 0.3D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(17, Byte.valueOf((byte) 1));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.9D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public int getTotalArmorValue() {
		return 8;
	}

	@Override
	public void setInWeb() {
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean isPotionApplicable(PotionEffect potionEffect) {
		return potionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(potionEffect);
	}

	@Override
	protected String getLivingSound() {
		return "mob.spider.say";
	}

	@Override
	protected String getHurtSound() {
		return "mob.spider.say";
	}

	@Override
	protected String getDeathSound() {
		return "mob.spider.death";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (getHealth() <= 150 && getFancyRenderOverlay())
			dataWatcher.updateObject(17, Byte.valueOf((byte) 0));

		if (getHealth() <= 150 && getHealth() > 0 && getAttackTarget() != null) {
			float distance = (float) getDistance(getAttackTarget().posX, getAttackTarget().boundingBox.minY, getAttackTarget().posZ);
			forceCollideWithPlayer(getAttackTarget(), distance);
		}

	}

	@Override
	protected void onDeathUpdate() {
		++deathTicks;

		if (deathTicks % 25 == 1) {
			worldObj.playSoundEffect(posX, posY, posZ, getDeathSound(), 1.0F, 0.1F);
			worldObj.playSoundEffect(posX, posY, posZ, getHurtSound(), 1.0F, 0.1F);
			worldObj.playSoundEffect(posX, posY, posZ, "mob.ghast.scream", 1.0F, 0.1F);
		}

		if (deathTicks >= 180 && deathTicks <= 200)
			PacketPipeline.sendToAllAround(this, 64D, new PacketParticle(this, ParticleType.BOSS_DEATH));

		int i;
		int j;

		if (!worldObj.isRemote)
			if (deathTicks > 150 && deathTicks % 5 == 0) {
				i = 1000;

				while (i > 0) {
					j = EntityXPOrb.getXPSplit(i);
					i -= j;
					worldObj.spawnEntityInWorld(new EntityXPOrb(worldObj, posX, posY, posZ, j));
				}
			}

		moveEntity(0.0D, 0.310000000149011612D, 0.0D);
		renderYawOffset = prevRenderYawOffset += 0.05F;
		limbSwingAmount = 0.5F;
		if (deathTicks == 200 && !worldObj.isRemote) {
			i = 2000;

			while (i > 0) {
				j = EntityXPOrb.getXPSplit(i);
				i -= j;
				worldObj.spawnEntityInWorld(new EntityXPOrb(worldObj, posX, posY, posZ, j));
			}
			worldObj.setBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), ModBlocks.tarantulaEgg);
			Utils.dropStackNoRandom(worldObj, MathHelper.floor_double(posX), MathHelper.floor_double(posY + 1.5), MathHelper.floor_double(posZ), new ItemStack(ModItems.spiderTShirt));
			setDead();
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (getHealth() > 150 && !(source instanceof EntityDamageSourceIndirect))
			return false;
		if (getHealth() <= 150 && source instanceof EntityDamageSourceIndirect)
			return false;
		return super.attackEntityFrom(source, damage);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			if (entity instanceof EntityLiving) {
				byte duration = 0;

				if (worldObj.difficultySetting.ordinal() > EnumDifficulty.EASY.ordinal() && rand.nextInt(19) == 0)
					if (worldObj.difficultySetting == EnumDifficulty.NORMAL)
						duration = 5;
					else if (worldObj.difficultySetting == EnumDifficulty.HARD)
						duration = 10;

				if (duration > 0)
					((EntityLiving) entity).addPotionEffect(new PotionEffect(Potion.poison.id, duration * 20, 0));
			}
			return true;
		} else
			return false;
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData entityLivingData) {
		Object entityLivingData1 = super.onSpawnWithEgg(entityLivingData);

		if (worldObj.rand.nextInt(100) == 0) {
			EntityMoneySpider entityspidermoney = new EntityMoneySpider(worldObj);
			entityspidermoney.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
			entityspidermoney.onSpawnWithEgg((IEntityLivingData) null);
			worldObj.spawnEntityInWorld(entityspidermoney);
			entityspidermoney.mountEntity(this);
		}
		if (entityLivingData1 == null) {
			entityLivingData1 = new EntitySpider.GroupData();
			if (worldObj.difficultySetting == EnumDifficulty.HARD && worldObj.rand.nextFloat() < 0.1F * worldObj.func_147462_b(posX, posY, posZ))
				((EntitySpider.GroupData) entityLivingData1).func_111104_a(worldObj.rand);

			if (entityLivingData1 instanceof EntitySpider.GroupData) {
				int i = ((EntitySpider.GroupData) entityLivingData1).field_111105_a;
				if (i > 0 && Potion.potionTypes[i] != null)
					addPotionEffect(new PotionEffect(i, Integer.MAX_VALUE));
			}
		}
		return (IEntityLivingData) entityLivingData1;
	}

	public boolean getFancyRenderOverlay() {
		return dataWatcher.getWatchableObjectByte(17) == 1;
	}

	public void forceCollideWithPlayer(EntityLivingBase entity, float distance) {
		if (distance > 1.0F && distance < 8.0F)
			if (onGround) {
				getLookHelper().setLookPositionWithEntity(entity, 30.0F, 30.0F);
				faceEntity(entity, 30.0F, 30.0F);
				double distanceX = entity.posX - posX;
				double distanceZ = entity.posZ - posZ;
				float squareRoot = MathHelper.sqrt_double(distanceX * distanceX + distanceZ * distanceZ);
				motionX = distanceX / squareRoot * 0.5D * 0.300000011920929D + motionX * 0.10000000298023224D;
				motionZ = distanceZ / squareRoot * 0.5D * 0.300000011920929D + motionZ * 0.10000000298023224D;
				motionY = 0D;
			}
	}

	@Override
	public void addVelocity(double x, double y, double z) {
		if (getHealth() > 150) {
			motionX = 0;
			motionY += y;
			motionZ = 0;
			isAirBorne = false;
		} else {
			motionX += x;
			motionY += y;
			motionZ += z;
			isAirBorne = true;
		}
	}

	public void spawnBlamParticles() {
		PacketPipeline.sendToAllAround(this, 64D, new PacketParticle(this, ParticleType.TARANTULA_BLAM));
	}
}