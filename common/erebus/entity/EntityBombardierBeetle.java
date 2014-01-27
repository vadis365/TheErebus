package erebus.entity;

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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.core.handler.ConfigurationHandler;
import erebus.entity.ai.EntityAIExplodeAttackOnCollide;
import erebus.item.ItemErebusMaterial;

public class EntityBombardierBeetle extends EntityMob {
	private final float explosionRadius = 1;
	private int collideTick;

	public EntityBombardierBeetle(World world) {
		super(world);
		stepHeight = 1.0F;
		setSize(2.0F, 1.3F);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIExplodeAttackOnCollide(this, EntityPlayer.class, 0.3D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.3D));
		tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		collideTick++;
		if (collideTick > 20 || entityToAttack == null)
			collideTick = 0;
		if (entityToAttack != null)
			if (!worldObj.isRemote && isCollidedHorizontally)
				if (collideTick == 20)
					clearpath();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.0D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(60.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(3.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D); // followRange
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(0.75D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:BombardierBeetleSound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:BombardierBeetleHurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void playStepSound(int x, int y, int z, int par4) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		dropItem(Item.gunpowder.itemID, 1);
		dropItem(Item.blazePowder.itemID, 1);
		entityDropItem(new ItemStack(ModItems.erebusMaterials, rand.nextInt(3) + 1, ItemErebusMaterial.dataExoPlate), 0.0F);
	}

	private void clearpath() {
		boolean rule = worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
		double direction = Math.toRadians(rotationYaw);
		int x = (int) (posX - Math.sin(direction) * 2.0D);
		int y = (int) posY;
		int z = (int) (posZ + Math.cos(direction) * 2.0D);
		if (worldObj.getBlockId(x, y, z) != ModBlocks.reinExoID)
			if (ConfigurationHandler.bombardierBlockDestroy == true) {
				worldObj.createExplosion(this, posX - Math.sin(direction) * 1.5D, posY + 1, posZ + Math.cos(direction) * 1.5D, explosionRadius, rule);
				worldObj.destroyBlock(x, y, z, true);
			}
	}
}
