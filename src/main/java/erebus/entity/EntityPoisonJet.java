package erebus.entity;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPoisonJet extends EntityThrowable {

	public EntityPoisonJet(World world) {
		super(world);
		setSize(0.7F, 0.7F);
	}

	public EntityPoisonJet(World world, EntityLiving entity) {
		super(world, entity);
	}

	public EntityPoisonJet(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityPoisonJet(World world, EntityPlayer player) {
		super(world, player);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote)
			trailParticles(worldObj, posX, posY + 0.35D, posZ, rand);

		if (ticksExisted > 140)
			setDead();
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {

		if (mop.entityHit != null) {

			if (mop.entityHit instanceof EntityLivingBase) {
				if (!worldObj.isRemote) {
					((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 5 * 20, 0));
					((EntityLivingBase) mop.entityHit).attackEntityFrom(DamageSource.causeMobDamage(getThrower()), 1.0F);
				}
				setDead();
			}
		} else
			setDead();
	}

	@Override
	protected float getGravityVelocity() {
		return 0.02F;
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	public boolean attackEntityFrom(DamageSource source, int amount) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public void trailParticles(World world, double x, double y, double z, Random rand) {
		for (int count = 0; count < 5; ++count)
			Erebus.proxy.spawnCustomParticle("poison", worldObj, x, y, z, 0.0D, 0.0D, 0.0D);
	}
}