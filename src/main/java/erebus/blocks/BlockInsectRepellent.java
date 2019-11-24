package erebus.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import erebus.Erebus;
import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInsectRepellent extends Block {

	protected static final AxisAlignedBB REPELLENT_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);

	public BlockInsectRepellent() {
		super(Material.CIRCUITS);
		setTickRandomly(true);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return REPELLENT_AABB;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, BlockPos pos, net.minecraft.client.particle.ParticleManager manager) {
        return true;
    }

	@Override
	 public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.down());
		if (state.getBlock() == null)
			return false;
		return state.getMaterial().blocksMovement();
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		Random random = world.rand;
		double d0 = 0.0625D;

		for (int l = 0; l < 6; ++l) {
			double particleX = pos.getX() + random.nextFloat();
			double particleY = pos.getY() + random.nextFloat();
			double particleZ = pos.getZ() + random.nextFloat();

			if (l == 0 && !world.getBlockState(pos.up()).isOpaqueCube())
				particleY = pos.getY() + 1 + d0;

			if (l == 1 && !world.getBlockState(pos.down()).isOpaqueCube())
				particleY = pos.getY() - d0;

			if (l == 2 && !world.getBlockState(pos.add(0, 0, 1)).isOpaqueCube())
				particleZ = pos.getZ() + 1 + d0;

			if (l == 3 && !world.getBlockState(pos.add(0, 0, -1)).isOpaqueCube())
				particleZ = pos.getZ() - d0;

			if (l == 4 && !world.getBlockState(pos.add(1, 0, 0)).isOpaqueCube())
				particleX = pos.getX() + 1 + d0;

			if (l == 5 && !world.getBlockState(pos.add(-1, 0, 0)).isOpaqueCube())
				particleX = pos.getX() + 0 - d0;

			if (particleX < pos.getX() || particleX > pos.getX() + 1 || particleY < 0.0D || particleY > pos.getY() + 1 || particleZ < pos.getZ() || particleZ > pos.getZ() + 1)
				Erebus.PROXY.spawnCustomParticle("repellent", world, particleX, particleY, particleZ, 0D, 0D, 0D);
		}
	}

	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (!world.isRemote && entity instanceof EntityLiving)
			if (entity.getEntityWorld().getBlockState(pos).getBlock() == ModBlocks.INSECT_REPELLENT && ((EntityLiving) entity).getCreatureAttribute().equals(EnumCreatureAttribute.ARTHROPOD)) {
				int Knockback = 1;
				entity.addVelocity(MathHelper.sin(entity.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.1F, 0.1D, MathHelper.cos(entity.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.1F);
				//entity.getEntityWorld().playSound(null, pos, SoundEvents.ENTITY_PLAYER_SMALL_FALL, SoundCategory.BLOCKS, 1.0F, 1.0F);
			}
	}
}