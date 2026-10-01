package erebus.item;

import erebus.Erebus;
import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public final class HornOfSummoningItem extends Item {
    public HornOfSummoningItem() {
        super(new Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("horn_of_summoning"))));
    }

    @Override
    public @NonNull InteractionResult use(Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        if (!level.isClientSide()) {
            level.playSound(null, player.blockPosition(), ModSounds.HORN_BLOW.get(), SoundSource.PLAYERS, 1, 1);
            for (int i = -3; i < level.getRandom().nextInt(6); i++) {
                var bee = ModEntities.WORKER_BEE.get().create(level, EntitySpawnReason.MOB_SUMMONED);
                if (bee != null) {
                    bee.setPos(player.getX(), player.getY() + 3, player.getZ());
                    level.addFreshEntity(bee);
                }
            }
            player.getItemInHand(hand).shrink(1);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, Consumer<Component> lines, @NonNull TooltipFlag flag) {
        lines.accept(Component.translatable("tooltip.erebus.hornsummon"));
    }
}
