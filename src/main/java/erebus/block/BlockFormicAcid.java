package erebus.block;

import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModFluids;
import erebus.ModMaterials;
import erebus.ModTabs;

public class BlockFormicAcid extends BlockFluidClassic {

	@SideOnly(Side.CLIENT)
	protected IIcon stillIcon, flowingIcon;

	public BlockFormicAcid() {
		super(ModFluids.formicAcid, ModMaterials.formicAcid);
		setBlockName("erebus.formicAcid");
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return side == 0 || side == 1 ? stillIcon : flowingIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		stillIcon = register.registerIcon("erebus:formic_acid");
		flowingIcon = register.registerIcon("erebus:formic_acid_flow");
	}

	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial().isLiquid())
			return false;
		return super.canDisplace(world, x, y, z);
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial().isLiquid())
			return false;
		return super.displaceIfPossible(world, x, y, z);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			((EntityLivingBase) entity).attackEntityFrom(DamageSource.generic, 2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		if (world.isAirBlock(x, y + 1, z)) {

			float xx = (float) x + 0.5F;
			double yy = y + 1.0F;
			float zz = (float) z + 0.5F;
			float fixedOffset = 0.25F;
			float randomOffset = rand.nextFloat() * 0.6F - 0.3F;

			Erebus.proxy.spawnCustomParticle("bubblegasAcid", world, (double) (xx - fixedOffset), (double) y + 0.75D, (double) (zz + randomOffset), 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("bubblegasAcid", world, (double) (xx + fixedOffset), (double) y + 0.75D, (double) (zz + randomOffset), 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("bubblegasAcid", world, (double) (xx + randomOffset), (double) y + 0.75D, (double) (zz - fixedOffset), 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("bubblegasAcid", world, (double) (xx + randomOffset), (double) y + 0.75D, (double) (zz + fixedOffset), 0.0D, 0.0D, 0.0D);

			Erebus.proxy.spawnCustomParticle("swampflame", world, (double) (xx - fixedOffset), yy, (double) (zz + randomOffset), 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("swampflame", world, (double) (xx + fixedOffset), yy, (double) (zz + randomOffset), 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("swampflame", world, (double) (xx + randomOffset), yy, (double) (zz - fixedOffset), 0.0D, 0.0D, 0.0D);
			Erebus.proxy.spawnCustomParticle("swampflame", world, (double) (xx + randomOffset), yy, (double) (zz + fixedOffset), 0.0D, 0.0D, 0.0D);
		}
	}
}