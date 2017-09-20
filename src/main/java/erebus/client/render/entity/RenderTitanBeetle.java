package erebus.client.render.entity;

import erebus.client.model.entity.ModelTitanBeetle;
import erebus.entity.EntityTitanBeetle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTitanBeetle extends RenderLiving <EntityTitanBeetle>{
	private static final ResourceLocation[] TEXTURES = new ResourceLocation[] { new ResourceLocation("erebus:textures/entity/titan_beetle.png"), new ResourceLocation("erebus:textures/entity/titan_beetle_kit.png"), new ResourceLocation("erebus:textures/entity/titan_beetle_chested.png"), new ResourceLocation("erebus:textures/entity/titan_beetle_ender_chested.png") };

	public RenderTitanBeetle(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelTitanBeetle(), 1.5F);
	}

	@Override
	protected void preRenderCallback(EntityTitanBeetle beetle, float partialTickTime ) {
		GlStateManager.scale(1.5F, 1.5F, 1.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTitanBeetle beetle) {
		if (beetle.getTameState() < 2)
			return TEXTURES[0];
		if (beetle.getTameState() == 2)
			return TEXTURES[1];
		if (beetle.getTameState() == 3)
			return TEXTURES[2];
		else
			return TEXTURES[3];
	}
}