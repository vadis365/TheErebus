package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelScytodes;
import erebus.entity.EntityMoneySpider;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderMoneySpider extends RenderLiving {

	private static final ResourceLocation[] TEXTURES = new ResourceLocation[] { new ResourceLocation("erebus:textures/entity/moneySpider.png"), new ResourceLocation("erebus:textures/entity/moneySpiderEuro.png"), new ResourceLocation("erebus:textures/entity/moneySpiderPound.png") };

	public RenderMoneySpider() {
		super(new ModelScytodes(), 0.15F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float par2) {
		GL11.glScalef(0.3F, 0.3F, 0.3F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURES[Math.min(TEXTURES.length - 1, ((EntityMoneySpider) entity).getSkin())];
	}
}