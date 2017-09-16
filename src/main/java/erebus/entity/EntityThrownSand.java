package erebus.entity;

import erebus.core.handler.configs.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

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
	protected void onImpact(RayTraceResult movingObjectPosition) {
		BlockPos pos = new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY), MathHelper.floor(posZ));

		if (movingObjectPosition.entityHit != null) {
			if (!getEntityWorld().isRemote)
				if (movingObjectPosition.entityHit instanceof EntityLivingBase) {
					((EntityLivingBase) movingObjectPosition.entityHit).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 2 * 20, 0));
					getEntityWorld().playEvent(null, 2001, pos, Block.getIdFromBlock(Blocks.SAND));
				}
			if (!movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), (float) (ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 4D : 4D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier)))
				;
		} else if (movingObjectPosition.entityHit == null && Blocks.SAND.canPlaceBlockAt(getEntityWorld(), pos))
			if (!getEntityWorld().isRemote) {
				setDead();
				getEntityWorld().setBlockState(pos, Blocks.SAND.getDefaultState());
			}
		if (!getEntityWorld().isRemote) {
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
