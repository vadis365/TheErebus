package erebus.entity;

import javax.annotation.Nullable;

import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import erebus.entity.ai.PathNavigateAboveWater;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import erebus.world.ChunkProviderErebus;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityPondSkater extends EntityMob {

	public EntityPondSkater(World world) {
		super(world);
		setSize(1.0F, 0.8F);
		stepHeight = 0.0F;
		setPathPriority(PathNodeType.WALKABLE, -8.0F);
		setPathPriority(PathNodeType.BLOCKED, -8.0F);
		setPathPriority(PathNodeType.LAVA, -8.0F);
		setPathPriority(PathNodeType.WATER, 16.0F);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(1, new EntityAIErebusAttackMelee(this, 0.5D, true));
		tasks.addTask(2, new EntityPondSkater.AIWaterWander(this, 0.5D, 10));
		tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(4, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.2D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 15D : 15D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 2D : 2D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}
	
	@Override
    protected PathNavigate createNavigator(World world) {
		return new PathNavigateAboveWater(this, world); 
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean getCanSpawnHere() {
		int y = MathHelper.floor(getEntityBoundingBox().minY);
		if(y <= ChunkProviderErebus.swampWaterHeight)
			return getEntityWorld().checkNoEntityCollision(getEntityBoundingBox()) && getEntityWorld().getCollisionBoxes(this, getEntityBoundingBox()).isEmpty() && getEntityWorld().isMaterialInBB(getEntityBoundingBox(), Material.WATER);
		return false;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 6;
	}

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
		playSound(SoundEvents.ENTITY_GENERIC_SWIM, 0.125F, 0.125F);
    }

	/*
	 * To stop console spam
	 * 
	 * @Override protected String getLivingSound() { return ""; }
	 * 
	 * @Override protected String getHurtSound() { return ""; }
	 */

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.HYDROFUGE.ordinal()), 0.0F);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		int x = MathHelper.floor(posX);
		int y = MathHelper.floor(getEntityBoundingBox().minY);
		int z = MathHelper.floor(posZ);

		if (getEntityWorld().getBlockState(new BlockPos(x, y, z)).getMaterial() == Material.WATER) {
			if (motionY < 0.0D)
				motionY = 0.0D;
			fallDistance = 0.0F;
		}

		if (!getEntityWorld().isRemote)
			if (getAttackTarget() != null && !getAttackTarget().isInWater())
				setAttackTarget(null);
		
		if(isInWater())
			getMoveHelper().setMoveTo(this.posX, this.posY + 1, this.posZ, 0.32D);
	}

	public boolean isEntityInWater(EntityLivingBase entity) {
		return entity.isInWater();
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (canEntityBeSeen(entity) && entity.isInWater()) {
			if (super.attackEntityAsMob(entity)) {
				if (entity instanceof EntityLivingBase) {
					byte duration = 0;
					if (getEntityWorld().getDifficulty() == EnumDifficulty.NORMAL)
						duration = 7;
					else if (getEntityWorld().getDifficulty() == EnumDifficulty.HARD)
						duration = 15;
					if (duration > 0) {
						((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, duration * 20, 0));
						((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, duration * 20, 0));
					}
				}
			}
			return true;
		} else
			return false;
	}

	public class AIWaterWander extends EntityAIWander {

		private final EntityPondSkater skater;

		public AIWaterWander(EntityPondSkater skaterIn, double speedIn, int chance) {
			super(skaterIn, speedIn, chance);
			setMutexBits(1);
			this.skater = skaterIn;
		}

		@Nullable
		@Override
		protected Vec3d getPosition() {
			return RandomPositionGenerator.findRandomTarget(skater, 16, 0);
		}
	}
}
