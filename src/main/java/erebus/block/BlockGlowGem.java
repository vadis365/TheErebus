package erebus.block;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;
import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.UP;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityGlowGem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGlowGem extends BlockContainer {

	public BlockGlowGem() {
		super(Material.glass);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.glowGemBlock");
		setBlockTextureName("erebus:glowGem");
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
		int meta = access.getBlockMetadata(x, y, z);
		float widthMin = 0, heightMin = 0, depthMin = 0;
		float widthMax = 0, heightMax = 0, depthMax = 0;
		switch (meta) {
			case 0:
				break;
			case 1:
				break;
			case 2:
				widthMin = 0.125F;
				heightMin = 0.1875F;
				depthMin = 0.8125F;
				widthMax = 0.125F;
				heightMax = 0.1875F;
				depthMax = 0F;
				break;
			case 3:
				widthMin = 0.125F;
				heightMin = 0.1875F;
				depthMin = 0F;
				widthMax = 0.125F;
				heightMax = 0.1875F;
				depthMax = 0.8125F;
				break;
			case 4:
				widthMin = 0.8125F;
				heightMin = 0.1875F;
				depthMin = 0.125F;
				widthMax = 0F;
				heightMax = 0.1875F;
				depthMax = 0.125F;
				break;
			case 5:
				widthMin = 0F;
				heightMin = 0.1875F;
				depthMin = 0.125F;
				widthMax = 0.8125F;
				heightMax = 0.1875F;
				depthMax = 0.125F;
				break;
			case 6:
				widthMin = 0.125F;
				heightMin = 0.8125F;
				depthMin = 0.1875F;
				widthMax = 0.125F;
				heightMax = 0F;
				depthMax = 0.1875F;
				break;
			case 7:
				widthMin = 0.1875F;
				heightMin = 0.8125F;
				depthMin = 0.125F;
				widthMax = 0.1875F;
				heightMax = 0F;
				depthMax = 0.125F;
				break;
			case 8:
				widthMin = 0.125F;
				heightMin = 0.8125F;
				depthMin = 0.1875F;
				widthMax = 0.125F;
				heightMax = 0F;
				depthMax = 0.1875F;
				break;
			case 9:
				widthMin = 0.1875F;
				heightMin = 0.8125F;
				depthMin = 0.125F;
				widthMax = 0.1875F;
				heightMax = 0F;
				depthMax = 0.125F;
				break;
			case 10:
				widthMin = 0.125F;
				heightMin = 0F;
				depthMin = 0.1875F;
				widthMax = 0.125F;
				heightMax = 0.8125F;
				depthMax = 0.1875F;
				break;
			case 11:
				widthMin = 0.1875F;
				heightMin = 0F;
				depthMin = 0.125F;
				widthMax = 0.1875F;
				heightMax = 0.8125F;
				depthMax = 0.125F;
				break;
			case 12:
				widthMin = 0.125F;
				heightMin = 0F;
				depthMin = 0.1875F;
				widthMax = 0.125F;
				heightMax = 0.8125F;
				depthMax = 0.1875F;
				break;
			case 13:
				widthMin = 0.1875F;
				heightMin = 0F;
				depthMin = 0.125F;
				widthMax = 0.1875F;
				heightMax = 0.8125F;
				depthMax = 0.125F;
				break;
		}
		setBlockBounds(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		TileEntityGlowGem tile = Utils.getTileEntity(world, x, y, z, TileEntityGlowGem.class);
		if (tile == null)
			return 0;

		return tile.lightOn ? 15 : 0;
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

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return world.isSideSolid(x, y + 1, z, DOWN) || world.isSideSolid(x, y - 1, z, UP) || world.isSideSolid(x - 1, y, z, EAST) || world.isSideSolid(x + 1, y, z, WEST) || world.isSideSolid(x, y, z - 1, SOUTH) || world.isSideSolid(x, y, z + 1, NORTH);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {

		if (side == 0 && world.isSideSolid(x, y + 1, z, DOWN))
			meta = 0;

		if (side == 1 && world.isSideSolid(x, y - 1, z, UP))
			meta = 1;

		if (side == 2 && world.isSideSolid(x, y, z + 1, NORTH))
			meta = 2;

		if (side == 3 && world.isSideSolid(x, y, z - 1, SOUTH))
			meta = 3;

		if (side == 4 && world.isSideSolid(x + 1, y, z, WEST))
			meta = 4;

		if (side == 5 && world.isSideSolid(x - 1, y, z, EAST))
			meta = 5;

		return meta;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		int meta = world.getBlockMetadata(x, y, z);
		boolean flag = false;

		if (meta == 0 || meta == 6 || meta == 7 || meta == 8 || meta == 9)
			if (world.isSideSolid(x, y + 1, z, DOWN))
				flag = true;

		if (meta == 1 || meta == 10 || meta == 11 || meta == 12 || meta == 13)
			if (world.isSideSolid(x, y - 1, z, UP))
				flag = true;

		if (meta == 2 && world.isSideSolid(x, y, z + 1, NORTH))
			flag = true;

		if (meta == 3 && world.isSideSolid(x, y, z - 1, SOUTH))
			flag = true;

		if (meta == 4 && world.isSideSolid(x + 1, y, z, WEST))
			flag = true;

		if (meta == 5 && world.isSideSolid(x - 1, y, z, EAST))
			flag = true;

		if (!flag) {
			dropBlockAsItem(world, x, y, z, meta, 0);
			world.setBlockToAir(x, y, z);
		}

		super.onNeighborBlockChange(world, x, y, z, neighbour);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

		TileEntityGlowGem tile = Utils.getTileEntity(world, x, y, z, TileEntityGlowGem.class);
		if (tile == null)
			return false;

		tile.toggleLight();
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		int direction = MathHelper.floor_double(player.rotationYaw * 4F / 360F + 0.5D) & 3;
		int meta = world.getBlockMetadata(x, y, z);
		int newMeta = meta;
		if (meta == 0)
			switch (direction) {
				case 0:
					newMeta = 6;
					break;
				case 1:
					newMeta = 7;
					break;
				case 2:
					newMeta = 8;
					break;
				case 3:
					newMeta = 9;
					break;
			}

		if (meta == 1)
			switch (direction) {
				case 0:
					newMeta = 10;
					break;
				case 1:
					newMeta = 11;
					break;
				case 2:
					newMeta = 12;
					break;
				case 3:
					newMeta = 13;
					break;
			}
		world.setBlockMetadataWithNotify(x, y, z, newMeta, 3);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityGlowGem();
	}
}