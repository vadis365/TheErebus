package erebus.item.wand;

import erebus.Erebus;
import erebus.entity.projectile.AmberStar;
import erebus.registries.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class WandOfPreservationItem extends Item {

    public WandOfPreservationItem() {
        super(new Item.Properties()
                .stacksTo(1)
                .durability(256)
                .setNoCombineRepair()
                .setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("wand_of_preservation"))));
    }

    @Override
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, Consumer<Component> lines, @NonNull TooltipFlag flag) {
        lines.accept(Component.translatable("tooltip.erebus.wand_of_preservation"));
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player == null) return InteractionResult.PASS;
        return use(context.getLevel(), player, context.getHand());
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        var stack = player.getItemInHand(hand);
        var ammunition = findAmmunition(player);
        if (!player.isCreative() && ammunition.isEmpty()) return InteractionResult.FAIL;
        if (level.isClientSide()) return InteractionResult.SUCCESS;

        var star = new AmberStar(level, player.getX(), player.getEyeY() - 0.1D, player.getZ());
        star.setOwner(player);
        star.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
        if (!level.addFreshEntity(star)) return InteractionResult.FAIL;
        if (!player.isCreative()) {
            ammunition.shrink(1);
            player.getInventory().setChanged();
        }
        stack.hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        level.playSound(null, player.blockPosition(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        return InteractionResult.SUCCESS_SERVER;
    }

    private ItemStack findAmmunition(Player player) {
        for (int slot = 0; slot < player.getInventory().getContainerSize(); slot++) {
            var stack = player.getInventory().getItem(slot);
            if (stack.is(ModItems.AMBER_STAR)) return stack;
        }
        return ItemStack.EMPTY;
    }
}
