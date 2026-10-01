package erebus.mixin;

import erebus.client.EntityLighting;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.BlockLightEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockLightEngine.class)
public abstract class EntityLightMixin {
    @Inject(method = "getEmission", at = @At("RETURN"), cancellable = true)
    private void erebus$entityEmission(long blockNode, BlockState state, CallbackInfoReturnable<Integer> result) {
        int emission = EntityLighting.emission(this, blockNode);
        if (emission > result.getReturnValue()) result.setReturnValue(emission);
    }
}
