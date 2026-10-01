package erebus.item.wand;

import erebus.Config;
import erebus.Erebus;
import erebus.entity.AnimatedBambooCrate;
import erebus.entity.AnimatedBlock;
import erebus.entity.AnimatedChest;
import erebus.registries.ModSounds;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class WandOfAnimationItem extends Item {
    public WandOfAnimationItem() {
        super(new Item.Properties()
                .stacksTo(1)
                .durability(64)
                .setNoCombineRepair()
                .setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("wand_of_animation")))
        );
    }

    @Override
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, Consumer<Component> lines, @NonNull TooltipFlag flag) {
        lines.accept(Component.translatable("tooltip.erebus.wand_of_animation"));
    }


    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        var level = context.getLevel();
        var player = context.getPlayer();
        if (player == null) return InteractionResult.PASS;
        var hand = context.getHand();
        var pos = context.getClickedPos();
        var stack = player.getItemInHand(hand);
        if (!level.mayInteract(player, pos) || !player.mayUseItemAt(pos, context.getClickedFace(), stack)) return InteractionResult.FAIL;
        var state = level.getBlockState(pos);
        if (Config.animationBlacklist.contains(BuiltInRegistries.BLOCK.getKey(state.getBlock()))) return InteractionResult.PASS;
        if (state.is(Blocks.CHEST)) return AnimatedChest.animate(context);
        if (state.is(ModBlocks.BAMBOO_CRATE)) return AnimatedBambooCrate.animate(context);
        if (!canAnimate(state, level, pos)) return InteractionResult.PASS;
        if (level.isClientSide()) return InteractionResult.SUCCESS;

        var entity = new AnimatedBlock(ModEntities.ANIMATED_BLOCK.get(), level);
        entity.setPos(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
        entity.setBlockType(state);
        entity.setOwnerId(player.getUUID());
        entity.setPersistenceRequired();
        if (!level.addFreshEntity(entity)) return InteractionResult.FAIL;
        if (!level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3)) {
            entity.discard();
            return InteractionResult.FAIL;
        }
        stack.hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        level.playSound(null, pos, ModSounds.ALTAR_OFFERING.get(), SoundSource.BLOCKS, 0.2F, 1.0F);
        return InteractionResult.SUCCESS_SERVER;
    }

    private boolean canAnimate(BlockState state, Level level, BlockPos pos) {
        return !state.isAir() && !state.hasBlockEntity() && state.getDestroySpeed(level, pos) >= 0F && state.isCollisionShapeFullBlock(level, pos);
    }

    @Override
    public @NonNull InteractionResult onItemUseFirst(@NonNull ItemStack stack, UseOnContext context) {
        return context.getLevel().getBlockState(context.getClickedPos()).is(Blocks.CHEST) ? useOn(context) : InteractionResult.PASS;
    }
}
