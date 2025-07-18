package erebus.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.registries.ModItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
public class HumanoidArmorLayerMixin {
    @Inject(
            method = "renderArmorPiece(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;ILnet/minecraft/client/model/HumanoidModel;FFFFFF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void render(PoseStack poseStack, MultiBufferSource bufferSource,
                        LivingEntity livingEntity, EquipmentSlot slot,
                        int packedLight, HumanoidModel p_model,
                        float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch,
                        CallbackInfo callback) {
        if (livingEntity.getItemBySlot(slot).is(ModItems.MUSHROOM_HELMET) || livingEntity.getItemBySlot(slot).is(ModItems.RHINO_EXOSKELETON_HELMET)) {
            callback.cancel();
        }
    }
}