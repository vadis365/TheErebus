 package erebus.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.Erebus;
import erebus.ModBlocks;

public class EntityCicada extends EntityCreature {
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
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(8.0D); // followRange
	}
	
	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}
	
	@Override
    public boolean getCanSpawnHere() {
			AxisAlignedBB axisalignedbb = boundingBox.expand(1D, 1D, 1D);
			int n = MathHelper.floor_double(axisalignedbb.minX);
			int o = MathHelper.floor_double(axisalignedbb.maxX + 1.0D);
			int p = MathHelper.floor_double(axisalignedbb.minY);
			int q = MathHelper.floor_double(axisalignedbb.maxY + 1.0D);
			int n1 = MathHelper.floor_double(axisalignedbb.minZ);
			int o1 = MathHelper.floor_double(axisalignedbb.maxZ + 1.0D);
			for (int p1 = n; p1 < o; p1++)
				for (int q1 = p; q1 < q; q1++)
					for (int n2 = n1; n2 < o1; n2++) {
						int o2 = worldObj.getBlockId(p1, q1, n2);
						if (o2 == 0)
							continue;
						if (o2 == ModBlocks.logErebusGroup1.blockID)
							//if(worldObj.isAirBlock(p1, q1+1, n2) && worldObj.isAirBlock(p1, q1-1, n2))
								return true;
					}
			return false;
		}
	
	@Override 
    public int getMaxSpawnedInChunk() {
		return 10;
	}
	
	@Override
    protected int getDropItemId() {
        return 0;
    }
    
	@Override
	public void onUpdate() {
		super.onUpdate();
		findEnemyToAttack();
			if(sonics<=20)
				sonics++;
			if(sonics>20)
				sonics=0;
			if(sonics>10)
				entityToAttack = null;	
	}
	
	protected Entity findEnemyToAttack() {
		List list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(posX + 0.5D, posY + 0.5D, posZ+ 0.5D, posX + 0.5D, posY + 0.5D, posZ + 0.5D).expand((float) sonics *0.2D, 0.5D, (float) sonics *0.2D));
			for (int i = 0; i < list.size(); i++) {
				Entity entity = (Entity) list.get(i);
				if (entity != null)
					if (entity instanceof EntityPlayer && !(entity instanceof EntityCicada) ) {
						if(sonics==20) {
							if(worldObj.isRemote)
									spawnSonicParticles();
							((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 8*20, 0));
							((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 8*20, 0));
							entity.addVelocity(-MathHelper.sin(rotationYaw * 3.141593F / 180.0F) * 2.0D, 0D, MathHelper.cos(rotationYaw * 3.141593F / 180.0F) * 2.0D);
							worldObj.playSoundAtEntity(this, "erebus:locustspawn", 1.0F, 6.0F);
						}
						return canEntityBeSeen(entity) ? entity : null;
					}
			}
		return null;
	}
	
	public void spawnSonicParticles() {
		for(int a=0; a<360; a+=4) {
		    double ang=a*Math.PI/180D;
		    Erebus.proxy.spawnCustomParticle("sonic", worldObj, this.posX+-MathHelper.sin((float) ang)*1.0, this.posY+0.5D, this.posZ+MathHelper.cos((float) ang)*1.0, -MathHelper.sin((float) ang)*0.3, 0D , MathHelper.cos((float) ang)*0.3);
		}
	}

}
