package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public class SiloRoofBlock extends Block {

	public static final MapCodec<SiloRoofBlock> CODEC = simpleCodec(SiloRoofBlock::new);
	protected static final VoxelShape SILO_ROOF_AABB = Block.box(0D, 0D, 0D, 16D, 12D, 16D);

	public SiloRoofBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected @NonNull MapCodec<SiloRoofBlock> codec() {
		return CODEC;
	}

    @Override
    protected boolean canSurvive(@NonNull BlockState state, LevelReader level, BlockPos pos) {
		return level.getBlockState(pos.below()).is(ModBlocks.SILO_TANK.get());
	}

	@Override
	protected @NonNull BlockState updateShape(@NonNull BlockState state, @NonNull LevelReader level, @NonNull ScheduledTickAccess ticks, @NonNull BlockPos pos, @NonNull Direction directionToNeighbour, @NonNull BlockPos neighbourPos, @NonNull BlockState neighbourState, @NonNull RandomSource random) {
		boolean canSurvive = canSurvive(state, level, pos);
        return !canSurvive ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
	}

	@Override
	public @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter worldIn, @NonNull BlockPos pos, @NonNull CollisionContext context) {
		return SILO_ROOF_AABB;
	}

	@Override
	public @NonNull RenderShape getRenderShape(@NonNull BlockState state) {
		return RenderShape.MODEL;
	}
}
