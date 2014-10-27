package erebus.entity;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;

public class EntityGasVent extends EntityLiving {

	public EntityGasVent(World world) {
		super(world);
		setSize(0.5F, 2.5F);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			if (ticksExisted > 20) {
				setDead();
			}
		}

		if (worldObj.isRemote) {
			trailParticles(worldObj, posX - 0.5D, posY, posZ - 0.5D, rand);
		}
	}

	@Override
	protected void collideWithEntity(Entity entity) {
		if (!worldObj.isRemote) {
			if(entity instanceof EntityLivingBase && !(entity instanceof EntityGasVent))
				entity.setFire(5);
		}
		setDead();
		super.collideWithEntity(entity);
	}

	@SideOnly(Side.CLIENT)
	public void trailParticles(World world, double posX, double posY, double posZ, Random rand) {
		for (double yy = posY; yy < posY + 2D; yy += 0.5D) {
			double d0 = posX + 0.4375F;
			double d1 = yy;
			double d2 = posZ + 0.4375F;
			double d3 = posX + 0.5625F;
			double d4 = posZ + 0.5625F;
			double d5 = posX + 0.5F;
			double d6 = yy + 0.25F;
			double d7 = posZ + 0.5F;
			Erebus.proxy.spawnCustomParticle("swampflame", world, d0, d1, d2, 0.0D, 0.05D, 0.0D);
			Erebus.proxy.spawnCustomParticle("swampflame", world, d0, d1, d4, 0.0D, 0.05D, 0.0D);
			Erebus.proxy.spawnCustomParticle("swampflame", world, d3, d1, d2, 0.0D, 0.05D, 0.0D);
			Erebus.proxy.spawnCustomParticle("swampflame", world, d3, d1, d4, 0.0D, 0.05D, 0.0D);
			Erebus.proxy.spawnCustomParticle("swampflame", world, d5, d6, d7, 0.0D, 0.05D, 0.0D);
		}
	}
}