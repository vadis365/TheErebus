package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityVelvetWorm extends EntityMob implements IRangedAttackMob {

	private final EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 0.25F, 20, 60, 15.0F);
	public int skin = rand.nextInt(99);

	public EntityVelvetWorm(World world) {
		super(world);
		setSize(1.35F, 1.0F);
		getNavigator().setAvoidsWater(false);
		experienceValue = 15;
		fireResistance = 10;
		isImmuneToFire = false;
		tasks.addTask(0, new EntityAISwimming(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		tasks.addTask(3, new EntityAILookIdle(this));
		tasks.addTask(5, new EntityAIWander(this, 0.5D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));

		if (world != null && !world.isRemote)
			tasks.addTask(4, aiArrowAttack);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(13, new Byte((byte) 0));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.5D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(getAttackStrength()); // atkDmg
	}

	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	public String getTexture() {
		if (skin <= 10)
			return "/Erebus/Textures/Mob/Velvet worm2.png";
		else
			return "/Erebus/Textures/Mob/Velvet worm.png";
	}

	public double getAttackStrength() {
		switch (worldObj.difficultySetting) {
			default:
				return 4.0D;
			case 1:
				return 4.0D;
			case 2:
				return 5.0D;
			case 3:
				return 6.0D;
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity))
			return true;
		else
			return false;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int chanceFiftyFifty = rand.nextInt(1) + 1;

		dropItem(Item.slimeBall.itemID, chanceFiftyFifty + par2);
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entityLiving, float par2) {
		EntityArrow entityarrow = new EntityArrow(worldObj, this, entityLiving, 1.0F, 14 - worldObj.difficultySetting * 4);
		entityarrow.setDamage(par2 * 2.0F + rand.nextGaussian() * 0.25D + worldObj.difficultySetting * 0.11F);
		worldObj.spawnEntityInWorld(entityarrow);
	}
}
