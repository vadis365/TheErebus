package erebus.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.registries.ModItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
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
    private void render(PoseStack pose, MultiBufferSource buffer, LivingEntity entity, EquipmentSlot slot, int packedLight, HumanoidModel model, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callback) {
        ItemStack helm = entity.getItemBySlot(EquipmentSlot.HEAD);

        if(helm.is(ModItems.RHINO_EXOSKELETON_HELMET) || helm.is(ModItems.MUSHROOM_HELMET)) {
            callback.cancel();
        }
    }
}
