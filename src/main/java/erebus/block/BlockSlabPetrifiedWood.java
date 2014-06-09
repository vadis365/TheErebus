package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModBlocks.ISubBlocksBlock;
import erebus.item.block.ItemBlockSlabPetrifiedWood;

public class BlockSlabPetrifiedWood extends BlockSlab implements ISubBlocksBlock {

	public BlockSlabPetrifiedWood(boolean isDouble) {
		super(isDouble, Material.rock);
		setHardness(2.0F);
		setLightOpacity(0);
		setStepSound(Block.soundTypeWood);
		setBlockTextureName("erebus:petrifiedWoodPlanks");
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.petrifiedWoodSlab[0]);
	}

	@Override
	protected ItemStack createStackedBlock(int meta) {
		return new ItemStack(ModBlocks.petrifiedWoodSlab[0], field_150004_a ? 2 : 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return Item.getItemFromBlock(ModBlocks.petrifiedWoodSlab[0]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return ModBlocks.petrifiedWoodPlanks.getIcon(side, 0);
	}

	@Override
	public String func_150002_b(int meta) {
		return super.getUnlocalizedName();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockSlabPetrifiedWood.class;
	}
}