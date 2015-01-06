package erebus.block;

import net.minecraft.block.BlockButtonStone;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModTabs;

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