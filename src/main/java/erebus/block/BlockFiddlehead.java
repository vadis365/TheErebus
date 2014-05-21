package erebus.block;

import net.minecraft.client.renderer.texture.IconRegister;

public class BlockFiddlehead extends BlockFern {

	public BlockFiddlehead(int id) {
		super(id);
		float var3 = 0.4F;
		setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
	}

	@Override
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("erebus:Fiddlehead");
	}
}