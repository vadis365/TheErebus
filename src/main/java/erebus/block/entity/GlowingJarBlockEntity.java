package erebus.block.entity;

import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class GlowingJarBlockEntity extends BlockEntity {

    private final Random random = new Random();
    private float particleSpawnTick = random.nextInt(100);
    public float particleSize;
    public float xRot;
    public float zRot;

    public GlowingJarBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        super(ModBlockEntities.GLOWING_JAR.get(), pos, state);
    }

    public void tick() {
        if (level != null && !level.isClientSide()) {
            particleSpawnTick++;
            if (particleSpawnTick <= 5) particleSize = particleSpawnTick / 25;
            else particleSize = 2 - (particleSpawnTick - 50) / 25;

            if (particleSpawnTick > 100) particleSpawnTick = 0;
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
