package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import erebus.item.ErebusMaterial.DATA;

public class BlockGlowshroom extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon bottomIcon;

	public BlockGlowshroom() {
		super(Material.circuits);
		setLightLevel(0.9375F);
		setBlockBounds(0.0625F, 0F, 0.0625F, 0.9375F, 1F, 0.9375F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 0 ? bottomIcon : blockIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon("erebus:mushroomYellow");
		bottomIcon = reg.registerIcon("erebus:mushroomYellowInside");
	}

	@Override
	public int getRenderType() {
		return BlockRenderIDs.GLOWSHROOM_CAPS.id();
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return true;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return ModItems.erebusMaterials;
	}

	@Override
	public int damageDropped(int meta) {
		return DATA.yellowDottedFungus.ordinal();
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x, y - 1, z));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x, y - 1, z));
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		return meta;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		int meta = world.getBlockMetadata(x, y, z);
		boolean flag = false;

		if (isValidBlock(world.getBlock(x, y - 1, z)))
			flag = true;

		if (!flag) {
			breakBlock(world, x, y, z, neighbour, meta);
			world.setBlockToAir(x, y, z);
		}

		super.onNeighborBlockChange(world, x, y, z, neighbour);
	}

	private boolean isValidBlock(Block block) {
		return block.getMaterial().blocksMovement();
	}
}