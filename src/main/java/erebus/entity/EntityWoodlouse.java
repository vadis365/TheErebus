package erebus.entity;

import erebus.Erebus;
import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.core.helper.Utils;
import erebus.items.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityWoodlouse extends EntityCreature {

	public EntityWoodlouse(World world) {
		super(world);
		stepHeight = 0.0F;
		setSize(1.0F, 0.3F);
		setPathPriority(PathNodeType.WATER, -8F);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAvoidEntity(this, EntityPlayer.class, 10.0F, 0.7D, 0.6D));
		tasks.addTask(2, new EntityAIAvoidEntity(this, EntityMob.class, 10.0F, 0.7D, 0.6D));
		tasks.addTask(3, new EntityAIWanderAvoidWater(this, 0.6D));
		tasks.addTask(4, new EntityAIPanic(this, 0.6D));
		tasks.addTask(5, new EntityAILookIdle(this));
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return isNotColliding();
		return super.getCanSpawnHere();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 15D : 15D * ConfigHandler.INSTANCE.mobHealthMultipier);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.BEETLE_HURT;
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
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.inventory.getCurrentItem();
		if (stack.isEmpty()) {
			if (!getEntityWorld().isRemote) {
				setDead();
				Utils.dropStack(getEntityWorld(), getPosition(), new ItemStack(ModItems.WOODLOUSE_BALL, 1));
			}
			return true;
		}
		return false;
	}

	@Override
	public void setDead() {
		super.setDead();

		if (getEntityWorld().isRemote && getHealth() <= 0)
			for (int i = 0; i < 7; i++) {
				double velX = rand.nextGaussian() * 0.02D;
				double velY = rand.nextGaussian() * 0.02D;
				double velZ = rand.nextGaussian() * 0.02D;
				Erebus.PROXY.spawnCustomParticle("smoke", getEntityWorld(), posX + rand.nextFloat() * width * 2.0F - width, posY + 0.5D + rand.nextFloat() * height, posZ + rand.nextFloat() * width * 2.0F - width, velX, velY, velZ);
			}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.IN_WALL) || source.equals(DamageSource.DROWN) || source instanceof EntityDamageSourceIndirect)
			return false;
		return super.attackEntityFrom(source, damage);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		for (int amount = 0; amount < chance; ++amount)
			entityDropItem(ItemMaterials.EnumErebusMaterialsType.WHETSTONE_POWDER.createStack(), 0F);
	}
}