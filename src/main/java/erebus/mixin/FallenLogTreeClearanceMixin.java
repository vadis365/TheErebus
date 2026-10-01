package erebus.mixin;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TrunkPlacer.class)
public abstract class FallenLogTreeClearanceMixin {
    @Inject(method = "isFree", at = @At("HEAD"), cancellable = true)
    private void erebus$protectFallenLogs(WorldGenLevel level, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
        if (level.getBlockState(pos).is(ModBlocks.LOG_ROTTEN.get())) callback.setReturnValue(false);
    }
}
