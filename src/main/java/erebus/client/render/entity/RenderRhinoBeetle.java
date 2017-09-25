package erebus.client.render.entity;

import erebus.client.model.entity.ModelRhinoBeetle;
import erebus.entity.EntityRhinoBeetle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class RenderRhinoBeetle extends RenderLiving<EntityRhinoBeetle> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/rhino_beetle.png");
	private static final ResourceLocation TEXTURE_UNTAME = new ResourceLocation("erebus:textures/entity/rhino_beetle_kit.png");

	public RenderRhinoBeetle(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelRhinoBeetle(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityRhinoBeetle beetle, float partialTickTime) {
		GlStateManager.scale(1.5F, 1.5F, 1.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityRhinoBeetle beetle) {
		return beetle.getTameState() < 2 ? TEXTURE : TEXTURE_UNTAME;
	}
}