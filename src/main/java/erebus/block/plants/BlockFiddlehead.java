package erebus.block.plants;

import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFiddlehead extends BlockFern
{
	public BlockFiddlehead()
	{
		super();
		setBlockName("erebus.fiddlehead");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon("erebus:fiddlehead");
	}
}