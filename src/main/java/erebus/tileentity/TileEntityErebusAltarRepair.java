package erebus.tileentity;

import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModSounds;
import erebus.network.client.PacketAltarAnimationTimer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityErebusAltarRepair extends TileEntityErebusAltar implements ITickable {

	public boolean active;
	private int spawnTicks;
	public boolean notUsed = true;
	private int collisions;

	@Override
	public void update() {
		if (!getWorld().isRemote) {
			prevAnimationTicks = animationTicks;
			if (active) {
				if (animationTicks == 0)
					getWorld().playSound(null, getPos(), ModSounds.ALTAR_CHANGE_STATE, SoundCategory.BLOCKS, 1.0F, 1.3F);
				if (animationTicks <= 24)
					animationTicks++;
			}
			if (!active) {
				if (animationTicks == 25)
					getWorld().playSound(null, getPos(), ModSounds.ALTAR_CHANGE_STATE, SoundCategory.BLOCKS, 1.0F, 1.3F);
				if (animationTicks >= 1)
					animationTicks--;
				if (animationTicks == 1)
					getWorld().setBlockState(getPos(), ModBlocks.ALTAR_BASE.getDefaultState());
			}
			if (spawnTicks == 160)
				setcanBeUsed(false);
			if (spawnTicks == 0)
				setActive(false);
			spawnTicks--;
			if (prevAnimationTicks != animationTicks)
				Erebus.NETWORK_WRAPPER.sendToAll(new PacketAltarAnimationTimer(getPos().getX(), getPos().getY(), getPos().getZ(), animationTicks));
		}

		if (getWorld().isRemote)
			if (animationTicks == 6)
				cloudBurst(getWorld(), getPos());
	}

	private void cloudBurst(World world, BlockPos pos) {
		if (world.isRemote) {
			double d0 = pos.getX() + 0.53125F;
			double d1 = pos.getY() + 1.25F;
			double d2 = pos.getZ() + 0.53125F;
			Erebus.PROXY.spawnCustomParticle("cloud", world, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("cloud", world, d0, d1, d2 - 0.265625, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("cloud", world, d0, d1, d2 + 0.265625, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("cloud", world, d0 - 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("cloud", world, d0 + 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("cloud", world, d0, d1 + 0.25, d2, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("cloud", world, d0, d1 + 0.5, d2, 0.0D, 0.0D, 0.0D);
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
	public void sparky(World world, BlockPos pos) {
		double d0 = pos.getX() + 0.53125F;
		double d1 = pos.getY() + 1.5F;
		double d2 = pos.getZ() + 0.53125F;
		Erebus.PROXY.spawnCustomParticle("enchantmenttable", world, d0, d1, d2, 0.5D, 0.0D, -0.5D);
		Erebus.PROXY.spawnCustomParticle("enchantmenttable", world, d0, d1, d2, -0.5D, 0.0D, 0.5D);
		Erebus.PROXY.spawnCustomParticle("enchantmenttable", world, d0, d1, d2, -0.5D, 0.0D, -0.5D);
		Erebus.PROXY.spawnCustomParticle("enchantmenttable", world, d0, d1, d2, 0.5D, 0.0D, 0.5D);
		Erebus.PROXY.spawnCustomParticle("portal", world, d0, d1 + 0.5, d2, 0.0D, 0.0D, 0.0D);
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
