package erebus.block.plants;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;
import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.UP;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.item.block.ItemBlockErebusPlantSmall;
import erebus.lib.EnumWood;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockWallPlants extends Block implements IShearable, ISubBlocksBlock {

	public static final String[] iconPaths = new String[] { "moss", "mould" };

	public static final int dataMoss = 0, dataMould = 1;

	@SideOnly(Side.CLIENT)
	public IIcon[] icons;

	public BlockWallPlants() {
		super(Material.plants);
		setHardness(0.2F);
		setTickRandomly(true);
		setCreativeTab(ModTabs.plants);
		setStepSound(Block.soundTypeGrass);
		setBlockName("erebus.wallPlants");
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.125F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons = new IIcon[iconPaths.length];
		int i = 0;
		for (String path : iconPaths)
			icons[i++] = reg.registerIcon("erebus:" + path);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (meta < 0)
			return null;
		if (meta == dataMoss)
			return icons[dataMoss];
		if (meta == dataMould)
			return icons[dataMould];
		if (meta > 1 && meta <= 7)
			return icons[dataMoss];
		else
			return icons[dataMould];
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < icons.length; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	public int getRenderType() {
		return 0;
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
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
		int meta = access.getBlockMetadata(x, y, z);
		float widthMin = 0, heightMin = 0, depthMin = 0;
		float widthMax = 0, heightMax = 0, depthMax = 0;

		switch (meta) {
			case 0:
				widthMin = 0F;
				heightMin = 0.875F;
				depthMin = 0F;
				widthMax = 0F;
				heightMax = 0F;
				depthMax = 0F;
				break;
			case 1:
				widthMin = 0F;
				heightMin = 0.875F;
				depthMin = 0F;
				widthMax = 0F;
				heightMax = 0F;
				depthMax = 0F;
				break;
			case 2:
				widthMin = 0F;
				heightMin = 0.875F;
				depthMin = 0F;
				widthMax = 0F;
				heightMax = 0F;
				depthMax = 0F;
				break;
			case 3:
				widthMin = 0F;
				heightMin = 0F;
				depthMin = 0F;
				widthMax = 0F;
				heightMax = 0.875F;
				depthMax = 0F;
				break;
			case 4:
				widthMin = 0F;
				heightMin = 0F;
				depthMin = 0.875F;
				widthMax = 0F;
				heightMax = 0F;
				depthMax = 0F;
				break;
			case 5:
				widthMin = 0F;
				heightMin = 0F;
				depthMin = 0F;
				widthMax = 0F;
				heightMax = 0F;
				depthMax = 0.875F;
				break;
			case 6:
				widthMin = 0.875F;
				heightMin = 0;
				depthMin = 0F;
				widthMax = 0F;
				heightMax = 0;
				depthMax = 0F;
				break;
			case 7:
				widthMin = 0F;
				heightMin = 0F;
				depthMin = 0F;
				widthMax = 0.875F;
				heightMax = 0F;
				depthMax = 0F;
				break;
			case 8:
				widthMin = 0F;
				heightMin = 0.875F;
				depthMin = 0F;
				widthMax = 0F;
				heightMax = 0F;
				depthMax = 0F;
				break;
			case 9:
				widthMin = 0F;
				heightMin = 0F;
				depthMin = 0F;
				widthMax = 0F;
				heightMax = 0.875F;
				depthMax = 0F;
				break;
			case 10:
				widthMin = 0F;
				heightMin = 0F;
				depthMin = 0.875F;
				widthMax = 0F;
				heightMax = 0F;
				depthMax = 0F;
				break;
			case 11:
				widthMin = 0F;
				heightMin = 0F;
				depthMin = 0F;
				widthMax = 0F;
				heightMax = 0F;
				depthMax = 0.875F;
				break;
			case 12:
				widthMin = 0.875F;
				heightMin = 0;
				depthMin = 0F;
				widthMax = 0F;
				heightMax = 0;
				depthMax = 0F;
				break;
			case 13:
				widthMin = 0F;
				heightMin = 0F;
				depthMin = 0F;
				widthMax = 0.875F;
				heightMax = 0F;
				depthMax = 0F;
				break;
		}
		setBlockBounds(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x, y + 1, z)) || isValidBlock(world.getBlock(x, y - 1, z)) || isValidBlock(world.getBlock(x - 1, y, z)) || isValidBlock(world.getBlock(x + 1, y, z)) || isValidBlock(world.getBlock(x, y, z - 1)) || isValidBlock(world.getBlock(x, y, z + 1));
	}

	private boolean isValidBlock(Block block) {
		return block == EnumWood.Rotten.getLog() || block == ModBlocks.umberstone;
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		if (meta == dataMoss) {
			if (side == 0 && world.isSideSolid(x, y + 1, z, DOWN))
				meta = 2;

			if (side == 1 && world.isSideSolid(x, y - 1, z, UP))
				meta = 3;

			if (side == 2 && world.isSideSolid(x, y, z + 1, NORTH))
				meta = 4;

			if (side == 3 && world.isSideSolid(x, y, z - 1, SOUTH))
				meta = 5;

			if (side == 4 && world.isSideSolid(x + 1, y, z, WEST))
				meta = 6;

			if (side == 5 && world.isSideSolid(x - 1, y, z, EAST))
				meta = 7;
		}

		if (meta == dataMould) {
			if (side == 0 && world.isSideSolid(x, y + 1, z, DOWN))
				meta = 8;

			if (side == 1 && world.isSideSolid(x, y - 1, z, UP))
				meta = 9;

			if (side == 2 && world.isSideSolid(x, y, z + 1, NORTH))
				meta = 10;

			if (side == 3 && world.isSideSolid(x, y, z - 1, SOUTH))
				meta = 11;

			if (side == 4 && world.isSideSolid(x + 1, y, z, WEST))
				meta = 12;

			if (side == 5 && world.isSideSolid(x - 1, y, z, EAST))
				meta = 13;
		}
		return meta;
	}

	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int meta) {
		onNeighborBlockChange(world, x, y, z, this);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		int meta = world.getBlockMetadata(x, y, z);
		boolean flag = false;

		if (meta == 2 || meta == 8)
			if (world.isSideSolid(x, y + 1, z, DOWN))
				flag = true;
		if (meta == 3 || meta == 9)
			if (world.isSideSolid(x, y - 1, z, UP))
				flag = true;
		if (meta == 4 || meta == 10)
			if (world.isSideSolid(x, y, z + 1, NORTH))
				flag = true;
		if (meta == 5 || meta == 11)
			if (world.isSideSolid(x, y, z - 1, SOUTH))
				flag = true;
		if (meta == 6 || meta == 12)
			if (world.isSideSolid(x + 1, y, z, WEST))
				flag = true;
		if (meta == 7 || meta == 13)
			if (world.isSideSolid(x - 1, y, z, EAST))
				flag = true;

		if (!flag || meta == dataMoss || meta == dataMould)
			if (!world.isRemote)
				Utils.breakBlockWithParticles(world, x, y, z, meta);

		super.onNeighborBlockChange(world, x, y, z, neighbour);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public Item getItemDropped(int id, Random random, int fortune) {
		return null;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		return false;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 2 || meta == 3 || meta == 4 || meta == 5 || meta == 6 || meta == 7)
			meta = dataMoss;
		if (meta == 8 || meta == 9 || meta == 10 || meta == 11 || meta == 12 || meta == 13)
			meta = dataMould;
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, meta));
		return ret;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);
		int attempt = 0;
		if (rand.nextInt(2) == 0) {
			byte radius = 4;
			int distance = 5;
			int xx;
			int yy;
			int zz;
			for (xx = x - radius; xx <= x + radius; ++xx)
				for (zz = z - radius; zz <= z + radius; ++zz)
					for (yy = y - radius; yy <= y + radius; ++yy)
						if (world.getBlock(xx, zz, yy) == this) {
							--distance;
							if (distance <= 0)
								return;
						}

			xx = x + rand.nextInt(3) - 1;
			yy = y + rand.nextInt(3) - 1;
			zz = z + rand.nextInt(3) - 1;
			if (world.isAirBlock(xx, yy, zz))
				for (attempt = 0; attempt < 6; attempt++) {
					int offset = 1;
					int randomiseSide = rand.nextInt(6);

					if (meta > 1 && meta <= 7)
						switch (randomiseSide) {
							case 0:
								if (world.isSideSolid(xx, yy + offset, zz, DOWN) && isValidBlock(world.getBlock(xx, yy + offset, zz)))
									world.setBlock(xx, yy, zz, ModBlocks.wallPlants, 2, 2);
								break;
							case 1:
								if (world.isSideSolid(xx, yy - offset, zz, UP) && isValidBlock(world.getBlock(xx, yy - offset, zz)))
									world.setBlock(xx, yy, zz, ModBlocks.wallPlants, 3, 2);
								break;
							case 2:
								if (world.isSideSolid(xx, yy, zz + offset, NORTH) && isValidBlock(world.getBlock(xx, yy, zz + offset)))
									world.setBlock(xx, yy, zz, ModBlocks.wallPlants, 4, 2);
								break;
							case 3:
								if (world.isSideSolid(xx, yy, zz - offset, SOUTH) && isValidBlock(world.getBlock(xx, yy, zz - offset)))
									world.setBlock(xx, yy, zz, ModBlocks.wallPlants, 5, 2);
								break;
							case 4:
								if (world.isSideSolid(xx + offset, yy, zz, WEST) && isValidBlock(world.getBlock(xx + offset, yy, zz)))
									world.setBlock(xx, yy, zz, ModBlocks.wallPlants, 6, 2);
								break;
							case 5:
								if (world.isSideSolid(xx - offset, yy, zz, EAST) && isValidBlock(world.getBlock(xx - offset, yy, zz)))
									world.setBlock(xx, yy, zz, ModBlocks.wallPlants, 7, 2);
								break;
						}
					else if (meta > 7 && meta <= 13)
						switch (randomiseSide) {
							case 0:
								if (world.isSideSolid(xx, yy + offset, zz, DOWN) && isValidBlock(world.getBlock(xx, yy + offset, zz)))
									world.setBlock(xx, yy, zz, ModBlocks.wallPlants, 8, 2);
								break;
							case 1:
								if (world.isSideSolid(xx, yy - offset, zz, UP) && isValidBlock(world.getBlock(xx, yy - offset, zz)))
									world.setBlock(xx, yy, zz, ModBlocks.wallPlants, 9, 2);
								break;
							case 2:
								if (world.isSideSolid(xx, yy, zz + offset, NORTH) && isValidBlock(world.getBlock(xx, yy, zz + offset)))
									world.setBlock(xx, yy, zz, ModBlocks.wallPlants, 10, 2);
								break;
							case 3:
								if (world.isSideSolid(xx, yy, zz - offset, SOUTH) && isValidBlock(world.getBlock(xx, yy, zz - offset)))
									world.setBlock(xx, yy, zz, ModBlocks.wallPlants, 11, 2);
								break;
							case 4:
								if (world.isSideSolid(xx + offset, yy, zz, WEST) && isValidBlock(world.getBlock(xx + offset, yy, zz)))
									world.setBlock(xx, yy, zz, ModBlocks.wallPlants, 12, 2);
								break;
							case 5:
								if (world.isSideSolid(xx - offset, yy, zz, EAST) && isValidBlock(world.getBlock(xx - offset, yy, zz)))
									world.setBlock(xx, yy, zz, ModBlocks.wallPlants, 13, 2);
								break;
						}
				}
		}
		if (rand.nextInt(25) == 0)
			world.setBlockToAir(x, y, z);
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockErebusPlantSmall.class;
	}

}