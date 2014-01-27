package erebus.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ErebusMod;
import erebus.ModItems;
import erebus.item.ItemErebusMaterial;

public class BlockBambooCrop extends Block {

	@SideOnly(Side.CLIENT)
	private Icon iconTop;

	public BlockBambooCrop(int id) {
		super(id, Material.wood);
		setTickRandomly(true);
		setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return meta >= 8 && rand.nextInt(17) <= 3 ? 0 : ModItems.erebusMaterials.itemID;
	}

	@Override
	public int damageDropped(int meta) {
		return ItemErebusMaterial.dataBamboo;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 1 || side == 0 ? iconTop : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		super.registerIcons(iconRegister);
		iconTop = iconRegister.registerIcon("erebus:bambooCropTop");
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		if (metadata == 0 && world.rand.nextInt(metadata >= 8 ? 35 : 20) == 0) {
			ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
			ret.add(new ItemStack(ModItems.erebusMaterials.itemID, 1, ItemErebusMaterial.dataBambooShoot));
			return ret;
		}
		return super.getBlockDropped(world, x, y, z, metadata, fortune);
	}

	@Override
	public int getRenderType() {
		return ErebusMod.proxy.bambooCropRenderID;
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
			if (BlockBambooShoot.calculateBambooHappiness(world, x, y, z, blockID) > rand.nextInt(110 - meta * 2) && world.isAirBlock(x, y + 1, z))
				world.setBlock(x, y + 1, z, blockID, meta - 1, 3);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockId) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		Block block = Block.blocksList[world.getBlockId(x, y - 1, z)];
		return world.getBlockId(x, y - 1, z) == blockID || block != null && block.isBlockSolidOnSide(world, x, y, z, ForgeDirection.UP);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return canPlaceBlockAt(world, x, y, z);
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack is) {
		Block block = Block.blocksList[world.getBlockId(x, y - 1, z)];
		if (!(world.getBlockId(x, y - 1, z) == blockID && world.getBlockMetadata(x, y - 1, z) == 0 || block != null && block.isBlockSolidOnSide(world, x, y, z, ForgeDirection.UP)))
			return false;

		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack is) {
		Block block = Block.blocksList[world.getBlockId(x, y - 1, z)];
		if (!(world.getBlockId(x, y - 1, z) == blockID && world.getBlockMetadata(x, y - 1, z) == 0 || block != null && block.isBlockSolidOnSide(world, x, y, z, ForgeDirection.UP)))
			world.setBlockToAir(x, y, z);
	}
}