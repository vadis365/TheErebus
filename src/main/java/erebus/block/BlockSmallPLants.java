package erebus.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockMushroom;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSmallPLants extends BlockMushroom {

	public static final String[] iconPaths = new String[] {
		"bulbCappedShroom", "mushroomSmall1", "mushroomSmall2", "mushroomSmall3", "dutchCapShroom", "cattail",
		"desertShrub", "hanger", "hangerSeed", "mireCoral", "nettle", "nettleFlowered", "swampPlant", "fireBloom" 
	};
	
	public static final int
		dataBulbCappedShroom = 0, dataMushroom1 = 1, dataMushroom2 = 2, dataMushroom3 = 3, dataDutchCapShroom = 4, dataCattail = 5,
		dataDesertShrub = 6, dataHanger = 7, dataHangerSeed = 8, dataMireCoral = 9, dataNettle = 10, dataNettleFlowered = 11, dataSwampPlant = 12, dataFireBloom = 13;
	
	public static final Icon[] icons = new Icon[iconPaths.length];

	public BlockSmallPLants(int id) {
		super(id);
		this.setTickRandomly(true);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
		int meta = access.getBlockMetadata(x, y, z);
		float widthReduced = 0, height = 0;

		switch (meta) {
		case 0:
			widthReduced = 0.3125F;
			height = 0.6875F;
			break;
		case 1:
			widthReduced = 0.0625F;
			height = 0.75F;
			break;
		case 2:
			widthReduced = 0.0625F;
			height = 0.75F;
			break;
		case 3:
			widthReduced = 0.125F;
			height = 0.625F;
			break;
		case 4:
			widthReduced = 0.0625F;
			height = 0.875F;
			break;
		case 5:
			widthReduced = 0;
			height = 0.9375F;
			break;
		case 6:
			widthReduced = 0;
			height = 1F;
			break;
		case 7:
			widthReduced = 0.1875F;
			height = 1F;
			break;
		case 8:
			widthReduced = 0.125F;
			height = 1F;
			break;
		case 9:
			widthReduced = 0;
			height = 0.9375F;
			break;
		case 10:
			widthReduced = 0.125F;
			height = 1F;
			break;
		case 11:
			widthReduced = 0.125F;
			height = 1F;
			break;
		case 12:
			widthReduced = 0.0625F;
			height = 0.4375F;
			break;
		case 13:
			widthReduced = 0.1875F;
			height = 1F;
			break;
		}
		setBlockBounds(0F + widthReduced, 0.0F, 0F + widthReduced, 1F - widthReduced, height, 1F - widthReduced);
	}

	@Override
	public void registerIcons(IconRegister reg) {
		int i = 0;
		for (String path : iconPaths)
			icons[i++] = reg.registerIcon("erebus:" + path);
	}

	@Override
	public Icon getIcon(int side, int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
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
	public int idDropped(int id, Random random, int fortune) {
		return blockID;
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
		int blockBelow = world.getBlockId(x, y - 1, z);
		Block soil = Block.blocksList[blockBelow];
		if (y >= 0 && y < 256 && meta < 5) 
			return (blockBelow == Block.mycelium.blockID || world.getFullBlockLightValue(x, y, z) < 13) && (soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this));
		 else if (y >= 0 && y < 256 && meta >= 5)
			return soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
		
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta==13){
		double xx = x + 0.5F;
		double yy = y + 1F;
		double zz = z + 0.5F;
		world.spawnParticle("flame", xx, yy, zz, 0.0D, 0.0D, 0.0D);
		}
	}
}
