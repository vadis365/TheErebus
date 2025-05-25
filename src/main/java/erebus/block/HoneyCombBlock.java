package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.HoneyCombBlockEntity;
import erebus.registries.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class HoneyCombBlock extends Block implements EntityBlock {
	
	public static final MapCodec<HoneyCombBlock> CODEC = simpleCodec(HoneyCombBlock::new);

	public HoneyCombBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	protected @NotNull MapCodec<HoneyCombBlock> codec() {
		return CODEC;
	}

    @Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new HoneyCombBlockEntity(pos, state);
	}

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.MODEL;
	}

    @Override
	public @NotNull ItemInteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
    	BlockEntity blockEntity = level.getBlockEntity(pos);
    	if (level.isClientSide()) {
			return ItemInteractionResult.SUCCESS;
    	} else if (blockEntity instanceof HoneyCombBlockEntity honeycomb) {
			if (!stack.isEmpty() && stack.is(blockEntity.getBlockState().getBlock().asItem()) || !stack.isEmpty() && stack.getItem() == ModItems.BEE_TAMING_AMULET.get())
				return ItemInteractionResult.FAIL;
			player.openMenu(honeycomb, pos);
		}
    	return ItemInteractionResult.SUCCESS;
	}

	@Override
	public void onRemove(@NotNull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
		HoneyCombBlockEntity tile = (HoneyCombBlockEntity) level.getBlockEntity(pos);
		if (tile != null)
			Containers.dropContents(level, pos, tile);
		level.levelEvent(2001, pos, Block.getId(state));
		super.onRemove(state, level, pos, newState, isMoving);
	}
}