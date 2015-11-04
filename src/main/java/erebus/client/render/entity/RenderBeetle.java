package erebus.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelBeetle;
import erebus.entity.EntityBeetle;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderBeetle extends RenderLiving {
	private static final ResourceLocation[] textures = new ResourceLocation[] { new ResourceLocation("erebus:textures/entity/beetleRareSpawn.png"), new ResourceLocation("erebus:textures/entity/beetleBlue.png"), new ResourceLocation("erebus:textures/entity/beetleBrown.png"), new ResourceLocation("erebus:textures/entity/beetleGreen.png"), new ResourceLocation("erebus:textures/entity/beetleRed.png"), new ResourceLocation("erebus:textures/entity/beetleTan.png") };

	public RenderBeetle() {
		super(new ModelBeetle(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityBeetle beetle = (EntityBeetle) entity;
		if (beetle.getSkin() > 0 && beetle.getSkin() <= 10)
			return textures[1];
		else if (beetle.getSkin() > 10 && beetle.getSkin() <= 20)
			return textures[2];
		else if (beetle.getSkin() > 20 && beetle.getSkin() <= 30)
			return textures[3];
		else if (beetle.getSkin() > 30 && beetle.getSkin() <= 40)
			return textures[4];
		else if (beetle.getSkin() > 40 && beetle.getSkin() <= 50)
			return textures[5];
		else if (beetle.getSkin() == 0)
			return textures[0];
		else
			return null;
	}
}