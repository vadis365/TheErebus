package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelTarantula;
import erebus.entity.EntityTarantula;

@SideOnly(Side.CLIENT)
public class RenderTarantula extends RenderLiving {

	private final ResourceLocation resource1 = new ResourceLocation("erebus:textures/entity/Tarantula.png");
	private final ResourceLocation resource2 = new ResourceLocation("erebus:textures/entity/Exaturqoise.png");

	public RenderTarantula() {
		super(new ModelTarantula(), 0.5F);
	}

	public void renderTarantula(EntityTarantula entityTarantula, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityTarantula, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderTarantula((EntityTarantula) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderTarantula((EntityTarantula) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityTarantula tarantula = (EntityTarantula) entity;
		if (tarantula.skin <= 4)
			return resource2;
		else
			return resource1;
	}
}
