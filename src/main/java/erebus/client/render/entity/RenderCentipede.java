package erebus.client.render.entity;

import erebus.client.model.entity.ModelCentipede;
import erebus.entity.EntityCentipede;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCentipede extends RenderLiving<EntityCentipede> {
	private static final ResourceLocation TEXTURE_1 = new ResourceLocation("erebus:textures/entity/centipede.png");
	private static final ResourceLocation TEXTURE_2 = new ResourceLocation("erebus:textures/entity/centipede_light.png");
	private static final ResourceLocation TEXTURE_3 = new ResourceLocation("erebus:textures/entity/centipede_black.png");

	public RenderCentipede(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelCentipede(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCentipede centipede) {
		switch (centipede.getSkin()) {
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