package erebus.client.render.entity;

import erebus.client.model.entity.ModelSolifuge;
import erebus.entity.EntitySolifuge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSolifuge extends RenderLiving<EntitySolifuge> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/solifuge.png");

	public RenderSolifuge(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelSolifuge(), 1F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySolifuge solifuge) {
		return TEXTURE;
	}
}