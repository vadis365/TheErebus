package erebus.block;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntityGlowGem;

public class BlockGlowGem extends BlockContainer {
	@SideOnly(Side.CLIENT)
	private Icon iconTop, iconBottom;

	public BlockGlowGem(int id) {
		super(id, Material.glass);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
//	@Override
//	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
//		return world.isBlockSolidOnSide(x - 1, y, z, EAST) || world.isBlockSolidOnSide(x + 1, y, z, WEST) || world.isBlockSolidOnSide(x, y, z - 1, SOUTH) || world.isBlockSolidOnSide(x, y, z + 1, NORTH);
//	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		int j1 = meta;

		if ((j1 == 0 || side == 2) && world.isBlockSolidOnSide(x, y, z + 1, NORTH))
			j1 = 2;

		if ((j1 == 0 || side == 3) && world.isBlockSolidOnSide(x, y, z - 1, SOUTH))
			j1 = 3;

		if ((j1 == 0 || side == 4) && world.isBlockSolidOnSide(x + 1, y, z, WEST))
			j1 = 4;

		if ((j1 == 0 || side == 5) && world.isBlockSolidOnSide(x - 1, y, z, EAST))
			j1 = 5;

		return j1;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourID) {
		int i1 = world.getBlockMetadata(x, y, z);
		boolean flag = false;

		if (i1 == 2 && world.isBlockSolidOnSide(x, y, z + 1, NORTH))
			flag = true;

		if (i1 == 3 && world.isBlockSolidOnSide(x, y, z - 1, SOUTH))
			flag = true;

		if (i1 == 4 && world.isBlockSolidOnSide(x + 1, y, z, WEST))
			flag = true;

		if (i1 == 5 && world.isBlockSolidOnSide(x - 1, y, z, EAST))
			flag = true;

		if (!flag) {
			dropBlockAsItem(world, x, y, z, i1, 0);
			world.setBlockToAir(x, y, z);
		}

		super.onNeighborBlockChange(world, x, y, z, neighbourID);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		//TODO some switching code here for off and on

		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		world.setBlockMetadataWithNotify(x, y, z, BlockPistonBase.determineOrientation(world, x, y, z, player), 2);
	}


	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityGlowGem();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		//TODO remove light here
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 0 ? iconBottom : side == 1 ? iconTop : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("erebus:glowGem");// Side
		iconTop = iconRegister.registerIcon("erebus:glowGem");// Top
		iconBottom = iconRegister.registerIcon("erebus:glowGem");
	}
}