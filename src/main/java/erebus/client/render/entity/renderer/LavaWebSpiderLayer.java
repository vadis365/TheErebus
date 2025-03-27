package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexFormat;

import erebus.Erebus;
import erebus.client.render.entity.model.LavaWebSpiderModel;
import erebus.entity.LavaWebSpider;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderStateShard.CullStateShard;
import net.minecraft.client.renderer.RenderStateShard.LightmapStateShard;
import net.minecraft.client.renderer.RenderStateShard.OverlayStateShard;
import net.minecraft.client.renderer.RenderStateShard.TransparencyStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LavaWebSpiderLayer extends RenderLayer<LavaWebSpider, LavaWebSpiderModel<LavaWebSpider>> {
    private static final ResourceLocation LIGHTING_TEXTURE = Erebus.prefix("textures/entity/lava_web_spider_flow.png");
    private final LavaWebSpiderModel<LavaWebSpider> lava_web_spiderModel;

    public LavaWebSpiderLayer(RenderLayerParent<LavaWebSpider, LavaWebSpiderModel<LavaWebSpider>> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.lava_web_spiderModel = new LavaWebSpiderModel<>(modelSet.bakeLayer(ModEntityRendering.LAVA_WEB_SPIDER_FLOW));
    }

    @Override
   	public void render(PoseStack matrix, MultiBufferSource buffer, int packedLight, LavaWebSpider entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		int colour =  654311423;
		float f = (float) entity.tickCount + partialTicks;
    	lava_web_spiderModel.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
		lava_web_spiderModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		lava_web_spiderModel.renderBody(matrix, buffer.getBuffer(getLavaOverlay(LIGHTING_TEXTURE, 0, -f * 0.004F)), packedLight, OverlayTexture.NO_OVERLAY, colour);
	}
    
	public static RenderType getLavaOverlay(ResourceLocation locationIn, float uIn, float vIn) {
		RenderType.CompositeState renderTypeState = RenderType.CompositeState.builder().setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeEnergySwirlShader)).setTextureState(new RenderStateShard.TextureStateShard(locationIn, false, false)).setTexturingState(new RenderStateShard.OffsetTexturingStateShard(uIn, vIn)).setTransparencyState(new TransparencyStateShard("translucent_transparency", () -> {
		      RenderSystem.enableBlend();
		      RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		   }, () -> {
		      RenderSystem.disableBlend();
		      RenderSystem.defaultBlendFunc();
		   })).setLightmapState(new LightmapStateShard(true)).setCullState(new CullStateShard(false)).setLightmapState(new LightmapStateShard(false)).setOverlayState(new OverlayStateShard(true)).createCompositeState(false);
		
		return RenderType.create("lava_overlay", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true, renderTypeState);
	}
}