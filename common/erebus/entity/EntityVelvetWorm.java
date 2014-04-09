package erebus.entity;

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
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.entity.ai.EntityErebusAIAttackOnCollide;

public class EntityVelvetWorm extends EntityMob {

public int skin = rand.nextInt(2);
	
	public EntityVelvetWorm(World world) {
		super(world);
		setSize(2F, 0.7F);
		getNavigator().setAvoidsWater(false);
		experienceValue = 15; 
		fireResistance = 10;
		isImmuneToFire = false;
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityErebusAIAttackOnCollide(this, EntityPlayer.class, 0.7D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 4.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.4D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(20, new Integer(0));
	}
	
	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.4D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(getAttackStrength()); // atkDmg
	}

	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(getAttackTarget() !=null) {
			float distance = (float) getDistance(getAttackTarget().posX, getAttackTarget().boundingBox.minY, getAttackTarget().posZ);
			if(getInflateSize()<100 && distance >3)
				setInflateSize(getInflateSize()+2);
			if(getInflateSize()>=100 && distance >3)
				shootGooBall(getAttackTarget(), distance);
			if(getInflateSize()==0);
				forceCollideWithPlayer(getAttackTarget(), distance);
		}
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}
	
	@Override
	public void setInWeb() {
	}
	
	protected String getWebSlingThrowSound() {
		return "erebus:webslingthrow";
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
	protected void dropFewItems(boolean hit, int looting) {
		int chanceFiftyFifty = rand.nextInt(2) + 1;

		dropItem(Item.slimeBall.itemID, chanceFiftyFifty + looting);
	}

	protected void shootGooBall(Entity entity, float distance) {
		if (distance < 16.0F)
			if (entity instanceof EntityPlayer) {
				worldObj.playSoundAtEntity(this, getWebSlingThrowSound(), 1.0F, 1.0F);
				setInflateSize(0);
				EntityGooBall gooBall = new EntityGooBall(worldObj, this);
				gooBall.posY = posY + height / 2.0F + 0.3D;
				worldObj.spawnEntityInWorld(gooBall);
			}
		}

	public void forceCollideWithPlayer(EntityLivingBase entity, float distance) {
		if (distance > 2.0F && distance < 4.0F) {
			if (onGround) {
				double distanceX = entity.posX - posX;
				double distanceZ = entity.posZ - posZ;
				float squareRoot = MathHelper.sqrt_double(distanceX * distanceX + distanceZ * distanceZ);
				motionX = distanceX / squareRoot * 0.5D * 0.300000011920929D + motionX * 0.10000000298023224D;
				motionZ = distanceZ / squareRoot * 0.5D * 0.300000011920929D + motionZ * 0.10000000298023224D;
				motionY = 0D;
			}
		}	
	}
	
	public void setInflateSize(int size) {
		dataWatcher.updateObject(20, Integer.valueOf(size));
	}
	
	public int getInflateSize() {
		return  dataWatcher.getWatchableObjectInt(20);
	}
}
