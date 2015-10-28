package erebus.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityPreservedBlock;

public class BlockPreservedBlock extends BlockContainer {

	public BlockPreservedBlock() {
		super(Material.glass);
		setHardness(1.0F);
		setBlockName("erebus.preservedBlock");
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
		if (!world.isRemote && stack.hasTagCompound()) {
			world.setBlock(x, y + 1, z, ModBlocks.preservedBlock);
			TileEntityPreservedBlock tile = Utils.getTileEntity(world, x, y + 1, z, TileEntityPreservedBlock.class);
			if (tile != null)
				tile.setEntityNBT(stack.getTagCompound().getCompoundTag("EntityNBT"));
		}
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
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityPreservedBlock();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		// TODO move this to a better place
		TileEntityPreservedBlock tile = Utils.getTileEntity(world, x, y, z, TileEntityPreservedBlock.class);
		if (tile != null)
			tile.spawnTrappedEntity();

		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return ModBlocks.amber.getIcon(side, 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}
}