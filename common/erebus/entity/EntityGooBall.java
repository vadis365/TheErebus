package erebus.entity;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;
import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.network.PacketHandler;
import erebus.network.packet.PacketParticle;

public class EntityGooBall extends EntityThrowable {

	public EntityGooBall(World world) {
		super(world);
		setSize(1F, 1F);
	}

	public EntityGooBall(World world, EntityLiving par2EntityLiving) {
		super(world, par2EntityLiving);
	}

	public EntityGooBall(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityGooBall(World world, EntityPlayer player) {
		super(world, player);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			randomDisplayTick(worldObj, posX, posY, posZ, rand);
		}
	}

	protected String getWebSlingSplatSound() {
		return "erebus:webslingsplat";
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (!worldObj.isRemote) {
			if (mop.entityHit != null) {
				if (mop.entityHit instanceof EntityLivingBase) { 
					((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 5 * 20, 3));
					PacketDispatcher.sendPacketToAllAround(mop.entityHit.posX, mop.entityHit.posY+2D, mop.entityHit.posZ, 64D, dimension, PacketHandler.buildPacket(2, PacketParticle.BEETLE_LARVA_SQUISH, mop.entityHit.entityId));	
				}	
			} 
				setDead();
		}
		worldObj.playSoundAtEntity(this, getWebSlingSplatSound(), 1.0F, 1.0F);
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	public boolean attackEntityFrom(DamageSource source, int par2) {
		return false;
	}
	   public void randomDisplayTick(World world, double d, double f, double e, Random rand) {
	        for (int l = 0; l < 3; ++l) {
	            double d0 = (double)((float)d + rand.nextFloat());
	            double d1 = (double)((float)f + rand.nextFloat());
	            d0 = (double)((float)e + rand.nextFloat());
	            double d2 = 0.0D;
	            double d3 = 0.0D;
	            double d4 = 0.0D;
	            int i1 = rand.nextInt(2) * 2 - 1;
	            int j1 = rand.nextInt(2) * 2 - 1;
	            d2 = ((double)rand.nextFloat() - 0.5D) * 0.125D;
	            d3 = ((double)rand.nextFloat() - 0.5D) * 0.125D;
	            d4 = ((double)rand.nextFloat() - 0.5D) * 0.125D;
	            double d5 = (double)e + 0.5D + 0.25D * (double)j1;
	            d4 = (double)(rand.nextFloat() * 1.0F * (float)j1);
	            double d6 = (double)d + 0.5D + 0.25D * (double)i1;
	            d2 = (double)(rand.nextFloat() * 1.0F * (float)i1);
	            world.spawnParticle("slime", d6, d1, d5, d2, d3, d4);
	        }
	    }
}