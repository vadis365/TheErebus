package erebus.client.render.entity;

import erebus.client.model.entity.ModelBotFly;
import erebus.entity.EntityBotFly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBotFly extends RenderLiving<EntityBotFly> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/bot_fly.png");

	public RenderBotFly(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelBotFly(), 0.3F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBotFly entity) {
		return TEXTURE;
	}
}