package erebus.item;

import erebus.Erebus;
import erebus.entity.Wasp;
import erebus.entity.projectile.WaspDagger;
import erebus.registries.data.ModToolMaterials;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public final class WaspDaggerItem extends Item {
    public WaspDaggerItem() {
        super(new Properties().sword(ModToolMaterials.WASP_DAGGER, 3, -2.4F)
                .component(DataComponents.MAX_DAMAGE, null).component(DataComponents.DAMAGE, null)
                .component(DataComponents.WEAPON, new Weapon(0)).stacksTo(64)
                .setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("wasp_dagger"))));
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        return context.getPlayer() == null ? InteractionResult.PASS : use(context.getLevel(), context.getPlayer(), context.getHand());
    }

    @Override
    public @NonNull InteractionResult use(Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        var stack = player.getItemInHand(hand);
        var dagger = new WaspDagger(ModEntities.WASP_DAGGER.get(), level);
        dagger.setOwner(player);
        dagger.setItem(stack);
        double yaw = Math.toRadians(player.getYRot());
        dagger.setPos(player.getX() - Math.sin(yaw) * 0.5, player.getEyeY(), player.getZ() + Math.cos(yaw) * 0.5);
        dagger.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 1, 0);
        if (!level.addFreshEntity(dagger)) return InteractionResult.FAIL;
        stack.consume(1, player);
        level.playSound(null, player.blockPosition(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS,
                0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public void hurtEnemy(@NonNull ItemStack stack, LivingEntity target, @NonNull LivingEntity attacker) {
        if (!target.level().isClientSide() && !(target instanceof Wasp)) target.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0));
    }

    @Override
    public void postHurtEnemy(@NonNull ItemStack stack, @NonNull LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide() && !(attacker instanceof Player player && player.isCreative())) stack.shrink(1);
    }

    @Override
    public boolean mineBlock(@NonNull ItemStack stack, Level level, @NonNull BlockState state, @NonNull BlockPos pos, @NonNull LivingEntity owner) {
        if (!level.isClientSide() && state.getDestroySpeed(level, pos) != 0
                && !(owner instanceof Player player && player.isCreative())) stack.shrink(1);
        return true;
    }

    @Override
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, Consumer<Component> lines, @NonNull TooltipFlag flag) {
        lines.accept(Component.translatable("tooltip.erebus.wasp_dagger_1"));
        lines.accept(Component.translatable("tooltip.erebus.wasp_dagger_2"));
    }
}
