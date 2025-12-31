package erebus.block;

import erebus.block.entity.ErebusChestBlockEntity;
import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ErebusChestBlock extends ChestBlock {

    private final String unlocalizedName;

    public ErebusChestBlock(Properties properties, String unlocalizedName) {
        super(ModBlockEntities.EREBUS_CHEST::get, SoundEvents.CHEST_OPEN, SoundEvents.CHEST_CLOSE, properties);
        this.unlocalizedName = unlocalizedName;
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    @Override
    public @NotNull BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new ErebusChestBlockEntity(pos, state);
    }
}