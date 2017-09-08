package erebus.client.render.entity;

import erebus.client.model.entity.ModelBeetleLarva;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBombardierBeetleLarva;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBeetleLarva extends RenderLiving<EntityBeetleLarva> {

	private static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
			new ResourceLocation("erebus:textures/entity/beetle_larva.png"),
			new ResourceLocation("erebus:textures/entity/beetle_larva_bombardier.png"),
			new ResourceLocation("erebus:textures/entity/beetle_larva_stag.png") };

	public RenderBeetleLarva(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelBeetleLarva(), 0.3F);
	}

	@Override
	protected void preRenderCallback(EntityBeetleLarva larva, float partialTickTime) {
		float larvaSize = larva.getLarvaSize();
		if (!(larva instanceof EntityBombardierBeetleLarva))
			GlStateManager.scale(larvaSize, larvaSize, larvaSize);
		if (larva instanceof EntityBombardierBeetleLarva) {
			int size = ((EntityBombardierBeetleLarva) larva).getInflateSize();
			GlStateManager.scale((float) (size * 0.009 + larvaSize), (float) (size * 0.009 + larvaSize), (float) (-size * 0.0025 + larvaSize));
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBeetleLarva larva) {
		if (larva.getLarvaType() == 4)
			return TEXTURES[1];
		else if (larva.getLarvaType() == 5)
			return TEXTURES[2];
		else
			return TEXTURES[0];
	}
}