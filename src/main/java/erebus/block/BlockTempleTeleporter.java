package erebus.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;

public class BlockTempleTeleporter extends BlockContainer {
	
	private IIcon jadeTop, exoTop, magmaCreamTop, magmaEyeTop, stringTop, closedTop, openTop1, openTop2, openTop3, activeSendTop, activeReturnTop;
	
	public BlockTempleTeleporter() {
		super(Material.rock);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.templeTeleporter");
		setBlockUnbreakable();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon("erebus:templeBrick");
		jadeTop = reg.registerIcon("erebus:templeTeleportJade");
		exoTop = reg.registerIcon("erebus:templeTeleportExo");
		magmaCreamTop = reg.registerIcon("erebus:templeTeleportCream");
		magmaEyeTop = reg.registerIcon("erebus:templeTeleportEye");
		stringTop = reg.registerIcon("erebus:templeTeleportString");
		closedTop = reg.registerIcon("erebus:templeTeleportClosed");
		openTop1 = reg.registerIcon("erebus:templeTeleportOpen1");
		openTop2 = reg.registerIcon("erebus:templeTeleportOpen2");
		openTop3 = reg.registerIcon("erebus:templeTeleportOpen3");
		activeSendTop = reg.registerIcon("erebus:templeTeleportSend");
		activeReturnTop = reg.registerIcon("erebus:templeTeleportReturn");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side == 1)
			switch(meta) {
			case 0:
				return jadeTop;
			case 1:
				return exoTop;
			case 2:
				return magmaCreamTop;
			case 3:
				return magmaEyeTop;
			case 4:
				return stringTop;
			case 5:
				return closedTop;
			case 6:
				return openTop1;
			case 7:
				return openTop2;
			case 8:
				return openTop3;
			case 9:
				return activeSendTop;
			case 10:
				return activeReturnTop;
			default:
				return blockIcon;
		}
			return blockIcon;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		//TileEntityTempleTeleporter() tile = Utils.getTileEntity(world, x, y, z, TileEntityTempleTeleporter().class);

		if (player.isSneaking())
			return false;

		if (player.getCurrentEquippedItem() != null) {
			int meta = world.getBlockMetadata(x, y, z);
			if(meta <= 10)
				world.setBlock(x, y, z, this, meta + 1, 3);
			return true;
		}
		return true;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return null;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO  Make this... return new TileEntityTempleTeleporter();
		return null;
	}
}