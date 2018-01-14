package erebus.client.render.entity;

import erebus.entity.EntitySporeBall;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSporeBall extends Render<EntitySporeBall> {

	public RenderSporeBall(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntitySporeBall entity, double x, double y, double z, float entityYaw, float partialTicks) {
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySporeBall entity) {
		return null;
	}
}