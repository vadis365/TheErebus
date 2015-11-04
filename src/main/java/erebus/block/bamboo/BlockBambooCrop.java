package erebus.block.bamboo;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import erebus.item.ItemMaterials.DATA;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockBambooCrop extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	public BlockBambooCrop() {
		super(Material.wood);
		setTickRandomly(true);
		setCreativeTab(ModTabs.plants);
		setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return meta >= 8 && rand.nextInt(17) <= 3 ? null : ModItems.materials;
	}

	@Override
	public int damageDropped(int meta) {
		return DATA.bamboo.ordinal();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 || side == 0 ? iconTop : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		super.registerBlockIcons(reg);
		iconTop = reg.registerIcon("erebus:bambooCropTop");
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		if (metadata == 0 && world.rand.nextInt(metadata >= 8 ? 35 : 20) == 0) {
			ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
			ret.add(new ItemStack(ModItems.materials, 1, DATA.bambooShoot.ordinal()));
			return ret;
		}
		return super.getDrops(world, x, y, z, metadata, fortune);
	}

	@Override
	public int getRenderType() {
		return BlockRenderIDs.BAMBOO_CROP.id();
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
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);

		if ((meta & 7) != 0)
			if (BlockBambooShoot.calculateBambooHappiness(world, x, y, z, this) > rand.nextInt(110 - meta * 2) && world.isAirBlock(x, y + 1, z))
				world.setBlock(x, y + 1, z, this, meta - 1, 3);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y - 1, z);
		return world.getBlock(x, y - 1, z) == this || block != null && block.isSideSolid(world, x, y, z, ForgeDirection.UP);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return canPlaceBlockAt(world, x, y, z);
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
		Block block = world.getBlock(x, y - 1, z);
		if (!(world.getBlock(x, y - 1, z) == this && world.getBlockMetadata(x, y - 1, z) == 0 || block != null && block.isSideSolid(world, x, y, z, ForgeDirection.UP)))
			return false;

		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack is) {
		Block block = world.getBlock(x, y - 1, z);
		if (!(world.getBlock(x, y - 1, z) == this && world.getBlockMetadata(x, y - 1, z) == 0 || block != null && block.isSideSolid(world, x, y, z, ForgeDirection.UP)))
			world.setBlockToAir(x, y, z);
	}
}