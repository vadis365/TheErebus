package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelCentipede;
import erebus.entity.EntityCentipede;

@SideOnly(Side.CLIENT)
public class RenderCentipede extends RenderLiving {
	private static final ResourceLocation resource1 = new ResourceLocation("erebus:textures/entity/centipede.png");
	private static final ResourceLocation resource2 = new ResourceLocation("erebus:textures/entity/centipedeLight.png");
	private static final ResourceLocation resource3 = new ResourceLocation("erebus:textures/entity/centipedeBlack.png");

	public RenderCentipede() {
		super(new ModelCentipede(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityCentipede entityCentipede = (EntityCentipede) entity;
		switch (entityCentipede.getSkin()) {
			case 0:
				return resource1;
			case 1:
				return resource2;
			case 2:
				return resource3;
		}
		return resource1;
	}
}