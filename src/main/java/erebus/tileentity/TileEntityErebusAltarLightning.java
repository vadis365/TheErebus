package erebus.tileentity;

import java.util.List;

import erebus.Erebus;
import erebus.ModBlocks;
import erebus.entity.EntityMobBlock;
import erebus.entity.EntityUmberGolem;
import erebus.entity.effect.EntityErebusLightningBolt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class TileEntityErebusAltarLightning extends TileEntityErebusAltar {

	public int animationTicks;
	public boolean active;
	public int fuzz;
	private int spawnTicks;

	@Override
	public void updateEntity() {
		findEnemyToAttack();
		spawnTicks--;
		if (active) {
			if (animationTicks == 0)
				worldObj.playSoundEffect(xCoord, yCoord, zCoord, "erebus:altarchangestate", 1.0F, 1.3F);
			if (animationTicks <= 24)
				animationTicks++;
			if (animationTicks == 25)
				if (fuzz < 20) {
					fuzz++;
					if (fuzz >= 20)
						fuzz = 0;
				}
		}
		if (!active) {
			if (animationTicks == 25)
				worldObj.playSoundEffect(xCoord, yCoord, zCoord, "erebus:altarchangestate", 1.0F, 1.3F);
			if (animationTicks >= 1)
				animationTicks--;
			if (animationTicks == 1)
				worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.altarBase);
		}
		if (animationTicks >= 1 && animationTicks <= 24)
			flameOn(worldObj, xCoord, yCoord, zCoord);
		if (spawnTicks == 0)
			setActive(false);
	}

	public void flameOn(World world, int x, int y, int z) {
		if (world.isRemote) {
			double d0 = x + 0.53125F;
			double d1 = y + 1.25F;
			double d2 = z + 0.53125F;
			Erebus.proxy.spawnCustomParticle("smoke", world, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("flame", world, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("smoke", world, d0, d1, d2 - 0.265625, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("flame", world, d0, d1, d2 - 0.265625, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("smoke", world, d0, d1, d2 + 0.265625, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("flame", world, d0, d1, d2 + 0.265625, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("smoke", world, d0 - 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("flame", world, d0 - 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("smoke", world, d0 + 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("flame", world, d0 + 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("smoke", world, d0, d1 + 0.25, d2, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("flame", world, d0, d1 + 0.25, d2, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("smoke", world, d0, d1 + 0.5, d2, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("flame", world, d0, d1 + 0.5, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	public void setActive(boolean par1) {
		active = par1;
	}

	public void setSpawnTicks(int i) {
		spawnTicks = i;
	}

	@SuppressWarnings("unchecked")
	protected Entity findEnemyToAttack() {
		List<EntityLivingBase> list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D).expand(6D, 2D, 6D));
		if (active)
			for (int i = 0; i < list.size(); i++) {
				Entity entity = list.get(i);
				if (entity != null)
					if (entity instanceof EntityLivingBase)
						if (((EntityLivingBase) entity).getCreatureAttribute().equals(EnumCreatureAttribute.ARTHROPOD) && !(entity instanceof EntityMobBlock) && !(entity instanceof EntityUmberGolem)) {
							double a = entity.posX;
							double b = entity.boundingBox.minY;
							double c = entity.posZ;
							EntityErebusLightningBolt entitybolt = new EntityErebusLightningBolt(worldObj, 0D, 0D, 0D);
							entitybolt.setLocationAndAngles(a, b, c, 0F, 0F);
							worldObj.addWeatherEffect(entitybolt);
						}
			}
		return null;
	}

	@Override
	protected void writeTileToNBT(NBTTagCompound nbt) {
		nbt.setInteger("animationTicks", animationTicks);
		nbt.setInteger("spawnTicks", spawnTicks);
		nbt.setBoolean("active", active);
	}

	@Override
	protected void readTileFromNBT(NBTTagCompound nbt) {
		animationTicks = nbt.getInteger("animationTicks");
		spawnTicks = nbt.getInteger("spawnTicks");
		active = nbt.getBoolean("active");
	}

}
