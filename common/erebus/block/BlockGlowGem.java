package erebus.block;
import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.UP;
import static net.minecraftforge.common.ForgeDirection.WEST;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
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
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return world.isBlockSolidOnSide(x, y + 1, z, DOWN) || world.isBlockSolidOnSide(x, y - 1, z, UP) ||world.isBlockSolidOnSide(x - 1, y, z, EAST) || world.isBlockSolidOnSide(x + 1, y, z, WEST) || world.isBlockSolidOnSide(x, y, z - 1, SOUTH) || world.isBlockSolidOnSide(x, y, z + 1, NORTH);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		
		if ((side == 0) && world.isBlockSolidOnSide(x, y + 1, z, DOWN))
			meta = 0;

		if ((side == 1) && world.isBlockSolidOnSide(x, y - 1, z, UP))
			meta = 1;

		if ((side == 2) && world.isBlockSolidOnSide(x, y, z + 1, NORTH))
			meta = 2;

		if ((side == 3) && world.isBlockSolidOnSide(x, y, z - 1, SOUTH))
			meta = 3;

		if ((side == 4) && world.isBlockSolidOnSide(x + 1, y, z, WEST))
			meta = 4;

		if ((side == 5) && world.isBlockSolidOnSide(x - 1, y, z, EAST))
			meta = 5;

		return meta;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourID) {
		int meta = world.getBlockMetadata(x, y, z);
		boolean flag = false;
		
		if (meta == 0 || meta == 6 || meta == 7 || meta == 8 || meta == 9)
			if(world.isBlockSolidOnSide(x, y + 1, z, DOWN))
				flag = true;
		
		if (meta == 1 || meta == 10 || meta == 11 || meta == 12 || meta == 13)
			if(world.isBlockSolidOnSide(x, y -1 , z, UP))
				flag = true;

		if (meta == 2 && world.isBlockSolidOnSide(x, y, z + 1, NORTH))
			flag = true;

		if (meta == 3 && world.isBlockSolidOnSide(x, y, z - 1, SOUTH))
			flag = true;

		if (meta == 4 && world.isBlockSolidOnSide(x + 1, y, z, WEST))
			flag = true;

		if (meta == 5 && world.isBlockSolidOnSide(x - 1, y, z, EAST))
			flag = true;

		if (!flag) {
			dropBlockAsItem(world, x, y, z, meta, 0);
			world.setBlockToAir(x, y, z);
		}

		super.onNeighborBlockChange(world, x, y, z, neighbourID);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		
		TileEntityGlowGem tile = (TileEntityGlowGem) world.getBlockTileEntity(x, y, z);
		if (tile == null)
			return false;
		
		tile.toggleLight();
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		int direction = MathHelper.floor_double((double)((player.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		int meta = world.getBlockMetadata(x, y, z);
		int newMeta=meta;
		if(meta==0)
			switch (direction){
			case 0:
				newMeta=6;
				break;
			case 1:
				newMeta=7;
				break;
			case 2:
				newMeta=8;
				break;
			case 3:
				newMeta=9;
				break;
			}
		
		if(meta==1)
			switch (direction){
			case 0:
				newMeta=10;
				break;
			case 1:
				newMeta=11;
				break;
			case 2:
				newMeta=12;
				break;
			case 3:
				newMeta=13;
				break;	
			}
			world.setBlockMetadataWithNotify(x, y, z, newMeta, 3);	
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityGlowGem();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int oldID, int oldMeta) {
		TileEntityGlowGem tile = (TileEntityGlowGem) world.getBlockTileEntity(x, y, z);
			tile.setIlluminated(false);
		super.breakBlock(world, x, y, z, oldID, oldMeta);
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