package erebus.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;

public class VelocityBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final VoxelShape VELOCITY_AABB = Block.box(0D, 0D, 0D, 16D, 14D, 16D);

	public VelocityBlock(Block.Properties properties) {
		super(properties);
	}

	@Nonnull
	@Override
	public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		return VELOCITY_AABB;
	}
	
	@Nonnull
	@Override
	public VoxelShape getInteractionShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos) {
		return Shapes.block();
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction direction = context.getHorizontalDirection().getOpposite();
		return this.defaultBlockState().setValue(FACING, direction);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public void entityInside(@Nonnull BlockState state, @Nonnull Level world, @Nonnull BlockPos pos, Entity entity) {
		if (entity.isShiftKeyDown())
			return;

		if (entity instanceof Mob)
			((Mob) entity).setPersistenceRequired();
		else if (entity instanceof ItemEntity)
			((ItemEntity) entity).setExtendedLifetime();

		double speed = speed();
		int meta = state.getValue(FACING).get3DDataValue() - 2;
		int[] factorX = { 0, 0, -1, 1 };
		int[] factorZ = { -1, 1, 0, 0 };

		if (entity.getY() > pos.getY() + 0.5D) {
			Vec3 vec3d = entity.getDeltaMovement();
			if (factorX[meta] == 0 && Math.abs(pos.getX() + 0.5D - entity.getX()) < 0.5D && Math.abs(pos.getX() + 0.5D - entity.getX()) > 0.1D)
				entity.setDeltaMovement(vec3d.x + Math.signum(pos.getX() + 0.5D - entity.getX()) * Math.min(speed, Math.abs(pos.getX() + 0.5D - entity.getX())) / 1.2D, vec3d.y, vec3d.z);
			if (factorZ[meta] == 0 && Math.abs(pos.getZ() + 0.5D - entity.getZ()) < 0.5D && Math.abs(pos.getZ() + 0.5D - entity.getZ()) > 0.1D)
				entity.setDeltaMovement(vec3d.x, vec3d.y, vec3d.z + Math.signum(pos.getZ() + 0.5D - entity.getZ()) * Math.min(speed, Math.abs(pos.getZ() + 0.5D - entity.getZ())) / 1.2D);

			entity.setDeltaMovement(vec3d.x + factorX[meta] * speed, vec3d.y, vec3d.z + factorZ[meta] * speed);

		}
	}

	protected double speed() {
		return 0.2D;
	}

}
