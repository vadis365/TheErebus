package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import erebus.ErebusMod;
import erebus.ModBlocks;

public class BlockInsectRepellent extends Block {

	public BlockInsectRepellent(int id) {
		super(id, Material.air);
		setTickRandomly(true);
		setTextureName("erebus:blockInsectRepellent");
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
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
		int l = world.getBlockId(x, y - 1, z);
		Block block = Block.blocksList[l];
		if (block == null)
			return false;
		if (block == this && (world.getBlockMetadata(x, y - 1, z) & 7) == 7)
			return true;
		if (!block.isLeaves(world, x, y - 1, z) && !Block.blocksList[l].isOpaqueCube())
			return false;
		return world.getBlockMaterial(x, y - 1, z).blocksMovement();
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return 0;

	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		Random random = world.rand;
		double d0 = 0.0625D;

		for (int l = 0; l < 6; ++l) {
			double particleX = x + random.nextFloat();
			double particleY = y + random.nextFloat();
			double particleZ = z + random.nextFloat();

			if (l == 0 && !world.isBlockOpaqueCube(x, y + 1, z))
				particleY = y + 1 + d0;

			if (l == 1 && !world.isBlockOpaqueCube(x, y - 1, z))
				particleY = y + 0 - d0;

			if (l == 2 && !world.isBlockOpaqueCube(x, y, z + 1))
				particleZ = z + 1 + d0;

			if (l == 3 && !world.isBlockOpaqueCube(x, y, z - 1))
				particleZ = z + 0 - d0;

			if (l == 4 && !world.isBlockOpaqueCube(x + 1, y, z))
				particleX = x + 1 + d0;

			if (l == 5 && !world.isBlockOpaqueCube(x - 1, y, z))
				particleX = x + 0 - d0;

			if (particleX < x || particleX > x + 1 || particleY < 0.0D || particleY > y + 1 || particleZ < z || particleZ > z + 1)
				ErebusMod.proxy.spawnCustomParticle("repellent", world, particleX, particleY, particleZ, 0D, 0D, 0D);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (!world.isRemote && entity instanceof EntityLiving)
			if (entity.worldObj.getBlockId(x, y, z) == ModBlocks.insectRepellentID && ((EntityLiving) entity).getCreatureAttribute().equals(EnumCreatureAttribute.ARTHROPOD)) {
				int Knockback = 1;
				entity.addVelocity(MathHelper.sin(entity.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.1F, 0.1D, MathHelper.cos(entity.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.1F);
				entity.worldObj.playSoundAtEntity(entity, "damage.fallbig", 1.0F, 1.0F);
			}
	}

	@ForgeSubscribe
	public void onArthropodSpawn(LivingSpawnEvent.CheckSpawn e) {
		if (e.entity instanceof EntityLivingBase)
			if (e.entity.worldObj.getBlockId((int) e.x, (int) e.y, (int) e.z) == ModBlocks.insectRepellentID)
				if (((EntityLivingBase) e.entity).getCreatureAttribute().equals(EnumCreatureAttribute.ARTHROPOD))
					e.setResult(Result.DENY);
	}
}