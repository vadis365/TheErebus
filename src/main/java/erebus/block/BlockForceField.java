package erebus.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockForceField extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon blockIcon;

	public BlockForceField() {
		super(Material.glass);
		setStepSound(soundTypeGlass);
		setBlockName("erebus.forceField");
		setCreativeTab(ModTabs.blocks);
		setBlockUnbreakable();
		setResistance(6000000.0F);
		setLightLevel(0.8F);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		return world.getBlock(x, y, z) == this ? false : super.shouldSideBeRendered(world, x, y, z, side);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return blockIcon;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("erebus:forceField");
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		Random random = world.rand;
		double pixel = 0.0625D;
		if (rand.nextInt(5) == 0)
			for (int l = 0; l < 6; ++l) {
				double particleX = x + random.nextFloat();
				double particleY = y + random.nextFloat();
				double particleZ = z + random.nextFloat();

				if (l == 0 && !world.getBlock(x, y + 1, z).isOpaqueCube())
					particleY = y + 1 + pixel;

				if (l == 1 && !world.getBlock(x, y - 1, z).isOpaqueCube())
					particleY = y - pixel;

				if (l == 2 && !world.getBlock(x, y, z + 1).isOpaqueCube())
					particleZ = z + 1 + pixel;

				if (l == 3 && !world.getBlock(x, y, z - 1).isOpaqueCube())
					particleZ = z - pixel;

				if (l == 4 && !world.getBlock(x + 1, y, z).isOpaqueCube())
					particleX = x + 1 + pixel;

				if (l == 5 && !world.getBlock(x - 1, y, z).isOpaqueCube())
					particleX = x - pixel;

				if (particleX < x || particleX > x + 1 || particleY < 0.0D || particleY > y + 1 || particleZ < z || particleZ > z + 1)
					Erebus.proxy.spawnCustomParticle("sparks", world, particleX, particleY, particleZ, 0, 0, 0);
			}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		double twoPixels = 0.125D;
		return AxisAlignedBB.getBoundingBox(x + twoPixels, y, z + twoPixels, x + 1 - twoPixels, y + 1, z + 1 - twoPixels);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			int Knockback = 1;
			entity.attackEntityFrom(DamageSource.cactus, 1);
			entity.addVelocity(MathHelper.sin(entity.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.1F, 0.08D, -MathHelper.cos(entity.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.1F);
			entity.worldObj.playSoundAtEntity(entity, "erebus:glowwormhurt", 1.0F, 1.0F);
		}
	}

}