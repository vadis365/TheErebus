package erebus.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.Erebus;
import erebus.client.render.item.model.MushroomHelmModel;
import erebus.client.render.item.model.RhinoHeadModel;
import erebus.registries.ModItems;
import erebus.registries.client.ModItemRendering;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CustomHelmLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {

    private static final ResourceLocation RHINO_HELM = Erebus.prefix("textures/models/armor/rhino_helm.png");
    private static final ResourceLocation MUSHROOM_HELM = Erebus.prefix("textures/models/armor/mushroom_helm_layer_1.png");

    private final RhinoHeadModel<T> rhinoHeadModel;
    private final MushroomHelmModel<T> mushroomHelmModel;

    public CustomHelmLayer(RenderLayerParent<T, M> renderer, EntityModelSet modelSet, boolean isSlim) {
        super(renderer);
        rhinoHeadModel = new RhinoHeadModel<>(modelSet.bakeLayer(ModelLayers.PLAYER), isSlim, modelSet.bakeLayer(ModItemRendering.RHINO_HELM));
        mushroomHelmModel = new MushroomHelmModel<>(modelSet.bakeLayer(ModelLayers.PLAYER), isSlim, modelSet.bakeLayer(ModItemRendering.MUSHROOM_HELM));
    }

    @Override
    public void render(@NotNull PoseStack pose, @NotNull MultiBufferSource buffer, int packedLight, @NotNull T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack stack = entity.getItemBySlot(EquipmentSlot.HEAD);

        if(shouldRender(stack)) {
            pose.pushPose();
            pose.scale(1.2F, 1.0F, 1.2F);
            if(stack.is(ModItems.RHINO_EXOSKELETON_HELMET)) {
                renderRhinoHelm(stack, pose, buffer, packedLight, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            } else {
                renderMushroomHelm(stack, pose, buffer, packedLight, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            }
            pose.popPose();
        }
    }

    private void renderRhinoHelm(ItemStack helm, @NotNull PoseStack pose, @NotNull MultiBufferSource buffer, int packedLight, @NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        getParentModel().copyPropertiesTo(rhinoHeadModel);
        rhinoHeadModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        VertexConsumer vertex = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.armorCutoutNoCull(RHINO_HELM), helm.hasFoil());
        rhinoHeadModel.renderToBuffer(pose, vertex, packedLight, OverlayTexture.NO_OVERLAY);
    }

    private void renderMushroomHelm(ItemStack helm, @NotNull PoseStack pose, @NotNull MultiBufferSource buffer, int packedLight, @NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        getParentModel().copyPropertiesTo(mushroomHelmModel);
        mushroomHelmModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        VertexConsumer vertex = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.armorCutoutNoCull(MUSHROOM_HELM), helm.hasFoil());
        mushroomHelmModel.renderToBuffer(pose, vertex, packedLight, OverlayTexture.NO_OVERLAY);
    }

    public boolean shouldRender(ItemStack stack) {
        return stack.is(ModItems.RHINO_EXOSKELETON_HELMET) || stack.is(ModItems.MUSHROOM_HELMET);
    }
}
