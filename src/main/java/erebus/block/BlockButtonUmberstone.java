package erebus.block;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModTabs;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.util.IIcon;

public class BlockButtonUmberstone extends BlockButtonStone {

	public BlockButtonUmberstone() {
		setHardness(0.5F);
		setStepSound(soundTypeStone);
		setCreativeTab(ModTabs.blocks);
		setUnlocalizedName("erebus.umberstoneButton");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return ModBlocks.umberstone.getBlockTextureFromSide(1);
	}
}