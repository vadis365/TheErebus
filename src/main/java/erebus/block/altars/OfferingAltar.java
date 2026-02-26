package erebus.block.altars;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.OfferingAltarBlockEntity;
import net.minecraft.core.BlockPos;
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
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class OfferingAltar extends Block implements EntityBlock {
	
	public static final MapCodec<OfferingAltar> CODEC = simpleCodec(OfferingAltar::new);
	
	public OfferingAltar(Properties properties) {
		super(properties);
		//setHardness(2.0F);
		//setHarvestLevel("pickaxe", 0);
	}
	
    @Override
    protected @NonNull MapCodec<OfferingAltar> codec() {
        return CODEC;
    }

	@Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new OfferingAltarBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@Nonnull Level pLevel, @Nonnull BlockState pState, @Nonnull BlockEntityType<T> pBlockEntityType) {
		return OfferingAltarBlockEntity::tick;
	}

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.INVISIBLE;
	}

    @Nonnull
	@Override
    public InteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
    	BlockEntity blockEntity = level.getBlockEntity(pos);
    	if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		}
		else if (blockEntity instanceof OfferingAltarBlockEntity) {
			if (stack.isEmpty()) {
				if (player.isCrouching()) {
					((OfferingAltarBlockEntity) blockEntity).popStack();
					return InteractionResult.SUCCESS;
				}
			} else if (!player.isCrouching()) {
				((OfferingAltarBlockEntity) blockEntity).addStack(stack);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.FAIL;
    }

}
