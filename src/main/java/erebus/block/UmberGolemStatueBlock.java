package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.UmberGolemStatueBlockEntity;
import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NonNull;

public class UmberGolemStatueBlock extends BaseEntityBlock {
    public static final MapCodec<UmberGolemStatueBlock> CODEC = simpleCodec(UmberGolemStatueBlock::new);
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    public UmberGolemStatueBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected @NonNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected @NonNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected @NonNull BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected @NonNull RenderShape getRenderShape(@NonNull BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new UmberGolemStatueBlockEntity(pos, state);
    }

    @Override
    protected @NonNull InteractionResult useItemOn(ItemStack stack, @NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hit) {
        if (!stack.is(ModItems.WAND_OF_ANIMATION)) return InteractionResult.PASS;
        if (!level.mayInteract(player, pos) || !player.mayUseItemAt(pos, hit.getDirection(), stack)) return InteractionResult.FAIL;
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        var golem = ModEntities.UMBER_GOLEM.get().create(level, EntitySpawnReason.TRIGGERED);
        if (golem == null) return InteractionResult.FAIL;
        golem.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        golem.setYRot(state.getValue(FACING).toYRot());
        golem.yBodyRot = golem.getYRot();
        golem.yHeadRot = golem.getYRot();
        golem.setOwnerId(player.getUUID());
        if (!level.isUnobstructed(golem) || !level.addFreshEntity(golem)) return InteractionResult.FAIL;
        if (!level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3)) {
            golem.discard();
            return InteractionResult.FAIL;
        }
        stack.hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        level.playSound(null, pos, ModSounds.ALTAR_OFFERING.get(), SoundSource.BLOCKS, 0.2F, 1);
        return InteractionResult.SUCCESS_SERVER;
    }
}
