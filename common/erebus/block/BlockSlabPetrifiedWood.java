package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;

public class BlockSlabPetrifiedWood extends BlockHalfSlab {

	public BlockSlabPetrifiedWood(int id, boolean isDouble) {
		super(id, isDouble, Material.rock);
		setHardness(2.0F);
		setLightOpacity(0);
		setStepSound(Block.soundWoodFootstep);
		setTextureName("erebus:petrifiedWoodPlanks");
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return ModBlocks.petrifiedWoodSlab[0].blockID;
	}

	@Override
	protected ItemStack createStackedBlock(int meta) {
		return new ItemStack(ModBlocks.petrifiedWoodSlab[0], isDoubleSlab ? 2 : 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int x, int y, int z) {
		return ModBlocks.petrifiedWoodSlab[0].blockID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return ModBlocks.petrifiedWoodPlanks.getIcon(side, 0);
	}

	@Override
	public String getFullSlabName(int meta) {
		return super.getUnlocalizedName();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
	}
}
