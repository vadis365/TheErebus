package erebus.entity;

import erebus.item.ItemMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntitySnapper extends EntityMob {
	public EntitySnapper(World world) {
		super(world);
		stepHeight = 0.0F;
		setSize(1.0F, 0.8F);
		tasks.addTask(0, new EntityAILeapAtTarget(this, 0.5F));
		tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0, true));
		targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(20, 0F);
		dataWatcher.addObject(21, 0F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public boolean attackEntityAsMob(Entity player) {
		player.attackEntityFrom(DamageSource.cactus, 4);
		((EntityLivingBase) player).addPotionEffect(new PotionEffect(Potion.poison.id, 50, 0));
		((EntityLivingBase) player).addPotionEffect(new PotionEffect(Potion.confusion.id, 50, 0));
		return true;
	}

	@Override
	public void onUpdate() {
		rotationYaw = prevRotationYaw = 0;
		if (!worldObj.isRemote && getAttackTarget() != null) {
			double distance = getDistance(getAttackTarget().posX, getAttackTarget().boundingBox.minY, getAttackTarget().posZ);
			float rot = getAttackTarget().rotationYaw;
			dataWatcher.updateObject(20, rot);
			if (distance <= 4.0D && dataWatcher.getWatchableObjectFloat(21) < 1.0F)
				dataWatcher.updateObject(21, dataWatcher.getWatchableObjectFloat(21) + 0.1F);
			if (distance > 4.0D && dataWatcher.getWatchableObjectFloat(21) > 0F)
				dataWatcher.updateObject(21, dataWatcher.getWatchableObjectFloat(21) - 0.1F);
		}
		super.onUpdate();
	}

	@Override
	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0 && !worldObj.isAnyLiquid(boundingBox);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 6;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(3) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(ItemMaterials.DATA.snapperRoot.makeStack(), 0.0F);
	}
}
