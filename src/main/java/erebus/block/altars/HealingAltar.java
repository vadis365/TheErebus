package erebus.block.altars;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.HealingAltarBlockEntity;
import erebus.registries.ModSounds;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HealingAltar extends AltarAbstract {
	public static final MapCodec<HealingAltar> CODEC = simpleCodec(HealingAltar::new);

	public HealingAltar(Properties properties) {
		super(properties);
	}

    @Override
    protected @NonNull MapCodec<HealingAltar> codec() {
        return CODEC;
    }

	@Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new HealingAltarBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@Nonnull Level pLevel, @Nonnull BlockState pState, @Nonnull BlockEntityType<T> pBlockEntityType) {
		return HealingAltarBlockEntity::tick;
	}

	@Override
	 protected void onPlace(@NonNull BlockState state, Level level, @NonNull BlockPos pos, @NonNull BlockState oldState, boolean movedByPiston) {
		BlockEntity blockEntity =  level.getBlockEntity(pos);
		if (blockEntity instanceof HealingAltarBlockEntity altar)
			altar.setActive(false);
	}

	@Nonnull
	@Override
	public InteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		} else if (blockEntity instanceof HealingAltarBlockEntity altar) {
			if (!stack.isEmpty())
				if (stack.getItem() == ModItems.WAND_OF_ANIMATION.get()) {
					stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
					if (!altar.active) {
						altar.setActive(true);
						altar.setSpawnTicks(12000);
                    } else {
						altar.setActive(false);
                    }
                    level.playSound(null, pos, ModSounds.ALTAR_CHANGE_STATE.get(), SoundSource.BLOCKS, 1.0F, 1.3F);
                    return InteractionResult.SUCCESS;
                }
		}
		return InteractionResult.FAIL;
	}
}