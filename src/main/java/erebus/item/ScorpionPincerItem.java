package erebus.item;

import erebus.Erebus;
import erebus.entity.Scorpion;
import erebus.registries.data.ModToolMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class ScorpionPincerItem extends Item {
    public ScorpionPincerItem() {
        super(new Item.Properties().sword(ModToolMaterials.SCORPION_PINCER, 3, -2.4F)
                .setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("enhanced_scorpion_pincer"))));
    }

    @Override
    public void hurtEnemy(@NonNull ItemStack stack, @NonNull LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide() && !(target instanceof Scorpion)) {
            double yaw = Math.toRadians(attacker.yBodyRot);
            target.push(-Math.sin(yaw) * 0.5, 0.25, Math.cos(yaw) * 0.5);
            target.hurtMarked = true;
        }
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        return context.getPlayer() == null ? InteractionResult.PASS : use(context.getLevel(), context.getPlayer(), context.getHand());
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, Player player, @NonNull InteractionHand hand) {
        var stack = player.getItemInHand(hand);
        var ammo = findAmmo(player);
        if (!player.isCreative() && ammo.isEmpty()) return InteractionResult.FAIL;
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        var fireball = new SmallFireball(level, player, player.getLookAngle());
        double yaw = Math.toRadians(player.yBodyRot);
        fireball.setPos(player.getX() - Math.sin(yaw), player.getY() + 1.2, player.getZ() + Math.cos(yaw));
        if (!level.addFreshEntity(fireball)) return InteractionResult.FAIL;
        if (!player.isCreative()) {
            ammo.shrink(1);
            player.getInventory().setChanged();
            stack.hurtAndBreak(10, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        }
        level.playSound(null, player.blockPosition(), SoundEvents.GHAST_SHOOT, SoundSource.PLAYERS,
                0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        return InteractionResult.SUCCESS_SERVER;
    }

    private ItemStack findAmmo(Player player) {
        for (int slot = 0; slot < player.getInventory().getContainerSize(); slot++) {
            var stack = player.getInventory().getItem(slot);
            if (stack.is(Items.FIRE_CHARGE)) return stack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display,
                                Consumer<Component> builder, @NonNull TooltipFlag flag) {
        builder.accept(Component.translatable("tooltip.erebus.scorpion_pincer").withStyle(ChatFormatting.YELLOW));
    }
}
