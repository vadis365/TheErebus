package erebus.client.render.entity;

import erebus.client.model.entity.ModelGlowWorm;
import erebus.entity.EntityGlowWorm;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGlowWorm extends RenderLiving<EntityGlowWorm> {

	private static final ResourceLocation TEXTURE_ON = new ResourceLocation("erebus:textures/entity/glow_worm_glow.png");
	private static final ResourceLocation TEXTURE_OFF = new ResourceLocation("erebus:textures/entity/glow_worm.png");

	public RenderGlowWorm(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelGlowWorm(), 0.0F);
	}

	@Override
	protected void preRenderCallback(EntityGlowWorm glowworm, float partialTickTime) {
		GlStateManager.scale(0.75F, 0.75F, 0.75F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGlowWorm glowworm) {
		return glowworm.isGlowing() ? TEXTURE_ON : TEXTURE_OFF;
	}
}