package erebus.block;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class UmberstonePillar extends BlockRotatedPillar {

	public UmberstonePillar(int id) {
		super(id, Material.rock);
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected Icon getSideIcon(int side) {
		return blockIcon;
	}

	@Override
	public void registerIcons(IconRegister reg) {
		super.registerIcons(reg);
		field_111051_a = reg.registerIcon("erebus:umberstonePillar_end");
	}
}