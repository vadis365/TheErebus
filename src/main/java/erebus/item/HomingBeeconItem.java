package erebus.item;

import erebus.Erebus;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.Optional;
import java.util.function.Consumer;

public final class HomingBeeconItem extends Item {
    private final boolean advanced;

    public HomingBeeconItem(boolean advanced) {
        super(new Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM,
                Erebus.prefix(advanced ? "homing_beecon_advanced" : "homing_beecon"))));
        this.advanced = advanced;
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        var player = context.getPlayer();
        if (player == null || !player.isShiftKeyDown() || context.getHand() != InteractionHand.MAIN_HAND)
            return InteractionResult.PASS;
        if (!context.getLevel().isClientSide()) context.getItemInHand().set(DataComponents.LODESTONE_TRACKER,
                new LodestoneTracker(Optional.of(GlobalPos.of(context.getLevel().dimension(), context.getClickedPos())), false));
        return InteractionResult.SUCCESS;
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        if (!advanced || player.isShiftKeyDown() || hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;
        var tracker = player.getItemInHand(hand).get(DataComponents.LODESTONE_TRACKER);
        if (tracker == null || tracker.target().isEmpty()) return InteractionResult.PASS;
        var target = tracker.target().get();
        if (!target.dimension().equals(level.dimension()) || player.isPassenger()) return InteractionResult.FAIL;
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        var pos = target.pos();
        if (!level.getWorldBorder().isWithinBounds(pos) || !level.isInWorldBounds(pos.above(2))
                || !level.isEmptyBlock(pos.above()) || !level.isEmptyBlock(pos.above(2))) return InteractionResult.FAIL;
        player.teleportTo(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
        player.fallDistance = 0;
        level.playSound(null, pos, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1, 1);
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean isFoil(@NonNull ItemStack stack) {
        return advanced || super.isFoil(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> lines, @NonNull TooltipFlag flag) {
        var tracker = stack.get(DataComponents.LODESTONE_TRACKER);
        if (tracker != null) tracker.target().ifPresent(target -> {
            lines.accept(Component.translatable("tooltip.erebus.dimension", target.dimension().identifier().toString()));
            lines.accept(Component.translatable("tooltip.erebus.death_compass.position", target.pos().getX(), target.pos().getY(), target.pos().getZ()));
        });
        lines.accept(Component.translatable(advanced ? "tooltip.erebus.homingbeecon_advanced_1" : "tooltip.erebus.homingbeecon"));
        if (advanced) lines.accept(Component.translatable("tooltip.erebus.homingbeecon_advanced_2"));
    }
}
