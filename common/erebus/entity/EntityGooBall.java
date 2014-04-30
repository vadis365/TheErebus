package erebus.entity;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.network.PacketTypeHandler;
import erebus.network.packet.PacketParticle;

public class EntityGooBall extends EntityThrowable {

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
		if (worldObj.isRemote)
			trailParticles(worldObj, posX - 0.5D, posY, posZ - 0.5D, rand);
	}

	protected String getJumpedOnSound() {
		return "erebus:beetlelarvasplat";
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (!worldObj.isRemote) {
			if (mop.entityHit != null)
				if (mop.entityHit instanceof EntityLivingBase) {
					((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 5 * 20, 3));
					PacketDispatcher.sendPacketToAllAround(mop.entityHit.posX, mop.entityHit.posY + 2D, mop.entityHit.posZ, 64D, dimension, PacketTypeHandler.populatePacket(new PacketParticle(PacketParticle.BEETLE_LARVA_SQUISH, mop.entityHit.entityId)));
				}
			setDead();
		}
		worldObj.playSoundAtEntity(this, getJumpedOnSound(), 1.0F, 1.0F);
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
			Erebus.proxy.spawnCustomParticle("slime", worldObj, x, y, z, velX, velY, velZ);
		}
	}
}