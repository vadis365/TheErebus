package erebus.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.lib.EnumWood;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class SaplessLog extends BlockRotatedPillar {
	public SaplessLog() {
		super(Material.wood);
		setHardness(0.5F);
		setResistance(1.0F);
		setCreativeTab(ModTabs.blocks);
		setStepSound(Block.soundTypeWood);
		setBlockName("erebus.saplessLog");
	}

	@Override
	protected IIcon getSideIcon(int meta) {
		return blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getTopIcon(int meta) {
		return EnumWood.Sap.getLog().getIcon(0, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon("erebus:log_sap_less");
	}
}