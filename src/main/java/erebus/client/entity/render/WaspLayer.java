package erebus.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;

import erebus.Erebus;
import erebus.client.entity.model.WaspModel;
import erebus.entity.WaspEntity;
import erebus.registries.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WaspLayer extends RenderLayer<WaspEntity, WaspModel<WaspEntity>> {
    private static final ResourceLocation LIGHTING_TEXTURE = Erebus.prefix("textures/entity/wasp.png");
    private final WaspModel<WaspEntity> waspModel;

    public WaspLayer(RenderLayerParent<WaspEntity, WaspModel<WaspEntity>> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.waspModel = new WaspModel<>(modelSet.bakeLayer(ModEntityRendering.WASP));
    }

    @Override
   	public void render(PoseStack matrix, MultiBufferSource buffer, int packedLight, WaspEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		int colour =  654311423;
    	waspModel.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
		waspModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		waspModel.renderWings(matrix, buffer.getBuffer(RenderType.entityTranslucentCull(LIGHTING_TEXTURE)), packedLight, OverlayTexture.NO_OVERLAY, colour);
	}
}