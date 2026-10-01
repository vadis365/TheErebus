package erebus.mixin;

import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LiquidBlock.class)
public abstract class WaterStridersCollisionMixin {
    @Inject(method = "getCollisionShape", at = @At("HEAD"), cancellable = true)
    private void erebus$waterSurface(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> result) {
        if (context.alwaysCollideWithFluid() || !(context instanceof EntityCollisionContext entityContext)
                || !(entityContext.getEntity() instanceof Player player)
                || !player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.WATER_STRIDERS)) return;
        var fluid = state.getFluidState();
        if (!fluid.is(FluidTags.WATER) || level.getFluidState(pos.above()).is(FluidTags.WATER)) return;
        var surface = Shapes.box(0, 0, 0, 1, fluid.getHeight(level, pos), 1);
        // The surface supports descending feet; submerged players can still swim upward.
        if (context.isAbove(surface, pos, true)) result.setReturnValue(surface);
    }
}
