package erebus.client.render.entity;

import erebus.client.model.entity.ModelGrasshopper;
import erebus.entity.EntityGrasshopper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGrasshopper extends RenderLiving<EntityGrasshopper> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/grasshopper.png");

	public RenderGrasshopper(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelGrasshopper(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGrasshopper grasshopper) {
		return TEXTURE;
	}
}