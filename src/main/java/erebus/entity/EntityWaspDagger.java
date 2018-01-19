package erebus.entity;

import erebus.Erebus;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityWaspDagger extends EntityThrowable {
	public static float rotationticks;

	public EntityWaspDagger(World world) {
		super(world);
	}

	public EntityWaspDagger(World world, EntityLivingBase thrower) {
		super(world, thrower);
	}

	public EntityWaspDagger(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected void onImpact(RayTraceResult mop) {
		if (mop.entityHit != null && mop.entityHit != thrower) {
			if (!getEntityWorld().isRemote) {
				byte byte0 = 6;
				if (mop.entityHit instanceof EntityWasp)
					byte0 = 0;
				if (!mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), byte0))
					;
				if (isBurning() && !(mop.entityHit instanceof EntityEnderman))
					mop.entityHit.setFire(5);
			}
		}
		if (getEntityWorld().isRemote)
			for (int i = 0; i < 8; i++)
				Erebus.PROXY.spawnCustomParticle("reddust", getEntityWorld(), posX, posY, posZ, 0.0D, 0.0D, 0.0D);
		setDead();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (getEntityWorld().isRemote) {
			if (rotationticks < 360F) {
				rotationticks = rotationticks + 20F;
				if (rotationticks >= 360F)
					rotationticks = 0;
			}
		}
	}
}