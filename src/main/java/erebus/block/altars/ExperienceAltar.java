package erebus.block.altars;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.ExperienceAltarBlockEntity;
import erebus.registries.data.tags.ModItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
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

public class ExperienceAltar extends AltarAbstract {

    public static final MapCodec<ExperienceAltar> CODEC = simpleCodec(ExperienceAltar::new);

    public ExperienceAltar(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull MapCodec<ExperienceAltar> codec() {
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
    protected void onPlace(@NonNull BlockState state, Level level, @NonNull BlockPos pos, @NonNull BlockState oldState, boolean movedByPiston) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof ExperienceAltarBlockEntity altar)
            altar.setActive(false);
    }

    @Override
    public void stepOn(Level level, @NonNull BlockPos pos, @NonNull BlockState state, @NonNull Entity entity) {
        if (level.isClientSide() || !(entity instanceof ItemEntity item) || !item.isAlive()
                || item.getY() < pos.getY() + 0.9
                || !(level.getBlockEntity(pos) instanceof ExperienceAltarBlockEntity altar) || !altar.active) return;
        ItemStack stack = item.getItem();
        if (!stack.is(ModItemTags.EXPERIENCE_ALTAR_FUEL)) return;
        int accepted = Math.min(stack.getCount(), ExperienceAltarBlockEntity.CAPACITY - altar.getUses());
        if (accepted <= 0) {
            altar.setActive(false);
            altar.setSpawnTicks(0);
            return;
        }
        var orb = new ExperienceOrb(level, pos.getX() + 0.5, pos.getY() + 1.8, pos.getZ() + 0.5, accepted * 5);
        if (!level.addFreshEntity(orb)) return;
        altar.setUses(altar.getUses() + accepted);
        if (accepted == stack.getCount()) item.discard();
        else item.setItem(stack.copyWithCount(stack.getCount() - accepted));
        if (altar.getUses() == ExperienceAltarBlockEntity.CAPACITY) {
            altar.setActive(false);
            altar.setSpawnTicks(0);
        }
    }

    @Override
    public @NonNull InteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else if (blockEntity instanceof ExperienceAltarBlockEntity altar) {
            return activateAltar(level, stack, player, altar, pos, hand);
        }
        return InteractionResult.FAIL;
    }
}
