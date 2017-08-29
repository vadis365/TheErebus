package erebus.blocks;

import java.util.Random;

import erebus.Erebus;
import erebus.ModTabs;
import erebus.entity.EntityGasVent;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSwampVent extends BlockGrass {

	public BlockSwampVent() {
		super();
		setHardness(0.6F);
		setTickRandomly(true);
		setCreativeTab(ModTabs.BLOCKS);
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
				vent.setFlameType((byte) 0);
				world.spawnEntity(vent);
				world.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.BLOCKS, 0.5F, 0.1F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
			}
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (world.isAirBlock(pos.up())) {
			double xx = pos.getX() + 0.5F;
			double yy = pos.getY() + 1.0F;
			double zz = pos.getZ() + 0.5F;
			Erebus.PROXY.spawnCustomParticle("bubblegas", world, xx, yy, zz, 0.1D, 0.0D, 0.1D);
		}
	}

}