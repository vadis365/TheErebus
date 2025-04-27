package erebus.block;

import erebus.registries.client.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class SwampVentBlock extends Block {
    public SwampVentBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        super.animateTick(state, level, pos, random);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        level.addParticle(ModParticles.SWAMP_VENT.get(), x + 0.5F, y + 1F, z + 0.5F, 0.1D, 0.0D, 0.1D);
        if (random.nextInt(5) == 0) {
            level.playLocalSound(
                    x,
                    y,
                    z,
                    SoundEvents.BUBBLE_COLUMN_BUBBLE_POP,
                    SoundSource.BLOCKS,
                    0.2F + random.nextFloat() * 0.2F,
                    0.9F + random.nextFloat() * 0.15F,
                    false
            );
        }
    }
}
