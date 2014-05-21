package erebus.block;

import net.minecraft.block.BlockButtonStone;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;

public class BlockButtonUmberstone extends BlockButtonStone {

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return ModBlocks.umberstone.getBlockTextureFromSide(1);
	}
}
