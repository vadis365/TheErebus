package erebus.block.altars;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.ExperienceAltarBlockEntity;
import erebus.registries.ModItems;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ExperienceAltar extends AltarAbstract {

	public static final MapCodec<ExperienceAltar> CODEC = simpleCodec(ExperienceAltar::new);

	public ExperienceAltar(Properties properties) {
		super(properties);
	}
	
    @Override
    protected MapCodec<ExperienceAltar> codec() {
        return CODEC;
    }

	@Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new ExperienceAltarBlockEntity(pos, state);
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@Nonnull Level pLevel, @Nonnull BlockState pState, @Nonnull BlockEntityType<T> pBlockEntityType) {
		return ExperienceAltarBlockEntity::tick;
	}

	@Override
	 protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
		BlockEntity blockEntity =  level.getBlockEntity(pos);
		if (blockEntity instanceof ExperienceAltarBlockEntity altar)
			altar.setActive(false);
	}

	@Override
	 public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity instanceof ExperienceAltarBlockEntity altar) {
		if (!level.isClientSide()) {
			if (entity instanceof ItemEntity && altar.active) {
				ItemStack stack = ((ItemEntity) entity).getItem();
				if (stack.getItem() == ModItems.BIO_LUMINESCENCE.get()) { //TODO make this only Erebus materials items
					altar.setUses(altar.getUses() + stack.getCount());
					ItemStack stackLeft = stack.copy();
					entity.remove(RemovalReason.DISCARDED);
					if (altar.getUses() <= 165) {
						ExperienceOrb orb = new ExperienceOrb(level, pos.getX() + 0.5D, pos.getY() - 0.125D, pos.getZ() + 0.5D, stack.getCount() * 5);
						level.addFreshEntity(orb);
					}
					if (altar.getExcess() > 0) {
						stackLeft.setCount(altar.getExcess());
						Block.popResource(level, pos.above(), stackLeft);
					}
					if (altar.getUses() > 165) {
						altar.active = false;
						altar.setSpawnTicks(0);
					}
				}
			}
		}
		}
	}

	@Override
	public ItemInteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (level.isClientSide()) {
			return ItemInteractionResult.SUCCESS;
		} else if (blockEntity instanceof ExperienceAltarBlockEntity altar) {
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