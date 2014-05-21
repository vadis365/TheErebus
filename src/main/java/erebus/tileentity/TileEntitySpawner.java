package erebus.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntitySpawner extends TileEntity {
	private int spawnDelay = 20;
	private String mobName;

	private final int minSpawnDelay = 200;
	private final int maxSpawnDelay = 800;

	private final int spawnCount = 4;
	private final int spawnRange = 4;
	private final int activatingRangeFromPlayer = 16;
	private final int maxNearbyEntities = 6;

	public TileEntitySpawner() {
		this("Pig"); // Idiot proofing
	}

	public TileEntitySpawner(String mobName) {
		this.mobName = mobName;
	}

	public boolean canRun() {
		return worldObj.getClosestPlayer(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, activatingRangeFromPlayer) != null;
	}

	@Override
	public void updateEntity() {
		if (canRun() || true)
			if (worldObj.isRemote) {
				double d1 = xCoord + worldObj.rand.nextFloat();
				double d2 = yCoord + worldObj.rand.nextFloat();
				double d0 = zCoord + worldObj.rand.nextFloat();
				worldObj.spawnParticle("smoke", d1, d2, d0, 0.0D, 0.0D, 0.0D);
				worldObj.spawnParticle("flame", d1, d2, d0, 0.0D, 0.0D, 0.0D);

				if (spawnDelay == -1)
					resetDelay();
				if (spawnDelay > 0)
					spawnDelay--;

			} else {
				if (spawnDelay == -1)
					resetDelay();
				if (spawnDelay > 0) {
					spawnDelay--;
					return;
				}

				for (int i = 0; i < spawnCount; i++) {
					Entity entity = EntityList.createEntityByName(mobName, worldObj);
					if (entity == null)
						return;
					int j = worldObj.getEntitiesWithinAABB(entity.getClass(), AxisAlignedBB.getAABBPool().getAABB(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(spawnRange * 2, 4.0D, spawnRange * 2)).size();

					if (j >= maxNearbyEntities) {
						resetDelay();
						return;
					}

					double x = xCoord + (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * spawnRange;
					double y = yCoord + worldObj.rand.nextInt(3);
					double z = zCoord + (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * spawnRange;
					entity.setLocationAndAngles(x, y, z, worldObj.rand.nextFloat() * 360.0F, 0.0F);

					if (entity instanceof EntityLiving) {
						spawnEntity(entity);
						worldObj.playAuxSFX(2004, xCoord, yCoord, zCoord, 0);
						((EntityLiving) entity).spawnExplosionParticle();
						resetDelay();
					}
				}
			}
	}

	public Entity spawnEntity(Entity entity) {
		if (entity instanceof EntityLivingBase && entity.worldObj != null) {
			((EntityLiving) entity).onSpawnWithEgg((EntityLivingData) null);
			worldObj.spawnEntityInWorld(entity);
		}
		return entity;
	}

	private void resetDelay() {
		spawnDelay = minSpawnDelay + worldObj.rand.nextInt(maxSpawnDelay - minSpawnDelay);
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		mobName = data.getString("mobName");
		spawnDelay = data.getInteger("spawnDelay");
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setString("mobName", mobName);
		data.setInteger("spawnDelay", spawnDelay);
	}

	@SideOnly(Side.CLIENT)
	public Entity getEntityToRender() {
		Entity entity = EntityList.createEntityByName(mobName, (World) null);
		entity = spawnEntity(entity);
		return entity;
	}

	@SideOnly(Side.CLIENT)
	public int getSpawnDelay() {
		return spawnDelay;
	}
}
