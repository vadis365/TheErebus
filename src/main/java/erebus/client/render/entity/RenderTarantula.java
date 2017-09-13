package erebus.client.render.entity;

import erebus.client.model.entity.ModelTarantula;
import erebus.entity.EntityTarantula;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTarantula extends RenderLiving<EntityTarantula> {

	private final ResourceLocation[] TEXTURES = new ResourceLocation[] {
			new ResourceLocation("erebus:textures/entity/tarantula.png"),
			new ResourceLocation("erebus:textures/entity/tarantula_turqoise.png"),
			new ResourceLocation("erebus:textures/entity/tarantula_yellow.png") };

	public RenderTarantula(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelTarantula(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTarantula tarantula) {
		return TEXTURES[tarantula.getSkin()];
	}
}