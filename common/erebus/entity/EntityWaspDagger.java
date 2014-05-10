package erebus.entity;

import erebus.Erebus;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityWaspDagger extends EntityThrowable {
	public static float rotationticks;

	public EntityWaspDagger(World world) {
		super(world);
	}

	public EntityWaspDagger(World world, EntityLivingBase par2EntityLiving) {
		super(world, par2EntityLiving);
	}

	public EntityWaspDagger(World world, double par2, double par4, double par6) {
		super(world, par2, par4, par6);
	}

	@Override
	protected void onImpact(MovingObjectPosition MovingObjectPosition) {
		if (MovingObjectPosition.entityHit != null) {
			byte byte0 = 4;
			if (MovingObjectPosition.entityHit instanceof EntityWasp)
				byte0 = 0;
			if (!MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), byte0))
				;
			if (isBurning() && !(MovingObjectPosition.entityHit instanceof EntityEnderman))
				MovingObjectPosition.entityHit.setFire(5);
			for (int i = 0; i < 8; i++)
				Erebus.proxy.spawnCustomParticle("reddust", worldObj, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
			if (!worldObj.isRemote)
				setDead();
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (rotationticks < 360F) {
			rotationticks = rotationticks + 20F;
			if (rotationticks >= 360F)
				rotationticks = 0;
		}
	}
}