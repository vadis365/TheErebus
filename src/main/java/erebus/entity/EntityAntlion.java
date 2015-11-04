package erebus.entity;

import erebus.ModBlocks;
import erebus.entity.ai.EntityErebusAIAttackOnCollide;
import erebus.item.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAntlion extends EntityMob {

	public EntityAntlion(World world) {
		super(world);
		setSize(2.0F, 0.9F);
		isImmuneToFire = true;
		experienceValue = 17;
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityErebusAIAttackOnCollide(this, EntityLivingBase.class, 0.7D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.7D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityFireAnt.class, 0, true));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityFireAntSoldier.class, 0, true));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityBlackAnt.class, 0, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.7D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(35.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.5D);
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public int getTotalArmorValue() {
		return 8;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:antliongrowl";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:antliongrowl";
	}

	@Override
	protected float getSoundPitch() {
		return super.getSoundPitch() * 2F;
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) { // playStepSound
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected Item getDropItem() {
		return Item.getItemFromBlock(Blocks.sand);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(ItemMaterials.DATA.plateExo.makeStack(), 0.0F);
	}

	@Override
	public boolean getCanSpawnHere() {
		return isOnSand() && super.getCanSpawnHere() || isOnGneiss() && super.getCanSpawnHere() || isOnSpawner() && super.getCanSpawnHere();
	}

	public boolean isOnSpawner() {
		return worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ)) == ModBlocks.antlionSpawner;
	}

	public boolean isOnSand() {
		return worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ)) == Blocks.sand;
	}

	public boolean isOnGneiss() {
		return worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ)) == ModBlocks.gneiss;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (findPlayerToAttack() != null)
			entityToAttack = findPlayerToAttack();
		else
			entityToAttack = null;

		if (!worldObj.isRemote && getEntityToAttack() == null && isOnSand())
			yOffset = -1;
		else
			yOffset = 0;
	}

	@Override
	protected Entity findPlayerToAttack() {
		EntityPlayer player = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return player;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.inWall) || source.equals(DamageSource.drown))
			return false;
		return super.attackEntityFrom(source, damage);
	}
}