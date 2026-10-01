package erebus.item;

import erebus.Erebus;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import org.jspecify.annotations.NonNull;

public class DarkFruitSeedsItem extends Item {
    public DarkFruitSeedsItem() {
        super(new Properties().setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("dark_fruit_seeds"))));
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        var player = context.getPlayer();
        var level = context.getLevel();
        var support = context.getClickedPos();
        var target = support.below();
        var stack = context.getItemInHand();
        if (player == null || stack.isEmpty() || context.getClickedFace() != Direction.DOWN
                || !level.getBlockState(support).isSolid()
                || level.isOutsideBuildHeight(target) || !level.getWorldBorder().isWithinBounds(target)
                || !level.isEmptyBlock(target)
                || !player.mayUseItemAt(support, Direction.DOWN, stack)
                || !player.mayUseItemAt(target, Direction.DOWN, stack)
                || !level.mayInteract(player, support) || !level.mayInteract(player, target)) {
            return InteractionResult.FAIL;
        }
        if (!level.isClientSide()) {
            if (!level.setBlockAndUpdate(target, ModBlocks.DARK_FRUIT_VINE.get().defaultBlockState())) return InteractionResult.FAIL;
            if (!player.isCreative()) stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }
}
