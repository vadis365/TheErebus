package erebus.item.wand;

import erebus.entity.projectile.AmberStar;
import erebus.registries.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WandOfPreservationItem extends Item {

    public WandOfPreservationItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.erebus.wand_of_preservation"));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if(player.isCreative() || consumeBullet(player)) {
            stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
            level.playSound(player, player.blockPosition(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 0.5F, 0.4F / level.random.nextFloat() * 0.4F + 0.8F);

            AmberStar star = new AmberStar(level, player);
            star.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(star);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    private boolean consumeBullet(Player player) {
        for(int c = 0; c < player.getInventory().getContainerSize(); c++ ) {
            ItemStack stack = player.getInventory().getItem(c);
            if(!stack.isEmpty() && stack.is(ModItems.AMBER_STAR)) {
                stack.shrink(1);
                player.getInventory().setChanged();
                return true;
            }
        }

        return false;
    }
}
