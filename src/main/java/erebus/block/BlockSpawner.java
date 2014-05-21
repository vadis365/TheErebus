package erebus.block;

import net.minecraft.block.BlockMobSpawner;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import erebus.tileentity.TileEntitySpawner;

public class BlockSpawner extends BlockMobSpawner {

	private final String mobName;

	public BlockSpawner(int id, String mobName) {
		disableStats();
		setHardness(5.0F);
		this.mobName = mobName;
		setStepSound(soundTypeMetal);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntitySpawner(mobName);
	}
}