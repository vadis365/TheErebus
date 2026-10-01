package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.GaeanKeystoneBlockEntity;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.item.ModItems;
import erebus.utils.AdvancedBlockPos;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;

public class GaeanKeystoneBlock extends BaseEntityBlock {

    public static final MapCodec<GaeanKeystoneBlock> CODEC = simpleCodec(GaeanKeystoneBlock::new);
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    private static final int LEAF_SEARCH = 8;
    private static final int MAX_PORTAL_SIZE = 81;

    public GaeanKeystoneBlock(Properties properties) {
        super(properties);

        registerDefaultState(getStateDefinition().any()
                .setValue(ACTIVE, false)
        );
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new GaeanKeystoneBlockEntity(pos, state);
    }

    @Override
    protected @NotNull InteractionResult useItemOn(@NotNull ItemStack stack, BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return state.getValue(ACTIVE) || stack.is(ModItems.PORTAL_ACTIVATOR)
                    ? InteractionResult.SUCCESS : InteractionResult.PASS;
        }
        if (state.getValue(ACTIVE)) {
            if (!stack.isEmpty()) {
                return InteractionResult.SUCCESS;
            }
            breakPortal(level, pos);
            level.setBlock(pos, state.setValue(ACTIVE, false), 3);
            player.setItemInHand(hand, new ItemStack(ModItems.PORTAL_ACTIVATOR.get()));
            return InteractionResult.SUCCESS;
        }

        if (stack.isEmpty() || stack.getItem() != ModItems.PORTAL_ACTIVATOR.asItem()) {
            return InteractionResult.PASS;
        }

        if (makePortal(level, pos)) {
            level.setBlock(pos, state.setValue(ACTIVE, true), 3);
            player.setItemInHand(hand, ItemStack.EMPTY);
        } else {
            level.setBlock(pos, state.setValue(ACTIVE, false), 3);
        }

        return InteractionResult.SUCCESS;
    }

    private void breakPortal(Level level, BlockPos pos) {
        AdvancedBlockPos here = new AdvancedBlockPos(level, pos);
        AdvancedBlockPos min = here.add(-LEAF_SEARCH, -LEAF_SEARCH, -LEAF_SEARCH);
        AdvancedBlockPos max = here.add(LEAF_SEARCH, LEAF_SEARCH, LEAF_SEARCH);

        here.iterateCube(min, max, at -> {
            BlockState state = level.getBlockState(at);
            if (!state.is(ModBlocks.PORTAL.get())) {
                return false;
            }

            HashSet<AdvancedBlockPos> found = new HashSet<>();
            ArrayList<AdvancedBlockPos> frontier = new ArrayList<>();
            frontier.add(at);

            while (!frontier.isEmpty()) {
                AdvancedBlockPos f = frontier.removeLast();
                found.add(f);
                for (AdvancedBlockPos abp : f.neighbors()) {
                    if (found.contains(abp)) continue;
                    if (level.getBlockState(abp).is(ModBlocks.PORTAL)) frontier.add(abp);
                }
            }

            for (AdvancedBlockPos abp : found) {
                level.setBlockAndUpdate(abp, Blocks.AIR.defaultBlockState());
            }
            return true;
        });
    }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {
        super.affectNeighborsAfterRemoval(state, level, pos, movedByPiston);
        if (state.getValue(ACTIVE)) {
            breakPortal(level, pos);
            Block.popResource(level, pos, new ItemStack(ModItems.PORTAL_ACTIVATOR.get()));
        }
    }

    private boolean makePortal(Level level, BlockPos pos) {
        AdvancedBlockPos keystone = new AdvancedBlockPos(level, pos);
        AdvancedBlockPos min = keystone.add(-LEAF_SEARCH, -LEAF_SEARCH, -LEAF_SEARCH);
        AdvancedBlockPos max = keystone.add(LEAF_SEARCH, LEAF_SEARCH, LEAF_SEARCH);
        HashSet<AdvancedBlockPos> badLeaves = new HashSet<>();
        HashSet<AdvancedBlockPos> contig = new HashSet<>();

        keystone.iterateCube(min, max, at -> {
            if (!at.isLeaf()) {
                return false;
            }
            if (badLeaves.contains(at)) {
                return false;
            }

            if (visitLeaves(at, contig, badLeaves) && contig.size() < MAX_PORTAL_SIZE) {
                return true;
            }

            badLeaves.addAll(contig);
            contig.clear();
            return false;
        });

        if (contig.isEmpty()) {
            return false;
        }

        // Install the complete plane before notifying neighbors, as in the original leaf conversion.
        for (AdvancedBlockPos at : contig) {
            level.setBlock(at, ModBlocks.PORTAL.get().defaultBlockState(), Block.UPDATE_CLIENTS);
        }
        for (AdvancedBlockPos at : contig) {
            level.updateNeighborsAt(at, ModBlocks.PORTAL.get());
        }
        return true;
    }

    private boolean visitLeaves(AdvancedBlockPos start, HashSet<AdvancedBlockPos> contig, HashSet<AdvancedBlockPos> invalidLeaves) {
        ArrayList<AdvancedBlockPos> frontier = new ArrayList<>();
        frontier.add(start);

        while (!frontier.isEmpty()) {
            AdvancedBlockPos at = frontier.removeLast();

            for (AdvancedBlockPos pos : at.neighbors()) {
                if (!pos.isLeaf()) {
                    continue;
                }
                if (invalidLeaves.contains(pos)) {
                    return false;
                }
                if (contig.add(pos)) {
                    if (!pos.isValidLeafPortal()) {
                        invalidLeaves.addAll(frontier);
                        return false;
                    }
                    frontier.add(pos);
                }
            }
        }

        return true;
    }
}
