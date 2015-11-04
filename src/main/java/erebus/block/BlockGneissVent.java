package erebus.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.entity.EntityGasVent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockGneissVent extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon topIcon;

	public BlockGneissVent() {
		super(Material.rock);
		setHardness(30F);
		setResistance(6000000.0F);
		setStepSound(soundTypeStone);
		setBlockName("erebus.gneissVent");
		setCreativeTab(ModTabs.blocks);
		setTickRandomly(true);
	}

	@Override
	public int tickRate(World world) {
		return 10;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? topIcon : blockIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon("erebus:gneissTiles");
		topIcon = reg.registerIcon("erebus:gneissTilesCracked");
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote)
			if (world.isAirBlock(x, y + 1, z)) {
				EntityGasVent vent = new EntityGasVent(world);
				vent.setPosition(x + 0.5D, y + 1D, z + 0.5D);
				vent.setFlameType((byte) 1);
				world.spawnEntityInWorld(vent);
				world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "mob.ghast.fireball", 0.5F, 0.1F);
			}
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		return 0;
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		return null;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		world.setBlock(x, y, z, Blocks.flowing_lava);
	}
}