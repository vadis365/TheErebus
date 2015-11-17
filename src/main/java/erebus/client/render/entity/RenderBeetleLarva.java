package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelBeetleLarva;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBombardierBeetleLarva;

@SideOnly(Side.CLIENT)
public class RenderBeetleLarva extends RenderLiving {

	private final ResourceLocation[] TEXTURES = new ResourceLocation[] { new ResourceLocation("erebus:textures/entity/beetleLarva.png"), new ResourceLocation("erebus:textures/entity/beetleLarvaBombardier.png") };

	public RenderBeetleLarva() {
		super(new ModelBeetleLarva(), 0.3F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		float larvaSize = ((EntityBeetleLarva) entityliving).getLarvaSize();
		EntityBeetleLarva larva = (EntityBeetleLarva) entityliving;
		GL11.glScalef(larvaSize, larvaSize, larvaSize);
		if(larva instanceof EntityBombardierBeetleLarva) {
			int size = ((EntityBombardierBeetleLarva) larva).getInflateSize();
			GL11.glScalef((float) (size * 0.009 + larvaSize), (float) (size * 0.009 + larvaSize), (float) (-size * 0.0025 + larvaSize));
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityBeetleLarva larva = (EntityBeetleLarva) entity;
		if (larva.getTame() == 4)
			return TEXTURES[1];
		else
			return TEXTURES[0];
	}
}