package erebus.client.render.entity;

import erebus.client.model.entity.ModelFireAnt;
import erebus.entity.EntityZombieAnt;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderZombieAnt extends RenderLiving<EntityZombieAnt> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/zombie_ant.png");

	public RenderZombieAnt(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelFireAnt(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityZombieAnt ant) {
		return TEXTURE;
	}
}