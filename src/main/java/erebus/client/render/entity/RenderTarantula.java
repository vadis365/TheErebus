package erebus.client.render.entity;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelTarantula;
import erebus.entity.EntityTarantula;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderTarantula extends RenderLiving {

	private final ResourceLocation resource1 = new ResourceLocation("erebus:textures/entity/tarantula.png");
	private final ResourceLocation resource2 = new ResourceLocation("erebus:textures/entity/tarantulaTurqoise.png");

	public RenderTarantula() {
		super(new ModelTarantula(), 0.5F);
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