package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.LavaWebSpiderModel;
import erebus.client.render.entity.renderer.state.LavaWebSpiderRenderState;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class LavaWebSpiderLayer extends RenderLayer<LavaWebSpiderRenderState, LavaWebSpiderModel> {
    private static final Identifier LIGHTING_TEXTURE = Erebus.prefix("textures/entity/lava_web_spider_flow.png");
    private final LavaWebSpiderModel lava_web_spiderModel;

    public LavaWebSpiderLayer(RenderLayerParent<LavaWebSpiderRenderState, LavaWebSpiderModel> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.lava_web_spiderModel = new LavaWebSpiderModel(modelSet.bakeLayer(ModEntityRendering.LAVA_WEB_SPIDER_FLOW));
    }

	@Override
	public void submit(@NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, int lightCoords, LavaWebSpiderRenderState state, float xRot, float yRot) {
		int colour =  654311423;
		/*float f = (float) entity.tickCount + partialTicks;
		lava_web_spiderModel.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
		lava_web_spiderModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		lava_web_spiderModel.renderBody(matrix, buffer.getBuffer(getLavaOverlay(LIGHTING_TEXTURE, 0, f * 0.004F)), packedLight, OverlayTexture.NO_OVERLAY, colour);*/
	}
    
	/*public static RenderType getLavaOverlay(Identifier locationIn, float uIn, float vIn) {
		RenderType.CompositeState renderTypeState = RenderType.CompositeState.builder()
				.setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeEnergySwirlShader))
				.setTextureState(new RenderStateShard.TextureStateShard(locationIn, false, false))
				.setTexturingState(new RenderStateShard.OffsetTexturingStateShard(uIn, vIn))
				.setLightmapState(new LightmapStateShard(true)).setCullState(new CullStateShard(false))
				.setLightmapState(new LightmapStateShard(false)).setOverlayState(new OverlayStateShard(true))
				.createCompositeState(false);

		return RenderType.create("lava_overlay", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true, renderTypeState);
	}*/
}
