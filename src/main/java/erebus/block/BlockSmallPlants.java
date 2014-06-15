package erebus.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockMushroom;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.item.block.ItemBlockErebusPlantSmall;

public class BlockSmallPlants extends BlockMushroom implements ISubBlocksBlock {

	public static final String[] iconPaths = new String[] { "bulbCappedShroom",
			"mushroomSmall1", "mushroomSmall2", "mushroomSmall3",
			"dutchCapShroom", "cattail", "desertShrub", "hanger", "hangerSeed",
			"mireCoral", "nettle", "nettleFlowered", "swampPlant", "fireBloom" };

	public static final int dataBulbCappedShroom = 0, dataMushroom1 = 1,
			dataMushroom2 = 2, dataMushroom3 = 3, dataDutchCapShroom = 4,
			dataCattail = 5, dataDesertShrub = 6, dataHanger = 7,
			dataHangerSeed = 8, dataMireCoral = 9, dataNettle = 10,
			dataNettleFlowered = 11, dataSwampPlant = 12, dataFireBloom = 13;

	@SideOnly(Side.CLIENT)
	public IIcon[] icons;

	public BlockSmallPlants() {
		setTickRandomly(true);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
		int meta = access.getBlockMetadata(x, y, z);
		float widthReduced = 0, height = 0;

		switch (meta) {
		case dataBulbCappedShroom:
			widthReduced = 0.3125F;
			height = 0.6875F;
			break;
		case dataMushroom1:
			widthReduced = 0.0625F;
			height = 0.75F;
			break;
		case dataMushroom2:
			widthReduced = 0.0625F;
			height = 0.75F;
			break;
		case dataMushroom3:
			widthReduced = 0.125F;
			height = 0.625F;
			break;
		case dataDutchCapShroom:
			widthReduced = 0.0625F;
			height = 0.875F;
			break;
		case dataCattail:
			widthReduced = 0;
			height = 0.9375F;
			break;
		case dataDesertShrub:
			widthReduced = 0;
			height = 1F;
			break;
		case dataHanger:
			widthReduced = 0.1875F;
			height = 1F;
			break;
		case dataHangerSeed:
			widthReduced = 0.125F;
			height = 1F;
			break;
		case dataMireCoral:
			widthReduced = 0;
			height = 0.9375F;
			break;
		case dataNettle:
			widthReduced = 0.125F;
			height = 1F;
			break;
		case dataNettleFlowered:
			widthReduced = 0.125F;
			height = 1F;
			break;
		case dataSwampPlant:
			widthReduced = 0.0625F;
			height = 0.4375F;
			break;
		case dataFireBloom:
			widthReduced = 0.1875F;
			height = 1F;
			break;
		}
		setBlockBounds(0F + widthReduced, 0.0F, 0F + widthReduced, 1F - widthReduced, height, 1F - widthReduced);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);
		if (rand.nextInt(10) == 0) {
			byte radius = 4;
			int distance = 5;
			int xx;
			int yy;
			int zz;
			for (xx = x - radius; xx <= x + radius; ++xx) {
				for (zz = z - radius; zz <= z + radius; ++zz) {
					for (yy = y - 1; yy <= y + 1; ++yy) {
						if (world.getBlock(xx, zz, yy) == this) {
							--distance;
							if (distance <= 0)
								return;
						}
					}
				}
			}
			xx = x + rand.nextInt(3) - 1;
			yy = y + rand.nextInt(2) - rand.nextInt(2);
			zz = z + rand.nextInt(3) - 1;
			if (world.isAirBlock(xx, yy, zz)
					&& this.canBlockStay(world, xx, yy, zz)) {
				if (meta == dataNettle && rand.nextInt(3) == 0)
					world.setBlock(x, y, z, this, dataNettleFlowered, 2);
				if (meta == dataNettleFlowered)
					world.setBlock(xx, yy, zz, this, dataNettle, 2);
				else
					world.setBlock(xx, yy, zz, this, meta, 2);
			}
		}
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
		return super.canPlaceBlockAt(world, x, y, z) && canBlockStay(world, x, y, z);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		Block soil = world.getBlock(x, y - 1, z);
		if (y >= 0 && y < 256 && meta < 5)
			return (soil == Blocks.mycelium || world.getFullBlockLightValue(x, y, z) < 13) && soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
		if (y >= 0 && y < 256 && meta >= 5 && meta != dataHanger || y >= 0 && y < 256 && meta >= 5 && meta != dataHangerSeed)
			return soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
		if(meta == dataHanger || meta == dataHangerSeed)
			return isValidBlock(world.getBlock(x, y + 1, z));
		return false;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == dataHanger || meta == dataHangerSeed)
			if (world.isAirBlock(x, y + 1, z))
				world.setBlockToAir(x, y, z);
		canBlockStay(world, x, y, z);
	}
	
	private boolean isValidBlock(Block block) {
		return block.getMaterial().blocksMovement() || block == this;
	}
	
	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
		return isValidBlock(world.getBlock(x, y + 1, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 13) {
			double xx = x + 0.5F;
			double yy = y + 1F;
			double zz = z + 0.5F;
			world.spawnParticle("flame", xx, yy, zz, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockErebusPlantSmall.class;
	}
}