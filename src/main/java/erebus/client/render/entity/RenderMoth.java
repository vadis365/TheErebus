package erebus.client.render.entity;

import erebus.client.model.entity.ModelMoth;
import erebus.entity.EntityMoth;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMoth extends RenderLiving<EntityMoth> {
	private static final ResourceLocation TEXTURE_1 = new ResourceLocation("erebus:textures/entity/moth_1.png");
	private static final ResourceLocation TEXTURE_2 = new ResourceLocation("erebus:textures/entity/moth_2.png");
	private static final ResourceLocation TEXTURE_3 = new ResourceLocation("erebus:textures/entity/moth_3.png");

	public RenderMoth(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelMoth(), 0.3F);
	}

	@Override
	protected void preRenderCallback(EntityMoth moth, float partialTickTime ) {
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMoth moth) {
		switch (moth.getSkin()) {
			case 0:
				return TEXTURE_1;
			case 1:
				return TEXTURE_2;
			case 2:
				return TEXTURE_3;
		}
		return TEXTURE_1;
	}
}