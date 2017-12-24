package erebus.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityGlowingJar extends TileEntity implements ITickable {
	private float particleSpawnTick;
	public float particleSize;

	@Override
	public void update() {
		if (world.isRemote) {
			particleSpawnTick++;
			if (particleSpawnTick <= 50)
				particleSize = particleSpawnTick / 25;
			else
				particleSize = 2 - (particleSpawnTick - 50) / 25;
			if (particleSpawnTick > 100)
				particleSpawnTick = 0;
		}
	}
}