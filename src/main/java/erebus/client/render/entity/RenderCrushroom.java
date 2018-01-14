package erebus.client.render.entity;

import erebus.client.model.entity.ModelCrushroom;
import erebus.entity.EntityCrushroom;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrushroom extends RenderLiving<EntityCrushroom> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/crushroom.png");

	public RenderCrushroom(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelCrushroom(), 1.5F);
	}

	@Override
	protected void preRenderCallback(EntityCrushroom crushroom, float partialTickTime) {
		GlStateManager.scale(2F, 2F, 2F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCrushroom crushroom) {
		return TEXTURE;
	}
}
