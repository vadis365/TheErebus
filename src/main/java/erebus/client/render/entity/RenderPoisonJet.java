package erebus.client.render.entity;

import erebus.entity.EntityPoisonJet;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPoisonJet extends Render<EntityPoisonJet> {

	public RenderPoisonJet(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public void doRender(EntityPoisonJet entity, double x, double y, double z, float yaw, float tick) {
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPoisonJet entity) {
		return null;
	}
}

