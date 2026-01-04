package erebus.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class CamoPowderItem extends Item {

    public CamoPowderItem() {
        super(new Item.Properties());
    }

    @Override
    public @NonNull InteractionResult onItemUseFirst(@NonNull ItemStack stack, UseOnContext context) {
        Player player = context.getPlayer();
        InteractionHand usedHand = context.getHand();
        Level level = context.getLevel();

        ItemStack itemStack = player.getItemInHand(usedHand);
        if(!player.isCreative()) itemStack.shrink(1);
        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 280, 1, false, false), player);

        if(level.isClientSide()) {
            for (int iteration = 0; iteration < 20; iteration++) {
                double velocityX = level.getRandom().nextGaussian() * 0.02;
                double velocityY = level.getRandom().nextGaussian() * 0.02;
                double velocityZ = level.getRandom().nextGaussian() * 0.02;
                level.addParticle(ParticleTypes.POOF,
                        player.getX(1.0) - velocityX * 10.0,
                        player.getRandomY() - velocityY * 10.0,
                        player.getRandomZ(1.0) - velocityZ * 10.0,
                        velocityX,
                        velocityY,
                        velocityZ);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
