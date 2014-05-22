package erebus.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
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
	protected void onImpact(MovingObjectPosition target) {
		if (target.entityHit != null) {
			byte byte0 = 4;
			if (!target.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), byte0))
				;
			if (isBurning() && !(target.entityHit instanceof EntityEnderman))
				target.entityHit.setFire(5);
			for (int i = 0; i < 8; i++)
				worldObj.spawnParticle("reddust", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
			if (!worldObj.isRemote) {
				target.entityHit.addVelocity(-MathHelper.sin(getThrower().rotationYaw * 3.141593F / 180.0F) * 0.5F, 0.1D, MathHelper.cos(getThrower().rotationYaw * 3.141593F / 180.0F) * 0.5F);
				spawnWoodlouse();
			}
		} else if (!worldObj.isRemote)
			spawnWoodlouse();
	}

	private void spawnWoodlouse() {
		if (!worldObj.isRemote) {
			setDead();
			EntityWoodlouse entity = new EntityWoodlouse(worldObj);
			entity.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
			worldObj.spawnEntityInWorld(entity);
		}
	}
}