package erebus.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CamoPowderItem extends Item {

    public CamoPowderItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        if(!player.isCreative()) itemStack.shrink(1);
        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 280, 1, false, false), player);

        if(level.isClientSide()) {
            for (int iteration = 0; iteration < 20; iteration++) {
                double velocityX = level.random.nextGaussian() * 0.02;
                double velocityY = level.random.nextGaussian() * 0.02;
                double velocityZ = level.random.nextGaussian() * 0.02;
                level.addParticle(ParticleTypes.POOF,
                        player.getX(1.0) - velocityX * 10.0,
                        player.getRandomY() - velocityY * 10.0,
                        player.getRandomZ(1.0) - velocityZ * 10.0,
                        velocityX,
                        velocityY,
                        velocityZ);
            }
        }

        return InteractionResultHolder.success(itemStack);
    }
}
