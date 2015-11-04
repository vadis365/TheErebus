package erebus.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.lib.EnumWood;
import erebus.lib.Reference;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class BlockWoodFenceGate extends BlockFenceGate {

	private final EnumWood wood;

	public BlockWoodFenceGate(EnumWood wood) {
		this.wood = wood;
		setHardness(2.0F);
		setResistance(5.0F);
		setStepSound(soundTypeWood);
		setCreativeTab(ModTabs.blocks);
		setBlockName(Reference.MOD_ID + ".fenceGate" + wood.name());
	}

	@Override
	public String getLocalizedName() {
		return StatCollector.translateToLocal("tile." + Reference.MOD_ID + ".fence_gate_" + wood.getUnlocalisedName() + ".name");
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