package erebus.tileentity;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}
}