package erebus.client.render.entity;

import erebus.client.model.entity.ModelBlackWidow;
import erebus.entity.EntityBlackWidow;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBlackWidow extends RenderLiving<EntityBlackWidow> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/black_widow.png");

	public RenderBlackWidow(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelBlackWidow(), 0.3F);
	}

	@Override
	protected void preRenderCallback(EntityBlackWidow widow, float f) {
		shadowSize = widow.getWidowSize() * 0.3F;
		GlStateManager.scale(shadowSize, shadowSize, shadowSize);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBlackWidow widow) {
		return TEXTURE;
	}
}