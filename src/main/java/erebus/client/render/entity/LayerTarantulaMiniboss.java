package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelTarantula;
import erebus.entity.EntityTarantulaMiniboss;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerTarantulaMiniboss implements LayerRenderer<EntityTarantulaMiniboss> {
	private final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/power.png");
    private final RenderTarantulaMiniboss RENDERER;
    private final ModelTarantula MODEL = new ModelTarantula(1.0F);

    public LayerTarantulaMiniboss(RenderTarantulaMiniboss rendererIn) {
        this.RENDERER = rendererIn;
    }

	@Override
    public void doRenderLayer(EntityTarantulaMiniboss tarantula, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (tarantula.getFancyRenderOverlay()) {
            boolean flag = tarantula.isInvisible();
            GlStateManager.depthMask(!flag);
            this.RENDERER.bindTexture(TEXTURE);
            float scrollTimer = (float)tarantula.ticksExisted + partialTicks;
            GlStateManager.matrixMode(GL11.GL_TEXTURE);
            GlStateManager.loadIdentity();
            float yScroll = scrollTimer * 0.02F;
            GlStateManager.translate(0F, yScroll, 0.0F);
            GlStateManager.matrixMode(GL11.GL_MODELVIEW);
            GlStateManager.enableBlend();
            float colour = 0.5F;
            GlStateManager.color(colour, colour, colour, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
            this.MODEL.setModelAttributes(this.RENDERER.getMainModel());
            this.MODEL.render(tarantula, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GlStateManager.matrixMode(GL11.GL_TEXTURE);
			GlStateManager.loadIdentity();
			GlStateManager.matrixMode(GL11.GL_MODELVIEW);
			GlStateManager.enableLighting();
			GlStateManager.disableBlend();
            GlStateManager.depthMask(flag);
        }
    }

	@Override
    public boolean shouldCombineTextures() {
        return false;
    }
}