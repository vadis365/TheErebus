package erebus.item;

import erebus.Erebus;
import erebus.network.data.QuakeHammerData;
import erebus.network.data.QuakeHammerDataHolder;
import erebus.registries.ModSounds;
import erebus.registries.data.ModDataComponents;
import erebus.registries.data.ModToolMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class QuakeHammerItem extends Item {

    public QuakeHammerItem() {
        super(new Item.Properties().sword(ModToolMaterials.QUAKE_HAMMER, 10, -1).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("quake_hammer"))));
    }

    private static int charge(ItemStack stack) {
        return Mth.clamp(stack.getOrDefault(ModDataComponents.QUAKE_HAMMER, QuakeHammerDataHolder.DEFAULT).charge(), 0, 25);
    }

    @Override
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, Consumer<Component> lines, @NonNull TooltipFlag flag) {
        lines.accept(Component.translatable("tooltip.erebus.quake_hammer_1").withStyle(ChatFormatting.YELLOW));
        lines.accept(Component.translatable("tooltip.erebus.quake_hammer_2").withStyle(ChatFormatting.YELLOW));
    }

    @Override
    public void postHurtEnemy(ItemStack stack, @NonNull LivingEntity target, @NonNull LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public int getUseDuration(@NonNull ItemStack stack, @NonNull LivingEntity entity) {
        return 1000;
    }

    // TODO - going to change this now to be something different - old stuff will be new stuff soon(tm)
    @Override
    public @NonNull InteractionResult use(Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        if (!level.isClientSide()) {
            var stack = player.getItemInHand(hand);
            stack.set(ModDataComponents.QUAKE_HAMMER, new QuakeHammerData(Math.min(25, charge(stack) + 1)));
        }
        return InteractionResult.PASS;
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        if (player == null || context.getClickedFace() != Direction.UP || !player.isShiftKeyDown()
                || !player.mayUseItemAt(pos, context.getClickedFace(), stack) || level.getBlockState(pos).isAir()) {
            return InteractionResult.FAIL;
        }
        if (level instanceof ServerLevel) {
            int charge = charge(stack);
            if (charge > 0) {
                level.playSound(null, pos, ModSounds.BLAM_SOUND.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                areaOfEffect(level, player, charge);
            }
            stack.set(ModDataComponents.QUAKE_HAMMER, QuakeHammerDataHolder.DEFAULT);
        }
        return InteractionResult.SUCCESS;
    }

    public void areaOfEffect(Level level, Player player, int charge) {
        if (!(level instanceof ServerLevel server)) return;
        charge = Mth.clamp(charge, 0, 25);
        if (charge == 0) return;
        var targets = level.getEntitiesOfClass(LivingEntity.class,
                player.getBoundingBox().inflate(charge * 0.25D, 1D, charge * 0.25D), target -> target != player);
        for (var target : targets) {
            float knockback = (float) (charge * 0.025D);
            target.hurtServer(server, player.damageSources().mobAttack(player), charge * 0.25F);
            target.push(-Mth.sin(player.getYRot() * -3.141593F + level.getRandom().nextInt(3) + 0.141593F / 180.0F) * knockback,
                    0.01D,
                    Mth.cos(player.getYRot() * -3.141593F + level.getRandom().nextInt(3) + 0.141593F / 180.0F) * knockback);
        }
    }
}
