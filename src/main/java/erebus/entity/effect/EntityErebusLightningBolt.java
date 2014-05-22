package erebus.entity.effect;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityWeatherEffect;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityErebusLightningBolt extends EntityWeatherEffect {

	private int lightningState;
	public long boltVertex;
	private int boltLivingTime;

	public EntityErebusLightningBolt(World world, double x, double y, double z) {
		super(world);
		setLocationAndAngles(x, y, z, 0.0F, 0.0F);
		lightningState = 2;
		boltVertex = rand.nextLong();
		boltLivingTime = rand.nextInt(3) + 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onUpdate() {
		super.onUpdate();

		if (lightningState == 2) {
			worldObj.playSoundEffect(posX, posY, posZ, "ambient.weather.thunder", 10000.0F, 0.8F + rand.nextFloat() * 0.2F);
			worldObj.playSoundEffect(posX, posY, posZ, "random.explode", 2.0F, 0.5F + rand.nextFloat() * 0.2F);
		}

		--lightningState;

		if (lightningState < 0)
			if (boltLivingTime == 0)
				setDead();
			else if (lightningState < -rand.nextInt(10)) {
				--boltLivingTime;
				lightningState = 1;
				boltVertex = rand.nextLong();
			}

		if (lightningState >= 0)
			if (worldObj.isRemote)
				worldObj.lastLightningBolt = 2;

		if (lightningState >= 0)
			if (worldObj.isRemote)
				worldObj.lastLightningBolt = 2;
			else {
				double d0 = 3.0D;
				List<Entity> list = worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getAABBPool().getAABB(posX - d0, posY - d0, posZ - d0, posX + d0, posY + 6.0D + d0, posZ + d0));

				for (int l = 0; l < list.size(); ++l) {
					Entity entity = list.get(l);
					if (entity != null)
						if (entity instanceof EntityLivingBase) {
							entity.setFire(5);
							entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase) entity), 8.0F);
						}
				}
			}
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderVec3D(Vec3 vec) {
		return lightningState >= 0;
	}
}