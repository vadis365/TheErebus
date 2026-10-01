package erebus.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.item.armour.GliderItem;
import erebus.network.data.GliderData;
import erebus.registries.data.ModDataComponents;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AvatarRenderer.class)
public abstract class GliderPoseMixin {
    @Inject(method = "setupRotations(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;FF)V", at = @At("TAIL"))
    private void erebus$gliderPose(AvatarRenderState state, PoseStack poseStack, float bodyRot, float entityScale, CallbackInfo callback) {
        if (!state.isFallFlying && state.chestEquipment.getItem() instanceof GliderItem && state.chestEquipment.getOrDefault(ModDataComponents.GLIDER, GliderData.EMPTY).active()) {
            poseStack.mulPose(Axis.XP.rotationDegrees(-60));
            state.walkAnimationSpeed = 0.1F;
        }
    }
}
