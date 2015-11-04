package erebus.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TileEntityErebusAltarRepair extends TileEntityErebusAltar {

	public int animationTicks;
	public boolean active;
	private int spawnTicks;
	public boolean notUsed = true;
	private int collisions;

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
		if (spawnTicks == 160)
			setcanBeUsed(false);
		if (spawnTicks == 0)
			setActive(false);
		spawnTicks--;
	}

	private void cloudBurst(World world, int x, int y, int z) {
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

	public int getSpawnTicks() {
		return spawnTicks;
	}

	public void setCollisions(int i) {
		collisions = i;
	}

	public int getCollisions() {
		return collisions;
	}

	public void setcanBeUsed(boolean canBeUsed) {
		notUsed = canBeUsed;
	}

	@SideOnly(Side.CLIENT)
	public void sparky(World world, int x, int y, int z) {
		double d0 = x + 0.53125F;
		double d1 = y + 1.5F;
		double d2 = z + 0.53125F;
		Erebus.proxy.spawnCustomParticle("enchantmenttable", world, d0, d1, d2, 0.5D, 0.0D, -0.5D);
		Erebus.proxy.spawnCustomParticle("enchantmenttable", world, d0, d1, d2, -0.5D, 0.0D, 0.5D);
		Erebus.proxy.spawnCustomParticle("enchantmenttable", world, d0, d1, d2, -0.5D, 0.0D, -0.5D);
		Erebus.proxy.spawnCustomParticle("enchantmenttable", world, d0, d1, d2, 0.5D, 0.0D, 0.5D);
		Erebus.proxy.spawnCustomParticle("portal", world, d0, d1 + 0.5, d2, 0.0D, 0.0D, 0.0D);
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
