package erebus.block;

import erebus.block.portal.ErebusPortalBlock;
import erebus.registries.ModBlocks;
import erebus.registries.ModItems;
import erebus.utils.AdvancedBlockPos;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;

public class GaeanKeystoneBlock extends Block {

    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    static final byte F = 1, L = 2, END = -1;
    static final byte[] portalFrame = new byte[]{0, F, F, F, 0, END, F, L, L, L, F, END, F, L, L, L, F, END, F, L, L, L, F, END, 0, F, F, F, 0, END,};

    public GaeanKeystoneBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

        registerDefaultState(getStateDefinition().any()
                .setValue(ACTIVE, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState();
    }
    private static final int LEAF_SEARCH = 8;
    private static final int MAX_PORTAL_SIZE = 81;

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (state.getValue(ACTIVE)) {
            if (!stack.isEmpty()) return ItemInteractionResult.SUCCESS;
            breakPortal(level, pos);
            level.setBlock(pos, state.setValue(ACTIVE, false), 3);
            player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.PORTAL_ACTIVATOR.get()));
            return ItemInteractionResult.SUCCESS;
        }

        if (stack.isEmpty() || stack.getItem() != ModItems.PORTAL_ACTIVATOR.asItem()) return ItemInteractionResult.FAIL;

        if (makePortal(level, pos)) {
            level.setBlock(pos, state.setValue(ACTIVE, true), 3);
            player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
        } else {
            level.setBlock(pos, state.setValue(ACTIVE, false), 3);
        }

        return ItemInteractionResult.SUCCESS;
    }

    private void breakPortal(Level level, BlockPos pos) {
        AdvancedBlockPos start = new AdvancedBlockPos(level, pos);
        BlockPos.MutableBlockPos min = pos.mutable().move(-LEAF_SEARCH, -LEAF_SEARCH, -LEAF_SEARCH);
        BlockPos.MutableBlockPos max = pos.mutable().move(LEAF_SEARCH, LEAF_SEARCH, LEAF_SEARCH);

        start.iterateCube(min, max, at -> {
            BlockState state = level.getBlockState(at);
            if (state.is(ModBlocks.PORTAL.get())) return false;
            HashSet<BlockPos> found = new HashSet<>();
            ArrayList<BlockPos> toCheck = new ArrayList<>();
            toCheck.add(at);

            while (!toCheck.isEmpty()) {
                BlockPos checked = toCheck.removeLast();
                found.add(checked);
                for (BlockPos blockPos : getSurroundingNeighbors(checked)) {
                    if (found.contains(blockPos)) continue;
                    if (level.getBlockState(blockPos).is(ModBlocks.PORTAL)) toCheck.add(blockPos);
                }
            }

            for (BlockPos blockPos : found) {
                level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
            }
            return true;
        });
    }

    private boolean makePortal(Level level, BlockPos pos) {
        AdvancedBlockPos keystone = new AdvancedBlockPos(level, pos);
        BlockPos.MutableBlockPos min = pos.mutable().move(-LEAF_SEARCH, -LEAF_SEARCH, -LEAF_SEARCH);
        BlockPos.MutableBlockPos max = pos.mutable().move(LEAF_SEARCH, LEAF_SEARCH, LEAF_SEARCH);
        HashSet<BlockPos> badLeaves = new HashSet<>();
        HashSet<BlockPos> contig = new HashSet<>();

        keystone.iterateCube(min, max, at -> {
            BlockState state = level.getBlockState(at);
            if (state.is(BlockTags.LEAVES)) return false;
            if (badLeaves.contains(at)) return false;
            if (visitLeaves(level, at, contig, badLeaves) && contig.size() <= MAX_PORTAL_SIZE) {
                return true;
            }
            badLeaves.addAll(contig);
            contig.clear();
            return false;
        });

        if (contig.isEmpty()) return false;
        for (BlockPos at : contig) {
            level.setBlockAndUpdate(at, ModBlocks.PORTAL.get().defaultBlockState());
        }
        return true;
    }

    private BlockPos[] getSurroundingNeighbors(BlockPos pos) {
        BlockPos[] ret = new BlockPos[6];
        int i = 0;
        for (Direction dir : Direction.values()) {
            BlockPos.MutableBlockPos mutable = pos.mutable();
            ret[i++] = mutable.move(dir).immutable();
        }
        return ret;
    }

    private boolean visitLeaves(Level level, BlockPos start, HashSet<BlockPos> contig, HashSet<BlockPos> invalidLeaves) {
        ArrayList<BlockPos> frontier = new ArrayList<>();
        frontier.add(start);

        while (!frontier.isEmpty()) {
            BlockPos at = frontier.removeLast();
            for (BlockPos pos : getSurroundingNeighbors(at)) {
                if (!level.getBlockState(pos).is(BlockTags.LEAVES)) continue;
                if (invalidLeaves.contains(pos)) return false;
                if (contig.add(pos)) {
                    if (!ErebusPortalBlock.obeysPortalRule(level, pos, false)) {
                        invalidLeaves.addAll(frontier);
                        return false;
                    }
                    frontier.add(pos);
                }
            }
        }

        return true;
    }

    public void buildDestinationPortal(Level level, BlockPos pos) {
        AdvancedBlockPos keystone;
        BlockPos.MutableBlockPos check = pos.mutable();

        while (level.getBlockState(check).is(Blocks.AIR) && check.getY() > 0) {
            check.move(Direction.DOWN);
        }

        check.move(Direction.UP);
        final int C = 5;
        int r = 5 / 2;

        BlockPos.MutableBlockPos min = check.move(-r, -1, -r);
        BlockPos.MutableBlockPos max = check.move(r, -1, r);
        keystone = new AdvancedBlockPos(level, check);

        keystone.iterateCube(min, max, at -> {
            at.ensureFloored();
            int yMin = at.getY() + 1;
            int yMax = at.getY() + C;

            for (int y = yMin; y <= yMax; y++) {
                level.setBlockAndUpdate(new BlockPos(at.getX(), y, at.getZ()), Blocks.AIR.defaultBlockState());
            }

            return false;
        });

        BlockPos start = min.move(Direction.UP);
        int dx = 0;
        int dy = 0;
        int dz = 0;

        for (byte b : portalFrame) {
            if (b == END) {
                dy++;
                dx = 0;
                continue;
            } else if (b == F || b == L) {
                BlockState state;
                if (b == L) {
                    if (level.getDifficulty() != Difficulty.HARD) {
                        state = Blocks.OAK_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true);
                    } else {
                        state = Blocks.AIR.defaultBlockState();
                    }
                } else {
                    state = level.random.nextBoolean() ? ModBlocks.UMBERTILE_SMOOTH.get().defaultBlockState() : ModBlocks.UMBERTILE_SMOOTH_SMALL.get().defaultBlockState();
                }

                level.setBlock(new BlockPos(start.getX() + dx, start.getY() + dy, start.getZ() + dz), state, 3);
            }
            dx++;
        }

        level.setBlockAndUpdate(keystone, ModBlocks.GAEAN_KEYSTONE.get().defaultBlockState());
    }
}
