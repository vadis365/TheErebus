 package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityCicada extends EntityMob {
	
	public EntityCicada(World world) {
		super(world);
		setSize(0.4F, 1.0F);
		stepHeight = 0.0F;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.2D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(5.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(2.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D); // followRange
	}
	
	@Override
	public boolean isAIEnabled() {
		return true;
	}
	
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
                byte byte0 = 0;
                if (worldObj.difficultySetting > 1) {
                    if (worldObj.difficultySetting == 2) {
                        byte0 = 7;
                    }
                    else if (worldObj.difficultySetting == 3) {
                        byte0 = 15;
                    }
                }
                if (byte0 > 0) {
                    ((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.poison.id, byte0 * 20, 0));
                    ((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.confusion.id, byte0 * 20, 0));
                }
            }
            return true;
        }
        else {
            return false;
        }
    }
	


}
