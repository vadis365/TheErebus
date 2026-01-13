package erebus.block.altars;

import erebus.block.entity.AltarAbstractBlockEntity;
import erebus.registries.ModSounds;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;

public abstract class AltarAbstract extends HorizontalDirectionalBlock implements EntityBlock {
	public static final VoxelShape ALTAR_AABB = Block.box(0D, 0D, 0D, 16D, 16D, 16D);

	public AltarAbstract(Properties properties) {
		super(properties);
	}

	@Nonnull
	@Override
	public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		return ALTAR_AABB;
	}

	@Nonnull
	@Override
	public VoxelShape getInteractionShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos) {
		return Shapes.block();
	}

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.INVISIBLE;
	}

	@Override
	 public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction direction = context.getHorizontalDirection().getOpposite();
		return this.defaultBlockState().setValue(FACING, direction);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	protected <A extends AltarAbstractBlockEntity> InteractionResult activateAltar(Level level, ItemStack stack, Player player, A altar, BlockPos pos) {
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
		return InteractionResult.FAIL;
	}

}
