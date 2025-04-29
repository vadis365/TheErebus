package erebus.block.altars;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import com.mojang.serialization.MapCodec;

import erebus.registries.ModBlocks;
import erebus.registries.ModItems;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AltarBase extends AltarAbstract {
	public static final MapCodec<AltarBase> CODEC = simpleCodec(AltarBase::new);

	private final Map<Item, Block> ALTAR_TYPES = new HashMap<Item, Block>();
	
	public AltarBase(Properties properties) {
		super(properties);
	}

    @Override
    protected MapCodec<AltarBase> codec() {
        return CODEC;
    }

	@Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return null;
	}

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.MODEL;
	}

	 @Nonnull
		@Override
	    public ItemInteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
		initMap();
		if (level.isClientSide())
			return ItemInteractionResult.SUCCESS;

		if (!stack.isEmpty()) {
			Block altar = ALTAR_TYPES.get(stack.getItem());
			if (altar != null) {
				if (!level.isClientSide()) {
					level.setBlock(pos, altar.defaultBlockState().setValue(FACING, state.getValue(FACING)), 3);
					level.playSound(null, pos, ModSounds.ALTAR_OFFERING.get(), SoundSource.BLOCKS, 0.2F, 1.0F);
					if (!player.isCreative() && stack.getCount() <= 0)
						player.setItemInHand(hand, ItemStack.EMPTY);
				} else
					for (int i = 0; i < 10; i++) {
						RandomSource rand = level.random;
						level.addParticle(ParticleTypes.FLAME, pos.getX() + rand.nextDouble(), pos.getY() + 1.1, pos.getZ() + rand.nextDouble(), 0D, 0D, 0D);
						level.addParticle(ParticleTypes.CLOUD, pos.getX() + rand.nextDouble(), pos.getY() + 1.1, pos.getZ() + rand.nextDouble(), 0D, 0D, 0D);
					}
				return ItemInteractionResult.SUCCESS;
			}
		}

		return ItemInteractionResult.FAIL;
	}

	private void initMap() {
		if (ALTAR_TYPES.isEmpty()) {
			ALTAR_TYPES.put(ModItems.BIO_VELOCITY.get(), ModBlocks.ALTAR_EXPERIENCE.get());
			ALTAR_TYPES.put(ModItems.ELASTIC_FIBER.get(), ModBlocks.ALTAR_REPAIR.get());
			ALTAR_TYPES.put(ModItems.RED_GEM.get(), ModBlocks.ALTAR_LIGHTNING.get());
			ALTAR_TYPES.put(ModItems.BIO_LUMINESCENCE.get(), ModBlocks.ALTAR_HEALING.get());
		}
	}
}