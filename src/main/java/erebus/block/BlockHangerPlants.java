package erebus.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.item.block.ItemBlockErebusPlantSmall;

public class BlockHangerPlants extends BlockBush implements ISubBlocksBlock {

	public static final String[] iconPaths = new String[] { "hanger", "hangerSeed" };

	public static final int dataHanger = 0, dataHangerSeed = 1;

	@SideOnly(Side.CLIENT)
	public IIcon[] icons;

	public BlockHangerPlants() {
		setTickRandomly(true);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
		int meta = access.getBlockMetadata(x, y, z);
		float widthReduced = 0, height = 0;

		switch (meta) {
		case dataHanger:
			widthReduced = 0.1875F;
			height = 1F;
			break;
		case dataHangerSeed:
			widthReduced = 0.125F;
			height = 1F;
			break;
		}
		setBlockBounds(0F + widthReduced, 0.0F, 0F + widthReduced, 1F - widthReduced, height, 1F - widthReduced);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);
		if (rand.nextInt(10) == 0) {
			int yy = y - 1;

			if (world.isAirBlock(x, yy, z) && this.canBlockStay(world, x, yy, z)) {
				if (meta == dataHanger)
					world.setBlock(x, yy, z, this, dataHanger, 2);
				if (meta == dataHangerSeed)
					world.setBlock(x, yy, z, this, dataHanger, 2);
			}
		}
		if (meta == dataHanger && rand.nextInt(10) == 0)
			world.setBlock(x, y, z, this, dataHangerSeed, 2);
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
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < icons.length; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		return 1;
	}

	@Override
	public Item getItemDropped(int id, Random random, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x, y + 1, z)) && canBlockStay(world, x, y, z);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x, y + 1, z));
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (world.isAirBlock(x, y + 1, z)) {
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
		canBlockStay(world, x, y, z);
	}
	
	private boolean isValidBlock(Block block) {
		return block.getMaterial().blocksMovement() || block == this;
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockErebusPlantSmall.class;
	}
}