package erebus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.core.proxy.CommonProxy;

public class BlockPetrifiedCraftingTable extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon blockTop, blockBottom;

	public BlockPetrifiedCraftingTable() {
		super(Material.rock);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		else {
			player.openGui(Erebus.instance, CommonProxy.GUI_ID_PETRIFIED_CRAFT, world, x, y, z);
			return true;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int m) {
		return side == 1 ? blockTop : side == 0 ? blockBottom : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon("erebus:petrifiedCraft_side");
		blockTop = reg.registerIcon("erebus:petrifiedCraft_top");
		blockBottom = reg.registerIcon("erebus:petrifiedCraft_bottom");
	}
}