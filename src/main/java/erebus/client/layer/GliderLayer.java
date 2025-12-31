package erebus.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.Erebus;
import erebus.client.render.item.model.ArmorGliderModel;
import erebus.registries.client.ModItemRendering;
import erebus.registries.item.ModItems;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class GliderLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    private static final ResourceLocation WINGS = Erebus.prefix("textures/models/armor/glider_layer_1.png");
    private final ArmorGliderModel<T> armorModel;

    public GliderLayer(RenderLayerParent<T, M> renderer, EntityModelSet modelSet) {
        super(renderer);
        armorModel = new ArmorGliderModel<>(modelSet.bakeLayer(ModItemRendering.ARMOR_GLIDER));
    }

    @Override
    public void render(@NotNull PoseStack pose, @NotNull MultiBufferSource buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack stack = entity.getItemBySlot(EquipmentSlot.CHEST);
        if(shouldRender(stack)) {
            ResourceLocation texture;

            if(entity instanceof AbstractClientPlayer player) {
                PlayerSkin skin = player.getSkin();

                if(skin.elytraTexture() != null) {
                    texture = skin.elytraTexture();
                } else if(skin.capeTexture() != null && player.isModelPartShown(PlayerModelPart.CAPE)) {
                    texture = skin.capeTexture();
                } else {
                    texture = getWingTexture();
                }

                pose.pushPose();
                pose.translate(0, 0, 0.125F);
                getParentModel().copyPropertiesTo(armorModel);
                armorModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                VertexConsumer vertex = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.armorCutoutNoCull(texture), stack.hasFoil());
                armorModel.renderToBuffer(pose, vertex, packedLight, OverlayTexture.NO_OVERLAY);
                pose.popPose();
            }
        }
    }

    public boolean shouldRender(ItemStack stack) {
        return stack.is(ModItems.GLIDER_CHESTPLATE);
    }

    public ResourceLocation getWingTexture() {
        return WINGS;
    }
}
