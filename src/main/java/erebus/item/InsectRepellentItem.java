package erebus.item;

import erebus.registries.ModSounds;
import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class InsectRepellentItem extends Item {
    public InsectRepellentItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        BlockPos clickedPos = context.getClickedPos();
        Level level = context.getLevel();
        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();

        if(context.getClickedFace() != Direction.UP) return InteractionResult.FAIL;

        if(player != null)
            if(!player.isCreative())
                stack.shrink(1);

        level.playSound(player, clickedPos, ModSounds.SPRAY_CAN_SOUND.get(), SoundSource.BLOCKS, 1.0F, 1.0F);

        if(level.getBlockState(clickedPos.above()).isAir()) {
            level.setBlock(clickedPos.above(), OtherBlocks.INSECT_REPELLENT.get().defaultBlockState(), Block.UPDATE_ALL);

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }
}
