package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NonNull;

public class TempleSealBlock extends Block {
    public static final MapCodec<TempleSealBlock> CODEC = simpleCodec(TempleSealBlock::new);
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public TempleSealBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(ACTIVE, false));
    }

    @Override
    protected @NonNull MapCodec<TempleSealBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    private boolean accepts(ItemStack stack) {
        if (this == ModBlocks.TEMPLE_BRICK_UNBREAKING_JADE.get()) return stack.is(ModItems.JADE);
        if (this == ModBlocks.TEMPLE_BRICK_UNBREAKING_EXO.get()) return stack.is(ModItems.PLATE_EXO);
        if (this == ModBlocks.TEMPLE_BRICK_UNBREAKING_CREAM.get()) return stack.is(Items.MAGMA_CREAM);
        if (this == ModBlocks.TEMPLE_BRICK_UNBREAKING_EYE.get()) return stack.is(ModItems.MAGMA_CRAWLER_EYE);
        return this == ModBlocks.TEMPLE_BRICK_UNBREAKING_STRING.get() && stack.is(Items.STRING);
    }

    @Override
    public @NonNull InteractionResult useItemOn(@NonNull ItemStack stack, @NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hit) {
        if (player.isShiftKeyDown()) return InteractionResult.TRY_WITH_EMPTY_HAND;
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        BlockState current = level.getBlockState(pos);
        if (!current.is(this)) return InteractionResult.PASS;
        if (!current.getValue(ACTIVE) && accepts(stack) && level.setBlock(pos, current.setValue(ACTIVE, true), 3)) {
            if (!player.getAbilities().instabuild) stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }
}
