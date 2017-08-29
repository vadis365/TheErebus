package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import erebus.core.handler.configs.ConfigHandler;

public class EntityThrownSand extends EntityThrowable {
	public static float rotationticks;

	public EntityThrownSand(World world) {
		super(world);
	}

	public EntityThrownSand(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityThrownSand(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected void onImpact(MovingObjectPosition movingObjectPosition) {
		int x = MathHelper.floor_double(posX);
		int y = MathHelper.floor_double(posY);
		int z = MathHelper.floor_double(posZ);
		if (movingObjectPosition.entityHit != null) {
			if (!worldObj.isRemote)
				if (movingObjectPosition.entityHit instanceof EntityLivingBase) {
					((EntityLivingBase) movingObjectPosition.entityHit).addPotionEffect(new PotionEffect(Potion.blindness.id, 2 * 20, 0));
					worldObj.playAuxSFXAtEntity(null, 2001, x, y, z, Block.getIdFromBlock(Blocks.sand));
				}
			if (!movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), (float) (ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 4D : 4D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier)))
				;
		} else if (movingObjectPosition.entityHit == null && Blocks.sand.canPlaceBlockAt(worldObj, x, y, z))
			if (!worldObj.isRemote) {
				setDead();
				worldObj.setBlock(x, y, z, Blocks.sand);
			}
		if (!worldObj.isRemote) {
			setDead();
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (rotationticks < 360F) {
			rotationticks = rotationticks + 10F;
			if (rotationticks >= 360F)
				rotationticks = 0;
		}
	}
}
