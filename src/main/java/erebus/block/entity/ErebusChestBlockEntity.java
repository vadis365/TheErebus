package erebus.block.entity;

import erebus.block.ErebusChestBlock;
import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.jetbrains.annotations.NotNull;

public class ErebusChestBlockEntity extends ChestBlockEntity {

    public ErebusChestBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EREBUS_CHEST.get(), pos, state);
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }

    @Override
    public @NotNull Component getDisplayName() {
        if(getBlockState().getValue(ErebusChestBlock.TYPE).equals(ChestType.SINGLE)) {
            String unlocalizedName = "chest";
            if (this.getBlockState().getBlock() instanceof ErebusChestBlock chestBlock) {
                unlocalizedName = chestBlock.getUnlocalizedName();
            }
            return Component.translatable("erebus.container.%s".formatted(unlocalizedName));
        } else {
            String unlocalizedName = "chest";
            if (this.getBlockState().getBlock() instanceof ErebusChestBlock chestBlock) {
                unlocalizedName = chestBlock.getUnlocalizedName();
            }
            return Component.translatable("erebus.container.large.%s".formatted(unlocalizedName));
        }
    }

    @Override
    protected @NotNull Component getDefaultName() {
        String unlocalizedName = "chest";
        if (this.getBlockState().getBlock() instanceof ErebusChestBlock chestBlock) {
            unlocalizedName = chestBlock.getUnlocalizedName();
        }
        return Component.translatable("erebus.container.%s".formatted(unlocalizedName));
    }
}
