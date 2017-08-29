package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelTitanBeetle;
import erebus.entity.EntityTitanBeetle;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderTitanBeetle extends RenderLiving {
	private static final ResourceLocation[] textures = new ResourceLocation[] { new ResourceLocation("erebus:textures/entity/titanBeetle.png"), new ResourceLocation("erebus:textures/entity/titanBeetleKit.png"), new ResourceLocation("erebus:textures/entity/titanBeetleChested.png"), new ResourceLocation("erebus:textures/entity/titanBeetleEnderChested.png") };

	public RenderTitanBeetle() {
		super(new ModelTitanBeetle(), 1.5F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		float f1 = 1.5F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityTitanBeetle beetle = (EntityTitanBeetle) entity;
		if (beetle.getTameState() < 2)
			return textures[0];
		if (beetle.getTameState() == 2)
			return textures[1];
		if (beetle.getTameState() == 3)
			return textures[2];
		else
			return textures[3];
	}
}