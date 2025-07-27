package erebus.block.bamboo;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;

public class BambooPole extends Block {
	public static final MapCodec<BambooPole> CODEC = simpleCodec(BambooPole::new);
	public static final VoxelShape NERD_POLE = Block.box(6D, 0D, 6D, 10D, 16D, 10D);

	public BambooPole(Properties properties) {
		super(properties);
	}
	
	@Nonnull
	@Override
	protected MapCodec<BambooPole> codec() {
		return CODEC;
	}

	@Nonnull
	@Override
	public VoxelShape getShape(BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		return NERD_POLE;
	}

	@Nonnull
	@Override
	public VoxelShape getInteractionShape(BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos) {
		return NERD_POLE;
	}

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.MODEL;
	}

	public boolean canPlace(BlockGetter blockReader, BlockPos pos, Direction direction) { //TODO - this is now done by the item block (will have to add one)
		BlockState stateBelow = blockReader.getBlockState(pos);
		return stateBelow.getBlock() == this || stateBelow.isFaceSturdy(blockReader, pos, direction) && !stateBelow.is(BlockTags.LEAVES) && !stateBelow.is(BlockTags.AIR);
	}

}