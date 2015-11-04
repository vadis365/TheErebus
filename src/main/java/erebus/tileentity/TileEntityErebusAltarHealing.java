package erebus.tileentity;

import java.util.List;

import erebus.Erebus;
import erebus.ModBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class TileEntityErebusAltarHealing extends TileEntityErebusAltar {

	public int animationTicks;
	public boolean active;
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
		}
		if (!active) {
			if (animationTicks == 25)
				worldObj.playSoundEffect(xCoord, yCoord, zCoord, "erebus:altarchangestate", 1.0F, 1.3F);
			if (animationTicks >= 1)
				animationTicks--;
			if (animationTicks == 1)
				worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.altarBase);
		}
		if (animationTicks == 6)
			bigLove(worldObj, xCoord, yCoord, zCoord);
		if (spawnTicks == 0)
			setActive(false);
	}

	public void bigLove(World world, int x, int y, int z) {
		if (world.isRemote) {
			double d0 = x + 0.53125F;
			double d1 = y + 1.25F;
			double d2 = z + 0.53125F;
			Erebus.proxy.spawnCustomParticle("heart", world, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("heart", world, d0, d1, d2 - 0.265625, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("heart", world, d0, d1, d2 + 0.265625, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("heart", world, d0 - 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("heart", world, d0 + 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("heart", world, d0, d1 + 0.25, d2, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("heart", world, d0, d1 + 0.5, d2, 0.0D, 0.0D, 0.0D);
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
		List<EntityLivingBase> list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D).expand(4D, 2D, 4D));
		if (active)
			for (int i = 0; i < list.size(); i++) {
				Entity entity = list.get(i);
				if (entity != null)
					if (entity instanceof EntityPlayer)
						((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.heal.id, 1 * 20, 0));
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
