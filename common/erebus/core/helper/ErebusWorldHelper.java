package erebus.core.helper;

import net.minecraft.world.World;

public class ErebusWorldHelper {

	public static void setBlockIfEmpty(int x, int y, int z, int blockID, int metadata, int flags, World world) {
		if (world.isAirBlock(x, y, z))
			world.setBlock(x, y, z, blockID, metadata, flags);
	}

	public static void setBlockUnderSky(int x, int y, int z, int blockID, int metadata, int flags, World world) {
		if (world.canBlockSeeTheSky(x, y, z))
			world.setBlock(x, y, z, blockID, metadata, flags);
	}
}
