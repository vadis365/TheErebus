package erebus.entity;

import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityBombardierBeetle extends EntityMob {
	private final float explosionRadius = 2;
	private int collideTick;

	public EntityBombardierBeetle(World world) {
		super(world);
		stepHeight = 1.0F;
		setSize(1.9F, 0.9F);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityBombardierBeetle.AIExplodeAttack(this));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.5D));
		tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.6D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return isNotColliding();
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		collideTick++;
		if (collideTick > 20 || getAttackTarget() == null)
			collideTick = 0;
		if (getAttackTarget() != null)
			if (!getEntityWorld().isRemote && isCollidedHorizontally)
				if (collideTick == 20)
					clearpath();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 60D : 60D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 3D : 3D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.75D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.BOMBARDIER_BEETLE_SOUND;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.BOMBARDIER_BEETLE_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		dropItem(Items.GUNPOWDER, 1);
		dropItem(Items.BLAZE_POWDER, 1);
		int var3 = 1 + rand.nextInt(3) + rand.nextInt(1 + looting);
		for (int a = 0; a < var3; ++a)
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.PLATE_EXO.ordinal()), 0.0F);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (canEntityBeSeen(entity)) {
			boolean hasHitTarget = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));
			if (hasHitTarget) {
				if (getEntityWorld().getGameRules().getBoolean("mobGriefing"))
					getEntityWorld().createExplosion(entity, entity.posX, entity.posY, entity.posZ, 1.0F, false);
			}
			return hasHitTarget;
		}
		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.isExplosion())
			return false;
		return super.attackEntityFrom(source, damage);
	}

	private void clearpath() {
		boolean rule = getEntityWorld().getGameRules().getBoolean("mobGriefing");
		double x = posX;
		double y = posY;
		double z = posZ;
		if (ConfigHandler.INSTANCE.bombardierBlockDestroy == true)
			getEntityWorld().createExplosion(this, x, y + 1, z, explosionRadius, false);
	}
	
	static class AIExplodeAttack extends EntityAIAttackMelee {
		
		public AIExplodeAttack(EntityBombardierBeetle beetle) {
			super(beetle, 0.6D, false);
		}

		@Override
		protected double getAttackReachSqr(EntityLivingBase attackTarget) {
			return (double) (4.0F + attackTarget.width);
		}
	}
}