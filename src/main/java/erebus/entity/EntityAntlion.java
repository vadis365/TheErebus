package erebus.entity;

import erebus.ModBlocks;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityErebusAIAttackOnCollide;
import erebus.items.ItemMaterials;
import net.minecraft.block.Block;
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
import net.minecraft.util.math.MathHelper;
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
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 35D : 35D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 1D : 1D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.7D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
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
		return Item.getItemFromBlock(Blocks.SAND);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(ItemMaterials.DATA.PLATE_EXO.makeStack(), 0.0F);
	}

	@Override
	public boolean getCanSpawnHere() {
		return isOnSand() && super.getCanSpawnHere() || isOnGneiss() && super.getCanSpawnHere() || isOnSpawner() && super.getCanSpawnHere();
	}

	public boolean isOnSpawner() {
		return getEntityWorld().getBlockState(getPosition().down()).getBlock() == ModBlocks.antlionSpawner;
	}

	public boolean isOnSand() {
		return getEntityWorld().getBlockState(getPosition().down()).getBlock() == Blocks.SAND;
	}

	public boolean isOnGneiss() {
		return getEntityWorld().getBlockState(getPosition().down()).getBlock() == ModBlocks.gneiss;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!getEntityWorld().isRemote && getAttackTarget() == null && isOnSand())
			yOffset = -1;
		else
			yOffset = 0;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.inWall) || source.equals(DamageSource.drown))
			return false;
		return super.attackEntityFrom(source, damage);
	}
}