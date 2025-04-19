package erebus.block;

import erebus.Erebus;
import erebus.registries.ModBlocks;
import erebus.registries.ModItems;
import erebus.utils.AdvancedBlockPos;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;

public class GaeanKeystoneBlock extends Block {

    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    private static final int LEAF_SEARCH = 8;
    private static final int MAX_PORTAL_SIZE = 81;
    private static final byte F = 1, L = 2, END = -1;
    private static final byte[] portalFrame = new byte[]{0, F, F, F, 0, END, F, L, L, L, F, END, F, L, L, L, F, END, F, L, L, L, F, END, 0, F, F, F, 0, END,};

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
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (state.getValue(ACTIVE)) {
            if (!stack.isEmpty()) {
                return ItemInteractionResult.SUCCESS;
            }
            breakPortal(level, pos);
            level.setBlock(pos, state.setValue(ACTIVE, false), 3);
            player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.PORTAL_ACTIVATOR.get()));
            return ItemInteractionResult.SUCCESS;
        }

        if (stack.isEmpty() || stack.getItem() != ModItems.PORTAL_ACTIVATOR.asItem()) {
            return ItemInteractionResult.FAIL;
        }

        if (makePortal(level, pos)) {
            level.setBlock(pos, state.setValue(ACTIVE, true), 3);
            player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
        } else {
            level.setBlock(pos, state.setValue(ACTIVE, false), 3);
        }

        return ItemInteractionResult.SUCCESS;
    }

    private void breakPortal(Level level, BlockPos pos) {
        Erebus.LOGGER.debug("GaeanKeystoneBlock: Breaking portal at {}", pos);
        AdvancedBlockPos here = new AdvancedBlockPos(level, pos);
        AdvancedBlockPos min = here.add(-LEAF_SEARCH, -LEAF_SEARCH, -LEAF_SEARCH);
        AdvancedBlockPos max = here.add(LEAF_SEARCH, LEAF_SEARCH, LEAF_SEARCH);

        Erebus.LOGGER.debug("GaeanKeystoneBlock: Searching for portal blocks in cube from {} to {}", min, max);
        here.iterateCube(min, max, at -> {
            BlockState state = level.getBlockState(at);
            if (!state.is(ModBlocks.PORTAL.get())) {
                Erebus.LOGGER.debug("GaeanKeystoneBlock: Found portal block at {}, skipping", at);
                return false;
            }

            Erebus.LOGGER.debug("GaeanKeystoneBlock: Starting portal search from {}", at);
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

            Erebus.LOGGER.debug("GaeanKeystoneBlock: Found {} portal blocks to remove", found.size());
            for (AdvancedBlockPos abp : found) {
                level.setBlockAndUpdate(abp, Blocks.AIR.defaultBlockState());
            }
            return true;
        });
        Erebus.LOGGER.debug("GaeanKeystoneBlock: Portal breaking completed");
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

        for (AdvancedBlockPos at : contig) {
            level.setBlockAndUpdate(at, ModBlocks.PORTAL.get().defaultBlockState());
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

    public void buildDestinationPortal(Level level, BlockPos pos) {
        Erebus.LOGGER.debug("GaeanKeystoneBlock: Building destination portal at {}", pos);
        AdvancedBlockPos keystone;
        BlockPos.MutableBlockPos check = pos.mutable();

        Erebus.LOGGER.debug("GaeanKeystoneBlock: Finding ground level");
        while (level.getBlockState(check).is(Blocks.AIR) && check.getY() > 0) {
            check.move(Direction.DOWN);
        }

        check.move(Direction.UP);
        Erebus.LOGGER.debug("GaeanKeystoneBlock: Found ground at {}", check);
        final int C = 5;
        int r = 5 / 2;

        BlockPos.MutableBlockPos min = check.move(-r, -1, -r);
        BlockPos.MutableBlockPos max = check.move(r, -1, r);
        keystone = new AdvancedBlockPos(level, check);
        Erebus.LOGGER.debug("GaeanKeystoneBlock: Portal area defined from {} to {} with keystone at {}", min, max, keystone);

        Erebus.LOGGER.debug("GaeanKeystoneBlock: Clearing space for portal");
        keystone.iterateCube(min, max, at -> {
            at.ensureFloored();
            int yMin = at.getY() + 1;
            int yMax = at.getY() + C;

            for (int y = yMin; y <= yMax; y++) {
                level.setBlockAndUpdate(new BlockPos(at.getX(), y, at.getZ()), Blocks.AIR.defaultBlockState());
            }

            return false;
        });

        Erebus.LOGGER.debug("GaeanKeystoneBlock: Building portal frame");
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
                        Erebus.LOGGER.debug("GaeanKeystoneBlock: Placing leaf block at {},{},{}", start.getX() + dx, start.getY() + dy, start.getZ() + dz);
                    } else {
                        state = Blocks.AIR.defaultBlockState();
                        Erebus.LOGGER.debug("GaeanKeystoneBlock: Hard difficulty, using air instead of leaf at {},{},{}", start.getX() + dx, start.getY() + dy, start.getZ() + dz);
                    }
                } else {
                    state = level.random.nextBoolean() ? ModBlocks.UMBERTILE_SMOOTH.get().defaultBlockState() : ModBlocks.UMBERTILE_SMOOTH_SMALL.get().defaultBlockState();
                    Erebus.LOGGER.debug("GaeanKeystoneBlock: Placing frame block at {},{},{}", start.getX() + dx, start.getY() + dy, start.getZ() + dz);
                }

                level.setBlock(new BlockPos(start.getX() + dx, start.getY() + dy, start.getZ() + dz), state, 3);
            }
            dx++;
        }

        Erebus.LOGGER.debug("GaeanKeystoneBlock: Placing keystone at {}", keystone);
        level.setBlockAndUpdate(keystone, ModBlocks.GAEAN_KEYSTONE.get().defaultBlockState());
        Erebus.LOGGER.debug("GaeanKeystoneBlock: Destination portal building completed");
    }
}
