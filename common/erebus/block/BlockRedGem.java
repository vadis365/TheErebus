package erebus.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.item.ItemErebusMaterial;

public class BlockRedGem extends Block {

	public static final String[] iconPaths = new String[] { "redgem", "redlamp_on", "redlamp_off" };
	@SideOnly(Side.CLIENT)
	public static Icon[] icons;

	public BlockRedGem(int id) {
		super(id, Material.glass);
	}

	@Override
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[iconPaths.length];

		int i = 0;
		for (String path : iconPaths)
			icons[i++] = iconRegister.registerIcon("erebus:" + path);
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
		list.add(new ItemStack(id, 1, 0));
		list.add(new ItemStack(id, 1, 1));
	}

	@Override
	public int damageDropped(int meta) {
		return meta == 1 || meta == 2 ? 1 : ItemErebusMaterial.dataRedGem;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		if (meta == 0)
			return 1 + random.nextInt(2 + fortune);
		return 1;
	}

	@Override
	public int idDropped(int meta, Random random, int fortune) {
		return meta == 0 ? ModItems.erebusMaterials.itemID : blockID;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		int realMeta = world.getBlockMetadata(x, y, z);
		return realMeta == 2 ? 1 : realMeta;
	}

	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 2)
			return false;
		return true;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 0 || meta == 1)
			return 15;
		return 0;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (!world.isRemote && (meta == 1 || meta == 2))
			if (meta == 2 && !world.isBlockIndirectlyGettingPowered(x, y, z))
				world.scheduleBlockUpdate(x, y, z, blockID, 4);
			else if (meta != 2 && world.isBlockIndirectlyGettingPowered(x, y, z))
				world.setBlock(x, y, z, blockID, 2, 2);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		int meta = world.getBlockMetadata(x, y, z);
		if (!world.isRemote && (meta == 1 || meta == 2))
			if (meta == 2 && !world.isBlockIndirectlyGettingPowered(x, y, z))
				world.scheduleBlockUpdate(x, y, z, blockID, 4);
			else if (meta != 2 && world.isBlockIndirectlyGettingPowered(x, y, z))
				world.setBlock(x, y, z, blockID, meta + 1, 2);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);
		if (!world.isRemote && meta == 2 && !world.isBlockIndirectlyGettingPowered(x, y, z))
			world.setBlock(x, y, z, blockID, 1, 2);
	}

	@Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 0 && side == ForgeDirection.UP)
			return true;
		if (meta == 1 || meta == 2)
			return true;
		return false;
	}
}
