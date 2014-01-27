package erebus.block;

import net.minecraft.block.BlockMobSpawner;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import erebus.tileentity.TileEntitySpawner;

public class BlockSpawner extends BlockMobSpawner {

	private final String mobName;

	public BlockSpawner(int id, String mobName) {
		super(id);
		disableStats();
		setHardness(5.0F);
		this.mobName = mobName;
		setStepSound(soundMetalFootstep);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySpawner(mobName);
	}
}
