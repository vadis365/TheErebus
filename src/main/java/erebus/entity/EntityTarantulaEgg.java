package erebus.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityTarantulaEgg extends EntityThrowable {
	public float rotationticks;

	public EntityTarantulaEgg(World world) {
		super(world);
		setSize(0.7F, 0.7F);
	}

	public EntityTarantulaEgg(World world, EntityLiving entity) {
		super(world, entity);
	}

	public EntityTarantulaEgg(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityTarantulaEgg(World world, EntityPlayer player) {
		super(world, player);
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

	protected String getSplatSound() {
		return "erebus:beetlelarvasplat";
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (!worldObj.isRemote)
			for (int a = 0; a < 2; a++) {
				EntityTarantulaBaby tarantulaBaby = new EntityTarantulaBaby(worldObj);
				tarantulaBaby.setPosition(posX + (rand.nextFloat() * 0.03D - rand.nextFloat() * 0.03D), posY + 1, posZ + (rand.nextFloat() * 0.03D - rand.nextFloat() * 0.03D));
				worldObj.spawnEntityInWorld(tarantulaBaby);
			}
		setDead();
		worldObj.playSoundAtEntity(this, getSplatSound(), 1.0F, 1.0F);
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	public boolean attackEntityFrom(DamageSource source, int amount) {
		return false;
	}

}