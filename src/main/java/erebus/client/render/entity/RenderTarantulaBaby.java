package erebus.client.render.entity;

import erebus.client.model.entity.ModelTarantula;
import erebus.entity.EntityTarantulaBaby;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTarantulaBaby extends RenderLiving<EntityTarantulaBaby> {

	private final ResourceLocation[] TEXTURES = new ResourceLocation[] {
			new ResourceLocation("erebus:textures/entity/tarantula.png"),
			new ResourceLocation("erebus:textures/entity/tarantula_turqoise.png"),
			new ResourceLocation("erebus:textures/entity/tarantula_yellow.png") };
	
	public RenderTarantulaBaby(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelTarantula(), 0.25F);
	}

	@Override
	protected void preRenderCallback(EntityTarantulaBaby tarantula, float partialTickTime) {
		GlStateManager.scale(0.3F, 0.3F, 0.3F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTarantulaBaby tarantula) {
		return TEXTURES[tarantula.getSkin()];
	}
}