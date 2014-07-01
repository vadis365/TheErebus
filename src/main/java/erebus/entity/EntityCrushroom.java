package erebus.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCrushroom extends EntityMob implements IRangedAttackMob {
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.25D, 80, 10.0F);
    private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.3D, false);
    
	public EntityCrushroom(World world) {
		super(world);
		setSize(3.3F, 4F);
		//tasks.addTask(0, new EntityAIArrowAttack(this, 1.25D, 80, 10.0F));
		//tasks.addTask(1, new EntityErebusAIAttackOnCollide(this, EntityPlayer.class, 0.6D, false));
		tasks.addTask(0, new EntityAIWander(this, 0.6D));
		tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(2, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(22, new Byte((byte) 0));
	}
	
	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public void onLivingUpdate() {
		worldObj.spawnParticle("reddust", posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height - 0.25D, posZ + (rand.nextDouble() - 0.5D) * width, 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble());

        if (!worldObj.isRemote && getAttackTarget() != null) {
        	double d1 = getDistance(getAttackTarget().posX, getAttackTarget().boundingBox.minY, getAttackTarget().posZ);
			if (d1 >= 5.0D) {
				setStanding((byte) 0);
				tasks.removeTask(aiAttackOnCollide);
				tasks.addTask(3, aiArrowAttack);
				}
			else if (d1 <= 5.0D) {
			//	setStanding((byte) 1); // attack animation to go here
				tasks.removeTask(aiArrowAttack);
				tasks.addTask(3, aiAttackOnCollide);
			}
        }
	
		super.onLivingUpdate();
	}

	private void setStanding(byte state) {
		dataWatcher.updateObject(22, state);	
	}
	
	public byte getStanding() {
		return dataWatcher.getWatchableObjectByte(22);	
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness(1.0F);
		if (light >= 0F)
			return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 3;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:sporelingliving";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:sporelinghurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:sporelingdeath";
	}
	
	@Override
	protected float getSoundPitch() {
		return 0.1F;
	}

	@Override
	protected Item getDropItem() {
		return Items.bone;
	}
	
	@Override
    public void attackEntityWithRangedAttack(EntityLivingBase entity, float range) {
        EntitySporeBall sporeball = new EntitySporeBall(worldObj, this);
        double distanceX = entity.posX - this.posX;
        double distanceY = entity.posY + height - 4D - sporeball.posY;
        double distanceZ = entity.posZ - this.posZ;
        float height = MathHelper.sqrt_double(distanceX * distanceX + distanceZ * distanceZ) * 0.2F;
        sporeball.setThrowableHeading(distanceX, distanceY + (double)height, distanceZ, 1.6F, 12.0F);
        playSound("erebus:spraycansound", 0.5F, 0.1F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        worldObj.spawnEntityInWorld(sporeball);
    }
}