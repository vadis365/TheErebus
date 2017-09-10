package erebus.client.render.entity;

import erebus.client.model.entity.ModelAntlion;
import erebus.entity.EntityAntlionMiniBoss;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderAntlionMiniBoss extends RenderLiving<EntityAntlionMiniBoss> {

	private static ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/antlion_sandstone.png");

	public RenderAntlionMiniBoss(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelAntlion(), 1.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityAntlionMiniBoss entity) {
		return TEXTURE;
	}
}