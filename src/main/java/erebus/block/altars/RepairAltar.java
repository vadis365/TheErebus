package erebus.block.altars;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import erebus.block.entity.RepairAltarBlockEntity;
import erebus.registries.ModItems;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class RepairAltar extends AltarAbstract {

	public static final MapCodec<RepairAltar> CODEC = simpleCodec(RepairAltar::new);

	public RepairAltar(Properties properties) {
		super(properties);
	}
	
    @Override
    protected MapCodec<RepairAltar> codec() {
        return CODEC;
    }

	@Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new RepairAltarBlockEntity(pos, state);
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@Nonnull Level pLevel, @Nonnull BlockState pState, @Nonnull BlockEntityType<T> pBlockEntityType) {
		return RepairAltarBlockEntity::tick;
	}

	@Override
	 protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
		BlockEntity blockEntity =  level.getBlockEntity(pos);
		if (blockEntity instanceof RepairAltarBlockEntity altar) {
			altar.setActive(false);
			altar.setcanBeUsed(true);
		}
	}

	@Override
	 public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof RepairAltarBlockEntity altar) {
				if (entity instanceof ItemEntity && altar.active) {
					altar.setCollisions(altar.getCollisions() + 1);
					ItemStack is = ((ItemEntity) entity).getItem();
					entity.yo = pos.getY() + 1.6D;
					int repairDamage = is.getMaxDamage();
					if (is.isRepairable() && repairDamage > 0) {
						if (altar.notUsed)
							altar.setSpawnTicks(160);
						if (altar.getSpawnTicks() == 60 && altar.getCollisions() == 101) {
							level.playSound(null, pos, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 0.2F, 1.0F);
							is.getItem().setDamage(is, -repairDamage);
						}
						if (altar.getSpawnTicks() % 2 == 0 && altar.getCollisions() < 101)
							if (level.isClientSide())
								altar.sparky(level, pos);
					}
					if (altar.getCollisions() > 101)
						altar.setSpawnTicks(0);
				}
			}
		}

	@Override
	public ItemInteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (level.isClientSide()) {
			return ItemInteractionResult.SUCCESS;
		} else if (blockEntity instanceof RepairAltarBlockEntity altar) {
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
