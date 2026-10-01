package erebus.item;

import erebus.Erebus;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.Consumer;

public class InstantEffectItem extends Item {
    private final Holder<MobEffect> effect;
    private final int duration;
    private final int amplifier;
    private final int blockingAmplifier;
    private final boolean smoke;

    public InstantEffectItem(String name, Holder<MobEffect> effect, int duration, int amplifier, int blockingAmplifier, boolean smoke) {
        super(new Properties().setId(ResourceKey.create(Registries.ITEM, Erebus.prefix(name))));
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.blockingAmplifier = blockingAmplifier;
        this.smoke = smoke;
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, Player player, @NonNull InteractionHand hand) {
        var current = player.getEffect(effect);
        if (current != null && current.getAmplifier() >= blockingAmplifier) return InteractionResult.PASS;
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        if (!player.addEffect(new MobEffectInstance(effect, duration, amplifier, true, false))) return InteractionResult.PASS;
        player.getItemInHand(hand).consume(1, player);
        player.awardStat(Stats.ITEM_USED.get(this));
        if (smoke && level instanceof ServerLevel server)
            server.sendParticles(ParticleTypes.POOF, player.getX(), player.getY() + player.getBbHeight() / 2,
                    player.getZ(), 20, 0.4, player.getBbHeight() / 2, 0.4, 0.02);
        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        return context.getPlayer() == null ? InteractionResult.PASS : use(context.getLevel(), context.getPlayer(), context.getHand());
    }

    @Override
    public void appendHoverText(@NonNull ItemStack stack, TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> lines, @NonNull TooltipFlag flag) {
        PotionContents.addPotionTooltip(List.of(new MobEffectInstance(effect, duration, amplifier)), lines, 1, context.tickRate());
    }
}
