package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.UmberFurnaceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class UmberFurnaceBlock extends AbstractFurnaceBlock {
    public static final MapCodec<UmberFurnaceBlock> CODEC = simpleCodec(UmberFurnaceBlock::new);

    public UmberFurnaceBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends AbstractFurnaceBlock> codec() {
        return CODEC;
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player) {
        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof UmberFurnaceBlockEntity) {

        }
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }
}
