package erebus.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityTarantulaEgg extends EntityThrowable
{

	public EntityTarantulaEgg(World world)
	{
		super(world);
		setSize(0.7F, 0.7F);
	}

	public EntityTarantulaEgg(World world, EntityLiving entity)
	{
		super(world, entity);
	}

	public EntityTarantulaEgg(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}

	public EntityTarantulaEgg(World world, EntityPlayer player)
	{
		super(world, player);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	protected String getSplatSound()
	{
		return "erebus:beetlelarvasplat";
	}

	@Override
	protected void onImpact(MovingObjectPosition mop)
	{

		if (mop.entityHit != null)
		{
			//spawn Baby tarantula cluster
		}
		worldObj.playSoundAtEntity(this, getSplatSound(), 1.0F, 1.0F);
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return false;
	}

	public boolean attackEntityFrom(DamageSource source, int amount)
	{
		return false;
	}

}