package erebus.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class InsectRepellent extends Block {
	public InsectRepellent() {
		super(Material.circuits);
		setTickRandomly(true);
		setBlockName("erebus.insectRepellent");
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isAir(IBlockAccess world, int x, int y, int z) {
		return true;
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
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y - 1, z);
		if (block == null)
			return false;
		if (block == this && (world.getBlockMetadata(x, y - 1, z) & 7) == 7)
			return true;
		if (!block.isLeaves(world, x, y - 1, z) && !block.isOpaqueCube())
			return false;
		return block.getMaterial().blocksMovement();
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return null;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		Random random = world.rand;
		double d0 = 0.0625D;

		for (int l = 0; l < 6; ++l) {
			double particleX = x + random.nextFloat();
			double particleY = y + random.nextFloat();
			double particleZ = z + random.nextFloat();

			if (l == 0 && !world.getBlock(x, y + 1, z).isOpaqueCube())
				particleY = y + 1 + d0;

			if (l == 1 && !world.getBlock(x, y - 1, z).isOpaqueCube())
				particleY = y + 0 - d0;

			if (l == 2 && !world.getBlock(x, y, z + 1).isOpaqueCube())
				particleZ = z + 1 + d0;

			if (l == 3 && !world.getBlock(x, y, z - 1).isOpaqueCube())
				particleZ = z + 0 - d0;

			if (l == 4 && !world.getBlock(x + 1, y, z).isOpaqueCube())
				particleX = x + 1 + d0;

			if (l == 5 && !world.getBlock(x - 1, y, z).isOpaqueCube())
				particleX = x + 0 - d0;

			if (particleX < x || particleX > x + 1 || particleY < 0.0D || particleY > y + 1 || particleZ < z || particleZ > z + 1)
				Erebus.proxy.spawnCustomParticle("repellent", world, particleX, particleY, particleZ, 0D, 0D, 0D);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (!world.isRemote && entity instanceof EntityLiving)
			if (entity.worldObj.getBlock(x, y, z) == ModBlocks.insectRepellent && ((EntityLiving) entity).getCreatureAttribute().equals(EnumCreatureAttribute.ARTHROPOD)) {
				int Knockback = 1;
				entity.addVelocity(MathHelper.sin(entity.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.1F, 0.1D, MathHelper.cos(entity.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.1F);
				entity.worldObj.playSoundAtEntity(entity, "game.player.hurt.fall.big", 1.0F, 1.0F);
			}
	}
}