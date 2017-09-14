package erebus.entity;

import java.util.Random;

import erebus.Erebus;
import erebus.ModSounds;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGooBall extends EntityThrowable {

	private boolean playedSound = false;

	public EntityGooBall(World world) {
		super(world);
		setSize(0.7F, 0.7F);
	}

	public EntityGooBall(World world, EntityLiving entity) {
		super(world, entity);
	}

	public EntityGooBall(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityGooBall(World world, EntityPlayer player) {
		super(world, player);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (getEntityWorld().isRemote)
			trailParticles(getEntityWorld(), posX - 0.5D, posY, posZ - 0.5D, rand);
	}

	protected SoundEvent getJumpedOnSound() {
		return ModSounds.BEETLE_LARVA_SPLAT;
	}

	@Override
	protected void onImpact(RayTraceResult mop) {

		if (mop.entityHit != null) {
			if (mop.entityHit instanceof EntityPlayer) {
				if (!getEntityWorld().isRemote) {
					((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 5 * 20, 3));
					setDead();
				}
				if (getEntityWorld().isRemote)
					Erebus.NETWORK_WRAPPER.sendToAll(new PacketParticle(ParticleType.BEETLE_LARVA_SQUISH, (float) posX, (float)posY, (float)posZ));
			}
			if (mop.typeOfHit != null && mop.typeOfHit == RayTraceResult.Type.BLOCK)
				setDead();
		}

		if (!playedSound) {
			getEntityWorld().playSound((EntityPlayer)null, getPosition(), getJumpedOnSound(), SoundCategory.HOSTILE, 1.0F, 1.0F);
			playedSound = true;
		}
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
		for (int count = 0; count < 20; ++count) {
			double velX = 0.0D;
			double velY = 0.0D;
			double velZ = 0.0D;
			int motionX = rand.nextInt(2) * 2 - 1;
			int motionZ = rand.nextInt(2) * 2 - 1;
			velY = (rand.nextFloat() - 0.5D) * 0.125D;
			velZ = rand.nextFloat() * 1.0F * motionZ;
			velX = rand.nextFloat() * 1.0F * motionX;
			Erebus.PROXY.spawnCustomParticle("slime", getEntityWorld(), x, y, z, velX, velY, velZ);
		}
	}
}