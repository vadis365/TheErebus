package erebus.block.altars;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import erebus.block.entity.ExperienceAltarBlockEntity;
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
/*
	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		ExperienceAltarBlockEntity te = Utils.getTileEntity(world, pos, ExperienceAltarBlockEntity.class);
		double offsetY = 0.9D;
		if (!world.isRemote) {
			if (entity instanceof EntityItem && entity.getEntityBoundingBox().minY >= pos.getY() + offsetY && te.active) {
				ItemStack stack = ((EntityItem) entity).getItem();
				if (stack.getItem() == ModItems.MATERIALS) {
					te.setUses(te.getUses() + stack.getCount());
					ItemStack stackLeft = stack.copy();
					entity.setDead();
					if (te.getUses() <= 165)
						world.spawnEntity(new EntityXPOrb(world, pos.getX() + 0.5D, pos.getY() + 1.8D, pos.getZ() + 0.5D, stack.getCount() * 5));
					if (te.getExcess() > 0) {
						stackLeft.setCount(te.getExcess());
						Utils.dropStackNoRandom(world, pos.up(), stackLeft);
					}
					if (te.getUses() > 165) {
						te.active = false;
						te.setSpawnTicks(0);
					}
				}
			}
		}
	}
*/
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