package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelGrasshopper;

@SideOnly(Side.CLIENT)
public class RenderGrasshopper extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/grasshopper.png");

	public RenderGrasshopper() {
		super(new ModelGrasshopper(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}