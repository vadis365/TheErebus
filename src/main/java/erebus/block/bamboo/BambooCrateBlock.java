package erebus.block.bamboo;

import erebus.block.entity.BambooCrateBlockEntity;
import erebus.block.types.EnumCrateType;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;

public class BambooCrateBlock extends Block implements EntityBlock {

    public static final EnumProperty<EnumCrateType> CRATE_TYPE = EnumProperty.create("crate_type", EnumCrateType.class);

    public BambooCrateBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(CRATE_TYPE, EnumCrateType.DEFAULT));
    }

    public static BlockPos getAnchor(BlockPos pos, EnumCrateType type) {
        return switch (type) {
            case BTR -> pos.offset(-1, 0, 0);
            case BBL -> pos.offset(0, 0, -1);
            case BBR -> pos.offset(-1, 0, -1);
            case TTL -> pos.below();
            case TTR -> pos.offset(-1, -1, 0);
            case TBL -> pos.offset(0, -1, -1);
            case TBR -> pos.offset(-1, -1, -1);
            default -> pos;
        };
    }

    public static boolean isFormed(LevelReader level, BlockPos anchor) {
        for (var pos : BlockPos.betweenClosed(anchor, anchor.offset(1, 1, 1))) {
            var state = level.getBlockState(pos);
            if (!state.is(ModBlocks.BAMBOO_CRATE) || state.getValue(CRATE_TYPE) == EnumCrateType.DEFAULT
                    || !getAnchor(pos, state.getValue(CRATE_TYPE)).equals(anchor)) return false;
        }
        return true;
    }

    @Override
    public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new BambooCrateBlockEntity(pos, state);
    }

    @Override
    public BlockState getStateForPlacement(@NonNull BlockPlaceContext context) {
        return this.defaultBlockState().setValue(CRATE_TYPE, EnumCrateType.DEFAULT);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CRATE_TYPE);
    }

    @Override
    protected @NonNull BlockState updateShape(@NonNull BlockState state, @NonNull LevelReader level, @NonNull ScheduledTickAccess ticks, @NonNull BlockPos pos, @NonNull Direction directionToNeighbour, @NonNull BlockPos neighbourPos, @NonNull BlockState neighbourState, @NonNull RandomSource random) {
        if (isCrate(level, pos)) {
            if (isCrate(level, pos.above()) && isCrate(level, pos.offset(1, 1, 0)) && isCrate(level, pos.offset(1, 1, 1)) && isCrate(level, pos.offset(0, 1, 1)))
                if (isCrate(level, pos.offset(1, 0, 0)) && isCrate(level, pos.offset(1, 0, 1)) && isCrate(level, pos.offset(0, 0, 1)))
                    return state.setValue(CRATE_TYPE, EnumCrateType.BTL);

            if (isCrate(level, pos.above()) && isCrate(level, pos.offset(-1, 1, 0)) && isCrate(level, pos.offset(-1, 1, 1)) && isCrate(level, pos.offset(0, 1, 1)))
                if (isCrate(level, pos.offset(-1, 0, 0)) && isCrate(level, pos.offset(-1, 0, 1)) && isCrate(level, pos.offset(0, 0, 1)))
                    return state.setValue(CRATE_TYPE, EnumCrateType.BTR);

            if (isCrate(level, pos.above()) && isCrate(level, pos.offset(1, 1, 0)) && isCrate(level, pos.offset(1, 1, -1)) && isCrate(level, pos.offset(0, 1, -1)))
                if (isCrate(level, pos.offset(1, 0, 0)) && isCrate(level, pos.offset(1, 0, -1)) && isCrate(level, pos.offset(0, 0, -1)))
                    return state.setValue(CRATE_TYPE, EnumCrateType.BBL);

            if (isCrate(level, pos.above()) && isCrate(level, pos.offset(-1, 1, 0)) && isCrate(level, pos.offset(-1, 1, -1)) && isCrate(level, pos.offset(0, 1, -1)))
                if (isCrate(level, pos.offset(-1, 0, 0)) && isCrate(level, pos.offset(-1, 0, -1)) && isCrate(level, pos.offset(0, 0, -1)))
                    return state.setValue(CRATE_TYPE, EnumCrateType.BBR);

            if (isCrate(level, pos.below()) && isCrate(level, pos.offset(1, -1, 0)) && isCrate(level, pos.offset(1, -1, 1)) && isCrate(level, pos.offset(0, -1, 1)))
                if (isCrate(level, pos.offset(1, 0, 0)) && isCrate(level, pos.offset(1, 0, 1)) && isCrate(level, pos.offset(0, 0, 1)))
                    return state.setValue(CRATE_TYPE, EnumCrateType.TTL);

            if (isCrate(level, pos.below()) && isCrate(level, pos.offset(-1, -1, 0)) && isCrate(level, pos.offset(-1, -1, 1)) && isCrate(level, pos.offset(0, -1, 1)))
                if (isCrate(level, pos.offset(-1, 0, 0)) && isCrate(level, pos.offset(-1, 0, 1)) && isCrate(level, pos.offset(0, 0, 1)))
                    return state.setValue(CRATE_TYPE, EnumCrateType.TTR);

            if (isCrate(level, pos.below()) && isCrate(level, pos.offset(1, -1, 0)) && isCrate(level, pos.offset(1, -1, -1)) && isCrate(level, pos.offset(0, -1, -1)))
                if (isCrate(level, pos.offset(1, 0, 0)) && isCrate(level, pos.offset(1, 0, -1)) && isCrate(level, pos.offset(0, 0, -1)))
                    return state.setValue(CRATE_TYPE, EnumCrateType.TBL);

            if (isCrate(level, pos.below()) && isCrate(level, pos.offset(-1, -1, 0)) && isCrate(level, pos.offset(-1, -1, -1)) && isCrate(level, pos.offset(0, -1, -1)))
                if (isCrate(level, pos.offset(-1, 0, 0)) && isCrate(level, pos.offset(-1, 0, -1)) && isCrate(level, pos.offset(0, 0, -1)))
                    return state.setValue(CRATE_TYPE, EnumCrateType.TBR);
        }
        return state.setValue(CRATE_TYPE, EnumCrateType.DEFAULT);
    }

    @Override
    public @NotNull InteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level,
                                                @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        if (stack.is(ModItems.WAND_OF_ANIMATION) || stack.is(ModItems.BAMBOO_CRATE)) return InteractionResult.PASS;
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        var anchor = getAnchor(pos, state.getValue(CRATE_TYPE));
        if (state.getValue(CRATE_TYPE) != EnumCrateType.DEFAULT && !isFormed(level, anchor)) return InteractionResult.FAIL;
        if (level.getBlockEntity(anchor) instanceof BambooCrateBlockEntity crate) {
            player.openMenu(crate);
            return InteractionResult.SUCCESS_SERVER;
        }
        return InteractionResult.FAIL;
    }

    private boolean isCrate(LevelReader level, BlockPos pos) {
        return level.getBlockState(pos).is(ModBlocks.BAMBOO_CRATE.get());
    }
}
