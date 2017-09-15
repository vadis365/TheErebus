package erebus.entity;

import erebus.Erebus;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityWoodlouseBall extends EntityThrowable {
	public static float rotationticks;

	public EntityWoodlouseBall(World world) {
		super(world);
		setSize(0.3F, 0.3F);
	}

	public EntityWoodlouseBall(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityWoodlouseBall(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected void onImpact(RayTraceResult target) {
		if (target.entityHit != null) {
			byte damage = 4;
			if (!target.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage))
				;
			if (isBurning() && !(target.entityHit instanceof EntityEnderman))
				target.entityHit.setFire(5);
			if (!getEntityWorld().isRemote) {
				target.entityHit.addVelocity(-MathHelper.sin(getThrower().rotationYaw * 3.141593F / 180.0F) * 0.5F, 0.1D, MathHelper.cos(getThrower().rotationYaw * 3.141593F / 180.0F) * 0.5F);
				spawnWoodlouse();
			}
		} else if (!getEntityWorld().isRemote)
			spawnWoodlouse();
	}

	private void spawnWoodlouse() {
		if (!getEntityWorld().isRemote) {
			setDead();
			EntityWoodlouse entity = new EntityWoodlouse(getEntityWorld());
			entity.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
			getEntityWorld().spawnEntity(entity);
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (getEntityWorld().isRemote)
			Erebus.PROXY.spawnCustomParticle("reddust", getEntityWorld(), posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height - 0.25D, posZ + (rand.nextDouble() - 0.5D) * width, 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble());
		
		if (rotationticks < 360F) {
			rotationticks = rotationticks + 20F;
			if (rotationticks >= 360F)
				rotationticks = 0;
		}
	}
}