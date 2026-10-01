package erebus.item;

import erebus.Erebus;
import erebus.entity.projectile.WebSling;
import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class WebSlingerItem extends Item {
    private final boolean wither;

    public WebSlingerItem(boolean wither) {
        super(new Item.Properties().durability(128).component(DataComponents.WEAPON, new Weapon(2))
                .setId(ResourceKey.create(Registries.ITEM, Erebus.prefix(wither ? "web_slinger_wither" : "web_slinger"))));
        this.wither = wither;
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
        var projectile = new WebSling(ModEntities.WEB_SLING.get(), level);
        projectile.setOwner(player);
        projectile.setWither(wither);
        projectile.setPos(player.getX(), player.getY() + player.getBbHeight() / 2, player.getZ());
        projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 1.5F, 0);
        if (!level.addFreshEntity(projectile)) return InteractionResult.FAIL;
        if (!player.isCreative()) {
            ammo.shrink(1);
            player.getInventory().setChanged();
            stack.hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        }
        level.playSound(null, player.blockPosition(), ModSounds.WEBSLING_THROW.get(), SoundSource.PLAYERS, 1, 1);
        return InteractionResult.SUCCESS_SERVER;
    }

    private ItemStack findAmmo(Player player) {
        for (int slot = 0; slot < player.getInventory().getContainerSize(); slot++) {
            var stack = player.getInventory().getItem(slot);
            if (stack.is(Items.COBWEB)) return stack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, Consumer<Component> builder, @NonNull TooltipFlag flag) {
        builder.accept(Component.translatable("tooltip.erebus.web_slinger").withStyle(ChatFormatting.YELLOW));
    }
}
