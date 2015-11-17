package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelSnapper;
import erebus.entity.EntitySnapper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderSnapper extends RenderLiving {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/snapper.png");

	public RenderSnapper() {
		super(new ModelSnapper(), 0F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
		EntitySnapper snapper = (EntitySnapper) entity;
		if (!snapper.onGround) {
			int yaw = (int) snapper.getDataWatcher().getWatchableObjectFloat(20);
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
			GL11.glRotatef(45F, x, 0, y);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE;
	}
}