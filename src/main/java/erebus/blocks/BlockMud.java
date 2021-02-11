package erebus.blocks;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModItems;
import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMud extends Block {

	protected static final AxisAlignedBB MUD_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);

	public BlockMud() {
		super(Material.GROUND);
		setHardness(2.0F);
		setSoundType(SoundType.GROUND);
		setCreativeTab(ModTabs.BLOCKS);
		setHarvestLevel("shovel", 0);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MUD_AABB;
	}

	public boolean canEntityWalkOnMud(Entity entity) {
		boolean wearingStriders = entity instanceof EntityPlayer && !((EntityPlayer) entity).inventory.armorInventory.isEmpty() && ((EntityPlayer) entity).inventory.armorInventory.get(0).getItem() == ModItems.WATER_STRIDERS;
		return  entity instanceof EntityItem || wearingStriders || (entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isCreativeMode && ((EntityPlayer) entity).capabilities.isFlying);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity, boolean isActualState) {
		AxisAlignedBB blockAABB = FULL_BLOCK_AABB.offset(pos);
		if (entityBox.intersects(blockAABB) && (entity == null || canEntityWalkOnMud(entity)))
			collidingBoxes.add(blockAABB);
		else if (world.isRemote) {
			blockAABB = MUD_AABB.offset(pos);
			if (entityBox.intersects(blockAABB)) {
				collidingBoxes.add(blockAABB);
			}
		}
	}

	@Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (!canEntityWalkOnMud(entity)) {
			entity.motionX *= 0.08D;
			if(!entity.isInWater() && entity.motionY < 0 && entity.onGround) entity.motionY = -0.1D;
			entity.motionZ *= 0.08D;
			if(!entity.isInWater()) {
				entity.setInWeb();
			} else {
				entity.motionY *= 0.02D;
			}
			entity.onGround = true;
			if(entity instanceof EntityLivingBase && entity.isInsideOfMaterial(Material.GROUND)) {
				entity.attackEntityFrom(DamageSource.IN_WALL, 2.0F);
			}
		}
	}

	@Override
	   public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable){
		return true;
	}
}