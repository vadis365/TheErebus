package erebus.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModTabs;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.util.IIcon;

public class BlockButtonUmberstone extends BlockButtonStone {

	public BlockButtonUmberstone() {
		setHardness(0.5F);
		setStepSound(soundTypeStone);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.umberstoneButton");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return ModBlocks.umberstone.getBlockTextureFromSide(1);
	}
}