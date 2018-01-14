package erebus.entity;

import java.util.Random;

import erebus.Erebus;
import erebus.ModItems;
import erebus.core.handler.configs.ConfigHandler;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySporeBall extends EntityThrowable {

	public EntitySporeBall(World world) {
		super(world);
		setSize(0.7F, 0.7F);
	}

	public EntitySporeBall(World world, EntityLiving entity) {
		super(world, entity);
	}

	public EntitySporeBall(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntitySporeBall(World world, EntityPlayer player) {
		super(world, player);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (getRidingEntity() == null)
			if (getEntityWorld().isRemote)
				trailParticles(getEntityWorld(), posX, posY + 0.35D, posZ, rand);

		if (getRidingEntity() != null) {
			if (getEntityWorld().isRemote)
				confusionParticles(getEntityWorld(), posX, posY + 1F, posZ, rand);

			if (!getEntityWorld().isRemote)
				if (getRidingEntity() instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) getRidingEntity();
					ItemStack is = player.getHeldItemMainhand();
					if (!is.isEmpty())
						player.dropItem(true);
				}

			if (ticksExisted > 140)
				setDead();
		}
	}

	@Override
	protected void onImpact(RayTraceResult mop) {

		if (mop.entityHit != null && !(mop.entityHit instanceof EntityCrushroom)) {

			if (mop.entityHit instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) mop.entityHit;
				ItemStack helm = player.inventory.armorItemInSlot(3);

				if (!helm.isEmpty() && helm.getItem() == ModItems.MUSHROOM_HELMET)
					setDead();

				else if (!getEntityWorld().isRemote)
					if (!player.isBeingRidden()) {
						startRiding(player, true);
						ticksExisted = 0;
					}
			}

			if (mop.entityHit instanceof EntityLivingBase && !(mop.entityHit instanceof EntityPlayer)) {
				if (!getEntityWorld().isRemote) {
					((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(MobEffects.POISON, 5 * 20, 0));
					((EntityLivingBase) mop.entityHit).attackEntityFrom(DamageSource.causeMobDamage(getThrower()), (float) (ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 1D : 1D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier));
				}
				setDead();
			}
		}
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	public boolean attackEntityFrom(DamageSource source, int amount) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public void trailParticles(World world, double x, double y, double z, Random rand) {
		for (int count = 0; count < 30; ++count)
			Erebus.PROXY.spawnCustomParticle("spell", getEntityWorld(), x, y, z, 0.0D, 0.0D, 0.0D);
	}

	@SideOnly(Side.CLIENT)
	public void confusionParticles(World world, double x, double y, double z, Random rand) {
		for (int count = 0; count < 2; ++count) {
			double velX = 0.0D;
			double velY = 0.0D;
			double velZ = 0.0D;
			int motionX = rand.nextInt(2) * 2 - 1;
			int motionZ = rand.nextInt(2) * 2 - 1;
			velY = (rand.nextFloat() - 0.5D) * 0.125D;
			velZ = rand.nextFloat() * 0.5F * motionZ;
			velX = rand.nextFloat() * 0.5F * motionX;
			Erebus.PROXY.spawnCustomParticle("spell", getEntityWorld(), x, y, z, velX, velY, velZ);
		}
	}
}