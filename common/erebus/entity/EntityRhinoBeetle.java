package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.item.ItemErebusMaterial;

public class EntityRhinoBeetle extends EntityTameable {
	private final EntityAINearestAttackableTarget aiNearestAttackableTarget = new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true);
	
	public EntityRhinoBeetle(World world) {
		super(world);
		this.setSize(1.8F, 1.4F);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackOnCollide(this, 0.5D, true));
		tasks.addTask(2, new EntityAIMate(this, 0.5D));
		tasks.addTask(3, new EntityAITempt(this, 0.5D, ModItems.turnip.itemID, false));
		tasks.addTask(5, new EntityAIWander(this, 0.5D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(1, aiNearestAttackableTarget);
		}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(31, new Byte((byte) 0));
	}
	
	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.5D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(40.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(8.0D);
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
	public boolean interact(EntityPlayer player) {
		ItemStack is = player.inventory.getCurrentItem();
		if (is != null && is.itemID == new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataBamboo).itemID && getHasBeenTamed()==0) {
			is.stackSize--;
			setTame((byte) 1);
			tasks.removeTask(aiNearestAttackableTarget);
			setAttackTarget((EntityLivingBase)null);
			return true;
		}
		if (is != null && is.itemID == ModItems.turnip.itemID && !isInLove() && getHasBeenTamed()==1) {
			is.stackSize--;
			inLove = 600;
			return true;
		} else
			return super.interact(player);
	}
	
    public void setAttackTarget(EntityLivingBase entityLivingBase){
        super.setAttackTarget(entityLivingBase);
    }

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (getHasBeenTamed() == 1)
			if(entity instanceof EntityPlayer){
				setAttackTarget((EntityLivingBase)null);
				return false;
			}
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
	
	@Override
	public boolean isBreedingItem(ItemStack is) {
		return is != null && is.itemID == ModItems.turnip.itemID;
	}

	public EntityBeetleLarva spawnBabyAnimal(EntityAgeable entityageable) {
		EntityBeetleLarva entityBeetleLarva = new EntityBeetleLarva(worldObj);
		entityBeetleLarva.setTame((byte) 1);
		return entityBeetleLarva;
	}

	@Override
	protected boolean canDespawn() {
		if (getHasBeenTamed() == 1)
			return false;
		else
			return true;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return spawnBabyAnimal(entityageable);
	}

	public void setTame(byte hasMated) {
		dataWatcher.updateObject(31, Byte.valueOf(hasMated));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("hasMated", getHasBeenTamed());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setTame(nbt.getByte("hasMated"));
	}

	public byte getHasBeenTamed() {
		return dataWatcher.getWatchableObjectByte(31);
	}
}
