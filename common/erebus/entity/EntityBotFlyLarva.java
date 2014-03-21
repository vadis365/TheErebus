package erebus.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBotFlyLarva extends EntityMob {
	public EntityBotFlyLarva(World world) {
		super(world);
		setSize(0.5F, 0.2F);
		isImmuneToFire = true;
		tasks.addTask(0, new EntityAIWander(this, 0.3D));
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Byte((byte) 1));
		dataWatcher.addObject(17, "");
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(8.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.6000000238418579D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(0.0D);
	}
	
	@Override
	public boolean isAIEnabled() {
		return true;
	}
	
	@Override
    public boolean canBeCollidedWith() {
        return false;
    }

	@Override
	protected String getLivingSound() {
		return "mob.silverfish.say";
	}

	@Override
	protected String getHurtSound() {
		return "mob.silverfish.hit";
	}

	@Override
	protected String getDeathSound() {
		return "mob.silverfish.kill";
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		if (!player.capabilities.isCreativeMode && !worldObj.isRemote)
			if (player.riddenByEntity==null) {
				mountEntity(player);
				setPosition(player.posX, player.posY+ ridingEntity.getYOffset(), player.posZ);
			setPersistanceOnPlayer(player.getEntityName());
			}
		setRotation(player.renderYawOffset, player.rotationPitch);
	}
	

	@Override
	public double getYOffset() {
		if (ridingEntity != null && ridingEntity instanceof EntityPlayer)
			return -2D;
		else if (ridingEntity != null)
			return ridingEntity.height * 0.75D - 1.0D;
		else
			return yOffset;
	}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4) {
		playSound("mob.silverfish.step", 0.15F, 1.0F);
	}

	@Override
	public void onUpdate() {
		renderYawOffset = rotationYaw;
		if (!worldObj.isRemote)
			if (ridingEntity != null && ridingEntity instanceof EntityPlayer && getParasiteCount()>0 && rand.nextInt(180/getParasiteCount()) == 0) {
				byte duration = (byte) (getParasiteCount()*5);
				((EntityLivingBase) ridingEntity).addPotionEffect(new PotionEffect(Potion.weakness.id, duration * 20, 0));
				((EntityLivingBase) ridingEntity).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, duration * 20, 0));
				((EntityLivingBase) ridingEntity).addPotionEffect(new PotionEffect(Potion.hunger.id, duration * 20, 0));
		}
		if(getParasiteCount()==0) {
			setDead();
		}
		super.onUpdate();
	}

	public void setABitDead() {
		worldObj.playSoundEffect(posX, posY, posZ, getDeathSound(), 1.0F, 0.7F);
		if (worldObj.isRemote)
			worldObj.spawnParticle("smoke", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
		if (!worldObj.isRemote)
			entityDropItem(new ItemStack(Item.slimeBall), 0.0F);
		setParasiteCount((byte) (getParasiteCount()-1));
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.inWall) || source.equals(DamageSource.drown))
			return false;
		return super.attackEntityFrom(source, damage);
	}
	
	public void setParasiteCount(byte parasites) {
		dataWatcher.updateObject(16, Byte.valueOf(parasites));
	}
	
	public byte getParasiteCount() {
		return  dataWatcher.getWatchableObjectByte(16);
	}
	
	private void setPersistanceOnPlayer(String entityName) {
		dataWatcher.updateObject(17, ""+entityName);	
	}
	
	public String getPersistanceOnPlayer() {
		 return  dataWatcher.getWatchableObjectString(17);	
	}
	
    public EntityLivingBase playerName() {
        return this.worldObj.getPlayerEntityByName(getPersistanceOnPlayer());
    }
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setParasiteCount(nbt.getByte("parasites"));
		setPersistanceOnPlayer(nbt.getString("playerName"));
		if(((EntityPlayer) playerName()!=null)) {
			EntityPlayer player = ((EntityPlayer) playerName());
			if (!player.capabilities.isCreativeMode && !worldObj.isRemote)
				if (player.riddenByEntity==null) {
					mountEntity(player);
					setPosition(player.posX, player.posY+ ridingEntity.getYOffset(), player.posZ);
				}
			setRotation(player.renderYawOffset, player.rotationPitch);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("parasites", getParasiteCount());
		nbt.setString("playerName", getPersistanceOnPlayer());
	}

}
