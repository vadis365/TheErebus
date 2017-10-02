package erebus.blocks;

import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.SoundType;
import net.minecraft.entity.EntityList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class BlockSpawner extends BlockMobSpawner {
	private final String mobName;

	public BlockSpawner(String mobName) {
		disableStats();
		setHardness(5.0F);
		this.mobName = mobName;
		setSoundType(SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		TileEntityMobSpawner tile = new TileEntityMobSpawner();
		tile.getSpawnerBaseLogic().setEntityId(EntityList.getKey(EntityList.getClassFromName(mobName)));
		return tile;
	}
}