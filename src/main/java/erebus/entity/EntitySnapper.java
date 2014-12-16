package erebus.entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntitySnapper extends EntityMob
{ 
	private boolean sucks = false;
	public static boolean bite = true;

    public EntitySnapper(World world)
    {
        super(world);
        stepHeight = 0.0F; 
        setSize(1.0F, 0.1F);
        this.renderDistanceWeight = 64;
        tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLiving.class, 0, true));
        tasks.addTask(2, new EntityAILeapAtTarget(this, 0.6F));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }
    
	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(20, 0F);
	}
    
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.1D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}
	
	@Override
	public boolean isAIEnabled() {
		return true;
	}
    
    @Override 
    protected boolean isMovementCeased()
    {
        return true;
    }
    
    @Override
    public boolean attackEntityAsMob(Entity player)
    {
    	bite = true;
    	sucks = true;
    	player.attackEntityFrom(DamageSource.cactus, 4);
    	((EntityLivingBase)player).addPotionEffect(new PotionEffect(Potion.poison.id, 50, 0));
        ((EntityLivingBase)player).addPotionEffect(new PotionEffect(Potion.confusion.id, 50, 0));
    	return true;
    }
    
	@Override
	public void onUpdate() {
		if (!worldObj.isRemote && getAttackTarget() != null) {
			double d1 = getDistance(getAttackTarget().posX, getAttackTarget().boundingBox.minY, getAttackTarget().posZ);
				float rot = getAttackTarget().rotationYaw;
				dataWatcher.updateObject(20, rot);
				if (d1 <= 4.0D)
					bite = true;
				if (d1 > 4.0D)
					bite = false;
		}
		super.onUpdate();
	}
    
    @Override
    public boolean getCanSpawnHere()
    {
    	return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0 && !worldObj.isAnyLiquid(boundingBox);
    }
    
    @Override 	 
    public int getMaxSpawnedInChunk()
	{
	return 3;
	}
    
    @Override
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
    	bite = true;
    	return true;
    }
  /* 
    @Override
    public void onLivingUpdate()
    {
    	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    	if(sucks)
    	{
    		if(player.posX < posX){player.motionX = 0.3D;}
        	if(player.posX > posX){player.motionX = -0.3D;}
        	
        	if(player.posZ < posZ){player.motionZ = 0.3D;}
        	if(player.posZ > posZ){player.motionZ = -0.3D;}
        	
        	if(player.posX > posX - 0.2 && player.posX < posX + 0.2 && player.posZ > posZ - 0.2 && player.posZ < posZ + 0.2)
        	{
        		sucks = false;
        	}
        	if(player.posX > posX + 1 || player.posX < posX - 1 || player.posZ > posZ + 1 || player.posZ < posZ - 1)
        	{
        		sucks = false;
        	}
    	}
        if (this.newPosRotationIncrements > 0)
        {
            double var1 = this.posX + (this.newPosX - this.posX) / (double)this.newPosRotationIncrements;
            double var3 = this.posY + (this.newPosY - this.posY) / (double)this.newPosRotationIncrements;
            double var5 = this.posZ + (this.newPosZ - this.posZ) / (double)this.newPosRotationIncrements;
            this.setPosition(var1, var3, var5);
            List var9 = this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox.contract(0.03125D, 0.0D, 0.03125D));

            if (var9.size() > 0)
            {
                double var10 = 0.0D;

                for (int var12 = 0; var12 < var9.size(); ++var12)
                {
                    AxisAlignedBB var13 = (AxisAlignedBB)var9.get(var12);

                    if (var13.maxY > var10)
                    {
                        var10 = var13.maxY;
                    }
                }

                var3 += var10 - this.boundingBox.minY;
                this.setPosition(var1, var3, var5);
            }
        }

        List var4 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));

        if (var4 != null && var4.size() > 0)
        {
            for (int var16 = 0; var16 < var4.size(); ++var16)
            {
                Entity var6 = (Entity)var4.get(var16);

                if (var6.canBePushed())
                {
                	var6.setInWeb();
                }
            }
        }
        super.onLivingUpdate();
    } 
    */
    //TODO add sound when it traps somthing
/*  protected String getLivingSound()
    {
        return "Betweenlands.WightMoan";
    }

    protected String getHurtSound()
    {
        return "Betweenlands.WightHurt";
    }

    protected String getDeathSound()
    {
    	return "Betweenlands.WightDeath";
    }*/

    protected int getDropItemId()
    {
        return 0;
    }

}
