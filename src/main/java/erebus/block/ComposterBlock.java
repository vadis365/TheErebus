package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.ComposterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComposterBlock extends Block implements EntityBlock {

	public static final MapCodec<ComposterBlock> CODEC = simpleCodec(ComposterBlock::new);

	public ComposterBlock(Properties properties) {
		super(properties);
		//	setHardness(2.0F);
		//	setSoundType(SoundType.WOOD);
		//	setCreativeTab(ModTabs.BLOCKS);
	}

	@Override
	protected @NotNull MapCodec<ComposterBlock> codec() {
		return CODEC;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, @Nonnull BlockState pState, @Nonnull BlockEntityType<T> pBlockEntityType) {
		return pLevel.isClientSide() ? null : ComposterBlockEntity::serverTick;
	}

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public @NotNull InteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		} else if (blockEntity instanceof ComposterBlockEntity composter) {
			player.openMenu(composter, pos);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new ComposterBlockEntity(pos, state);
	}

	@Override
	public void onRemove(@NotNull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
		ComposterBlockEntity composter = (ComposterBlockEntity) level.getBlockEntity(pos);
		if (composter != null)
			Containers.dropContents(level, pos, composter);
		level.levelEvent(2001, pos, Block.getId(state));
		super.onRemove(state, level, pos, newState, isMoving);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		ComposterBlockEntity composter = (ComposterBlockEntity) level.getBlockEntity(pos);
		if (composter != null && composter.compostingProgressTicks > 0) {
			double particleX = pos.getX() + 0.5F;
			double particleY = pos.getY() + 1.1F + random.nextFloat() * 6.0F / 16.0F;
			double particleZ = pos.getZ() + 0.5F;
			level.addParticle(ParticleTypes.HAPPY_VILLAGER, particleX, particleY, particleZ, 0D, 0D, 0D);
		}
	}
}
