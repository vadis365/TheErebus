package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelScytodes;
import erebus.entity.EntityMoneySpider;

@SideOnly(Side.CLIENT)
public class RenderMoneySpider extends RenderLiving {
	private static final ResourceLocation[] textures = new ResourceLocation[] { new ResourceLocation("erebus:textures/entity/moneySpider.png"), new ResourceLocation("erebus:textures/entity/moneySpiderEuro.png"), new ResourceLocation("erebus:textures/entity/moneySpiderPound.png") };

	public RenderMoneySpider(ModelScytodes model, float shadowSize) {
		super(model, shadowSize * 0.3F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float par2) {
		GL11.glScalef(0.3F, 0.3F, 0.3F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return textures[Math.min(textures.length - 1, ((EntityMoneySpider) entity).skin)];
	}
}