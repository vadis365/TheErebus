package erebus.block;

import java.util.Random;

import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockGhostSand extends Block {

	public BlockGhostSand() {
		super(Material.portal);
		setHardness(0.42F);
		setLightOpacity(255);
		setStepSound(soundTypeSand);
		setHarvestLevel("shovel", 0);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.ghostSand");
		setBlockTextureName("erebus:ghostSand");
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
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Item.getItemFromBlock(Blocks.sand);
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}
}