package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class CapstoneBlock extends Block {
    public static final MapCodec<CapstoneBlock> CODEC = simpleCodec(CapstoneBlock::new);
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public CapstoneBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(ACTIVE, false));
    }

    @Override
    protected @NonNull MapCodec<CapstoneBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    private boolean accepts(ItemStack stack) {
        if (this == ModBlocks.CAPSTONE_MUD.get()) return stack.is(ModItems.MUD_SCARAB);
        if (this == ModBlocks.CAPSTONE_IRON.get()) return stack.is(ModItems.IRON_SCARAB);
        if (this == ModBlocks.CAPSTONE_GOLD.get()) return stack.is(ModItems.GOLD_SCARAB);
        return this == ModBlocks.CAPSTONE_JADE.get() && stack.is(ModItems.JADE_SCARAB);
    }

    @Override
    protected @NonNull InteractionResult useItemOn(@NonNull ItemStack stack, @NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hit) {
        if (player.isShiftKeyDown()) return InteractionResult.TRY_WITH_EMPTY_HAND;
        if (!(level instanceof ServerLevel server)) return InteractionResult.SUCCESS;
        var current = server.getBlockState(pos);
        if (!current.is(this)) return InteractionResult.PASS;
        if (!current.getValue(ACTIVE) && accepts(stack) && server.setBlock(pos, current.setValue(ACTIVE, true), 3)) {
            stack.consume(1, player);
            tryOpen(server, pos);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void neighborChanged(BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Block neighbor,
                                   @Nullable Orientation orientation, boolean movedByPiston) {
        if (state.getValue(ACTIVE) && level instanceof ServerLevel server) tryOpen(server, pos);
    }

    private void tryOpen(ServerLevel level, BlockPos pos) {
        BlockPos mud = pos;
        if (this == ModBlocks.CAPSTONE_IRON.get()) mud = pos.west();
        else if (this == ModBlocks.CAPSTONE_GOLD.get()) mud = pos.north();
        else if (this == ModBlocks.CAPSTONE_JADE.get()) mud = pos.north().west();
        BlockPos[] positions = {mud, mud.east(), mud.south(), mud.south().east()};
        Block[] blocks = {ModBlocks.CAPSTONE_MUD.get(), ModBlocks.CAPSTONE_IRON.get(),
                ModBlocks.CAPSTONE_GOLD.get(), ModBlocks.CAPSTONE_JADE.get()};
        for (int i = 0; i < positions.length; i++) {
            if (!level.hasChunkAt(positions[i])) return;
            var state = level.getBlockState(positions[i]);
            if (!state.is(blocks[i]) || !state.getValue(ACTIVE)) return;
        }

        for (var target : positions) {
            level.levelEvent(2001, target, Block.getId(level.getBlockState(target)));
            level.setBlock(target, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
        }
        for (int i = 0; i < positions.length; i++) level.updateNeighborsAt(positions[i], blocks[i]);
        var lightning = EntityType.LIGHTNING_BOLT.create(level, EntitySpawnReason.TRIGGERED);
        if (lightning != null) {
            lightning.setPos(mud.getX() + 1, mud.getY(), mud.getZ() + 1);
            level.addFreshEntity(lightning);
        }
    }
}
