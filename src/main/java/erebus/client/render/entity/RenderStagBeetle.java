package erebus.client.render.entity;

import erebus.client.model.entity.ModelStagBeetle;
import erebus.entity.EntityStagBeetle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderStagBeetle extends RenderLiving<EntityStagBeetle> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/stag_beetle.png");
	private static final ResourceLocation TEXTURE_TAME = new ResourceLocation("erebus:textures/entity/stag_beetle_kit.png");

	public RenderStagBeetle(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelStagBeetle(), 1F);
	}

	@Override
	protected void preRenderCallback(EntityStagBeetle beetle, float partialTickTime) {
		GlStateManager.scale(1.2F, 1.2F, 1.2F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityStagBeetle beetle) {
		return beetle.getTameState() < 2 ? TEXTURE : TEXTURE_TAME;
	}
}