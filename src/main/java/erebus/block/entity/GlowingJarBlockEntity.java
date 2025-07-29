package erebus.block.entity;

import java.util.Random;

import org.jetbrains.annotations.NotNull;

import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GlowingJarBlockEntity extends BlockEntity {

    private final Random random = new Random();
    private float particleSpawnTick = random.nextInt(100);
    public float particleSize;
    public float xRot;
    public float zRot;

    public GlowingJarBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        super(ModBlockEntities.GLOWING_JAR.get(), pos, state);
    }

	public static <T extends BlockEntity> void clientTick(Level world, BlockPos worldPosition, BlockState blockState, T t) {
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

    public void setRotation(float xRot, float zRot) {
        this.xRot = xRot;
        this.zRot = zRot;

        if (level != null && !level.isClientSide()) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }
}
