package erebus.client.render.entity;

import erebus.client.model.entity.ModelDragonfly;
import erebus.entity.EntityDragonfly;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDragonfly extends RenderLiving<EntityDragonfly>{
	private static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
			new ResourceLocation("erebus:textures/entity/dragonfly_ender.png"),
			new ResourceLocation("erebus:textures/entity/dragonfly_green.png"),
			new ResourceLocation("erebus:textures/entity/dragonfly_red.png"),
			new ResourceLocation("erebus:textures/entity/dragonfly_purple.png"),
			new ResourceLocation("erebus:textures/entity/dragonfly_blue.png"),
			new ResourceLocation("erebus:textures/entity/dragonfly_tan.png") };

	public RenderDragonfly(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelDragonfly(), 0.3F);
	}

	@Override
	protected void preRenderCallback(EntityDragonfly dragonfly, float partialTickTime) {
		// Other sizes to be added
		GlStateManager.scale(1.0F, 1.0F, 1.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDragonfly dragonfly) {
		if (dragonfly.getSkin() > 0 && dragonfly.getSkin() <= 10)
			return TEXTURES[1];
		else if (dragonfly.getSkin() > 10 && dragonfly.getSkin() <= 20)
			return TEXTURES[2];
		else if (dragonfly.getSkin() > 20 && dragonfly.getSkin() <= 30)
			return TEXTURES[3];
		else if (dragonfly.getSkin() > 30 && dragonfly.getSkin() <= 40)
			return TEXTURES[4];
		else if (dragonfly.getSkin() > 40 && dragonfly.getSkin() <= 50)
			return TEXTURES[5];
		else if (dragonfly.getSkin() == 0)
			return TEXTURES[0];
		else
			return TEXTURES[1];
	}
}