package erebus.entity;

import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIExplodeAttackOnCollide;
import erebus.item.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBombardierBeetle extends EntityMob {
	private final float explosionRadius = 2;
	private int collideTick;

	public EntityBombardierBeetle(World world) {
		super(world);
		stepHeight = 1.0F;
		setSize(1.9F, 0.9F);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIExplodeAttackOnCollide(this, EntityPlayer.class, 0.3D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.3D));
		tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
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
			if (!worldObj.isRemote && isCollidedHorizontally)
				if (collideTick == 20)
					clearpath();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D); // followRange
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.75D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:bombardierbeetlesound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:bombardierbeetlehurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		dropItem(Items.gunpowder, 1);
		dropItem(Items.blaze_powder, 1);
		int var3 = 1 + rand.nextInt(3) + rand.nextInt(1 + looting);
		for (int a = 0; a < var3; ++a)
			entityDropItem(ItemMaterials.DATA.plateExo.makeStack(), 0.0F);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.isExplosion())
			return false;
		return super.attackEntityFrom(source, damage);
	}

	private void clearpath() {
		boolean rule = worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
		double x = posX;
		double y = posY;
		double z = posZ;
		if (ConfigHandler.INSTANCE.bombardierBlockDestroy == true)
			worldObj.createExplosion(this, x, y + 1, z, explosionRadius, rule);
	}
}