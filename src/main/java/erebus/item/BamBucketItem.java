package erebus.item;

import erebus.registries.item.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jspecify.annotations.NonNull;

public class BamBucketItem extends BucketItem {

    public BamBucketItem(Fluid fluid, Properties properties) {
        super(fluid, properties);
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, Player player, @NonNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        BlockHitResult hitResult = getPlayerPOVHitResult(level, player, getContent() == Fluids.EMPTY ? net.minecraft.world.level.ClipContext.Fluid.SOURCE_ONLY : net.minecraft.world.level.ClipContext.Fluid.NONE);
        if (hitResult.getType() == HitResult.Type.MISS) {
            return InteractionResult.PASS;
        } else if (hitResult.getType() != HitResult.Type.BLOCK) {
            return InteractionResult.PASS;
        } else {
            BlockPos pos = hitResult.getBlockPos();
            Direction direction = hitResult.getDirection();
            BlockPos directionOffsetPos = pos.relative(direction);
            if (level.mayInteract(player, pos) && player.mayUseItemAt(directionOffsetPos, direction, stack)) {
                if (getContent() == Fluids.EMPTY) {
                    BlockState state = level.getBlockState(pos);
                    if (state.getBlock() instanceof BucketPickup bucketPickupBlock) {
                        ItemStack taken = bucketPickupBlock.pickupBlock(player, level, pos, state);
                        if (!taken.isEmpty()) {
                            player.awardStat(Stats.ITEM_USED.get(this));
                            bucketPickupBlock.getPickupSound(state).ifPresent((soundEvent) -> player.playSound(soundEvent, 1.0F, 1.0F));
                            level.gameEvent(player, GameEvent.FLUID_PICKUP, pos);
                            ItemStack result = ItemUtils.createFilledResult(stack, player, taken);
                            if (!level.isClientSide()) {
                                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer)player, taken);
                            }

                            return InteractionResult.SUCCESS.heldItemTransformedTo(result);
                        }
                    }

                    return InteractionResult.FAIL;
                } else {
                    BlockState clicked = level.getBlockState(pos);
                    BlockPos placePos = this.canBlockContainFluid(player, level, pos, clicked) && this.content == Fluids.WATER ? pos : directionOffsetPos;
                    if (this.emptyContents(player, level, placePos, hitResult, stack)) {
                        this.checkExtraContent(player, level, stack, placePos);
                        if (player instanceof ServerPlayer) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, placePos, stack);
                        }

                        player.awardStat(Stats.ITEM_USED.get(this));
                        ItemStack emptyResult = ItemUtils.createFilledResult(stack, player, getEmptySuccessItem(stack, player));
                        return InteractionResult.SUCCESS.heldItemTransformedTo(emptyResult);
                    } else {
                        return InteractionResult.FAIL;
                    }
                }
            } else {
                return InteractionResult.FAIL;
            }
        }
    }

    public static @NonNull ItemStack getEmptySuccessItem(@NonNull ItemStack stack, Player player) {
        return !player.hasInfiniteMaterials() ? new ItemStack(ModItems.BAMBUCKET.get()) : stack;
    }
}
