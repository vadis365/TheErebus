package erebus.entity;

import erebus.Erebus;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
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
		this.thrower = thrower;
	}

	public EntityWaspDagger(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected void onImpact(RayTraceResult mop) {

		if (!getEntityWorld().isRemote) {
			if (mop.entityHit != null && mop.entityHit != thrower) {
				byte byte0 = 6;
				if (mop.entityHit instanceof EntityWasp)
					byte0 = 0;
				if (!mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), byte0))
					;
				if (isBurning() && !(mop.entityHit instanceof EntityEnderman))
					mop.entityHit.setFire(5);
				if (!(mop.entityHit instanceof EntityWasp) && mop.entityHit instanceof EntityLivingBase)
					((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(new PotionEffect(MobEffects.POISON, 60, 0)));
				setDead();
			}
			Erebus.NETWORK_WRAPPER.sendToAll(new PacketParticle(ParticleType.WASP_DAGGER, (float) posX, (float) posY, (float) posZ));
		}
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