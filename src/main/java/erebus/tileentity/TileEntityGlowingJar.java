package erebus.tileentity;

import java.util.Random;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityGlowingJar extends TileEntity implements ITickable {
	Random rand = new Random();
	private float particleSpawnTick = rand.nextInt(100);
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