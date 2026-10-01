package erebus.entity;

import erebus.block.bamboo.BambooCrateBlock;
import erebus.block.entity.BambooCrateBlockEntity;
import erebus.block.types.EnumCrateType;
import erebus.inventory.server.BambooCrateMenu;
import erebus.registries.ModSounds;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.client.ModMenuTypes;
import erebus.registries.entity.ModEntities;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class AnimatedBambooCrate extends AnimatedContainer {
    public AnimatedBambooCrate(EntityType<? extends AnimatedBambooCrate> type, Level level) {
        super(type, level);
    }

    public static InteractionResult animate(UseOnContext context) {
        var level = context.getLevel();
        var pos = context.getClickedPos();
        var player = context.getPlayer();
        var state = level.getBlockState(pos);
        if (player == null || !state.is(ModBlocks.BAMBOO_CRATE) || state.getValue(BambooCrateBlock.CRATE_TYPE) != EnumCrateType.DEFAULT
                || !(level.getBlockEntity(pos) instanceof BambooCrateBlockEntity source)) return InteractionResult.PASS;
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        var crate = ModEntities.ANIMATED_BAMBOO_CRATE.get().create(level, EntitySpawnReason.TRIGGERED);
        if (crate == null) return InteractionResult.FAIL;
        crate.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        crate.setOwnerId(player.getUUID());
        if (!level.addFreshEntity(crate)) return InteractionResult.FAIL;
        if (level.getBlockEntity(pos) != source || !level.getBlockState(pos).equals(state)) {
            crate.discard();
            return InteractionResult.FAIL;
        }
        crate.copyContents(source);
        source.clearContent();
        if (!level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3)) {
            for (int slot = 0; slot < crate.getContainerSize(); slot++) source.setItem(slot, crate.getItem(slot));
            crate.clearContent();
            crate.discard();
            return InteractionResult.FAIL;
        }
        var hand = context.getHand();
        context.getItemInHand().hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        level.playSound(null, pos, ModSounds.ALTAR_OFFERING.get(), SoundSource.BLOCKS, 0.2F, 1);
        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public BlockState getBlockType() {
        return ModBlocks.BAMBOO_CRATE.get().defaultBlockState();
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new BambooCrateMenu(ModMenuTypes.ANIMATED_BAMBOO_CRATE.get(), id, inventory, this);
    }

}
