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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockQuickSand extends Block {

	public BlockQuickSand() {
		super(Material.SAND);
		setHardness(28F);
		setSoundType(SoundType.SAND);
		setHarvestLevel("shovel", 2);
		setCreativeTab(ModTabs.BLOCKS);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	public boolean canEntityWalkOnBlock(Entity entity) {
		boolean wearingStriders = entity instanceof EntityPlayer && !((EntityPlayer) entity).inventory.armorInventory.isEmpty() && ((EntityPlayer) entity).inventory.armorInventory.get(0).getItem() == ModItems.WATER_STRIDERS;
		return  entity instanceof EntityItem || wearingStriders || (entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isCreativeMode && ((EntityPlayer) entity).capabilities.isFlying);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity, boolean isActualState) {
		AxisAlignedBB blockAABB = FULL_BLOCK_AABB.offset(pos);
		if (entityBox.intersects(blockAABB) && (entity == null || canEntityWalkOnBlock(entity)))
			collidingBoxes.add(blockAABB);
	}

	@Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (!canEntityWalkOnBlock(entity)) {
			entity.motionX *= 0.08D;
			if(!entity.isInWater() && entity.motionY < 0 && entity.onGround) entity.motionY = -0.1D;
			entity.motionZ *= 0.08D;
			if(!entity.isInWater()) {
				entity.setInWeb();
			} else {
				entity.motionY *= 0.02D;
			}
			entity.onGround = true;
			if(entity instanceof EntityLivingBase && entity.isInsideOfMaterial(Material.SAND)) {
				entity.attackEntityFrom(DamageSource.IN_WALL, 2.0F);
			}
		}
	}

	@SubscribeEvent
	public void onEntityJump(LivingJumpEvent e) {
		if (e.getEntity().getEntityWorld().getBlockState(new BlockPos((int) Math.floor(e.getEntity().posX), (int) Math.floor(e.getEntity().posY) - 1, (int) Math.floor(e.getEntity().posZ))).getBlock() == this && !canEntityWalkOnBlock(e.getEntity()))
			e.getEntityLiving().motionY = 0D;
	}
}