package erebus.client.render.entity;

import erebus.client.model.entity.ModelScorpion;
import erebus.entity.EntityScorpion;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderScorpion extends RenderLiving<EntityScorpion> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/scorpion.png");

	public RenderScorpion(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelScorpion(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityScorpion scopion) {
		return TEXTURE;
	}
}