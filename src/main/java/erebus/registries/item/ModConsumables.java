package erebus.registries.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ClearAllStatusEffectsConsumeEffect;

import java.util.List;

public class ModConsumables {

    public static final Consumable GREEN_TEA_GRASSHOPPER = smoothie()
            .onConsume(
                new ApplyStatusEffectsConsumeEffect(
                        new MobEffectInstance(
                                MobEffects.JUMP_BOOST,
                                1000,
                                2
                        )
                )
            ).build();

    public static final Consumable MONEY_HONEY = smoothie()
            .onConsume(
                new ApplyStatusEffectsConsumeEffect(
                        new MobEffectInstance(
                                MobEffects.REGENERATION,
                                200,
                                2
                        )
                )
            ).build();

    public static final Consumable NOTHING_IN_THE_MIDDLE = smoothie()
            .onConsume(
                new ApplyStatusEffectsConsumeEffect(
                        new MobEffectInstance(
                                MobEffects.INVISIBILITY,
                                500,
                                1
                        )
                )
            ).build();

    public static final Consumable GREEN_GIANT = smoothie()
            .onConsume(
                new ClearAllStatusEffectsConsumeEffect()
            ).build();

    public static final Consumable SEEDY_GOODNESS = smoothie()
            .onConsume(
                new ApplyStatusEffectsConsumeEffect(
                        new MobEffectInstance(
                                MobEffects.HASTE,
                                500,
                                1
                        )
                )
            ).build();

    public static final Consumable GIVIN_ME_THE_BLUES = smoothie()
            .onConsume(
                new ApplyStatusEffectsConsumeEffect(
                        new MobEffectInstance(
                                MobEffects.SLOWNESS,
                                500,
                                2
                        )
                )
            ).build();

    public static final Consumable HOT_HOT_BABY = smoothie()
            .onConsume(
                new ApplyStatusEffectsConsumeEffect(
                        new MobEffectInstance(
                                MobEffects.STRENGTH,
                                1000,
                                1
                        )
                )
            ).build();

    public static final Consumable DONT_MEDDLE_WITH_THE_NETTLE = smoothie()
            .onConsume(
                new ApplyStatusEffectsConsumeEffect(
                        new MobEffectInstance(
                                MobEffects.RESISTANCE,
                                1000,
                                1
                        )
                )
            ).build();

    public static final Consumable LIQUID_GOLD = smoothie()
            .onConsume(
                new ApplyStatusEffectsConsumeEffect(
                        new MobEffectInstance(
                                MobEffects.SATURATION,
                                1000,
                                1
                        )
                )
            ).build();

    public static final Consumable BRYUFS_BREW = smoothie()
            .onConsume(
                new ApplyStatusEffectsConsumeEffect(
                        List.of(
                                new MobEffectInstance(
                                        MobEffects.SPEED,
                                        1000,
                                        3
                                ),
                                new MobEffectInstance(
                                        MobEffects.STRENGTH,
                                        1000,
                                        3
                                ),
                                new MobEffectInstance(
                                        MobEffects.JUMP_BOOST,
                                        1000,
                                        3
                                )
                        )
                )
            ).build();

    public static final Consumable MELONADE_SPARKLY = smoothie()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(
                                    MobEffects.REGENERATION,
                                    200,
                                    1
                            )
                    )
            ).build();

    public static final Consumable BEETLE_LARVA_RAW = rawFood(300, 2);
    public static final Consumable LARVAE_ON_STICK = rawFood(100,1);

    public static final Consumable TITAN_CHOP_COOKED = food()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(
                                    MobEffects.STRENGTH,
                                    600,
                                    1
                            )
                    )
            ).build();

    public static final Consumable PRICKLY_PEAR = food()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(
                                    MobEffects.INSTANT_DAMAGE
                            )
                    )
            ).build();

    public static Consumable.Builder food() {
        return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT);
    }

    private static Consumable rawFood(int duration, int amplifier) {
        return food().onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.HUNGER, duration, amplifier)))).build();
    }

    public static Consumable.Builder smoothie() {
        return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_DRINK).hasConsumeParticles(false);
    }
}
