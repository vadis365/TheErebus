package erebus.client.render.entity;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderSporeBall extends Render {

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float tick) {
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
}