package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModTabs;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import erebus.entity.EntityGasVent;

public class BlockSwampVent extends Block {

	public BlockSwampVent() {
		super(Material.grass);
		setHardness(0.6F);
		setTickRandomly(true);
		setStepSound(soundTypeGrass);
		setCreativeTab(ModTabs.blocks);
	}

	@Override
	public int tickRate(World world) {
		return 10;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return Blocks.grass.getBlockTextureFromSide(side);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote)
			if (world.isAirBlock(x, y + 1, z)) {
				EntityGasVent vent = new EntityGasVent(world);
				vent.setPosition(x + 0.5D, y + 1D, z + 0.5D);
				vent.setFlameType((byte) 0);
				world.spawnEntityInWorld(vent);
				world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "mob.ghast.fireball", 0.5F, 0.1F);
			}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		if (world.isAirBlock(x, y + 1, z)) {
			double xx = x + 0.5F;
			double yy = y + 1.0F;
			double zz = z + 0.5F;
			Erebus.proxy.spawnCustomParticle("bubblegas", world, xx, yy, zz, 0.1D, 0.0D, 0.1D);
		}
	}

	@Override
	public Item getItemDropped(int id, Random rand, int fortune) {
		return Blocks.dirt.getItemDropped(0, rand, fortune);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		return Blocks.grass.getBlockTextureFromSide(side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		double d0 = 0.5D;
		double d1 = 1.0D;
		return ColorizerGrass.getGrassColor(d0, d1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int colour) {
		return Blocks.grass.getBlockColor();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		int l = 0;
		int i1 = 0;
		int j1 = 0;

		for (int k1 = -1; k1 <= 1; ++k1)
			for (int l1 = -1; l1 <= 1; ++l1) {
				int i2 = world.getBiomeGenForCoords(x + l1, z + k1).getBiomeGrassColor(x + l1, y, z + k1);
				l += (i2 & 16711680) >> 16;
				i1 += (i2 & 65280) >> 8;
				j1 += i2 & 255;
			}

		return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
	}

	@SideOnly(Side.CLIENT)
	public static IIcon getIconSideOverlay() {
		return BlockGrass.getIconSideOverlay();
	}

	@Override
	public int getRenderType() {
		return BlockRenderIDs.SWAMP_VENT.id();
	}
}