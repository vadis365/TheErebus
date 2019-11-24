package erebus.blocks;

import java.util.Random;

import erebus.Erebus;
import erebus.ModFluids;
import erebus.ModMaterials;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFormicAcid extends BlockFluidClassic {

	public BlockFormicAcid() {
		super(ModFluids.FORMIC_ACID, ModMaterials.FORMIC_ACID);
		setLightLevel(0.7F);
	}

	@Override
	public boolean canDisplace(IBlockAccess world, BlockPos pos) {
		if (world.getBlockState(pos).getMaterial().isLiquid())
			return false;
		return super.canDisplace(world, pos);
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos) {
		if (world.getBlockState(pos).getMaterial().isLiquid())
			return false;
		return super.displaceIfPossible(world, pos);
	}

	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			((EntityLivingBase) entity).attackEntityFrom(DamageSource.GENERIC, 2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (world.isAirBlock(pos.up())) {

			float xx = (float) pos.getX() + 0.5F;
			float yy = pos.getY() + 1.0F;
			float zz = (float) pos.getZ() + 0.5F;
			float fixedOffset = 0.25F;
			float randomOffset = rand.nextFloat() * 0.6F - 0.3F;

			Erebus.PROXY.spawnCustomParticle("bubblegasAcid", world, (double) (xx - fixedOffset), (double) pos.getY() + 0.75D, (double) (zz + randomOffset), 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("bubblegasAcid", world, (double) (xx + fixedOffset), (double) pos.getY() + 0.75D, (double) (zz + randomOffset), 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("bubblegasAcid", world, (double) (xx + randomOffset), (double) pos.getY() + 0.75D, (double) (zz - fixedOffset), 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("bubblegasAcid", world, (double) (xx + randomOffset), (double) pos.getY() + 0.75D, (double) (zz + fixedOffset), 0.0D, 0.0D, 0.0D);

			Erebus.PROXY.spawnCustomParticle("swampflame", world, (double) (xx - fixedOffset), yy, (double) (zz + randomOffset), 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("swampflame", world, (double) (xx + fixedOffset), yy, (double) (zz + randomOffset), 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("swampflame", world, (double) (xx + randomOffset), yy, (double) (zz - fixedOffset), 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("swampflame", world, (double) (xx + randomOffset), yy, (double) (zz + fixedOffset), 0.0D, 0.0D, 0.0D);
		}
	}
}