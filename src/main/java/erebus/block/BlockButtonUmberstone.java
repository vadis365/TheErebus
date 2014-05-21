package erebus.block;

import net.minecraft.block.BlockButtonStone;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;

public class BlockButtonUmberstone extends BlockButtonStone {
	public BlockButtonUmberstone(int id) {
		super(id);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return ModBlocks.umberstone.getBlockTextureFromSide(1);
	}
}
