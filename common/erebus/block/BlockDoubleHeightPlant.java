package erebus.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.item.ItemErebusMaterial.DATA;

public class BlockDoubleHeightPlant extends Block {

	public static final String[] plantName = new String[] { "Sundew", "WeepingBlue", "Bullrush", "DroughtedShrub", "Grass", "Shroom1", "Shroom2", "Fern" };
	@SideOnly(Side.CLIENT)
	private Icon[] doublePlantBottomIcons;
	@SideOnly(Side.CLIENT)
	private Icon[] doublePlantTopIcons;

	public BlockDoubleHeightPlant(int id) {
		super(id, Material.plants);
		setBlockBounds(0F, 0F, 0F, 1F, 1.F, 1F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		doublePlantBottomIcons = new Icon[plantName.length];
		doublePlantTopIcons = new Icon[plantName.length];

		for (int i = 0; i < doublePlantBottomIcons.length; ++i) {
			doublePlantBottomIcons[i] = reg.registerIcon("erebus:doublePlant" + plantName[i] + "Bottom");
			doublePlantTopIcons[i] = reg.registerIcon("erebus:doublePlant" + plantName[i] + "Top");
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if (meta <= 7)
			return doublePlantBottomIcons[meta];
		else
			return doublePlantTopIcons[meta - 8];
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
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
	public int getRenderType() {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess access, int x, int y, int z) {
		int meta = access.getBlockMetadata(x, y, z);
		if (meta == 4 || meta == 12 || meta == 7 || meta == 15)
			return access.getBiomeGenForCoords(x, z).getBiomeGrassColor();
		return 16777215;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		int l = world.getBlockId(x, y - 1, z);
		int m = world.getBlockId(x, y + 1, z);
		Block block = Block.blocksList[l];
		if (block == null || m != 0)
			return false;
		if (block == this && world.getBlockMetadata(x, y - 1, z) < 8)
			return true;
		if (!block.isLeaves(world, x, y - 1, z) && !block.isOpaqueCube())
			return false;
		else
			return world.getBlockMaterial(x, y - 1, z).blocksMovement();
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		world(world, x, y, z);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (world.getBlockId(x, y + 1, z) == 0 && world.getBlockId(x, y, z) == blockID && world.getBlockMetadata(x, y, z) <= 7) {
			int meta = world.getBlockMetadata(x, y, z);
			world.setBlock(x, y + 1, z, blockID, meta + 8, 3);
		}
	}

	protected boolean world(World world, int x, int y, int z) {
		if (world.getBlockId(x, y - 1, z) == 0) {
			world.setBlockToAir(x, y, z);
			return false;
		}
		return true;
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int id, EntityPlayer player) {
		ItemStack item = null;
		int meta = world.getBlockMetadata(x, y, z);

		if (meta <= 7)
			world.setBlockToAir(x, y + 1, z);
		else
			world.setBlockToAir(x, y - 1, z);

		if (meta > 7)
			meta -= 8;

		switch (meta) {
			case 0:
				item = new ItemStack(ModItems.erebusMaterials, 1, DATA.bioLuminescence.ordinal());
				break;
			case 1:
				item = new ItemStack(ModItems.erebusMaterials, 1, DATA.weepingBluePetal.ordinal());
				break;
			case 2:
				item = new ItemStack(ModItems.erebusMaterials, 1, DATA.papyrus.ordinal());
				break;
			case 3:
			case 4:
			case 7:
				item = ForgeHooks.getGrassSeed(world);
				break;
			case 5:
				item = new ItemStack(ModBlocks.doubleHeightPlant, 1, meta);
				break;
			case 6:
				item = new ItemStack(ModBlocks.doubleHeightPlant, 1, meta);
				break;
		}
		Utils.dropStack(world, (int) (x + 0.5D), (int) (y + 0.5D), (int) (z + 0.5D), item);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < doublePlantBottomIcons.length; ++i)
			list.add(new ItemStack(id, 1, i));
	}
}
