package erebus.blocks;

import java.util.Random;

import erebus.ModTabs;
import erebus.entity.EntityGasVent;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGneissVent extends Block {

	public BlockGneissVent() {
		super(Material.ROCK);
		setHardness(30F);
		setResistance(6000000.0F);
		setSoundType(SoundType.STONE);
		setCreativeTab(ModTabs.BLOCKS);
		setTickRandomly(true);
	}

	@Override
	public int tickRate(World world) {
		return 10;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote)
			if (world.isAirBlock(pos.up())) {
				EntityGasVent vent = new EntityGasVent(world);
				vent.setPosition(pos.getX() + 0.5D, pos.getY() + 1D, pos.getZ() + 0.5D);
				vent.setFlameType((byte) 1);
				world.spawnEntity(vent);
				world.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.BLOCKS, 0.5F, 0.1F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
			}
	}

	@Override
    public int quantityDropped(Random random) {
        return 0;
    }

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote)
			world.setBlockState(pos, Blocks.FLOWING_LAVA.getDefaultState(), 11);
	}
}