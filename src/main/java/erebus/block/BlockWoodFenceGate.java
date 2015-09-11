package erebus.block;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.lib.EnumWood;
import erebus.lib.Reference;

public class BlockWoodFenceGate extends BlockFenceGate {

	private final EnumWood wood;

	public BlockWoodFenceGate(EnumWood wood) {
		this.wood = wood;
		setHardness(2.0F);
		setResistance(5.0F);
		setStepSound(soundTypeWood);
		setCreativeTab(ModTabs.blocks);
		setBlockName(Reference.MOD_ID + ".fenceGame" + wood.name());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return ModBlocks.planks.getIcon(side, wood.ordinal());
	}
}