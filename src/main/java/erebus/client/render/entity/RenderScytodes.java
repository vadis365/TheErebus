package erebus.client.render.entity;

import erebus.client.model.entity.ModelScytodes;
import erebus.entity.EntityScytodes;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderScytodes extends RenderLiving<EntityScytodes> {

	private static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
			new ResourceLocation("erebus:textures/entity/scytodes_1.png"),
			new ResourceLocation("erebus:textures/entity/scytodes_2.png"),
			new ResourceLocation("erebus:textures/entity/scytodes_3.png"),
			new ResourceLocation("erebus:textures/entity/scytodes_4.png") };

	public RenderScytodes(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelScytodes(), 1.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityScytodes scytodes) {
		return TEXTURES[scytodes.getSkin()];
	}
}