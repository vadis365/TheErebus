package erebus.entity;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.ModBiomes;
import erebus.ModBlocks;

public class EntitySporeling extends EntityAnimal { 
	private boolean areParticlesActive;
	private Random random;

	
    public EntitySporeling(World world) {
        super(world);
        setSize(0.5F, 0.3F);
        stepHeight = 0.0F;
        this.getNavigator().setBreakDoors(true);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityPlayer.class, 10.0F, 0.4D, 0.5D));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.4D));
        this.tasks.addTask(6, new EntityAIPanic(this, 0.6D));
        this.tasks.addTask(7, new EntityAITempt(this, 0.4D, Item.wheat.itemID, false));
        this.tasks.addTask(8, new EntityAIWander(this, 0.4D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(10, new EntityAILookIdle(this));    
    }
    
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D); 
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(5.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}

	@Override
    public boolean isAIEnabled() {
        return true;
    }

	@Override
    public void onLivingUpdate() {
    	worldObj.spawnParticle("reddust", posX + (rand.nextDouble() - 0.5D) * width, (posY + rand.nextDouble() * height) - 0.25D, posZ + (rand.nextDouble() - 0.5D) * width, 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble());
    	
         if (rand.nextInt(200) == 0) {
        	 int x = MathHelper.floor_double(this.posX);
        	 int y = MathHelper.floor_double(this.posY);
             int z = MathHelper.floor_double(this.posZ);
             
        	 if (this.worldObj.getBlockId(x, y, z) == 0 && this.worldObj.getBiomeGenForCoords(x, z).biomeID == ModBiomes.fungalForestID && Block.mushroomBrown.canPlaceBlockAt(this.worldObj, x, y, z)) {
        		 int mush = rand.nextInt(3);
        		 if (mush != 0)
        			 this.worldObj.setBlock(x, y, z, Block.mushroomBrown.blockID + rand.nextInt(2));
        		 else
        			 this.worldObj.setBlock(x, y, z, ModBlocks.erebusPlantSmall.blockID,rand.nextInt(5), 3);
        		 }
         }
    	super.onLivingUpdate();
    }

	@Override
	public boolean getCanSpawnHere() {
		float light = this.getBrightness(1.0F);
		if (light >= 0F) {
			return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(this.boundingBox);
	    }
	    return super.getCanSpawnHere();
	}
    
	@Override
    public int getMaxSpawnedInChunk() {
    	return 3;
	}

	@Override
    protected String getLivingSound(){
        	return "erebus:sporelingLiving";
    }

	@Override
    protected String getHurtSound() {
        	return "erebus:sporelingHurt";
    }

	@Override
    protected String getDeathSound() {
    	return "erebus:sporelingDeath";
    }

	@Override
    protected int getDropItemId() {
        return Item.bone.itemID;
    }

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
    	if(source.getSourceOfDamage() instanceof EntityPlayer) {
    		EntityPlayer entityPlayer = (EntityPlayer)source.getSourceOfDamage();
    		if(entityPlayer.getCurrentEquippedItem() != null && entityPlayer.getCurrentEquippedItem().itemID == 0) {
	    		return super.attackEntityFrom(source, damage);
			}
    	}
    		return super.attackEntityFrom(source, damage);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable child) {
		return null;
	}
}
