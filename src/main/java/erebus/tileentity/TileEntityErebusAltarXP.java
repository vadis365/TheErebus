package erebus.tileentity;

import erebus.Erebus;
import erebus.ModBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TileEntityErebusAltarXP extends TileEntityErebusAltar {

	public int animationTicks;
	public boolean active;
	private int spawnTicks;
	private int uses;

	@Override
	public void updateEntity() {
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
			cloudBurst(worldObj, xCoord, yCoord, zCoord);
		if (spawnTicks == 0)
			setActive(false);
		spawnTicks--;
	}

	public void cloudBurst(World world, int x, int y, int z) {
		if (world.isRemote) {
			double d0 = x + 0.53125F;
			double d1 = y + 1.25F;
			double d2 = z + 0.53125F;
			Erebus.proxy.spawnCustomParticle("cloud", world, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("cloud", world, d0, d1, d2 - 0.265625, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("cloud", world, d0, d1, d2 + 0.265625, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("cloud", world, d0 - 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("cloud", world, d0 + 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("cloud", world, d0, d1 + 0.25, d2, 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("cloud", world, d0, d1 + 0.5, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	public void setActive(boolean par1) {
		active = par1;
	}

	public void setSpawnTicks(int i) {
		spawnTicks = i;
	}

	public void setUses(int isSize) {
		uses = isSize;
	}

	public int getUses() {
		return uses;
	}

	public int getExcess() {
		return uses - 165;
	}

	@Override
	protected void writeTileToNBT(NBTTagCompound nbt) {
		nbt.setInteger("animationTicks", animationTicks);
		nbt.setInteger("spawnTicks", spawnTicks);
		nbt.setInteger("uses", uses);
		nbt.setBoolean("active", active);
	}

	@Override
	protected void readTileFromNBT(NBTTagCompound nbt) {
		animationTicks = nbt.getInteger("animationTicks");
		spawnTicks = nbt.getInteger("spawnTicks");
		uses = nbt.getInteger("uses");
		active = nbt.getBoolean("active");
	}
}
