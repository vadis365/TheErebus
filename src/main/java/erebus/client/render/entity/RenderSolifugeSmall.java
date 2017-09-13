package erebus.client.render.entity;

import erebus.client.model.entity.ModelSolifuge;
import erebus.entity.EntitySolifugeSmall;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSolifugeSmall extends RenderLiving<EntitySolifugeSmall> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/solifuge.png");

	public RenderSolifugeSmall(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelSolifuge(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntitySolifugeSmall solifuge, float f) {
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySolifugeSmall solifuge) {
		return TEXTURE;
	}
}