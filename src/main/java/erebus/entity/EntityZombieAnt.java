package erebus.entity;

import erebus.Erebus;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import erebus.items.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityZombieAnt extends EntityMob {

	public EntityZombieAnt(World world) {
		super(world);
		stepHeight = 1.0F;
		setSize(1.5F, 0.75F);
		setPathPriority(PathNodeType.WATER, -8F);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIErebusAttackMelee(this, 0.4D, false));
		tasks.addTask(2, new EntityAILookIdle(this));
		tasks.addTask(3, new EntityAIWanderAvoidWater(this, 0.6D));
		targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 25D : 25D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 1.5D : 1.5D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}

	@Override
	public int getTotalArmorValue() {
		return 5;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.FIRE_ANT_SOUND;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.FIRE_ANT_HURT;
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
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return isNotColliding();
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 5;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int amount = 1 + rand.nextInt(3) + rand.nextInt(1 + looting);
		for (int a = 0; a < amount; ++a)
			entityDropItem(ItemMaterials.EnumErebusMaterialsType.PLATE_ZOMBIE_ANT.createStack(), 0.0F);
		if (rand.nextInt(5) == 0)
			entityDropItem(ItemMaterials.EnumErebusMaterialsType.ANT_PHEROMONES.createStack(), 0.0F);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (getEntityWorld().isRemote)
			Erebus.PROXY.spawnCustomParticle("spores", getEntityWorld(), posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height - 0.25D, posZ + (rand.nextDouble() - 0.5D) * width, 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble());
	}
}
