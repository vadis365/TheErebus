package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityRhinoBeetle extends EntityMob {

	public EntityRhinoBeetle(World par1World) {
		super(par1World);
		this.setSize(1.8F, 1.4F);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.0D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(40.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(3.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(0.75D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:rhinobeetlesound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:rhinobeetlehurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void playStepSound(int x, int y, int z, int blockID) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected int getDropItemId() {
		return Item.ingotIron.itemID;
	}

	@Override
	protected void dropRareDrop(int par1) {
		this.dropItem(Item.ghastTear.itemID, 1);
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
	public boolean attackEntityAsMob(Entity entity) {
		return Attack(entity);
	}

	protected boolean Attack(Entity entity) {
		float Knockback = 1;
		entity.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F + 3);
		entity.addVelocity(-MathHelper.sin(this.rotationYaw * 3.141593F / 180.0F) * Knockback, 0.4D, MathHelper.cos(this.rotationYaw * 3.141593F / 180.0F) * Knockback);
		this.worldObj.playSoundAtEntity(entity, "damage.fallbig", 1.0F, 1.0F);
		((EntityLivingBase) entity) .addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, this.worldObj.difficultySetting * 50, 0));
		return true;
	}
}
