package erebus.item;

import erebus.Erebus;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public final class FlowerSeedItem extends Item {
    private final Supplier<Block> flower;

    public FlowerSeedItem(String name, Supplier<Block> flower) {
        super(new Properties().setId(ResourceKey.create(Registries.ITEM, Erebus.prefix(name))));
        this.flower = flower;
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        var level = context.getLevel();
        var player = context.getPlayer();
        var pos = context.getClickedPos().above();
        var stack = context.getItemInHand();
        var state = flower.get().defaultBlockState();
        if (player == null || context.getClickedFace() != Direction.UP || !player.mayUseItemAt(pos, Direction.UP, stack)
                || !level.isInWorldBounds(pos) || !level.getWorldBorder().isWithinBounds(pos)
                || !level.isEmptyBlock(pos) || !state.canSurvive(level, pos)) return InteractionResult.FAIL;
        if (!level.isClientSide()) {
            if (!level.setBlock(pos, state, 3)) return InteractionResult.FAIL;
            if (player instanceof ServerPlayer serverPlayer) CriteriaTriggers.PLACED_BLOCK.trigger(serverPlayer, pos, stack);
            level.gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(player, state));
            var sound = state.getSoundType(level, pos, player);
            level.playSound(null, pos, sound.getPlaceSound(), SoundSource.BLOCKS, 1, 0.8F);
            stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }
}
