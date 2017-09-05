package erebus.client.render.entity;

import erebus.client.model.entity.ModelBeetle;
import erebus.entity.EntityBeetle;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBeetle extends RenderLiving<EntityBeetle> {
	private static final ResourceLocation[] TEXTURE = new ResourceLocation[] {
			new ResourceLocation("erebus:textures/entity/beetle_rare_spawn.png"),
			new ResourceLocation("erebus:textures/entity/beetle_blue.png"),
			new ResourceLocation("erebus:textures/entity/beetle_brown.png"),
			new ResourceLocation("erebus:textures/entity/beetle_green.png"),
			new ResourceLocation("erebus:textures/entity/beetle_red.png"),
			new ResourceLocation("erebus:textures/entity/beetle_tan.png") };

	public RenderBeetle(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelBeetle(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBeetle beetle) {
		if (beetle.getSkin() > 0 && beetle.getSkin() <= 10)
			return TEXTURE[1];
		else if (beetle.getSkin() > 10 && beetle.getSkin() <= 20)
			return TEXTURE[2];
		else if (beetle.getSkin() > 20 && beetle.getSkin() <= 30)
			return TEXTURE[3];
		else if (beetle.getSkin() > 30 && beetle.getSkin() <= 40)
			return TEXTURE[4];
		else if (beetle.getSkin() > 40 && beetle.getSkin() <= 50)
			return TEXTURE[5];
		else if (beetle.getSkin() == 0)
			return TEXTURE[0];
		else
			return null;
	}
}