package erebus.client.render.entity;

import erebus.client.model.entity.ModelBogMaw;
import erebus.entity.EntityBogMaw;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBogMaw extends RenderLiving<EntityBogMaw> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/bog_maw.png");

	public RenderBogMaw(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelBogMaw(), 0F);
	}

	@Override
	protected void preRenderCallback(EntityBogMaw bog_maw, float partialTickTime) {
		if (!bog_maw.onGround) {
			int yaw = (int) bog_maw.getRotation();
			yaw += 22;
			yaw %= 360;
			if (yaw < 0)
				yaw += 360;
			int facing = yaw / 45;
			float x = 0;
			float y = 0;
			switch (facing) {
				case 0:
					x = -1;
					y = 0;
					break;
				case 1:
					x = -1;
					y = 1;
					break;
				case 2:
					x = 0;
					y = 1;
					break;
				case 3:
					x = 1;
					y = 1;
					break;
				case 4:
					x = 1;
					y = 0;
					break;
				case 5:
					x = 1;
					y = -1;
					break;
				case 6:
					x = 0;
					y = -1;
					break;
				case 7:
					x = -1;
					y = -1;
					break;
			}
			GlStateManager.rotate(45F, x, 0F, y);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBogMaw bog_maw) {
		return TEXTURE;
	}
}