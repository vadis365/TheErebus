package erebus.block.entity;

import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class GlowingJarBlockEntity extends BlockEntity {

    private final Random random = new Random();
    private float particleSpawnTick = random.nextInt(100);
    public float particleSize;

    public GlowingJarBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        super(ModBlockEntities.GLOWING_JAR.get(), pos, state);
    }

	public static <T extends BlockEntity> void clientTick(Level ignoredWorld, BlockPos ignoredWorldPosition, BlockState ignoredBlockState, T t) {
		if (t instanceof GlowingJarBlockEntity tile) {
			tile.particleSpawnTick++;
	            if (tile.particleSpawnTick <= 50)
	            	tile.particleSize = tile.particleSpawnTick / 25;
	            else
	            	tile.particleSize = 2 - (tile.particleSpawnTick - 50) / 25;

	            if (tile.particleSpawnTick > 100)
	            	tile.particleSpawnTick = 0;
	        }
	}
}
