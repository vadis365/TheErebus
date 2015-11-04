package erebus.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.item.ItemMaterials.DATA;
import erebus.item.block.ItemBlockGeneric;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockRedGem extends Block implements ISubBlocksBlock {

	public static final String[] iconPaths = new String[] { "redgem", "redlampOn", "redlampOff" };
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlockRedGem() {
		super(Material.glass);
		setHardness(0.3F);
		setLightLevel(1F);
		setStepSound(soundTypeGlass);
		setBlockName("erebus.redGem");
		setCreativeTab(ModTabs.blocks);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		icons = new IIcon[iconPaths.length];

		int i = 0;
		for (String path : iconPaths)
			icons[i++] = iconRegister.registerIcon("erebus:" + path);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs tab, List list) {
		list.add(new ItemStack(id, 1, 0));
		list.add(new ItemStack(id, 1, 1));
	}

	@Override
	public int damageDropped(int meta) {
		return meta == 1 || meta == 2 ? 1 : DATA.redGem.ordinal();
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		if (meta == 0)
			return 1 + random.nextInt(2 + fortune);
		return 1;
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		return meta == 0 ? ModItems.materials : Item.getItemFromBlock(this);
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
				world.scheduleBlockUpdate(x, y, z, this, 4);
			else if (meta != 2 && world.isBlockIndirectlyGettingPowered(x, y, z))
				world.setBlock(x, y, z, this, 2, 2);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborID) {
		int meta = world.getBlockMetadata(x, y, z);
		if (!world.isRemote && (meta == 1 || meta == 2))
			if (meta == 2 && !world.isBlockIndirectlyGettingPowered(x, y, z))
				world.scheduleBlockUpdate(x, y, z, this, 4);
			else if (meta != 2 && world.isBlockIndirectlyGettingPowered(x, y, z))
				world.setBlock(x, y, z, this, meta + 1, 2);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);
		if (!world.isRemote && meta == 2 && !world.isBlockIndirectlyGettingPowered(x, y, z))
			world.setBlock(x, y, z, this, 1, 2);
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 0 && side == ForgeDirection.UP)
			return true;
		if (meta == 1 || meta == 2)
			return true;
		return false;
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockGeneric.class;
	}
}