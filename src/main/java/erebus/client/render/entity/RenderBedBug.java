package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelBeetle;
import erebus.entity.EntityBedBug;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBedBug extends RenderLiving<EntityBedBug> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/bed_bug.png");

	public RenderBedBug(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelBeetle(), 0.3F);
	}

	@Override
	protected void preRenderCallback(EntityBedBug bedbug, float partialTickTime) {
		GL11.glScalef(0.75F, 0.75F, 0.75F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBedBug bedbug) {
		return TEXTURE;
	}
}