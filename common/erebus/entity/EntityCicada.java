 package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCicada extends EntityMob {
	private int sonics;
	public EntityCicada(World world) {
		super(world);
		setSize(1.0F, 0.4F);
		stepHeight = 0.0F;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.0D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(5.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(2.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D); // followRange
	}
	
	//@Override
	//public boolean isAIEnabled() {
	//	return true;
	//}
	
	@Override
    public boolean getCanSpawnHere() {
    	return worldObj.difficultySetting > 0 && !worldObj.isAnyLiquid(boundingBox);
    }
	
	@Override 
    public int getMaxSpawnedInChunk() {
		return 3;
	}
	
	@Override
    protected int getDropItemId() {
        return 0;
    }
	
	@Override	 
    protected Entity findPlayerToAttack() {
        EntityPlayer var1 = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
        return var1 != null && this.canEntityBeSeen(var1) ? var1 : null;
    }

    @Override
	public boolean attackEntityAsMob(Entity entity) {
    	if (super.attackEntityAsMob(entity)) {
            if (entity instanceof EntityLiving) {
            	
        		 }
            return true;
        }
        else {
            return false;
        }
    }
    
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(sonics<=10) {
			sonics++;
		}
		if(sonics>10) {
			sonics=0;
		}
		
		if(sonics==0) {
			spawnSonicParticles();
		}
	}
	
	public void spawnSonicParticles() {
		for(int a=0; a<360; a+=4) {
		    double ang=a*Math.PI/180D;
		    worldObj.spawnParticle("flame", this.posX+-MathHelper.sin((float) ang)*1.0, this.posY+0.5D, this.posZ+MathHelper.cos((float) ang)*1.0, -MathHelper.sin((float) ang)*0.3, 0D , MathHelper.cos((float) ang)*0.3);
		}
	}

}
