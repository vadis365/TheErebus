package erebus.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import erebus.core.proxy.CommonProxy;
import erebus.tileentity.TileEntityComposter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockComposter extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon composterTop, composterBottom;

	public BlockComposter() {
		super(Material.rock);
		setCreativeTab(ModTabs.blocks);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? composterTop : side == 0 ? composterBottom : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon("erebus:composterSide");
		composterTop = reg.registerIcon("erebus:composterTop");
		composterBottom = reg.registerIcon("erebus:composterBottom");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		else {
			TileEntityComposter composter = (TileEntityComposter) world.getTileEntity(x, y, z);

			if (composter != null)
				player.openGui(Erebus.instance, CommonProxy.GuiID.COMPOSTER.ordinal(), world, x, y, z);

			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityComposter();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		Utils.dropInventoryContents(Utils.getTileEntity(world, x, y, z, TileEntityComposter.class));
		world.func_147453_f(x, y, z, block);
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		if (world.getBlockMetadata(x, y, z) == 1) {
			float f = x + 0.5F;
			float f1 = y + 1.1F + rand.nextFloat() * 6.0F / 16.0F;
			float f2 = z + 0.5F;
			world.spawnParticle("happyVillager", f, f1, f2, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
		return Container.calcRedstoneFromInventory(Utils.getTileEntity(world, x, y, z, IInventory.class));
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
		return BlockRenderIDs.COMPOSTER.id();
	}
}