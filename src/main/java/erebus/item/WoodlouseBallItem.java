package erebus.item;

import erebus.Erebus;
import erebus.entity.projectile.WoodlouseBall;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public final class WoodlouseBallItem extends Item {
    public WoodlouseBallItem() {
        super(new Properties().stacksTo(16).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("woodlouse_ball"))));
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        return context.getPlayer() == null ? InteractionResult.PASS : use(context.getLevel(), context.getPlayer(), context.getHand());
    }

    @Override
    public @NonNull InteractionResult use(Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        var stack = player.getItemInHand(hand);
        var ball = new WoodlouseBall(ModEntities.WOODLOUSE_BALL.get(), level);
        ball.setOwner(player);
        ball.setItem(stack);
        ball.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
        ball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 1.5F, 1);
        if (!level.addFreshEntity(ball)) return InteractionResult.FAIL;
        stack.consume(1, player);
        level.playSound(null, player.blockPosition(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResult.SUCCESS_SERVER;
    }
}
