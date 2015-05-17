package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import erebus.item.ItemMaterials;
import erebus.tileentity.TileEntityGaeanKeystone;

public class GaeanKeystone extends BlockContainer {
	@SideOnly(Side.CLIENT)
	private IIcon icons[];

	public GaeanKeystone() {
		super(Material.rock);
		setHardness(3.0f);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.gaeanKeystone");
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return ModItems.materials;
	}

	@Override
	public int damageDropped(int meta) {
		return ItemMaterials.DATA.gaeanGem.ordinal();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		icons = new IIcon[3];
		icons[0] = reg.registerIcon("erebus:keystone_top");
		icons[1] = reg.registerIcon("erebus:keystone_sides");
		icons[2] = reg.registerIcon("erebus:gaeanEye");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? icons[0] : side == 0 ? Blocks.stonebrick.getIcon(0, 1) : icons[1];
	}

	public static boolean isGemActive(int metadata) {
		return metadata > 0;
	}

	public IIcon getEyeIcon() {
		return icons[2];
	}

	@Override
	public int getRenderType() {
		return BlockRenderIDs.KEYSTONE.id();
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getCurrentEquippedItem();
		if (stack != null && stack.getItem() == ModItems.portalActivator) {
			if (!world.isRemote)
				if (ErebusPortal.makePortal(world, x, y - 2, z)) {
					world.setBlockMetadataWithNotify(x, y, z, 1, 3);
					stack.damageItem(1, player);
					return true;
				} else
					world.setBlockMetadataWithNotify(x, y, z, 0, 3);
			return true;
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityGaeanKeystone();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		super.breakBlock(world, x, y, z, block, meta);
		if (world.getBlock(x, y - 1, z) == ModBlocks.portal)
			world.setBlockToAir(x, y - 1, z);
		if (world.getBlock(x, y - 2, z) == ModBlocks.portal)
			world.setBlockToAir(x, y - 2, z);
	}
}