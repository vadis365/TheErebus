package erebus.block.altars;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.LightningAltarBlockEntity;
import erebus.registries.ModItems;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class LightningAltar extends AltarAbstract {
	
	public static final MapCodec<LightningAltar> CODEC = simpleCodec(LightningAltar::new);

	public LightningAltar(Properties properties) {
		super(properties);
	}
	
    @Override
    protected MapCodec<LightningAltar> codec() {
        return CODEC;
    }

	@Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new LightningAltarBlockEntity(pos, state);
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@Nonnull Level pLevel, @Nonnull BlockState pState, @Nonnull BlockEntityType<T> pBlockEntityType) {
		return LightningAltarBlockEntity::tick;
	}

	@Override
	 protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
		BlockEntity blockEntity =  level.getBlockEntity(pos);
		if (blockEntity instanceof LightningAltarBlockEntity altar)
			altar.setActive(false);
	}

	@Override
	public ItemInteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (level.isClientSide()) {
			return ItemInteractionResult.SUCCESS;
		} else if (blockEntity instanceof LightningAltarBlockEntity altar) {
			if (!stack.isEmpty())
				if (stack.getItem() == ModItems.WAND_OF_ANIMATION.get()) {
					stack.hurtAndBreak(1, player, Player.getSlotForHand(hand));
					if (!altar.active) {
						altar.setActive(true);
						altar.setSpawnTicks(12000);
						level.playSound(null, pos, ModSounds.ALTAR_CHANGE_STATE.get(), SoundSource.BLOCKS, 1.0F, 1.3F);
						return ItemInteractionResult.SUCCESS;
					}
					if (altar.active) {
						altar.setActive(false);
						level.playSound(null, pos, ModSounds.ALTAR_CHANGE_STATE.get(), SoundSource.BLOCKS, 1.0F, 1.3F);
						return ItemInteractionResult.SUCCESS;
					}
				}
		}
		return ItemInteractionResult.FAIL;
	}
}