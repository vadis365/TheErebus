package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.BlenderBlockEntity;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.transfer.fluid.FluidUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class BlenderBlock extends BaseEntityBlock {

    public static final Property<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final MapCodec<BlenderBlock> CODEC = simpleCodec(BlenderBlock::new);

    public BlenderBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    protected @NotNull BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    protected @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new BlenderBlockEntity(pos, state);
    }

    @Override
    public @NotNull InteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else if (blockEntity instanceof BlenderBlockEntity blender) {
            if(player.getItemInHand(hand).is(Items.BOOK)) {
                player.getItemInHand(hand).shrink(1);
                player.addItem(new ItemStack(ModItems.SMOOTHIE_BOOK.get()));
            }
            FluidUtil.interactWithFluidHandler(player, hand, level, pos, hit.getDirection());
            player.openMenu(blender, pos);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        return BlenderBlockEntity::tick;
    }
}
