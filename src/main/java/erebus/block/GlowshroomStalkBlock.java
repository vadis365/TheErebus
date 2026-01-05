package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.block.types.EnumGlowshroomPart;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;

public class GlowshroomStalkBlock extends Block {
	public static final MapCodec<GlowshroomStalkBlock> CODEC = simpleCodec(GlowshroomStalkBlock::new);
    public static final EnumProperty<EnumGlowshroomPart> PART = EnumProperty.create("part", EnumGlowshroomPart.class);

    public GlowshroomStalkBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(PART, EnumGlowshroomPart.MAIN));
    }

	@Nonnull
	@Override
	protected MapCodec<GlowshroomStalkBlock> codec() {
		return CODEC;
	}

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PART);
    }

	@Override
	 public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(PART, EnumGlowshroomPart.MAIN);
	}

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.MODEL;
	}

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return isValidBlock(level.getBlockState(pos.below())) || isValidBlock(level.getBlockState(pos.above())) || isValidBlock(level.getBlockState(pos.north())) || isValidBlock(level.getBlockState(pos.south())) || isValidBlock(level.getBlockState(pos.west())) || isValidBlock(level.getBlockState(pos.east()));
	}

	@SuppressWarnings("deprecation")
	private boolean isValidBlock(BlockState state) {
		 return state.isSolid() || state.is(this);
	}

	@Nonnull
	@Override
	public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		EnumGlowshroomPart part = state.getValue(PART);
		float widthMin = 0, heightMin = 0, depthMin = 0;
		float widthMax = 16F, heightMax = 16F, depthMax = 16F;

		switch (part) {
			case MAIN:
				break;
		//down
			case DOWN_1:
				widthMin = 5F;
				heightMin = 8F;
				depthMin = 5F;
				widthMax = 11F;
				heightMax = 16F;
				depthMax = 11F;
				break;
			case DOWN_2:
				widthMin = 5F;
				heightMin = 0F;
				depthMin = 5F;
				widthMax = 11F;
				heightMax = 16F;
				depthMax = 11F;
				break;
			case DOWN_3:
				widthMin = 3F;
				heightMin = 0F;
				depthMin = 3F;
				widthMax = 3F;
				heightMax = 16F;
				depthMax = 3F;
				break;
		//north		
			case NORTH_1:
				widthMin = 5F;
				heightMin = 5F;
				depthMin = 5F;
				widthMax = 11F;
				heightMax = 11F;
				depthMax = 16F;
				break;
			case NORTH_2:
				widthMin = 3F;
				heightMin = 3F;
				depthMin = 0F;
				widthMax = 13F;
				heightMax = 13F;
				depthMax = 16F;
				break;
			case NORTH_3:
				widthMin = 5F;
				heightMin = 5F;
				depthMin = 5F;
				widthMax = 11F;
				heightMax = 16F;
				depthMax = 16F;
				break;
				
		//south
			case SOUTH_1:
				widthMin = 5F;
				heightMin = 5F;
				depthMin = 0F;
				widthMax = 11F;
				heightMax = 11F;
				depthMax = 11F;
				break;
			case SOUTH_2:
				widthMin = 3F;
				heightMin = 3F;
				depthMin = 0F;
				widthMax = 13F;
				heightMax = 13F;
				depthMax = 16F;
				break;
			case SOUTH_3:
				widthMin = 5F;
				heightMin = 5F;
				depthMin = 0F;
				widthMax = 11F;
				heightMax = 16F;
				depthMax = 11F;
				break;
		//west
			case WEST_1:
				widthMin = 5F;
				heightMin = 5F;
				depthMin = 5F;
				widthMax = 16F;
				heightMax = 11F;
				depthMax = 11F;
				break;
			case WEST_2:
				widthMin = 0F;
				heightMin = 3F;
				depthMin = 3F;
				widthMax = 16F;
				heightMax = 13F;
				depthMax = 13F;
				break;
			case WEST_3:
				widthMin = 5F;
				heightMin = 5F;
				depthMin = 5F;
				widthMax = 16F;
				heightMax = 16F;
				depthMax = 11F;
				break;
		//east
			case EAST_1:
				widthMin = 0F;
				heightMin = 5F;
				depthMin = 5F;
				widthMax = 11F;
				heightMax = 11F;
				depthMax = 11F;
				break;
			case EAST_2:
				widthMin = 0F;
				heightMin = 3F;
				depthMin = 3F;
				widthMax = 16F;
				heightMax = 13F;
				depthMax = 13F;
				break;
			case EAST_3:
				widthMin = 0F;
				heightMin = 5F;
				depthMin = 5F;
				widthMax = 11F;
				heightMax = 16F;
				depthMax = 11F;
				break;		
		}

		VoxelShape voxelshape = Block.box(widthMin, heightMin, depthMin, widthMax, heightMax, depthMax);
		return voxelshape;
	}

	@Override
	 protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		EnumGlowshroomPart part = state.getValue(PART);

		switch (part) {
		case MAIN: {
			switch (random.nextInt(5)) {
			case 0:
				if (level.isEmptyBlock(pos.below()))
					level.setBlock(pos.below(), defaultBlockState().setValue(PART, EnumGlowshroomPart.DOWN_1), 2);
				break;
			case 1:
				if (level.isEmptyBlock(pos.north()) && level.getBlockState(pos.above()).is(ModBlocks.GLOWSHROOM_STALK.get()))
					level.setBlock(pos.north(), defaultBlockState().setValue(PART, EnumGlowshroomPart.NORTH_1), 2);
				break;
			case 2:
				if (level.isEmptyBlock(pos.south()) && level.getBlockState(pos.above()).is(ModBlocks.GLOWSHROOM_STALK.get()))
					level.setBlock(pos.south(), defaultBlockState().setValue(PART, EnumGlowshroomPart.SOUTH_1), 2);
				break;
			case 3:
				if (level.isEmptyBlock(pos.west()) && level.getBlockState(pos.above()).is(ModBlocks.GLOWSHROOM_STALK.get()))
					level.setBlock(pos.west(), defaultBlockState().setValue(PART, EnumGlowshroomPart.WEST_1), 2);
				break;
			case 4:
				if (level.isEmptyBlock(pos.east()) && level.getBlockState(pos.above()).is(ModBlocks.GLOWSHROOM_STALK.get()))
					level.setBlock(pos.east(), defaultBlockState().setValue(PART, EnumGlowshroomPart.EAST_1), 2);
				break;
			}

			if (random.nextInt(10) == 0)
				if (level.isEmptyBlock(pos.above()))
					level.setBlock(pos.above(), ModBlocks.GLOWSHROOM_BLOCK.get().defaultBlockState(), 2);
			break;
		}
		case DOWN_1:
			level.setBlock(pos, defaultBlockState().setValue(PART, EnumGlowshroomPart.DOWN_2), 2);
			break;
		case DOWN_2:
			level.setBlock(pos, defaultBlockState().setValue(PART, EnumGlowshroomPart.DOWN_3), 2);
			break;
		case DOWN_3:
			level.setBlock(pos, defaultBlockState().setValue(PART, EnumGlowshroomPart.MAIN), 2);
			break;
		case EAST_1:
			if (random.nextBoolean()) {
				level.setBlock(pos, defaultBlockState().setValue(PART, EnumGlowshroomPart.EAST_2), 2);
				if (level.isEmptyBlock(pos.east()))
					level.setBlock(pos.east(), defaultBlockState().setValue(PART, EnumGlowshroomPart.EAST_1), 2);
			} else if (level.isEmptyBlock(pos.above())) {
				level.setBlock(pos, defaultBlockState().setValue(PART, EnumGlowshroomPart.EAST_3), 2);
				level.setBlock(pos.above(), ModBlocks.GLOWSHROOM_BLOCK.get().defaultBlockState(), 2);
			}
			break;
		case EAST_2:
			if (level.getBlockState(pos.west()) == defaultBlockState().setValue(PART, EnumGlowshroomPart.MAIN) && level.getBlockState(pos.east()) != defaultBlockState().setValue(PART, EnumGlowshroomPart.EAST_3)) {
				level.setBlock(pos, defaultBlockState().setValue(PART, EnumGlowshroomPart.MAIN), 2);
				if (level.getBlockState(pos.east()) == defaultBlockState().setValue(PART, EnumGlowshroomPart.EAST_1))
					level.setBlock(pos.east(), defaultBlockState().setValue(PART, EnumGlowshroomPart.EAST_2), 2);
			}
			break;
		case NORTH_1:
			if (random.nextBoolean()) {
				level.setBlock(pos, defaultBlockState().setValue(PART, EnumGlowshroomPart.NORTH_2), 2);
				if (level.isEmptyBlock(pos.north()))
					level.setBlock(pos.north(), defaultBlockState().setValue(PART, EnumGlowshroomPart.NORTH_1), 2);
			} else if (level.isEmptyBlock(pos.above())) {
				level.setBlock(pos, defaultBlockState().setValue(PART, EnumGlowshroomPart.NORTH_3), 2);
				level.setBlock(pos.above(), ModBlocks.GLOWSHROOM_BLOCK.get().defaultBlockState(), 2);
			}
			break;
		case NORTH_2:
			if (level.getBlockState(pos.south()) == defaultBlockState().setValue(PART, EnumGlowshroomPart.MAIN) && level.getBlockState(pos.north()) != defaultBlockState().setValue(PART, EnumGlowshroomPart.NORTH_3)) {
				level.setBlock(pos, defaultBlockState().setValue(PART, EnumGlowshroomPart.MAIN), 2);
				if (level.getBlockState(pos.north()) == defaultBlockState().setValue(PART, EnumGlowshroomPart.NORTH_1))
					level.setBlock(pos.north(), defaultBlockState().setValue(PART, EnumGlowshroomPart.NORTH_2), 2);
			}
			break;
		case SOUTH_1:
			if (random.nextBoolean()) {
				level.setBlock(pos, defaultBlockState().setValue(PART, EnumGlowshroomPart.SOUTH_2), 2);
				if (level.isEmptyBlock(pos.south()))
					level.setBlock(pos.south(), defaultBlockState().setValue(PART, EnumGlowshroomPart.SOUTH_1), 2);
			} else if (level.isEmptyBlock(pos.above())) {
				level.setBlock(pos, defaultBlockState().setValue(PART, EnumGlowshroomPart.SOUTH_3), 2);
				level.setBlock(pos.above(), ModBlocks.GLOWSHROOM_BLOCK.get().defaultBlockState(), 2);
			}
			break;
		case SOUTH_2:
			if (level.getBlockState(pos.north()) == defaultBlockState().setValue(PART, EnumGlowshroomPart.MAIN) && level.getBlockState(pos.south()) != defaultBlockState().setValue(PART, EnumGlowshroomPart.SOUTH_3)) {
				level.setBlock(pos, defaultBlockState().setValue(PART, EnumGlowshroomPart.MAIN), 2);
				if (level.getBlockState(pos.south()) == defaultBlockState().setValue(PART, EnumGlowshroomPart.SOUTH_1))
					level.setBlock(pos.south(), defaultBlockState().setValue(PART, EnumGlowshroomPart.SOUTH_2), 2);
			}
			break;
		case WEST_1:
			if (random.nextBoolean()) {
				level.setBlock(pos, defaultBlockState().setValue(PART, EnumGlowshroomPart.WEST_2), 2);
				if (level.isEmptyBlock(pos.west()))
					level.setBlock(pos.west(), defaultBlockState().setValue(PART, EnumGlowshroomPart.WEST_1), 2);
			} else if (level.isEmptyBlock(pos.above())) {
				level.setBlock(pos, defaultBlockState().setValue(PART, EnumGlowshroomPart.WEST_3), 2);
				level.setBlock(pos.above(), ModBlocks.GLOWSHROOM_BLOCK.get().defaultBlockState(), 2);
			}
			break;
		case WEST_2:
			if (level.getBlockState(pos.east()) == defaultBlockState().setValue(PART, EnumGlowshroomPart.MAIN) && level.getBlockState(pos.west()) != defaultBlockState().setValue(PART, EnumGlowshroomPart.WEST_3)) {
				level.setBlock(pos, defaultBlockState().setValue(PART, EnumGlowshroomPart.MAIN), 2);
				if (level.getBlockState(pos.west()) == defaultBlockState().setValue(PART, EnumGlowshroomPart.WEST_1))
					level.setBlock(pos.west(), defaultBlockState().setValue(PART, EnumGlowshroomPart.WEST_2), 2);
			}
			break;
		case EAST_3:
		case NORTH_3:
		case SOUTH_3:
		case WEST_3:
			break;
		default:
			break;
		}
	}
	
	@Override
	protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
		EnumGlowshroomPart part = state.getValue(PART);
		boolean flag = true;
		if (part == EnumGlowshroomPart.MAIN)
			if (canSurvive(state, level, pos))
				flag = true;
		if (part == EnumGlowshroomPart.DOWN_1 || part == EnumGlowshroomPart.DOWN_2 || part == EnumGlowshroomPart.DOWN_3)
			if (!isValidBlock(level.getBlockState(pos.above())))
				flag = false;
		if (part == EnumGlowshroomPart.NORTH_1 || part == EnumGlowshroomPart.NORTH_2 || part == EnumGlowshroomPart.NORTH_3)
			if (!isValidBlock(level.getBlockState(pos.south())))
				flag = false;
		if (part == EnumGlowshroomPart.SOUTH_1 || part == EnumGlowshroomPart.SOUTH_2 || part == EnumGlowshroomPart.SOUTH_3)
			if (!isValidBlock(level.getBlockState(pos.north())))
				flag = false;
		if (part == EnumGlowshroomPart.EAST_1 || part == EnumGlowshroomPart.EAST_2 || part == EnumGlowshroomPart.EAST_3)
			if (!isValidBlock(level.getBlockState(pos.west())))
				flag = false;
		if (part == EnumGlowshroomPart.WEST_1 || part == EnumGlowshroomPart.WEST_2 || part == EnumGlowshroomPart.WEST_3)
			if (!isValidBlock(level.getBlockState(pos.east())))
				flag = false;

		return !flag ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, pos, facingPos);
	}
}
