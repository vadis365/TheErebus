package erebus.entity;

import java.util.Random;

import javax.annotation.Nullable;

import erebus.Erebus;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLavaWebSpider extends EntityMob {

	private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityLavaWebSpider.class, DataSerializers.BYTE);
	
	public EntityLavaWebSpider(World world) {
		super(world);
		setSize(3F, 1.5F);
		setPathPriority(PathNodeType.LAVA, 8.0F);
		setPathPriority(PathNodeType.DANGER_FIRE, 0.0F);
		setPathPriority(PathNodeType.DAMAGE_FIRE, 0.0F);
		isImmuneToFire = true;
		experienceValue = 10;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(CLIMBING, Byte.valueOf((byte)0));
	}
	
	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityLavaWebSpider.AILavaWebSlingAttack(this));
		tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
		tasks.addTask(3, new EntityAIErebusAttackMelee(this, 0.5D, true));
		tasks.addTask(4, new EntityAIWanderAvoidWater(this, 0.5D));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false, new Class[0]));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 60D : 60D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 2D : 2D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1D);
	}

	@Override
    protected PathNavigate createNavigator(World worldIn) {
        return new PathNavigateClimber(this, worldIn);
    }

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	@Override
	public boolean getCanSpawnHere() {
		return getEntityWorld().checkNoEntityCollision(getEntityBoundingBox()) && getEntityWorld().getCollisionBoxes(this, getEntityBoundingBox()).isEmpty() && getEntityWorld().isMaterialInBB(getEntityBoundingBox(), Material.LAVA);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
        if (!this.world.isRemote)
            this.setBesideClimbableBlock(this.isCollidedHorizontally);

		if (getEntityWorld().isRemote && getEntityWorld().getWorldTime() % 5 == 0)
			lavaParticles(getEntityWorld(), posX, posY + 1.3D, posZ, rand);
	}

	@Override
	public void onLivingUpdate() {
		if (rand.nextInt(50) == 0) {
			int i = MathHelper.floor(posX);
			int j = MathHelper.floor(posY);
			int k = MathHelper.floor(posZ);
			for (int l = 0; l < 4; ++l) {
				i = MathHelper.floor(posX + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
				j = MathHelper.floor(posY);
				k = MathHelper.floor(posZ + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
				BlockPos blockpos = new BlockPos(i, j, k);
				if (getEntityWorld().getBlockState(blockpos).getMaterial() == Material.AIR && Blocks.FIRE.canPlaceBlockAt(getEntityWorld(), blockpos))
					getEntityWorld().setBlockState(blockpos, Blocks.FIRE.getDefaultState());
			}
		}
		super.onLivingUpdate();
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_SPIDER_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_SPIDER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SPIDER_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block) {
		this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
	}

	protected SoundEvent getWebSlingThrowSound() {
		return ModSounds.WEBSLING_THROW;
	}

	@Override
	protected Item getDropItem() {
		return Items.FIRE_CHARGE;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		super.dropFewItems(recentlyHit, looting);
		if (recentlyHit && (rand.nextInt(3) == 0 || rand.nextInt(1 + looting) > 0))
			dropItem(Items.SPIDER_EYE, 1);
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	@Override
	public void setInWeb() {
	}

	@Override
    public boolean isOnLadder() {
        return isBesideClimbableBlock();
    }

    public boolean isBesideClimbableBlock() {
        return (((Byte)dataManager.get(CLIMBING)).byteValue() & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean climbing) {
        byte b0 = ((Byte)dataManager.get(CLIMBING)).byteValue();
        if (climbing)
            b0 = (byte)(b0 | 1);
        else
            b0 = (byte)(b0 & -2);

        this.dataManager.set(CLIMBING, Byte.valueOf(b0));
    }

	@Override
	public boolean isPotionApplicable(PotionEffect potioneffectIn) {
		 return (potioneffectIn.getPotion() == MobEffects.POISON || potioneffectIn.getPotion() == MobEffects.WITHER) ? false : super.isPotionApplicable(potioneffectIn);
	}

	@Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);

		if (getEntityWorld().rand.nextInt(100) == 0) {
			EntityMoneySpider moneyspider = new EntityMoneySpider(getEntityWorld());
			moneyspider.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
			moneyspider.onInitialSpawn(difficulty, (IEntityLivingData) null);
			getEntityWorld().spawnEntity(moneyspider);
			moneyspider.startRiding(this);
		}
		if (livingdata == null) {
			livingdata = new EntitySpider.GroupData();
            if (this.world.getDifficulty() == EnumDifficulty.HARD && this.world.rand.nextFloat() < 0.1F * difficulty.getClampedAdditionalDifficulty())
                ((EntitySpider.GroupData)livingdata).setRandomEffect(this.world.rand);

            if (livingdata instanceof EntitySpider.GroupData) {
                Potion potion = ((EntitySpider.GroupData)livingdata).effect;
                if (potion != null)
                    this.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE));
            }
		}
		return livingdata;
	}

	@SideOnly(Side.CLIENT)
	public void lavaParticles(World world, double x, double y, double z, Random rand) {
		Erebus.PROXY.spawnCustomParticle("lava", getEntityWorld(), x, y, z, 0F, 0F, 0F);
	}
	
	static class AILavaWebSlingAttack extends EntityAIBase {
		private final EntityLavaWebSpider lavaweb_spider;
		private int attackStep;
		private int attackTime;

		public AILavaWebSlingAttack(EntityLavaWebSpider lavaweb_spiderIn) {
			lavaweb_spider = lavaweb_spiderIn;
			setMutexBits(3);
		}

		@Override
		public boolean shouldExecute() {
			EntityLivingBase entitylivingbase = lavaweb_spider.getAttackTarget();
			return entitylivingbase != null && entitylivingbase.isEntityAlive();
		}

		@Override
		public void startExecuting() {
			attackStep = 0;
		}

		@Override
		public void updateTask() {
			--attackTime;
			EntityLivingBase entitylivingbase = lavaweb_spider.getAttackTarget();
			double distance = lavaweb_spider.getDistanceSqToEntity(entitylivingbase);

			if (distance < 4.0D) {
				if (attackTime <= 0) {
					attackTime = 20;
					lavaweb_spider.attackEntityAsMob(entitylivingbase);
				}

				lavaweb_spider.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, lavaweb_spider.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());

			} else if (distance < 256.0D) {
				double targetX = entitylivingbase.posX - lavaweb_spider.posX;
				double targetY = entitylivingbase.getEntityBoundingBox().minY + (double) (entitylivingbase.height / 2.0F) - (lavaweb_spider.posY + (double) (lavaweb_spider.height / 2.0F));
				double targetZ = entitylivingbase.posZ - lavaweb_spider.posZ;

				if (attackTime <= 0) {
					++attackStep;
					if (attackStep == 1)
						attackTime = 60;
					else if (attackStep <= 4)
						attackTime = 6;
					else {
						attackTime = 100;
						attackStep = 0;
					}

					if (attackStep > 1 && entitylivingbase instanceof EntityPlayer) {
						lavaweb_spider.getEntityWorld().playEvent((EntityPlayer) null, 1009, lavaweb_spider.getPosition(), 0);
						lavaweb_spider.getEntityWorld().playSound((EntityPlayer) null, lavaweb_spider.getPosition(), lavaweb_spider.getWebSlingThrowSound(), SoundCategory.HOSTILE, 1.0F, 1.0F);
						for (int count = 0; count < 1; ++count) {
							EntityWebSling webSling = new EntityWebSling(lavaweb_spider.getEntityWorld(), lavaweb_spider);
							webSling.posY = lavaweb_spider.posY + (double) (lavaweb_spider.height / 2.0F) + 0.5D;
							webSling.setType((byte) 2);
							webSling.setThrowableHeading(targetX, targetY, targetZ, 1.0F, 0.0F);
							lavaweb_spider.getEntityWorld().spawnEntity(webSling);
						}
					}
				}
				lavaweb_spider.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
				lavaweb_spider.getNavigator().clearPathEntity();
				lavaweb_spider.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, lavaweb_spider.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
			}
			super.updateTask();
		}
	}
}