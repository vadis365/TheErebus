package erebus.item;

import erebus.Erebus;
import erebus.network.data.DeathCompassData;
import erebus.registries.data.ModDataComponents;
import erebus.registries.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

public class DeathCompass extends Item {
    public DeathCompass() {
        super(new Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("death_compass"))));
    }

    public static ItemStack create(GlobalPos target, String time) {
        var stack = new ItemStack(ModItems.DEATH_COMPASS.get());
        stack.set(DataComponents.LODESTONE_TRACKER, new LodestoneTracker(Optional.of(target), false));
        BlockPos pos = target.pos();
        stack.set(ModDataComponents.DEATH_COMPASS, new DeathCompassData(pos.getX(), pos.getY(), pos.getZ(), time));
        return stack;
    }

    @Override
    public void inventoryTick(ItemStack stack, @NonNull ServerLevel level, @NonNull Entity owner, @Nullable EquipmentSlot slot) {
        if (stack.has(DataComponents.LODESTONE_TRACKER) || !(owner instanceof Player player)) return;
        var data = stack.get(ModDataComponents.DEATH_COMPASS);
        if (data != null) player.getLastDeathLocation().filter(last -> last.pos().equals(new BlockPos(data.x(), data.y(), data.z())))
                .ifPresent(last -> stack.set(DataComponents.LODESTONE_TRACKER, new LodestoneTracker(Optional.of(last), false)));
    }

    @Override
    public @NonNull Component getHighlightTip(@NonNull ItemStack stack, @NonNull Component displayName) {
        var data = stack.get(ModDataComponents.DEATH_COMPASS);
        return data == null ? displayName : Component.translatable("tooltip.erebus.death_compass.position", data.x(), data.y(), data.z())
                .withStyle(ChatFormatting.YELLOW);
    }

    @Override
    public void appendHoverText(ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> lines, @NonNull TooltipFlag flag) {
        var data = stack.get(ModDataComponents.DEATH_COMPASS);
        if (data == null) return;
        lines.accept(getHighlightTip(stack, stack.getHoverName()));
        var tracker = stack.get(DataComponents.LODESTONE_TRACKER);
        if (tracker != null) tracker.target().ifPresent(target -> lines.accept(
                Component.translatable("tooltip.erebus.death_compass.dimension", target.dimension().identifier().toString()).withStyle(ChatFormatting.YELLOW)));
        if (!data.deathTime().isEmpty()) lines.accept(Component.translatable("tooltip.erebus.time_of_death", data.deathTime()).withStyle(ChatFormatting.YELLOW));
    }
}
