package erebus.block.bamboo;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState below = context.getLevel().getBlockState(context.getClickedPos().below());
        // This is a placement restriction, not a survival rule: extenders can grow downwards.
        boolean supported = below.is(this) || below.blocksMotion()
                && (!below.is(BlockTags.LEAVES) || below.isSolidRender());
        return supported ? super.getStateForPlacement(context) : null;
    }

}
