package erebus.client.render.entity;

import erebus.client.model.entity.ModelBotFlyLarva;
import erebus.entity.EntityBotFlyLarva;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBotFlyLarva extends RenderLiving<EntityBotFlyLarva> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/bot_fly_larva.png");

	public RenderBotFlyLarva(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelBotFlyLarva(), 0.5F);
	}

	@Override
	protected float getDeathMaxRotation(EntityBotFlyLarva larva) {
		return 180F;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBotFlyLarva larva) {
		return TEXTURE;
	}

	@Override
	protected void preRenderCallback(EntityBotFlyLarva larva, float partialTickTime) {
		GlStateManager.scale(0.6F, 0.6F, 0.6F);
		if (larva.getRidingEntity() != null) {
			GlStateManager.translate(0F, 0F, -0.2F);
			GlStateManager.rotate(180F, 0F, 1F, 0F);
		}
	}

}