package erebus.entity;

import java.util.List;

import cpw.mods.fml.common.network.PacketDispatcher;
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
import erebus.network.PacketHandler;
import erebus.network.packet.PacketParticle;

public class EntityRhinoBeetle extends EntityTameable {
	private final EntityAINearestAttackableTarget aiNearestAttackableTarget = new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true);
	
	public EntityRhinoBeetle(World world) {
		super(world);
		this.setSize(2.3F, 1.4F);
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
	public boolean isOnLadder() {
		return riddenByEntity != null && isCollidedHorizontally;
	}
	
	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack is = player.inventory.getCurrentItem();
		if (is != null && is.itemID == ModItems.erebusMaterials.itemID && is.getItemDamage() == 11 && getHasBeenTamed()==0) {
			is.stackSize--;
			setTame((byte) 1);
			playTameEffect(true);
			tasks.removeTask(aiNearestAttackableTarget);
			setAttackTarget((EntityLivingBase)null);
			return true;
		}
		
		if (is != null && is.itemID == ModItems.erebusMaterials.itemID && is.getItemDamage() == 20 && getHasBeenTamed()==1) {
			is.stackSize--;
			setTame((byte) 2);
			return true;
		}
		
		if (is != null && is.itemID == ModItems.turnip.itemID && !isInLove() && getHasBeenTamed()!=0){
			is.stackSize--;
			inLove = 600;
			return true;
		} 
		
		if (is == null && getHasBeenTamed()==2) {
	        if (!this.worldObj.isRemote) {
	            player.mountEntity(this);
	        }
			return true;
		} 		
		else
			return super.interact(player);
	}
	
    @Override
	public void setAttackTarget(EntityLivingBase entityLivingBase){
        super.setAttackTarget(entityLivingBase);
    }

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (getHasBeenTamed() != 0)
			if(entity instanceof EntityPlayer){
				setAttackTarget((EntityLivingBase)null);
				return false;
			}
		return Attack(entity);
	}

	protected boolean Attack(Entity entity) {
		float knockback=1;
		entity.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F + 3);
		entity.addVelocity(-MathHelper.sin(this.rotationYaw * 3.141593F / 180.0F) * knockback, 0.4D, MathHelper.cos(this.rotationYaw * 3.141593F / 180.0F) * knockback);
		this.worldObj.playSoundAtEntity(entity, "damage.fallbig", 1.0F, 1.0F);
		((EntityLivingBase) entity) .addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, this.worldObj.difficultySetting * 50, 0));
		return true;
	}
	
	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (getHasBeenTamed() == 2)
			entityDropItem( new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataRhinoRidingKit), 0.0F);
	}
	
	@Override
	public boolean isBreedingItem(ItemStack is) {
		if (getHasBeenTamed() != 0)
		return is != null && is.itemID == ModItems.turnip.itemID;
		else
			return false;
	}

	public EntityBeetleLarva spawnBabyAnimal(EntityAgeable entityageable) {
		EntityBeetleLarva entityBeetleLarva = new EntityBeetleLarva(worldObj);
		entityBeetleLarva.setTame((byte) 1);
		return entityBeetleLarva;
	}

	@Override
	protected boolean canDespawn() {
		if (getHasBeenTamed() != 0)
			return false;
		else
			return true;
	}
	
	@Override
    public void moveEntityWithHeading(float par1, float par2) {
        if (riddenByEntity != null) {
            this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
            this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
            par1 = ((EntityLivingBase)this.riddenByEntity).moveStrafing * 0.5F;
            par2 = ((EntityLivingBase)this.riddenByEntity).moveForward;

            if (par2 <= 0.0F) {
                par2 *= 0.25F;
            }
            
            this.stepHeight = 1.0F;
            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

            if (!this.worldObj.isRemote) {
                this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
                super.moveEntityWithHeading(par1, par2);
            }
            this.prevLimbSwingAmount = this.limbSwingAmount;
            double d0 = this.posX - this.prevPosX;
            double d1 = this.posZ - this.prevPosZ;
            float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

            if (f4 > 1.0F) {
                f4 = 1.0F;
            }

            this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
            this.limbSwing += this.limbSwingAmount;

        }
        else {
            this.stepHeight = 0.5F;
            this.jumpMovementFactor = 0.02F;
            super.moveEntityWithHeading(par1, par2);
        }
    }
    
	@Override
    public void updateRiderPosition() {
    	super.updateRiderPosition();
    		if (this.riddenByEntity instanceof EntityLivingBase) {
    			double a = Math.toRadians(renderYawOffset);
    			double offSetX = -Math.sin(a) * 0.35D;
    			double offSetZ = Math.cos(a) * 0.35D;
    			riddenByEntity.setPosition(posX - offSetX, posY + 1.3D + riddenByEntity.getYOffset(), posZ - offSetZ);
    }
   }

	@Override
    protected void collideWithEntity(Entity entity) {
		     double x = this.posX - this.prevPosX;
	         double z = this.posZ - this.prevPosZ;
	         float velocity = MathHelper.sqrt_double(x * x + z * z) * 4.0F;
    	if(riddenByEntity != null && (entity instanceof EntityLivingBase) && !(entity instanceof EntityPlayer) && velocity>=4.317)
    		Attack(entity);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return spawnBabyAnimal(entityageable);
	}

	public void setTame(byte tameState) {
		dataWatcher.updateObject(31, Byte.valueOf(tameState));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("tameState", getHasBeenTamed());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setTame(nbt.getByte("tameState"));
	}

	public byte getHasBeenTamed() {
		return dataWatcher.getWatchableObjectByte(31);
	}
}
